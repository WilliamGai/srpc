package org.srpc.http.servlet;

public interface IDispatcher {
	void doService(HttpServletRequest request, HttpServletResponse response) throws Exception;
	
}
