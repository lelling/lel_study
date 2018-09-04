package com.mybatis.model;

import java.io.Serializable;

public class OrderItem implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7811189100538539262L;

	private Integer id;
	
	private String orderid;
	
	private Integer goodsid;
	
	private Integer num;
	
	private Goods goods;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getOrderid() {
		return orderid;
	}

	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}

	public Integer getGoodsid() {
		return goodsid;
	}

	public void setGoodsid(Integer goodsid) {
		this.goodsid = goodsid;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public Goods getGoods() {
		return goods;
	}

	public void setGoods(Goods goods) {
		this.goods = goods;
	}
	
	
}
