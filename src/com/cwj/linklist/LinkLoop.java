package com.cwj.linklist;

/**
 * Created by cwj on 18-6-25.
 * 判断链表是否有环：快慢指针，如果有环一定相遇
 * 快指针每次走两步，慢指针走一步
 */
public class LinkLoop {

    public static boolean isLoop(ListNode ListNode) {
        ListNode slow = ListNode;
        ListNode fast = ListNode.next;

        while (slow.next != null) {
            Object dataSlow = slow.val;
            Object dataFast = fast.val;

            //有环
            if (dataFast == dataSlow) {
                return true;
            }

            //只有两个节点且无环
            if (fast.next == null) {
                return false;
            }

            slow = slow.next;
            fast = fast.next.next;

            //快指针发现为空，则无环
            if (fast == null) {
                return false;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        ListNode ListNode1 = new ListNode(1);
        ListNode ListNode2 = new ListNode(2);
        ListNode ListNode3 = new ListNode(3);
        ListNode1.next = ListNode2;
        ListNode2.next = ListNode3;
        ListNode3.next = ListNode1;
        boolean isLoop = isLoop(ListNode1);
        System.out.println(isLoop);
    }
}
