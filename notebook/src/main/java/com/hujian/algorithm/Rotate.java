package com.hujian.algorithm;

/**
 * 48. 旋转图像
 * https://leetcode-cn.com/problems/rotate-image/
 *
 *
 * 这里用了取巧的方法。。用一个数组进行暂存，然后再找出变化后的数组的规律
 * 例如 [1][2]   [3][4]   ->   [3][1]  [4][2]
 * 第一个元素变成每个数组的最后一个，第二个元素变成每个数组的倒数第二个...
 * 因此有规律  array[j][len-i-1] = matrix[i][j];
 */
public class Rotate {

    public void rotate(int[][] matrix) {
        int len = matrix.length;
        int[][] array = new int[len][len];
        for(int i=0;i<len;i++){
            for(int j=0;j<len;j++){
                array[j][len-i-1] = matrix[i][j];
            }
        }
        for(int k=0;k<len;k++){
            matrix[k] = array[k].clone();
        }
    }
}
