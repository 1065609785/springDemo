package com.zsy.bean;

import org.springframework.beans.factory.annotation.Value;

public class TestJavaConfigBean {
	@Value("${test:qqq}")
	private String test;
	@Value("${zsy:zsy}")
	private String zsy;
	public String getTest() {
		return test;
	}
	public void setTest(String test) {
		this.test = test;
	}
	public String getZsy() {
		return zsy;
	}
	public void setZsy(String zsy) {
		this.zsy = zsy;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TestJavaConfigBean [test=");
		builder.append(test);
		builder.append(", zsy=");
		builder.append(zsy);
		builder.append("]");
		return builder.toString();
	}
  
}
