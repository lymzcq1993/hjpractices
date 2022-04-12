package com.hujian.algorithm.tree;

import com.hujian.algorithm.tree.treenode.TreeNode;

/**
 * 101. 对称二叉树
 * https://leetcode-cn.com/problems/symmetric-tree/
 * @author hujian
 */
public class isSymmetric101 {
    //递归
    public boolean isSymmetric(TreeNode root) {
        if(root == null)return false;
        return digui(root.left,root.right);
    }

    boolean digui(TreeNode left, TreeNode right){
        if(left == null && right == null){
            return true;
        }
        if(left == null || right == null || left.val != right.val){
            return false;
        }
        return digui(left.left,right.right) && digui(left.right,right.left);
    }
}
