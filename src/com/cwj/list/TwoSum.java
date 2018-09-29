package com.cwj.list;

import java.util.*;

/**
 * Created by cwj on 18-3-25.
 * 给定一个整数数列，找出其中和为特定值的那两个数。
 * 你可以假设每个输入都只会有一种答案，同样的元素不能被重用。
 * 给定 nums = [2, 7, 11, 15], target = 9
 * 因为 nums[0] + nums[1] = 2 + 7 = 9
 * 所以返回 [0, 1]
 */
public class TwoSum {

    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; ++i) {
            map.put(nums[i], i);
        }

        int[] res = new int[2];
        for (int i = 0; i < map.size(); ++i) {
            if (map.containsKey(target - nums[i]) && map.get(target - nums[i]) != i) {
                res[0] = i;
                res[1] = map.get(target - nums[i]);
                return res;
            }
        }
        return null;
    }

    //这种更好，一边插入map，一边找
    public int[] twoSum2(int[] nums, int target) {
        int[] res = new int[2];
        Map<Integer, Integer> map = new HashMap<>();
        for(int i=0; i< nums.length; i++){
            if(!map.containsKey(target - nums[i])){
                map.put(nums[i], i);
            }else{
                res[0] = i;
                res[1] = map.get(target-nums[i]);
                break;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        TwoSum t = new TwoSum();
        int[] nums = {3, 2, 4};
        int[] res = t.twoSum(nums, 5);
        for (int data : res) {
            System.out.print(data + " ");
        }
    }
}
