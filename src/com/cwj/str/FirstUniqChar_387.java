package com.cwj.str;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by cwj on 18-9-4.
 * 387. 字符串中的第一个唯一字符
 * 首先想到用map
 * 虽然能AC但是用了额外空间
 */
public class FirstUniqChar_387 {
    public static int firstUniqChar(String s) {
        int index = Integer.MAX_VALUE;
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            if (map.containsKey(s.charAt(i))){
                map.put(s.charAt(i), -1);
            }else{
                map.put(s.charAt(i), i);
            }
        }

        for (Integer num : map.values()) {
            if(num != -1)
                index = Math.min(index, num);
        }
        return index == Integer.MAX_VALUE ? -1 : index;
    }

    /**
     * 注意一个问题，String.lastIndexOf(ch) == String.indexOf(ch) 则说明这个字符只出现一次
     */
    public static int firstUniqChar2(String s) {
        int res = -1;
        for (char c = 'a'; c < 'z'; c++) {
            int index = s.indexOf(c);
            if (index != -1 && index == s.lastIndexOf(c)){
                res = res == -1 ? index : Math.min(res, index);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(firstUniqChar("loveleetcode"));
    }
}
