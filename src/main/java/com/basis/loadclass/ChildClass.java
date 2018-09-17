package com.basis.loadclass;

/**
 * 先父后子；先静态后成员；先块后构造函数
 * 在创建子类时
 *   1-先执行父类的静态变量赋值，再执行父类的静态块；后执行子类的静态变量赋值，再执行子类的静态块；
 *   2-执行父类的成员变量赋值，再执行父类的类块，继续执行父类的构造方法；后执行子类的成员变量赋值，再执行子类的类块，最后执行子类的构造方法；
 * @author lel
 */
public class ChildClass extends FatherClass {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1183638734374392839L;
	
	private int cid = -1;
	private String cname;
	private static int scid = -1;
	private static String scname;
	
	static{
		scid = 1;
		System.out.println("执行" + ChildClass.class.getName() + "--static块");
	}
	{
		cid = 1;
		System.out.println("执行" + ChildClass.class.getName() + "--类块");
	}
	
	public ChildClass(){
//		super(2, "lel");
		this.cname = "ChildClass";
		System.out.println("执行" + ChildClass.class.getName() + "--构造函数");
	}
	
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public static int getScid() {
		return scid;
	}
	public static void setScid(int scid) {
		ChildClass.scid = scid;
	}
	public static String getScname() {
		return scname;
	}
	public static void setScname(String scname) {
		ChildClass.scname = scname;
	}
	
	@Override
	public String toString() {
		return "ChildClass [cid=" + cid + ", cname=" + cname + ", scname=" + scname + "]";
	}

	public static void main(String[] args){
		ChildClass childClass = new ChildClass();
		System.out.println(childClass.toString());
	}
}
