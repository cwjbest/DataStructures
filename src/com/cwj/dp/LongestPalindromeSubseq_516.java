package com.cwj.dp;

/**
 * Created by cwj on 18-9-26.
 * 516. 最长回文子序列
 给定一个字符串s，找到其中最长的回文子序列。可以假设s的最大长度为1000。

 示例 1:
 输入:

 "bbbab"
 输出:

 4
 一个可能的最长回文子序列为 "bbbb"。

 示例 2:
 输入:

 "cbbd"
 输出:

 2
 一个可能的最长回文子序列为 "bb"。
 */
public class LongestPalindromeSubseq_516 {
    public static int longestPalindromeSubseq(String str){
        int[][] dp = new int[str.length()][str.length()];
        for (int i = str.length()-1; i >= 0; i--) {
            dp[i][i] = 1;
            for (int j = i+1; j < str.length(); j++) {
                if (j < str.length() && str.charAt(i) == str.charAt(j))
                    dp[i][j] = dp[i+1][j-1] + 2;
                else
                    dp[i][j] = Math.max(dp[i+1][j], dp[i][j-1]);
            }
        }
        return dp[0][str.length()-1];
    }
}
