package com.gtp.apisupport.aop;

import java.util.ArrayList;
import java.util.List;

/**
 * 多个拦截器如何管理：
 * 
 * @author gaotingping
 *
 *         2016年11月16日 下午5:56:39
 */
public class ApiInvocationChain {

	private final List<ApiMethodInvocation> invocations = new ArrayList<ApiMethodInvocation>();

	// 在所有的拦截器行注册目标对象
	public Object pluginAll(Object target) {
		for (ApiMethodInvocation invocation : invocations) {
			target = invocation.plugin(target);
		}
		return target;
	}

	public void addInvocation(ApiMethodInvocation invocation) {
		invocations.add(invocation);
	}

	public List<ApiMethodInvocation> getInvocations() {
		return invocations;
	}
}
