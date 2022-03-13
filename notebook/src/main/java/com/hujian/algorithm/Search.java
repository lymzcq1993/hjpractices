package com.hujian.algorithm;

/**
 *  搜索旋转排序数组
 */
public class Search {
    public static void main(String[] args) {
        System.out.println(search(new int[]{1,3},3));
    }

    public static int search(int[] nums, int target) {
        int len = nums.length;
        if(len == 1){
            return nums[0] == target?0:-1;
        }
        // if(len == 2){
        //     if(nums[0] == target)return 0;
        //     if(nums[1] == target)return 1;
        //     return -1;
        // }
        int start = 0;
        int end = len-1;
        //二分数组，一部分有序一部分局部有序
        //如果nums[0]<nums[mid] 则前半有序，后半局部有序
        while(start <= end){
            int mid = (end+start)/2;
            //说明前半部分有序
            if(nums[start] <= nums[mid]){
                for(int i = start;i<=mid;i++){
                    if(target == nums[i])return i;
                }
                //如果有序部分没找到
                start = mid+1;
            }
            //后半部分有序
            else{
                for(int j = mid;j<len;j++){
                    if(target == nums[j])return j;
                }
                end = mid;
            }
        }
        return -1;
    }
}
