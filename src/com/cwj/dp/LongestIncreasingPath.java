package com.cwj.dp;

/**
 * Created by cwj on 18-8-26.
 * 329. 矩阵中的最长递增路径
 给定一个整数矩阵，找出最长递增路径的长度。

 对于每个单元格，你可以往上，下，左，右四个方向移动。 你不能在对角线方向上移动或移动到边界外（即不允许环绕）。

 示例 1:

 输入: nums =
 [
 [9,9,4],
 [6,6,8],
 [2,1,1]
 ]
 输出: 4
 解释: 最长递增路径为 [1, 2, 6, 9]。
 示例 2:

 输入: nums =
 [
 [3,4,5],
 [3,2,6],
 [2,2,1]
 ]
 输出: 4
 解释: 最长递增路径是 [3, 4, 5, 6]。注意不允许在对角线方向上移动。

 思路1：暴力递归
 */
public class LongestIncreasingPath {
    public static int longestIncreasingPath(int[][] matrix) {
        int max = Integer.MIN_VALUE;
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[0].length; col++) {
                max = Math.max(max, process(matrix, row, col));
            }
        }
        return max;
    }

    //每个点都可以移动四个方向，最大路径 = 移动后的下一个点的最大路径+1，很明显是递归问题
    private static int process(int[][] matrix, int row, int col){
        int path = 1;//当前点路径为1
        if (col > 0 && matrix[row][col-1] > matrix[row][col])//向左的情况
            path = Math.max(path, process(matrix, row, col-1) + 1);//四个方向的最大值
        if (row > 0 && matrix[row-1][col] > matrix[row][col])//上
            path = Math.max(path, process(matrix, row-1, col) + 1);
        if (row < matrix.length - 1 && matrix[row+1][col] > matrix[row][col])//下
            path = Math.max(path, process(matrix, row+1, col) + 1);
        if (col < matrix[0].length - 1 && matrix[row][col+1] > matrix[row][col])//右
            path = Math.max(path, process(matrix, row, col+1) + 1);
        return path;
    }

    /**
     * 暴力递归会超时，思考这个问题
     *  [3,4,5],
        [3,9,6],
        [2,2,1]
     **3-9， 4-9， 6-9， 2-9 这四个方向而言，process（9）的值是固定不变的
     * 也就是说不论前面的路径怎么定，到9这个点时，后面的路径是固定的，这就叫无后效性问题
     * 很容易想到动态规划
     * 用一张dp表将每个点的path存起来，表和矩阵大小是一样的
     * 哪个是返回值，就把这个值用缓存存起来，之后要用的话直接拿出来
     * 这就是动态规划
     */
    public static int longestIncreasingPathDP(int[][] matrix) {
        int max = Integer.MIN_VALUE;
        int[][] dp = new int[matrix.length][matrix[0].length];
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[0].length; col++) {
                max = Math.max(max, processDP(matrix, dp, row, col));
            }
        }
        return max;
    }

    private static int processDP(int[][] matrix, int[][] dp, int row, int col){
        if (dp[row][col] == 0) {//如果这个点的路径没被算过，肯定值为0
            dp[row][col] = 1;//原地点代表路径长度为1，将path换成dp表中的值
            if (col > 0 && matrix[row][col - 1] > matrix[row][col])//向左的情况
                dp[row][col] = Math.max(dp[row][col], processDP(matrix, dp, row, col - 1) + 1);//四个方向的最大值
            if (row > 0 && matrix[row - 1][col] > matrix[row][col])//上
                dp[row][col] = Math.max(dp[row][col], processDP(matrix, dp, row - 1, col) + 1);
            if (row < matrix.length - 1 && matrix[row + 1][col] > matrix[row][col])//下
                dp[row][col] = Math.max(dp[row][col], processDP(matrix, dp, row + 1, col) + 1);
            if (col < matrix[0].length - 1 && matrix[row][col + 1] > matrix[row][col])//右
                dp[row][col] = Math.max(dp[row][col], processDP(matrix, dp, row, col + 1) + 1);
        }
        return dp[row][col];//如果算过，直接返回这个值
    }

}
