package com.cwj.list;

import java.util.Arrays;

/**
 * Created by cwj on 18-9-26.
 *
 */
public class BubbleSort {
    public static int[] bubbleSort(int[] nums){
        if (nums == null || nums.length == 1)
            return nums;
        boolean isSorted = true;
        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < nums.length-i; j++) {
                if (nums[j] > nums[j+1]){
                    swap(nums, j, j+1);
                    isSorted = false;
                }
            }
            if (isSorted)
                break;
        }
        return nums;
    }

    private static void swap(int[] nums, int i, int j){
        nums[j] = nums[i] ^ nums[j];
        nums[i] = nums[i] ^ nums[j];
        nums[j] = nums[i] ^ nums[j];
    }

    public static void main(String[] args) {
        int[] nums = new int[]{6, 3, 1, 2, 5, 4};
        System.out.println(Arrays.toString(bubbleSort(nums)));
    }
}
