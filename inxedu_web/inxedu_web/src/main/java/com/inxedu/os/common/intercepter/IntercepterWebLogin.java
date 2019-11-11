package com.inxedu.os.common.intercepter;

import com.inxedu.os.common.util.SingletonLoginUtils;
import com.inxedu.os.edu.entity.user.User;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 前台用户登录拦截器
 * @author www.inxedu.com
 */
public class IntercepterWebLogin extends HandlerInterceptorAdapter{

	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		User user = SingletonLoginUtils.getLoginUser(request);
		if(user==null){
			response.sendRedirect("/?msg=ExternalLogin");//跳转登录页面 弹出登录框
			return false;
		}
		return true;
	}

	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		super.postHandle(request, response, handler, modelAndView);
	}

	
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		super.afterCompletion(request, response, handler, ex);
	}

	
	public void afterConcurrentHandlingStarted(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		super.afterConcurrentHandlingStarted(request, response, handler);
	}

}