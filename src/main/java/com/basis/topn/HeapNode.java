package com.basis.topn;

import com.basis.sort.SortHelper;
import com.lel.utils.json.GsonUtils;

public class HeapNode {
	
	private int parentIdx(int idx){
		return (idx - 1 ) / 2;
	}
	
	private int leftIdx(int idx){
		return 2 * idx + 1;
	}
	
	private int rightIdx(int idx){
		return 2 * idx + 2;
	}
	
	private void buildHeap(int n, int[] arr){
		for(int i = 1; i < n; i++){
			int t= i;
			while(t != 0 && arr[parentIdx(t)] > arr[t]){
				SortHelper.swap(arr, parentIdx(t), t);
				t = parentIdx(t);
			}
		}
	}
	
	private void adjust(int i, int n, int[] arr){
		if (arr[i] <= arr[0]) {
			return;
		}
		SortHelper.swap(arr, 0, i);
		int t= 0;
		// 子节点 < 父节点
		while((leftIdx(t) < n && arr[t] > arr[leftIdx(t)])
				|| (rightIdx(t) < n && arr[t] > arr[rightIdx(t)])	
				){
			if (rightIdx(t) < n && arr[rightIdx(t)] < arr[leftIdx(t)]) {
				// 右节点 < 左节点
				SortHelper.swap(arr, t, rightIdx(t));
				t = rightIdx(t);
			}else{
				SortHelper.swap(arr, t, leftIdx(t));
				t = leftIdx(t);
			}
		}
	}
	
	/**
	 * 取TOPN构建小顶堆
	 */
	public void topN(int n, int[]arr) {
		buildHeap(n, arr);
		for(int i=n; i < arr.length; i++){
			adjust(i, n, arr);
		}
	}
	
	public static void main(String[]args){
		HeapNode hd = new HeapNode();
		int[] arr = {2, 5, 1, 3, 9, 7, 8, 4, 3, 5 , 6, 10, 5};//SortHelper.createArr(20, 1, 1000);
		System.out.println(GsonUtils.toJson(arr));
		hd.topN(5, arr);
		System.out.println(GsonUtils.toJson(arr));
	}
}
