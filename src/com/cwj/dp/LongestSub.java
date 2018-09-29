package com.cwj.dp;

/**
 * Created by cwj on 18-8-29.
 * <p>
 * 题目1：如果字符串一的所有字符按其在字符串中的顺序出现在另外一个字符串二中，则字符串一称之为字符串二的子串。
 * <p>
 * 注意，并不要求子串（字符串一）的字符必须连续出现在字符串二中。请编写一个函数，输入两个字符串，求它们的最长公共子串，并打印出最长公共子串（不连续）。
 * <p>
 * 例如：输入两个字符串BDCABA和ABCBDAB，字符串BCBA和BDAB都是是它们的最长公共子序列，则输出它们的长度4，并打印任意一个子序列。
 */
public class LongestSub {
    public static String longestSub(String s1, String s2) {
        int[][] maxLen = new int[s1.length() + 1][s2.length() + 1];
        int[][] flag = new int[s1.length() + 1][s2.length() + 1];

        for (int i = 1; i <= s1.length(); i++) {
            for (int j = 1; j <= s2.length(); j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    maxLen[i][j] = maxLen[i - 1][j - 1] + 1;
                    flag[i][j] = 1;
                } else if (maxLen[i - 1][j] > maxLen[i][j - 1]) {
                    maxLen[i][j] = maxLen[i - 1][j];
                    flag[i][j] = 2;
                } else {
                    maxLen[i][j] = maxLen[i][j - 1];
                    flag[i][j] = 3;
                }
            }
        }
        int i = s1.length();
        int j = s2.length();
        StringBuilder res = new StringBuilder();
        while (i > 0 && j > 0) {
            if (flag[i][j] == 1) {
                res.append(s1.charAt(i - 1));
                i--;
                j--;
            } else if (flag[i][j] == 2)
                i--;
            else if (flag[i][j] == 3)
                j--;
        }
        return res.reverse().toString();
    }


    /**
     * 二、计算两个字符串的最大公共子串（Longest Common Substring）的长度，字符不区分大小写。
     * 输入两个字符串，输出一个整数。例如：asdfas werasdfaswer，输出6.这里的最大公共字串要求的字串是连续的。
     * 思路：方法和不连续的差不多，只不过是s1[i] != s2[j]的时候，maxLen[i][j]置0即可
     * 因为是连续的，所以我们只要最大的那个位置即可，不需要再用一个flag数组来记住位置
     */
    public static String longestSub2(String s1, String s2) {
        int[][] maxLen = new int[s1.length() + 1][s2.length() + 1];
        int max = 0;

        for (int i = 1; i <= s1.length(); i++) {
            for (int j = 1; j <= s2.length(); j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    maxLen[i][j] = maxLen[i - 1][j - 1] + 1;
                    max = Math.max(maxLen[i][j], max);
                } else
                    maxLen[i][j] = 0;//这句有没有无所谓，初始化就是0
            }
        }

        String res = "";
        for (int i = 1; i <= s1.length(); i++) {
            for (int j = 1; j <= s2.length(); j++) {
                if (maxLen[i][j] == max) {
                    if (i - max + 1 >= 0) {
                        res = s1.substring(i - max, i);
                    }
                }
            }
        }
        System.out.println(max);
        return res;
    }

    public static void main(String[] args) {
        String s1 = "abcbdab";
        String s2 = "bdcbda";
//        System.out.println(longestSub(s1, s2));
        System.out.println(longestSub2(s1, s2));
    }

}
