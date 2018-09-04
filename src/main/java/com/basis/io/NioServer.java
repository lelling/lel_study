package com.basis.io;

import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class NioServer {
	public static void main(String... args) throws Exception{
		Selector serverSelector = Selector.open();
		Selector clientSelector = Selector.open();
		new Thread(()->{
			try {
				System.out.println("nio 启动... ");
				ServerSocketChannel listenerChannel = ServerSocketChannel.open();
				listenerChannel.socket().bind(new InetSocketAddress(8081));
				listenerChannel.configureBlocking(false);
				listenerChannel.register(serverSelector, SelectionKey.OP_ACCEPT);
				while(true){
					if (serverSelector.select(1) > 0) {
						Set<SelectionKey> set = serverSelector.selectedKeys();
						Iterator<SelectionKey> keyItertor = set.iterator();
						while(keyItertor.hasNext()){
							SelectionKey key = keyItertor.next();
							if (key.isAcceptable()) {
								try {
									SocketChannel clientChannel = ((ServerSocketChannel)key.channel()).accept();
									clientChannel.configureBlocking(false);
									clientChannel.register(clientSelector, SelectionKey.OP_ACCEPT);
								} finally{
									keyItertor.remove();
								}
							}
						}
					}
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}).start();
	}
}
