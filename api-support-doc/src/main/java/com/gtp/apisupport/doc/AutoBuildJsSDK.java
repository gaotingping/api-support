package com.gtp.apisupport.doc;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.List;

import com.gtp.apisupport.annotation.ApiMethod;
import com.gtp.apisupport.annotation.ApiService;
import com.gtp.apisupport.common.ScannerUtils;
import com.gtp.apisupport.doc.template.TemplateTool;

/**
 * 构建js sdk文档
 */
public class AutoBuildJsSDK {

	/**
	 * 构建接口文档
	 * 
	 * @param docPath		文档输出地址
	 * @param p				所需要扫描的包
	 * @throws Exception
	 */
	public static void build(String docPath, List<String> p) throws Exception {

		File file=getFileByPath(docPath);
		PrintWriter out = new PrintWriter(file);
		
		//sdk-info
		out.write(TemplateTool.getJsSDKInfo());
		out.write("\r\n");
		
		List<Class<?>> list = ScannerUtils.getServiceEntry(p,ApiService.class);
		if(list == null || list.isEmpty()){
			out.close();
			return;
		}

		for (Class<?> c:list) {
			ApiService apiService=c.getAnnotation(ApiService.class);
			
			Method[] ms = c.getDeclaredMethods();
			for (Method m:ms) {
				
				ApiMethod apiMethod=m.getAnnotation(ApiMethod.class);
				
				String methodDescribe=apiService.value()+"-"+apiMethod.desc();
				String methodName=m.getName();
				String methodNumber=apiMethod.value();
				out.write(TemplateTool.getJsSdk(methodDescribe, methodName, methodNumber));
				out.write("\r\n");
			}
		}

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
