package com.cwj.list;

/**
 * Created by cwj on 18-9-23.
 * 输入一个整数数组，实现一个函数来调整该数组中数字的顺序，使得所有的奇数位于数组的前半部分，
 * 所有的偶数位于数组的后半部分，并保证奇数和奇数，偶数和偶数之间的相对位置不变。
 *
 * 思路1：遍历数组，遇到偶数的时候，将这个偶数之后的数字依次前移，然后将这个偶数放到数组末位
 * n方的复杂度较高
 * 思路2：如果不考虑相对位置不变，可以用双指针前后扫描然后交换
 */
public class ReOrderArray_jz {
    public void reOrderArray(int[] array){
        if (array == null || array.length == 0)
            return;
        int[] nums = new int[array.length];
        int l = 0;
        for (int a:array) {
            if ((a & 1) == 1)
                nums[l++] = a;
        }
        for (int a:array) {
            if ((a & 1) == 0)
                nums[l++] = a;
        }
        System.arraycopy(nums, 0, array, 0, nums.length);
    }

    public static void main(String[] args) {
    }
}
