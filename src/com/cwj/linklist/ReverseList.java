package com.cwj.linklist;

/**
 * Created by cwj on 18-8-17.
 * 206. 反转链表
 反转一个单链表。

 示例:

 输入: 1->2->3->4->5->NULL
 输出: 5->4->3->2->1->NULL
 进阶:
 你可以迭代或递归地反转链表。你能否用两种方法解决这道题？

 思路1：头插法反转链表
 思路2：递归
 1->2->3->4
 1. 当递进到最后一个节点时，将这个节点(4)返回
 2. 归约到上一步，此时newHead=4， head=3,我们要将4插入到3之后
    head.next.next = head;//将3接到4后面
    3.next.next = 3
    head.next = null;//切断3到4的指针
    3.next = null
                         1
                         |
                         2
                         |
    此时链表变成了这样：4->3
 3. 归约到上一步，此时newHead=4, head=2, 将2插入到3后面
    .....
 4.newHead是一直不变的，也是最后要返回的头结点

 */
public class ReverseList {
    public static ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode next = null;
        ListNode pre = null;
        while(head != null){
            next = head.next;//当前节点的下一个节点得记住
            head.next = pre;//将上一个节点接到当前节点之后
            //这里头结点的上一个节点是空，不太好想，可以将第二个节点当做头结点来模拟
            //这是一种思维，可以将边界后延

            //两个指针下沿
            pre = head;
            head = next;
        }
        return pre;
    }

    /**
     * 1->2->3->4
     1. 当递进到最后一个节点时，将这个节点(4)返回
     2. 归约到上一步，此时newHead=4， head=3,我们要将3插入到4之后
     head.next.next = head;//将3接到4后面
     3.next.next = 3
     head.next = null;//切断3到4的指针
     3.next = null
                           1
                           |
                           2
                           |
     此时链表变成了这样：4->3
     3. 归约到上一步，此时newHead=4, head=2, 将2插入到3后面
     .....
     4.newHead是一直不变的，也是最后要返回的头结点
     *
     */
    public static ListNode reverseList2(ListNode head){
        if (head == null || head.next == null) return head;
        ListNode newHead = reverseList2(head.next);
        head.next.next = head;
        head.next = null;
        return newHead;
    }
}
