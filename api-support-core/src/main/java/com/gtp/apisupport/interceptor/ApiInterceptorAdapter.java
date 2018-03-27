package com.gtp.apisupport.interceptor;

import com.gtp.apisupport.model.ApiContext;

/**
 * 拦截器适配器
 * 
 * @author gaotingping@cyberzone.cn
 */
public abstract class ApiInterceptorAdapter implements ApiInterceptor{

    public boolean preHandle(ApiContext rc){
    	return true;
    }
    
    public boolean postHandle(ApiContext rc){
    	return true;
    }
}
