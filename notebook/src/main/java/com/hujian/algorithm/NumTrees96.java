package com.hujian.algorithm;

/**
 * @author hujian
 * @since 2022-03-31 16:00
 */
public class NumTrees96 {
    /**
     * 这里假设有n个结点，则N个节点对应的个数为G(n)
     * 针对G(n)，i为作为root节点的时候的二叉树的个数，则左边的树为i-1,右边的树为n-i
     * G(n) = G(i-1)*G(n-i)
     * G0 = 1
     * G1 = 1 * 1
     * G2 = 1 * 1 +  1*1
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
