package com.pxx.netty.first;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

import java.net.URI;

/**
 * @author pxx
 * Date 2019/11/8 10:21
 * @Description
 */
public class TestHttpServerHandler extends SimpleChannelInboundHandler<HttpObject> {
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, HttpObject msg) throws Exception {
		if (msg instanceof HttpRequest) {
			
			HttpRequest request = (HttpRequest)msg;
			System.out.println("请求方法名：" + request.method().name());
			URI uri = new URI(request.uri());
			if ("/favicon.ico".equals(uri.getPath())) {
				System.out.println("请求favicon.ico");
				return;
			}
			ByteBuf content = Unpooled.copiedBuffer("Hello World", CharsetUtil.UTF_8);
			FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1,
					HttpResponseStatus.OK,
					content);
			response.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/plain");
			response.headers().set(HttpHeaderNames.CONTENT_LENGTH, content.readableBytes());
			
			ctx.writeAndFlush(response);
		}
	}
	
	
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		System.out.println("channel active.");
		super.channelActive(ctx);
	}
	
	@Override
	public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
		System.out.println("channel registered.");
		super.channelRegistered(ctx);
	}
	
	@Override
	public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
		System.out.println("handler added.");
		super.handlerAdded(ctx);
	}
	
	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		System.out.println("TestHttpServerHandler.channelInactive");
		super.channelInactive(ctx);
	}
	
	@Override
	public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
		System.out.println("TestHttpServerHandler.channelUnregistered");
		super.channelUnregistered(ctx);
	}
}
