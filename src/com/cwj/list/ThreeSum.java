package com.cwj.list;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by cwj on 18-8-13.
 * 15. 三数之和
 题目描述提示帮助提交记录社区讨论阅读解答
 给定一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？找出所有满足条件且不重复的三元组。

 注意：答案中不可以包含重复的三元组。

 例如, 给定数组 nums = [-1, 0, 1, 2, -1, -4]，

 满足要求的三元组集合为：
 [
 [-1, 0, 1],
 [-1, -1, 2]
 ]

 两数之和的升级版，这个题就不能在用map来做了，因为不能去重
 只能先排序，然后双指针。
 先取第一个数字为基准，然后拿target-这个数字作为两数之和
 接下来的问题就是在剩余的数组中解两数之和的问题了
 注意跳过重复的问题

 */
public class ThreeSum {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if(nums == null || nums.length < 3) return null;
        Arrays.sort(nums);//先排序
        //循环到倒数第3个数停止，因为是三数之和
        for(int i=0; i<nums.length-2; i++){
            if(i == 0 || nums[i] != nums[i-1]){//跳过重复，因为情况是相同的
                int l = i+1;
                int r = nums.length-1;
                int target = 0-nums[i];//取第一个数为基准，在剩余的数组解两数之和问题。

                while(l<r){
                    if(nums[l] + nums[r] < target) l++;
                    else if(nums[l] + nums[r] > target) r--;
                    else{
                        if(l == i+1 || nums[l] != nums[l-1]){//也要去重
                            List<Integer> list = new ArrayList<>();
                            list.add(nums[i]);
                            list.add(nums[l]);
                            list.add(nums[r]);
                            res.add(list);
                        }
                        l++;
                        r--;
                    }
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums = {-1, 0, 1, 2, -1, -4};
        List<List<Integer>> lists = new ThreeSum().threeSum(nums);
        for (int i = 0; i<lists.size(); i++){
            System.out.println(lists.get(i));
        }
    }
}
