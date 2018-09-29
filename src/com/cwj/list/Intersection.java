package com.cwj.list;

import java.util.*;

/**
 * Created by cwj on 18-8-26.
 *
 * 349. 两个数组的交集
 给定两个数组，编写一个函数来计算它们的交集。

 示例 1:

 输入: nums1 = [1,2,2,1], nums2 = [2,2]
 输出: [2]
 示例 2:

 输入: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
 输出: [9,4]
 说明:

 输出结果中的每个元素一定是唯一的。
 我们可以不考虑输出结果的顺序。

 * 先排序，然后双指针，注意用set去重，因为交集没有重复元素
 */
public class Intersection {
    public int[] intersection(int[] nums1, int[] nums2) {
        int i=0, j=0;
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        Set<Integer> set = new HashSet<>();
        while(i<nums1.length && j<nums2.length){
            if(nums1[i] > nums2[j])
                j++;
            else if(nums1[i] < nums2[j])
                i++;
            else{
                set.add(nums1[i]);
                i++;
                j++;
            }
        }
        int[] res = new int[set.size()];
        i=0;
        for(int n:set){
            res[i++] = n;
        }
        return res;
    }

    /**
     * 350. 两个数组的交集 II
     给定两个数组，编写一个函数来计算它们的交集。

     示例 1:

     输入: nums1 = [1,2,2,1], nums2 = [2,2]
     输出: [2,2]
     示例 2:

     输入: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
     输出: [4,9]
     说明：

     输出结果中每个元素出现的次数，应与元素在两个数组中出现的次数一致。
     我们可以不考虑输出结果的顺序。
     进阶:

     如果给定的数组已经排好序呢？你将如何优化你的算法？
     如果 nums1 的大小比 nums2 小很多，哪种方法更优？
     如果 nums2 的元素存储在磁盘上，磁盘内存是有限的，并且你不能一次加载所有的元素到内存中，你该怎么办？

     思路：需要重复，那就将set换成list即可
     或者用map来存储nums1，然后遍历nums2，map中的值对应-1即可
     */
    public int[] intersect(int[] nums1, int[] nums2) {
        List<Integer> list = new ArrayList<>();
        Map<Integer, Integer> map_1 = new HashMap<>();
        for (int a:nums1) {
            map_1.put(a, map_1.getOrDefault(a, 0) + 1);
        }
        for (int a:nums2) {
            if (map_1.containsKey(a) && map_1.get(a) != 0){
                map_1.put(a, map_1.get(a)-1);
                list.add(a);
            }
        }

        int i = 0;
        int[] res = new int[list.size()];
        for (int a:list) {
            res[i++] = a;
        }
        return res;
    }
}
