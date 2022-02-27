package com.hujian.algorithm;

/**
 *  扩散法
 *  https://leetcode-cn.com/problems/longest-palindromic-substring/
 *
 *给你一个字符串 s，找到 s 中最长的回文子串。
 *
 * '.' 匹配任意单个字符
 * '*' 匹配零个或多个前面的那一个元素
 * 所谓匹配，是要涵盖 整个 字符串 s的，而不是部分字符串。
 *
 * 例如aa -> aa，
 *
 */
public class Palindrome {
    public static void main(String[] args) {
        System.out.println(longestPalindrome("babad"));
    }

    //abcbd
    public static String longestPalindrome(String s) {
        int totalLength = s.length();
        if (totalLength <2){
            return s;
        }
        int maxLength = 1;
        int maxStart = 0;
        for(int i=0;i <totalLength;i++){
            char c = s.charAt(i);
            int length = 1;
            int R = i+1;
            int L = i-1;
            //先向左扩散，直到找到和c一样的字符
            while (L >= 0 && c == s.charAt(L)){
                L--;
                length++;
            }
            //再向右扩散，直到找到和c一样的
            while (R < totalLength && s.charAt(i) == s.charAt(R)){
                R++;
                length++;
            }
            //两边扩撒
            while(L >= 0 && R <totalLength && s.charAt(L) == s.charAt(R)){
                L--;
                R++;
                length+=2;
            }
            if (length >maxLength){
                maxStart = L;
                maxLength = length;
            }
        }
        //最后得到的长度从L开始截取，因为长度
        return s.substring(maxStart+1,maxLength+maxStart+1);
    }
}
