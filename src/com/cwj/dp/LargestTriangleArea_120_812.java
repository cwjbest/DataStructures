package com.cwj.dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by cwj on 18-9-10.
 * 812. 最大三角形面积
 题目描述提示帮助提交记录社区讨论阅读解答
 给定包含多个点的集合，从其中取三个点组成三角形，返回能组成的最大三角形的面积。

 示例:
 输入: points = [[0,0],[0,1],[1,0],[0,2],[2,0]]
 输出: 2
 解释:
 这五个点如下图所示。组成的橙色三角形是最大的，面积为2。


 注意:

 3 <= points.length <= 50.
 不存在重复的点。
 -50 <= points[i][j] <= 50.
 结果误差值在 10^-6 以内都认为是正确答案。
 */
public class LargestTriangleArea_120_812 {
    public double largestTriangleArea(int[][] points) {
        double res = 0;
        int len = points.length;
        for(int i=0;i<len;i++){
            for(int j=i+1;j<len;j++){
                for(int k=j+1;k<len;k++){
                    res = Math.max(res,0.5*Math.abs(points[i][0]*(points[j][1]-points[k][1])+points[j][0]*(points[k][1]-points[i][1])+points[k][0]*(points[i][1]-points[j][1])));
                }
            }
        }
        return res;
    }

    /**
     *
     * 120. 三角形最小路径和
     题目描述提示帮助提交记录社区讨论阅读解答
     给定一个三角形，找出自顶向下的最小路径和。每一步只能移动到下一行中相邻的结点上。

     例如，给定三角形：

     [
     [2],
     [3,4],
     [6,5,7],
     [4,1,8,3]
     ]
     自顶向下的最小路径和为 11（即，2 + 3 + 5 + 1 = 11）。

     说明：

     如果你可以只使用 O(n) 的额外空间（n 为三角形的总行数）来解决这个问题，那么你的算法会很加分。
     经典动态规划：dp表赋初值为最后一层的节点值，然后根据这层的值计算上一层的dp值
     dp[j] = min(dp[j], dp[j+1]) + triangle.get(i-1).get(j);
     */
    public static int minimumTotal(List<List<Integer>> triangle) {
        if (triangle.size() == 0 || triangle.get(0).size() == 0)
            return 0;
        int len = triangle.get(triangle.size()-1).size();
        int[] dp = new int[len];
        for (int i = 0; i < len; i++)
            dp[i] = triangle.get(triangle.size()-1).get(i);
        for (int i = triangle.size()-1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                 dp[j] = Math.min(dp[j], dp[j+1]) + triangle.get(i-1).get(j);
            }
        }
        return dp[0];
    }

    public static void main(String[] args) {
        List<Integer> list1 = Arrays.asList(2);
        List<Integer> list2 = Arrays.asList(3, 4);
        List<Integer> list3 = Arrays.asList(6, 5, 7);
        List<Integer> list4 = Arrays.asList(4, 1, 8, 3);
        List<List<Integer>> res = new ArrayList<>();
        res.add(list1);
        res.add(list2);
        res.add(list3);
        res.add(list4);

        minimumTotal(res);
    }
}
