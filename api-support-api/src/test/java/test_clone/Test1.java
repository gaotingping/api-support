package test_clone;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.BeanUtils;

import com.alibaba.fastjson.JSON;

import springbustest.vo.TestBeanVO;
import springbustest.vo.TestBeanVO2;
import springbustest.vo.TestBeanVO3;

/**
 * 结论: 1.str<->bean:fastjson 2.bean<->bean:BeanUtils
 */
public class Test1 {
	
	@Test
	public void test212(){
		TestBeanVO t1 = getTestBean();
		TestBeanVO t2 = new TestBeanVO();
		org.springframework.beans.BeanUtils.copyProperties(t1, t2);
		System.out.println(t2);
	}

	/*
	 * vo-po的转换: 1.obj->json->obj 2.beanUtis
	 * 
	 * obj的clone 缓存对象的复制等等
	 */
	@Test
	public void test1() throws Exception {
		// bean utis有优势
		for (int i = 0; i < 20; i++) {
			springUtils();//块一个数量级
			beanUtis();//是json的2培
			jsonUtis();//json
			System.out.println("==========");
		}
	}

	@Test
	public void test22() throws Exception {
		TestBeanVO t1 = getTestBean();
		TestBeanVO t2 = MyBeanUtils.convert(t1, TestBeanVO.class);
		System.out.println(t2);
	}

	public void springUtils() {
		long s = System.currentTimeMillis();
		for (int i = 0; i < 100000; i++) {
			TestBeanVO t1 = getTestBean();
			TestBeanVO t2 = new TestBeanVO();
			org.springframework.beans.BeanUtils.copyProperties(t1, t2);
		}
		System.out.println(System.currentTimeMillis() - s);
	}

	// 对象必须有getter setter方法
	@SuppressWarnings("unchecked")
	public <T> T beanConvert(Object obj, Class<T> c) throws Exception {
		Object t = c.newInstance();
		BeanUtils.copyProperties(t, obj);
		return (T) t;
	}

	public void beanUtis() throws Exception {
		long s = System.currentTimeMillis();
		for (int i = 0; i < 100000; i++) {
			TestBeanVO t1 = getTestBean();
			TestBeanVO t2 = new TestBeanVO();
			BeanUtils.copyProperties(t2, t1);
		}
		System.out.println(System.currentTimeMillis() - s);
	}

	public void jsonUtis() {
		long s = System.currentTimeMillis();
		for (int i = 0; i < 100000; i++) {
			//序列化+反序列化都是beanUtis的一半时间
			TestBeanVO t = getTestBean();
			String json = JSON.toJSONString(t);
			TestBeanVO t2 = JSON.parseObject(json, TestBeanVO.class);
		}
		System.out.println(System.currentTimeMillis() - s);
	}

	TestBeanVO getTestBean() {
		TestBeanVO t1 = new TestBeanVO();
		t1.id = 1;
		t1.name = "t1";
		t1.pid = null;

		TestBeanVO3 t3 = new TestBeanVO3();
		t3.id = 1;
		t3.name = "t3";
		t3.date = new Date();

		TestBeanVO2 t2 = new TestBeanVO2();
		t2.id = 22;
		t2.name = "t2";
		t2.t3 = t3;
		t1.t2 = t2;

		List<TestBeanVO2> list = new ArrayList<>();
		list.add(t2);
		list.add(t2);
		t1.list = list;

		return t1;
	}
}
