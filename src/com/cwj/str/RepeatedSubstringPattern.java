package com.cwj.str;

/**
 * Created by cwj on 18-4-2.
 * Input: "abab"
 * Output: True
 * Explanation: It's the substring "ab" twice.
 * Example 2:
 * Input: "aba"
 * Output: False
 * Example 3:
 * Input: "abcabcabcabc"
 * Output: True
 * Explanation: It's the substring "abc" four times. (And the substring "abcabc" twice.)
 */
public class RepeatedSubstringPattern {
    public boolean repeatedSubstringPattern(String s) {
        if (s == null || s.length() < 2)
            return false;
        int n = s.length();
        StringBuilder s1 = new StringBuilder(s);
        for (int i = n / 2; i > 0; --i) {
            if (n % i == 0){
                StringBuilder sub = new StringBuilder();
                for (int j = 0; j < n / i; j++) {
                    sub.append (s1.substring(0, i));
                }
                if (sub.equals(s1))
                    return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        String str = "abab";
        RepeatedSubstringPattern repeatedSubstringPattern = new RepeatedSubstringPattern();
        repeatedSubstringPattern.repeatedSubstringPattern(str);
    }
}
