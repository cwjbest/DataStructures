package com.cwj.list;

import java.util.Arrays;

/**
 * Created by cwj on 18-8-30.
 *
 */
public class Sort {
    public static void binaryInsert(int[] nums){

        for (int i = 1; i < nums.length; i++) {
            int left = 0;
            int right = i - 1;
            int temp = nums[i];

            while (left <= right){
                int mid = (left + right) / 2;
                if (nums[mid] >= temp)
                    right = mid - 1;
                else
                    left = mid + 1;
            }

            for (int j = i-1; j >= left; j--) {
                nums[j+1] = nums[j];
            }
            nums[left] = temp;
        }
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 5, 5, 7, 3, 4, 8};
        binaryInsert(nums);
        System.out.println(Arrays.toString(nums));
    }
}
