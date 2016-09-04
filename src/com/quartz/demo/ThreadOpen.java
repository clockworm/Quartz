package com.quartz.demo;


public class ThreadOpen {
	public static int i = 0;

	public int openOrclose() {
		i += 5;
		return i;
	}

}
