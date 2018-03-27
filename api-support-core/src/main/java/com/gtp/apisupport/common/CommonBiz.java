package com.gtp.apisupport.common;

import java.util.List;

import com.gtp.apisupport.model.ApiParamInfo;

/**
 * 公共逻辑
 * 
 * @author gaotingping@cyberzone.cn
 *
 */
public class CommonBiz {

	public static boolean isEmpty(String p) {
		
		if(p == null || "".equals(p)){
			return true;
		}
		
		return false;
	}

	public static boolean isEquals(String p1, String p2) {
		
		if(isEmpty(p1) || isEmpty(p2)){
			return false;
		}
		
		return p1.equals(p2);
	}

	public static boolean isEmpty(List<ApiParamInfo> list) {
		
		if(list == null || list.isEmpty()){
			return true;
		}
		
		return false;
	}

	public static boolean isId(Long id) {
		if(id == null || id<1){
			return false;
		}
		return true;
	}

}
