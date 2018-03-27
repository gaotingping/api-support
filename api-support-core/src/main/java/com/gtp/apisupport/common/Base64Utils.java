package com.gtp.apisupport.common;

import javax.xml.bind.DatatypeConverter;

/**
 * base64就用jdk的，性能也不错！
 * 
 * @author gaotingping@cyberzone.cn
 *
 */
public class Base64Utils {

	public static String encode(byte[] b) {
		return DatatypeConverter.printBase64Binary(b);
	}

	public static byte[] decode(String str) {
		return DatatypeConverter.parseBase64Binary(str);
	}
}
