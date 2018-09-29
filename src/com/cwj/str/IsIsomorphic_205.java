package com.cwj.str;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Created by cwj on 18-9-7.
 * 205. 同构字符串
 * 题目描述提示帮助提交记录社区讨论阅读解答
 * 给定两个字符串 s 和 t，判断它们是否是同构的。
 * <p>
 * 如果 s 中的字符可以被替换得到 t ，那么这两个字符串是同构的。
 * <p>
 * 所有出现的字符都必须用另一个字符替换，同时保留字符的顺序。两个字符不能映射到同一个字符上，但字符可以映射自己本身。
 * <p>
 * 示例 1:
 * <p>
 * 输入: s = "egg", t = "add"
 * 输出: true
 * 示例 2:
 * <p>
 * 输入: s = "foo", t = "bar"
 * 输出: false
 * 示例 3:
 * <p>
 * 输入: s = "paper", t = "title"
 * 输出: true
 * 说明:
 * 你可以假设 s 和 t 具有相同的长度。
 */
public class IsIsomorphic_205 {
    public boolean isIsomorphic(String s, String t) {
        Map<Character, Integer> map1 = new HashMap<>();
        Map<Character, Integer> map2 = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            if (map1.containsKey(s.charAt(i))){
                if(!map2.containsKey(t.charAt(i)))
                    return false;
                else if (!map1.get(s.charAt(i)).equals(map2.get(t.charAt(i))))
                    return false;
            }

            if (map2.containsKey(t.charAt(i))){
                if (!map1.containsKey(s.charAt(i)))
                    return false;
                else if (!map2.get(t.charAt(i)).equals(map1.get(s.charAt(i))))
                    return false;
            }

            map1.put(s.charAt(i), i);
            map2.put(t.charAt(i), i);
        }
        return true;
    }

    public boolean isIsomorphic2(String s, String t) {
        int[] map1 = new int[128];
        int[] map2 = new int[128];

        for (int i = 0; i < s.length(); i++) {
            map1[s.charAt(i)] = i;
            map2[t.charAt(i)] = i;
        }

        for (int i = 0; i < s.length(); i++) {
            if (map1[s.charAt(i)] != map2[t.charAt(i)])
                return false;
        }
        return true;
    }

    /**
     *
     * 290. 单词模式
     题目描述提示帮助提交记录社区讨论阅读解答
     给定一种 pattern(模式) 和一个字符串 str ，判断 str 是否遵循相同的模式。

     这里的遵循指完全匹配，例如， pattern 里的每个字母和字符串 str 中的每个非空单词之间存在着双向连接的对应模式。

     示例1:

     输入: pattern = "abba", str = "dog cat cat dog"
     输出: true
     示例 2:

     输入:pattern = "abba", str = "dog cat cat fish"
     输出: false
     示例 3:

     输入: pattern = "aaaa", str = "dog cat cat dog"
     输出: false
     示例 4:

     输入: pattern = "abba", str = "dog dog dog dog"
     输出: false
     说明:
     你可以假设 pattern 只包含小写字母， str 包含了由单个空格分隔的小写字母
     */
    public boolean wordPattern(String pattern, String str) {
        String[] s = str.split(" ");
        Map<Character, Integer> map1 = new HashMap<>();
        Map<String, Integer> map2 = new HashMap<>();
        for (int i = 0; i < pattern.length(); i++) {
            if (map1.containsKey(pattern.charAt(i))){
                if(!map2.containsKey(s[i]))
                    return false;
                else if (!map1.get(pattern.charAt(i)).equals(map2.get(s[i])))
                    return false;
            }

            if (map2.containsKey(s[i])){
                if (!map1.containsKey(pattern.charAt(i)))
                    return false;
                else if (!map2.get(s[i]).equals(map1.get(pattern.charAt(i))))
                    return false;
            }

            map1.put(pattern.charAt(i), i);
            map2.put(s[i], i);
        }
        return true;
    }

    public static void main(String[] args) {
        String[] str = "aa bb cc".split(" ");
    }
}
