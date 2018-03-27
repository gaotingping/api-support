package doc2.code;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import doc2.common.DocConfig;

/**
 * 类注释工具(获取一个类的所有字段注释，包括依赖类等)
 */
public class ClassNoteTool {

	//历史记录
	private static Map<String, String> hs = new HashMap<>();

	private static Map<String, JSONObject> allNote = new HashMap<>();
	
	private static Map<String, JSONObject> allData = new HashMap<>();

	public static Map<String, JSONObject> getData(List<Class<?>> list) throws Exception{
		
		//初始化
		toInit();
		
		for(Class<?> c:list){
			allData.put(c.getSimpleName(), showAllFileds(c));
		}
		
		return allData;
	}

	private static void toInit() throws Exception {
		
		//先扫描每个所有类  ScanFileNote
		Map<String, JSONObject> map1 = ScanFileNote.getAllNote(DocConfig.dto_vo_dir, "VO.java");
		Map<String, JSONObject> map2 = ScanFileNote.getAllNote(DocConfig.dto_vo_dir, "DTO.java");
		allNote.putAll(map1);
		allNote.putAll(map2);
		
		//再扫描父类   ScanParentClass
		Map<String, String> father = ScanParentClass.getClassRely(DocConfig.basePackage);
		
		//再合并父类  MergeFieldNote
		MergeFieldNote.merge(allNote, father);
	}

	private static JSONObject showAllFileds(Class<?> c) throws Exception {

		JSONObject result = allNote.get(c.getSimpleName());

		if(result==null){
			result=new JSONObject();
		}
		
		// 判断循环引用
		if (hs.containsKey(c.getSimpleName())) {
			result.put(c.getSimpleName(), "$");
			return result;
		}
		hs.put(c.getSimpleName(), null);

		//这里不需要处理父类，只需要当前类，然后再合并类的依赖关系即可
		do {
			Field[] fs = c.getDeclaredFields();
			for (Field f : fs) {

				if (isBaseType(f.getType())) {
					// 忽略
				} else if (f.getType() == List.class) {
					Type type = f.getGenericType();
					if (type instanceof ParameterizedType) {
						Type[] actualTypes = ((ParameterizedType) type).getActualTypeArguments();
						Class<?> tmpC = (Class<?>) actualTypes[0];
						
						if(isBaseType(tmpC)){
							JSONArray tmpData=new JSONArray();
							tmpData.add(tmpC.getSimpleName());
							result.put(f.getName(),tmpData);
						}else{
							JSONArray tmpData=new JSONArray();
							tmpData.add(showAllFileds(tmpC));
							result.put(f.getName(),tmpData);
						}
					}
				} else {
					result.put(f.getName(), showAllFileds(f.getType()));
				}
			}
			c = c.getSuperclass();
		} while (c != null && c != Object.class);

		return result;
	}

	private static boolean isBaseType(Class<?> c) {
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
