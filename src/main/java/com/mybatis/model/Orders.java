package com.mybatis.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Orders implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4400020305254822261L;
	
	private Integer id;
	
	private Integer buyerid;
	
	private Date createdate;
	
	private String remark;
	
	private List<OrderItem> orderitems;

	public long getId() {
		return id;
	}
	
	public Integer getBuyerid() {
		return buyerid;
	}

	public void setBuyerid(Integer buyerid) {
		this.buyerid = buyerid;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getCreatedate() {
		return createdate;
	}

	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public List<OrderItem> getOrderitems() {
		return orderitems;
	}

	public void setOrderitems(List<OrderItem> orderitems) {
		this.orderitems = orderitems;
	}
	
	
}
