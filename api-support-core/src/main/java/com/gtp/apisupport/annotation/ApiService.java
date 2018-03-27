package com.gtp.apisupport.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 服务或接口标识
 * 
 * @author gaotingping@cyberzone.cn
 *
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface ApiService {
	
	/**
	 * 可以给接口一个说明或分组
	 */
	String value();
	
	/**
	 * 是否验证输入参数,默认不验证
	 * 详情 @see ApiValidation
	 */
	boolean validation() default false;
}
