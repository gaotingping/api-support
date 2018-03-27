package com.gtp.apisupport.doc.common;

/**
 * 名称映射管理:命名规则
 */
public class NameMapper {
	
	/**
	 * 驼峰标识转java字段:去掉下划线，首字母大写(第一词除外)
	 * @param str
	 * @return
	 */
	public static String getJavaFieldName(String str) {
		String[] ts = str.split("_");
		if (ts.length < 2) {
			return str;
		} else {
			String newName = null;
			for (String t : ts) {
				if (newName == null) {
					newName = t;
				} else {
					newName = newName + firstLetterUpperCase(t);
				}
			}
			return newName;
		}
	}
	
	/**
	 * 驼峰标识转请求路径，以下划线隔开
	 * 例子:
	 * applyAuditStep -> apply_audit_step
     * applyAAuditStep -> apply_aAudit_step
	 * @param p
	 * @return
	 */
	public static String humpLogo2Path(String p){
		p=firstLetterLowerCase(p);
		StringBuilder r=new StringBuilder();
		boolean f=false;//是否找到大写
		for(int i=0;i<p.length();i++){
			char t = p.charAt(i);
			if( t >= 'A' && t <= 'Z'){//大写
				if(!f){
					r.append("_");
				}
				f=true;
			}else{
				f=false;
			}
			r.append(t);
		}
		return r.toString().toLowerCase();
	}
	
	/**
	 * 首字母大写
	 * @param t
	 * @return
	 */
	public static String firstLetterUpperCase(String t){
		return t.substring(0, 1).toUpperCase() + t.substring(1);
	}

	/**
	 * 首字母小写
	 * @param t
	 * @return
	 */
	public static String firstLetterLowerCase(String t){
		return t.substring(0, 1).toLowerCase() + t.substring(1);
	}

	/**
	 * jdbc类型转java类型   @see Types
	 * @param jdbcType
	 * @return
	 */
	public static String getJavaType(String jdbcType) {
		
		//boolean
		if("boolean".equals(jdbcType)){
			return "Boolean";
		}
		
		//整数数值
		if("int".equals(jdbcType)
				|| "tinyint".equals(jdbcType)
				|| "smallint".equals(jdbcType)
				|| "mediumint".equals(jdbcType)){
			return "Integer";
		}
		
		if("bigint".equals(jdbcType)){
			return "Long";
		}
		
		//浮点型
		if("float".equals(jdbcType)
				|| "double".equals(jdbcType)
				|| "decimal".equals(jdbcType)){
			return "Double";
		}
		
		//日期与时间
		if("date".equals(jdbcType)
				|| "time".equals(jdbcType)
				|| "datetime".equals(jdbcType)
				|| "timestamp".equals(jdbcType)){
			return "Date";
		}
		
		//其它都默认是字符串
		return "String";
	}
}
