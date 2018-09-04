package com.basis.oom;

public class HeapOom {
	
	static final int SIZE = 2*1024*1024;
	
	/**
	 * 堆内存不足<br>
	 * 产生原因：1-当前剩余内存不足已new新的大对象（如数组）2-存在内存泄露<br>
	 * 此处为1 设置jvm参数 -Xmx12m 内存不足; 增大后正常<br>
	 */
	public static void createArr(){
		System.out.println("开始创建长度为" + SIZE + "的整型数组");
		int[] i = new int[SIZE];
		System.out.println( "创建数组成功："+ i);
	}
	
	
	public static void main(String[] args) {
//		ArrayList<Inner> list = new ArrayList<>();
//		int i = 1;
//		while (true) {
//			Inner inner = new Inner(i , i);
//			i++;
//			list.add(inner);
//		}
		
		createArr();
	}
}
