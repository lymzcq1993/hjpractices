package com.hujian.algorithm;

/**
 * 70. 爬楼梯
 * https://leetcode-cn.com/problems/climbing-stairs/
 *
 * @author hujian
 * @since 2022-03-22 10:04
 */
public class ClimbStairs {
    public static void main(String[] args) {
        System.out.println(climbStairs(5));
    }
    /**
     * 这题主要的难点是在于如何理解f(n)=f(n-2)+f(n-1),因为一次爬1层或者2层
     * 例如我爬f(1)=1   f(2)=2  f(3)=3 f(4)= 5   f(5)=8
     * 所以可以理解为只要我只需要计算我爬到n-2+n-1的情况的总和我就可以算出到n的情况总和
     */
    public static int climbStairs(int n) {
        if (n<=2)return n;
        //n1为n-2,n2=n-1 ,temp是n层的总数,下次循环中就是n-1
        int n1=1,n2 = 2,temp=1;
        for (int i = 3; i <= n; i++) {
            //获取前两层的总数
            temp = n1+n2;
            n1 = n2;
            n2 = temp;
        }
        return temp;
    }
}
