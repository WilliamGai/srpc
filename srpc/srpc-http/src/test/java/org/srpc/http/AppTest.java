package org.srpc.http;

import org.sonic.rpc.core.LogCore;
import org.sonic.rpc.core.utils.HttpUtil;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest extends TestCase {
	public AppTest(String testName) {
		super(testName);
	}

	public static Test suite() {
		return new TestSuite(AppTest.class);
	}

	public void testApp() {
		String rst = HttpUtil.sendPost("http://localhost:8045", "你好");
		LogCore.BASE.info("test{}", rst);
		assertTrue(true);
	}
}
