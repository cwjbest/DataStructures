package com.cwj.binarytree;

/**
 * Created by cwj on 18-8-6.
 * 108. 将有序数组转换为二叉搜索树
 将一个按照升序排列的有序数组，转换为一棵高度平衡二叉搜索树。

 本题中，一个高度平衡二叉树是指一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1。

 示例:

 给定有序数组: [-10,-3,0,5,9],

 一个可能的答案是：[0,-3,9,-10,null,5]，它可以表示下面这个高度平衡二叉搜索树：

 0
 / \
 -3   9
 /   /
 -10  5

 数组有序，可知中间数字为二叉树的根，二分查找，递归
 */


public class SortedArrayToBST {
    public TreeNode sortedArrayToBST(int[] nums) {
        int end = nums.length;
        if (end <= 0) return null;
        return buildTree(nums, 0, end-1);
    }

    private TreeNode buildTree(int[] nums, int start, int end){
        if (start > end) return null;
        else{
            int mid = (start + end)/2;
            TreeNode root = new TreeNode(nums[mid]);
            root.left = buildTree(nums, start, mid-1);
            root.right = buildTree(nums, mid+1, end);
            return root;
        }
    }
}
