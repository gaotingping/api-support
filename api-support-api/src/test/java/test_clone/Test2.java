package test_clone;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.junit.Test;

import springbustest.vo.TestBeanVO;
import springbustest.vo.TestBeanVO2;
import springbustest.vo.TestBeanVO3;

/**
 * 结论: 1.str<->bean:fastjson 2.bean<->bean:BeanUtils
 */
public class Test2 {

	@Test
	public void test212() throws Exception{
		TestBeanVO t1 = getTestBean();
		emptyToNull(t1);
		System.out.println(t1);
	}

	@SuppressWarnings("unchecked")
	private void emptyToNull(Object b) throws Exception {
		Class<? extends Object> c = b.getClass();
		//bug fix:List<baseType> 栈溢出
		if(isBaseType(c)){
			if("".equals(b)){
				b=null;
			}
			return;
		}
		while (c != Object.class) {
			Field[] fs = c.getDeclaredFields();
			for (Field f : fs) {
				f.setAccessible(true);
				if (isBaseType(f.getType())) {
					Object fv = f.get(b);
					if("".equals(fv)){
						f.set(b, null);
					}
				} else if (f.getType() == List.class) {
					Object fv = f.get(b);
					if (fv != null) {
						List<Object> tt = (List<Object>) fv;
						Iterator<Object> tt_it = tt.iterator();
						while (tt_it.hasNext()) {
							emptyToNull(tt_it.next());
						}
					}
				} else {
					Object fv = f.get(b);
					if (fv != null) {
						emptyToNull(fv);
					}
				}
			}
			c = c.getSuperclass();
		}
	}
	
	TestBeanVO getTestBean() {
		TestBeanVO t1 = new TestBeanVO();
		t1.id = 1;
		t1.name = "";
		t1.pid = null;

		TestBeanVO3 t3 = new TestBeanVO3();
		t3.id = 1;
		t3.name = "";
		t3.date = new Date();

		TestBeanVO2 t2 = new TestBeanVO2();
		t2.id = 22;
		t2.name = "";
		t2.t3 = t3;
		t1.t2 = t2;

		List<TestBeanVO2> list = new ArrayList<>();
		list.add(t2);
		list.add(t2);
		t1.list = list;

		return t1;
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
