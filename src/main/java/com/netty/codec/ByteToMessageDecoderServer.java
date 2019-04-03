package com.netty.codec;

import java.util.List;

import com.netty.demo.SerializationUtil;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

/* <pre>　　
* 数据包格式
*@authro 何威
*2019年3月30日
* +——----——+——-----——+——----——+——----——+——-----——+
* | 包头   | 模块号  | 命令号 | 长度   | 数据              |
* +——----——+——-----——+——----——+——----——+——-----——+
* </pre>
* 包头4字节
* 模块号2字节short
* 命令号2字节short
* 长度4字节(描述数据部分字节长度)
*/
public class ByteToMessageDecoderServer extends ByteToMessageDecoder{

	@Override
	protected void decode(ChannelHandlerContext context, ByteBuf buf, List<Object> list) throws Exception {
		System.out.println("我已经进来了");
		buf.markReaderIndex();
		int read = buf.readableBytes();
		if (buf.readableBytes() < 4) {
			return;
		}
		int length = buf.readInt();
		if (read < length) {
			buf.resetReaderIndex();
			return;
		}
		System.out.println(buf.readShort());
		System.out.println(buf.readShort());
		int objLength = buf.readInt();
		System.out.println(objLength);
		byte[] by = new byte[objLength];
		buf.readBytes(by);
		list.add(SerializationUtil.deserialize(by));
		System.out.println(list.size()+ "list的长度");
	}

}
