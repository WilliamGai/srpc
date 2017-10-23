package org.srpc.http.servlet.mapping.handlermapping;


import org.srpc.http.servlet.HttpServletRequest;

public interface MatchableHandlerMapping extends HandlerMapping {
	RequestMatchResult match(HttpServletRequest request, String pattern);

}