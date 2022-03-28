package com.hujian.algorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * 78. 子集
 * https://leetcode-cn.com/problems/subsets/
 */
public class Subsets78 {
    //标注回溯算法
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        //从第一个元素开始循环遍历
        for(int i =0;i<nums.length;i++){
            back(list,new ArrayList<>(),nums,i);
        }
        list.add(new ArrayList<>());
        return list;
    }


    void back(List<List<Integer>> list,List<Integer> num,int[] nums,int cur){
        //当累加到最后的时候进行return
        if(cur ==nums.length){
            return;
        }
        List<Integer> ll = new ArrayList<>(num);
        ll.add(nums[cur]);
        list.add(ll);
        //这里会从左往右开始循环  例如对于1234  ->  12   13   14这样循环
        for(int j=1;j<nums.length-cur;j++){
            back(list,ll,nums,cur+j);
        }
    }
}
