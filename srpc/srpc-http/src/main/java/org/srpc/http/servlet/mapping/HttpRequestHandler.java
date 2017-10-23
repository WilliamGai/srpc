package org.srpc.http.servlet.mapping;

import java.io.IOException;

import org.srpc.http.servlet.HttpServletRequest;
import org.srpc.http.servlet.HttpServletResponse;

@Deprecated
public interface HttpRequestHandler {

	void handleRequest(HttpServletRequest request, HttpServletResponse response)
			throws IOException;

}