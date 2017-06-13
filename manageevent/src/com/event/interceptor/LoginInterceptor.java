package com.event.interceptor;

import com.event.domain.TUser;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

public class LoginInterceptor extends MethodFilterInterceptor{
	
	@Override
	protected String doIntercept(ActionInvocation arg0) throws Exception {
		TUser admin=(TUser) ActionContext.getContext().getSession().get("admin");
		if(admin==null){
			return "login";
		}
		String in=arg0.invoke();
		return in;
	}

}
