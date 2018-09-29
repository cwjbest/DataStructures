package com.cwj.binarytree;

import java.util.*;

/**
 * Created by cwj on 18-8-31.
 * 112. 路径总和
 * 给定一个二叉树和一个目标和，判断该树中是否存在根节点到叶子节点的路径，这条路径上所有节点值相加等于目标和。
 * <p>
 * 说明: 叶子节点是指没有子节点的节点。
 * <p>
 * 示例:
 * 给定如下二叉树，以及目标和 sum = 22，
 * <p>
 * 5
 * / \
 * 4   8
 * /   /  \
 * 11  13    4
 * /  \        \
 * 7     2       1
 * 返回 true, 因为存在目标和为 22 的根节点到叶子节点的路径 5->4->11->2。
 */
public class HasPathSum {
    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) return false;
        if (root.left == null && root.right == null && sum == 0) return true;
        return hasPathSum(root.left, sum - root.val) || hasPathSum(root.right, sum - root.val);
    }

    /**
     * 113. 路径总和 II
     * 题目描述提示帮助提交记录社区讨论阅读解答
     * 给定一个二叉树和一个目标和，找到所有从根节点到叶子节点路径总和等于给定目标和的路径。
     * <p>
     * 说明: 叶子节点是指没有子节点的节点。
     * <p>
     * 示例:
     * 给定如下二叉树，以及目标和 sum = 22，
     * <p>
     * 5
     * / \
     * 4   8
     * /   / \
     * 11  13  4
     * /  \    / \
     * 7    2  5   1
     * 返回:
     * <p>
     * [
     * [5,4,11,2],
     * [5,8,4,5]
     * ]
     */
    public List<List<Integer>> pathSum2(TreeNode root, int sum) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        List<Integer> list = new ArrayList<>();
        dfs2(root, sum, res, list);
        return res;
    }

    public void dfs2(TreeNode root, int sum, List<List<Integer>> res, List<Integer> list) {
        if (root == null) return;
        list.add(root.val);
        if (root.left == null && root.right == null) {
            if (root.val == sum)
                res.add(new ArrayList<>(list));//list是引用，如果直接将list加进去，之后list的改变会影响到res中的list
            //那String虽然也是引用，但String是不可变的，所以每次改变会创建一个新的，故而不会影响旧的；
//            list.remove(Integer.valueOf(root.val));list这样取元素，如果有重复值就会返回第一个出现的，最后顺序会乱
            list.remove(list.size() - 1);
            return;
        }

        dfs2(root.left, sum - root.val, res, list);
        dfs2(root.right, sum - root.val, res, list);
        list.remove(list.size() - 1);
    }

    /**
     * 437. 路径总和 III
     * 题目描述提示帮助提交记录社区讨论阅读解答
     * 给定一个二叉树，它的每个结点都存放着一个整数值。
     * <p>
     * 找出路径和等于给定数值的路径总数。
     * <p>
     * 路径不需要从根节点开始，也不需要在叶子节点结束，但是路径方向必须是向下的（只能从父节点到子节点）。
     * <p>
     * 二叉树不超过1000个节点，且节点数值范围是 [-1000000,1000000] 的整数。
     * <p>
     * 示例：
     * <p>
     * root = [10,5,-3,3,2,null,11,3,-2,null,1], sum = 8
     * <p>
     * 10
     * /  \
     * 5   -3
     * / \    \
     * 3   2   11
     * / \   \
     * 3  -2   1
     * <p>
     * 返回 3。和等于 8 的路径有:
     * <p>
     * 1.  5 -> 3
     * 2.  5 -> 2 -> 1
     * 3.  -3 -> 11
     * <p>
     * bfs+dfs
     */
    public int pathSum3(TreeNode root, int sum) {
        int pathSum = 0;
        if (root == null) return 0;
        Deque<TreeNode> queue = new LinkedList<>();
        queue.addLast(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.removeFirst();
            pathSum += dfs3(node, sum);
            if (node.left != null)
                queue.addLast(node.left);
            if (node.right != null)
                queue.addLast(node.right);
        }
        return pathSum;
    }

    public int dfs3(TreeNode root, int sum) {
        int pathSum = 0;
        if (root == null) return pathSum;
        if (root.val == sum)
            pathSum++;
        pathSum += dfs3(root.left, sum - root.val);
        pathSum += dfs3(root.right, sum - root.val);
        return pathSum;
    }

    /**
     * 257. 二叉树的所有路径
     题目描述提示帮助提交记录社区讨论阅读解答
     给定一个二叉树，返回所有从根节点到叶子节点的路径。

     说明: 叶子节点是指没有子节点的节点。

     示例:

     输入:

     1
     /   \
     2     3
     \
     5

     输出: ["1->2->5", "1->3"]

     解释: 所有根节点到叶子节点的路径为: 1->2->5, 1->3
     */
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> res = new ArrayList<>();
        if(root == null) return res;
        String str = "";
        re(root, str, res);
        return res;
    }

    public void re(TreeNode root, String str, List<String> res){
        if(root == null) return;
        str += root.val + " ";
        if(root.left == null && root.right == null){
            res.add(str.trim().replace(" ", "->"));
        }
        re(root.left, str, res);
        re(root.right, str, res);
    }

    public static void main(String[] args) {
    }

}
