package com.basis.thread.blockingqueue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;

import com.basis.thread.Product;

public class BlockingTest {
	
	public static void main(String[] args) throws Exception{
		BlockingQueue<Product> queue = new LinkedBlockingDeque<>(10);
        Producer p1 = new Producer(1, queue);
        Producer p2 = new Producer(2, queue);
        Producer p3 = new Producer(3, queue);
        Consumer c1 = new Consumer(4, queue);
        Consumer c2 = new Consumer(5, queue);
        Consumer c3 = new Consumer(6,queue);
        ExecutorService service = Executors.newCachedThreadPool();
        service.execute(p1);
        service.execute(p2);
        service.execute(p3);
        service.execute(c1);
        service.execute(c2);
        service.execute(c3);
//        Thread.sleep(10*1000);
//        p1.stop();
//        p2.stop();
//        p3.stop();
//        Thread.sleep(3000);
//        service.shutdown();
	}
}
