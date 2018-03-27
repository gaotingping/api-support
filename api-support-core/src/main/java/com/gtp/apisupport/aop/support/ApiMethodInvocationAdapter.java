package com.gtp.apisupport.aop.support;

import com.gtp.apisupport.aop.ApiMethodInvocation;
import com.gtp.apisupport.aop.ApiTargetProxy;

/**
 * 建议业务继承这个类型，只关注自己的业务逻辑
 * 绑定过程可以让父类处理
 */
public abstract class  ApiMethodInvocationAdapter implements ApiMethodInvocation{
	
	@Override
	public Object plugin(Object target) {
		return ApiTargetProxy.bind(target, this);
	}
}
