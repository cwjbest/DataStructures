package com.cwj.binarytree;

import com.cwj.linklist.ListNode;
import sun.reflect.generics.tree.Tree;

import java.util.*;

/**
 * Created by cwj on 18-8-3.
 * 给定一个二叉树，找出其最大深度。

 二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。

 说明: 叶子节点是指没有子节点的节点。

 示例：
 给定二叉树 [3,9,20,null,null,15,7]，

 3
 / \
 9  20
 /  \
 15   7
 返回它的最大深度 3 。
 */
public class maxDepth {

    public int findMaxDepth(TreeNode root) {

        int ld, rd;
        if (root == null) return 0;
        else {
            ld = findMaxDepth(root.left);
            rd = findMaxDepth(root.right);
        }
        return (ld > rd ? ld : rd) + 1;
    }

    /**
     *111. 二叉树的最小深度
     题目描述提示帮助提交记录社区讨论阅读解答
     给定一个二叉树，找出其最小深度。

     最小深度是从根节点到最近叶子节点的最短路径上的节点数量。

     说明: 叶子节点是指没有子节点的节点。

     示例:

     给定二叉树 [3,9,20,null,null,15,7],

     3
     / \
     9  20
     /  \
     15   7
     返回它的最小深度  2.
     *
     * 注意，与最大深度不同的是，如果只有左子树，返回左子树高，右子树也一样
     */
    public static int minDepth(TreeNode root){
        if (root == null) return 0;
        if (root.left == null)
            return minDepth(root.right) + 1;
        if (root.right == null)
            return minDepth(root.left) + 1;
        return Math.min(minDepth(root.left), minDepth(root.right)) + 1;
    }

    /**
     *
     * 513. 找树左下角的值
     题目描述提示帮助提交记录社区讨论阅读解答
     给定一个二叉树，在树的最后一行找到最左边的值。

     示例 1:

     输入:

     2
     / \
     1   3

     输出:
     1

     示例 2:

     输入:

     1
     / \
     2   3
     /   / \
     4   5   6
     /
     7

     输出:
     7
     注意: 您可以假设树（即给定的根节点）不为 NULL。
     /
     思路：层次遍历，将最后一个输出即可，但是效率不高
     */
    public static int findBottomLeftValue(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        Queue<TreeNode> cur = new LinkedList<>();
        Queue<TreeNode> next = new LinkedList<>();
        Queue<TreeNode> temp;
        TreeNode node;
        cur.add(root);
        while (!cur.isEmpty()){
            List<Integer> layout = new LinkedList<>();
            while (!cur.isEmpty()){
                node = cur.poll();
                layout.add(node.val);
                if (node.left != null)
                    next.add(node.left);
                if (node.right != null)
                    next.add(node.right);
            }
            temp = cur;
            cur = next;
            next = temp;
            res.add(layout);
        }

        return res.get(res.size()-1).get(0);
    }

    /**
     * 深度遍历，效果更好
     */
    public static int findBottomLeftValueByDfs(TreeNode root) {
        List<Integer> res = Arrays.asList(0, 0);
        dfs(root, res, 1);
        return res.get(1);
    }

    public static void dfs(TreeNode root, List<Integer> res, int depth){
        if (root == null)
            return;
        if (root.left == null && root.right == null){
            if (depth > res.get(0)){
                res.set(0, depth);
                res.set(1, root.val);
            }
        }
        dfs(root.left, res, depth+1);
        dfs(root.right, res, depth+1);
    }

    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(2);
        TreeNode node2 = new TreeNode(1);
        TreeNode node3 = new TreeNode(3);
        node1.left = node2;
        node1.right = node3;

        findBottomLeftValue(node1);
    }
}