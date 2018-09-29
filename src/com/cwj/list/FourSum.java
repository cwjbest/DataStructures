package com.cwj.list;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by cwj on 18-8-14.
 * 18. 四数之和
 题目描述提示帮助提交记录社区讨论阅读解答
 给定一个包含 n 个整数的数组 nums 和一个目标值 target，判断 nums 中是否存在四个元素 a，b，c 和 d ，使得 a + b + c + d 的值与 target 相等？找出所有满足条件且不重复的四元组。

 注意：

 答案中不可以包含重复的四元组。

 示例：

 给定数组 nums = [1, 0, -1, 0, -2, 2]，和 target = 0。

 满足要求的四元组集合为：
 [
 [-1,  0, 0, 1],
 [-2, -1, 1, 2],
 [-2,  0, 0, 2]
 ]

 三数之和的变种问题
 在3sum只有一个i基准的情况下，两个循环嵌套，分别为i，和j，对应两个基准值，然后再初始化两个指针p和q，剩下的做法和3sum一样
 */
public class FourSum {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> res = new ArrayList<>();
        if(nums == null || nums.length < 4) return res;
        Arrays.sort(nums);//先排序
        //循环到倒数第4个数停止，因为是4数之和
        for(int i=0; i<nums.length-3; i++){
            if(i == 0 || nums[i] != nums[i-1]){
                for(int j=i+1; j<nums.length-2; j++){
                    if(j == i+1 || nums[j] != nums[j-1]){//跳过重复，因为情况是相同的,注意这里不能是j==0，而是j循环的初始值
                        int l = j+1;
                        int r = nums.length-1;
                        int k = target-nums[i]-nums[j];//取i,j为基准，在剩余的数组解两数之和问题。
                        while(l<r){
                            if(nums[l] + nums[r] < k) l++;
                            else if(nums[l] + nums[r] > k) r--;
                            else{
                                if(l == j+1 || nums[l] != nums[l-1]){//也要去重
                                    List<Integer> list = new ArrayList<>();
                                    list.add(nums[i]);
                                    list.add(nums[j]);
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
            }
        }
        return res;
    }
}
