package com.cwj.binarytree;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by cwj on 18-8-3.
 * 102. 二叉树的层次遍历
 * 题目描述提示帮助提交记录社区讨论阅读解答
 * 给定一个二叉树，返回其按层次遍历的节点值。 （即逐层地，从左到右访问所有节点）。
 * <p>
 * 例如:
 * 给定二叉树: [3,9,20,null,null,15,7],
 * <p>
 * 3
 * / \
 * 9  20
 * /  \
 * 15   7
 * 返回其层次遍历结果：
 * <p>
 * [
 * [3],
 * [9,20],
 * [15,7]
 * ]
 * <p>
 * 使用两队列，一个保存当前处理的层，一个保存下一次要处理的层。直到每一层都处理完。
 */
public class levelOrder {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> results = new ArrayList<>();//总的返回列表
        if (root == null) return results;

        Deque<TreeNode> cur = new LinkedList<>();//当前层
        Deque<TreeNode> next = new LinkedList<>();//下一层
        Deque<TreeNode> tmp;

        TreeNode node;
        cur.add(root);//根节点先入队
        while (!cur.isEmpty()) {//当前队列不空
            List<Integer> layout = new LinkedList<>();//存储当前层的值
            while (!cur.isEmpty()) {
                node = cur.removeFirst();//队头出队
                layout.add(node.val);
                if (node.left != null) next.add(node.left);//左子树进next队
                if (node.right != null) next.add(node.right);//右子树进next队
            }

            //当前队以空，表示当前层已全部出队，然后让next为cur，next为空继续遍历
            tmp = cur;
            cur = next;
            next = tmp;

            results.add(layout);//将当前层加入
        }
        return results;
    }

    /**
     *
     * 递归版本
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrderByRe(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();//总的返回列表
        set(root, 0, res);
        return res;
    }
    private static void set(TreeNode root, int level, List<List<Integer>> res){
        if (root == null) return;
        if (res.size() == level)
            res.add(new ArrayList<>());
        res.get(level).add(root.val);
        set(root.left, level+1, res);
        set(root.right, level+1, res);
    }

    /**
     * 429. N叉树的层序遍历
     * 给定一个N叉树，返回其节点值的层序遍历。 (即从左到右，逐层遍历)。
     * 1
     * / \  \
     * 3   2  4
     * / \
     * 5  6
     * <p>
     * 返回其层序遍历:
     * [
     * [1],
     * [3,2,4],
     * [5,6]
     * ]
     * 说明:
     * 树的深度不会超过 1000。
     * 树的节点总数不会超过 5000。
     */
    public List<List<Integer>> levelOrder(NTreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null)
            return res;
        Deque<NTreeNode> cur = new LinkedList<>();
        Deque<NTreeNode> next = new LinkedList<>();
        Deque<NTreeNode> temp;
        NTreeNode node;
        cur.addLast(root);
        while (!cur.isEmpty()) {
            List<Integer> layout = new ArrayList<>();
            while (!cur.isEmpty()) {
                node = cur.removeFirst();
                layout.add(node.val);
                if (!node.children.isEmpty()) {
                    for (NTreeNode childNode : node.children) {
                        next.addLast(childNode);
                    }
                }
            }
            temp = next;
            next = cur;
            cur = temp;
            res.add(layout);
        }
        return res;
    }

    public static List<List<Integer>> levelOrderByRe(NTreeNode root){
        List<List<Integer>> res = new ArrayList<>();
        set(root, 0, res);
        return res;
    }

    private static void set(NTreeNode root, int level, List<List<Integer>> res){
        if (root == null) return;
        if (res.size() == level)
            res.add(new ArrayList<>());
        res.get(level).add(root.val);
        for (NTreeNode node : root.children) {
            set(node, level+1, res);
        }
    }

    public static void main(String[] args) {
        List<NTreeNode> list1 = new ArrayList<>();
        List<NTreeNode> list3 = new ArrayList<>();

        NTreeNode node1 = new NTreeNode(1, list1);
        NTreeNode node3 = new NTreeNode(3, list3);
        NTreeNode node2 = new NTreeNode(2, new ArrayList<>());
        NTreeNode node4 = new NTreeNode(4, new ArrayList<>());
        NTreeNode node5 = new NTreeNode(5, new ArrayList<>());
        NTreeNode node6 = new NTreeNode(6, new ArrayList<>());

        list1.add(node3);
        list1.add(node2);
        list1.add(node4);
        list3.add(node5);
        list3.add(node6);

        node1.children = list1;
        node3.children = list3;

        List<List<Integer>> res = new ArrayList<>();
        res = levelOrderByRe(node1);
        System.out.println(res.toString());

    }

}
