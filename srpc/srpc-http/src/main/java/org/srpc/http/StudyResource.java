package org.srpc.http;

import java.util.Set;

import org.sonic.rpc.core.LogCore;
import org.sonic.rpc.core.utils.Util;
import org.srpc.http.servlet.DispatcherServlet;
import org.srpc.http.servlet.mapping.handlermapping.HandlerMapping;

import io.netty.handler.codec.http.HttpRequest;

public class StudyResource {

	public static void main(String[] args) {
		DispatcherServlet d;
		
		HttpRequest c;
		Set<Class<?>> set = PackageClass.find();
		LogCore.BASE.info("{}", Util.prettyJsonStr(set));

	}

}
