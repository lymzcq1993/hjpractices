package com.hujian.algorithm;


import cn.hutool.core.lang.Pair;

import java.util.HashMap;
import java.util.Map;

/**
 * 62. 不同路径
 * https://leetcode-cn.com/problems/unique-paths/
 * 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为 “Start” ）。
 *
 * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为 “Finish” ）。
 *
 * 问总共有多少条不同的路径？
 */
public class UniquePaths {
    public static void main(String[] args) {
        System.out.println();
    }

    /**
     *
     * 该题最明显的是使用回溯算法+动态规划剪枝
     * 1.return的条件是m或者n达到边界，因为达到边界后毕竟只有一条路走，所以此时可以直接返回+1
     * 2.去重的方法是使用map来记录已经走过的点，因为某个点必定会从不同点过来，但是得到的结果是相同的，所以可以在此处进行去重
     */
    public static int uniquePaths(int m, int n) {
        return back(new HashMap<Pair<Integer,Integer>,Integer>(), 0, 0, m, n);
    }


    public static  int back(Map<Pair<Integer,Integer>,Integer> cache, int i, int j, int m, int n) {
        Pair<Integer,Integer> p = new Pair(i,j);
        //如果(i,j)在缓存中则直接返回，缓存中已记录从该点走得到的最终结果，无需重复走
        if(cache.containsKey(p)) {
            return cache.get(p);
        }
        //到达边界时，返回 1
        if(i == m - 1 || j == n - 1) {
            return 1;
        }
        //继续递归调用，往下i+1，往右j+1
        cache.put(p, back(cache, i + 1, j, m, n) + back(cache, i, j + 1, m, n) );
        return cache.get(p);
    }
}
