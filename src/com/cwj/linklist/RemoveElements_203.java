package com.cwj.linklist;

/**
 * Created by cwj on 18-9-1.
 * 203. 删除链表中的节点
 题目描述提示帮助提交记录社区讨论阅读解答
 删除链表中等于给定值 val 的所有节点。

 示例:

 输入: 1->2->6->3->4->5->6, val = 6
 输出: 1->2->3->4->5

 考虑头部就是一个或多个val，有头部删除的这种题，最好在头部加一个没用的节点
 然后用cur.next!=null遍历，这样可以省一个pre节点
 */
public class RemoveElements_203 {
    public ListNode removeElements(ListNode head, int val) {
        if(head == null) return head;
        ListNode pre = new ListNode(0);
        pre.next = head;
        ListNode cur = pre;
        while(cur.next != null){
            if(cur.next.val == val)
                cur.next = cur.next.next;
            else
                cur = cur.next;
        }
        return pre.next;
    }

    /**
     *
     * 递归太精妙了，我等凡人想不出
     * @param head
     * @param val
     * @return
     */
    public ListNode removeElementsByRe(ListNode head, int val) {
        if(head == null) return null;
        head.next = removeElementsByRe(head.next, val);
        return head.val == val ? head.next : head;
    }

    /**
     * 237. 删除链表中的节点
     请编写一个函数，使其可以删除某个链表中给定的（非末尾）节点，你将只被给定要求被删除的节点。

     现有一个链表 -- head = [4,5,1,9]，它可以表示为:

     4 -> 5 -> 1 -> 9
     示例 1:

     输入: head = [4,5,1,9], node = 5
     输出: [4,1,9]
     解释: 给定你链表中值为 5 的第二个节点，那么在调用了你的函数之后，该链表应变为 4 -> 1 -> 9.
     示例 2:

     输入: head = [4,5,1,9], node = 1
     输出: [4,5,9]
     解释: 给定你链表中值为 1 的第三个节点，那么在调用了你的函数之后，该链表应变为 4 -> 5 -> 9.
     说明:

     链表至少包含两个节点。
     链表中所有节点的值都是唯一的。
     给定的节点为非末尾节点并且一定是链表中的一个有效节点。
     不要从你的函数中返回任何结果。

     函数只给了这个要删除的节点，这怎么搞？
     ABCD，要删除的是C，先将C用D覆盖 ABDD，再将后面的D删除
     删除必须要有前驱，没有的时候怎么办？用后面的覆盖掉
     真是精妙啊
     */
    public void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }
}
