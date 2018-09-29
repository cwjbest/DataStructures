package com.cwj.str;

/**
 * Created by cwj on 18-8-18.
 * 345. 反转字符串中的元音字母
 编写一个函数，以字符串作为输入，反转该字符串中的元音字母。

 示例 1:

 输入: "hello"
 输出: "holle"
 示例 2:

 输入: "leetcode"
 输出: "leotcede"
 说明:
 元音字母不包含字母"y"。

 344. 反转字符串
 题目描述提示帮助提交记录社区讨论阅读解答
 编写一个函数，其作用是将输入的字符串反转过来。

 示例 1:

 输入: "hello"
 输出: "olleh"
 示例 2:

 输入: "A man, a plan, a canal: Panama"
 输出: "amanaP :lanac a ,nalp a ,nam A"

 541. 反转字符串 II
 给定一个字符串和一个整数 k，你需要对从字符串开头算起的每个 2k 个字符的前k个字符进行反转。如果剩余少于 k 个字符，则将剩余的所有全部反转。如果有小于 2k 但大于或等于 k 个字符，则反转前 k 个字符，并将剩余的字符保持原样。

 示例:

 输入: s = "abcdefg", k = 2
 输出: "bacdfeg"
 要求:

 该字符串只包含小写的英文字母。
 给定字符串的长度和 k 在[1, 10000]范围内。
 */
public class ReverseVowels {
    public String reverseVowels(String s) {
        int begin = 0, end = s.length() - 1;
        char[] str = s.toCharArray();
        while(begin < end){
            while(begin < end && !isYuanyin(str[begin]))
                begin++;
            while(end > begin && !isYuanyin(str[end]))
                end--;
            if(begin < end){
                char temp = str[begin];
                str[begin] = str[end];
                str[end] = temp;
                begin++;
                end--;
            }
        }
        return new String(str);
    }

    private boolean isYuanyin(char c){
        return c == 'a' | c == 'e' | c == 'i' | c == 'o' | c == 'u' |
                c == 'A' | c == 'E' | c == 'I' | c == 'O' | c == 'U';
    }

    public static String reverseString(String s) {
        char[] chs = s.toCharArray();
        int i=0, j=s.length()-1;
        while (i < j){
            char temp = chs[i];
            chs[i] = chs[j];
            chs[j] = temp;
        }
        return String.valueOf(chs);
    }

    public String reverseStr(String s, int k) {
        int len = s.length();
        char[] chars = s.toCharArray();
        int index = 0;
        while(index < len){
            if(len - index > k){
                for(int i = index,j = index + k -1;i<j;i++,j--){
                    char temp = chars[i];
                    chars[i] = chars[j];
                    chars[j] = temp;
                }
                index = index + 2*k;
            }else{
                for(int i = index,j = len-1;i<j;i++,j--){
                    char temp = chars[i];
                    chars[i] = chars[j];
                    chars[j] = temp;
                }
                index = len;
            }
        }
        return new String(chars);
    }


}
