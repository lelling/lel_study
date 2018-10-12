package com.basis.bit;

/**
 * 计算n的二进制位中1的个数
 */
public class CountBitOne {
	
	public static void show(int n, int count){
		System.out.println(n + "含1的个数：" + count);
	}
	
	/**
	 * A、位移法<br>
	 * 思路：n为int类型，为32位；每次取n的最低位判断是否为1，固定位移32次可计算1的个数<br>
	 * 分析：无论n的二进制中含多少1，都需要32次，比较耗时<br>
	 */
	public static int countByMoveBit(int n){
		int count = 0;
		while(n > 0){
			if ((n & 1) == 1) {
				count++;
			}
			n >>= 1;
		}
		return count;
	}
	
	/**
	 * B、减1求与法<br>
	 * 思路：如何消除最低位的1；n->(n-1),n末位1变为0，n末位1之后的位为1，末位1之前的高位相同；即n&（n-1）正好消除末位1；
	 *   能消除几次就有多少个1
	 * 扩展：可使用n&(n-1)来判断n是否为2的x次幂（2的x次幂只有一个1）
	 * 分析：相对于A减少了循环次数，但最坏情况下同A
	 */
	public static int countByAndSub(int n){
		int count = 0;
		while(n > 0){
			count++;
			n &= (n-1);
		}
		return count;
	}
	
	/**
	 * C、查表法
	 * 思路：一旦n值确定，n含多少位1也是确定的，可提前计算好，建一个大数组result[n] = i;
	 * 分析：时间复杂度为O(1),但需要很大内存；
	 *   n为32位，则i<=32需要5bit存储，数组长度2^32个；需要内存2^32*5bit = 2.5GB
	 * 
	 */
	public static int countByFullTable(int n){
		// result占用空间过大，不予给出代码
		return countByHalfTable(n);
	}
	
	/**
	 * 二次查表
	 * @param n
	 * @return
	 */
	private static int countByHalfTable(int n){
		int low16 = n & 0XFFFF;
		int high16 = (n>>16) & 0XFFFF;
		return countByQuarterTable(low16) + countByQuarterTable(high16);
	}
	
	/**
	 * 四次查表
	 * @param n
	 * @return
	 */
	private static int countByQuarterTable(int n){
		int low8 = n & 0XFF;
		int high8 = (n>>8) & 0XFF;
		return countByEighthTable(low8) + countByEighthTable(high8);
	}
	
	private static int countByEighthTable(int n){
		int low4 = n & 0XF;
		int high4 = (n>>4) & 0XF;
		int[] arr = new int[16];
		for(int i = 0; i < 16; i++){
			arr[i] = countByAndSub(i);
		}
		return arr[low4] + arr[high4];
	}
	
	/**
	 * 位移+查表
	 */
	public static int countByBitAndTable(int n){
		int[] arr = new int[16];
		for(int i = 0; i < 16; i++){
			arr[i] = countByAndSub(i);
		}
		return countByMoveBitAndTable(n, arr);
	}
	
	private static int countByMoveBitAndTable(int n, int[] arr){
		if (n < 16) {
			return arr[n];
		}
		return countByMoveBitAndTable(n >> 4, arr) + countByMoveBitAndTable(n & 0xF, arr);
	}
	
	public static void main(String[] args){
//		show(10, countByAndSub(10));
//		show(58585858, countByAndSub(58585858));
//		show(10, countByFullTable(10));
//		show(58585858, countByFullTable(58585858));
		
		show(58585858, countByBitAndTable(58585858));
	}
}
