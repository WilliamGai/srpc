package org.srpc.http.netty;

import java.util.function.Function;

import org.sonic.rpc.core.LogCore;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.DefaultHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpContent;
import io.netty.handler.codec.http.HttpHeaderNames;
import io.netty.handler.codec.http.HttpHeaderValues;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpUtil;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.util.CharsetUtil;

public class NettyHttpServerHandler extends ChannelInboundHandlerAdapter {
	private HttpRequest request;
	private Function<String, String> httpContentfunc;

	public NettyHttpServerHandler(Function<String, String> httpCall) {
		this.httpContentfunc = httpCall;
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		LogCore.BASE.info("msg is{}", msg.getClass().getCanonicalName());
		if (HttpRequest.class.isInstance(msg)) {
			request = (HttpRequest) msg;

			String uri = request.uri();
			LogCore.BASE.info("Uri:" + uri);
			FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK,
			            Unpooled.wrappedBuffer("没有内容吗哈哈".getBytes(CharsetUtil.UTF_8)));
			response.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/html;charset=utf-8");
			response.headers().set(HttpHeaderNames.SET_COOKIE, "JSESSIONID=10086; Path=/; HttpOnly");//Set-Cookie:JSESSIONID=8D7A69FDFFC2B995320205AFAE5BED66; Path=/; HttpOnly

//			response.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/plain;charset=utf-8");
			response.headers().set(HttpHeaderNames.CONTENT_LENGTH, response.content().readableBytes());
			if (HttpUtil.isKeepAlive(request)) {
				response.headers().set(HttpHeaderNames.CONNECTION, HttpHeaderValues.KEEP_ALIVE);
			}
			ctx.write(response);
			ctx.flush();
		}
//		if(DefaultHttpRequest.class.isInstance(msg)){
//			
//		}
		if (msg instanceof HttpContent) {
			HttpContent content = (HttpContent) msg;
			ByteBuf buf = content.content();

			LogCore.BASE.debug("buffer={}," + "{}", buf, buf.readableBytes());
			String res = httpContentfunc.apply(buf.toString(CharsetUtil.UTF_8));
			buf.release();
			FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK,
			            Unpooled.wrappedBuffer(res.getBytes(CharsetUtil.UTF_8)));
			response.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/plain");
			response.headers().set(HttpHeaderNames.CONTENT_LENGTH, response.content().readableBytes());
			if (HttpUtil.isKeepAlive(request)) {
				response.headers().set(HttpHeaderNames.CONNECTION, HttpHeaderValues.KEEP_ALIVE);
			}
			ctx.write(response);
			ctx.flush();
		}
	}

	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		ctx.flush();
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
		LogCore.BASE.error("err", cause);
		ctx.close();
	}
}