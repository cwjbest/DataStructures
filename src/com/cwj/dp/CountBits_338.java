package com.cwj.dp;

/**
 * 338. 比特位计数
 题目描述提示帮助提交记录社区讨论阅读解答
 给定一个非负整数 num。对于 0 ≤ i ≤ num 范围中的每个数字 i ，计算其二进制数中的 1 的数目并将它们作为数组返回。

 示例 1:

 输入: 2
 输出: [0,1,1]
 示例 2:

 输入: 5
 输出: [0,1,1,2,1,2]
 进阶:

 给出时间复杂度为O(n*sizeof(integer))的解答非常容易。但你可以在线性时间O(n)内用一趟扫描做到吗？
 要求算法的空间复杂度为O(n)。

 思路：既然 n&(n-1)这个数的1的个数+1 = n的1的个数，那么地推公式为
 dp[n] = dp[n & (n-1)] + 1
 */
class CountBits_338{
    public int[] countBits(int nums){
        int[] dp = new int[nums+1];
        for (int i = 1; i <= nums; i++) {
            dp[i] = dp[i & (i-1)] + 1;
        }
        return dp;
    }
}
