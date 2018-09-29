package com.cwj.list;

/**
 * Created by cwj on 18-9-20.
 * 求任意精度的除法
 */
public class Divade {
    public static String highPrecisionDivide(int x, int y, int n){
        if(y == 0)
            return null;
        if(x == 0)
            return "0";
        String result = "";
        result += x / y + ".";
        x = x - x / y * y;
        while(result.length() <= n){
            int tmp = x * 10 / y;
            result += tmp;
            x = x * 10 - x * 10 / y * y;
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(highPrecisionDivide(10, 3, 5));
    }
}
