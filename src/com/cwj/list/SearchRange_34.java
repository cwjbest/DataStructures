package com.cwj.list;

/**
 * Created by cwj on 18-9-7.
 * 34. 在排序数组中查找元素的第一个和最后一个位置
 题目描述提示帮助提交记录社区讨论阅读解答
 给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。

 你的算法时间复杂度必须是 O(log n) 级别。

 如果数组中不存在目标值，返回 [-1, -1]。

 示例 1:

 输入: nums = [5,7,7,8,8,10], target = 8
 输出: [3,4]
 示例 2:

 输入: nums = [5,7,7,8,8,10], target = 6
 输出: [-1,-1]

 */
public class SearchRange_34 {
    public int[] searchRange(int[] nums, int target) {
        int i = 0;
        int j = nums.length-1;
        int[] res = new int[]{-1, -1};
        while(i <= j){
            while(i <= j && nums[i] != target)
                i++;
            if(i <= j && res[0] == -1)
                res[0] = i;
            while(i <= j && nums[j] != target)
                j--;
            if(j >= i && res[1] == -1)
                res[1] = j;
            if(res[0] != -1 && res[1] != -1)
                return res;
            else{
                i++;
                j--;
            }
        }
        return res;
    }
}
