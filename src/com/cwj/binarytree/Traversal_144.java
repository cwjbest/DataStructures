package com.cwj.binarytree;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by cwj on 18-9-4.
 *
 */
public class Traversal_144 {
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        LinkedList<TreeNode> stack = new LinkedList<>();
        TreeNode cur = root;
        TreeNode temp;
        while (cur != null || !stack.isEmpty()) {
            while (cur != null) {
                res.add(cur.val);
                stack.push(cur);
                cur = cur.left;
            }

            if (!stack.isEmpty()) {
                temp = stack.pop();
                cur = temp.right;
            }
        }
        return res;
    }

    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        LinkedList<TreeNode> stack = new LinkedList<>();
        TreeNode cur = root;
        TreeNode temp;
        while (cur != null || !stack.isEmpty()) {
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }
            if (!stack.isEmpty()) {
                temp = stack.pop();
                res.add(temp.val);
                cur = temp.right;
            }
        }
        return res;
    }

    /**
     *
     * 后续有个问题就是节点可能会两次位于栈顶，第一次是左子树完了要换到右子树，第二次是右子树完了要把根弹出来
     * 可以修改二叉树的定义，加一个标记为来表示是否第一次位于栈顶
     * 不能修改定义的话，可以两次压栈
     * stack.pop() == stack.peek()时，说明第一次位于栈顶
     * @param root
     * @return
     */
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        LinkedList<TreeNode> stack = new LinkedList<>();
        TreeNode cur = root;
        TreeNode temp;
        TreeNode p;
        while (cur != null || !stack.isEmpty()) {
            while (cur != null) {
                stack.push(cur);
                stack.push(cur);
                cur = cur.left;
            }
            if (!stack.isEmpty()) {
                p = stack.pop();
                if (p == stack.peek()){
                    temp = stack.peek();
                    cur = temp.right;
                }else {
                    temp = p;
                    res.add(temp.val);
                }
            }
        }
        return res;
    }

    /**
     *
     * 这种方法更好，记住最后一个访问的节点，然后看这个节点是从当前栈顶节点的左孩子还是右孩子返回的
     * @param root
     * @return
     */
    public List<Integer> postorderTraversal2(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        LinkedList<TreeNode> stack = new LinkedList<>();
        TreeNode cur = root;
        TreeNode temp;
        TreeNode r = null;
        while (cur != null || !stack.isEmpty()) {
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }
            if (!stack.isEmpty()) {
                TreeNode right = stack.peek().right;
                if (right != null && r != right){
                    temp = stack.peek();
                    cur = temp.right;
                }else {
                    temp = stack.pop();
                    res.add(temp.val);
                    r = temp;
                }
            }
        }
        return res;
    }
}
