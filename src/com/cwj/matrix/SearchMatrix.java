package com.cwj.matrix;

/**
 * Created by cwj on 18-3-25.
 *  检查数组是否为空的三种条件
 * 一是数组首地址是否为空
 * 二是是否为{}，也就是array.length==0的情况
 * 三是{{}}，这时array.length=1，但是array[0].length==0。满足任意一个条件就可以返回false了。
 */
public class SearchMatrix {
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || (matrix.length == 1) && (matrix[0].length == 0)) {
            return false;
        }
        int row = matrix.length;
        int col = matrix[0].length;
        if (target < matrix[0][0] && target > matrix[row-1][col-1]){
            return false;
        }
        int left = 0;
        int right = row * col - 1;
        while (left <= right){
            int mid = (row + right) / 2;
            if (matrix[mid/col][mid%col] == target){
                return true;
            }else if (matrix[mid/col][mid%col] > target){
                left = mid + 1;
            }else {
                right = mid - 1;
            }
        }
        return false;
    }
}
