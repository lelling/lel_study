package com.mybatis.model;

import java.io.Serializable;

import org.apache.ibatis.type.Alias;

@Alias("goods")
public class Goods implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8732778821099835174L;

	private Integer id;
	
	private String goodsname;
	
	private String detail;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getGoodsname() {
		return goodsname;
	}

	public void setGoodsname(String goodsname) {
		this.goodsname = goodsname;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}
	
	
}
