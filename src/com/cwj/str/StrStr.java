package com.cwj.str;

/**
 * Created by cwj on 18-4-2.
 * 实现 strStr()。
 * 返回蕴含在 haystack 中的 needle 的第一个字符的索引，如果 needle 不是 haystack 的一部分则返回 -1 。
 * 例 1:
 * 输入: haystack = "hello", needle = "ll"
 * 输出: 2
 * 例 2:
 * 输入: haystack = "aaaaa", needle = "bba"
 * 输出: -1
 */
public class StrStr {
    public int strStr(String haystack, String needle) {
        if (haystack == null || needle == null ||needle.length() > haystack.length())
            return -1;
        for (int i = 0; i <= haystack.length() - needle.length(); i++) {
            int j;
            for (j = 0; j < needle.length(); j++) {
                if (haystack.charAt(j) != needle.charAt(j)){
                    break;
                }
            }
            if (j == needle.length())
                return i;
        }
        return -1;
    }
}
