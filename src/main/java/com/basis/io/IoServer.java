package com.basis.io;

import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class IoServer {
	public static void main(String[] args) throws Exception{
		System.out.println("启动服务端...");
		ServerSocket server = new ServerSocket(8081);
		new Thread(()->{
			while(true){
				try {
					Socket socket = server.accept();
					new Thread(()->{
						try {
							byte[] data = new byte[1024];
							InputStream iStream = socket.getInputStream();
							while (true) {
								int len;
								while((len=iStream.read(data))!=-1){
									System.out.println(new String(data, 0 ,len));
								}
							}
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
					}).start();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}).start();
	}
}
