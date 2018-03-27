package com.gtp.apisupport.codecs;

import com.gtp.apisupport.model.ApiResult;

/**
 * 输入输出编码和解码，纯粹是为了适配总线
 * 默认提供:
 *    1.总线2.0适配
 *    2.总线3.0适配
 * 
 * @author gaotingping@cyberzone.cn
 */
public interface MessageDecoder {

	String decode(String body);
	
	String encode(ApiResult result);
}
