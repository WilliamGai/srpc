package org.srpc.http.netty;

public abstract class Container implements Lifecycle{
	public static volatile boolean isStart = false;
	public static volatile Container container = null;
	
	public abstract String toString();
}
