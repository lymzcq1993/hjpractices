package com.hujian.algorithm;

/**
 *
 * @author hujian
 * @since 2022-03-18 10:50
 */
public class CanJump {
    public static void main(String[] args) {
        int[] a = new int[10000];
        a[0] = 9997;
        int c = 9997;
        for (int i = 1; i  <9998 ; i++) {
            a[i] = c--;
        }
        System.out.println(canJump(a));
    }

    /**
     * 使用回溯+动态规划
     * 但是效率不行
     * 主要是从第一个元素直接查找所能到达的最远地方，然后只要不行的就标记一下
     * @param nums
     * @return
     */
    public static boolean canJump(int[] nums) {
        boolean[] isa = new boolean[nums.length];
        return back(nums,0,isa);
    }

    public static boolean back(int[] nums,int cur,boolean[] isa){
        if(cur == nums.length || isa[cur])return false;
        if(cur+nums[cur] >= nums.length-1)return true;
        int j =  nums[cur];
        while(j >0){
            if(back(nums,cur+j,isa)){
                return true;
            }
            j--;
        }
        isa[cur] = true;
        return false;
    }
}
