package com.gtp.apisupport.doc;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.gtp.apisupport.annotation.ApiMethod;
import com.gtp.apisupport.annotation.ApiService;
import com.gtp.apisupport.common.JsonUtils;
import com.gtp.apisupport.common.ScannerUtils;

/**
 * 构造文档左侧树结构
 *  格式=[{
 		"text":描述,
  		children:"子节点json"
    }]
 */
public class AutoBuildJsTree {

	/**
	 * 构建接口文档
	 * 
	 * @param docPath		文档输出地址
	 * @param p				所需要扫描的包
	 * @throws Exception
	 */
	public static void build(String docPath, List<String> p) throws Exception {
		
		JSONArray treeInfo=new JSONArray();
		
		List<Class<?>> list = ScannerUtils.getServiceEntry(p,ApiService.class);
		if(list == null || list.isEmpty()){
			return;
		}

		for (Class<?> c:list) {
			
			JSONObject mInfo=new JSONObject();
			ApiService apiService=c.getAnnotation(ApiService.class);
			mInfo.put("text", apiService.value());
			
			Method[] ms = c.getDeclaredMethods();
			
			JSONArray tmpData=new JSONArray();
			for (Method m:ms) {
				
				ApiMethod apiMethod=m.getAnnotation(ApiMethod.class);
				JSONObject tmp=new JSONObject();
				tmp.put("text", apiMethod.desc());
				tmp.put("code", apiMethod.value());
				tmp.put("mName", m.getName());
				tmpData.add(tmp);
				
			}
			mInfo.put("children", tmpData);
			treeInfo.add(mInfo);
		}
		
		//写出
		File file=getFileByPath(docPath);
		PrintWriter out = new PrintWriter(file);
		out.write("var treeData = ");
		out.write(JsonUtils.formatJson(treeInfo.toJSONString()));
		out.write("\r\n");
		out.close();
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
