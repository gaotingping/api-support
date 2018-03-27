package com.gtp.apisupport.doc.common;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.gtp.apisupport.annotation.ApiDescribe;

/**
 * 文档反射工具(精简)
 * 
 * @author gaotingping@cyberzone.cn
 */
public class DocReflectUtil {

	public static JSONObject allFields(Class<?> c) {
		JSONObject result = new JSONObject();
		if (isBaseType(c)) {
			ApiDescribe desc = c.getAnnotation(ApiDescribe.class);
			result.put(c.getSimpleName(), desc == null ? "" : desc.value());
		} else {
			do {
				Field[] fs = c.getDeclaredFields();
				for (Field f : fs) {
					HashMap<String, String> circuleRef = new HashMap<String, String>();
					circuleRef.put(c.getName(), null);
					ApiDescribe desc = f.getAnnotation(ApiDescribe.class);
					if (desc == null) {
						continue;
					}
					if (isBaseType(f.getType())) {
						result.put(f.getName(), desc.value());
					} else if (f.getType() == List.class) {
						Type type = f.getGenericType();
						if (type instanceof ParameterizedType) {
							Type[] actualTypes = ((ParameterizedType) type).getActualTypeArguments();
							Class<?> tmpC = (Class<?>) actualTypes[0];
							if (circuleRef.containsKey(tmpC.getName())) {
								result.put(f.getName(), desc.value() + "($ref_" + tmpC.getSimpleName() + ")");
							} else {
								JSONArray data = new JSONArray();
								data.add(innerAllFields(tmpC, circuleRef));
								result.put(f.getName(), data);
								circuleRef.remove(tmpC.getName());
							}
						}
					} else {
						if (circuleRef.containsKey(f.getType().getName())) {
							result.put(f.getName(), desc.value() + "($ref_" + f.getType().getSimpleName() + ")");
						} else {
							result.put(f.getName(), innerAllFields(f.getType(), circuleRef));
							circuleRef.remove(f.getType().getName());
						}
					}
				}
				c = c.getSuperclass();
			} while (c != null && c != Object.class);
		}

		return result;
	}

	private static JSONObject innerAllFields(Class<?> c, HashMap<String, String> circuleRef) {
		JSONObject result = new JSONObject();
		circuleRef.put(c.getName(), null);
		do {
			Field[] fs = c.getDeclaredFields();
			for (Field f : fs) {
				ApiDescribe desc = f.getAnnotation(ApiDescribe.class);
				if (desc == null) {
					continue;
				}
				if (isBaseType(f.getType())) {
					result.put(f.getName(), desc.value());
				} else if (f.getType() == List.class) {
					Type type = f.getGenericType();
					if (type instanceof ParameterizedType) {
						Type[] actualTypes = ((ParameterizedType) type).getActualTypeArguments();
						Class<?> tmpC = (Class<?>) actualTypes[0];
						JSONArray data = new JSONArray();
						data.add(innerAllFields(tmpC, circuleRef));
						result.put(f.getName(), data);
						circuleRef.remove(tmpC.getName());
					}
				} else {
					if (circuleRef.containsKey(f.getType().getName())) {
						result.put(f.getName(), desc.value() + "($ref_" + f.getType().getSimpleName() + ")");
					} else {
						result.put(f.getName(), innerAllFields(f.getType(), circuleRef));
					}
					circuleRef.remove(f.getType().getName());
				}
			}
			c = c.getSuperclass();
		} while (c != null && c != Object.class);

		return result;
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
		}else if(c == Date.class){
			return true;
		}else if(c == Object.class){
			return true;
		}
		return false;
	}
}