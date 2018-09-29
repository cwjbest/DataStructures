package com.cwj.list;

/**
 * Created by cwj on 18-9-24.
 * 请实现一个函数，将一个字符串中的每个空格替换成“%20”。例如，当字符串为We Are Happy.
 * 则经过替换之后的字符串为We%20Are%20Happy。
 */
public class ReplaceSpace {
    public String replaceSpace(StringBuffer str) {
        char[] chs1 = str.toString().toCharArray();
        int spaceNum = 0;
        for (char ch : chs1) {
            if (ch == ' ')
                spaceNum++;
        }
        int len = spaceNum * 2 + chs1.length;
        char[] chs2 = new char[len];
        for (int i = chs1.length - 1; i >= 0; i--) {
            if (chs1[i] != ' ')
                chs2[len--] = chs1[i];
            else {
                chs2[len--] = '0';
                chs2[len--] = '2';
                chs2[len--] = '%';
            }
        }
        return String.valueOf(chs2);
    }

    public static void main(String[] args) {
    }
}
