package com.basis.sort;

import java.util.Random;

import com.lel.utils.json.GsonUtils;

public final class SortHelper {
	
	/**
	 * 生成数组对象
	 */
	public static int[] createArr(int length, int min, int max) {
		if (length < 0) {
			throw new RuntimeException("无效数组长度");
		}
		if (min > max) {
			max += min;
			min = max - min;
			max = max - min;
		}
		int range = max - min + 1;
		int[] arr = new int[length];
		for (int i = 0; i < length; i++) {
			int nextInt = new Random().nextInt(range);
			arr[i] = nextInt + min;
		}
		return arr;
	}
	
	/**
	 * 交换元素
	 * @param arr
	 * @param a
	 * @param b
	 */
	public static void swap(int[] arr, int a, int b) {
		if (null == arr) {
			throw new RuntimeException("数组为空");
		}
		if (a < 0 || b > arr.length) {
			throw new RuntimeException("待交换元素下标越界");
		}
		arr[a] += arr[b];
		arr[b] = arr[a] - arr[b];
		arr[a] = arr[a]-arr[b];
	}
	
	public static void main(String[] args){
		int[] arr = createArr(10, 1, 10);
		System.out.println(GsonUtils.toJson(arr));
//		swap(arr, 2, 4);
//		Sort.select(arr);
//		System.out.println(GsonUtils.toJson(arr));
		
//		Sort.bubble(arr);
//		System.out.println(GsonUtils.toJson(arr));
		
//		Sort.insert(arr);
//		System.out.println(GsonUtils.toJson(arr));
		
		Sort.merge(arr, 0, arr.length);
		System.out.println(GsonUtils.toJson(arr));
	}
}
