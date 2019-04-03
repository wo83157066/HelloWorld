package com.netty.demo;

import com.netty.codec.ChannelHandlerClient;
import com.netty.codec.MessageToByteEndecoderClient;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;

/**
*
*@authro 何威
*2019年3月30日
*/
public class NettyClient {
	
	public static void main(String[] args) {
		Bootstrap strap = new Bootstrap();
		
		EventLoopGroup event = new NioEventLoopGroup();
		
		strap.group(event)
			 .option(ChannelOption.TCP_NODELAY, true)
			 .option(ChannelOption.SO_KEEPALIVE, true)
			 .channel(NioSocketChannel.class)
			 .handler(new ChannelInitializer<SocketChannel>() {

				@Override
				protected void initChannel(SocketChannel socket) throws Exception {
					socket.pipeline().addLast(new ObjectDecoder(1024 * 1024,
							ClassResolvers.weakCachingConcurrentResolver(this.getClass().getClassLoader())))
									 .addLast(new MessageToByteEndecoderClient())
									 .addLast(new ChannelHandlerClient());
				}
			});
		try {
			ChannelFuture sync = strap.connect("127.0.0.1",8010);
			sync.channel().closeFuture().sync();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			event.shutdownGracefully();
		}
	}

}
