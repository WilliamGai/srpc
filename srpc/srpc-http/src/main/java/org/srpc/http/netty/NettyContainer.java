package org.srpc.http.netty;

import java.util.List;

import org.sonic.rpc.core.LogCore;
import org.sonic.rpc.core.utils.LimitThreadPool;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;

public class NettyContainer extends Container {
	ServerBootstrap bootstrap = new ServerBootstrap();
	int port = 0;
	int connectTimeout = 0;
	int readTimeout = 0;
	HandlerHolder handlerHolder;

	/** function */
	public static interface HandlerHolder {
		List<ChannelHandler> getChannelHandlers();
	}

	public NettyContainer(int port, int connectTimeout, int readTimeout, HandlerHolder handlerHolder) {
		super();
		this.port = port;
		this.connectTimeout = connectTimeout;
		this.readTimeout = readTimeout;
		this.handlerHolder = handlerHolder;
	}
	/***
	 * netty自己可能会开非守护的子线程，也可能不会，因此设置此处的线程deamon为false
	 */
	public void start() {
		LimitThreadPool.execute(new Runnable() {

			@Override
			public void run() {
				EventLoopGroup bossGroup = new NioEventLoopGroup();
				EventLoopGroup workerGroup = new NioEventLoopGroup(Runtime.getRuntime().availableProcessors() * 2);
				try {

					bootstrap.group(bossGroup, workerGroup).option(ChannelOption.SO_TIMEOUT, readTimeout)
							.option(ChannelOption.CONNECT_TIMEOUT_MILLIS, connectTimeout)
							.option(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT)
							.channel(NioServerSocketChannel.class)
							.childHandler(new ChannelInitializer<SocketChannel>() {
								// 会在不同的线程触发
								@Override
								public void initChannel(SocketChannel ch) throws Exception {
									LogCore.BASE.info("netty server init! SocketChannel,{}", ch);
									try {
										final List<ChannelHandler> channelHandlers = handlerHolder.getChannelHandlers();
										ChannelPipeline pipeline = ch.pipeline();
										pipeline.addLast(new HttpRequestDecoder());
										pipeline.addLast(new HttpResponseEncoder());
										pipeline.addLast(channelHandlers.toArray(new ChannelHandler[] {}));
									} catch (Exception e) {
										LogCore.BASE.error("init Channel err,Thread is{}", Thread.currentThread(), e);
									}
								}

							});
					LogCore.BASE.info("netty server start! port={}, connectTimeout={}, readTimeout{}", port,
							connectTimeout, readTimeout);
					bootstrap.bind(port).sync().channel().closeFuture().sync();
				} catch (Exception e) {
					LogCore.BASE.error("http start server err", e);
				} finally {
					bossGroup.shutdownGracefully();
					workerGroup.shutdownGracefully();
				}
			}
		});
	}

	@Override
	public String toString() {
		return "HttpServer [port=" + port + ", connectTimeout=" + connectTimeout + ", readTimeout=" + readTimeout
				+ "]";
	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isRunning() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isAutoStartup() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void stop(Runnable callback) {
		// TODO Auto-generated method stub
		
	}
}
