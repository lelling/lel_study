package com.basis.tree.node;

/**
 * 二叉树节点
 *
 */
public class BinaryNode {
	
	private int value;
	
	private BinaryNode left;
	
	private BinaryNode right;

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public BinaryNode getLeft() {
		return left;
	}

	public void setLeft(BinaryNode left) {
		this.left = left;
	}

	public BinaryNode getRight() {
		return right;
	}

	public void setRight(BinaryNode right) {
		this.right = right;
	}
	
	public static BinaryNode initTree(){
		int i=1;
		BinaryNode n1 = new BinaryNode();
		BinaryNode n2 = new BinaryNode();
		BinaryNode n3 = new BinaryNode();
		BinaryNode n4 = new BinaryNode();
		BinaryNode n5 = new BinaryNode();
		BinaryNode n6 = new BinaryNode();
		BinaryNode n7 = new BinaryNode();
		n1.setValue(i++);
		n2.setValue(i++);
		n3.setValue(i++);
		n4.setValue(i++);
		n5.setValue(i++);
		n6.setValue(i++);
		n7.setValue(i++);
		n1.setLeft(n2);
		n1.setRight(n3);
		n2.setLeft(n4);
		n2.setRight(n5);
		n3.setLeft(n6);
		n3.setRight(n7);
		return n1;
	}
}
