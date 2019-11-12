package com.pxx.netty.third;

import com.pxx.netty.second.MyClientInitializer;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author pxx
 * Date 2019/11/12 14:19
 * @Description
 */
public class MyChatClient {
	public static void main(String[] args) throws Exception {
		EventLoopGroup eventLoopGroup = new NioEventLoopGroup();
		try {
			Bootstrap bootstrap = new Bootstrap();
			bootstrap.group(eventLoopGroup).channel(NioSocketChannel.class)
					.handler(new MyChatClientInitializer());
			
			Channel channel = bootstrap.connect("localhost", 8899).sync().channel();
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			
			while (true) {
				channel.writeAndFlush(br.readLine() + "\r\n");
			}
		}finally {
			eventLoopGroup.shutdownGracefully();
		}
	}
}
