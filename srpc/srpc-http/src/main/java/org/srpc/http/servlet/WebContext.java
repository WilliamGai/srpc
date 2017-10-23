package org.srpc.http.servlet;

import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import javax.xml.transform.stream.StreamSource;

import org.sonic.rpc.core.LogCore;
import org.sonic.rpc.core.utils.Util;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.EmbeddedValueResolverAware;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringValueResolver;
import org.srpc.http.servlet.mapping.RequestMapping;

/***
 * 管理所有的Controller
 * 
 * @author bao
 * @date 2017年10月4日 下午2:27:43
 */
@Order(value = Integer.MAX_VALUE)
@Component
public class WebContext implements ApplicationContextAware, EmbeddedValueResolverAware, CommandLineRunner {

	private ApplicationContext applicationContext;
	private static StringValueResolver valueResolver = null;

	private Boolean applicationContextInjected = false;
	public static final String WEB_APPLICATION_CONTEXT_ATTRIBUTE = DispatcherServlet.class.getName() + ".CONTEXT";

	private static final Map<String, Object> mappings = new HashMap<>();

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) {
		LogCore.BASE.info("webcontext init {}", applicationContext);
		if (this.applicationContext == null) {
			this.applicationContext = applicationContext;
			this.applicationContextInjected = true;
		}
		Map<String, Object> controllers = applicationContext.getBeansWithAnnotation(Controller.class);
		controllers.values().forEach((handler) -> {
			Method[] methods = handler.getClass().getDeclaredMethods();
			Stream.of(methods).forEach(m -> {
				RequestMapping mpping = m.getAnnotation(RequestMapping.class);
				if (null != mpping) {
					String[] values = mpping.value();
					LogCore.BASE.info("{},{}", m.getName(), values);
					LogCore.BASE.info("{},{}", m.getName(), mpping);
				}
			});

		});
		LogCore.BASE.info("all controllers={}", Util.prettyJsonStr(controllers));
	}

	public static StringValueResolver getStringValueResolver() {
		return valueResolver;
	}

	public static String getString(String strVal) {
		return valueResolver.resolveStringValue(strVal);
	}

	public final ApplicationContext getApplicationContext() {
		return this.applicationContext;
	}

	@Override
	public void setEmbeddedValueResolver(StringValueResolver resolver) {
		valueResolver = resolver;
		LogCore.BASE.info("init valueResolver:{}", valueResolver);

	}

	@Override
	public void run(String... args) throws Exception {
		LogCore.BASE.info("{} init start! the order is {}", this.getClass().getSimpleName(),
		            Util.toNullDefalut(this.getClass().getAnnotation(Order.class), Order::value, "null"));
		LogCore.BASE.info("{} init start! args is {}", this.getClass().getSimpleName(), args);
	}
}
