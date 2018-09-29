package com.cwj.binarytree;

import java.util.List;

/**
 * Created by cwj on 18-8-28.
 * N叉树
 */
public class NTreeNode {
    public int val;
    public List<NTreeNode> children;

    public NTreeNode() {}

    public NTreeNode(int _val,List<NTreeNode> _children) {
        val = _val;
        children = _children;
    }
}
