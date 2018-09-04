package com.basis.oom;

public class StatckOom {
	
	/**
	 * 递归调用方法
	 */
	public static void method(){
		method();
	}
	
	public static void ceateThread(){
		while (true) {
			Thread thread = new Thread(new Runnable() {
				
				@Override
				public void run() {
					//System.out.println("");
					while(true);
				}
			});
			thread.start();
		}
	}
	
	
	public static void main(String[]args){
		ceateThread();
	}
}
