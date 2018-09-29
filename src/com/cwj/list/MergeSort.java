package com.cwj.list;

import com.cwj.linklist.ListNode;

import java.util.Arrays;
import java.util.List;

/**
 * Created by cwj on 18-8-28.
 * 二路归并排序
 */
public class MergeSort {
    public static void main(String[] args) {
        int[] arr = {9, 7, 8, 5, 6, 4, 3, 1, 2};
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void sort(int[] arr){
        int[] temp = new int[arr.length];
        sort(arr, 0, arr.length-1, temp);
    }

    private static void sort(int[] arr, int left, int right, int[] temp){
        if (left < right){
            int mid = left + (right - left)/2;
            sort(arr, left, mid, temp);
            sort(arr, mid + 1, right, temp);
            merge(arr, left, mid, right, temp);
        }
    }

    private static void merge(int[] arr, int left, int mid, int right, int[] temp){
        int i = left;
        int j = mid + 1;
        int t = 0;
        while (i <= mid && j <= right){
            temp[t++] = arr[i] < arr[j] ? arr[i++] : arr[j++];
        }
        while (i <= mid)
            temp[t++] = arr[i++];
        while (j <= right)
            temp[t++] = arr[j++];
        t = 0;
        while (left <= right)
            arr[left++] = temp[t++];
    }
}

/**
 * 148. 排序链表
 题目描述提示帮助提交记录社区讨论阅读解答
 在 O(n log n) 时间复杂度和常数级空间复杂度下，对链表进行排序。

 示例 1:

 输入: 4->2->1->3
 输出: 1->2->3->4
 示例 2:

 输入: -1->5->3->4->0
 输出: -1->0->3->4->5
 *
 * 归并排序
 */
class MergeSortList_148 {
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null)
            return head;
        ListNode fast = head;
        ListNode slow = head;
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        fast = slow.next;
        slow.next = null;
        slow = sortList(head);
        fast = sortList(fast);
        return merge(slow, fast);
    }

    private static ListNode merge(ListNode left, ListNode right){
        if (left == null) return right;
        if (right == null) return left;
        ListNode res;
        ListNode p;
        if (left.val < right.val){
            res = left;
            left = left.next;
        }else {
            res = right;
            right = right.next;
        }
        p = res;
        while (left != null && right != null){
            if (left.val < right.val){
                p.next = left;
                left = left.next;
            }else {
                p.next = right;
                right = right.next;
            }
            p = p.next;
        }
        if (left != null)
            p.next = left;
        if (right != null)
            p.next = right;
        return res;
    }
}

/**
 *147. 对链表进行插入排序
 对链表进行插入排序。

 插入排序的动画演示如上。从第一个元素开始，该链表可以被认为已经部分排序（用黑色表示）。
 每次迭代时，从输入数据中移除一个元素（用红色表示），并原地将其插入到已排好序的链表中。

 插入排序算法：

 插入排序是迭代的，每次只移动一个元素，直到所有元素可以形成一个有序的输出列表。
 每次迭代中，插入排序只从输入数据中移除一个待排序的元素，找到它在序列中适当的位置，并将其插入。
 重复直到所有输入数据插入完为止。

 示例 1：

 输入: 4->2->1->3
 输出: 1->2->3->4
 示例 2：

 输入: -1->5->3->4->0
 输出: -1->0->3->4->5
 */
class InsertionSortList_147{
    public ListNode insertionSortList(ListNode head) {
        if (head == null || head.next == null)
            return head;
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode p = head;
        while (p.next != null){
            if (p.val <= p.next.val)
                p = p.next;
            else {
                ListNode tmp = p.next;
                ListNode q = dummy;
                p.next = p.next.next;
                while (q.next.val < tmp.val)
                    q = q.next;
                tmp.next = q.next;
                q.next = tmp;
            }
        }
        return dummy.next;
    }
}