package com.cwj.binarytree;

/**
 * Created by cwj on 18-8-2.
 * 100. Same Tree
 Given two binary trees, write a function to check if they are equal or not.
 给定两个二叉树，写一个函数来检查它们是否相等。

 Two binary trees are considered equal if they are structurally identical and the nodes have the same value.
 如果两个二叉树在结构上相同，并且节点具有相同的值，则它们被认为是相等的。

 思路：
 递归算法
 1.判断两个二叉树是否为空，若均为空则返回true，若只有一个为空则返回false
 2.两个二叉树均不为空。
 如果根节点具有相同的值并且根的左子树是相同的（递归）和根的右子树是相同的（递归）返回true，否则返回false。
 */
public class SameTree {
    public boolean isSameTree(TreeNode p, TreeNode q){
        if(p == null || q == null){
            if (p == q){
                return true;
            }else {
                return false;
            }
        }

        return (p.val == q.val) && isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }
}
