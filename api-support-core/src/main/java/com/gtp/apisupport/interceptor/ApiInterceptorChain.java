package com.gtp.apisupport.interceptor;

import java.util.ArrayList;
import java.util.List;

import com.gtp.apisupport.model.ApiContext;

/**
 * 多个拦截器如何管理：
 * 说拦截器不妥，有点过滤器的味道(过程处理器:前置和后置)
 * 
 * @author gaotingping
 *
 *         2016年11月16日 下午5:56:39
 */
public class ApiInterceptorChain {

	private List<ApiInterceptor> interceptors = new ArrayList<ApiInterceptor>();

	//循环所有拦截器列表
	public boolean preAll(ApiContext rc) {
		
		//正序执行
		for (int i=0;i<interceptors.size();i++) {
			ApiInterceptor interceptor=interceptors.get(i);
			boolean f = interceptor.preHandle(rc);
			if(!f){ //false 回去了
				return f;
			}
		}
		
		return true;
	}
	
	public void postAll(ApiContext rc) {
		
		//反序执行
		for (int i=interceptors.size()-1;i>-1;i--) {
			ApiInterceptor interceptor=interceptors.get(i);
			boolean f = interceptor.postHandle(rc);
			if(!f){ //false 回去了
				break;
			}
		}
		
	}

	public void addInterceptor(ApiInterceptor interceptor) {
		interceptors.add(interceptor);
	}

	public List<ApiInterceptor> getInterceptors() {
		return interceptors;
	}

	public void setInterceptors(List<ApiInterceptor> interceptors) {
		this.interceptors = interceptors;
	}
}
