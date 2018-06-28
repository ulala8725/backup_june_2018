package myseasar2.interceptor;

import javax.annotation.Resource;

import org.aopalliance.intercept.MethodInvocation;
import org.seasar.framework.aop.interceptors.AbstractInterceptor;
import org.seasar.struts.annotation.Execute;

import myseasar2.service.LoginService;

public class LoginInterceptor extends AbstractInterceptor {
	@Resource
	protected LoginService loginService;
	
	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		if (invocation.getMethod().isAnnotationPresent(Execute.class)){
			if(!loginService.isLoginOK()){
				return "index/index";
			}
		}
		return invocation.proceed();
	}

}
