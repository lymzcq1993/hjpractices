package com.hujian.algorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * 全排列
 * https://leetcode-cn.com/problems/permutations/
 *
 * 题目经典的回溯算法，关键点在于如何剪枝。这里使用了一个bool数组来记录每个数字的使用记录
 * 最后回溯的时候会进行状态的转换
 * 这里我想到了回溯，但是没想到如何剪枝。。。
 */
public class Permute {
    public static void main(String[] args) {
        System.out.println(permute(new int[]{1,2,3,4}));
        System.gc();
    }

    public static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        //这是关键点，记录每个元素是否被用过
        boolean[] record = new boolean[nums.length];
        back(list,0,nums,record,new ArrayList());
        return list;
    }

    public static void back(List<List<Integer>> list,int n,int[] nums,boolean[] record,List<Integer> newList){
        //到达末尾
        if(nums.length == n){
            list.add(new ArrayList(newList));
            return;
        }
        //这里开始遍历所有组合深度
        for(int i=0;i<nums.length;i++){
            if(!record[i]){
                //添加元素
                newList.add(nums[i]);
                //然后该元素被使用过
                record[i] = true;
                back(list,n+1,nums,record,newList);
                //当跳出循环后，最后一个元素又可以使用
                newList.remove(newList.size()-1);
                record[i] = false;
            }
        }
    }
}
