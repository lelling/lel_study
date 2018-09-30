package com.basis.thread.blockingqueue;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

import com.basis.thread.Product;

public class Producer implements Runnable{
	
	private volatile boolean isRunning = true;
	
	private int no;
	
	private BlockingQueue<Product> queue;
	
	private static AtomicInteger count = new AtomicInteger();
	
	private static final int SLEEPTIME = 1000;
	
	public Producer(int no, BlockingQueue<Product> queue){
		this.no = no;
		this.queue = queue;
	}
	
	@Override
	public void run() {
		Product product = null;
		Random random = new Random();
		try {
			while (isRunning) {
				Thread.sleep(random.nextInt(SLEEPTIME));

				product = new Product(count.incrementAndGet());
				if (!queue.offer(product)) {
					System.err.println(no + "生产失败：" + product.getId());
				} else {
					System.out.println(no + "生产成功：" + product.getId());
				}
			}
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}
	
	public void stop(){
		isRunning = false;
	}

}
