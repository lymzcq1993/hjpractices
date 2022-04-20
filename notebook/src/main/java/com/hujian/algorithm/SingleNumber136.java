package com.hujian.algorithm;

import java.util.Arrays;

/**
 * 136. 只出现一次的数字
 * https://leetcode-cn.com/problems/single-number/
 * @author hujian
 */
public class SingleNumber136 {

    /**
     * 主要考察异或预算符   二进制  相同为0，不同为1
     * 1交换律   2.结合律
     * 使用异或运算符   6^6 = 0     0^6 =  6
     * @param nums
     * @return
     */
    public int singleNumber(int[] nums) {
        int len = nums.length;
        if(len ==0 )return 0;
        int num = nums[0];
        for(int i =1;i<nums.length;i++){
            num = num ^ nums[i];
        }
        return num;
    }

    /**
     * n*log(n)   超出了题目要求
     * @param nums
     * @return
     */
    public int singleNumber2(int[] nums) {
        int len = nums.length;
        if(len ==0 )return 0;
        Arrays.sort(nums);
        for(int i=0;i<len-1;i+=2){
            if(nums[i] != nums[i+1]){
                if(i == 0)return nums[i];
                else {
                    return nums[i];
                }
            }
        }
        return nums[len-1];
    }
}
