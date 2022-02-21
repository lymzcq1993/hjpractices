package com.hujian.algorithm;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;

/**
 * https://leetcode-cn.com/problems/longest-substring-without-repeating-characters/
    无重复字符的最长子串

 给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度。
 */
public class FindStringNoRepeatStr {
    public static void main(String[] args) {
        System.out.println(biaozhunAnswer("pwwkew"));
    }

    /**
     * 算法效率低第一次尝试
     * @param s
     * @return
     */
    public static int lengthOfLongestSubstring(String s) {
        int maxLength = 0;
        if (s != null && s.length() != 0){
            StringBuilder chars = new StringBuilder();
            for (int i = 0; i < s.length(); i++) {
//                StringBuilder builder = new StringBuilder();
                char c = s.charAt(i);
                if (chars.toString().contains(String.valueOf(c))){
                    maxLength = Math.max(chars.length(),maxLength);
                    //此处从上一次重复的字符串开始计算
                    i = s.substring(0,i).lastIndexOf(c);
                    chars = new StringBuilder();
                }
                else{
                    chars.append(c);
                    if (i == s.length()-1){
                        return Math.max(chars.length(), maxLength);
                    }
                }
            }
        }
        return maxLength;
    }

    /**
     * 标准答案
     * @param s
     * @return
     */
    public static int biaozhunAnswer(String s) {
        //pwwkew
        int maxLength = 0;
        Map<Character,Integer> map = new HashMap<>();
        int start = 0;
        for (int i = 0; i < s.length(); i++) {
            //获取字符串最后的起始位置
            char c = s.charAt(i);
            Integer integer = map.get(c);
            start = Math.max(integer == null?0:integer,start );
            if (map.containsKey(c)){
                maxLength = Math.max(maxLength,i-start);
            }
            else{
                maxLength = Math.max(maxLength,i-start+1);
            }

            map.put(c,i);
//            maxLength = Math.max(,maxLength-start)
        }
        return maxLength;
    }


    /**
     * 记录大佬的解法思路:
     * 1.初始化一个ASCII  127位表，用来记录每个字符最后出现的位置
     * 2.index为字符的ASCII码值
     * 3.start记录字符每次出现的最后位置
     * 4.res记录当前字符与该最后一次出现的位置中隔了多少个字符
     * pwwkew
     * @param s
     * @return
     */
    public static int dalao(String s) {
        // 记录字符上一次出现的位置
        int[] last = new int[128];
        for(int i = 0; i < 128; i++) {
            last[i] = -1;
        }
        int n = s.length();

        int res = 0;
        // 窗口开始位置
        int start = 0;
        for(int i = 0; i < n; i++) {
            int index = s.charAt(i);
            start = Math.max(start, last[index] + 1);
            res   = Math.max(res, i - start + 1);
            last[index] = i;
        }

        return res;
    }

}
