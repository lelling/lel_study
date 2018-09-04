package com.mybatis;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.google.gson.Gson;
import com.mybatis.mapper.MyInterface;
import com.mybatis.model.Orders;

public class MySqlSession {
	
	private static SqlSessionFactoryBuilder sqlSessionFactoryBuilder;
	
	private static SqlSessionFactory sqlSessionFactory;
	static{
		String resource = "mybatis/mybatis-config.xml";
		Reader reader = null;
		try {
			reader = Resources.getResourceAsReader(resource);
			sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
			sqlSessionFactory = sqlSessionFactoryBuilder.build(reader);
		} catch (IOException e) {
			System.err.println(">>>>>>>>>>>>>>异常>>>>>>>>>>>>");
		}
	}
	
	public static void main(String... args) throws Exception{
		SqlSession sqlSession = sqlSessionFactory.openSession();
		MyInterface mapper = sqlSession.getMapper(MyInterface.class);

		/**
		 * 1-单条查询，做后置合并行
		 */
		Orders order = mapper.qryOrderById(1);
		System.out.println(new Gson().toJson(order));
		
		/**
		 * 2-分页查询
		 * 此时total为所有关联到的结果数，返回列表时会合并list类型属性，导致当前分页数据个数减少
		 */
		Page page = PageHelper.startPage(1, 10, true, true, null);
		List<Orders> orders = mapper.qryOrderForList(null);
		int total = (int) page.getTotal();
		System.out.println( orders.size() + ", " + page.getTotal() + "---"+ new Gson().toJson(orders));
		
		
		sqlSession.close();
	}
}
