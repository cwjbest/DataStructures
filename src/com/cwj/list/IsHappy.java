package com.cwj.list;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by cwj on 18-9-7.
 * 202. 快乐数
 题目描述提示帮助提交记录社区讨论阅读解答
 编写一个算法来判断一个数是不是“快乐数”。

 一个“快乐数”定义为：对于一个正整数，每一次将该数替换为它每个位置上的数字的平方和，
 然后重复这个过程直到这个数变为 1，也可能是无限循环但始终变不到 1。如果可以变为 1，那么这个数就是快乐数。

 示例:

 输入: 19
 输出: true
 解释:
 12 + 92 = 82
 82 + 22 = 68
 62 + 82 = 100
 12 + 02 + 02 = 1
 */
public class IsHappy {
    public boolean isHappy(int n){
        if (n <= 0) return false;
        Set<Integer> set = new HashSet<>();
        while (n != 1){
            n = fun(n);
            if (set.contains(n))
                return false;
            else
                set.add(n);
        }
        return true;
    }

    public int fun(int n){
        int res = 0;
        if (n < 10)
            return n*n;
        if(n >= 10){
            while (n > 0){
                int x = n % 10;
                res += x * x;
                n = n / 10;
            }
        }
        return res;
    }
}
