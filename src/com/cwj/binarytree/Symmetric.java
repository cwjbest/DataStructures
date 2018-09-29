package com.cwj.binarytree;

/**
 * Created by cwj on 18-8-2.
 * 给定一个二叉树，检查它是否是镜像对称的。

 例如，二叉树 [1,2,2,3,4,4,3] 是对称的。

 1
 / \
 2   2
 / \ / \
 3  4 4  3
 但是下面这个 [1,2,2,null,3,null,3] 则不是镜像对称的:

 1
 / \
 2   2
 \   \
 3    3
 */
public class Symmetric {
    public boolean compare(TreeNode p, TreeNode q){
        if (p == null && q == null) return true;
        if (p == null || q == null) return false;
        if (p.val != q.val) return false;
        return compare(p.left, q.right) && compare(p.right, q.left);
    }

    public boolean isSymmetric(TreeNode root){
        return compare(root.left, root.right);
    }

    /**
     * 226. 翻转二叉树
     翻转一棵二叉树。

     示例：

     输入：

     4
     /   \
     2     7
     / \   / \
     1   3 6   9
     输出：

     4
     /   \
     7     2
     / \   / \
     9   6 3   1
     备注:
     这个问题是受到 Max Howell 的 原问题 启发的 ：

     谷歌：我们90％的工程师使用您编写的软件(Homebrew)，但是您却无法在面试时在白板上写出翻转二叉树这道题，这太糟糕了。
     *
     */
    public TreeNode invertTree(TreeNode root) {
        if(root == null || root.left == null && root.right == null) return root;

        invertTree(root.left);
        invertTree(root.right);

        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
        return root;
    }
}
