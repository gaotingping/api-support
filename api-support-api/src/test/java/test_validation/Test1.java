package test_validation;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

import com.gtp.apisupport.validation.annotation.ApiValidation;

import springbustest.vo.TestBeanVO;
import springbustest.vo.TestBeanVO2;

/**
 * 想法:将参数的验证，也统一提取出来
 * 一般支持正则就够了，但是错误一般提示编码(code-mes)
 * 方便使用者自定义提示
 * 
 * 1.非空
 * 2.min-max
 * 3.[特定字符]
 * 
 * @author gaotingping@cyberzone.cn
 *
 */
public class Test1 {

	@Test
	public void test1() throws Exception{
		
		TestBeanVO t1=new TestBeanVO();
		t1.id=1;
		t1.name="t1";
		t1.pid=2;
		
		TestBeanVO2 t2=new TestBeanVO2();
		t2.id=22;
		t2.name="t2";
		//t2.t=null; //设置为null,不够成循环引用
		t1.t2=t2;
		
		List<TestBeanVO2> list=new ArrayList<>();
		list.add(t2);
		list.add(t2); //t1->list->t2->t1 这就是一个环
		t1.list=list;
		
		//验证是否有循环依赖
		//isRing(t1,"t1");
		//validation(t1);
		//validationV3(t1);
		//System.out.println(JSON.toJSONString(t1,SerializerFeature.DisableCircularReferenceDetect));
		//System.out.println(verifyRef(t1.getClass()));
		showTree(t1.getClass(),new LinkedList<String>());
	}
	
	private static void showTree(Class<? extends Object> c,LinkedList<String> queue) {
		do {
			Field[] fs = c.getDeclaredFields();
			for (Field f : fs) {
				queue.addLast(f.getName());
				if (isBaseType(f.getType())) {
					showList(queue);
				} else if (f.getType() == List.class) {
					/*list取第一个,是循环返回，否则递归*/
					Type type = f.getGenericType();
					if (type instanceof ParameterizedType) {
						Type[] actualTypes = ((ParameterizedType) type).getActualTypeArguments();
						Class<?> tmpC = (Class<?>) actualTypes[0];
						showTree(tmpC, queue);
						queue.removeLast();
					}
				} else {
					showTree(f.getType(), queue);
					queue.removeLast();
				}
			}
			c = c.getSuperclass();
		} while (c != null && c != Object.class);
	}

	private static void showList(LinkedList<String> queue) {
		for(String s:queue){
			System.out.print(s+"->");
		}
		System.out.println();
		queue.removeLast();
	}

	/**
	 * 循环引用检测
	 * 
	 * @param b
	 * @throws Exception
	 */
	public boolean verifyRef(Class<?> c) throws Exception {
		if (isBaseType(c)) {
			return false;
		} else {
			return innerVerifyRef(c, new HashMap<String, String>());
		}
	}

	private static boolean innerVerifyRef(Class<?> c, HashMap<String, String> circuleRef) {
		circuleRef.put(c.getName(), null);
		do {
			Field[] fs = c.getDeclaredFields();
			for (Field f : fs) {
				if (isBaseType(f.getType())) {
					/*基本类型继续*/
					continue;
				} else if (f.getType() == List.class) {
					/*list取第一个,是循环返回，否则递归*/
					Type type = f.getGenericType();
					if (type instanceof ParameterizedType) {
						Type[] actualTypes = ((ParameterizedType) type).getActualTypeArguments();
						Class<?> tmpC = (Class<?>) actualTypes[0];
						boolean tf=innerVerifyRef(tmpC, circuleRef);
						if(tf){
							return true;
						}
						circuleRef.remove(tmpC.getName());
					}
				} else {
					/*pojo是循环返回，否则递归*/
					if (circuleRef.containsKey(f.getType().getName())) {
						return true;
					} else {
						boolean tf=innerVerifyRef(f.getType(), circuleRef);
						if(tf){
							return true;
						}
					}
					circuleRef.remove(f.getType().getName());
				}
			}
			c = c.getSuperclass();
		} while (c != null && c != Object.class);

		//最后继续
		return false;
	}

	/**
	 * 2个主要问题：
	 * 1.字段具有嵌套性
	 * 2.bean有相互引用(循环嵌套问题:怎样判断依赖关系中存在环:递归)
	 * 
	 * @param b
	 * @throws Exception
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void validation(Object b) throws Exception {
		
		//环:
		//这得是一个队列:以队列代替递归
		LinkedList<Object> queue=new LinkedList<>();
		queue.add(b);
		
		Object bb=queue.poll();
		while(bb!=null){ //迭代队列
			
			Class<? extends Object> c = bb.getClass();
			
			while( c != Object.class){ //处理一个bean
				Field[] fs = c.getDeclaredFields();
				for(Field f:fs){
					f.setAccessible(true);
					if (isBaseType(f.getType())) {
						
						//基本类型:验证
						ApiValidation v = f.getAnnotation(ApiValidation.class);
						if(v!=null){
							Object fv = f.get(bb);
							if(fv!=null){
								if(!fv.toString().matches(v.regex())){
									System.out.println(v.error());
								}
							}
						}
						
					} else if (f.getType() == List.class) {
						
						//list集合
						//Type type = f.getGenericType();
						//System.out.println("list type:"+type);
						Object fv = f.get(bb);
						if(fv!=null){
							//转成list,每个元素入队列
							List<Object> tt = (List)fv;
							Iterator<Object> tt_it = tt.iterator();
							while(tt_it.hasNext()){
								queue.addLast(tt_it.next());
							}
						}
						
					}else{
						
						//自定义pojo:解决方案是加入到集合
						//当前元素入队列
						Object fv = f.get(bb);
						if(fv!=null){
							queue.addLast(f.get(bb)); 
						}
					}
				}
				//父类
				c=c.getSuperclass();
			}
			
			bb=queue.poll();
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
		}else if(c == Date.class){
			return true;
		}else if(c == Object.class){
			return true;
		}
		
		return false;
	}
}
