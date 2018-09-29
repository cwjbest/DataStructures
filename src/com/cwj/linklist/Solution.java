package com.cwj.linklist;

/**
 * Created by cwj on 18-4-9.
 * 给定一个排序链表，删除所有重复的元素使得每个元素只留下一个。
 * <p>
 * 案例：
 * 给定 1->1->2，返回 1->2
 * 给定 1->1->2->3->3，返回 1->2->3
 */
public class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        ListNode pre = new ListNode(head.val);
        ListNode p = head;
        while (head != null) {
            if (pre.val == head.next.val) {
                head.next = head.next.next;
            }
            pre = head;
            head = head.next;
        }
        return p;
    }
}
