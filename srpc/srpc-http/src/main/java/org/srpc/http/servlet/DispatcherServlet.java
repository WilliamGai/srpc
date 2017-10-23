package org.srpc.http.servlet;


import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

import org.sonic.rpc.core.LogCore;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;//org.springframework.web.context.WebApplicationContext;
import org.srpc.http.servlet.mapping.HandlerAdapter;
import org.srpc.http.servlet.mapping.handlermapping.HandlerMapping;

public class DispatcherServlet implements IDispatcher, ApplicationContextAware {
	private ApplicationContext applicationContext;
	private Boolean applicationContextInjected = false;
	public static final String WEB_APPLICATION_CONTEXT_ATTRIBUTE = DispatcherServlet.class.getName() + ".CONTEXT";
	private List<HandlerMapping> handlerMappings;
	private List<HandlerAdapter> handlerAdapters;
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) {
		LogCore.BASE.info("DispatcherServlet setApplicationContext {}", applicationContext);
		if (this.applicationContext == null) {
			this.applicationContext =  applicationContext;
			this.applicationContextInjected = true;
		}
	}
	
	public final ApplicationContext getApplicationContext() {
		return this.applicationContext;
	}
	
	@Override
	public void doService(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		request.setAttribute(WEB_APPLICATION_CONTEXT_ATTRIBUTE, getApplicationContext());
		try {
			doDispatch(request, response);
		}catch (Exception e) {
			throw e;
		}
	}

	private void doDispatch(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	}
	
}
