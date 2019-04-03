package com.netty.codec;

import com.netty.entity.im;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/**
*
*@authro 何威
*2019年3月30日
*/
public class ChannelHandlerClient extends ChannelHandlerAdapter{

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		for (int i = 0; i < 2000; i++) {
			im m = new im();
			m.setS("张三"+i);
			ctx.channel().writeAndFlush(m);
			
		}
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		System.out.println(msg.toString());
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		super.exceptionCaught(ctx, cause);
	}
	
	
}
