package com.cwj.dp;

/**
 * Created by cwj on 18-8-18.
 * 53. 最大子序和
 题目描述提示帮助提交记录社区讨论阅读解答
 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。

 示例:

 输入: [-2,1,-3,4,-1,2,1,-5,4],
 输出: 6
 解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
 进阶:

 如果你已经实现复杂度为 O(n) 的解法，尝试使用更为精妙的分治法求解。
 设nums[i]为子序列的起点，如果nums[i]为负数，则它不可能是起点，因为任何一个以nums[i]为起点的序列都可以将nums[i]剔除。
 同样，总和为负数的序列也不是最大子序列的前缀。
 一定注意负数中参杂0的情况，这个时候返回的应该是0，而不是最大的那个负数，
 所以第二个if判断对每个数都要做，不然就会漏掉0的情况
 */
public class MaxSubArray {
    public static int maxSubArray(int[] nums){
        int sum = 0;
        int maxSum = nums[0];
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (sum > maxSum)
                maxSum = sum;
            if (sum < 0)
                sum = 0;
        }
        return maxSum;
    }
}
