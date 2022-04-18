package com.hujian.algorithm.tree;

import com.hujian.algorithm.dataStruce.TreeNode;

import java.util.Collections;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 *https://leetcode-cn.com/problems/validate-binary-search-tree/
 *  98. 验证二叉搜索树
 *     基于中序遍历，每次last都是取到的上一次的值，这样能保证总的value是升序排序的.
 *     这里最重要的就是需要直到如果从左往上走，第一列最上面的数字一定比第二列最下面的小
 *
 * @author hujian
 */
public class IsValidBST98 {
    public static void main(String[] args) {
        Map<String, Integer> map = Collections.synchronizedMap(new HashMap<>(16));
        Hashtable<String,Integer> hashtable = new Hashtable<>();
        hashtable.put("sdsd",1);
        hashtable.get("sdds");
        hashtable.remove("sd");
        ConcurrentHashMap<String,Integer> concurrentHashMap = new ConcurrentHashMap<>();
        concurrentHashMap.put("sd",1);
    }



    /**
     * 这里如果用Integer.MAX_VALUE有一个案例是[-2147483648]
     */
    double last = -Double.MAX_VALUE;
    public boolean isValidBST(TreeNode root) {
        if(root == null){
            return true;
        }
        //遍历所有左节点
        if(isValidBST(root.left)){
            //这里因为都是升序排序的，如果不符合就说明搜索树不规范
            if(last < root.val){
                last = root.val;
                //继续遍历右子树
                return isValidBST(root.right);
            }
        }
        return false;
    }

}
