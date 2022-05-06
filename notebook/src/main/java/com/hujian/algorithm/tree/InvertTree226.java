package com.hujian.algorithm.tree;

import com.hujian.algorithm.dataStruce.TreeNode;

/**
 *226. 翻转二叉树
 * https://leetcode-cn.com/problems/invert-binary-tree/
 * @author hujian
 * @since 2022-04-28 17:21
 */
public class InvertTree226 {
    /**
     * 树的分支算法，把大树化成无数颗小树递归搜索
     * @param root
     * @return
     */
    public TreeNode invertTree(TreeNode root) {
        if(root == null){
            return null;
        }
        return swap(root);
    }

    TreeNode swap(TreeNode root){
        TreeNode tmp = root.left;
        if(root.right != null){
            root.left = root.right;
            swap(root.left);
        }
        else{
            root.left = null;
        }
        if(tmp != null){
            root.right = tmp;
            swap(root.right);
        }
        else{
            root.right = null;
        }

        return root;
    }
}
