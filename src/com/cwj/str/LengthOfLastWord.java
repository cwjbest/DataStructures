package com.cwj.str;

/**
 * Created by cwj on 18-4-8.
 * 给定一个字符串， 包含大小写字母、空格 ' '，请返回其最后一个单词的长度。
 * 如果不存在最后一个单词，请返回 0 。
 * 注意事项：一个单词的界定是，由字母组成，但不包含任何的空格。
 * 案例:
 * 输入: "Hello World"
 * 输出: 5
 */
public class LengthOfLastWord {
    public int lengthOfLastWord(String s) {
        int length = 0;
        int preLength = length;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ' '){
                length = 0;
            }else {
                length++;
                preLength = length;
            }
        }
        return preLength;
    }

    public int lengthOfLastWord1(String s) {
        int length = 0;
        int flag = 0;
        for (int i = s.length()-1; i >= 0; --i) {
            if ((flag == 1) && s.charAt(i) == ' '){
                break;
            }else {
                flag = 1;
                length++;
            }
        }
        return length;
    }
}
