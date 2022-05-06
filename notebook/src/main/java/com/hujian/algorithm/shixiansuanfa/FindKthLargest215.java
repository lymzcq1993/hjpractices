package com.hujian.algorithm.shixiansuanfa;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * 215. 数组中的第K个最大元素
 * https://leetcode-cn.com/problems/kth-largest-element-in-an-array/
 * @author hujian
 */
public class FindKthLargest215 {
    //减治算法，通过不断减少区间来确定最终位置
    public int findKthLargest(int[] nums, int k) {
        int len = nums.length;
        int left = 0;
        int right =len-1;
        int targetIndex = len - k;
        while(true){
            int index = partition(nums,left,right);
            System.out.println(index);
            if(index == targetIndex){
                return nums[index];
            }
            else if(index <targetIndex ){
                left = index +1;
            }
            else{
                right = index - 1;
            }
        }
    }

    int partition(int[] nums,int left,int right){
        //从后面一个数开始排列
        int pivot  = nums[left];
        //用于记录交换了多少次，到最后进行一次交换来确定数字在数组种的最终位置
        int j = left;
        for(int i = left +1;i<=right;i++){
            if(nums[i] < pivot ){
                j++;
                swap(nums,i,j);
            }
        }
        //最终确定位置;
        swap(nums,left,j);
        return j;
    }

    void swap(int[] nums,int i,int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }


    /******************分割线*******************************/


    /**
     * 使用优先级队列+小顶堆来实现插入的元素排序
     * @param nums
     * @param k
     * @return
     */
    public int findKthLargest1(int[] nums, int k) {
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for(int i = 0;i<nums.length;i++){
            queue.offer(nums[i]);
            if(queue.size() > k){
                queue.poll();
            }
        }
        return queue.peek();
    }


    /******************分割线*******************************/


    /**
     * 暴力解法调用函数库
     * @param nums
     * @param k
     * @return
     */
    public int findKthLargest2(int[] nums, int k) {
        Arrays.sort(nums);
        return nums[nums.length-k];
    }
}
