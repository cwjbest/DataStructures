package com.cwj.dp;

import java.util.Arrays;

/**
 * Created by cwj on 18-8-29.
 * 674. 最长连续递增序列
 给定一个未经排序的整数数组，找到最长且连续的的递增序列。

 示例 1:

 输入: [1,3,5,4,7]
 输出: 3
 解释: 最长连续递增序列是 [1,3,5], 长度为3。
 尽管 [1,3,5,7] 也是升序的子序列, 但它不是连续的，因为5和7在原数组里被4隔开。
 示例 2:

 输入: [2,2,2,2,2]
 输出: 1
 解释: 最长连续递增序列是 [2], 长度为1。
 注意：数组长度不会超过10000。
 */
public class FindLengthOfLCIS {
    public int findLengthOfLCIS(int[] nums) {
        int maxLen = 0;
        int sum = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i-1] < nums[i]){
                sum++;
            }else{
                maxLen = Math.max(maxLen, sum);
                sum = 1;
            }
        }
        return Math.max(maxLen, sum);
    }

    /**
     *
     * 300. 最长上升子序列
     题目描述提示帮助提交记录社区讨论阅读解答
     给定一个无序的整数数组，找到其中最长上升子序列的长度。

     示例:

     输入: [10,9,2,5,3,7,101,18]
     输出: 4
     解释: 最长的上升子序列是 [2,3,7,101]，它的长度是 4。
     说明:

     可能会有多种最长上升子序列的组合，你只需要输出对应的长度即可。
     你算法的时间复杂度应该为 O(n2) 。
     进阶: 你能将算法的时间复杂度降低到 O(n log n) 吗?

     动态规划：用longest[i]数组表示以i结尾的最大子序列长度
     longest[i] = arr[i] > arr[i-1] && max(longest[i-1]+1, longest[i])
     */
    public static int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int[] dp = new int[nums.length];
        for (int i = 0; i < nums.length; i++)
            dp[i] = 1;
        int max = 1;
        for (int i = 1; i < nums.length; i++) {
            for (int j = i-1; j >= 0; j--) {
                if (nums[i] > nums[j] && dp[i] < dp[j] + 1){
                    dp[i] = dp[j] + 1;
                    max = Math.max(dp[i], max);
                }
            }
        }
        System.out.println(Arrays.toString(dp));
        return max;
    }

//    这种写法也ok，i,j都从0开始，少了初始赋值的过程，加到大循环里面
//    public int lengthOfLIS(int[] nums) {
//        if (nums == null || nums.length == 0) return 0;
//        int[] dp = new int[nums.length];
//        int max = 1;
//        for (int i = 0; i < nums.length; i++) {
//            dp[i] = 1;
//            for (int j = 0; j < i; j++) {
//                if (nums[i] > nums[j] && dp[i] < dp[j] + 1){
//                    dp[i] = dp[j] + 1;
//                    max = Math.max(dp[i], max);
//                }
//            }
//        }
//        return max;
//    }

    /**
     * 基于二分查找，改进复杂度为nlogn
     * 假设存在一个序列d[1..9] = 2 1 5 3 6 4 8 9 7，可以看出来它的LIS长度为5。n
     下面一步一步试着找出它。
     我们定义一个序列B，然后令 i = 1 to 9 逐个考察这个序列。
     此外，我们用一个变量Len来记录现在最长算到多少了

     首先，把d[1]有序地放到B里，令B[1] = 2，就是说当只有1一个数字2的时候，长度为1的LIS的最小末尾是2。这时Len=1

     然后，把d[2]有序地放到B里，令B[1] = 1，就是说长度为1的LIS的最小末尾是1，d[1]=2已经没用了，很容易理解吧。这时Len=1

     接着，d[3] = 5，d[3]>B[1]，所以令B[1+1]=B[2]=d[3]=5，就是说长度为2的LIS的最小末尾是5，很容易理解吧。这时候B[1..2] = 1, 5，Len＝2

     再来，d[4] = 3，它正好加在1,5之间，放在1的位置显然不合适，因为1小于3，长度为1的LIS最小末尾应该是1，这样很容易推知，长度为2的LIS最小末尾是3，于是可以把5淘汰掉，这时候B[1..2] = 1, 3，Len = 2

     继续，d[5] = 6，它在3后面，因为B[2] = 3, 而6在3后面，于是很容易可以推知B[3] = 6, 这时B[1..3] = 1, 3, 6，还是很容易理解吧？ Len = 3 了噢。

     第6个, d[6] = 4，你看它在3和6之间，于是我们就可以把6替换掉，得到B[3] = 4。B[1..3] = 1, 3, 4， Len继续等于3

     第7个, d[7] = 8，它很大，比4大，嗯。于是B[4] = 8。Len变成4了

     第8个, d[8] = 9，得到B[5] = 9，嗯。Len继续增大，到5了。

     最后一个, d[9] = 7，它在B[3] = 4和B[4] = 8之间，所以我们知道，最新的B[4] =7，B[1..5] = 1, 3, 4, 7, 9，Len = 5。

     于是我们知道了LIS的长度为5。

     !!!!! 注意。这个1,3,4,7,9不是LIS，它只是存储的对应长度LIS的最小末尾。有了这个末尾，
     我们就可以一个一个地插入数据。虽然最后一个d[9] = 7更新进去对于这组数据没有什么意义，
     但是如果后面再出现两个数字 8 和 9，那么就可以把8更新到d[5], 9更新到d[6]，得出LIS的长度为6。

     然后应该发现一件事情了：在B中插入数据是有序的，而且是进行替换而不需要挪动——也就是说，
     我们可以使用二分查找，将每一个数字的插入时间优化到O(logN)~于是算法的时间复杂度就降低到了O(NlogN)～！

     对于上述解题思想，B 数组中的最后一个有效元素 B[last] 需要保持为所有大于 B[last-1] 的元素中的最小值，
     因为只有两个元素的差值越小，才能在边界在一定范围内的有序集合中填充更多的有序的元素。
     */
    public static int lengthOfLIS2(int[] nums){
        if (nums == null || nums.length == 0)
            return 0;
        int dp[] = new int[nums.length];
        dp[0] = nums[0];
        int len = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > dp[len-1]){
                dp[len] = nums[i];
                len++;
            }else {
                int left = 0;
                int right = len - 1;
                while (left < right){//这里不能写成<=,会死循环
                    int mid = (left + right)/2;
                    if (dp[mid] >= nums[i])
                        right = mid;//替换比他大的第一个值
                    else
                        left = mid + 1;
                }
                dp[left] = nums[i];
            }
        }
        return len;
    }

    /**
     * 673. 最长递增子序列的个数
     给定一个未排序的整数数组，找到最长递增子序列的个数。

     示例 1:

     输入: [1,3,5,4,7]
     输出: 2
     解释: 有两个最长递增子序列，分别是 [1, 3, 4, 7] 和[1, 3, 5, 7]。
     示例 2:

     输入: [2,2,2,2,2]
     输出: 5
     解释: 最长递增子序列的长度是1，并且存在5个子序列的长度为1，因此输出5。
     注意: 给定的数组长度不超过 2000 并且结果一定是32位有符号整数。
     除了dp之外，再找一个数组来表示当前字符为结尾的最大子序列的个数

     每次刚到达第i位时，将len[i]和cnt[i]都置为1表示只考虑第i位这一个数。
     扫描第i位之前的数字nums[j]，如果nums[i]>nums[j]，则表示nums[i]可以加在nums[j]最长序列的后面构成一3个新的序列。
     再对这个新的序列长度和已经找到的i上的最长序列长度加以判断，新的序列的长度是len[j] + 1
     如果len[i] == len[j] + 1，则表示在找到j之前已经有加上nums[i]构成序列且长度与这次新的序列长度相同，二者的cnt可以累加。
     如果len[i] < len[j] + 1，则表示新的序列时目前找到的到i为止最长的序列，则将len[i]和cnt[i]更新为len[j] + 1和cnt[j]。
     如果len[i] > len[j]+1，则表示新的序列不如之前已经找到的序列长，就不用考虑了。
     */
    public static int findNumberOfLIS(int[] nums) {
        if(nums.length <= 1) return nums.length;
        int[] len = new int[nums.length];
        int[] cnt = new int[nums.length];
        int res = 0, max_len = 0;
        for(int i = 0; i < nums.length; ++i){
            len[i] = cnt[i] = 1;  //长度为1，计数为1
            for(int j = 0; j < i; j++){
                if(nums[i] > nums[j]){  //递增
                    if(len[i] == len[j] + 1){
                        cnt[i] += cnt[j];
                    }else if(len[i] < len[j] + 1){
                        len[i] = len[j] + 1;
                        cnt[i] = cnt[j];
                    }
                }
                max_len = Math.max(max_len, len[i]);
            }
        }

        for (int i = 0; i < cnt.length; i++) {
            if (len[i] == max_len){
                res += cnt[i];
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{10,9,2,5,3,7,101,1};
//        int[] nums = new int[]{2, 2, 2, 2, 2};
//        System.out.println(lengthOfLIS(nums));
        System.out.println(findNumberOfLIS(nums));
    }

}


