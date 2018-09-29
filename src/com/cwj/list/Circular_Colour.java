package com.cwj.list;

/**
 * Created by cwj on 18-9-7.
 * 问题定义
 一个圆划分为N个扇形，现用M种颜色对其上色，要求相邻两块扇形的颜色不能相同，问有多少种上色方案？（其中N>=1,M>=3)
 注意：不考虑对称性，例如：一个圆划分为2个扇形，用3种颜色上色方案有“黑红，黑白，白红，白黑，红白，红黑”6种，
 相当于每个扇形都有编号，是不一样的。

 解题思路
 采用数学归纳的思想。求n个扇形的上色方案，相当于在n-1个扇形中插入一个扇形，这时只需考虑两种情况：
 1、第1个扇形和第n-1个扇形颜色不一样，这有s(n-1)种情况，此时插入的扇形n有m-2种颜色选择；
 2、第1个扇形和第n-1个扇形颜色一样(n>3)(将两个扇形看成一个)，这有s(n-2)种情况，此时插入的扇形n有m-1种颜色选择。
 由以上分析可得：

 s(1) = m;
 s(2) = m(m-1);
 s(3) = m(m-1)(m-2);
 s(n) = s(n-1)*(m-2)+s(n-2)*(m-1);
 */
public class Circular_Colour {
    public static int fun(int n, int m){
        if (n == 1) return m;
        if (n == 2) return m * (m-1);
        if (n == 3) return m * (m-1)*(m-2);

        return fun(n-1, m) * (m-2) + fun(n-2, m) * (m-1);
    }

    public static void main(String[] args) {
        System.out.println(fun(4, 4));
    }
}
