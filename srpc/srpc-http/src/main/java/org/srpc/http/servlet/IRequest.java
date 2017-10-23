package org.srpc.http.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Locale;
import java.util.Map;

public interface IRequest {

	public Object getAttribute(String name);

	public String getCharacterEncoding();

	public void setCharacterEncoding(String env) throws java.io.UnsupportedEncodingException;

	public int getContentLength();

	public long getContentLengthLong();

	public String getContentType();

	public String getParameter(String name);

	// public Enumeration<String> getParameterNames();

	public String[] getParameterValues(String name);

	public Map<String, String[]> getParameterMap();

	public String getProtocol();

	public String getScheme();

	public String getServerName();

	public int getServerPort();

	public BufferedReader getReader() throws IOException;

	public String getRemoteAddr();

	public String getRemoteHost();

	public void setAttribute(String name, Object o);

	public void removeAttribute(String name);

	public Locale getLocale();

	// public Enumeration<Locale> getLocales();

	public boolean isSecure();

	public int getRemotePort();

	public String getLocalName();

	public String getLocalAddr();

	public int getLocalPort();

	public boolean isAsyncStarted();

	public boolean isAsyncSupported();
	// public AsyncContext getAsyncContext();

	public DispatcherType getDispatcherType();

	public String getPathInfo();

	public String getRequestURI();

	public String getContextPath();
	
	public String getServletPath();

}
