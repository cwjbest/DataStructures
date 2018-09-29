package com.cwj.list;

import java.util.*;

/**
 * Created by cwj on 18-8-13.
 * 46. 全排列
 题目描述提示帮助提交记录社区讨论阅读解答
 给定一个没有重复数字的序列，返回其所有可能的全排列。

 示例:

 输入: [1,2,3]
 输出:
 [
 [1,2,3],
 [1,3,2],
 [2,1,3],
 [2,3,1],
 [3,1,2],
 [3,2,1]
 ]

 47. 全排列 II
 题目描述提示帮助提交记录社区讨论阅读解答
 给定一个可包含重复数字的序列，返回所有不重复的全排列。

 示例:

 输入: [1,1,2]
 输出:
 [
 [1,1,2],
 [1,2,1],
 [2,1,1]
 ]
 *
 */
public class Permute {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        recursion(res, nums, 0);
        return res;
    }

    private void recursion(List<List<Integer>> res, int[] nums, int n){
        if (n == nums.length){
            List<Integer> temp = new ArrayList<>();
            for (int num : nums) {
                temp.add(num);
            }
            res.add(temp);
        }

        //去重集合
        Set<Integer> set = new HashSet<>();

        for (int i = n; i < nums.length; i++) {
            //如果出现过，不交换
            if (set.contains(nums[i]))
                continue;
            set.add(nums[i]);
            swap(nums, i, n);
            recursion(res, nums, n+1);
            swap(nums, i, n);
        }
    }

    private static void swap(int[] nums, int x, int y){
        int temp = nums[x];
        nums[x] = nums[y];
        nums[y] = temp;
    }

    /**
     *31. 下一个排列
     实现获取下一个排列的函数，算法需要将给定数字序列重新排列成字典序中下一个更大的排列。

     如果不存在下一个更大的排列，则将数字重新排列成最小的排列（即升序排列）。

     必须原地修改，只允许使用额外常数空间。

     以下是一些例子，输入位于左侧列，其相应输出位于右侧列。
     1,2,3 → 1,3,2
     3,2,1 → 1,2,3
     1,1,5 → 1,5,1
     *
     */
    public static void nextPermutation(int[] nums) {
        int len = nums.length;
        int i = len-1;
        for (; i > 0 ; i--) {
            if (nums[i] > nums[i-1])
                break;
        }
        if (i == 0) {
//            Arrays.sort(nums);
            reverse(nums);
            return;
        }
        for (int j = len-1; j >= i ; j--) {
            if (nums[j] > nums[i-1]){
                swap(nums, j, i-1);
                break;
            }
        }
        Arrays.sort(nums, i, len);
    }

    public static void reverse(int[] nums){
        int i = 0;
        int j = nums.length-1;
        while (i < j){
            swap(nums, i, j);
            i++;
            j--;
        }
    }


    public static void main(String[] args) {
        int[] nums = new int[]{3, 2, 1};
        nextPermutation(nums);
    }
}
