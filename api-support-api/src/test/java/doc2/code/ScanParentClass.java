package doc2.code;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 扫描类的父类
 */
public class ScanParentClass {

	//放这里只是为看方便
	private static Map<String, String> father = new HashMap<>();

	public static Map<String, String> getClassRely(String packageName) throws Exception{
		
		List<Class<?>> list =ScanClassUtils.scaning(packageName, null, null);
		
		if(list==null || list.isEmpty()){
			return null;
		}
		
		for(Class<?> c:list){
			handlerClass(c);
		}
		
		return father;
	}

	private static void handlerClass(Class<?> c) {
		Class<?> c2 = c.getSuperclass();
		if(c2 == null || c2 == Object.class){
			//father.put(c.getSimpleName(), null);
		}else{
			father.put(c.getSimpleName(), c2.getSimpleName());
			handlerClass(c2);
		}
	}
}
