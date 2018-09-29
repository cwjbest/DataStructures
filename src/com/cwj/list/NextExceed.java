package com.cwj.list;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

/**
 * Created by cwj on 18-9-17.
 * 给一个数组，返回一个大小相同的数组。返回的数组的第i个位置的值应当是，对于原数组中的第i个元素，
 * 至少往右走多少步，才能遇到一个比自己大的元素（如果之后没有比自己大的元素，或者已经是最后一个元素，
 * 则在返回数组的对应位置放上-1）。
 * 简单的例子：input: 5,3,1,2,4return: -1 3 1 1 -1
 * explaination: 对于第0个数字5，之后没有比它更大的数字，因此是-1，对于第1个数字3，
 * 需要走3步才能达到4（第一个比3大的元素），对于第2和第3个数字，都只需要走1步，就可以遇到比自己大的元素。
 * 对于最后一个数字4，因为之后没有更多的元素，所以是-1。

 思路：单调栈，解决区间最值问题
 */
public class NextExceed {
    public static int[] nextExceed(int[] nums){
        if (nums == null) return null;
        int[] res = new int[nums.length];
        Deque<Integer> stack = new LinkedList<>();
        for (int i = 0; i < nums.length; i++) {
            res[i] = -1;
            while (!stack.isEmpty() && nums[stack.peek()] < nums[i]){
                res[stack.peek()] = i-stack.pop();
            }
            stack.push(i);
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{5, 3, 1, 2, 4};
        int[] arr = nextExceed(nums);
        System.out.println(Arrays.toString(arr));
    }
}


/**
 *84. 柱状图中最大的矩形
 题目描述提示帮助提交记录社区讨论阅读解答
 给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。
 求在该柱状图中，能够勾勒出来的矩形的最大面积。
 以上是柱状图的示例，其中每个柱子的宽度为 1，给定的高度为 [2,1,5,6,2,3]。
 图中阴影部分为所能勾勒出的最大矩形面积，其面积为 10 个单位。

 示例:

 输入: [2,1,5,6,2,3]
 输出: 10
 *
 * 作者：法号桑菜
 链接：https://zhuanlan.zhihu.com/p/26465701
 来源：知乎
 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。

 那么到底为什么可以这么做？我们需要分析一下，每一个元素都要入栈一次，出栈一次。
 入栈的时候是for loop的iteration走到它的时候，那出栈的时候意味着什么呢。
 想清楚了这一点，我们也就理解了上面的答案。
 在上一题，每个元素出栈，是说明它找到了它在原数组中的next greater element.那这道题呢？
 元素出栈，意味着，我们已经计算了以它的顶为上边框的最大矩形。
 首先我们可以通过反证法轻松证明，最后的结果中的最大矩形的上边框，一定和某一个bar的顶重合，
 否则我们一定可以通过提高上边框来增加这个矩形的面积。这一步之后，我们还需要理解，
 这时候我们计算的矩形的左右边框都已经到达了极限。结合栈内元素的单调性，
 我们知道左边的边框是栈顶的元素+1，栈顶元素所对应的bar一定比出栈元素对应的bar小，
 所以以出栈元素对应的bar为高的矩形无法往左边延展。结合代码，我们知道右边的边框是正在处理的i，
 因为我们已经判断过这个第i个元素所对应的bar也一定比出栈元素对应的bar小，所以矩形无法往右边延展。
 这个元素和左右边框之间如果还有空隙，那么这些空隙里所存在的bar，一定是因为维护栈的单调性而被弹出了。
 换言之，这些bar如果存在，那么一定比这个出栈元素所对应的bar高。既然这些bar的高度更高，
 那么就可以被纳入这个最大矩形的计算中（例如一个“凹”字）。因此我们证明了，
 当我们将第i个元素弹出栈的时候，我们计算了以hight[i]为高的最大矩形的面积。
 */
class LargestRectangleArea_84{
    public static int largestRectangleArea(int[] heights) {
        if (heights == null) return 0;
        Deque<Integer> stack = new LinkedList<>();
        int[] height = new int[heights.length+1];
        System.arraycopy(heights, 0, height, 0, heights.length);
        height[heights.length] = 0;
        int maxArea = 0;
        for (int i = 0; i < height.length; i++) {
            while (!stack.isEmpty() && height[stack.peek()] >= height[i]){
                int h = height[stack.peek()];
                stack.pop();
                int sidx = stack.isEmpty() ? -1 : stack.peek();
                maxArea = Math.max(maxArea, h * (i - sidx - 1));
            }
            stack.push(i);
        }
        return maxArea;
    }
}

/**
 *
 给定一个仅包含 0 和 1 的二维二进制矩阵，找出只包含 1 的最大矩形，并返回其面积。

 示例:

 输入:
 [
 ["1","0","1","0","0"],
 ["1","0","1","1","1"],
 ["1","1","1","1","1"],
 ["1","0","0","1","0"]
 ]
 输出: 6

 把每一行都看成是上个题中的柱状图，这样每行的最大矩形就知道了
 不同的是要合并一下相邻的两行，如果上一行是0，不变，如果是1，就给他加1
 比如前两行，这样第二行的最大矩形就是前两行的最大矩形
 ["1","0","1","0","0"],
 ["2","0","2","0","0"],
 真是牛逼
 */
class MaximalRectangle_85{
    public int maximalRectangle(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0)
            return 0;
        int[] heights = new int[matrix[0].length];
        int maxArea = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                heights[j] = matrix[i][j] == '0' ? 0 : heights[j]+1;
            }
            maxArea = Math.max(maxArea, LargestRectangleArea_84.largestRectangleArea(heights));
        }
        return maxArea;
    }

    public static void main(String[] args) {
        int[][] nums = new int[][]{{1,0,0}, {1,1,0}, {0,1,1}};
        int[] he = new int[3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                he[j] = nums[i][j] == 0 ? 0 : ++he[j];
            }
            System.out.println(Arrays.toString(he));
        }

    }
}


