package com.basis.simple;

public class StringTest {
	
	
	
	public static void main(String[] args){
		String str1 = "123";
		String str2 = str1;
		String str3 = str2 + "456";
		
		String str4 = new String(str1);
		
		System.out.println(str1 + ","+ str2 + ","+ str3+ ","+ str4);
		str2 = "111";
		System.out.println(str1 + ","+ str2 + ","+ str3+ ","+ str4);
	}
}
