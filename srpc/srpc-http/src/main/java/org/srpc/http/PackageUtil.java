package org.srpc.http;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.sonic.rpc.core.LogCore;
import org.sonic.rpc.core.utils.Util;

public class PackageUtil {
	private static List<String> packages = new ArrayList<>();
	private static Set<String> packagesSet = new HashSet<>();
	private static void scanPackage(String packageName) {
		String scanPath = packageToPath(packageName);
		URL url = PackageUtil.class.getClassLoader().getResource(scanPath);
		LogCore.BASE.info("scanPath={},URL={}", scanPath, url);
		String pathFile = url.getFile();
		File file = new File(pathFile);
		String[] files = file.list();
		for (String path : files) {
			File f = new File(pathFile +"/"+ path);
			if (f.isDirectory()) {
				scanPackage(packageName + "." + path);
				continue;
			}
			String className = packageName + "_" + f.getName();
			packages.add(className);
			packagesSet.add(className);
			
		}
	}

	public static String packageToPath(String packageName) {
		return packageName.replaceAll("\\.", "/");
	}

	public static void main(String[] args) {
		scanPackage("org");
		LogCore.BASE.info("list={}", packages);
		LogCore.BASE.info("list.size={}, set.size={}", packages.size(), packagesSet.size());
		Set<Class<?>> set = PackageClass.find();
		LogCore.BASE.info("{}", Util.prettyJsonStr(set.size()));
	}

}
