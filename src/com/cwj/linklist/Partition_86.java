package com.cwj.linklist;

/**
 * Created by cwj on 18-8-31.
 *86. 分隔链表
 给定一个链表和一个特定值 x，对链表进行分隔，使得所有小于 x 的节点都在大于或等于 x 的节点之前。

 你应当保留两个分区中每个节点的初始相对位置。

 示例:

 输入: head = 1->4->3->2->5->2, x = 3
 输出: 1->2->2->4->3->5

 思路：双链表法
 h1 -> 1 -> 2 -> 2
 h2 -> 4 -> 3 -> 5
 */
public class Partition_86 {
    public ListNode partition(ListNode head, int x) {
        if (head == null || head.next == null)
            return head;
        ListNode cur = head;
        //这四个节点初始值可以随便赋值，最后只要从next返回即可
        //如果不初始化的话可能会空指针，链表的一个小技巧
        ListNode cur_min = new ListNode(0);
        ListNode cur_max = new ListNode(0);
        ListNode pre_min = cur_min;
        ListNode pre_max = cur_max;

        while (cur != null){
            if (cur.val < x){
                cur_min.next = cur;
                cur_min = cur;
            }
            else {
                cur_max.next = cur;
                cur_max = cur;
            }
            cur = cur.next;
        }

        cur_min.next = pre_max.next;
        cur_max.next = null;
        return pre_min.next;
    }
}
