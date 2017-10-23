package org.srpc.http.servlet.mapping;

import org.srpc.http.servlet.HttpServletRequest;
import org.srpc.http.servlet.HttpServletResponse;

public interface AsyncHandlerInterceptor extends HandlerInterceptor {
	void afterConcurrentHandlingStarted(HttpServletRequest request, HttpServletResponse response, Object handler)
	            throws Exception;

}
