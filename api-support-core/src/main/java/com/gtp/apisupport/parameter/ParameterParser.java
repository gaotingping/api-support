package com.gtp.apisupport.parameter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gtp.apisupport.model.ApiContext;

/**
 * 参数解析
 * 
 * @author gaotingping@cyberzone.cn
 */
public interface ParameterParser {

	/**
	 * 参数解析
	 * 
	 * @param body
	 * @param request
	 * @param response
	 * @return
	 */
	public ApiContext parser(String body,HttpServletRequest request, HttpServletResponse response);
}
