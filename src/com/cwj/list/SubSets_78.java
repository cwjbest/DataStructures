package com.cwj.list;

import java.util.*;

/**
 * Created by cwj on 18-9-13.
 * 78. 子集
 * 题目描述提示帮助提交记录社区讨论阅读解答
 * 给定一组不含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。
 * <p>
 * 说明：解集不能包含重复的子集。
 * <p>
 * 示例:
 * <p>
 * 输入: nums = [1,2,3]
 * 输出:
 * [
 * [3],
 * [1],
 * [2],
 * [1,2,3],
 * [1,3],
 * [2,3],
 * [1,2],
 * []
 * ]
 * <p>
 * 例子1， 2， 3
 * 思路，首先将第一个元素1拿出来，然后求{2， 3}的子集，{2}， {3}， {2， 3}.
 * 然后将1分别加入到子集当中{1， 2}， {1， 3}， {1， 2， 3}，最后就可以找出来了。
 */
public class SubSets_78 {
    public static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        res.add(new ArrayList<>());
        List<List<Integer>> temp;
        List<Integer> ele;
        for (int i = 0; i < nums.length; i++) {
            temp = new ArrayList<>();
            //这里一定要来个临时变量，直接使用res会死循环
            for (int j = 0; j < res.size(); j++) {
                ele = new ArrayList<>();
                ele.add(nums[i]);
                ele.addAll(res.get(j));
                temp.add(ele);
            }
            res.addAll(temp);
        }
        return res;
    }

    /**
     * 递归解法
     *
     * @param nums
     * @return
     */
    public static List<List<Integer>> subsetsByDfs(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        dfs(nums, res, 0);
        return res;
    }

    public static void dfs(int[] nums, List<List<Integer>> res, int index) {
        if (index == nums.length) {
            res.add(new ArrayList<>());
            return;
        }
        dfs(nums, res, index + 1);
        List<List<Integer>> copy = new ArrayList<>();
        for (List<Integer> list : res) {
            List<Integer> element = new ArrayList<>();
            element.add(nums[index]);
            element.addAll(list);
            copy.add(element);
        }
        res.addAll(copy);
    }

    /**
     * 二进制解法
     * n个元素的集合，在构造子集合过程中，每个元素有两种情况，在子集合中“存在”与“不存在”，
     * “存在”用“1”表示，“不存在”用“0”表示，这样共有2^n种情况，即2^n个子集合。
     * 此时我们想到用长度为n的二进制数字 “1111….000…”，1表示该位置在子集合中存在，
     * 0表示该位置在子集合中不存在，让该二进制数从0 一直加到 2^n，则所有情况都会出现
     * (1,1,1)->(a,b,c)
     * (1,1,0)->(a,b)
     * (1,0,1)->(a,c)
     * 太精妙了
     */
    public static List<List<Integer>> subsetsByBinary(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < Math.pow(2, nums.length); i++) {
            List<Integer> list = new ArrayList<>();
            int k = i;
            int index = 0;
            while (k > 0) {
                if ((k & 1) == 1)//最低位为1时，表示该元素在子集中
                    list.add(nums[index]);
                k >>= 1;//k右移一位，保证每次比较最低位，当k为0时，所有的1遍历完毕
                index++;
            }
            res.add(list);
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3};
        subsetsByDfs(nums);
    }
}

/**
 * 90. 子集 II
 * 题目描述提示帮助提交记录社区讨论阅读解答
 * 给定一个可能包含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。
 * 排序，set去重即可
 */
class subsetsWithDup_90 {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        Set<List<Integer>> set = new HashSet<>();
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < Math.pow(2, nums.length); i++) {
            int k = i;
            int index = 0;
            List<Integer> list = new ArrayList<>();
            while (k > 0) {
                if ((k & 1) == 1)
                    list.add(nums[index]);
                k >>= 1;
                index++;
            }
            if (!set.contains(list)) {
                set.add(list);
                res.add(list);
            }
        }
        return res;
    }
}


/**
 * 416. 分割等和子集
 题目描述提示帮助提交记录社区讨论阅读解答
 给定一个只包含正整数的非空数组。是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。

 注意:

 每个数组中的元素不会超过 100
 数组的大小不会超过 200
 示例 1:

 输入: [1, 5, 11, 5]

 输出: true

 解释: 数组可以分割成 [1, 5, 5] 和 [11].


 示例 2:

 输入: [1, 2, 3, 5]

 输出: false

 解释: 数组不能分割成两个元素和相等的子集.
 */
class CanPartition_416 {
    public static boolean canPartition(int[] nums) {
        if (nums.length == 1)
            return false;
        if (nums.length == 2)
            return nums[0] == nums[1];
        Arrays.sort(nums);
        int mid = nums.length/2;
        int leftSum = 0;
        int rightSum = 0;
        for (int i = 0; i < mid; i++)
            leftSum += nums[i];
        for (int i = nums.length-1; i >= mid ; i--)
            rightSum += nums[i];

        if(((leftSum + rightSum) & 1) == 1)

        while (mid < nums.length-1 && leftSum < rightSum){
            leftSum += nums[mid];
            rightSum -= nums[mid];
            mid++;
        }
        int start = 0;
        while (start<nums.length-2 &&  leftSum > rightSum){
            leftSum -= nums[start];
            rightSum += nums[start];
            start++;
        }
        return (leftSum == rightSum);
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1, 3, 4, 4};
        canPartition(nums);
    }
}
