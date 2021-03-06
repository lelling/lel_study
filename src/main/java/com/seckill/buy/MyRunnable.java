package com.seckill.buy;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

public class MyRunnable implements Runnable {
 
    String watchkeys = "watchkeys";// 监视keys
    Jedis jedis = new Jedis("127.0.0.1", 6379);
    String userinfo;
    public MyRunnable() {
    }
    public MyRunnable(String uinfo) {
        this.userinfo=uinfo;
    }
    @Override
    public void run() {
        try {
        	String userI = watchkeys+userinfo;
        	String res = jedis.get(userI);
        	if (StringUtils.isNotEmpty(res) && (res.contains("抢购成功") || res.contains("已抢购过"))) {
        		String succinfo="用户：" + userI + "已抢购过";
                System.out.println(succinfo);
                /* 抢购成功业务逻辑 */
                jedis.set(userI, succinfo);
				return;
			}
            jedis.watch(watchkeys);// watchkeys
            String val = jedis.get(watchkeys);
            int valint = Integer.valueOf(val);
            
            if (valint <= 100 && valint>=1) {
            	Transaction tx = jedis.multi();// 开启事务
                tx.incrBy(watchkeys, -1);
                List<Object> list = tx.exec();// 提交事务，如果此时watchkeys被改动了，则返回null
                if (CollectionUtils.isEmpty(list)) {
                    String failuserifo = watchkeys+userinfo;
                    String failinfo="用户：" + failuserifo + "商品争抢失败，抢购失败";
                    System.out.println(failinfo);
                    /* 抢购失败业务逻辑 */
                    //jedis.set(failuserifo, failinfo);
                } else {
//                    for(Object succ : list){
                         String succuserifo = watchkeys+userinfo ;
                         String succinfo="用户：" + succuserifo + "抢购成功，当前抢购成功人数:"
                                 + (1-(valint-100));
                         System.out.println(succinfo);
                         /* 抢购成功业务逻辑 */
                         jedis.set(succuserifo, succinfo);
//                    }
                }
 
            } else {
                String failuserifo = watchkeys +  userinfo;
                String failinfo1="用户：" + failuserifo + "商品被抢购完毕，抢购失败";
                System.out.println(failinfo1);
                //jedis.setnx(failuserifo, failinfo1);
                return;
            }
 
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            jedis.close();
        }
 
    }
}
     
   