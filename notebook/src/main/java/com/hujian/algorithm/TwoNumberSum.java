package com.hujian.algorithm;

import java.util.Arrays;
import java.util.HashMap;

/**
 * https://leetcode-cn.com/problems/two-sum/
 * 给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 target  的那 两个 整数，并返回它们的数组下标。
 *
 * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。
 *
 * 你可以按任意顺序返回答案。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：nums = [11,15,2,7], target = 9
 * 输出：[0,1]
 * 解释：因为 nums[0] + nums[1] == 9 ，返回 [0, 1] 。
 *
 * 总结：该题重点是把两数之和x+y=target的思路转换为x=target-y从数组中查数
 */
public class TwoNumberSum {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(hash(new int[]{10, 12, 7, 8, 5, 4, 1}, 5)));
        System.out.println(Arrays.toString(meiju(new int[]{10, 12, 7, 8, 5, 4, 1}, 5)));
    }

    /**
     * O(n^2)枚举
     */
    public static int[] meiju(int[] nums,int target){
        for (int i = 0; i < nums.length; i++) {
            if (i+1 == nums.length){
                return new int[0];
            }
            for (int j = i+1; j < nums.length; j++) {
                if (target-nums[i] == nums[j]){
                    return new int[]{i,j};
                }
            }
        }
        return new int[0];
    }


    /**
     * O(1) 使用Hash表，将两数之和转化为target-x，变成从数组中查数。
     * 思路是每次减得到的值放入到hash表中，如果命中则返回，没有则将该数放入hash
     */
    public static int[] hash(int[] nums,int target){
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if(map.containsKey(target-nums[i])){
                return new int[]{map.get(target-nums[i]),i};
            }
            map.put(nums[i],i);
        }
        return new int[0];
    }

}
