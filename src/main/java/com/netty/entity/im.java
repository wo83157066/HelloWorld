package com.netty.entity;

import java.io.Serializable;

/**
*
*@authro 何威
*2019年3月30日
*/
public class im implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String s;
	
	public void setS(String s) {
		this.s = s;
	}
	
	public String getS() {
		return s;
	}

	@Override
	public String toString() {
		return "im [s=" + s + "]";
	}
}
