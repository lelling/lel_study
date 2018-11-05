package com.basis.tree.qry;

import java.util.Stack;

import com.basis.tree.node.BinaryNode;

/**
 * 非递归
 */
public class NonRecursion {
	
	/**
	 * 先序
	 * statck先进后出
	 */
	public static void preOrderNon(BinaryNode node){
		if (node == null) {
			return;
		}
		Stack<BinaryNode> stack = new Stack<>();
		stack.push(node);
		while (!stack.isEmpty()) {
			BinaryNode hNode = stack.pop();
			System.out.print(hNode.getValue() + ",");
			
			if (null != hNode.getRight()) {
				stack.push(hNode.getRight());
			}
			if (null != hNode.getLeft()) {
				stack.push(hNode.getLeft());
			}
		}
		System.out.println();
	}
	
	/**
	 * 中序
	 * statck先进后出
	 */
	public static void inOrderNon(BinaryNode node){
		if (node == null) {
			return;
		}
		Stack<BinaryNode> stack = new Stack<>();
		while (!stack.isEmpty() || node != null) {
			if (node != null) {
				stack.push(node);
				node = node.getLeft();
			}else{
				node = stack.pop();
				System.out.print(node.getValue() + ",");
				node = node.getRight();
			}
		}
	}
	
	/**
	 * 后序
	 * statck先进后出
	 */
	public static void postOrderNon(BinaryNode node){
		if (node == null) {
			return;
		}
		Stack<BinaryNode> s1 = new Stack<>();
		s1.push(node);
		Stack<BinaryNode> s2 = new Stack<>();
		while (!s1.isEmpty()) {
			node = s1.pop();
			s2.push(node);
			if (node.getLeft() != null) {
				s1.push(node.getLeft());
			}
			if (node.getRight() != null) {
				s1.push(node.getRight());
			}
		}
		while(!s2.isEmpty()){
			System.out.print(s2.pop().getValue() + ",");
		}
	}
	
	/**
	 * 后序
	 * statck先进后出
	 */
	public static void postOrderNon2(BinaryNode node){
		if (node == null) {
			return;
		}
		Stack<BinaryNode> stack = new Stack<>();
		stack.push(node);
		while (!stack.isEmpty()) {
			node = stack.pop();
			System.out.println(node.getValue());
			if (node.getLeft() != null) {
				stack.push(node.getLeft());
			}
			if (node.getRight() != null) {
				stack.push(node.getRight());
			}
		}
	}
	
	public static void main(String[] args){
		BinaryNode node = BinaryNode.initTree();
//		preOrderNon(node);
//		
//		inOrderNon(node);
		
		postOrderNon2(node);
	}
}
