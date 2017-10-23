package org.srpc.http;

import org.sonic.rpc.core.LogCore;
import org.sonic.rpc.core.utils.ThreadUtil;
import org.sonic.rpc.core.utils.Util;

/**
 *  后台线程是否会撤销？
 */
public class ThreadDeamoTest {
	public static void main(String args[]) {

		Thread t = new DeamoThread();
		t.setDaemon(true);
		t.start();
		LogCore.BASE.info("count={}, all={}", Thread.activeCount(),
		            Util.prettyJsonStr(Thread.getAllStackTraces().keySet()));
	}

	private static class DeamoThread extends Thread {
		public DeamoThread() {
			this.setName("test deamo");
		}

		@Override
		public void run() {
			System.out.println("sttart");
			new NotDeamoThread().start();
			while (true) {
				ThreadUtil.sleep(10000);
				System.out.println("hei"+this.isDaemon());
			}
		}
	}

	private static class NotDeamoThread extends Thread {
		public NotDeamoThread() {
			this.setName("test not deamo");
			this.setDaemon(false);
		}

		@Override
		public void run() {
			System.out.println("sttart");
			while (true) {
				ThreadUtil.sleep(10000);
				System.out.println("no demo hei"+this.isDaemon());
			}
		}
	}
}
