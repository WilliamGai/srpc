package org.srpc.http;

import java.util.concurrent.atomic.AtomicInteger;

public class TestProblem {
	public static void main(String args[]) {
		Integer n = Integer.MAX_VALUE;
		AtomicInteger count = new AtomicInteger(Integer.MAX_VALUE);
		System.out.println(n + 1);
		System.out.println(count.incrementAndGet());
		System.out.println(count.incrementAndGet());
		System.out.println(" "+ Integer.toBinaryString(Integer.MAX_VALUE));
		System.out.println(Integer.toBinaryString(Integer.MIN_VALUE));

		System.out.println(Integer.toBinaryString(0-Integer.MAX_VALUE));
		System.out.println(Integer.toBinaryString(Integer.MIN_VALUE));
	}
}
