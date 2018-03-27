package doc2.test;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.alibaba.fastjson.JSONObject;
import com.gtp.apisupport.annotation.ApiService;

import doc2.code.ClassNoteTool;
import doc2.code.ScanClassUtils;
import doc2.code.ScanFileNote;
import doc2.code.ScanParentClass;
import doc2.common.DocConfig;

public class Test1 {

	@Test
	public void test1() throws Exception{
		Map<String, String> map = ScanParentClass.getClassRely("com.mvwchina.cttms");
		Iterator<String> it = map.keySet().iterator();
		while(it.hasNext()){
			String k = it.next();
			System.out.println(k+"="+map.get(k));
		}
	}
	
	@Test
	public void test2(){
		Map<String, JSONObject> map = ScanFileNote.getAllNote(DocConfig.dto_vo_dir,"VO.java");
		Iterator<String> it = map.keySet().iterator();
		while(it.hasNext()){
			String k = it.next();
			System.out.println(k+"="+map.get(k));
		}
	}
	
	@Test
	public void test3() throws Exception{
		
		List<Class<?>> list = new ArrayList<>();
		List<Class<?>> dto = ScanClassUtils.scaning("com.mvwchina.cttms", null, "/**/dto/*DTO.class");
		List<Class<?>> vo = ScanClassUtils.scaning("com.mvwchina.cttms", null, "/**/*VO.class");
		list.addAll(vo);
		list.addAll(dto);
		
		Map<String, JSONObject> r = ClassNoteTool.getData(list);
		System.out.println(r.get("TeacherGroupDTO"));
		System.out.println(r.get("AccountVO"));
	}
	
	@Test
	public void test4() throws Exception{
		List<Class<?>> list = ScanClassUtils.scaning("springbustest.service", ApiService.class,null);
		for(Class<?> c:list){
			Method[] ms = c.getDeclaredMethods();
			for(Method m:ms){
				Type[] ts = m.getGenericParameterTypes();
				for(Type t:ts){
					if(t instanceof ParameterizedType){//含泛型  但是我只允许有list,并且只允许一层
						ParameterizedType parameterizedType = (ParameterizedType) t;
		                //获取参数的类型  
		                System.out.println(parameterizedType.getRawType());  
		                //获取参数的泛型列表  
		                Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();  
		                for (Type type2 : actualTypeArguments) {  
		                    System.out.println(type2);  
		                }  
					}else{
						System.out.println(t);
					}
				}
			}
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
