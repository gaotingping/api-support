package interceptor;

import org.springframework.core.annotation.Order;

import com.gtp.apisupport.interceptor.ApiInterceptor;
import com.gtp.apisupport.model.ApiContext;

/**
 * 演示拦截器怎么用，@Order可以调整顺序
 */
@Order(1)
public class MyInterceptor2 implements ApiInterceptor{

	@Override
	public boolean preHandle(ApiContext rc) {
		System.out.println("前置拦截器2");
		return true;
	}

	@Override
	public boolean postHandle(ApiContext rc) {
		System.out.println("后置拦截器2");
		return true;
	}

}
