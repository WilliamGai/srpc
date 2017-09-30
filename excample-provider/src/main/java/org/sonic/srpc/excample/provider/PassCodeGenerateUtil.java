package org.sonic.srpc.excample.provider;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.sonic.rpc.core.utils.Util;
public class PassCodeGenerateUtil {
	public static final SecureRandom RAND = new SecureRandom();

	public static final Integer[] DIGITS = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };

	public static String geneateOne(Integer num) {
		return any(DIGITS, num).stream().map(Object::toString).collect(Collectors.joining());
	}
	//JSE7
	public static String geneateString(Integer num) {
		StringBuilder sb = new StringBuilder();
		while (num-- > 0) {
			sb.append(any(DIGITS));
		}
		return sb.toString();
	}


	public static <T> T any(T[] arr) {
		if (Util.isEmpty(arr)) {
			return null;
		}
		return arr[RAND.nextInt(arr.length)];
	}

	public static <T> List<T> any(T[] arr, int num) {
		List<T> rst = new ArrayList<>(num);
		while (num-- > 0) {
			rst.add(any(arr));
		}
		return rst;
	}
	public static void main(String args[]){
		System.out.println(geneateString(4));
	}

}
