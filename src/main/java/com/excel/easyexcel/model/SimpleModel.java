package com.excel.easyexcel.model;

import java.math.BigDecimal;
import java.sql.Date;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;

public class SimpleModel extends BaseRowModel{
	
	@ExcelProperty(value = "编号", index = 0)
	private Integer id;
	
	@ExcelProperty(value = "姓名", index = 1)
	private String name;
	
	@ExcelProperty(value = "大小", index = 2)
	private long size;
	
	@ExcelProperty(value = "创建日期", index = 3, format = "yyyy-MM-dd")
	private Date createDate;
	
	@ExcelProperty(value = "月额", index = 4)
	private BigDecimal inPerMonth;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getSize() {
		return size;
	}
	public void setSize(long size) {
		this.size = size;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public BigDecimal getInPerMonth() {
		return inPerMonth;
	}
	public void setInPerMonth(BigDecimal inPerMonth) {
		this.inPerMonth = inPerMonth;
	}
	
}
