package com.cwj.str;

import java.util.*;

/**
 * Created by cwj on 18-8-24.
 * 49. 字母异位词分组
 给定一个字符串数组，将字母异位词组合在一起。字母异位词指字母相同，但排列不同的字符串。

 示例:

 输入: ["eat", "tea", "tan", "ate", "nat", "bat"],
 输出:
 [
 ["ate","eat","tea"],
 ["nat","tan"],
 ["bat"]
 ]
 说明：

 所有输入均为小写字母。
 不考虑答案输出的顺序。

 思路1：将str排序，这样，变形词就会相同,然后用map来存储
 */
public class GroupAnagrams {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, ArrayList<String>> map = new HashMap<>();
        for (String str:strs) {
            char[] chs = str.toCharArray();
            Arrays.sort(chs);
            String key = String.valueOf(chs);
            if (!map.containsKey(key)){
                map.put(key, new ArrayList<>());//key作为每一组变形词的代表
            }
            map.get(key).add(str);
        }

        List<List<String>> result = new ArrayList<>();
        for (List<String> list : map.values()) {
            result.add(list);
        }
        return result;
    }

    /**
     * 用数组来保存每个字符串的元素，放在对应下标
     * 然后互为变形词的下标数组肯定相同
     * 思想要记住，一串元素的全排列，存到对应下标的数组中，都是相同的
     *
     * @param strs
     * @return result
     */
    public List<List<String>> groupAnagrams2(String[] strs) {
        Map<String, ArrayList<String>> map = new HashMap<>();
        for (String str : strs) {
            int[] record = new int[26];
            for (char ch : str.toCharArray()) {
                record[ch-'a']++;
            }
            StringBuilder stringBuilder = new StringBuilder();
            for (int a:record) {
                stringBuilder.append(String.valueOf(a)).append("_");
            }
            String key = stringBuilder.toString();
            if (!map.containsKey(key)){
                map.put(key, new ArrayList<>());
            }
            map.get(key).add(str);
        }

        List<List<String>> result = new ArrayList<>();
        for (List<String> list : map.values()) {
            result.add(list);
        }
        return result;
    }

    /**
     * 242. 有效的字母异位词
     题目描述提示帮助提交记录社区讨论阅读解答
     给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的一个字母异位词。

     示例 1:

     输入: s = "anagram", t = "nagaram"
     输出: true
     示例 2:

     输入: s = "rat", t = "car"
     输出: false
     *
     */
    public boolean isAnagram(String s, String t) {
        if(s.length() != t.length()) return false;
        char[] chs_s = s.toCharArray();
        char[] chs_t = t.toCharArray();
        Arrays.sort(chs_s);
        Arrays.sort(chs_t);
        return String.valueOf(chs_s).equals(String.valueOf(chs_t));
    }

    public boolean isAnagram2(String s, String t){
        if (s.length() != t.length()) return false;
        int[] num_s = new int[26];
        int[] num_t = new int[26];
        for (int i=0; i<s.length(); i++) {
            num_s[s.charAt(i) - 'a']++;
            num_t[t.charAt(i) - 'a']++;
        }
        for (int i = 0; i < num_s.length; i++) {
            if (num_s[i] != num_t[i])
                return false;
        }
        return true;
    }

    /**
     *
     * 438. 找到字符串中所有字母异位词
     题目描述提示帮助提交记录社区讨论阅读解答
     给定一个字符串 s 和一个非空字符串 p，找到 s 中所有是 p 的字母异位词的子串，返回这些子串的起始索引。

     字符串只包含小写英文字母，并且字符串 s 和 p 的长度都不超过 20100。

     说明：

     字母异位词指字母相同，但排列不同的字符串。
     不考虑答案输出的顺序。
     示例 1:

     输入:
     s: "cbaebabacd" p: "abc"

     输出:
     [0, 6]

     解释:
     起始索引等于 0 的子串是 "cba", 它是 "abc" 的字母异位词。
     起始索引等于 6 的子串是 "bac", 它是 "abc" 的字母异位词。
     示例 2:

     输入:
     s: "abab" p: "ab"

     输出:
     [0, 1, 2]

     解释:
     起始索引等于 0 的子串是 "ab", 它是 "ab" 的字母异位词。
     起始索引等于 1 的子串是 "ba", 它是 "ab" 的字母异位词。
     起始索引等于 2 的子串是 "ab", 它是 "ab" 的字母异位词。
     *
     * 思路：两个map加滑动窗口
     *
     */
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> res = new ArrayList<>();
        Map<Character, Integer> map_p = new HashMap<>();
        Map<Character, Integer> map_s = new HashMap<>();
        for (char ch:p.toCharArray()) {
            map_p.put(ch, map_p.getOrDefault(ch, 0) + 1);
        }

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            char left;
            map_s.put(ch, map_s.getOrDefault(ch, 0) + 1);
            if (i >= p.length()){
                left = s.charAt(i - p.length());
                if (map_s.get(left) == 1)
                    map_s.remove(left);
                else
                    map_s.put(left, map_s.get(left)-1);
            }

            if (map_p.equals(map_s))
                res.add(i-p.length()+1);
        }
        return res;
    }
}
