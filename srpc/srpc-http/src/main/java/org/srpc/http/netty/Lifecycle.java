package org.srpc.http.netty;

public interface Lifecycle {
	void start();
	void stop();
	boolean isRunning();
	boolean isAutoStartup();
	void stop(Runnable callback);

}
