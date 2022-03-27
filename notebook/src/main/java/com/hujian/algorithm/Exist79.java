package com.hujian.algorithm;

/**
 * 79. 单词搜索
 * https://leetcode-cn.com/problems/word-search/
 */
public class Exist79 {
    public static void main(String[] args) {
        char[] chars1 = {'A', 'A', 'A', 'A','A','A'};
        char[] chars2 = {'A', 'A', 'A', 'A','A','A'};
        char[] chars3 = {'A', 'A', 'A', 'A','A','A'};
        char[] chars4 = {'A', 'A', 'A', 'A','A','A'};
        char[] chars5 = {'A', 'A', 'A', 'A','A','B'};
        char[] chars6 = {'A', 'A', 'A', 'A','B','A'};
        System.out.println(exist(new char[][]{chars1,chars2,chars3,chars4,chars5,chars6},"ABCEFSADEESE"));
    }

    /**
     * for循环找到起点位置。使用一个布尔数组来记录是否被使用过
     * 这里的一个难点是我怎么重置被使用过的标记，
     * 实际上是上下左右都没有查询到之后就可以重置标记了。。这里我想多了还准备使用深拷贝的数组
     * @param board
     * @param word
     * @return
     */
    public static boolean exist(char[][] board, String word) {
        int len1 = board.length;
        int len2 = board[0].length;
        boolean[][] b = new boolean[len1][len2];
        for(int i = 0;i<len1;i++){
            for(int j=0;j<len2;j++){
                //开始回溯查找
                if(word.charAt(0) == board[i][j]){
                    if(word.length() ==1){
                        return true;
                    }
                    //需要记录哪些字母被使用过
                    if(back(i,j,board,word,b,0)){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    static boolean back(int i,int j,char[][] board,String word,boolean[][] b,int cur){
        if( b[i][j])return false;
        if(board[i][j] == word.charAt(cur)){
            if(cur == word.length()-1)return true;
            cur++;
        }
        else {
            return false;
        }
        b[i][j] = true;
        //从第二个字母开始查找,找右边
        if(j< board[0].length-1){
            boolean flag = back(i,j+1,board,word,b,cur);
            if(flag)return true;
        }
        //从下方开始找
        if(i<board.length-1){
            boolean flag = back(i+1,j,board,word,b,cur);
            if(flag)return true;
        }
        //从左边开始找
        if(j>0){
            boolean flag = back(i,j-1,board,word,b,cur);
            if(flag)return true;
        }
        //从上边开始找
        if(i>0){
            boolean flag = back(i-1,j,board,word,b,cur);
            if(flag)return true;
        }
        b[i][j] = false;
        return false;
    }

}
