package com.cwj.dp;

/**
 * Created by cwj on 18-9-14.
 * 给定两个单词 word1 和 word2，计算出将 word1 转换成 word2 所使用的最少操作数 。

 你可以对一个单词进行如下三种操作：

 插入一个字符
 删除一个字符
 替换一个字符
 示例 1:

 输入: word1 = "horse", word2 = "ros"
 输出: 3
 解释:
 horse -> rorse (将 'h' 替换为 'r')
 rorse -> rose (删除 'r')
 rose -> ros (删除 'e')
 示例 2:

 输入: word1 = "intention", word2 = "execution"
 输出: 5
 解释:
 intention -> inention (删除 't')
 inention -> enention (将 'i' 替换为 'e')
 enention -> exention (将 'n' 替换为 'x')
 exention -> exection (将 'n' 替换为 'c')
 exection -> execution (插入 'u')

 思路：任意两个字符串都可以分为两种情况，末位相同或不相同
 我们用dp[i][j]表示s1到i，s2到j的最短编辑距离
 如果末位相同：dp[i][j] = dp[i-1][j-1];
 如果末位不同：1.向s1插入s2的末位字符 dp[i][j] = dp[i][j-1] + 1;
              2.向s2插入s1的末位字符 dp[i][j] = dp[i-1][j] + 1;
              3.替换s1,或者s2的末位字符 dp[i][j] = dp[i-1][j-1] + 1
 dp[i][j]应取这三者的最小值。
 考虑 s1=s，s2=tsss这种长度不一的情况，dp[i][0] = i,dp[0][j] = j
 */
public class EditDistance_72 {
    public int minDistance(String word1, String word2) {
        if (word1.equals(word2))
            return 0;
        if (word1.equals(" ") && !word2.equals(" "))
            return word2.length();
        if (word2.equals(" ") && !word1.equals(" "))
            return word1.length();
        int rows = word1.length();
        int cols = word2.length();
        int[][] dp = new int[rows+1][cols+1];
        for (int i = 0; i <= rows; i++) {
            dp[i][0] = i;
        }
        for (int i = 0; i <= cols ; i++) {
            dp[0][i] = i;
        }
        for (int i = 1; i <= rows ; i++) {
            for (int j = 1; j <= cols ; j++) {
                if (word1.charAt(i-1) == word2.charAt(j-1))
                    dp[i][j] = dp[i-1][j-1];
                else
                    dp[i][j] = Math.min(Math.min(dp[i-1][j], dp[i][j-1]), dp[i-1][j-1]) + 1;
            }
        }
        return dp[rows][cols];
    }
}
