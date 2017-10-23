package org.srpc.http.servlet.mapping.handlermapping;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationContextException;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.Assert;
import org.springframework.util.PathMatcher;
import org.srpc.http.servlet.HttpServletRequest;
import org.srpc.http.servlet.mapping.HandlerExecutionChain;
import org.srpc.http.servlet.mapping.HandlerInterceptor;
import org.srpc.http.servlet.mapping.MappedInterceptor;

public abstract class AbstractHandlerMapping implements HandlerMapping, ApplicationContextAware{
	private int order = Integer.MAX_VALUE;  // default: same as non-Ordered
	private UrlPathHelper urlPathHelper = new UrlPathHelper();

	private Object defaultHandler;


	private PathMatcher pathMatcher = new AntPathMatcher();

	private final List<Object> interceptors = new ArrayList<Object>();

	private final List<HandlerInterceptor> adaptedInterceptors = new ArrayList<HandlerInterceptor>();

	public void setDefaultHandler(Object defaultHandler) {
		this.defaultHandler = defaultHandler;
	}

	public Object getDefaultHandler() {
		return this.defaultHandler;
	}

	public void setPathMatcher(PathMatcher pathMatcher) {
		Assert.notNull(pathMatcher, "PathMatcher must not be null");
		this.pathMatcher = pathMatcher;
	}
	
	public PathMatcher getPathMatcher() {
		return this.pathMatcher;
	}
	public void setInterceptors(Object... interceptors) {
		this.interceptors.addAll(Arrays.asList(interceptors));
	}
	protected void initApplicationContext() throws BeansException {
		extendInterceptors(this.interceptors);
		detectMappedInterceptors(this.adaptedInterceptors);
		initInterceptors();
	}

	protected void extendInterceptors(List<Object> interceptors) {
	}

	protected void detectMappedInterceptors(List<HandlerInterceptor> mappedInterceptors) {
		mappedInterceptors.addAll(
				BeanFactoryUtils.beansOfTypeIncludingAncestors(
						getApplicationContext(), MappedInterceptor.class, true, false).values());
	}

	protected void initInterceptors() {
		if (!this.interceptors.isEmpty()) {
			for (int i = 0; i < this.interceptors.size(); i++) {
				Object interceptor = this.interceptors.get(i);
				if (interceptor == null) {
					throw new IllegalArgumentException("Entry number " + i + " in interceptors array is null");
				}
				this.adaptedInterceptors.add(adaptInterceptor(interceptor));
			}
		}
	}

	protected HandlerInterceptor adaptInterceptor(Object interceptor) {
		if (interceptor instanceof HandlerInterceptor) {
			return (HandlerInterceptor) interceptor;
		}
//		else if (interceptor instanceof WebRequestInterceptor) {
////			return new WebRequestHandlerInterceptorAdapter((WebRequestInterceptor) interceptor);
//		}
		else {
			throw new IllegalArgumentException("Interceptor type not supported: " + interceptor.getClass().getName());
		}
	}

	protected final HandlerInterceptor[] getAdaptedInterceptors() {
		int count = this.adaptedInterceptors.size();
		return (count > 0 ? this.adaptedInterceptors.toArray(new HandlerInterceptor[count]) : null);
	}

	protected final MappedInterceptor[] getMappedInterceptors() {
		List<MappedInterceptor> mappedInterceptors = new ArrayList<MappedInterceptor>();
		for (HandlerInterceptor interceptor : this.adaptedInterceptors) {
			if (interceptor instanceof MappedInterceptor) {
				mappedInterceptors.add((MappedInterceptor) interceptor);
			}
		}
		int count = mappedInterceptors.size();
		return (count > 0 ? mappedInterceptors.toArray(new MappedInterceptor[count]) : null);
	}


	@Override
	public final HandlerExecutionChain getHandler(HttpServletRequest request) throws Exception {
		Object handler = getHandlerInternal(request);
		if (handler == null) {
			handler = getDefaultHandler();
		}
		if (handler == null) {
			return null;
		}
		// Bean name or resolved handler?
		if (handler instanceof String) {
			String handlerName = (String) handler;
			handler = getApplicationContext().getBean(handlerName);
		}

		HandlerExecutionChain executionChain = getHandlerExecutionChain(handler, request);
		return executionChain;
	}

	protected abstract Object getHandlerInternal(HttpServletRequest request) throws Exception;

	protected HandlerExecutionChain getHandlerExecutionChain(Object handler, HttpServletRequest request) {
		HandlerExecutionChain chain = (handler instanceof HandlerExecutionChain ?
				(HandlerExecutionChain) handler : new HandlerExecutionChain(handler));

		String lookupPath = this.urlPathHelper.getLookupPathForRequest(request);
		for (HandlerInterceptor interceptor : this.adaptedInterceptors) {
			if (interceptor instanceof MappedInterceptor) {
				MappedInterceptor mappedInterceptor = (MappedInterceptor) interceptor;
				if (mappedInterceptor.matches(lookupPath, this.pathMatcher)) {
					chain.addInterceptor(mappedInterceptor.getInterceptor());
				}
			}
			else {
				chain.addInterceptor(interceptor);
			}
		}
		return chain;
	}

	private ApplicationContext applicationContext;
	@Override
	public final void setApplicationContext(ApplicationContext context) throws BeansException {
		if (context == null) {
			// Reset internal context state.
			this.applicationContext = null;
		}
		else if (this.applicationContext == null) {
			this.applicationContext = context;
		}
		else {
			// Ignore reinitialization if same context passed in.
			if (this.applicationContext != context) {
				throw new ApplicationContextException(
						"Cannot reinitialize with different application context: current one is [" +
						this.applicationContext + "], passed-in one is [" + context + "]");
			}
		}
		initApplicationContext();
	}
	
	public final ApplicationContext getApplicationContext() throws IllegalStateException {
		if (this.applicationContext == null) {
			throw new IllegalStateException(
					"ApplicationObjectSupport instance [" + this + "] does not run in an ApplicationContext");
		}
		return this.applicationContext;
	}
}
