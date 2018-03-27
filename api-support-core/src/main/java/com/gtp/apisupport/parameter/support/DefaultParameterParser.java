package com.gtp.apisupport.parameter.support;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.gtp.apisupport.common.CommonBiz;
import com.gtp.apisupport.enums.ApiErrorCode;
import com.gtp.apisupport.model.ApiContext;
import com.gtp.apisupport.parameter.ParameterParser;

@Component
public class DefaultParameterParser implements ParameterParser{

	@Override
	public ApiContext parser(String body, HttpServletRequest request, HttpServletResponse response) {
			
		//空
		if(CommonBiz.isEmpty(body)){
			throw new RuntimeException(ApiErrorCode.PARAM_ERROR.getError());
		}
		
		JSONObject json=null;
		try{
			json=JSON.parseObject(body);
		}catch(Exception e){
			throw new RuntimeException(ApiErrorCode.PARAM_NOT_JSON.getError());
		}
				
		ApiContext rc=new ApiContext();
		rc.setBody(json);
		rc.setArgs(json.getJSONObject("args"));
		rc.setServiceModule(json.getString("serviceModule"));
		rc.setServiceNumber(json.getString("serviceNumber"));
		rc.setToken(json.getString("token"));
		rc.setRequest(request);
		rc.setResponse(response);
		
		return rc;
	}
}
