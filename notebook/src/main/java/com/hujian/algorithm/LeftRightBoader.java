package com.hujian.algorithm;

/**
 * 在排序数组中查找元素的第一个和最后一个位置
 * https://leetcode-cn.com/problems/find-first-and-last-position-of-element-in-sorted-array/
 */
public class LeftRightBoader {
    public static int[] searchRange(int[] nums, int target) {
        int[] index = new int[]{-1,-1};
        //二分法，先找右边界，再找左边界
        int right = getRightBorder(nums,target);
        int left = getLeftBorder(nums,target);
        //使用闭合区间[]
        if(right == -1 || left == -1){
            return new int[]{-1,-1};
        }
        return new int[]{left,right};
    }

    //获取右边界
    static int getRightBorder(int[] nums,int target){
        int start = 0;
        int rightBorder = -1;
        int right = nums.length-1;
        //使用闭合区间[]
        while(start <= right){
            int mid = (start+right)/2;
            //中位数如果大于目标值，则target应该在左区间
            if(nums[mid] > target){
                right = mid -1;
            }
            //否则在右边区间找
            else if(nums[mid] < target){
                start = mid +1;
            }
            //找到了该值，继续向右边逼近查找
            else{
                rightBorder = mid;
                start = mid + 1;
            }
        }
        return rightBorder;
    }

    static int getLeftBorder(int[] nums,int target){
        int start = 0;
        int leftBorder = -1;
        int right = nums.length-1;
        //使用闭合区间[]
        while(start <= right){
            int mid = (start+right)/2;
            //中位数如果大于目标值，则target应该在左区间
            if(nums[mid] > target){
                right = mid -1;
            }
            //否则在右边区间找
            else if(nums[mid] < target){
                start = mid +1;
            }
            //找到了该值，继续向左边逼近查找
            else{
                leftBorder = mid;
                right = mid - 1;
            }
        }
        return leftBorder;
    }
}
