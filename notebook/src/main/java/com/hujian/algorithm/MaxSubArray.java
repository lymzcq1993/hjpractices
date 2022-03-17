package com.hujian.algorithm;

/**
 * @author hujian
 *53. 最大子数组和
 *https://leetcode-cn.com/problems/maximum-subarray/
 * 给你一个整数数组 nums ，请你找出一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 *
 * 子数组 是数组中的一个连续部分。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：nums = [-2,1,-3,4,-1,2,1,-5,4]
 * 输出：6
 * 解释：连续子数组 [4,-1,2,1] 的和最大，为 6 。
 * 示例 2：
 *
 * 输入：nums = [1]
 * 输出：1
 * 示例 3：
 *
 * 输入：nums = [5,4,-1,7,8]
 * 输出：23
 *  
 *
 * 提示：
 *
 * 1 <= nums.length <= 105
 * -104 <= nums[i] <= 104
 *  
 *
 * 进阶：如果你已经实现复杂度为 O(n) 的解法，尝试使用更为精妙的 分治法 求解。
 *
 */
public class MaxSubArray {
    public static void main(String[] args) {
        System.out.println(maxSubArray(new int[]{-2,1,-3,4,-1,2,1,-5,4}));
    }

    /**
     * O(n)一次循环
     * 这题最大的难度是怎么找出规律。。如何找到连续累加最大的数
     */
    public static int maxSubArray(int[] nums) {
        //pre定义为前一个数，maxS是最大的连续长度
        int pre = 0,maxS= nums[0];
        for (int num : nums) {
            //最核心一个规律，这句的意思是如果前面累加的数没有当前的数大
            //那么就从当前的数重新开始计算累加
            pre = Math.max(pre + num, num);
            maxS = Math.max(pre, maxS);
        }
        return maxS;
    }
}
