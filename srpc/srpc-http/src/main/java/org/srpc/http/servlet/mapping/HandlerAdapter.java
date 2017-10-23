package org.srpc.http.servlet.mapping;

import org.srpc.http.servlet.HttpServletRequest;
import org.srpc.http.servlet.HttpServletResponse;
import org.srpc.http.servlet.view.ModelAndView;

public interface HandlerAdapter {
	boolean supports(Object handler);

	ModelAndView handle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception;

	long getLastModified(HttpServletRequest request, Object handler);
}
