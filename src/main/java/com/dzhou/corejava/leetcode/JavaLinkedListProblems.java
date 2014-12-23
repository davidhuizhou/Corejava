package com.dzhou.corejava.leetcode;

/**
 * Created by davidzhou on 12/23/14.
 */
public class JavaLinkedListProblems {

    /*
    *   Insertion Sort List -- LeetCode
    *   https://oj.leetcode.com/problems/insertion-sort-list/
    *   Sort a linked list using insertion sort.
     */
    public ListNode insertionSortList(ListNode head) {
        if (head == null)
            return null;
        ListNode helper = new ListNode(0);
        ListNode pre = helper;
        ListNode cur = head;
        while (cur != null) {
            ListNode next = cur.next;
            pre = helper;
            while (pre.next != null && pre.next.val <= cur.val) {
                pre = pre.next;
            }
            cur.next = pre.next;
            pre.next = cur;
            cur = next;
        }
        return helper.next;
    }


    /*
    *   Sort List -- LeetCode
    *   https://oj.leetcode.com/problems/sort-list/
    *   Sort a linked list in O(n log n) time using constant space complexity.
     */

    private ListNode mergeSort(ListNode head) {
        if (head == null || head.next == null)
            return head;
        ListNode walker = head;
        ListNode runner = head;
        while (runner.next != null && runner.next.next != null) {
            walker = walker.next;
            runner = runner.next.next;
        }
        ListNode head2 = walker.next;
        walker.next = null;
        head = mergeSort(head);
        head2 = mergeSort(head2);
        return merge(head, head2);
    }

    private static ListNode merge(ListNode list1, ListNode list2) {
        if (list1 == null)
            return list2;
        if (list2 == null)
            return list1;

        ListNode sentinel = new ListNode(0);
        ListNode pre = sentinel;
        ListNode cur;

        while (list1 != null && list2 != null) {
            if (list1.val <= list2.val) {
                cur = list1;
                list1 = list1.next;

            } else {
                cur = list2;
                list2 = list2.next;

            }
            pre.next = cur;
            cur.next = null;
            pre = cur;

        }

        if (list1 != null)
            pre.next = list1;
        if (list2 != null)
            pre.next = list2;
        return sentinel.next;

    }



}
