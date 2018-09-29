package com.cwj.binarytree;

import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by cwj on 18-3-13.
 * 二叉排序树
 */
public class BSTree {
    private BTreeNode root;

    public BSTree(BTreeNode root){
        this.root = root;
    }
    //查找
    //先比较它与根节点，相等就返回；或者根节点为空，说明树为空，也返回；
    //如果它比根节点小，就从根的左子树里进行递归查找；
    //如果它比根节点大，就从根的右子树里进行递归查找。
    public BTreeNode search(int data){
        return search(root, data);
    }

    private BTreeNode search(BTreeNode node, int data){
        //递归出口，遍历结束，或者找到了匹配节点
        if (node == null || node.getData() == data){
            return node;
        }

        if (data < node.getData()){
            return search(node.getLeftChild(), data);
        }else {
            return search(node.getRightChild(), data);
        }
    }

    //插入
    public void insert(int data){
        if (root == null){
            root = new BTreeNode();
            root.setData(data);
            return;
        }

        searchAndInsert(null, root, data);
    }

    private BTreeNode searchAndInsert(BTreeNode parent, BTreeNode node, int data){
        //递归出口，没有找到，直接新建，插入
        if (node == null){
            node = new BTreeNode();
            node.setData(data);
            if(parent != null){//绑定父子关系
                if (parent.getData() > data){
                    parent.setLeftChild(node);
                }else {
                    parent.setRightChild(node);
                }
            }
            return node;
        }

        if (node.getData() == data){
            return node;
        }else if (node.getData() > data){
            return searchAndInsert(node, node.getLeftChild(), data);
        }else {
            return searchAndInsert(node, node.getRightChild(), data);
        }
    }

    /**
     * 删除
     * 如果要删除的节点正好是叶子节点，直接删除就 Ok 了；
     * 如果要删除的节点还有子节点，就需要建立父节点和子节点的关系：
     * 如果只有左孩子或者右孩子，直接把这个孩子上移放到要删除的位置就好了；
     * 如果有两个孩子，就需要选一个合适的孩子节点作为新的根节点，该节点称为 继承节点。
     * 新节点要求要比所有左子树大，比所有右子树小，怎么选择呢？
     * 要比所有左子树的值大、右子树小，就从右子树里找最小的好了
     */

    /**
     * 在整个树中 查找指定数据节点的父亲节点
     *
     * @param data
     * @return
     */
    public BTreeNode searchParent(int data) {
        return searchParent(null, root, data);
    }

    /**
     * 在指定节点下 查找指定数据节点的父亲节点
     *
     * @param parent 当前比较节点的父节点
     * @param node   当前比较的节点
     * @param data   查找的数据
     * @return
     */
    public BTreeNode searchParent(BTreeNode parent, BTreeNode node, int data) {
        if (node == null) { //比较的节点为空返回空
            return null;
        }
        if (node.getData() == data) {    //找到了目标节点，返回父节点
            return parent;
        } else if (data < node.getData()) {   //数据比当前节点小，左子树中递归查找
            return searchParent(node, node.getLeftChild(), data);
        } else {
            return searchParent(node, node.getRightChild(), data);
        }
    }

    /**
     * 删除指定数据的节点
     *
     * @param data
     */
    public void delete(int data) {
        if (root == null || root.getData() == data) {  //根节点为空或者要删除的就是根节点，直接删掉
            root = null;
            return;
        }
        //在删除之前需要找到它的父亲
        BTreeNode parent = searchParent(data);
        if (parent == null) {        //如果父节点为空，说明这个树是空树，没法删
            return;
        }

        //接下来该找要删除的节点了
        BTreeNode deleteNode = search(parent, data);
        if (deleteNode == null) {    //树中找不到要删除的节点
            return;
        }
        //删除节点有 4 种情况
        //1.左右子树都为空，说明是叶子节点，直接删除
        if (deleteNode.getLeftChild() == null && deleteNode.getRightChild() == null) {
            //删除节点
            deleteNode = null;
            //重置父节点的孩子状态，告诉他你以后没有这个儿子了
            if (parent.getLeftChild() != null && parent.getLeftChild().getData() == data) {
                parent.setLeftChild(null);
            } else {
                parent.setRightChild(null);
            }
            return;
        } else if (deleteNode.getLeftChild() != null && deleteNode.getRightChild() == null) {
            //2.要删除的节点只有左子树，左子树要继承位置
            if (parent.getLeftChild() != null && parent.getLeftChild().getData() == data) {
                parent.setLeftChild(deleteNode.getLeftChild());
            } else {
                parent.setRightChild(deleteNode.getLeftChild());
            }
            deleteNode = null;
            return;
        } else if (deleteNode.getRightChild() != null && deleteNode.getRightChild() == null) {
            //3.要删除的节点只有右子树，右子树要继承位置
            if (parent.getLeftChild() != null && parent.getLeftChild().getData() == data) {
                parent.setLeftChild(deleteNode.getRightChild());
            } else {
                parent.setRightChild(deleteNode.getRightChild());
            }

            deleteNode = null;
        } else {
            //4.要删除的节点儿女双全，既有左子树又有右子树，需要选一个合适的节点继承，这里使用右子树中最左节点
            BTreeNode copyOfDeleteNode = deleteNode;   //要删除节点的副本，指向继承节点的父节点
            BTreeNode heresNode = deleteNode.getRightChild(); //要继承位置的节点，初始为要删除节点的右子树的树根
            //右子树没有左孩子了，他就是最小的，直接上位
            if (heresNode.getLeftChild() == null) {
                //上位后，兄弟变成了孩子
                heresNode.setLeftChild(deleteNode.getLeftChild());
            } else {
                //右子树有左孩子，循环找到最左的，即最小的
                while (heresNode.getLeftChild() != null) {
                    copyOfDeleteNode = heresNode;       //copyOfDeleteNode 指向继承节点的父节点
                    heresNode = heresNode.getLeftChild();
                }
                //找到了继承节点，继承节点的右子树（如果有的话）要上移一位
                copyOfDeleteNode.setLeftChild(heresNode.getRightChild());
                //继承节点先继承家业，把自己的左右孩子变成要删除节点的孩子
                heresNode.setLeftChild(deleteNode.getLeftChild());
                heresNode.setRightChild(deleteNode.getRightChild());
            }
            //最后就是确认位置，让要删除节点的父节点认识新儿子
            if (parent.getLeftChild() != null && parent.getLeftChild().getData() == data) {
                parent.setLeftChild(heresNode);
            } else {
                parent.setRightChild(heresNode);
            }
        }
    }
}

