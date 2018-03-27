package aop;

import org.springframework.core.annotation.Order;

import com.gtp.apisupport.aop.ApiInvocation;
import com.gtp.apisupport.aop.support.ApiMethodInvocationAdapter;

/**
 * 演示AOP怎么用，@Order可以调整顺序
 */
@Order(1)
public class ApiMethodInvocationMockImpl2 extends ApiMethodInvocationAdapter{
	
	@Override
	public Object invoke(ApiInvocation invocation) throws Throwable {
		System.out.println("我是过滤器,哈哈哈!好玩吧！order 1");
		return invocation.proceed();
	}
}
