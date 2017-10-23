package org.srpc.http.servlet.mapping;

import org.srpc.http.servlet.HttpServletRequest;
import org.srpc.http.servlet.HttpServletResponse;
import org.srpc.http.servlet.view.ModelAndView;

public abstract class AbstractHandlerMethodAdapter implements HandlerAdapter{
	@Override
	public final boolean supports(Object handler) {
		return (handler instanceof HandlerMethod && supportsInternal((HandlerMethod) handler));
	}
	protected abstract boolean supportsInternal(HandlerMethod handlerMethod);
	@Override
	public final ModelAndView handle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		return handleInternal(request, response, (HandlerMethod) handler);
	}
	protected abstract ModelAndView handleInternal(HttpServletRequest request,
			HttpServletResponse response, HandlerMethod handlerMethod) throws Exception;

	@Override
	public final long getLastModified(HttpServletRequest request, Object handler) {
		return getLastModifiedInternal(request, (HandlerMethod) handler);
	}

	protected abstract long getLastModifiedInternal(HttpServletRequest request, HandlerMethod handlerMethod);
}
