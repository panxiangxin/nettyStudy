package com.pxx.netty.second;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.UUID;

/**
 * @author pxx
 * Date 2019/11/12 10:26
 * @Description
 */
public class MyServerHandler extends SimpleChannelInboundHandler<String> {
	
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
		System.out.println(ctx.channel().remoteAddress() + ", " + msg);
		ctx.channel().writeAndFlush("from server: " + UUID.randomUUID());
	}
	
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		cause.printStackTrace();
		ctx.close();
	}
}
