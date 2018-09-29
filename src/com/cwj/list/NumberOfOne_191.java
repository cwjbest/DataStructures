package com.cwj.list;

/**
 * Created by cwj on 18-9-11.
 * 191. 位1的个数
 题目描述提示帮助提交记录社区讨论阅读解答
 编写一个函数，输入是一个无符号整数，返回其二进制表达式中数字位数为 ‘1’ 的个数（也被称为汉明重量）。

 示例 :

 输入: 11
 输出: 3
 解释: 整数 11 的二进制表示为 00000000000000000000000000001011


 示例 2:

 输入: 128
 输出: 1
 解释: 整数 128 的二进制表示为 00000000000000000000000010000000
 */
public class NumberOfOne_191 {
    public int hammingWeight(int n) {
        int count = 0;
        while (n != 0){
            n &= n-1;
            count++;
        }
        return count;
    }
}

