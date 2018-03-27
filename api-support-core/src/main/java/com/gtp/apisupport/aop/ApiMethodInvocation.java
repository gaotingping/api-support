package com.gtp.apisupport.aop;

/**
 * 动态代理给接口方法提供一种简便的动态代理(AOP:环绕通知)
 * 关注重点是方法的执行拦截
 * 
 * @author gaotingping@cyberzone.cn
 */
public interface ApiMethodInvocation {
	
    public Object invoke(ApiInvocation invocation)throws Throwable;
    
    public Object plugin(Object target);
    
}
