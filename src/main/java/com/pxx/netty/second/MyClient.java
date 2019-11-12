package com.pxx.netty.second;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * @author pxx
 * Date 2019/11/12 10:33
 * @Description
 */
public class MyClient {
	public static void main(String[] args) throws InterruptedException {
		EventLoopGroup eventLoopGroup = new NioEventLoopGroup();
		try {
			Bootstrap bootstrap = new Bootstrap();
			bootstrap.group(eventLoopGroup).channel(NioSocketChannel.class)
					.handler(new MyClientInitializer());
			
			ChannelFuture channelFuture = bootstrap.connect("localhost", 8899).sync();
			channelFuture.channel().closeFuture().sync();
		}finally {
		eventLoopGroup.shutdownGracefully();
		}
	}
}
