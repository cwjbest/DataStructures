package com.cwj.list;

/**
 * Created by cwj on 18-9-5.
 * 11. 盛最多水的容器
 * 给定 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。在坐标内画 n 条垂直线，
 * 垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0)。找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
 * <p>
 * 说明：你不能倾斜容器，且 n 的值至少为 2。
 * 图中垂直线代表输入数组 [1,8,6,2,5,4,8,3,7]。在此情况下，容器能够容纳水（表示为蓝色部分）的最大值为 49。
 * 示例:
 * <p>
 * 输入: [1,8,6,2,5,4,8,3,7]
 * 输出: 49
 * 暴力法
 */
public class MaxArea_11 {
    public int maxArea(int[] height) {
        int max = 0;
        int len = height.length;
        for (int i = 1; i < len; i++) {
            for (int j = i - 1; j >= 0; j--) {
                max = Math.max(Math.min(height[i], height[j]) * (i - j), max);
            }
        }
        return max;
    }

    /**
     * 双指针法，每次将最小的剔除，牛逼
     * 涉及到两边遍历的一定要想能不能双指针
     * @param height
     * @return
     */
    public int maxArea2(int[] height) {
        int max = 0;
        int l = 0;
        int r = height.length-1;
        while (l < r) {
            max = Math.max(Math.min(height[l], height[r]) * (r-l), max);
            if (height[l] < height[r])
                l++;
            else
                r--;
        }
        return max;
    }

    /**
     *
     * 42. 接雨水
     题目描述提示帮助提交记录社区讨论阅读解答
     给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
     上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。

     示例:

     输入: [0,1,0,2,1,0,1,3,2,1,2,1]
     输出: 6
     双指针法，最小的先动
     */
    public static int trap(int[] height) {
        if (height.length < 3) return 0;
        int sum = 0;
        int lmax = 0, rmax = 0;
        int l = 0, r = height.length-1;
        while (l < r){
            lmax = Math.max(lmax, height[l]);
            rmax = Math.max(rmax, height[r]);
            if (lmax < rmax){
                sum += (lmax - height[l]);
                l++;
            }else {
                sum += (rmax - height[r]);
                r--;
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{0,1,0,2,1,0,1,3,2,1,2,1};
//        int[] nums = new int[]{4, 2, 3};
        System.out.println(trap(nums));
    }
}
