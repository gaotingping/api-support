package com.gtp.apisupport.executor.support;

import java.lang.reflect.Method;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.gtp.apisupport.annotation.ApiMethod;
import com.gtp.apisupport.common.CommonBiz;
import com.gtp.apisupport.enums.ApiErrorCode;
import com.gtp.apisupport.executor.DispatcherExecutor;
import com.gtp.apisupport.model.ApiContext;
import com.gtp.apisupport.model.ApiParamInfo;
import com.gtp.apisupport.model.ApiResult;
import com.gtp.apisupport.parameter.ParameterBinder;

@Component
public class DefaultDispatcherExecutor implements DispatcherExecutor {

	@Autowired
	private ParameterBinder parameterBinder;

	@PostConstruct
	public void init() {

	}

	public void execute(ApiContext rc) throws Exception {

		Object instance = rc.getInstance();
		Method method = rc.getMethod();

		/**
		 * step2:形参为空
		 */
		Class<?>[] pts = method.getParameterTypes();
		if (pts == null || pts.length == 0) {
			rc.setResult(doExecute(method, instance, null, rc));
			return;
		}

		/**
		 * step3:形参不为空，实参为空
		 */
		JSONObject args = rc.getArgs();
		if (args == null || args.isEmpty()) {
			rc.setResult(ApiResult.failure(ApiErrorCode.REQUIRED_NOT_SET));
			return;
		}

		/**
		 * step4:跳过参数绑定,适配超复杂类型
		 */
		ApiMethod apiMethod = method.getAnnotation(ApiMethod.class);
		if (apiMethod.skipBP()) {
			rc.setResult(doExecute(method, instance, new Object[] { args }, rc));
			return;
		}

		/**
		 * step5:绑定参数
		 */
		List<ApiParamInfo> paramNames = parameterBinder.getParamNames(rc.getServiceNumber(), method);
		if (!CommonBiz.isEmpty(paramNames)) {
			Object[] paramValues = parameterBinder.getParamValues(method, paramNames, args);
			rc.setResult(doExecute(method, instance, paramValues, rc));
			return;
		} else {
			rc.setResult(doExecute(method, instance, new Object[] { args }, rc));
			return;
		}
	}

	private ApiResult doExecute(Method method, Object instance, Object[] args, ApiContext rc) throws Exception {
		Object result = method.invoke(instance, args);
		ApiResult r = ApiResult.result(result);
		return r;
	}
}
