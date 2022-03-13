package com.hujian.algorithm;

import java.util.ArrayList;
import java.util.List;

public class Huisu {
    public static void main(String[] args) {
        System.out.println(combinationSum(new int[]{3,5,8},11));
    }

    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> list = new ArrayList<>();
        int len = candidates.length;
        for(int i=0;i<len;i++){
            back(candidates,i,target,list,new ArrayList());
        }
        return list;
    }

    static void back(int[] nums,int nextNum,int target,List<List<Integer>> tarList,List<Integer> myList){
        //达到最后面返回
        if(nextNum >= nums.length){
            return;
        }
        int cur = nums[nextNum];
        int l = target - cur;
        if(l < 0){
            return;
        }
        //是一种组合
        if(l == 0){
            myList.add(cur);
            tarList.add(myList);
            return;
        }
        myList.add(cur);
        for (int i =nextNum;i< nums.length;i++){
            back(nums,i,l,tarList,new ArrayList<>(myList));
        }
//        back(nums,nextNum+1,l,tarList,new ArrayList<>(myList));
//        back(nums,nextNum+2,l,tarList,new ArrayList<>(myList));
    }
}
