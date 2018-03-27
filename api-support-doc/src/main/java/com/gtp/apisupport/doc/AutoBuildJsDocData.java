package com.gtp.apisupport.doc;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.gtp.apisupport.annotation.ApiMethod;
import com.gtp.apisupport.annotation.ApiService;
import com.gtp.apisupport.common.JsonUtils;
import com.gtp.apisupport.common.ReflectUtil;
import com.gtp.apisupport.common.ScannerUtils;
import com.gtp.apisupport.doc.common.DocReflectUtil;
import com.gtp.apisupport.model.ApiParamInfo;

/**
构造js文档方法详情数据
 格式=[
	 code:{ //方法编码
	   req:{}, //输入
	   resp:{} //输出
	 }
 ]
 */
public class AutoBuildJsDocData {


	/**
	 * 构建接口文档
	 * 
	 * @param docPath		文档输出地址
	 * @param p				所需要扫描的包
	 * @throws Exception
	 */
	public static void build(String docPath, List<String> p) throws Exception {

		List<Class<?>> list = ScannerUtils.getServiceEntry(p,ApiService.class);
		if(list == null || list.isEmpty()){
			return;
		}
		
		JSONObject data=new JSONObject();
		
		for (Class<?> c:list) {

			Method[] ms = c.getDeclaredMethods();
			for (Method m:ms) {
				
				if (Modifier.isPublic(m.getModifiers()) && m.getDeclaringClass() != Object.class) {

					ApiMethod apiMethod = m.getAnnotation(ApiMethod.class);
					if (apiMethod != null) {

						JSONObject mInfo=new JSONObject();

						// 输入						
						JSONObject args = new JSONObject();
						List<ApiParamInfo> params = ReflectUtil.getParameterInfo(m);
						if(params!=null){
							for(ApiParamInfo p3:params){
								if(p3.getApiParam()!=null){
									if(p3.getIsList()){
										JSONArray tmpData=new JSONArray();
										tmpData.add(DocReflectUtil.allFields(p3.getType()));
										args.put(p3.getApiParam().value(),tmpData);
									}else if(DocReflectUtil.isBaseType(p3.getType())){
										args.put(p3.getApiParam().value(),p3.getApiParam().desc());
									}else{
										args.put(p3.getApiParam().value(), DocReflectUtil.allFields(p3.getType()));
									}
								}else{
									//没加注解
									if(p3.getIsList()){
										throw new RuntimeException("API接口不支持直接注入无注解List!"+m);
									}else if(DocReflectUtil.isBaseType(p3.getType())){
										throw new RuntimeException("API接口不支持直接注入无注解基本类型!"+m);
									}else{
										args=DocReflectUtil.allFields(p3.getType());
									}
								}
							}
						}
						mInfo.put("req",args);

						// 输出
						Class<?> r = m.getReturnType();
						if(r == List.class){ //List
							Class<?> tmpC = getClassByType(m.getGenericReturnType());
							JSONObject returnJson = DocReflectUtil.allFields(tmpC);
							JSONArray returnData=new JSONArray();
							returnData.add(returnJson);
							mInfo.put("resp",returnData);
						}else{
							JSONObject returnJson = DocReflectUtil.allFields(r);
							mInfo.put("resp",returnJson);
						}
						
						data.put(apiMethod.value(), mInfo);
					}
				}
			}
		}

		//写出
		File file=getFileByPath(docPath);
		PrintWriter out = new PrintWriter(file);
		out.write("var msData = ");
		out.write(JsonUtils.formatJson(data.toJSONString()));
		out.write("\r\n");
		out.close();
	}

	private static Class<?> getClassByType(Type t) {
		
		if(t instanceof ParameterizedType){
			Type[] actualTypes = ((ParameterizedType) t).getActualTypeArguments();
			Class<?> tmpC = (Class<?>) actualTypes[0];
			return tmpC;
		}
		
		return null;
	}
	
	private static File getFileByPath(String path) throws IOException {
		File file=new File(path);
		if(!file.exists()){
			file.getParentFile().mkdirs();
			file.createNewFile();
		}
		return file;
	}

}
