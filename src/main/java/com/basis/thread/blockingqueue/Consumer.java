package com.basis.thread.blockingqueue;

import java.util.Random;
import java.util.concurrent.BlockingQueue;

import com.basis.thread.Product;

public class Consumer implements Runnable{
	private int no;
	private BlockingQueue<Product> queue;
	
	private static final int SLEEPTIME = 1000;
	
	public Consumer(int no, BlockingQueue<Product> queue){
		this.queue = queue;
		this.no = no;
	}
	
	@Override
	public void run() {
		Random random = new Random();
		
		try {
			while(true){
				Product product = queue.take();
				if (null != product) {
					System.out.println(no + "消费：" + product.getId());
					Thread.sleep(random.nextInt(SLEEPTIME));
				}
			}
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}
	
}
