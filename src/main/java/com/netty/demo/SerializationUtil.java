package com.netty.demo;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
*
*@authro 何威
*2019年3月30日
*/
public class SerializationUtil {
	public static final byte[] serialize(Object obj) throws IOException {
		ByteArrayOutputStream  out = new ByteArrayOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(out);
		oos.writeObject(obj);
		return out.toByteArray();
	}
	public static final Object deserialize(byte[] by) throws IOException, ClassNotFoundException {
		ByteArrayInputStream bis = new ByteArrayInputStream(by);
		ObjectInputStream ois = new ObjectInputStream(bis);
		return ois.readObject();
	}
}
