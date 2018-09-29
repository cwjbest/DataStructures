package com.cwj.binarytree;

/**
 * Created by cwj on 18-3-13.
 * 用递归节点实现法/左右链表示法 表示一个二叉树节点
 */
public class BTreeNode {
    private int data;
    private BTreeNode leftChild;
    private BTreeNode rightChild;

    public BTreeNode(){}

    public BTreeNode(int data, BTreeNode leftChild, BTreeNode rightChild){
        this.data = data;
        this.leftChild = leftChild;
        this.rightChild = rightChild;
    }

    public int getData() {
        return data;
    }

    public BTreeNode getLeftChild() {
        return leftChild;
    }

    public BTreeNode getRightChild() {
        return rightChild;
    }

    public void setData(int data) {
        this.data = data;
    }

    public void setLeftChild(BTreeNode leftChild) {
        this.leftChild = leftChild;
    }

    public void setRightChild(BTreeNode rightChild) {
        this.rightChild = rightChild;
    }
}


