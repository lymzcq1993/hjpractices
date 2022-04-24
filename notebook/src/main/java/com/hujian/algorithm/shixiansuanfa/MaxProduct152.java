package com.hujian.algorithm.shixiansuanfa;

/**
 * 152. 乘积最大子数组
 * https://leetcode-cn.com/problems/maximum-product-subarray/
 * @author hujian
 */
public class MaxProduct152 {
    //以0为边界，分为N个子数组，每个子数组中：
    //1.负数为偶数个，乘以全部的数即可获取最大值
    //2.负数为奇数个，乘到最后一个负数的时候，右边可能会出现最大值
    //[3,-1,-1,-2,5]比如这种情况，不从右边开始计算会忽略掉最大值5的情况
    public int maxProduct(int[] nums) {
        //从左往右边乘
        int last = 1;
        int res = nums[0];
        for(int num:nums){
            last = last * num;
            if(res < last){
                res = last;
            }
            if(num == 0){
                last = 1;
            }
        }
        last = 1;
        for(int i = nums.length-1;i>=0;i--){
            int num = nums[i];
            last = last * num;
            if(res < last){
                res = last;
            }
            if(num == 0){
                last = 1;
            }
        }
        return res;
    }
}
