package com.gtp.apisupport.dispatcher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gtp.apisupport.model.ApiResult;

/**
 * 服务转发器:参数解析，必要性验证
 */
public interface ServiceDispatcher{
	
	/**
	 * 服务转发
	 * 
	 * @param body  请求内容
	 * @param request
	 * @param response
	 * @return
	 */
	public ApiResult doService(String body,HttpServletRequest request, HttpServletResponse response) throws Exception;
	
}
