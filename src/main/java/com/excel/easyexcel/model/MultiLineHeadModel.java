package com.excel.easyexcel.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;

public class MultiLineHeadModel extends BaseRowModel implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3658555700957092003L;

	@ExcelProperty(value = {"基本信息","人员信息", "编号"}, index = 0)
	private Integer id;
	
	@ExcelProperty(value = {"基本信息","人员信息", "姓名"}, index = 1)
	private String name;
	
	@ExcelProperty(value = {"基本信息","规模", "大小"}, index = 2)
	private long size;
	
	@ExcelProperty(value = {"基本信息","创建日期","创建日期"}, index = 3, format = "yyyy-MM-dd")
	private Date createDate;
	
	@ExcelProperty(value = {"月额","月额","月额t"}, index = 4)
	private BigDecimal inPerMonth;

	@ExcelProperty(value = {"月额","月额","小"}, index = 5)
	private String a_ss;
	
	private String remark;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getA_ss() {
		return a_ss;
	}

	public void setA_ss(String a_ss) {
		this.a_ss = a_ss;
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

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}
