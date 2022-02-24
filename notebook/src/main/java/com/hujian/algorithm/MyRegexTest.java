package com.hujian.algorithm;

public class MyRegexTest {
    public static void main(String[] args) {
        System.out.println(isMatch("aa","a"));
    }


    //利用筒的方式
    public static boolean isMatch(String s, String p) {
        int pLength = p.length();
        int sLength = s.length();
        //连续匹配模式开关
        boolean lianxupipei = false;
        char pipeizifu = 'a';
        //标记s的字符串到哪了
        int pOffset = 0;
        int sOffset = 0;
        while (sOffset < sLength) {
            //如果模式先到边界
            if (pOffset == pLength){
                return sLength ==sOffset;
            }
            if (lianxupipei){
                //此处开启连续匹配，所以需要返回上个字符串匹配
                char ssChar = s.charAt(sOffset);
                //遇到没有匹配上的关闭开关
                if(ssChar != pipeizifu){
                    pOffset++;
                    lianxupipei = false;
                }
                sOffset++;
                if (sOffset == sLength)pOffset++;
                continue;
            }
            char sChar = s.charAt(sOffset);
            char pChar = p.charAt(pOffset);

            //检查pChar下一位是否为*,则开启连续匹配模式
            if (pOffset != pLength-1 && p.charAt(pOffset+1) == '*'){
                lianxupipei = true;
                pipeizifu = pChar;
                pOffset++;
                continue;
            }
            if (pChar == '.' || pChar == sChar){
                pOffset ++;
                sOffset ++;
            }
        }
        //当S字符串消耗完需要检查匹配模式是否被消耗完
        for (int i = pOffset; i < pLength; i++) {
            //是.继续循环
            char nowChar = p.charAt(i);
            //检查下一位是否*
            if (pOffset != pLength-1){
                char nextChar =p.charAt(i+1);
                if (nextChar == '*'|| nextChar == '.'){
                    i++;
                    continue;
                }
                else{
                    return false;
                }
            }
            if (nowChar == '.'){
                continue;
            }
            else{
                return false;
            }
        }
        return true;
    }

}
