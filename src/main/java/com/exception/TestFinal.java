package com.exception;

public class TestFinal {
	
	private static int res;
	
	public static int div(int i, int base) throws Exception{
		res = 0;
		try {
			return i/base;
		} finally{
			res = 1;
		}
	}
	
	public static void main(String[] args){
		try {
			System.out.println(div(1, 0));
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(res);
	}
}
