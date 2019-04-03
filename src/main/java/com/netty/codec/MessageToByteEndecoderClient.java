package com.netty.codec;

import com.netty.demo.SerializationUtil;
import com.netty.entity.HttpBody;
import com.netty.entity.im;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
*
*@authro 何威
*2019年3月30日
*/
public class MessageToByteEndecoderClient extends MessageToByteEncoder<im>{

	@Override
	protected void encode(ChannelHandlerContext context, im i, ByteBuf buf) throws Exception {
		byte[] serialize = SerializationUtil.serialize(i);
		int length = serialize.length;
		System.out.println(length); 
		buf.writeInt(12 + length);
		buf.writeShort((short)23);
		buf.writeShort((short)23);
		buf.writeInt(length);
		buf.writeBytes(serialize);
	}
}
