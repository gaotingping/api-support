package com.gtp.apisupport.validation.core;

import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.List;

import com.gtp.apisupport.aop.ApiInvocation;
import com.gtp.apisupport.aop.support.ApiMethodInvocationAdapter;
import com.gtp.apisupport.common.ReflectUtil;
import com.gtp.apisupport.validation.annotation.ApiValidation;
import com.gtp.apisupport.validation.enums.ApiVP;

/**
 * 参数验证
 */
public class ApiValidationImpl extends ApiMethodInvocationAdapter {

	@Override
	public Object invoke(ApiInvocation invocation) throws Throwable {
		
		//全局控制开关
		Object t = invocation.getTarget();
		ApiValidation apiVal=t.getClass().getAnnotation(ApiValidation.class);
		if(apiVal !=null && apiVal.value() == false){
			return invocation.proceed();
		}
		
		//参数逐个验证
		Object[] args = invocation.getArgs();
		if (args != null) {
			for (Object obj : args) {
				String error = validation(obj);
				if (error != null) {
					throw new RuntimeException(error);
				}
			}
		}

		return invocation.proceed();
	}

	@SuppressWarnings("unchecked")
	public String validation(Object b) throws Exception {

		Class<? extends Object> c = b.getClass();

		// bug fix
		if (ReflectUtil.isBaseType(c)) {
			return null;
		}

		// 循环递归每个非空字段
		while (c != Object.class) {
			Field[] fs = c.getDeclaredFields();
			for (Field f : fs) {

				ApiValidation apiValidation = f.getAnnotation(ApiValidation.class);
				if (apiValidation == null) {
					continue;
				}

				f.setAccessible(true);

				Object fv = f.get(b);
				if (fv != null) {
					String r = validationField(apiValidation, fv);
					if (r != null) {
						return r;
					}
				}

				// 集合与bean递归
				if (f.getType() == List.class) {
					List<Object> tt = (List<Object>) fv;
					Iterator<Object> tt_it = tt.iterator();
					while (tt_it.hasNext()) {
						validation(tt_it.next());
					}
				} else {
					validation(fv);
				}
			}
			c = c.getSuperclass();
		}

		return null;
	}

	private String validationField(ApiValidation apiValidation, Object fv) {

		ApiVP pattern = apiValidation.pattern();
		if (pattern == ApiVP.NOTNULL) {
			if(fv instanceof List){ //集合
				List<?> t = (List<?>)fv;
				if(t==null || t.isEmpty()){
					return apiValidation.error();
				}
			}else if (fv == null || "".equals(fv)) {//普通字段
				return apiValidation.error();
			}
		} else {
			if (!fv.toString().matches(apiValidation.regex())) {
				return apiValidation.error();
			}
		}
		return null;
	}
}
