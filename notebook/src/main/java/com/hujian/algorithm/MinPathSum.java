package com.hujian.algorithm;


import javafx.util.Pair;

import java.util.HashMap;
import java.util.Map;

/**
 * 64. 最小路径和
 * https://leetcode-cn.com/problems/minimum-path-sum/
 * @author hujian
 * @since 2022-03-21 10:05
 */
public class MinPathSum {
    public static void main(String[] args) {
        int[] a = new int[]{1,4,8,6,2,2,1,7};
        int[] b = new int[]{4,7,3,1,4,5,5,1};
        int[] c = new int[]{8,8,2,1,1,8,0,1};
        int[] d = new int[]{8,9,2,9,8,0,8,9};
        int[] e = new int[]{5,7,5,7,1,8,5,5};
        int[] f = new int[]{7,0,9,4,5,6,5,6};
        int[] g = new int[]{4,9,9,7,9,1,9,0};
//        int[] c = new int[]{4,2,1};
        int[][] h = new int[7][8];
        h[0] = a;
        h[1] = b;
        h[2] = c;
        h[3] = d;
        h[4] = e;
        h[5] = f;
        h[6] = g;
//        d[2] = c;
        System.out.println(minPathSum(h));

    }

    /**
     * DP动态规划，因为每次只有向右和向下，因此可以从外围开始计算，然后用一个新的二维数组来存储最小值
     *
     * @param grid
     */
    public static int minPathSum(int[][] grid) {
        int[][] dp = new int[grid.length][grid[0].length];
        dp[0][0] = grid[0][0];
        for (int i = 1; i < grid.length; i++) {
            dp[i][0] = dp[i-1][0]+grid[i][0];
        }
        for (int j = 1; j < grid[0].length; j++) {
            dp[0][j] = dp[0][j-1]+grid[0][j];
        }
        for (int k =1;k<grid.length;k++){
            for (int m = 1; m < grid[0].length; m++) {
                dp[k][m] = Math.min(grid[k][m]+dp[k-1][m],grid[k][m]+dp[k][m-1]);
            }
        }
        return dp[grid.length-1][grid[0].length-1];
    }


    /**
     * 这里使用了回溯算法。
     * 直接找到最后一个元素，从最后的元素开始算起到最后元素所需的最小路径
     * 但是这种不是最佳算法。。
     * @param grid
     * @return
     */
    public static int minPathSum1(int[][] grid) {
        return back(new HashMap<Pair,Integer>(),0,0,grid);
    }

    //初始点0，0开始走路
    public static int back(Map<Pair,Integer> cache, int m, int n, int[][] grid){
        Pair pair = new Pair(m,n);
        if(cache.containsKey(pair)){
            return cache.get(pair);
        }
        int num = grid[m][n];
        //达到终点
        if(m == grid.length -1 && n == grid[0].length-1){
            return num;
        }
        //m是竖着的
        if(m == grid.length - 1){
            num += back(cache,m,n+1,grid);
            cache.put(pair,num);
            return num;
        }
        //n是横着的
        if(n == grid[0].length - 1){
            num  += back(cache,m+1,n,grid);
            cache.put(pair,num);
            return num;
        }
        num +=Math.min(back(cache, m, n + 1, grid), back(cache, m + 1, n, grid));
        cache.put(pair, num);
        return num;
    }
}
