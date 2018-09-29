package com.cwj.list;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by cwj on 18-8-24.
 * 204. 计数质数
 统计所有小于非负整数 n 的质数的数量。

 示例:

 输入: 10
 输出: 4
 解释: 小于 10 的质数一共有 4 个, 它们是 2, 3, 5, 7 。
 剔除法，将2的倍数剔除，3， 5， 。。。
 */
public class CountPrimes {
    public int countPrimes(int n) {
        if(n < 2) return 0;
        boolean[] flag = new boolean[n];
        int count = 0;
        for(int i=2; i<n; i++){
            if(!flag[i])
                count++;
            for(int j=1; j * i < n; j++){
                flag[i*j] = true;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        Set<Integer> set = new HashSet<>();
    }
}
