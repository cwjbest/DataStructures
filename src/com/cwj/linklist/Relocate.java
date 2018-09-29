package com.cwj.linklist;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by cwj on 18-8-15.
 * 1.给定一个链表list，
 如果：
 list = 1 调整之后1。
 list = 1->2 调整之后1->2
 list = 1->2->3 调整之后1->2->3
 list = 1->2->3->4 调整之后1->3->2->4
 list = 1->2->3->4->5 调整之后1->3->2->4->5
 list = 1->2->3->4->5->6 调整之后1->4->2->5->3->6
 list = 1->2->3->4->5->6->7 调整之后1->4->2->5->3->6->7
 根据上面的规律， 调整一个任意长度的链表。

 分析题意，可知有两种情况：
 当链表长度为奇数时，L1L2L3L4R1R2R3R4 应调整成 L1R1L2R2L3R3L4R4 的形式
 当链表长度为偶数时，L1L2L3L4R1R2R3R4R5 应调整成 L1R1L2R2L3R3L4R4R5 的形式
 所以对于该题，有两种方法：
 方法一：先将整条链表存入数组中，然后根据调整规律，对于 前 n/2个元素，i 索引处的元素应存放在调整后数组的 2*i 处 ，
 对于后 n/2 个元素，i索引处的元素应存放在调整后数组的 （i-n/2）* 2+1处。而奇数长度的链表最后一个元素直接加在最后即可。
 举例：原数组为{1，2，3，4，5，6}，调整后数组应为{1，3，2，4，5，6}，
 每个元素对应的索引由{0，1，2，3，4，5}变成了{0，2，4，1，3，5}
 最后将调整后的数组串成链表

 方法二：用快慢指针法找到中间位置,列表找中间的经典算法，分成左右两个链表，然后合并两个链表
 */
public class Relocate {
    public static void relocate1(ListNode head){
        if (head == null || head.next == null || head.next.next == null){
            return;
        }
        int size = 0;
        ListNode cur = head;
        while (cur != null){
            size++;
            cur = cur.next;
        }
        cur = head;
        ListNode[] arr = new ListNode[(size & 1) == 1 ? (size-1):size];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = cur;
            cur = cur.next;
        }
        ListNode[] help = new ListNode[arr.length];
        for (int i = 0; i < arr.length; i++) {
            if (i < size/2){
                help[i*2] = arr[i];
            }else {
                help[(i-arr.length/2)*2+1] = arr[i];
            }
        }
        ListNode cur2 = head;
        for (int i = 1; i < help.length; i++) {
            ListNode ListNode = help[i];
            cur2.next = ListNode;
            cur2 = cur2.next;
        }
        while (head != null){
            System.out.print(head.val + " ");
            head = head.next;
        }
    }

    public static void relocate2(ListNode head){
        if (head == null || head.next == null || head.next.next == null)
            return;
        //定义快慢指针找到中间位置
        // 1 -> 2 -> 3 -> 4，最终mid停在2处
        // 1 -> 2 -> 3 -> 4 -> 5,最终mid依旧停在2处
        ListNode slow = head;
        ListNode fast = head.next;
        while (fast.next != null && fast.next.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode mid = slow;
        //分成左右两个链，如 左链是  1 -> 2,右链是3 -> 4 -> 5
        ListNode rHead = slow.next;
        mid.next = null;
        ListNode lHead = head;

        mergeList(lHead, rHead);

        while (lHead != null){
            System.out.print(lHead.val + " ");
            lHead = lHead.next;
        }

    }

    /*
     *  1 2 3
     *  4 5 6 7
     *  连接顺序为 4连2,1连4,(注意4连2之后会丢失5，所以要先记住5)这是一次循环，然后5连3,2连5，这又是一次循环
     *  然后退出循环，3连6
     */
    private static void mergeList(ListNode lHead, ListNode rHead){
        //起初lCur指向1，rCur指向4
        ListNode lCur = lHead;
        ListNode rCur = rHead;

        //当lCur指向3时，跳出循环
        while (lCur.next != null){
            //将5先存起来
            ListNode rNext = rCur.next;
            //4->2
            rCur.next = lCur.next;
            lCur.next = rCur;
            //lCur,rCur迭代
            lCur = rCur.next;
            rCur = rNext;
        }
        lCur.next = rCur;
    }

    public static void main(String[] args) {
        ListNode ListNode1 = new ListNode(1);
        ListNode ListNode2 = new ListNode(2);
        ListNode ListNode3 = new ListNode(3);
        ListNode ListNode4 = new ListNode(4);
        ListNode ListNode5 = new ListNode(5);
        ListNode ListNode6 = new ListNode(6);
        ListNode ListNode7 = new ListNode(7);
        ListNode1.next = ListNode2;
        ListNode2.next = ListNode3;
        ListNode3.next = ListNode4;
        ListNode4.next = ListNode5;
        ListNode5.next = ListNode6;
        ListNode6.next = ListNode7;
//        Relocate.relocate1(ListNode1);
        Relocate.relocate2(ListNode1);

    }
}
