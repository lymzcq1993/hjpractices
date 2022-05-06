package com.hujian.algorithm.shixiansuanfa;

/**
 * 221. 最大正方形
 * https://leetcode-cn.com/problems/maximal-square/
 * @author hujian
 */
public class MaximalSquare221 {
    public static void main(String[] args) {

        char[] a1 = new char[]{'1','1','1','1','1','1','1','1'};
        char[] a2 = new char[]{'1','1','1','1','1','1','1','0'};
        char[] a3 = new char[]{'1','1','1','1','1','1','1','0'};
        char[] a4 = new char[]{'1','1','1','1','1','0','0','0'};
        char[] a5 = new char[]{'0','1','1','1','1','0','0','0'};

        char[][] aa =new char[5][8];
        aa[0] = a1;
        aa[1] = a2;
        aa[2] = a3;
        aa[3] = a4;
        aa[4] = a5;
        int square = maximalSquare(aa);
        System.out.println(square);
    }

    /**
     * 暴力法
     * 通过每次找到正方形的左上角，循环次数为  以该点为原点向右边和下边发散两者比较最小
     * 每次循环记录当前的围成的最大面积，否则break
     * @param matrix
     * @return
     */
    public static int maximalSquare(char[][] matrix) {
        int res = 0;
        if(matrix.length == 0 || matrix[0].length == 0){
            return res;
        }
        //定义变量列或者行
        int column = matrix[0].length,rows = matrix.length;
        for(int i =0;i<rows;i++){
            for(int j=0;j<column;j++){
                //如果遇到1可以开始计算
                if (matrix[i][j] == '1'){
                    res = Math.max(res,1);
                    int maxSize = Math.min(rows - i,column -j);
                    for( int  k =1;k<maxSize;k++){
                        boolean flag =true;
                        //斜对角如果是0，则直接break;
                        if ( matrix[i+k][j+k] == '0'){
                            break;
                        }
                        //向左边和右边新增一行判断是否都等于1
                        //如果都等于1则记录这次的面积，如果有一个不等于1则说明此次得不到最大面积
                        for( int l = 0;l<k;l++){
                            //判断下面一行
                            if(matrix[i+k][j+l] == '0'
                                    //判断右边一列
                              || matrix[i+l][j+k] == '0'){
                                //跳出循环并比较为false
                                flag = false;
                                break;
                            }
                        }
                        if (flag){
                            res = Math.max(res,k+1);
                        }
                        else {
                            break;
                        }
                    }

                }
            }
        }
        return res * res;
    }

}
