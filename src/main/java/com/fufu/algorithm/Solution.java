package com.fufu.algorithm;

import com.fufu.algorithm.sort.ListNode;

/**
 * Created by zhoujunfu on 2018/7/31.
 */
class Solution {

    public ListNode middleNode(ListNode head) {

        if (head == null || head.next == null) {
            return head;
        }

        ListNode fast = head;
        ListNode slow = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;

    }
}