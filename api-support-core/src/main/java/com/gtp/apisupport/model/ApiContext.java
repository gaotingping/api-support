package com.gtp.apisupport.model;

import java.io.Serializable;
import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.gtp.apisupport.annotation.ApiDescribe;

/**
 * 请求上下文
 * 
 * @author gaotingping@cyberzone.cn
 */
@ApiDescribe("请求上下文")
public class ApiContext implements Serializable {

	private static final long serialVersionUID = 423696669690776574L;

	@ApiDescribe("请求内容体")
	private JSONObject body;
	
	@ApiDescribe("request")
	private HttpServletRequest request;
	
	@ApiDescribe("response")
	private HttpServletResponse response;
	
	@ApiDescribe("模块")
	private String serviceModule;

	@ApiDescribe("服务")
	private String serviceNumber;
	
	@ApiDescribe("参数")
	private JSONObject args;
	
	@ApiDescribe("token")
	private String token;
	
	@ApiDescribe("方法")
	private Method method;
	
	@ApiDescribe("实例")
	private Object instance;
	
	@ApiDescribe("执行结果")
	private ApiResult result;

	public JSONObject getBody() {
		return body;
	}

	public void setBody(JSONObject body) {
		this.body = body;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public HttpServletResponse getResponse() {
		return response;
	}

	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}

	public Method getMethod() {
		return method;
	}

	public void setMethod(Method method) {
		this.method = method;
	}

	public String getServiceModule() {
		return serviceModule;
	}

	public void setServiceModule(String serviceModule) {
		this.serviceModule = serviceModule;
	}

	public String getServiceNumber() {
		return serviceNumber;
	}

	public void setServiceNumber(String serviceNumber) {
		this.serviceNumber = serviceNumber;
	}
	
	public JSONObject getArgs() {
		return args;
	}

	public void setArgs(JSONObject args) {
		this.args = args;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public ApiResult getResult() {
		return result;
	}

	public void setResult(ApiResult result) {
		this.result = result;
	}

	public Object getInstance() {
		return instance;
	}

	public void setInstance(Object instance) {
		this.instance = instance;
	}
}
