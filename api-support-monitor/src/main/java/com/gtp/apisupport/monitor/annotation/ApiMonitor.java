package com.gtp.apisupport.monitor.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.stereotype.Component;

/**
 * 接口监控日志:轻量级的机制记录,以记录什么人什么时间操作了什么
 * 一般建议给修改类方法添加
 * 使用例子
 * <pre>
 *  //编辑用户
 *  @ApiMonitor(playId="${token}",key="editorUser",field1="${id}")
	public ResultBean editorUser(JSONObject args);
 * </pre>
 * 
 * #应该也做成扩展式功能(拦截器)
 * 
 * 需要业务配合的地方，@see MonitorReserved
 * 
 * @author gaotingping@cyberzone.cn
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Component
public @interface ApiMonitor {
	
	/**
	 * 操作人:变量${xxx}解析获得实际值,常量xxx原样保存
	 */
	String  playId() default "";
	
	/**
	 * 自定义“见名知意”的标识，如delUser,addUser,同一服务内尽量保持唯一
	 */
	String  key();
	
	/**
	 * 是否保存参数,在有些情况下参数可能很大，除非很有必要
	 * 否则不建议保存，默认不保存
	 */
	boolean args() default false;
	
	/**
	 * 预留5个扩展串,以便于保存业务信息,如管理员修改密码
	 * 可以附带保存被修改者ID,newPwd等
	 * 允许如下定义:
	 * 	1.空,
	 *  2.xxx 常量
	 *  3.${xxx} 变量,从args参数中提取
	 *  4.${xxx.xxx} 变量多层次获取，注意不支持数组
	 */
	String field1() default "";
	String field2() default "";
	String field3() default "";
	String field4() default "";
	String field5() default "";
}
