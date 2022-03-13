package com.hujian.algorithm;

import java.util.Arrays;

public class NextSort {
    public static void main(String[] args) {
        //213
        System.out.println(Arrays.toString(nextPermutation(new int[]{1,3,2})));
    }

    public static int[] nextPermutation(int[] nums) {
        int length = nums.length;
        int i= length-1;
        while(i>0){
            //倒序找出右边比右边大的数字
            if(nums[i-1] < nums[i]){
                //这里从最右边开始找是因为从右边往左边已经是升序了，所以找到的第一个大的数就是离nums[i-1]最近比其大的数
                for (int j=length-1;j>=i;j--){
                    if (nums[j]>nums[i-1]){
                        int tmp = nums[j];
                        nums[j] = nums[i-1];
                        nums[i-1] = tmp;
                        break;
                    }
                }
                Arrays.sort(nums,i,length);
                break;
            }
            i--;
        }
        if(i==0){
            Arrays.sort(nums);
        }
        return nums;
    }
}
