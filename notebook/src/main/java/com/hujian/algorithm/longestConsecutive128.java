package com.hujian.algorithm;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 128. 最长连续序列
 * https://leetcode-cn.com/problems/longest-consecutive-sequence/
 * @author hujian
 * @since 2022-04-15 10:45
 */
public class longestConsecutive128 {
    /**
     * 每次都会记录该数字临近的值，例如  [2,1,3]，每次都是更新边界的值，因为里面的值不会因为临界而计算到
     * 先放入2
     * 1. left 没有，0
     * 2. right没有，0
     * 3.更新当前的最长长度map.put(2,1);
     * 4. 放入1 ，left无 0，right有值，1，更新长度 map.put(1,2)  map,put(2,2)
     * 5. 放入3  left有值，2，right无，0  更新长度 map.put(3,3)  map,put(1,3)
     * @param nums
     * @return
     */
    public int longestConsecutive(int[] nums) {
        Map<Integer,Integer> map = new HashMap<>();
        int maxLen = 0;
        for(int i=0;i<nums.length;i++){
            int num = nums[i];
            //如果map种没有值
            if(map.get(num) ==null){
                int left = map.getOrDefault(num-1,0);
                int right = map.getOrDefault(num+1,0);
                //每次更新数字边界的长度，左右两边累加
                int curLen = 1 + left + right;
                maxLen = Math.max(maxLen,curLen);
                map.put(num,curLen);
                map.put(num-left,curLen);
                map.put(num+right,curLen);
            }
        }
        return maxLen;
    }

    //题目要求O(n)，虽然此处代码通过，但是加上排序的时间复杂度n*logn
    public int longestConsecutive2(int[] nums) {
        if(nums.length == 0) {
            return 0;
        }
        HashMap<Integer, Integer> map = new HashMap<>();
        Arrays.sort(nums);
        int maxLen = 1;
        int cur = 1;
        for(int i = 1;i<nums.length;i++){
            if(nums[i] == nums[i-1]+1){
                cur ++;
                // System.out.println(cur);
            }
            else if(nums[i] == nums[i-1]){
                continue;
            }
            else{
                cur =1;
            }
            maxLen = Math.max(maxLen,cur);
        }
        return maxLen;
    }
}
