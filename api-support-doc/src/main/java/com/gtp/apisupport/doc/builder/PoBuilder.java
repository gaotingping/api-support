package com.gtp.apisupport.doc.builder;

import java.util.List;

import com.gtp.apisupport.doc.common.NameMapper;
import com.gtp.apisupport.doc.model.FieldInfo;
import com.gtp.apisupport.doc.model.TableInfo;
import com.gtp.apisupport.doc.template.TemplateTool;

//负责构建PO
public class PoBuilder extends BaseBuilder {

	public PoBuilder(TableInfo tableInfo) {
		this.tableInfo = tableInfo;
	}

	public String getPoInfo() throws Exception {

		String packageName = tableInfo.getPoPackageName();
		final String imports = "";
		final String entityName = tableInfo.getEntityName();
		final String fields = getFields();
		final String setter = getSetter();
		final String getter = getGetter();

		return TemplateTool.getPo(packageName, imports, entityName, fields, setter, getter);
	}

	private String getGetter() {

		StringBuilder result = new StringBuilder();

		List<FieldInfo> fs = tableInfo.getFields();

		for (int i=0;i<fs.size();i++) {
			
			FieldInfo f = fs.get(i);
			
			if(i == 0){
				result.append("public " + f.getJavaType() + " " + getMethodName("get", f.getJavaField()) + "() {\r\n");
			}else{
				result.append("\tpublic " + f.getJavaType() + " " + getMethodName("get", f.getJavaField()) + "() {\r\n");
			}
			
			result.append("\t\treturn " + f.getJavaField() + ";\r\n");
			result.append("\t}\r\n");
			result.append("\r\n");
		}

		return result.toString();
	}

	private String getSetter() {

		StringBuilder result = new StringBuilder();

		List<FieldInfo> fs = tableInfo.getFields();

		for (int i=0;i<fs.size();i++) {
			
			FieldInfo f = fs.get(i);
			
			if(i == 0){
				result.append("public void " + getMethodName("set", f.getJavaField()) + "(" + f.getJavaType() + " "+ f.getJavaField() + ") {\r\n");
			}else{
				result.append("\tpublic void " + getMethodName("set", f.getJavaField()) + "(" + f.getJavaType() + " "+ f.getJavaField() + ") {\r\n");
			}
			
			result.append("\t\tthis." + f.getJavaField() + "= " + f.getJavaField() + ";\r\n");
			result.append("\t}\r\n");
			result.append("\r\n");
		}

		return result.toString();
	}

	private String getFields() {

		StringBuilder result = new StringBuilder();
		
		List<FieldInfo> fs = tableInfo.getFields();

		for (int i=0;i<fs.size();i++) {
			
			FieldInfo f = fs.get(i);
			if(i == 0){
				result.append("@ApiDescribe(\""+f.getComment()+"\")\r\n");
			}else{
				result.append("\t@ApiDescribe(\""+f.getComment()+"\")\r\n");
			}
			
			result.append("\tprivate " + f.getJavaType() + " " + f.getJavaField() + ";\r\n");
			result.append("\r\n");
		}
		
		return result.toString();
	}

	private String getMethodName(String prefix, String t) {
		return prefix + NameMapper.firstLetterUpperCase(t);
	}
}
