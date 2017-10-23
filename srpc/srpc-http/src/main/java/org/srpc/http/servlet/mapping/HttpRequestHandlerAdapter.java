package org.srpc.http.servlet.mapping;

import org.srpc.http.servlet.HttpServletRequest;
import org.srpc.http.servlet.HttpServletResponse;
import org.srpc.http.servlet.view.ModelAndView;
@Deprecated
public class HttpRequestHandlerAdapter implements HandlerAdapter {

	@Override
	public boolean supports(Object handler) {
		return (handler instanceof HttpRequestHandler);
	}

	@Override
	public ModelAndView handle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		((HttpRequestHandler) handler).handleRequest(request, response);
		return null;
	}

	@Override
	public long getLastModified(HttpServletRequest request, Object handler) {
		return -1L;
	}

}