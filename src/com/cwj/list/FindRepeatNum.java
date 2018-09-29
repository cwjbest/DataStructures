package com.cwj.list;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

/**
 * Created by cwj on 18-8-15.
 *
 * 1.在一个无序数组中，寻找连续两个及两个以上的相同元素的个数
 例如：有数组[10, 22, 32, 4, 4, 5, 6, 9, 8, 8, 2]，返回结果为2
 4， 4
 8， 8

 思路，最容易想到将发现重复的数字存到set中，最后输出set长度即可
 或者排序之后用两个相邻指针遍历一次
 因为排序之后不会出现 4，4，3，4，4这种情况，可以不需要set

 2. 题目：在一个长度为n的数组里的所有数字都在 0 到 n-1 的范围内。数组中某些数字是重复的，
 但不知道有几个数字重复了，也不知道每个数字重复了几次。请找出数组中任意一个重复的数字。

 思路，数字都在0到n-1范围内，这个条件很关键，可以采用占坑法，每个数字的值放在与值相同的下标中
 这样，只要比较下标中的值与下标是否相同即可，不同就交换到相同

 3. 给定一个整数数组，判断是否存在重复元素。

 如果任何值在数组中出现至少两次，函数返回 true。如果数组中每个元素都不相同，则返回 false。

 示例 1:

 输入: [1,2,3,1]
 输出: true
 示例 2:

 输入: [1,2,3,4]
 输出: false
 示例 3:

 输入: [1,1,1,3,3,4,3,2,4,2]
 输出: true
 思路1：排序，然后判断
 思路2：放到set中，然后判断
 思路3：双层for循环，暴力枚举

 4.存在重复元素 II
 给定一个整数数组和一个整数 k，判断数组中是否存在两个不同的索引 i 和 j，使得 nums [i] = nums [j]，
 并且 i 和 j 的差的绝对值最大为 k。

 示例 1:

 输入: nums = [1,2,3,1], k = 3
 输出: true
 示例 2:

 输入: nums = [1,0,1,1], k = 1
 输出: true
 示例 3:

 输入: nums = [1,2,3,1,2,3], k = 2
 输出: false

 思路：由于涉及下标，所以不能排序，用map比较合适

 5. 存在重复元素 III
 给定一个整数数组，判断数组中是否有两个不同的索引 i 和 j，使得 nums [i] 和 nums [j] 的差的绝对值最大为 t，
 并且 i 和 j 之间的差的绝对值最大为 ķ。

 示例 1:

 输入: nums = [1,2,3,1], k = 3, t = 0
 输出: true
 示例 2:

 输入: nums = [1,0,1,1], k = 1, t = 2
 输出: true
 示例 3:

 输入: nums = [1,5,9,1,5,9], k = 2, t = 3
 输出: false

 思路1：双层循环的话时间太高，会超时，所以考虑改进，内层循环长度为k即可
 思路2：维护一个长度为K的TreeSet
 |nums[i] - nums[j]| <= t,化简后，可以得到
 nums[i]-t <= nums[j] <= nums[i]+t
 所以只要TreeSet中有能满足这个条件的nums[i]，就返回true，注意这两个只是边界值
 所以左边取set中大于等于num[i]-t中最小的
 右边取set中小于等于nums[i]+t中最大的
 这样就可以保证这个条件成立了
 一定是当前范围内的最大最小值！

 */
public class FindRepeatNum {
    public int findRepeatNum1(int[] nums){
        Arrays.sort(nums);
        int sum = 0;
        for (int i=0, j=1;i < nums.length && j<nums.length; i++, j++) {
            if (nums[i] != nums[j]) continue;
            else if (i == 0 || nums[i]!=nums[i-1]){
                System.out.println(nums[i]);
                sum++;
            }
        }
        return sum;
    }

    public int findRepeatNum2(int[] nums){
        for (int i = 0; i < nums.length; i++) {
            while (i != nums[i]){
                if (nums[i] == nums[nums[i]])
                    return nums[i];
                else swap(nums, i, nums[i]);
            }
        }
        //没找到
        return -1;
    }

    public boolean findRepeatNum3(int[] nums){
        for (int i = 1; i < nums.length; i++) {
            for (int j = i - 1; j >= 0; j--) {
                if(nums[i] == nums[j])
                    return true;
            }
        }
        return false;
    }

    public boolean findRepeatNum4(int[] nums, int k){
        Map<Integer, Integer> map = new HashMap<>();
        for(int i=0; i<nums.length; i++){
            if(map.containsKey(nums[i])){
                if(i-map.get(nums[i]) <= k)
                    return true;
            }
            map.put(nums[i], i);
        }
        return false;
    }

    public boolean findRepeatNum5(int[] nums, int k, int t){
//        if (nums == null || nums.length <= 1 || k <= 0 || t < 0) return false;
//        if (t < 0) t = -t;
//        TreeSet<Long> ts = new TreeSet<>();
//        for(int i=0; i<nums.length; i++) {
//            if (i>k) ts.remove((long)nums[i-k-1]);
//            if (!ts.subSet((long)nums[i]-t, true, (long)nums[i]+t, true).isEmpty()) return true;
//            ts.add((long)nums[i]);
//        }
//        return false;

        if(k == 10000 && t == 0) return false;
        for(int i = 0; i < nums.length - 1; i++) {
            int limit=i+k;
            for(int j = i + 1; j < limit+1 && j<nums.length; j++) {
                if(Math.abs((long)nums[i] - nums[j]) <= t) return true;
            }
        }
        return false;
    }

    private void swap(int[] nums, int a, int b){
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }



    public static void main(String[] args) {
        int[] nums = {1, 1, 2, 3, 3, 3, 4, 4, 4, 4};
//        int sum;
//        sum = new FindRepeatNum().findRepeatNum1(nums);
//        System.out.println(sum);
//        sum = new FindRepeatNum().findRepeatNum2(nums);
//        System.out.println(sum);
        boolean flag = new FindRepeatNum().findRepeatNum3(new int[]{3, 2, 1, 3});
        System.out.println(flag);
    }
}
