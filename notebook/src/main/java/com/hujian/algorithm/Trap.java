package com.hujian.algorithm;

/**
 * 接雨水
 * https://leetcode-cn.com/problems/trapping-rain-water/comments/\
 *
 *
 * 我的基本思路是首先寻找最高的点，然后往两边往最高的点进行移动计算每块区域的接水面积
 * 有多个最高点不影响，因为最高点也是会计算最高点之间的接水面积
 */
public class Trap {
    public static void main(String[] args) {
        System.out.println(trap(new int[]{0,1,0,2,1,0,1,3,2,1,2,1}));
    }

    //1.动态规划，从左到右计算每块区域最多可以接的水,相当于每个点都要往两边扩散计算
    //2.双向指针，较难理解一点，此处用双向指针
    //双向指针可以理解为，第一步：比较两边边界的墙的大小
    //如果左边小，先从左边靠近，第一次（left）能承载的雨水左边最大的高度-当前最小的高度
    //此处有一个比较难理解的点是为什么可以这么计算，如果右边比左边大，那么左边一直向右计算，最好的情况是中间的墙都比右边小，那么可以直接计算出所有的
    //而如果中间出现比右边高的墙，那么再从右向左计算，以此类推。
    public static int trap(int[] height) {
        int len = height.length;
        int area = 0;
        int left = 0;
        int right = len-1;
        int leftMax = 0;
        int rightMax = 0;
        while(left < right){
            if(height[left] < height[right]){
                leftMax = Math.max(height[left],leftMax);
                area += leftMax - height[left];
                ++left;
            }else{
                rightMax = Math.max(height[right],rightMax);
                area += rightMax - height[right];
                --right;
            }
        }
        return area;
    }
}