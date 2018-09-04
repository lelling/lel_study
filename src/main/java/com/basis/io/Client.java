package com.basis.io;

import java.net.Socket;

public class Client {
	public static void main(String[] args){
		System.out.println("启动客户端...");
		new Thread(()->{
			Socket socket = null;
				while(null == socket){
					try {
						socket = new Socket("127.0.0.1", 8081);
					} catch (Exception e) {
						System.err.println(System.currentTimeMillis() + "尝试连接失败");
						try {
							Thread.sleep(3000);
						} catch (Exception e1) {
							
						}
					}
				}
				while(true){
					try {
						String str = "hello:" + System.currentTimeMillis();
						System.out.println("send:" + str);
						socket.getOutputStream().write(str.getBytes());
						socket.getOutputStream().flush();
						Thread.sleep(2000);
					} catch (Exception e) {
						System.err.println(System.currentTimeMillis() + "尝试发送失败");
						try {
							Thread.sleep(3000);
						} catch (Exception e1) {
							
						}
					}
				}
		}).start();
	}
}
