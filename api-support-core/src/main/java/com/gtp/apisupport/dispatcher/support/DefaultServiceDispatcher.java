package com.gtp.apisupport.dispatcher.support;

import java.lang.reflect.Method;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.gtp.apisupport.annotation.ApiMethod;
import com.gtp.apisupport.annotation.ApiService;
import com.gtp.apisupport.aop.ApiInvocationChain;
import com.gtp.apisupport.aop.ApiMethodInvocation;
import com.gtp.apisupport.common.CommonBiz;
import com.gtp.apisupport.common.ScannerUtils;
import com.gtp.apisupport.config.BusSupportConfig;
import com.gtp.apisupport.dispatcher.ServiceDispatcher;
import com.gtp.apisupport.enums.ApiErrorCode;
import com.gtp.apisupport.executor.DispatcherExecutor;
import com.gtp.apisupport.interceptor.ApiInterceptor;
import com.gtp.apisupport.interceptor.ApiInterceptorChain;
import com.gtp.apisupport.model.ApiContext;
import com.gtp.apisupport.model.ApiResult;
import com.gtp.apisupport.parameter.ParameterParser;

@Component
public class DefaultServiceDispatcher implements ServiceDispatcher, ApplicationContextAware {

	private static final Logger logger = LoggerFactory.getLogger(DefaultServiceDispatcher.class);

	@Autowired
	private DispatcherExecutor dispatcherExecutor;

	@Autowired
	private BusSupportConfig config;

	@Autowired
	private ParameterParser parameterParser;

	private ApplicationContext applicationContext = null;

	private static Map<String, Object> flyweights = new HashMap<String, Object>();

	private static Map<String, Method> methods = new HashMap<String, Method>();

	// aop
	private ApiInvocationChain invocationChain = new ApiInvocationChain();

	// 拦截器
	private ApiInterceptorChain interceptorChain = new ApiInterceptorChain();

	@PostConstruct
	public void init() {
		try {

			// aop
			Map<String, ApiMethodInvocation> m1 = applicationContext.getBeansOfType(ApiMethodInvocation.class);
			if (m1 != null && !m1.isEmpty()) {
				Iterator<String> it = m1.keySet().iterator();
				while (it.hasNext()) {
					String k = it.next();
					invocationChain.addInvocation(m1.get(k));
				}
			}

			// 排序
			Collections.sort(invocationChain.getInvocations(), new Comparator<ApiMethodInvocation>() {
				@Override
				public int compare(ApiMethodInvocation o1, ApiMethodInvocation o2) {

					/**
					 * 规则: 1.有标注按标注 2.无标注默认为Integer.MAX_VALUE
					 */
					Order d1 = o1.getClass().getAnnotation(Order.class);
					Order d2 = o2.getClass().getAnnotation(Order.class);

					int v1 = Integer.MAX_VALUE, v2 = Integer.MAX_VALUE;
					if (d1 != null) {
						v1 = d1.value();
					}
					if (d2 != null) {
						v2 = d2.value();
					}

					return v2 - v1;
				}
			});

			// 拦截器
			/**
			 * 拦截器初始化
			 */
			// Interceptor
			Map<String, ApiInterceptor> m2 = applicationContext.getBeansOfType(ApiInterceptor.class);
			if (m2 != null && !m2.isEmpty()) {

				Iterator<String> it = m2.keySet().iterator();
				while (it.hasNext()) {
					String k = it.next();
					interceptorChain.addInterceptor(m2.get(k));
				}

				// 排序
				Collections.sort(interceptorChain.getInterceptors(), new Comparator<ApiInterceptor>() {
					@Override
					public int compare(ApiInterceptor o1, ApiInterceptor o2) {

						/**
						 * 规则: 1.有标注按标注 2.无标注默认为Integer.MAX_VALUE
						 */
						Order d1 = o1.getClass().getAnnotation(Order.class);
						Order d2 = o2.getClass().getAnnotation(Order.class);

						int v1 = Integer.MAX_VALUE, v2 = Integer.MAX_VALUE;
						if (d1 != null) {
							v1 = d1.value();
						}
						if (d2 != null) {
							v2 = d2.value();
						}

						return v1 - v2;
					}
				});
			}

			if (flyweights.isEmpty()) {

				for (Class<?> clazz : ScannerUtils.getServiceEntry(config.getPackages(), ApiService.class)) {

					logger.info("加载接口模块:" + clazz.getName());
					
					//Object target = applicationContext.getBean(method.getDeclaringClass());
					Object target = applicationContext.getBean(clazz);
					target = invocationChain.pluginAll(target);
					
					for (Method method : clazz.getDeclaredMethods()) {
						ApiMethod serviceCode = method.getAnnotation(ApiMethod.class);
						if (serviceCode != null) {
							flyweights.put(serviceCode.value(), target);
							methods.put(serviceCode.value(), method);
						}
					}
				}
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new RuntimeException(e.getMessage(), e);
		}
	}

	@PreDestroy
	public void destroy() {
		flyweights.clear();
		methods.clear();
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

	@Override
	public ApiResult doService(String body, HttpServletRequest request, HttpServletResponse response) throws Exception {

		// step1:参数解析
		ApiContext rc = parameterParser.parser(body, request, response);
		ApiErrorCode tmpError = checkRequest(rc);
		if (tmpError != null) {
			return ApiResult.failure(tmpError);
		}

		// step2:服务转发
		Object instance = flyweights.get(rc.getServiceNumber());
		Method method = methods.get(rc.getServiceNumber());
		if (method == null || instance == null) {
			return ApiResult.failure(ApiErrorCode.SERVICE_CODE_NOT_FIND);
		}
		rc.setMethod(method);
		rc.setInstance(instance);

		// step3:执行与结果
		// 前置拦截器
		if (interceptorChain != null && !interceptorChain.preAll(rc)) {
			return rc.getResult();
		}

		dispatcherExecutor.execute(rc);

		// 后置拦截器
		if (interceptorChain != null) {
			interceptorChain.postAll(rc);
		}

		return rc.getResult();
	}

	// 初步校验请求
	private ApiErrorCode checkRequest(ApiContext rc) {

		String serviceModule = rc.getServiceModule();

		if (!CommonBiz.isEquals(config.getServiceModule(), serviceModule)) {
			return ApiErrorCode.SERVICE_MODULE_ERROR;
		}

		String serviceNumber = rc.getServiceModule();

		if (CommonBiz.isEmpty(serviceNumber)) {
			return ApiErrorCode.SERVICE_CODE_EMPTY;
		}

		return null;
	}
}
