package com.hujian.algorithm;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 三数之和
 *https://leetcode-cn.com/problems/3sum/
 * 给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有和为 0 且不重复的三元组。
 *
 * 注意：答案中不可以包含重复的三元组。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：nums = [-1,0,1,2,-1,-4]
 * 输出：[[-1,-1,2],[-1,0,1]]
 * 示例 2：
 *
 * 输入：nums = []
 * 输出：[]
 * 示例 3：
 *
 * 输入：nums = [0]
 * 输出：[]
 *  
 *
 * 提示：
 *
 * 0 <= nums.length <= 3000
 * -105 <= nums[i] <= 105
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/3sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class ThreeNumberSum {
    public static void main(String[] args) {
        System.out.println(Arrays.equals(new int[]{1,2,3},new int[]{3,2,1}));
    }


    /**
     * -5 2  3
     * @param nums
     * @return
     */
    public static List<List<Integer>> threeSum1(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        int l = nums.length;
        //数组长度小于3直接返回
        if(l < 3){
            return list;
        }
        //对数组进行排序,这样可以判断重复的问题,三个三个的判断，因此到length-2就停止
        Arrays.sort(nums);
        for(int i = 0;i<l-2;i++){
            //找完负数就不用再找正数
            if (nums[i] >0 )return list;
            //避免重复的寻找，比如[0,0,0]
            if(i>0 && nums[i] == nums[i-1])continue;
            System.out.println(i);
            int L = i+1;
            int R = l-1;
            while (L < R){
                if (L == i)continue;
                int sum = nums[L] + nums[R] + nums[i];
                //符合条件的放入数组
                if (sum == 0){
                    List<Integer> ll = new ArrayList<>();
                    ll.add(nums[L]);
                    ll.add(nums[R]);
                    ll.add(nums[i]);
                    while(L < R &&nums[L+1] == nums[L])L++;
                    while(L < R && nums[R-1] == nums[R])R--;
                    L++;
                    R--;
                    list.add(ll);
                }
                //说明正数大了，R左移动
                if (sum  > 0){
                    R--;
                }
                //说明负数大了，L右移动
                if (sum < 0){
                    L++;
                }
            }
        }
        return list;    }

    /**
     * 此算法无法避免重复的问题，并且重复的细节非常多。。遂放弃
     * @param nums
     * @return
     */
    @Deprecated
    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        int l = nums.length;
        if(l < 3){
            return list;
        }
        for(int i = 0;i<l;i++){
            Map<Integer,Integer> map = new HashMap<>();
            int sum = nums[i];
            for(int j = i+1;j<l-1;j++){
                int x = -sum - nums[j];
                System.out.println(nums[i]+" "+nums[j]+" "+nums[j+1]);
                if(x == nums[j+1]){
                    List<Integer> ll = new ArrayList<>();
                    ll.add(nums[i]);
                    ll.add(nums[j]);
                    ll.add(x);
                    list.add(ll);
                }
                else{
                    map.put(x,j+1);
                }
            }
        }
        List<List<Integer>> list1 = list.stream().distinct().collect(Collectors.toList());
        return list1;
    }
}
