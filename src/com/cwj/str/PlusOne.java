package com.cwj.str;

/**
 * Created by cwj on 18-4-8.
 * 给定一个非负整数组成的非空数组，数组中的每个数都是这个整数的某一位（0-9）,给整数加一。
 * 可以假设整数不包含任何前导零，除了数字0本身。
 * 最高位数字存放在列表的首位。
 *
 * 思路：考虑进位的问题
 */
public class PlusOne {
    public int[] plusOne1(int[] digits) {
        int carry = 1;
        for (int i = digits.length - 1; i >= 0; --i) {
            if (carry == 0){
                break;
            }
            int temp = digits[i] + carry;
            carry = temp / 10;
            digits[i] =temp % 10;
        }
        if (carry == 1){
            int newDigits[] = new int[digits.length + 1];
            newDigits[0] = 1;
            return newDigits;
        }
        return digits;
    }

    public static int[] plus2(int[] digits){
        for (int i = digits.length-1; i >= 0; i--) {
            if (digits[i] < 9){
                digits[i]++;
                return digits;
            }else
                digits[i] = 0;
        }

        int[] newDigits = new int[digits.length+1];
        newDigits[0] = 1;
        return newDigits;
    }

    public static void main(String[] args) {
        int[] num = new int[]{9, 9, 8};
        int[] nums = plus2(num);
        for (int i = 0; i < nums.length; i++) {
            System.out.println(nums[i]);
        }
    }
}
