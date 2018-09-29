package com.cwj.linklist;


/**
 * Created by cwj on 18-9-3.
 *24. 两两交换链表中的节点
 给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。

 示例:

 给定 1->2->3->4, 你应该返回 2->1->4->3.
 说明:

 你的算法只能使用常数的额外空间。
 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。

 注意，head是引用
 ListNode p = head;
 p和head指向同一块地址，如果p.next改变，head.next也会跟着改变
 但是遍历链表呢？
 ListNode p = head;
 while(p != null){
    p = p.next;
 }
 return head;
 为什么head不会跟着p变呢，p重新指向了p.next,不再指向head！！！！
 */
public class SwapPairs_24 {
    public static ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null)
            return head;
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode pre = dummy;
        while (pre.next != null && pre.next.next != null){
            ListNode node1 = pre.next;
            ListNode node2 = node1.next;
            ListNode node3 = node2.next;

            pre.next = node2;
            node1.next = node3;
            node2.next = node1;
            pre = node1;
        }
        return dummy.next;
    }

    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;

        swapPairs(node1);
        int[] news  = new int[]{1, 2};
    }
}
