package com.cwj.test.copy;

/**
 * Created by cwj on 18-9-5.
 *
 */
public class Zuoyebang {
    public static void main(String[] args) {
        System.out.println(fun(6));
    }

    public static double fun(int n){
        double sum = 0;
        int i = (n & 1) == 0 ? 2:1;
        for (; i <= n; i = i + 2)
            sum += 1.0 / i;
        return sum;
    }
}
