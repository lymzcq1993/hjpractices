package com.hujian.algorithm;

import java.util.HashMap;
import java.util.Map;

/**
 * 76. 最小覆盖子串
 * https://leetcode-cn.com/problems/minimum-window-substring/
 * @author hujian
 * @since 2022-03-25 16:41
 */
public class MinWindow76 {
    public static void main(String[] args) {
        System.out.println(minWindow("aa","aa"));
    }

    /**
     * 使用滑块算法。使用两个指针进行滑动
     * @param s
     * @param t
     * @return
     */
    public static String minWindow(String s, String t) {
        Map<Character,Integer> map = new HashMap<>();
        //将所有要查找的字母存字典
        for(char c:t.toCharArray()){
            if(map.containsKey(c)){
                map.put(c,map.get(c)+1);
            }
            else{
                map.put(c,1);
            }
        }
        int needCount = t.length();
        int left = 0;
        int right;
        String str = "";
        //开始进行判断，先移动right
        for(right = 0;right<s.length();right++){
            char a = s.charAt(right);
            if(map.containsKey(a)){
                map.put(a,map.get(a)-1);
                //每有一个字母>=0说明包含了所有的重复字母
                if(map.get(a) >= 0){
                    needCount --;
                }
                //包含全部字母
                if(needCount == 0){
                    //已经找到了目标，缩小left
                    for(int j = left ;j <=right;j++){
                        char d = s.charAt(j);
                        if(map.containsKey(d)){
                            int l = map.get(d)+1;
                            map.put(d,l);
                            //主要是为了消除重复的字母，缩短距离
                            if(l > 0){
                                needCount++;
                                String tmp = s.substring(j,right+1);
                                if(str.isEmpty() ||
                                        tmp.length() < str.length()){
                                    str = tmp;
                                }
                                left = j+1;
                                break;
                            }
                        }
                    }
                }
            }
        }
        return str;

    }
}
