package springbustest.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;

import springbustest.dto.TestBeanDTO;
import springbustest.service.TestApi;
import springbustest.vo.TestBeanVO;
import springbustest.vo.TestBeanVO2;
import springbustest.vo.TestBeanVO3;

/**
 * 服务接口测试
 */
@Component
public class TestApiImpl implements TestApi{

	@Override
	public TestBeanVO test1() {
		return getTestData(1);
	}
	
	@Override
	public TestBeanVO test2(JSONObject args) {
		System.out.println("args:"+args);
		return getTestData(2);
	}

	@Override
	public TestBeanVO test3(TestBeanDTO b) {
		System.out.println("b:"+b);
		return getTestData(2);
	}

	@Override
	public List<TestBeanVO> test4(TestBeanDTO b) {
		List<TestBeanVO> result=new ArrayList<>();
		result.add(getTestData(4));
		return result;
	}
	
	@Override
	public List<TestBeanVO> test5(List<TestBeanDTO> list) {
		List<TestBeanVO> result=new ArrayList<>();
		result.add(getTestData(5));
		return result;
	}
	
	private TestBeanVO getTestData(int i) {
		
		TestBeanVO t1 = new TestBeanVO();
		t1.id = i;
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
