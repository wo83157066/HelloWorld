package com.netty.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* <pre>　　
* 数据包格式
*@authro 何威
*2019年3月30日
* +——----——+——-----——+——----——+——----——+——-----——+
* | 包头   | 模块号  | 命令号 | 长度   | 数据    |
* +——----——+——-----——+——----——+——----——+——-----——+
* </pre>
* 包头4字节
* 模块号2字节short
* 命令号2字节short
* 长度4字节(描述数据部分字节长度)
*/
public class HttpBody implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int header;
	
	private short model;
	
	private short cmd;
	
	private int length;
	
	private byte[] obj;
	

	public int getHeader() {
		return header;
	}

	public void setHeader(int header) {
		this.header = header;
	}

	public short getModel() {
		return model;
	}

	public void setModel(short model) {
		this.model = model;
	}

	public short getCmd() {
		return cmd;
	}

	public void setCmd(short cmd) {
		this.cmd = cmd;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public byte[] getObj() {
		return obj;
	}

	public void setObj(byte[] obj) {
		this.obj = obj;
	}

	@Override
	public String toString() {
		return "HttpBody [header=" + header + ", model=" + model + ", cmd=" + cmd + ", length=" + length + ", obj="
				+ Arrays.toString(obj) + "]";
	}
}