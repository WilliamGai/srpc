package org.srpc.http.servlet.mapping;

import org.springframework.ui.ModelMap;
import org.srpc.http.servlet.IRequest;

public interface WebRequestInterceptor {
	void preHandle(IRequest request) throws Exception;
	void postHandle(IRequest request, ModelMap model) throws Exception;

	void afterCompletion(IRequest request, Exception ex) throws Exception;

}
