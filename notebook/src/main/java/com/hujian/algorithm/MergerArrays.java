package com.hujian.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 56. 合并区间
 * https://leetcode-cn.com/problems/merge-intervals/
 */
public class MergerArrays {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(merge(new int[][]{new int[]{3,4},new int[]{5,6}})));
//        System.out.println(Arrays.toString(merge(new int[][]{new int[]{1,3},new int[]{2,6},new int[]{8,10},new int[]{15,18}})));
    }

    //应该是先排序，然后从左往右进行合并
    public static int[][] merge(int[][] intervals) {
        //先从小到大排序
        Arrays.sort(intervals,(x,y) ->{
            return x[0]-y[0];
        });
        List<int[]> list = new ArrayList<>();
         int n = intervals.length;
        int i =0;
         while ( i <n){
             int prex = intervals[i][0];
             int preY = intervals[i][1];
             while(i < n-1 && preY >= intervals[i+1][0]){
                 preY = Math.max(preY,intervals[i+1][1]);
                 i++;
             }
             list.add(new int[]{prex,preY});
             i++;
         }
         return list.toArray(new int[list.size()][2]);
    }

    /**
     * 思路错了。。。这里是从左边开始排序然后一直不满足某些条件。。
     * @param intervals
     * @return
     */
    public static int[][] merge2(int[][] intervals) {
        //表示哪些被合并了
        List<int[]> a = new ArrayList<>();
        boolean[] b = new boolean[intervals.length];
        for(int i=0;i<intervals.length;i++){
            if(b[i])continue;
            int preX =intervals[i][0];
            int preY = intervals[i][1];
            for(int j=i+1;j<intervals.length;j++){
                if(b[j])continue;
                int nextX = intervals[j][0];
                int nextY = intervals[j][1];
                //1.中间值有交叉
                if( preY >= nextX && preY <= nextY && preX <= nextX){
                    System.out.println(1111);
                    preY = nextY;
                    b[j] = false;
                }
                //2.位于下一个值中间
                else if(preX >= nextX  && preY <= nextY){
                    System.out.println(2222);
                    preX = nextX;
                    preY = nextY;
                }
                //3.位于下一个值右边
                else if(preX >= nextX  && preX <= nextY  && preY >= nextY){
                    System.out.println(333);
                    preX = nextX;
                    b[j] = false;
                }
                //4.位于前一个值中间
                else if(nextX >= preX && nextY <= preY){
                    System.out.println(4);
                }
                else{
                    b[j] = false;
                }
            }
            a.add(new int[]{preX,preY});
            // System.out.println(preX+","+preY);
        }
        int[][] c = new int[a.size()][2];
        for(int k=0;k<a.size();k++){
            c[k] = a.get(k);
        }
        return c;    }
}
