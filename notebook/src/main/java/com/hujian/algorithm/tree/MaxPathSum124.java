package com.hujian.algorithm.tree;

import com.hujian.algorithm.tree.treenode.TreeNode;

/**
 * 124. 二叉树中的最大路径和
 * https://leetcode-cn.com/problems/binary-tree-maximum-path-sum/
 * @author hujian
 * @since 2022-04-14 16:40
 */
public class MaxPathSum124 {
    //用于比较单个树的三个结点的总和可能为最大值的情况，因为有负数，所以选择负数最大值
    int max = -Integer.MAX_VALUE;
    public int maxPathSum(TreeNode root) {
        digui(root);
        return max;
    }

    int digui(TreeNode node){
        if(node == null){
            return 0;
        }
        //递归计算左分支的最大结点
        //此处获取的最大值如果是负数，需要清0
        int leftMaxVal = Math.max(0,digui(node.left));
        //递归计算右分支的最大结点
        int rightMaxVal = Math.max(0,digui(node.right));
        //获取比较后的最大值
        max = Math.max(max,node.val+leftMaxVal+rightMaxVal);
        return node.val+Math.max(leftMaxVal,rightMaxVal);
    }
}
