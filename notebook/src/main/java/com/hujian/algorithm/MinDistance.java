package com.hujian.algorithm;

/**
 * 72. 编辑距离
 * https://leetcode-cn.com/problems/edit-distance/
 *
 * 给你两个单词 word1 和 word2， 请返回将 word1 转换成 word2 所使用的最少操作数  。
 *
 * 你可以对一个单词进行如下三种操作：
 *
 * 插入一个字符
 * 删除一个字符
 * 替换一个字符
 * @author hujian
 * @since 2022-03-23 11:00
 */
public class MinDistance {
    public static void main(String[] args) {

        System.out.println(minDistance("wuxianshashasld","ros"));
    }

    /**
     * 也是考的动态规划，难的是dp[]数组里面应该怎么存
     * 可以执行的操作有  增  删   改
     * 1.对于hor增来说，相当于对hors删。所以也可以认为删除对于A来说相当于是word2增加
     * 2.相同的，对于hors删来说，相当于对hor增
     * 主要就是理解能获取当前字符串的上一步的所有情况，就能知道dp存的是什么
     * 例如  horse    ros
     * 1.dp[1][1]=1 h->r   dp[0][1] = 1  dp[1][0]=1    dp[0][0]比较特殊，需要比较字母是否相同，不同的再一步即可得到当前的字符,所以此处是1
     * 2.dp[1][2]=2 h->ro   dp[0][2] = 1+1  dp[2][0]=1+1    dp[0][1] = 1+( ''=='o'?:0:1)1
     * ......
     * 3.dp[2][2]=1  ho->ro  dp[1][2]= 2    dp[2][1]=2   dp[1][1] = 1+0
     */
    public static int minDistance(String word1, String word2) {
        int len1 = word1.length();
        int len2 = word2.length();

        //这里有边界，即需要初始化空串变化的边界，即从0开始得出变成所有的字符串的编辑距离
        int dp[][] = new int[len1+1][len2+1];
        for (int i = 0; i < len1 + 1; i++) {
            dp[i][0] = i;
        }

        for (int j= 0; j < len2 + 1; j++) {
            dp[0][j] = j;
        }

        for (int k = 1; k < len1+1; k++) {
            for (int m=1;m<len2+1;m++){
                //计算从左边开始的
                int left = dp[k-1][m]+1;
                int right = dp[k][m-1]+1;
                int l_d = dp[k-1][m-1];
                //计算这个位置上的字符串是否相同，如果不同需要多一步
                if (word1.charAt(k-1) != word2.charAt(m-1)){
                    l_d ++;
                }
                //设置距离最短的
                dp[k][m] = Math.min(left,Math.min(right,l_d));
            }
        }
        return dp[len1][len2];
    }
}
