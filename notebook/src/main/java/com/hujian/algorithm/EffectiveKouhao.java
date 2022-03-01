package com.hujian.algorithm;

import java.util.*;

/**
 * @author hujian
 * @description 有效括号
 * @create 2022-02-28 11:06
 *
 * https://leetcode-cn.com/problems/valid-parentheses/
 *
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。
 *
 * 有效字符串需满足：
 *
 * 左括号必须用相同类型的右括号闭合。
 * 左括号必须以正确的顺序闭合。
 *  
 *
 * 示例 1：
 *
 * 输入：s = "()"
 * 输出：true
 * 示例 2：
 *
 * 输入：s = "()[]{}"
 * 输出：true
 * 示例 3：
 *
 * 输入：s = "(]"
 * 输出：false
 * 示例 4：
 *
 * 输入：s = "([)]"
 * 输出：false
 * 示例 5：
 *
 * 输入：s = "{[]}"
 * 输出：true
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/valid-parentheses
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class EffectiveKouhao {
    public static void main(String[] args) {
        System.out.println(isValidEffective("(sdsd)"));
    }



    /**
     * 用map存取，效率不高...思路有错，题目的字符串只包含括号，这里把字母也包含进去了
     * @see EffectiveKouhao#isValidEffective(String)
     * 以上为该思路正确方法
     * @param s
     * @return
     */
    public static boolean isValid(String s) {
        LinkedList<Character> linkedList = new LinkedList<>();
        Map<Character,Character> map = new HashMap<>();
        map.put('(',')');
        map.put('{','}');
        map.put('[',']');

        List<Character> cList =  new ArrayList<>();
        cList.add(')');
        cList.add(']');
        cList.add('}');

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            //如果队列中没有值首次碰到反括号则false
            if (linkedList.size() == 0 && cList.contains(c)){
                return false;
            }
            //如果包含则入队
            if (map.containsKey(c)){
                linkedList.add(c);
                continue;
            }
            //检验出队条件
            if (cList.contains(c)){
                //出队
                if (map.get(linkedList.getLast()).equals(c)){
                    linkedList.removeLast();
                }
                else{
                    return false;
                }
            }
        }
        return linkedList.size() == 0;
    }


    /**
     * 效率栈
     * @param s
     * @return
     */
    public static boolean isValidEffective(String s) {
        Stack<Character>stack = new Stack<Character>();
        for(char c: s.toCharArray()){
            if(c=='(') {
                stack.push(')');
            } else if(c=='[') {
                stack.push(']');
            } else if(c=='{') {
                stack.push('}');
            } else if(stack.isEmpty()||c!=stack.pop()) {
                return false;
            }
        }
        return stack.isEmpty();
    }

    /**
     * 该算法从两边开始找，从中间靠拢，但是忽略了(){}[]这种结构
     * @param s
     * @return
     */
    @Deprecated
    public static boolean isValidErr1(String s) {
        int l = s.length()-1;
        int i = 0;
        Map<Character,Character> map = new HashMap<>();
        Map<Character,Character> map2 = new HashMap<>();
        map.put('(',')');
        map.put('{','}');
        map.put('[',']');

        map2.put(')','(');
        map2.put('}','{');
        map2.put(']','[');
        boolean left = true;
        boolean right = false;
        Character curC = 'a';
        while(i < l){
            if(left){
                char lc =  s.charAt(i);
                if(map.containsKey(lc)){
                    curC = map.get(lc);
                    left = false;
                    right = true;
                    continue;
                }
                if (map2.containsKey(lc))return false;
                i++;
            }
            if(right){
                Character rc = s.charAt(l);
                if(map2.containsKey(rc)){
                    if(!rc.equals(curC)){
                        return false;
                    }
                    left = true;
                    right = false;
                    i++;
                }
                if (map.containsKey(rc)) {
                    return false;
                }
                l--;
            }
        }
        return left;
    }

}
