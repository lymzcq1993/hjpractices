package com.hujian.algorithm.tree;

import com.hujian.algorithm.tree.treenode.TreeNode;

import java.util.HashMap;

/**
 *105. 从前序与中序遍历序列构造二叉树
 * https://leetcode-cn.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/
 * @author hujian
 * @since 2022-04-12 16:24
 */
public class BuildTree105 {
    HashMap<Integer,Integer> map =  new HashMap<>();
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        int len = preorder.length;
        if(len == 0){
            return null;
        }
        //改进使用的HashMap来节省遍历中序的时间
        for(int i =0;i<inorder.length;i++){
            map.put(inorder[i],i);
        }
        return begin(preorder,0,preorder.length,inorder,0,inorder.length);
    }

    /**
     *  先序遍历的特点：第一个结点一定是根结点，后面的则是自己的子结点
     *  中序遍历：以根结点为分界，左边是左树，右边是右树
     */
    TreeNode begin(int[] preorder,int p_start,int p_end,
                   int[] inorder,int i_start,int i_end){
        if(p_start == p_end){
            return null;
        }
        //先序遍历获取第一个，也就是根节点
        int rootValue = preorder[p_start];
        TreeNode root = new TreeNode(rootValue);
        //从中序遍历中查找根节点所在位置
        // int zhongxuIndex = 0;
        // for(int i=i_start;i<i_end;i++){
        //     if(inorder[i] == rootValue){
        //         zhongxuIndex = i;
        //         break;
        //     }
        // }
        //改进使用 map的方案
        int zhongxuIndex = map.get(rootValue);
        //获取左树元素元素数量，即中序遍历对应下标左边-中序起始位置
        int leftNum = zhongxuIndex -  i_start;
        //这里左树就是zhongxuIndex左边构建一个树，起始位置就是[当前位置+1,当前位置+中序遍历得出的左树元素的数量)
        root.left = begin(preorder,p_start+1,p_start+leftNum+1,inorder,i_start,zhongxuIndex);
        //右树构建
        root.right = begin(preorder,p_start+leftNum+1,p_end,inorder,zhongxuIndex+1,i_end);
        return root;
    }
}
