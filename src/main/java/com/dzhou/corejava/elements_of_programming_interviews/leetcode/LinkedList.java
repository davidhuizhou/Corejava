package com.dzhou.corejava.elements_of_programming_interviews.leetcode;

public class LinkedList {

  /**
   * Add Two Numbers - https://leetcode.com/explore/interview/card/amazon/77/linked-list/513/
   */
  private static class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
      val = x;
    }
  }

  public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
    ListNode dummy = new ListNode(0);
    ListNode curr = dummy;
    ListNode p = l1, q = l2;
    int carry = 0;
    while (p != null || q != null) {
      if (p != null) {
        carry += p.val;
        p = p.next;
      }
      if (q != null) {
        carry += q.val;
        q = q.next;
      }
      curr.next = new ListNode(carry % 10);
      carry /= 10;
      curr = curr.next;
    }
    if (carry > 0) {
      curr.next = new ListNode(carry);
    }
    return dummy.next;
  }

}
