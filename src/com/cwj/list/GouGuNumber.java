package com.cwj.list;

/**
 * Created by cwj on 18-9-12.
 勾股数，是由三个正整数组成的数组；能符合勾股定理 a*a + b*b = c*c ， (a, b, c) 的正整数解。
 如果 (a, b, c) 是勾股数，它们的正整数倍数，也是勾股数。如果 (a, b, c) 互质，它们就称为素勾股数。
 给定正整数N，计算出小于或等于N的素勾股数个数。(0 < a <= b <= c <= N)

 输入
 正整数N

 输出
 小于或等于N的素勾股数个数

 (0 < a <= b <= c <= N)

 样例输入
 10
 样例输出
 1
 */
public class GouGuNumber {
    public static int gouGuNum(int n){
        int sum = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = i+1; j <= n; j++) {
                int c = (int)Math.sqrt(i*i + j*j);
                if (c <= n && i*i + j*j == c*c && isHuZhi(i, j, c)) {
                    sum++;
                }
            }
        }
        return sum;
    }

    public static boolean isHuZhi(int a, int b, int c){
        return (gcd(b, a) == 1) &&
                (gcd(c, a) == 1) &&
                (gcd(c, b) == 1);
    }

    /**
     * 欧几里得算法求最大公约数
     * 最小公倍数为 a*b/gcd(a,b)
     */
    public static int gcd(int a, int b){
        if (a < b)
            swap(a, b);
        while (b != 0){
            int c = a % b;
            a = b;
            b = c;
        }
        return a;
    }

    public static void swap(int m, int n){
        m = m ^ n;
        n = m ^ n;
        m = m ^ n;
    }

    public static void main(String[] args) {
        gouGuNum(100);
    }
}
