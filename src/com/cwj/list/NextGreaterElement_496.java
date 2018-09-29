package com.cwj.list;

import java.util.Arrays;

/**
 * Created by cwj on 18-9-6.
 * 496. 下一个更大元素 I
 * 给定两个没有重复元素的数组 nums1 和 nums2 ，其中nums1 是 nums2 的子集。
 * 找到 nums1 中每个元素在 nums2 中的下一个比其大的值。
 nums1 中数字 x 的下一个更大元素是指 x 在 nums2 中对应位置的右边的第一个比 x 大的元素。如果不存在，对应位置输出-1。
 示例 1:

 输入: nums1 = [4,1,2], nums2 = [1,3,4,2].
 输出: [-1,3,-1]
 解释:
 对于num1中的数字4，你无法在第二个数组中找到下一个更大的数字，因此输出 -1。
 对于num1中的数字1，第二个数组中数字1右边的下一个较大数字是 3。
 对于num1中的数字2，第二个数组中没有下一个更大的数字，因此输出 -1。
 示例 2:

 输入: nums1 = [2,4], nums2 = [1,2,3,4].
 输出: [3,-1]
 解释:
 对于num1中的数字2，第二个数组中的下一个较大数字是3。
 对于num1中的数字4，第二个数组中没有下一个更大的数字，因此输出 -1。
 注意:

 nums1和nums2中所有元素是唯一的。
 nums1和nums2 的数组大小都不超过1000。
 */
public class NextGreaterElement_496 {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        int[] res = new int[nums1.length];
        int k;
        for (int i = 0; i < nums1.length; i++) {
            for (int j = 0; j < nums2.length; j++) {
                if (nums1[i] == nums2[j]){
                    for (k = j; k < nums2.length; k++) {
                        if (nums1[i] < nums2[k]){
                            res[i] = nums2[k];
                            break;
                        }
                    }
                    if (k == nums2.length)
                        res[i] = -1;
                    break;
                }
            }
        }

        return res;
    }

    /**
     *
     * 503. 下一个更大元素 II
     题目描述提示帮助提交记录社区讨论阅读解答
     给定一个循环数组（最后一个元素的下一个元素是数组的第一个元素），输出每个元素的下一个更大元素。
     数字 x 的下一个更大的元素是按数组遍历顺序，这个数字之后的第一个比它更大的数，这意味着你应该循环地搜索它的下一个更大的数。
     如果不存在，则输出 -1。

     示例 1:

     输入: [1,2,1]
     输出: [2,-1,2]
     解释: 第一个 1 的下一个更大的数是 2；
     数字 2 找不到下一个更大的数；
     第二个 1 的下一个最大的数需要循环搜索，结果也是 2。
     注意: 输入数组的长度不会超过 10000。
     */
    public int[] nextGreaterElements2(int[] nums) {
        int[] res = new int[nums.length];
        int j, k;
        for (int i = 0; i < nums.length; i++) {
            for (j = i + 1; j < nums.length; j++) {
                if (nums[i] < nums[j]){
                    res[i] = nums[j];
                    break;
                }
            }
            if (j == nums.length){
                for (k = 0; k < i; k++) {
                    if (nums[i] < nums[k]){
                        res[i] = nums[k];
                        break;
                    }
                }
                if (k == i)
                    res[i] = -1;
            }
        }
        return res;
    }

    /**
     *
     * 556. 下一个更大元素 III
     题目描述提示帮助提交记录社区讨论阅读解答
     给定一个32位正整数 n，你需要找到最小的32位整数，其与 n 中存在的位数完全相同，并且其值大于n。
     如果不存在这样的32位整数，则返回-1。

     示例 1:

     输入: 12
     输出: 21
     示例 2:

     输入: 21
     输出: -1
     这道题给了我们一个数字，让我们对各个位数重新排序，求出刚好比给定数字大的一种排序，如果不存在就返回-1。
     这道题给的例子的数字都比较简单，我们来看一个复杂的，比如12443322，这个数字的重排序结果应该为13222344，
     如果我们仔细观察的话会发现数字变大的原因是左数第二位的2变成了3，细心的童鞋会更进一步的发现后面的数字由降序变为了升序，
     这也不难理解，因为我们要求刚好比给定数字大的排序方式。那么我们再观察下原数字，看看2是怎么确定的，
     我们发现，如果从后往前看的话，2是第一个小于其右边位数的数字，
     因为如果是个纯降序排列的数字，做任何改变都不会使数字变大，直接返回-1。
     知道了找出转折点的方法，再来看如何确定2和谁交换，这里2并没有跟4换位，而是跟3换了，那么如何确定的3？
     其实也是从后往前遍历，找到第一个大于2的数字交换，然后把转折点之后的数字按升序排列就是最终的结果了。
     最后记得为防止越界要转为长整数型，然后根据结果判断是否要返回-1即可
     */
    public static int nextGreaterElement3(int n) {
        char[] chs = String.valueOf(n).toCharArray();
        int len = chs.length;
        int i = len - 1;
        for (; i > 0; i--) {
            if (chs[i] > chs[i-1])
                break;
        }
        if (i == 0)
            return -1;
        for (int j = len-1; j >= i; j--) {
            if (chs[j] > chs[i-1]){
                swap(chs, j, i-1);
                break;
            }
        }
        Arrays.sort(chs, i, len);
        //这儿得注意一下转换过来之后可能会溢出
        long res = Long.parseLong(String.valueOf(chs));
        return res > Integer.MAX_VALUE ? -1 : (int)res;
    }

    private static void swap(char[] chs, int j, int i){
        char temp = chs[j];
        chs[j] = chs[i];
        chs[i] = temp;
    }

    public static void main(String[] args) {
        nextGreaterElement3(230241);
    }
}
