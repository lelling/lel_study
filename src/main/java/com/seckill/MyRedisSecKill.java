package com.seckill;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lel.Result;
import com.lel.utils.json.GsonUtils;
import com.seckill.buy.CycleCallable;
import com.seckill.buy.MyCallable;
import com.seckill.buy.MyRunnable;
import com.seckill.model.SecKillOrder;

import redis.clients.jedis.Jedis;

public class MyRedisSecKill {
	private Logger logger = LoggerFactory.getLogger(MyRedisSecKill.class);
	
	private final int taskSize = 100;
	
	public static final int GOODS_NUM = 1000;

	private final ExecutorService pool = Executors.newFixedThreadPool(taskSize);

	private Set<SecKillOrder> set = new HashSet<SecKillOrder>();

	public static void main(String[] args) {
        MyRedisSecKill myRedistest = new MyRedisSecKill();
        myRedistest.cycleCall();
    }
	
	/**
	 * 抢购<br>
	 * 无返回值，runable<br>
	 */
	public static void run(){
		final String watchkeys = "watchkeys";
        final Jedis jedis = new Jedis("127.0.0.1", 6379);
        jedis.set(watchkeys, "100");//设置起始的抢购数
        jedis.close();
        
        ExecutorService executor = Executors.newFixedThreadPool(20);  //20个线程池并发
        for (int i = 0; i < 1000; i++) {//设置1000个人来发起抢购
        	String user = "user_"+ i;//getRandomString(6);
            executor.submit(new MyRunnable(user));
        }
        executor.shutdown();
	}
	
	/**
	 * 抢购，排队中跳过<br>
	 */
	public void call(){
		final String goodsid = "10001";
	    String goods_key = "goods_num_" + goodsid;
        final Jedis jedis = new Jedis("127.0.0.1", 6379);
        jedis.set(goods_key, String.valueOf(GOODS_NUM));//设置起始的抢购数
        jedis.close();
        MyCallable myCallable = null;
        int total = 10;
        CompletionService<SecKillOrder> completionService = new ExecutorCompletionService<SecKillOrder>(pool);
        for (int i = 0; i < total; i++) {//设置1000个人来发起抢购
        	String user = "user_"+ i;//getRandomString(6);
        	int num = new Random(System.currentTimeMillis()).nextInt(3) + 1;
        	num = 1;
        	myCallable = new MyCallable(user, goodsid, num);
        	completionService.submit(myCallable);
        }
        for(int i = 0; i < total; i++){
        	try {
				SecKillOrder secKillOrder = completionService.take().get();
				if (null != secKillOrder) {
					System.err.println("执行入库操作:" + GsonUtils.toJson(secKillOrder));
				}
			} catch (Exception e) {
				logger.error("【抢购-排队抢占跳过】异常", e);
			}
        }
        pool.shutdown();
	}
	
	/**
	 * 抢购,排队中继续调用<br>
	 */
	public void cycleCall(){
		final String goodsid = "10001";
	    String goods_key = "goods_num_" + goodsid;
        final Jedis jedis = new Jedis("127.0.0.1", 6379);
        jedis.set(goods_key, "1000");//设置起始的抢购数
        jedis.close();
        CycleCallable myCallable = null;
        int total = 10 * 10000;
        CompletionService<Result<SecKillOrder>> completionService = new ExecutorCompletionService<Result<SecKillOrder>>(pool);
        for (int i = 0; i < total; i++) {//设置1000个人来发起抢购
        	String user = "user_"+ i;//getRandomString(6);
        	int num = 1;
        	// num = new Random(System.currentTimeMillis()).nextInt(3) + 1;
        	SecKillOrder secKillOrder = new SecKillOrder();
        	secKillOrder.setGoodsid(goodsid);
        	secKillOrder.setNum(num);
        	secKillOrder.setUserid(user);
        	myCallable = new CycleCallable(secKillOrder);
        	completionService.submit(myCallable);
        }
        for(int i = 0; i < total; i++){
        	try {
				Result<SecKillOrder> result = completionService.take().get();
				if (null != result) {
					if (result.exeSuccess()) {
						System.out.println("执行入库操作:" + GsonUtils.toJson(result.getData()));
					}else{
						System.out.println("抢购失败" + GsonUtils.toJson(result));
					}
					
				}
			} catch (Exception e) {
				logger.error("【抢购-持续尝试】异常", e);
			}
        }
        pool.shutdown();
	}
	
	public static String getRandomString(int length) { //length是随机字符串长度
        String base = "abcdefghijklmnopqrstuvwxyz0123456789";  
        Random random = new Random();  
        StringBuffer sb = new StringBuffer();  
        for (int i = 0; i < length; i++) {  
            int number = random.nextInt(base.length());  
            sb.append(base.charAt(number));  
        }  
        return sb.toString();  
     }
 
}
