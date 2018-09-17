package com.basis.loadclass;

import java.io.Serializable;

public class FatherClass implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4414778134444664271L;

	private int id = -1;
	
	private String name;
	
	private static int si = -1;
	
	private static String sname;
	
	static {
		si = 1;
		System.out.println("执行" + FatherClass.class.getName() + "--static块");
//		i=1;
	}
	
	{
		id=1;
		System.out.println("执行"+ FatherClass.class.getName() + "--类块");
		sname = "类块设置";
	}
	
	public FatherClass(){
		this.name = "构造函数";
		System.out.println("执行"+ FatherClass.class.getName() + "--构造函数");
	}
	
	public FatherClass(int id, String name){
		this.id = id;
		this.name = name;
		System.out.println("执行"+ FatherClass.class.getName() + "--有参构造函数");
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public static int getSi() {
		return si;
	}

	public static void setSi(int si) {
		FatherClass.si = si;
	}

	public static String getSname() {
		return sname;
	}

	public static void setSname(String sname) {
		FatherClass.sname = sname;
	}

	@Override
	public String toString() {
		return "FatherClass [id=" + id + ", name=" + name + "]";
	}
	
	
}
