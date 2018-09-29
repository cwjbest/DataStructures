package com.cwj.binarytree;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by cwj on 18-8-3.
 107. 二叉树的层次遍历 II
 给定一个二叉树，返回其节点值自底向上的层次遍历。 （即按从叶子节点所在层到根节点所在的层，逐层从左向右遍历）

 例如：
 给定二叉树 [3,9,20,null,null,15,7],

 3
 / \
 9  20
 /  \
 15   7
 返回其自底向上的层次遍历为：

 [
 [15,7],
 [9,20],
 [3]
 ]
 */
public class levelOrderBottom {
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        Deque<List<Integer>> results = new LinkedList<>();
        if (root == null) return null;

        Deque<TreeNode> cur = new LinkedList<>();
        Deque<TreeNode> next = new LinkedList<>();
        Deque<TreeNode> tmp;

        TreeNode node;

        cur.add(root);
        while (!cur.isEmpty()){
            List<Integer> layout = new ArrayList<>();
            while (!cur.isEmpty()){
                node = cur.removeFirst();
                layout.add(node.val);
                if (node.left != null) next.add(node.left);
                if (node.right != null) next.add(node.right);
            }

            tmp = cur;
            cur = next;
            next = tmp;

            results.push(layout);
        }

        List<List<Integer>> lists = new ArrayList<>();
        while (results.peek() != null){
            lists.add(results.pop());
        }
        return lists;
    }
}
