package com.cwj.str;

import java.math.BigInteger;

/**
 * Created by cwj on 18-4-10.
 * 实现 int sqrt(int x) 函数。
 * 计算并返回 x 的平方根。
 * x 保证是一个非负整数。
 * 案例 1:
 * 输入: 4
 * 输出: 2
 * 案例 2:
 * 输入: 8
 * 输出: 2
 * 说明: 8 的平方根是 2.82842..., 由于我们想返回一个整数，小数部分将被舍去。
 */
public class MySqrt {
    public int mySqrt(int x) {
        if(x == 0) return 0;
        int i;
        for (i = 1; i < x / 2; ++i) {
            if (i >= x/i) {
                break;
            }
        }
        return i * i > x ? i - 1 : i;
    }
}
