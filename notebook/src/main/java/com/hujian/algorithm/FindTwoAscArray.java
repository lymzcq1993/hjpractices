package com.hujian.algorithm;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode-cn.com/problems/median-of-two-sorted-arrays/
 寻找两个正序数组的中位数

 给定两个大小分别为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。请你找出并返回这两个正序数组的 中位数 。

 算法的时间复杂度应该为 O(log (m+n)) 。

  

 示例 1：

 输入：nums1 = [1,3], nums2 = [2]
 输出：2.00000
 解释：合并数组 = [1,2,3] ，中位数 2
 示例 2：

 输入：nums1 = [1,2], nums2 = [3,4]
 输出：2.50000
 解释：合并数组 = [1,2,3,4] ，中位数 (2 + 3) / 2 = 2.5

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/median-of-two-sorted-arrays
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class FindTwoAscArray {
    public static void main(String[] args) {
//        System.out.println(findMedianSortedArrays(new int[]{1,2,4},new int[]{3}));
        System.out.println(10 & 2);
    }

    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int kLength = nums1.length;
        int nLength = nums2.length;
        int[] total = new int[nums1.length+nums2.length];
        int k = 0;
        int n = 0;
        for(int i=0;i<total.length;i++){
            if(k >= kLength){
                total[i] = nums2[n];
                n++;
            }
            else if(n >= nLength){
                total[i] = nums1[k];
                k++;
            }
            else{
                total[i] = Math.min(nums1[k],nums2[n]);
                if (nums1[k] <= nums2[n]){
                    k++;
                }
                else{
                    n++;
                }


            }
        }
        if((total.length & 2) != 2){
            return total[total.length/2];
        }
        else{
            int c = (total.length-1)/2;
            double a = total[c],b = total[c+1];
            return (a+b)/2;
        }
    }
}
