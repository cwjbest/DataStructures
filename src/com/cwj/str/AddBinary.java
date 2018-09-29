package com.cwj.str;

/**
 * Created by cwj on 18-4-9.
 * 给定两个二进制字符串，返回他们的和（用二进制表示）。
 * <p>
 * 案例：
 * a = "11"
 * b = "1"
 * 返回 "100" 。
 */
public class AddBinary {
    public static String addBinary(String a, String b) {
        StringBuilder result = new StringBuilder();
        int pointA = a.length() - 1;
        int pointB = b.length() - 1;
        int carry = 0;
        while (pointA >= 0 || pointB >= 0){
            int sum = carry;
            if (pointA >= 0){
                sum += (a.charAt(pointA) - '0');
                pointA--;
            }
            if (pointB >= 0){
                sum += (b.charAt(pointB) - '0');
                pointB--;
            }
            result.append(sum%2);
            carry = sum/2;
        }

        if (carry != 0){
            result.append('1');
        }
        return result.reverse().toString();
    }
}
