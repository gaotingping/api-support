package com.gtp.apisupport.parameter;

import java.lang.reflect.Method;
import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.gtp.apisupport.model.ApiParamInfo;

/**
 * 参数绑定
 * 
 * @author gaotingping@cyberzone.cn
 */
public interface ParameterBinder {

	/**
	 * 获得参数上的所有注解
	 * 
	 * @param serviceCode  服务编码
	 * @param method       方法
	 * 
	 * @return
	 */
	public List<ApiParamInfo> getParamNames(String serviceCode,Method method);


	/**
	 * FIXME:可以考虑，注入request,response,让接口有了解上下文的能力
	 * 
	 * 根据参数名称获得参数对应的输入值
	 * 
	 * @param method       方法
	 * @param paramNames   参数名称
	 * @param args         输入的参数集合
	 *    
	 * @return
	 */
	public Object[] getParamValues(Method method,List<ApiParamInfo> paramNames,JSONObject args);
}
