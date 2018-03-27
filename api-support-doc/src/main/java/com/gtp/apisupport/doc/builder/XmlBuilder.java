package com.gtp.apisupport.doc.builder;

import java.util.List;

import com.gtp.apisupport.doc.model.FieldInfo;
import com.gtp.apisupport.doc.model.TableInfo;
import com.gtp.apisupport.doc.template.TemplateTool;

//负责构建xml
public class XmlBuilder extends BaseBuilder {

	public XmlBuilder(TableInfo tableInfo) {
		this.tableInfo = tableInfo;
	}

	public String getAll() throws Exception {

		String nameSpace = tableInfo.getDaoPackageName() + "." + tableInfo.getDaoFileName();
		String poType = tableInfo.getPoPackageName() + "." + tableInfo.getPoFileName();
		String tableName = tableInfo.getTableName();
		String columnMap = getResultMap();
		String allColumns = getColumns("id");
		String allIfWhere = getIfWhere();
		String allValues = getValues("");
		String allValues2 = getValues("item.");
		String allIfSet = getIfSet();

		return TemplateTool.getXml(nameSpace, poType, columnMap, allColumns, allIfWhere, tableName, allValues,
				allValues2, allIfSet);
	}

	private String getIfSet() {

		StringBuilder result = new StringBuilder();

		List<FieldInfo> fs = tableInfo.getFields();

		for (int i = 0; i < fs.size(); i++) {

			FieldInfo f = fs.get(i);

			if ("id".equals(f.getName())) {
				continue;
			}

			if (i == 0) {
				result.append("<if test=\"" + f.getJavaField() + " !=null\">\r\n");
				result.append("\t\t\t" + f.getName() + " = #{" + f.getJavaField() + "},\r\n");
				result.append("\t\t\t</if>");
			} else if (i == fs.size() - 1) {
				result.append("\t\t<if test=\"" + f.getJavaField() + " !=null\">\r\n");
				result.append("\t\t\t" + f.getName() + " = #{" + f.getJavaField() + "},\r\n");
				result.append("\t\t</if>");
			} else {
				result.append("\t\t<if test=\"" + f.getJavaField() + " !=null\">\r\n");
				result.append("\t\t\t" + f.getName() + " = #{" + f.getJavaField() + "},\r\n");
				result.append("\t\t</if>\r\n");
			}
		}

		return result.toString();
	}

	private String getResultMap() {

		StringBuilder result = new StringBuilder();

		List<FieldInfo> fs = tableInfo.getFields();

		for (int i = 0; i < fs.size(); i++) {

			FieldInfo f = fs.get(i);

			if ("id".equals(f.getName())) {
				continue;
			}

			if (i == fs.size() - 1) {
				result.append("\t\t<result column=\"" + f.getName() + "\" property=\"" + f.getJavaField() + "\"/>");
			} else {
				result.append("\t\t<result column=\"" + f.getName() + "\" property=\"" + f.getJavaField() + "\"/>\r\n");
			}
		}

		return result.toString();
	}

	private String getIfWhere() {

		StringBuilder result = null;

		List<FieldInfo> fs = tableInfo.getFields();

		for (int i = 0; i < fs.size(); i++) {

			FieldInfo f = fs.get(i);

			if ("id".equals(f.getName())) {
				continue;
			}

			if (result == null) {
				result = new StringBuilder();
				result.append("\t<if test=\"" + f.getJavaField() + " !=null\">\r\n");
				result.append("\t\t\tAND " + f.getName() + " =#{" + f.getJavaField() + "}\r\n");
			} else {
				result.append("\t\t<if test=\"" + f.getJavaField() + " !=null\">\r\n");
				result.append("\t\t\tAND " + f.getName() + " =#{" + f.getJavaField() + "}\r\n");
			}

			// 最后一个不换行
			if (i == fs.size() - 1) {
				result.append("\t\t</if>");
			} else {
				result.append("\t\t</if>\r\n");
			}
		}

		return result.toString();
	}

	private String getColumns(String ignore) {
		StringBuilder result = null;
		for (FieldInfo f : tableInfo.getFields()) {
			if (ignore != null && ignore.equals(f.getName())) {
				continue;
			}
			if (result == null) {
				result = new StringBuilder();
				result.append("" + f.getName());
			} else {
				result.append("," + f.getName());
			}
		}

		return result.toString();
	}

	/**
	 * insert value 组装
	 * 
	 * @param prefix
	 *            如批量插入时 #{item.id}
	 * @return
	 */
	private String getValues(String prefix) {
		StringBuilder result = null;

		List<FieldInfo> fs = tableInfo.getFields();

		for (int i = 0; i < fs.size(); i++) {

			FieldInfo f = fs.get(i);

			if ("id".equals(f.getName())) {
				continue;
			}

			if (result == null) {
				result = new StringBuilder();
				result.append("#{" + prefix + f.getJavaField() + "}\r\n");
			} else if (i == fs.size() - 1) {
				result.append("\t\t\t,#{" + prefix + f.getJavaField() + "}");
			} else {
				result.append("\t\t\t,#{" + prefix + f.getJavaField() + "}\r\n");
			}
		}

		return result.toString();
	}
}
