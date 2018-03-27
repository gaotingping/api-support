package org.mybatis.generator.internal;


import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.Field;

/**
 * 重写mybatis generator将表的注释以注解的方式带到PO
 * 
 * @author gaotingping@cyberzone.cn
 */
public class CustomeCommentGenerator extends DefaultCommentGenerator {

	@Override //字段注释
	public void addFieldComment(Field field, IntrospectedTable introspectedTable,
			IntrospectedColumn introspectedColumn) {
		String remark = introspectedColumn.getRemarks();
		field.addJavaDocLine("@ApiDescribe(\""+remark+"\")");
	}
}
