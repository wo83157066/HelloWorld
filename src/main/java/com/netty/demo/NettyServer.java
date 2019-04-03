package com.netty.demo;


import com.netty.codec.ByteToMessageDecoderServer;
import com.netty.codec.ChannelHandlerServer;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.ServerSocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.serialization.ObjectEncoder;

/**
*
*@authro 何威
*2019年3月30日
*/
public class NettyServer {
	public static void main(String[] args) {
		ServerBootstrap server = new ServerBootstrap();
		
		EventLoopGroup listener = new NioEventLoopGroup();
		EventLoopGroup work = new NioEventLoopGroup();
		
		server.group(listener,work)
			  .option(ChannelOption.SO_BACKLOG, 2048)
			  .childOption(ChannelOption.TCP_NODELAY, true)
			  .childOption(ChannelOption.SO_KEEPALIVE, true)
			  .channel(NioServerSocketChannel.class)
			  .childHandler(new ChannelInitializer<Channel>() {

				@Override
				protected void initChannel(Channel socket) throws Exception {
					socket.pipeline()
						  .addLast(new ObjectEncoder())
						  .addLast(new ByteToMessageDecoderServer())
						  .addLast(new ChannelHandlerServer());
				}
			});
		try {
			ChannelFuture sync = server.bind(8010).sync();
			sync.channel().closeFuture().sync();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally {
			listener.shutdownGracefully();
			work.shutdownGracefully();
		}
		
		
	}
}
