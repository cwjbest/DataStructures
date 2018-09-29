package com.cwj.str;


/**
 * Created by cwj on 18-3-29.
 * 编写一个函数来查找字符串数组中最长的公共前缀字符串。
 * 最长公共前缀的长度肯定不大于字符串数组中的最短字符串的长度，所以每次比较对应字符不必比较完全
 */
public class LongestCommonPrefix {

    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0){
            return null;
        }
        String pre = strs[0];
        for (int i = 1; i < strs.length; i++) {
            int len = pre.length() > strs[i].length() ? strs[i].length() : pre.length();
            int j;
            for (j = 0; j < len; j++) {
                if (pre.charAt(j)!=strs[i].charAt(j)){
                    break;
                }
            }
            pre = pre.substring(0, j);
        }
        return pre;
    }
}
