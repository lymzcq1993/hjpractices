package com.hujian.algorithm;

/**
 * 121. 买卖股票的最佳时机
 * https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock/
 * @author hujian
 * @since 2022-04-13 16:09
 */
public class MaxProfit121 {
    /**
     * 使用dp
     * i天最高收益=第i天的收益-前i天的最低价格
     * 第i天的最高收益  = max(i-1天的最高收益，i天的最高收益)
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        int len = prices.length;
        //当前第i天的最大收益
        int res = 0;
        //记录每个i天的最低价格
        int[] array = new int[len];
        array[0] = prices[0];
        for(int i =1;i<len;i++){
            int minPrice = Math.min(array[i-1],prices[i]);
            array[i] = minPrice;
            res = Math.max(res,prices[i]-array[i-1]);
        }
        return res;
    }
}
