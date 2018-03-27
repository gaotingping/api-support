package com.gtp.apisupport.doc.template;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import com.gtp.apisupport.doc.common.NameMapper;

/**
 * 模板工具
 */
public class TemplateTool {

	/**
	 * 获取serviceimpl实现
	 */
	public static String getServiceImpl(final String packageName, 
			final String imports, 
			final String entityName) throws Exception {
		return template("serviceImpl", new Rowhandler() {
			@Override
			public String handler(String str) {
				// 处理 {0} 包 {1} 导入 {2} 实体名称
				str = str.replace("${packageName}", packageName);
				str = str.replace("${imports}", imports);
				str = str.replace("${entityName}", entityName);
				str = str.replace("${entityName2}", NameMapper.firstLetterLowerCase(entityName));
				str += "\r\n";
				return str;
			}
		});
	}

	/**
	 * 获取servie实现
	 */
	public static String getService(final String packageName, 
			final String imports,
			final String entityName) throws Exception {
		
		return template("service", new Rowhandler() {
			@Override
			public String handler(String str) {
				// 处理 {0} 包 {1} 导入 {2} 实体名称
				str = str.replace("${packageName}", packageName);
				str = str.replace("${imports}", imports);
				str = str.replace("${entityName}", entityName);
				str += "\r\n";
				return str;
			}
		});
	}

	/**
	 * 获取dao实现
	 */
	public static String getDao(final String packageName, 
			final String imports, 
			final String entityName) throws Exception {
		return template("dao", new Rowhandler() {
			@Override
			public String handler(String str) {
				// 处理 {0} 包 {1} 导入 {2} 实体名称
				str = str.replace("${packageName}", packageName);
				str = str.replace("${imports}", imports);
				str = str.replace("${entityName}", entityName);
				str += "\r\n";
				return str;
			}
		});
	}

	/**
	 * 获取xml
	 */
	public static String getXml(final String nameSpace, 
			final String poType, 
			final String columnMap, 
			final String allColumns,
			final String allIfWhere,
			final String tableName, 
			final String allValues,
			final String allValues2,
			final String allIfSet) throws Exception {

		return template("xml", new Rowhandler() {
			@Override
			public String handler(String str) {
				str = str.replace("${nameSpace}", nameSpace);
				str = str.replace("${poType}", poType);
				str = str.replace("${columnMap}", columnMap);
				str = str.replace("${allColumns}", allColumns);
				str = str.replace("${allIfWhere}", allIfWhere);
				str = str.replace("${tableName}", tableName);
				str = str.replace("${allValues}", allValues);
				str = str.replace("${allValues2}", allValues2);
				str = str.replace("${allIfSet}", allIfSet);
				str += "\r\n";
				return str;
			}
		});

	}
	
	/**
	 * 获取PO
	 */
	public static String getPo(final String packageName, 
			final String imports, 
			final String entityName, 
			final String fields,
			final String setter,
			final String getter) throws Exception {
		return template("po", new Rowhandler() {
			@Override
			public String handler(String str) {
				str = str.replace("${packageName}", packageName);
				str = str.replace("${imports}", imports);
				str = str.replace("${entityName}", entityName);
				str = str.replace("${fields}", fields);
				str = str.replace("${setter}", setter);
				str = str.replace("${getter}", getter);
				str += "\r\n";
				return str;
			}
		});
	}
	
	/**
	 * 获取PO
	 */
	public static String getApi(final String packageName, 
			final String imports, 
			final String entityName,
			final String entityNote,
			final String entityCodePrefix) throws Exception {
		return template("api", new Rowhandler() {
			@Override
			public String handler(String str) {
				str = str.replace("${packageName}", packageName);
				str = str.replace("${imports}", imports);
				str = str.replace("${entityName}", entityName);
				str = str.replace("${entityName2}", NameMapper.humpLogo2Path(entityName));
				str = str.replace("${entityNote}", entityNote);
				str = str.replace("${entityCodePrefix}", entityCodePrefix);
				str += "\r\n";
				return str;
			}
		});
	}
	
	/**
	 * 获取PO
	 */
	public static String getApiImpl(final String packageName, 
			final String imports, 
			final String entityName) throws Exception {
		return template("apiImpl", new Rowhandler() {
			@Override
			public String handler(String str) {
				str = str.replace("${packageName}", packageName);
				str = str.replace("${imports}", imports);
				str = str.replace("${entityName}", entityName);
				str = str.replace("${entityName2}", NameMapper.firstLetterLowerCase(entityName));
				str += "\r\n";
				return str;
			}
		});
	}

	public static String template(String t, Rowhandler rowhandler) throws Exception {

		String path = TemplateTool.class.getResource(t).getFile();
		File file = new File(path);
		if (!file.exists()) {
			return null;
		}
		StringBuilder result = new StringBuilder();
		FileReader reader = new FileReader(file);
		BufferedReader br = new BufferedReader(reader);
		String str = null;
		while ((str = br.readLine()) != null) {
			result.append(rowhandler.handler(str));
		}
		br.close();
		reader.close();

		return result.toString();
	}
	
	//sdk
	public static String getJsSDKInfo()  throws Exception{
		return readFile("sdk-info");
	}
	public static String getJsSdk(final String methodDescribe, 
			final String methodName, 
			final String methodNumber)  throws Exception{
		
		return template("sdk-method", new Rowhandler() {
			@Override
			public String handler(String str) {
				str = str.replace("${p1}", methodDescribe);
				str = str.replace("${p2}", methodName);
				str = str.replace("${p3}", methodNumber);
				str += "\r\n";
				return str;
			}
		});
	}
	
	public static String readFile(String fileName) throws Exception {

		String path = TemplateTool.class.getResource(fileName).getFile();
		File file = new File(path);
		if (!file.exists()) {
			return null;
		}
		StringBuilder result = new StringBuilder();
		FileReader reader = new FileReader(file);
		BufferedReader br = new BufferedReader(reader);
		String str = null;
		while ((str = br.readLine()) != null) {
			result.append(str+"\r\n");
		}
		br.close();
		reader.close();

		return result.toString();
	}

	interface Rowhandler {
		public String handler(String row);
	}
}
