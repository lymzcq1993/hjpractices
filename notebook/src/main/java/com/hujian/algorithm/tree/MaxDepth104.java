package com.hujian.algorithm.tree;

import com.hujian.algorithm.dataStruce.TreeNode;

import java.util.Stack;

/**
 * 同样使用递归来查找最深的树
 * 104. 二叉树的最大深度
 * https://leetcode-cn.com/problems/maximum-depth-of-binary-tree/
 * @author hujian
 */
public class MaxDepth104 {
    public int maxDepth(TreeNode root) {
        if(root == null)return 0;
        return digui(root,1);
    }

    int digui(TreeNode node,int cur){
        Stack<String> stack = new Stack<>();

        TreeNode left = node.left;
        TreeNode right = node.right;
        if(left ==null && right == null){
            return cur;
        }
        if(left !=null && right != null){
            return Math.max(digui(left,cur+1),digui(right,cur+1));
        }
        if(left != null){
            return digui(left,cur+1);
        }
        else{
            return digui(right,cur+1);
        }
    }
}
