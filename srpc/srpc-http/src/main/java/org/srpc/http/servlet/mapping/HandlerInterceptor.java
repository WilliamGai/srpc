package org.srpc.http.servlet.mapping;

import org.srpc.http.servlet.HttpServletRequest;
import org.srpc.http.servlet.HttpServletResponse;

public interface HandlerInterceptor {
	boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception;

	void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, Object modelAndView)
	            throws Exception;// ModelAndView

	void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
	            throws Exception;

}
