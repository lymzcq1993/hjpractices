package com.hujian.algorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * 22. 括号生成
 * https://leetcode-cn.com/problems/generate-parentheses/
 *
 * 数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：n = 3
 * 输出：["((()))","(()())","(())()","()(())","()()()"]
 * 示例 2：
 *
 * 输入：n = 1
 * 输出：["()"]
 *  
 *
 * 提示：
 *
 * 1 <= n <= 8
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/generate-parentheses
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class GenerateParenthesis {
    public static void main(String[] args) throws InterruptedException {
        System.out.println(generateParenthesis(3));
        Thread.sleep(99999999);
    }

    /**
     * 实在想不出来看了评论区的思路。。
     * 回溯算法，先将括号的最大可能计算出来，假设n=3
     * 则第一次循环count1=0-3和count2=0-3均达到最多的点，即((())))
     * 第二次return到减去一个括号，count1= 2->3,count2=0-3  (())())
     * 第三次再减去  count1=1-3,count3=0->3  ()(())
     * @param n
     * @return
     */
    public static List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<String>();
        generate(res, "", 0, 0, n);
        return res;
    }

    //1 c1=3  c2=0->3
    //2 c1=2  c2=0->2
    //1 c1=1  c1=0->1
    //如果不用C1>=c2，避免从)(开始循环
    public static void generate(List<String> list , String ans, int c1, int c2, int n){
        if(c1 > n || c2 > n){
            return;
        }
        //当括号数相等的时候增加
        if(c1 == n && c2 == n) list.add(ans);
        if(c1 >= c2){
            String anw = ans;
            //左边一直添加左括号
            generate(list,anw+"(",c1+1,c2,n);
            //添加右括号
            generate(list,anw+")",c1,c2+1,n);
        }
    }
}
