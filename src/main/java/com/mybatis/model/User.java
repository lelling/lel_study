package com.mybatis.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

public class User implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7157887960414025286L;
	private Integer id;
	private String uname;
	private String upsw;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getUpsw() {
		return upsw;
	}
	public void setUpsw(String upsw) {
		this.upsw = upsw;
	}
	
	public static void main(String[] args){
		List<User> list = new ArrayList<>();
		User user = null;
		for(int i=0; i < 5; i++){
			user = new User();
			user.setId(i);
			user.setUname(String.valueOf(i));
			user.setUpsw(String.valueOf(i));
			list.add(user);
			user.setUname("u-" + i);
			user = null;
		}
		System.out.println(new Gson().toJson(list));
	}
}
