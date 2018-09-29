package com.cwj.str;

import java.util.HashMap;

/**
 * Created by cwj on 18-4-10.
 * 给定一个大小为 n 的数组，找到其中的众数。众数是指在数组中出现次数大于 ⌊ n/2 ⌋ 的元素。
 * 你可以假设数组是非空的，并且数组中的众数永远存在。
 */
public class MajorityElement {
    public static int majorityElement(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], map.get(nums[i]) == null ? 1:map.get(nums[i])+1);
            if (map.get(nums[i]) > nums.length/2){
                return nums[i];
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        int[] num = {1, 2, 3, 3, 4, 4};
    }
}
