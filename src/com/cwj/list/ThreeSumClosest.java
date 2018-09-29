package com.cwj.list;

import java.util.Arrays;

/**
 * Created by cwj on 18-8-14.
 * 16. 最接近的三数之和
 题目描述提示帮助提交记录社区讨论阅读解答
 给定一个包括 n 个整数的数组 nums 和 一个目标值 target。找出 nums 中的三个整数，使得它们的和与 target 最接近。返回这三个数的和。假定每组输入只存在唯一答案。

 例如，给定数组 nums = [-1，2，1，-4], 和 target = 1.

 与 target 最接近的三个数的和为 2. (-1 + 2 + 1 = 2).

 与三数之和相似，只不过判断条件变了而已
 */
public class ThreeSumClosest {
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int min = Integer.MAX_VALUE;
        int closestSum = 0;
        for (int i = 0; i < nums.length-2; i++) {
            if (i == 0 || nums[i-1]!=nums[i]){
                int l = i+1;
                int r = nums.length-1;

                while (l<r){
                    int tempSum = nums[i] + nums[l] + nums[r];
                    int tempMin = Math.abs(tempSum - target);
                    if (tempMin == 0) return tempSum;
                    if (tempMin < min){
                        closestSum = tempSum;
                        min = tempMin;
                    }
                    if (tempSum < target){
                        l++;
                    }else if(tempSum > target){
                        r--;
                    }
                }
            }
        }
        return closestSum;
    }
}
