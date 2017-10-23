package org.srpc.http.servlet.mapping;


import org.springframework.util.ObjectUtils;
import org.springframework.util.PathMatcher;
import org.srpc.http.servlet.HttpServletRequest;
import org.srpc.http.servlet.HttpServletResponse;

public final class MappedInterceptor implements HandlerInterceptor {

	private final String[] includePatterns;

	private final String[] excludePatterns;

	private final HandlerInterceptor interceptor;

	private PathMatcher pathMatcher;

	public MappedInterceptor(String[] includePatterns, HandlerInterceptor interceptor) {
		this(includePatterns, null, interceptor);
	}

	public MappedInterceptor(String[] includePatterns, String[] excludePatterns, HandlerInterceptor interceptor) {
		this.includePatterns = includePatterns;
		this.excludePatterns = excludePatterns;
		this.interceptor = interceptor;
	}

	public void setPathMatcher(PathMatcher pathMatcher) {
		this.pathMatcher = pathMatcher;
	}
	public PathMatcher getPathMatcher() {
		return this.pathMatcher;
	}

	public String[] getPathPatterns() {
		return this.includePatterns;
	}

	public HandlerInterceptor getInterceptor() {
		return this.interceptor;
	}
	public boolean matches(String lookupPath, PathMatcher pathMatcher) {
		PathMatcher pathMatcherToUse = (this.pathMatcher != null) ? this.pathMatcher : pathMatcher;
		if (this.excludePatterns != null) {
			for (String pattern : this.excludePatterns) {
				if (pathMatcherToUse.match(pattern, lookupPath)) {
					return false;
				}
			}
		}
		if (ObjectUtils.isEmpty(this.includePatterns)) {
			return true;
		}
		else {
			for (String pattern : this.includePatterns) {
				if (pathMatcherToUse.match(pattern, lookupPath)) {
					return true;
				}
			}
			return false;
		}
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		return this.interceptor.preHandle(request, response, handler);
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			Object modelAndView) throws Exception {

		this.interceptor.postHandle(request, response, handler, modelAndView);
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) throws Exception {

		this.interceptor.afterCompletion(request, response, handler, ex);
	}
}
