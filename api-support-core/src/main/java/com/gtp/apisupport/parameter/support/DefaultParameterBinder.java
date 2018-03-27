package com.gtp.apisupport.parameter.support;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.gtp.apisupport.common.ReflectUtil;
import com.gtp.apisupport.model.ApiParamInfo;
import com.gtp.apisupport.parameter.ParameterBinder;	

@Component
public class DefaultParameterBinder implements ParameterBinder{

	/**
	 * 一个方法的参数是固定的，所以这块数据可缓存
	 */
	private static Map<String, List<ApiParamInfo>> cacheParms = new ConcurrentHashMap<String,List<ApiParamInfo>>();

	public List<ApiParamInfo> getParamNames(String serviceCode,Method method) {
		
		List<ApiParamInfo> params = cacheParms.get(serviceCode);
		
		/*不考虑并发处理，并发下也最多是多执行一次不会导致数据错误*/
		if(params==null){
			params = ReflectUtil.getParameterInfo(method);
			if(!params.isEmpty()){
				cacheParms.put(serviceCode, params);
			}
		}
		
		return params;
	}

	public Object[] getParamValues(Method method,List<ApiParamInfo> paramNames,
			JSONObject args) {
		
		int size=paramNames.size();
		Object [] paramValues = new Object[size];
		for (int i = 0; i < size; i++) {
			ApiParamInfo bp = paramNames.get(i);
			paramValues[i] = getInptVal(method,args,bp);
		}
		
		return paramValues;
	}

	private Object getInptVal(Method method,JSONObject args, ApiParamInfo p) {
		if(p.getApiParam()!=null){
			if(p.getIsList()){ /*列表的话取得真实类型*/
				String v = args.getString(p.getApiParam().value());
				return JSON.parseArray(v,p.getType());
			}else{
				return args.getObject(p.getApiParam().value(), p.getType());
			}
		}else{
			return JSONObject.parseObject(args.toJSONString(), p.getType());
		}
	}
}
