package com.cwj.str;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by cwj on 18-3-25.
 * 给定元素全为非负整数的非空数组nums，数组的度等于出现最多的元素的次数。找到具有和nums相同度的连续子串的最小长度。
 * 1. Input:[1, 2, 2, 3, 1]
 * Output: 2
 * 解释:nums的度为2，因为元素1和2都各出现了两次。
 * 和原数组具有相同的度的连续子串有[1, 2, 2, 3, 1], [1, 2, 2, 3], [2, 2, 3, 1], [1, 2, 2], [2, 2, 3], [2, 2]。
 * 其中最短的长度为2，所以输出2。
 * 2. Input: [1,2,2,3,1,4,2]
 * Output: 6
 * <p>
 * 解题思路分析
 * Ⅰ. 稍加分析就会发现，目标子串的首元素一定是nums中出现最多的元素的第一个，目标子数组的尾元素一定是nums中该元素的最后一个。
 * Ⅱ. 这样问题就转化为了：求nums中出现次数最多的某个元素第一次出现，到最后一次出现的子串的最小长度。
 * Ⅲ. 那么只需一次扫描nums，并在扫描过程中用一个map（命名为count）记录元素值到目前为止出现的次数，
 * 再用一个map（命名为startIndex）记录元素值出现的首位置，用于计算扫描到当前元素的子串长度。
 * Ⅳ. 同时循环中不断判断是否更新当前的最大次数frequency。如果最大次数变大了，那么相应更新子串长度len；
 * 如果当前元素的出现次数和最大次数相等，因最大次数相等的元素可任意选择，所以要选择长度较小的，故这里有可能更新len。
 * Ⅴ. map的插入和查看平均复杂度为O(1),一次扫描，故整个算法平均时间复杂度O(n)，空间复杂度O(n)。
 */
public class DegreeOfArray {
    public int findShortestSubArray(int[] nums) {
        Map<Integer, Integer> startIndex = new HashMap<>();
        Map<Integer, Integer> count = new HashMap<>();
        int len = Integer.MAX_VALUE, frequency = 0;
        for (int i = 0; i < nums.length; i++) {
            if (!startIndex.containsKey(nums[i])) {
                startIndex.put(nums[i], i);
            }
            count.put(nums[i], count.getOrDefault(nums[i], 0) + 1);
            if (count.get(nums[i]) == frequency){

                //这种写法可以有效的替换if比较语句，以后应当多用
                len = Math.min(i - startIndex.get(nums[i]), len);
            }else if (count.get(nums[i]) > frequency){
                len = i - startIndex.get(nums[i]) + 1;
                frequency = count.get(nums[i]);

                //一趟遍历能完成的基本上都需要给一个初始值，然后不断更新这个值这种思想很重要。
            }
        }
        return len;
    }

    public static void main(String[] args) {
        DegreeOfArray degreeOfArray= new DegreeOfArray();
        int[] nums = {1, 2, 2, 3, 1};
        int len = degreeOfArray.findShortestSubArray(nums);
        System.out.println(len);

    }
}
