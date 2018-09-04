package com.seckill.buy;

import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lel.Result;
import com.seckill.model.SecKillOrder;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

/**
 * 此处仅模拟单redis单应用服务场景<br>
 * @author lel
 *
 */
public class CycleCallable implements Callable<Result<SecKillOrder>>{
	
	private Logger logger = LoggerFactory.getLogger(CycleCallable.class);
	
	private Jedis jedis = new Jedis("127.0.0.1", 6379);
	
	private SecKillOrder secKillOrder;
	
	/**
	 * 此值为库存数量的10倍，即允许10倍库存的用户进入访问redis<br>
	 * 好处：可清除大量没有必要的请求;此数量用户的执行状态只有：抢购成功、抢购失败（含已抢购）两种情况
	 * 缺点：大量已抢购过的或者相同用户请求
	 */
	public static int max = 10000;
	
	public CycleCallable(SecKillOrder secKillOrder){
		this.secKillOrder = secKillOrder;
	}
	
	@Override
	public Result<SecKillOrder> call() throws Exception {
		Result<SecKillOrder> result = new Result<>();
		result.setData(secKillOrder);
		// 剔除大量无效请求
		System.out.println("****当前值***" + CycleCallable.max + "****");
		if (CycleCallable.max-- < 1) {
			result.fail("已售完");
			return result;
		}
		while(true){
			// 到此处的请求必有两种回执状态：抢购成功，抢购失败(含已抢购)
			// 由于redis事务导致的排队（抢购失败的一种情况）可重新发起
			try {
				String goodsid = secKillOrder.getGoodsid();
				String userid = secKillOrder.getUserid();
				Integer num = secKillOrder.getNum();
				String watchkeys = "goods_num_" + goodsid;
				String buy = "user_buy_"+ userid + "_" + goodsid;
				// 检查是否已购买
				String sBuyNum = jedis.get(buy);
				if (StringUtils.isNotBlank(sBuyNum)) {
					System.out.println(buy + "已抢购过:" + sBuyNum);
					logger.debug(buy + "已抢购过:" + sBuyNum);
					result.fail("2", "已抢购过");
					break;
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
				        logger.debug(failinfo);
				        /* 抢购失败业务逻辑 */
				        //jedis.set(failuserifo, failinfo);
				    } else {
//				        for(Object succ : list){
				             String succinfo = buy + " 抢购成功, 已抢购" + (num - (valint-100)) + "- 本次抢购：" + num;
				             System.out.println(succinfo);
				             /* 抢购成功业务逻辑 */
				             jedis.set(buy, String.valueOf(num));
				             secKillOrder.setOrderTime(new Date());
				             result.success("抢购成功");
				             logger.debug(succinfo);
//				        }
				        break;
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
					logger.debug(failinfo1);
					result.fail("-1", failinfo1);
					break;
				}
			} catch (Exception e) {
				throw new RuntimeException(e);
			}finally {
				jedis.close();
			}
		}
		return result;
	}
	
}
