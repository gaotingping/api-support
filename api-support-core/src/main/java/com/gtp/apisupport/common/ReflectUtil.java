package com.gtp.apisupport.common;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.gtp.apisupport.annotation.ApiParam;
import com.gtp.apisupport.model.ApiParamInfo;

/**
 * 反射工具类
 */
public class ReflectUtil {

	public static List<ApiParamInfo> getParameterInfo(Method m) {

		Class<?>[] p1 = m.getParameterTypes();
		if (p1 == null || p1.length < 1) {
			return null;
		}

		Type[] p11 = m.getGenericParameterTypes();

		Annotation[][] p2 = m.getParameterAnnotations();

		List<ApiParamInfo> list = new ArrayList<>();

		for (int i = 0; i < p1.length; i++) {

			ApiParamInfo tmp = new ApiParamInfo();

			Class<?> c = p1[i];
			if (p1[i] == List.class) {
				c = getClassByType(p11[i]);
				tmp.setIsList(true);
			}

			ApiParam apiParam = null;
			for (Annotation a : p2[i]) {
				if (a instanceof ApiParam) {
					apiParam = (ApiParam) a;
					break;
				}
			}
			tmp.setApiParam(apiParam);
			
			tmp.setType(c);
			list.add(tmp);
		}

		return list;
	}

	private static Class<?> getClassByType(Type t) {

		if (t instanceof ParameterizedType) {
			Type[] actualTypes = ((ParameterizedType) t).getActualTypeArguments();
			Class<?> tmpC = (Class<?>) actualTypes[0];
			return tmpC;
		}

		return null;
	}
	
	public static Object getFieldValue(Object obj, String fieldName) 
			throws IllegalArgumentException, IllegalAccessException {
		Object result = null;
		final Field field = getField(obj, fieldName);
		if (field != null) {
			field.setAccessible(true);
			result = field.get(obj);
		}
		return result;
	}

	private static Field getField(Object obj, String fieldName) {
		Field field = null;
		for (Class<?> clazz = obj.getClass(); clazz != Object.class; clazz = clazz.getSuperclass()) {
			try {
				field = clazz.getDeclaredField(fieldName);
				break;
			} catch (NoSuchFieldException e) {
				//e.printStackTrace();
			}
		}
		return field;
	}

	public static void setFieldValue(Object obj, String fieldName, String fieldValue) 
			throws IllegalArgumentException, IllegalAccessException{
		final Field field = getField(obj, fieldName);
		if (field != null) {
				field.setAccessible(true);
				field.set(obj, fieldValue);
		}
	}
	
	public static boolean isBaseType(Class<?> c) {

		if (c.isPrimitive()) {
			return true;
		} else if (c == String.class) {
			return true;
		} else if (c == Integer.class) {
			return true;
		} else if (c == Long.class) {
			return true;
		} else if (c == Double.class) {
			return true;
		} else if (c == Float.class) {
			return true;
		} else if (c == Boolean.class) {
			return true;
		} else if (c == Short.class) {
			return true;
		} else if (c == Byte.class) {
			return true;
		} else if (c == Character.class) {
			return true;
		} else if (c == Date.class) {
			return true;
		} else if (c == Object.class) {
			return true;
		}

		return false;
	}
}
