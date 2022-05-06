package com.hujian.algorithm.tree;

import com.hujian.algorithm.dataStruce.TreeNode;

/**
 * 236. 二叉树的最近公共祖先
 * https://leetcode-cn.com/problems/lowest-common-ancestor-of-a-binary-tree/
 * @author hujian
 */
public class LowestCommonAncestor236 {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        //判空返回
        if(root == null){
            return root;
        }

        if(root == p || root == q){
            return root;
        }
        //递归寻找目标node
        TreeNode left = lowestCommonAncestor(root.left,p,q);

        TreeNode right = lowestCommonAncestor(root.right,p,q);
        //在根节点左右两侧
        if(left != null && right != null){
            return root;
        }
        //都在左子树上
        if(left != null){
            return left;
        }

        if(right != null){
            return right;
        }

        return null;
    }
}
