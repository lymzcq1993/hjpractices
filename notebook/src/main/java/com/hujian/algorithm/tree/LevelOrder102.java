package com.hujian.algorithm.tree;

import com.hujian.algorithm.tree.treenode.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 102. 二叉树的层序遍历
 * https://leetcode-cn.com/problems/binary-tree-level-order-traversal/
 * @author hujian
 */
public class LevelOrder102 {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> list = new ArrayList<>();
        digui(list,root,0);
        return list;
    }

    /**
     * 参考98验证是否堆成二叉树的写法进行递归查询
     */
    void digui(List<List<Integer>> list,TreeNode node,int index){
        if(node == null){
            return;
        }
        if(list.size() == index){
            List<Integer> l = new ArrayList<>();
            l.add(node.val);
            list.add(l);
        }
        else{
            list.get(index).add(node.val);
        }
        digui(list,node.left,index+1);
        digui(list,node.right,index+1);
    }
}
