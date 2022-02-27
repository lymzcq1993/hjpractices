package com.hujian.algorithm;

/**
 * 盛最多水的容器
 *https://leetcode-cn.com/problems/container-with-most-water/
 * 给定一个长度为 n 的整数数组 height 。有 n 条垂线，第 i 条线的两个端点是 (i, 0) 和 (i, height[i]) 。
 *
 * 找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
 *
 * 返回容器可以储存的最大水量。
 *
 * 说明：你不能倾斜容器。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/container-with-most-water
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 *
 *
 * 总结：最开始想的最暴力的枚举，导致超时。然后转变思路从最长的边界开始向中心聚拢。并且中间要根据情况进行 跳跃
 */
public class MaxWaterComponent {
    public static void main(String[] args) {
        System.out.println(maxArea2(new int[]{1,8,6,2,5,4,8,3,7}));
    }


    public static int maxAreaMost(int[] height) {
        int length = height.length;
        if(length <= 1) return -1;
        int i = 0, j = length - 1, res = 0;
        while(i < j){
            int h = Math.min(height[i], height[j]);
            res = Math.max(res, h * (j - i));
            if(height[i] < height[j]) ++i;
            else --j;
        }
        return res;
    }

    /**
     * 慢写法
     * @param height
     * @return
     */
    public static int maxArea2(int[] height) {
        int max = 0;
        for(int i =0;i<height.length;i++){
            for(int j = height.length-1;j>i;j--){
                int cur = (j - i) * Math.min(height[i], height[j]);
                max = Math.max(cur,max);
                //如果最长的面积
                if(height[j] > height[i]){
                    i++;
                    j++;
                };
            }
        }
        return max;
    }
}
