package com.basis.topn;

import java.util.Random;

import com.lel.utils.json.GsonUtils;

public class TopN {

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
	
	public static int[] getTopNByInsertSort(int topN, int[] arr){
		for(int i =0; i < arr.length; i++){
			if (i < topN) {
				// 前topN做直插排序
				int k = i;
				while(k > 0 && arr[k] > arr[k-1]){
					swap(arr, k, k-1);
					k--;
				}
			}else{
				int d = arr[i];
				int k = i;
				for(int j = topN - 1; j >= 0; j--){
					if (d > arr[j]) {
						swap(arr, j, k);
						k = j;
					}else{
						break;
					}
				}
			}
		}
		int[] top = new int[topN];
		System.arraycopy(arr, 0, top, 0, topN);
		return top;
	}
	
	
	public static void main(String[] args) {
		int[] arr = createArr(20, 10, 1000);
		System.out.println(GsonUtils.toJson(arr));
		int[] topN = getTopNByInsertSort(5, arr);
		System.out.println(GsonUtils.toJson(topN));
	}

}
