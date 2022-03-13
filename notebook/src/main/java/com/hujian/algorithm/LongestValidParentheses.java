package com.hujian.algorithm;

import java.util.Stack;

/**
 * 最长有效括号
 *https://leetcode-cn.com/problems/longest-valid-parentheses/comments/
 *
 * 给你一个只包含 '(' 和 ')' 的字符串，找出最长有效（格式正确且连续）括号子串的长度。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：s = "(()"
 * 输出：2
 * 解释：最长有效括号子串是 "()"
 * 示例 2：
 *
 * 输入：s = ")()())"
 * 输出：4
 * 解释：最长有效括号子串是 "()()"
 * 示例 3：
 *
 * 输入：s = ""
 * 输出：0
 */
public class LongestValidParentheses {
    public static void main(String[] args) {
        System.out.println(longestValidParentheses(")()())"));
    }

    /**
     * 空间复杂度较高，原理是用一个数组将多出来的左括号和右括号标记为1，然后数组初始都是0，为1的就是断点的地方
     * 最后计算最长的连续0
     * @param s
     * @return
     */
    public static int longestValidParentheses(String s) {
        int length = s.length();
        int[] array = new int[length];
        int ans = 0;
        int len = 0;
        Stack<Integer> stack = new Stack<>();
        //此循环把所有多出来的")"位置标记为1
        for(int i=0;i<length;i++){
            char cur = s.charAt(i);
            if(cur == '('){
                stack.push(i);
            }
            else{
                if(stack.isEmpty()){
                    array[i] = 1;
                }
                else{
                    stack.pop();
                }
            }
        }
        //拿出栈剩余的"("括号，则是多余的
        while(!stack.isEmpty()){
            array[stack.pop()] = 1;
        }
        // 寻找标记与标记之间的最大长度
        for(int i = 0; i < length; i++) {
            if(array[i] == 1) {
                len = 0;
                continue;
            }
            len++;
            ans = Math.max(ans, len);
        }
        return ans;    }
}
