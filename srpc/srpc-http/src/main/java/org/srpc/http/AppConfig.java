package org.srpc.http;

import java.util.ArrayList;
import java.util.List;

import org.sonic.rpc.core.LogCore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.srpc.http.netty.Container;
import org.srpc.http.netty.NettyContainer;
import org.srpc.http.netty.NettyContainer.HandlerHolder;
import org.srpc.http.netty.NettyHttpServerHandler;
import org.srpc.http.servlet.DispatcherServlet;

import io.netty.channel.ChannelHandler;
/***
 * 参考
 * @author bao
 * @date 2017年10月10日 下午9:50:09
 */
@Configuration
public class AppConfig {
	@Configuration
//	@Conditional(DefaultDispatcherServletCondition.class)
	protected static class DispatcherServletConfiguration {
		@Bean
		public DispatcherServlet dispatcherServlet() {
			DispatcherServlet dispatcherServlet = new DispatcherServlet();
//			dispatcherServlet.setDispatchOptionsRequest(
//					this.webMvcProperties.isDispatchOptionsRequest());
//			dispatcherServlet.setDispatchTraceRequest(
//					this.webMvcProperties.isDispatchTraceRequest());
//			dispatcherServlet.setThrowExceptionIfNoHandlerFound(
//					this.webMvcProperties.isThrowExceptionIfNoHandlerFound());
			LogCore.BASE.info("DispatcherServlet bean init!");
			return dispatcherServlet;
		}
	}

	/** netty container */
	@Bean
	@Autowired
	Container getContainer(
			@Value("${server.port}") final int port,
			@Value("${server.connectTimeout}") final int connectTimeout,
			@Value("${server.readTimeout}") final int readTimeout, 
			final ApplicationContext ctx) {
		final HandlerHolder holder = new HandlerHolder() {

			@Override
			public List<ChannelHandler> getChannelHandlers() {
				final List<ChannelHandler> channelHandlers = new ArrayList<>();
				channelHandlers.add(new NettyHttpServerHandler(a->"您发送的是"+a));
//				channelHandlers.add((ChannelHandler) ctx.getBean("httpIdleStateHandler"));// scope="prototype"
//				channelHandlers.add((ChannelHandler) ctx.getBean("httpServerHandler"));// scope="prototype"
				return channelHandlers;
			}
		};
		Container nettyContainer = new NettyContainer(port, connectTimeout, readTimeout, holder);
		nettyContainer.start();
		return nettyContainer;
	}
}