package org.srpc.http.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class HttpServletRequest implements IRequest{
	private final Map<String, Object> attributes = new ConcurrentHashMap<>();
	private final Map<String, Object> params = new LinkedHashMap<>();
	protected static final int CACHED_POST_LEN = 8192;
	protected byte[] postData = null;
	@Override
	public Object getAttribute(String name) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String getCharacterEncoding() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void setCharacterEncoding(String env) throws UnsupportedEncodingException {
		// TODO Auto-generated method stub
		
	}
	@Override
	public int getContentLength() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public long getContentLengthLong() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public String getContentType() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String getParameter(String name) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String[] getParameterValues(String name) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Map<String, String[]> getParameterMap() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String getProtocol() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String getScheme() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String getServerName() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public int getServerPort() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public BufferedReader getReader() throws IOException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String getRemoteAddr() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String getRemoteHost() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void setAttribute(String name, Object o) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void removeAttribute(String name) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public Locale getLocale() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public boolean isSecure() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public int getRemotePort() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public String getLocalName() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String getLocalAddr() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public int getLocalPort() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public boolean isAsyncStarted() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean isAsyncSupported() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public DispatcherType getDispatcherType() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String getPathInfo() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String getRequestURI() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String getContextPath() {
		// TODO Auto-generated method stub
		return null;
	}
}
