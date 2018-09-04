package com.seckill.buy;

import java.util.List;
import java.util.concurrent.Callable;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.seckill.model.SecKillOrder;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

public class MyCallable implements Callable<SecKillOrder>{
	
	private Jedis jedis = new Jedis("127.0.0.1", 6379);
	
	private String userid;
	
	private String goodsid;
	
	private Integer num;
	
	public MyCallable(String userid, String goodsid, Integer num){
		this.userid = userid;
		this.goodsid = goodsid;
		this.num = num;
	}
	
	@Override
	public SecKillOrder call() throws Exception {
		SecKillOrder secKillOrder = null;
		try {
			String watchkeys = "goods_num_" + goodsid;
			String buy = "user_buy_"+ userid + "_" + goodsid;
			// 检查是否已购买
			String sBuyNum = jedis.get(buy);
			if (StringUtils.isNotBlank(sBuyNum)) {
				System.out.println(buy + "已抢购过:" + sBuyNum);
				return secKillOrder;
			}
			jedis.watch(watchkeys);
			String val = jedis.get(watchkeys);
			int valint = Integer.valueOf(val);
			if (valint >= num) {
				// 只要当前数量大于购买数量就扣减redis库存
				Transaction tx = jedis.multi();// 开启事务
			    tx.incrBy(watchkeys, -1 * num);
			    List<Object> list = tx.exec();// 提交事务，如果此时watchkeys被改动了，则返回null
			    if (CollectionUtils.isEmpty(list)) {
			        String failinfo = buy + " 抢购排队中...";
			        System.out.println(failinfo);
			        /* 抢购失败业务逻辑 */
			        //jedis.set(failuserifo, failinfo);
			    } else {
			        for(Object succ : list){
			             String succinfo = buy + " 抢购成功, 已抢购" + (num - (valint-100)) + "- 本次抢购：" + num;
			             System.out.println(succinfo);
			             secKillOrder = new SecKillOrder();
			             secKillOrder.setNum(num);
			     		 secKillOrder.setGoodsid(goodsid);
			     		 secKillOrder.setUserid(userid);
			             /* 抢购成功业务逻辑 */
			             jedis.set(buy, String.valueOf(num));
			        }
			    }

			} else {
				String failinfo1 = null;
				if (valint > 0) {
					// 数量不足
					failinfo1 = buy + " 商品数量不足, 剩余：" + valint + " - 抢购:" + num;
				}else{
					// 已抢完
					failinfo1 = buy + " 已售完";
				}
			    System.out.println(failinfo1);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			jedis.close();
		}
		return secKillOrder;
	}

}
