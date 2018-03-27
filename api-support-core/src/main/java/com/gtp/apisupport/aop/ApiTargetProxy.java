package com.gtp.apisupport.aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * jdk动态代理，处理拦截逻辑
 */
public class ApiTargetProxy implements InvocationHandler {
	
	private Object target;
	
	private ApiMethodInvocation invocation ;
	
	private ApiTargetProxy(Object target, ApiMethodInvocation invocation) {
		this.target = target;
		this.invocation = invocation;
	}
	
	public static Object bind(Object target, ApiMethodInvocation interceptor) {
		return Proxy.newProxyInstance(target.getClass().getClassLoader(), 
						   target.getClass().getInterfaces(),
						   new ApiTargetProxy(target, interceptor));
	}
	
	public Object invoke(Object proxy, Method method,Object[] args) throws Throwable {
		 return invocation.invoke(new ApiInvocation(target,method,args));
	}
}
