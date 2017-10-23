package org.srpc.http;

import java.util.Arrays;

import org.sonic.rpc.core.LogCore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication(scanBasePackages = { "org.srpc" })
public class App implements CommandLineRunner {

	@Autowired
	ApplicationContext ct;
	@Autowired
	App app;

	public static void main(String[] args) {
		LogCore.BASE.info("provider app start 开始");
		ConfigurableApplicationContext context = SpringApplication.run(App.class, args);
		LogCore.BASE.info("测试配置文件调用{}", Arrays.toString(context.getEnvironment().getActiveProfiles()));
	}

	@Override
	public void run(String... arg0) throws Exception {
//		String s=null;
//		Assert.notNull(s, "Class must not be null");
		LogCore.BASE.info("context autowired = {}", ct.getClass());// AnnotationConfigApplicationContext
		LogCore.BASE.info("app = {}", app.getClass());// App
	}
}
