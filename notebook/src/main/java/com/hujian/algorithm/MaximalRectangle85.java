package com.hujian.algorithm;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * 85. 最大矩形
 * https://leetcode-cn.com/problems/maximal-rectangle/
 * 参考84求柱形的最大面积
 * @author hujian
 * @since 2022-03-29 12:21
 */
public class MaximalRectangle85 {
    /**
     * 难点是如何转化思想，将求矩形最大面积转化成求柱形图的最大面积
     *
     * 每一层看作是柱状图，可以套用84题柱状图的最大面积。
     *
     * 第一层柱状图的高度["1","0","1","0","0"]，最大面积为1；
     *
     * 第二层柱状图的高度["2","0","2","1","1"]，最大面积为3；
     *
     * 第三层柱状图的高度["3","1","3","2","2"]，最大面积为6；
     *
     * 第四层柱状图的高度["4","0","0","3","0"]，最大面积为4；
     *
     * 这里有一点需要注意的是，下面一层的高度并不是上面 高度的总和。
     * 每当遇到0的时候就会出现断层，因为矩形的面积是需要连续的，而出现0说明不是连续的
     */
    public int maximalRectangle(char[][] matrix) {
        int[] dp = new int[matrix[0].length];
        int res = 0;
        //计算每一层的最大层高隐藏条件，如果断层了就需要重新计算
        for(int i=0;i<matrix.length;i++){
            for(int j =0;j < matrix[i].length;j++){
                int a =  matrix[i][j] - '0';
                if(a == 0){
                    dp[j] = 0;
                }
                else{
                    dp[j]++;
                }
            }
            int j = maxArea(dp);
            System.out.println(Arrays.toString(dp)+"---"+j);
            res = Math.max(res,j);
        }
        return res;
    }

    //计算柱状图每个的最大面积
    int maxArea(int[] m){
        int res = 0;
        int len = m.length;
        if(len == 1){
            return m[0];
        }
        LinkedList<Integer> list = new LinkedList();
        for(int i=0;i<len;i++){
            //计算出不止一个柱子的面积，计算是从右往左计算
            while(!list.isEmpty()
                    && m[i] < m[list.peekLast()]){
                int height = m[list.pollLast()];
                while(!list.isEmpty() && m[list.peekLast()] == height){
                    list.pollLast();
                }
                int width;
                if(list.isEmpty()){
                    width = i;
                }
                else{
                    //这里peekLast()相当于是一堵墙，宽度需要再-1
                    width = i - list.peekLast()-1;
                }
                res  =Math.max(width*height,res);
            }
            list.add(i);
        }
        //遍历链表中剩余的
        while(!list.isEmpty()){
            int height = m[list.pollLast()];
            while (!list.isEmpty() && m[list.peekLast()] == height){
                list.pollLast();
            }
            int width;
            if (list.isEmpty() ){
                width = len;
            }
            else{
                width= len-list.peekLast()-1;
            }
            res = Math.max(res,width*height);
        }
        return res;
    }
}
