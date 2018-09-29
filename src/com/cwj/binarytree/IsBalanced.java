package com.cwj.binarytree;

/**
 * Created by cwj on 18-8-6.
 * 110. 平衡二叉树
 * 给定一个二叉树，判断它是否是高度平衡的二叉树。
 * <p>
 * 本题中，一棵高度平衡二叉树定义为：
 * <p>
 * 一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过1。
 * <p>
 * 示例 1:
 * <p>
 * 给定二叉树 [3,9,20,null,null,15,7]
 * <p>
 * 3
 * / \
 * 9  20
 * /  \
 * 15   7
 * 返回 true 。
 * <p>
 * 示例 2:
 * <p>
 * 给定二叉树 [1,2,2,3,3,null,null,4,4]
 * <p>
 * 1
 * / \
 * 2   2
 * / \
 * 3   3
 * / \
 * 4   4
 * 返回 false 。
 */
public class IsBalanced {
    public boolean isBalanced(TreeNode root) {
        if (root == null) return true;
        int ld = depth(root.left);
        int rd = depth(root.right);
        if (Math.abs(ld - rd) > 1) {
            return false;
        } else {
            return isBalanced(root.left) && isBalanced(root.right);
        }
    }

    private int depth(TreeNode node) {
        int ld, rd;
        if (node == null) return 0;
        else {
            ld = depth(node.left);
            rd = depth(node.right);
        }
        return (ld > rd ? ld : rd) + 1;
    }
}
