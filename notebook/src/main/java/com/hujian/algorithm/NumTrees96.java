package com.hujian.algorithm;

/**
 * 96. 不同的二叉搜索树
 * https://leetcode-cn.com/problems/unique-binary-search-trees/
 */
public class NumTrees96 {
    /**
     * 动态规划
     * 根据搜索二叉树的特性，左边的树结点一定比右边小
     * 因此可以得出当有n个结点的时候，G(n) 为二叉树数量
     * 对于任意n个结点的任意一点i  都有    f(i) = G(i-1) +G(n-i)
     * 因为G(0)=1  G(1)=1
     * 因此n>=1  G(n)  =G(0) +G(1)   +  G(1)+G(0) +  .... +  G(i-1)+G(n-i)
     * @param n
     * @return
     */
    public int numTrees(int n) {
        int[] G = new int[n + 1];
        G[0] = 1;
        G[1] = 1;

        for (int i = 2; i <= n; ++i) {
            for (int j = 1; j <= i; ++j) {
                G[i] += G[j - 1] * G[i - j];
            }
        }
        return G[n];
    }

}
