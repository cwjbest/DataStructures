package com.cwj.dp;

/**
 * Created by cwj on 18-9-10.
 * 322. 零钱兑换
 题目描述提示帮助提交记录社区讨论阅读解答
 给定不同面额的硬币 coins 和一个总金额 amount。编写一个函数来计算可以凑成总金额所需的最少的硬币个数。
 如果没有任何一种硬币组合能组成总金额，返回 -1。

 示例 1:

 输入: coins = [1, 2, 5], amount = 11
 输出: 3
 解释: 11 = 5 + 5 + 1
 示例 2:

 输入: coins = [2], amount = 3
 输出: -1
 说明:
 你可以认为每种硬币的数量是无限的。

 dp[i] = min(dp[i], dp[i-coins[j]] + 1)

 */
public class CoinChange_322 {
    public static int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount+1];
        for (int i = 1; i <= amount ; i++) {
            dp[i] = amount+1;
            for (int j = 0; j < coins.length; j++) {
                if (coins[j] <= i)
                    dp[i] = Math.min(dp[i], dp[i-coins[j]]+1);
            }
        }
        return dp[amount] == amount+1 ? -1 : dp[amount];
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 5};
        int amount = 11;
        coinChange(nums, amount);
    }
}
