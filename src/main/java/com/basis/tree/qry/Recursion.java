package com.basis.tree.qry;

import com.basis.tree.node.BinaryNode;

/**
 * 递归输出
 */
public class Recursion {
	
	/**
	 * 先序
	 */
	public static void preOrder(BinaryNode node){
		if (node == null) {
			return;
		}
		System.out.print(node.getValue() + ",");
		preOrder(node.getLeft());
		preOrder(node.getRight());
	}
	/**
	 * 中序
	 */
	public static void inOrder(BinaryNode node){
		if (node == null) {
			return;
		}
		inOrder(node.getLeft());
		System.out.print(node.getValue() + ",");
		inOrder(node.getRight());
	}
	/**
	 * 后序
	 */
	public static void postOrder(BinaryNode node){
		if (node == null) {
			return;
		}
		postOrder(node.getLeft());
		postOrder(node.getRight());
		System.out.print(node.getValue() + ",");
	}
	
	public static void main(String[] args){
		BinaryNode node = BinaryNode.initTree();
		preOrder(node);
		System.out.println();
		inOrder(node);
		System.out.println();
		postOrder(node);
		System.out.println();
	}
}
