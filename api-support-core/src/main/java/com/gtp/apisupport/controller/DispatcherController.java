package com.gtp.apisupport.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gtp.apisupport.codecs.MessageDecoder;
import com.gtp.apisupport.codecs.support.DefaultMessageDecoder;
import com.gtp.apisupport.dispatcher.ServiceDispatcher;
import com.gtp.apisupport.enums.ApiErrorCode;
import com.gtp.apisupport.model.ApiResult;

/**
 * 服务入口:完成参数接收，解码和响应输出，编码
 * 单纯的servlet也是可以的
 * 
 * @author gaotingping@cyberzone.cn
 */
@Controller
public class DispatcherController {

	@Autowired
	private ServiceDispatcher serviceDispatcher;
	
	private MessageDecoder messageDecoder=new DefaultMessageDecoder();
	
	private static final Logger logger = LoggerFactory.getLogger(DispatcherController.class);
	
	@RequestMapping(method = RequestMethod.POST, value = "/services",produces = {"text/html; charset=utf-8"})
	@ResponseBody
	public String doService(@RequestBody String body,HttpServletRequest request,HttpServletResponse response) {
		
		response.setHeader("Access-Control-Allow-Origin", "*");
		
		try{
			
			//step1:解码
			String message = messageDecoder.decode(body); 
			
			//step2:执行
			ApiResult result = serviceDispatcher.doService(message, request, response);
			
			//step3:编码
			return messageDecoder.encode(result);
			
		}catch(Exception e){
			logger.error(e.getMessage(),e);
			return messageDecoder.encode(ApiResult.failure(ApiErrorCode.SYSTEM_ERROR));
		}
		
	}

	//setter
	public void setMessageDecoder(MessageDecoder messageDecoder) {
		this.messageDecoder = messageDecoder;
	}
}
