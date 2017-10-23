package org.srpc.http.utils;

import java.security.SecureRandom;

/***
 * gbn
 */
public class PassCodeGenerator {
	private PassCodeGenerator() {
	};

	public static final PassCodeGenerator instance = new PassCodeGenerator();

	private SecureRandom RAND = new SecureRandom();

	private static final Integer[] DIGITS = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };

	public String geneateString(Integer num) {
		StringBuilder sb = new StringBuilder();
		while (num-- > 0) {
			sb.append(any(DIGITS));
		}
		return sb.toString();
	}

	private <T> T any(T[] arr) {
		if (null == arr || arr.length == 0) {
			return null;
		}
		return arr[RAND.nextInt(arr.length)];
	}

}
