package com.basis.sort;

public class Sort {
	
	/**
	 * 选择
	 */
	public static void select(int[] arr){
		if (null == arr) {
			return;
		}
		int length = arr.length;
		for(int i = 0; i < length - 1; i++){
			int k = i;
			for(int j = i + 1; j < length; j++){
				if (arr[j] > arr[k]) { // k存放当前最大元素; 升序或降序由此条代码控制
					k = j;
				}
			}
			if (k != i) {
				SortHelper.swap(arr, i, k);
			}
		}
	}
	
	/**
	 * 冒泡
	 */
	public static void bubble(int[] arr){
		if (null == arr) {
			return;
		}
		int length = arr.length;
		for(int i = 0; i < length; i++){
			for(int j= 0; j < length - 1 - i; j++){
				if (arr[j] < arr[j+1]) {
					SortHelper.swap(arr, j, j+1);
				}
			}
		}
	}
	
	/**
	 * 直插
	 */
	public static void insert(int[] arr){
		if (null == arr) {
			return;
		}
		int length = arr.length;
		for(int i = 1; i < length; i++){
			int j = i;
			while(j > 0 && arr[j] < arr[j-1]){
				SortHelper.swap(arr, j, j-1);
				j--;
			}
		}
	}
	
	/**
	 * 归并
	 */
	public static int[] merge(int[] arr, int s, int e){
		if (null == arr) {
			return arr;
		}
		if (e - s < 1) {
			return arr;
		}
		return mergeStep2(merge(arr, s, (e-s) / 2 ), merge(arr, (e-s) / 2 + 1, e));
	}
	
	private static int[] mergeStep2(int[] a, int[] b){
		int[] s = new int[a.length + b.length];
		int i= 0,j= 0; 
		int k = 0;
		while(i < a.length && j < b.length){
			if (a[i] > b[j]) {
				s[k++] = a[i++];
			}else{
				s[k++] = b[j++];
			}
		}
		while(i < a.length){
			s[k++] = a[i++];
		}
		while(i < b.length){
			s[k++] = b[i++];
		}
		return s;
	}
}