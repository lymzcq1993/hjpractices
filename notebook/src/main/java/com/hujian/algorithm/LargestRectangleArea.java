package com.hujian.algorithm;

import java.util.LinkedList;

/**
 * 84. 柱状图中最大的矩形
 * https://leetcode-cn.com/problems/largest-rectangle-in-histogram/
 */
public class LargestRectangleArea {
    public static void main(String[] args) {
        System.out.println(largestRectangleArea(new int[]{0,2,0}));
    }

    /**
     * 1.首先最容易想到的是暴力解法，即计算出以每根柱子为中心，向两边扩散能计算出的最大面积。
     * 每根柱子向两边到达第一个比自己矮（<）的柱子，最大面积就是两个矮的中间的面积
     * 2.单调栈。根据每个柱子最大面积达的特性可以计算出
     * @param heights
     * @return
     */
    public static int largestRectangleArea(int[] heights) {
        int length = heights.length;
        if (length ==1){
            return heights[0];
        }
        int res = 0;
        LinkedList<Integer> list = new LinkedList<>();
        for (int i = 0; i < length; i++) {
            //如果找到了第一个比自己小的
            while(!list.isEmpty() && heights[i] < heights[list.peekLast()]){
                int height = heights[list.pollLast()];
                while (!list.isEmpty() && heights[list.peekLast()] == height){
                    list.pollLast();
                }
                int width;
                if (list.isEmpty() ){
                    width = i;
                }
                else{
                    width= i-list.peekLast()-1;
                }
                res = Math.max(res,width*height);
            }
            list.add(i);
        }

        while(!list.isEmpty()){
            int height = heights[list.pollLast()];
            while (!list.isEmpty() && heights[list.peekLast()] == height){
                list.pollLast();
            }
            int width;
            if (list.isEmpty() ){
                width = length;
            }
            else{
                width= length-list.peekLast()-1;
            }
            res = Math.max(res,width*height);
        }
        return  res;    }
}
