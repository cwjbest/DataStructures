package com.cwj.binarytree;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cwj on 18-9-5.
 * 129. 求根到叶子节点数字之和
 给定一个二叉树，它的每个结点都存放一个 0-9 的数字，每条从根到叶子节点的路径都代表一个数字。
 例如，从根到叶子节点路径 1->2->3 代表数字 123。
 计算从根到叶子节点生成的所有数字之和。
 说明: 叶子节点是指没有子节点的节点。
 示例 1:

 输入: [1,2,3]
 1
 / \
 2   3
 输出: 25
 解释:
 从根到叶子节点路径 1->2 代表数字 12.
 从根到叶子节点路径 1->3 代表数字 13.
 因此，数字总和 = 12 + 13 = 25.
 示例 2:

 输入: [4,9,0,5,1]
 4
 / \
 9   0
 / \
 5   1
 输出: 1026
 解释:
 从根到叶子节点路径 4->9->5 代表数字 495.
 从根到叶子节点路径 4->9->1 代表数字 491.
 从根到叶子节点路径 4->0 代表数字 40.
 因此，数字总和 = 495 + 491 + 40 = 1026.
 */
public class SumNumbers_129 {
    public static int sumNumbers(TreeNode root) {
        List<String> res = new ArrayList<>();
        String s = "";
        int sum = 0;
        dfs(root, s, res);
        for (String str : res) {
            sum += Integer.parseInt(str);
        }
        return sum;
    }

    public static void dfs(TreeNode root, String s, List<String> res){
        if (root == null) return;
        s += "" + root.val;
        if (root.left == null && root.right == null)
            res.add(s);
        dfs(root.left, s, res);
        dfs(root.right, s, res);
    }

    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(0);
        TreeNode node2 = new TreeNode(9);
        TreeNode node3 = new TreeNode(0);
        TreeNode node4 = new TreeNode(5);
        TreeNode node5 = new TreeNode(1);

        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        node2.right = node5;

        sumNumbers(node1);
    }
}
