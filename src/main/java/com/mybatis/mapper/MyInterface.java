package com.mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.mybatis.model.Orders;
import com.mybatis.model.TestModel;

public interface MyInterface {
	
	public List<TestModel> list(@Param("test")TestModel testModel);
	
	public int update(@Param("test") TestModel testModel);
	
	public List<Orders> qryOrderForList(@Param("id") Integer id);
	
	public Orders qryOrderById(@Param("id") Integer id);
}
