package com.cwj.linklist;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Created by cwj on 18-9-3.
 * 第一个版本真垃圾，不忍直视，完全不用考虑一长一短的情况，短的补0即可
 *
 */
public class AddTwoNumbers_2 {
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
         ListNode newHead = new ListNode(0);
         ListNode cur = newHead;
         int carry = 0;
         while(l1 != null && l2 != null){
             int sum = l1.val + l2.val + carry;
             cur.next = new ListNode(sum % 10);
             cur = cur.next;
             carry = sum / 10;
             l1 = l1.next;
             l2 = l2.next;
         }

         while(l1 != null){
             if(carry == 1){
                 int sum = l1.val + l2.val + carry;
                 cur.next = new ListNode(sum % 10);
                 cur = cur.next;
                 carry = sum / 10;
                 l1 = l1.next;
             }else{
                 cur.next = l1;
                 break;
             }
         }

         while(l2 != null){
             if(carry == 1){
                 int sum = l1.val + l2.val + carry;
                 cur.next = new ListNode(sum % 10);
                 cur = cur.next;
                 carry = sum / 10;
                 l2 = l2.next;
             }else{
                 cur.next = l2;
                 break;
             }
         }

         if(carry == 1){
             cur.next = new ListNode(1);
         }

         return newHead.next;
    }

    //改进版本
    public static ListNode addTwoNumbers2(ListNode l1, ListNode l2){
        ListNode l3 = new ListNode(0);
        ListNode p = l1, q = l2;
        ListNode cur = l3;
        int carry = 0;
        while (p != null || q != null){
            int x = (p == null ? 0:p.val);
            int y = (q == null ? 0:q.val);
            int sum = x + y + carry;
            //当需要构建新链表时，直接将当前节点的next指向一个新建的节点即可
            cur.next = new ListNode(sum % 10);
            cur = cur.next;
            carry = sum / 10;
            if(p != null)
                p = p.next;
            if(q != null)
                q = q.next;
        }
        if (carry == 1){
            cur.next = new ListNode(1);
        }
        return l3;
    }


    /**
     *
     * 445. 两数相加 II
     给定两个非空链表来代表两个非负整数。数字最高位位于链表开始位置。它们的每个节点只存储单个数字。将这两数相加会返回一个新的链表。
     你可以假设除了数字 0 之外，这两个数字都不会以零开头。
     进阶:
     如果输入链表不能修改该如何处理？换句话说，你不能对列表中的节点进行翻转。
     示例:
     输入: (7 -> 2 -> 4 -> 3) + (5 -> 6 -> 4)
     输出: 7 -> 8 -> 0 -> 7
     与上一个不同的是，这个得从后往前加
     头插，额外数组，栈
     不能改变链表结构，所以头插不行，栈比数组更高效。
     记得新链表的构建要头插，不然顺序会反
     */
    public static ListNode addTwoNumbers3(ListNode l1, ListNode l2) {
        Deque<ListNode> stack1 = new LinkedList<>();
        Deque<ListNode> stack2 = new LinkedList<>();
        ListNode p = l1;
        ListNode q = l2;
        while (p != null) {
            stack1.push(p);
            p = p.next;
        }
        while (q != null){
            stack2.push(q);
            q = q.next;
        }
        ListNode l3 = null;
        int carry = 0;
        while (!stack1.isEmpty() || !stack2.isEmpty()){
            int x = stack1.peek() == null ? 0 : stack1.pop().val;
            int y = stack2.peek() == null ? 0 : stack2.pop().val;
            int sum = x + y + carry;
            p = new ListNode(sum % 10);
            p.next = l3;
            l3 = p;
            carry = sum / 10;
        }
        if (carry == 1){
            p = new ListNode(1);
            p.next = l3;
            l3 = p;
        }
        return l3;
    }
    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(8);
        ListNode node3 = new ListNode(0);
        node1.next = node2;

        addTwoNumbers3(node1, node2);
    }
}
