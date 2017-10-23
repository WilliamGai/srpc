package org.srpc.http.servlet.view;

import java.util.Map;

import org.srpc.http.servlet.HttpServletRequest;
import org.srpc.http.servlet.HttpServletResponse;



public interface View {
	String RESPONSE_STATUS_ATTRIBUTE = View.class.getName() + ".responseStatus";

	String PATH_VARIABLES = View.class.getName() + ".pathVariables";

	String SELECTED_CONTENT_TYPE = View.class.getName() + ".selectedContentType";

	String getContentType();

	void render(Map<String, ?> model, HttpServletRequest request, HttpServletResponse response) throws Exception;

}
