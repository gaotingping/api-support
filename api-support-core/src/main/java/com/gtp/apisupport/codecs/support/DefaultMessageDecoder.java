package com.gtp.apisupport.codecs.support;

import com.gtp.apisupport.codecs.MessageDecoder;
import com.gtp.apisupport.common.JsonUtils;
import com.gtp.apisupport.model.ApiResult;

/**
 * 编码实现
 * 
 * @author gaotingping@cyberzone.cn
 */
public class DefaultMessageDecoder implements MessageDecoder{

	@Override
	public String decode(String body) {
		return body;
	}

	@Override
	public String encode(ApiResult result) {
		return JsonUtils.toJsonStr(result);
	}
	
}
