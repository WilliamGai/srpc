package org.sonic.rpc.core.utils;

import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


public class LimitThreadPool {
	static String name = "sonic-rpc";
	static int cores = 0;
	static int threads = 200;
	static int queues = 0;

	public static final Executor EXECUTOR = new ThreadPoolExecutor(
			cores,
			threads, 
			Long.MAX_VALUE,
	        TimeUnit.MILLISECONDS,
	        queues == 0 ? new SynchronousQueue<Runnable>()
	                        : (queues < 0 ? new LinkedBlockingQueue<Runnable>()
	                                    : new LinkedBlockingQueue<Runnable>(queues)),
	                        new NamedThreadFactory(name, false));

	public static void execute(Runnable command) {
		EXECUTOR.execute(command);
	}
	
	public static String getInfo(){
		return Util.format("name={}, cores={}, threads={}, queues={}", name, cores, threads, queues);
	}
}
