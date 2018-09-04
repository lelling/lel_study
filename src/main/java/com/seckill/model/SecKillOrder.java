package com.seckill.model;

import java.io.Serializable;
import java.util.Date;

public class SecKillOrder implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1516299274766347399L;

	/**
	 * 用户id<br>
	 */
	private String userid;
	
	/**
	 * 商品编码<br>
	 */
	private String goodsid;
	
	/**
	 * 数量<br>
	 */
	private Integer num;
	
	/**
	 * 订单日期<br>
	 */
	private Date orderTime;
	
	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getGoodsid() {
		return goodsid;
	}

	public void setGoodsid(String goodsid) {
		this.goodsid = goodsid;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public Date getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(Date orderTime) {
		this.orderTime = orderTime;
	}
	
	
	
}
