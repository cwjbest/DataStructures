package com.cwj.linklist;

/**
 * Created by cwj on 18-8-15.
 *
 */
public class ListNode {
    public int val;
    public ListNode next;

    public ListNode(int data, ListNode next) {
        this.val = data;
        this.next = next;
    }

    public ListNode(int data) {
        this.val = data;
    }
}
