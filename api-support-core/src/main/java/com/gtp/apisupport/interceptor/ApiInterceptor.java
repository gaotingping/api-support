package com.gtp.apisupport.interceptor;

import com.gtp.apisupport.model.ApiContext;

/**
 * 拦截器的设想
 *   1.能处理或扩展业务需要
 *   2.能了解上下文body,request,response,method
 *   3.简单方便，易用
 *   4.返回值:true/false
 *   5.多拦截器排序:@Order 属性接受整形值。如：1,2 等等。值越小拥有越高的优先级,默认为Integer.MAX_VALUE
 *   			先后顺序如何调整  @See @Order 值越小优先级越高
 *   
 * #有点过程处理器的味道，能在请求执行前后进行一定的处理
 * #但是无法控制执行，若能与ApiFilter的功能整合，就特别满意了
 *   
 * 关注的重点是请求访问的全流程处理能力，能访问请求上下文
 * 
 * @author gaotingping@cyberzone.cn
 */
public interface ApiInterceptor {
	
	/**
	 * 前置拦截器
	 * 
	 * @param rc
	 * @return true继续执行，false返回，返回内容可设置在rc.result中
	 */
    public boolean preHandle(ApiContext rc);
    
    /**
     * 后置拦截器
     * 
     * @param rc 
     * @return true继续执行，false不执行下一个
     */
    public boolean postHandle(ApiContext rc);
    
}
