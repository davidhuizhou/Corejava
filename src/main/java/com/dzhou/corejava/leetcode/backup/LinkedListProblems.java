package com.dzhou.corejava.leetcode.backup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by davidzhou on 12/23/14.
 */
public class LinkedListProblems {


  public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
    ListNode dummyNode = new ListNode(0);
    ListNode p = l1, q = l2, curr = dummyNode;
    int carry = 0;
    while (p != null || q != null) {
      int x = (p != null) ? p.val : 0;
      int y = (q != null) ? q.val : 0;
      int sum = carry + x + y;
      carry = sum / 10;
      curr.next = new ListNode(sum % 10);
      if (p != null) {
        p = p.next;
      }
      if (q != null) {
        q = q.next;
      }
    }
    if (carry > 0) {
      curr.next = new ListNode(carry);
    }
    return dummyNode.next;
  }

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

  /**
   * https://oj.leetcode.com/problems/linked-list-cycle-ii/
   * Given a linked list, return the node where the cycle begins. If there is no cycle, return
   * null.
   */
  ListNode findBeginning(ListNode head) {
    ListNode slow = head;
    ListNode fast = head;

    while (fast != null && fast.next != null) {
      slow = slow.next;
      fast = fast.next.next;

      if (slow == fast)
        break;
    }

    if (fast == null || fast.next == null)
      return null;

    slow = head;
    while (slow != fast) {
      slow = slow.next;
      fast = fast.next;
    }

    return fast;

  }

  /**
   * Reorder List -- LeetCode
   * https://oj.leetcode.com/problems/reorder-list/
   * Given a singly linked list L: L0→L1→…→Ln-1→Ln,
   * reorder it to: L0→Ln→L1→Ln-1→L2→Ln-2→…
   * You must do this in-place without altering the nodes' values.
   * For example,
   * Given {1,2,3,4}, reorder it to {1,4,2,3}.
   */
  public void reorderList(ListNode head) {
    if (head == null || head.next == null) {
      return;
    }
    ListNode walker = head;
    ListNode runner = head;
    while (runner.next != null && runner.next.next != null) {
      walker = walker.next;
      runner = runner.next.next;
    }
    ListNode head1 = head;
    ListNode head2 = walker.next;
    walker.next = null;
    head2 = reverse(head2);
    while (head1 != null && head2 != null) {
      ListNode next = head2.next;
      head2.next = head1.next;
      head1.next = head2;
      head1 = head2.next;
      head2 = next;
    }
  }

  private ListNode reverse(ListNode head) {
    ListNode pre = null;
    ListNode cur = head;
    while (cur != null) {
      ListNode next = cur.next;
      cur.next = pre;
      pre = cur;
      cur = next;
    }
    return pre;
  }

  //Recursive reverse
  public ListNode recursive_reverse(ListNode head) {
    if (head == null || head.next == null)
      return head;
    return recursive_reverse(head, head.next);
  }

  private ListNode recursive_reverse(ListNode current, ListNode next) {
    if (next == null)
      return current;
    ListNode newHead = recursive_reverse(current.next, next.next);
    next.next = current;
    current.next = null;
    return newHead;
  }

  /**
   * Rotate List -- LeetCode
   * https://oj.leetcode.com/problems/rotate-list/
   * Given a list, rotate the list to the right by k places, where k is non-negative.
   * For example:
   * Given 1->2->3->4->5->NULL and k = 2,
   * return 4->5->1->2->3->NULL.
   */
  public ListNode rotateRight(ListNode head, int n) {

    if (n == 0 || head == null)
      return head;

    //Get the length of the list
    int len = 1;
    ListNode node = head;
    while (node.next != null) {
      node = node.next;
      len++;
    }

    if (len == 1 || n % len == 0)
      return head;

    int offset = len - n % len;
    ListNode prev = head;
    while (offset > 1) {
      prev = prev.next;
      offset--;
    }
    ListNode newHead = prev.next;
    prev.next = null;
    node.next = head;
    return newHead;

  }

  /**
   * Copy List with Random Pointer -- LeetCode https://oj.leetcode
   * .com/problems/copy-list-with-random-pointer/
   * A linked list is given such that each node contains an additional random pointer which could
   * point to any node in the list or null. Return a deep copy of the list.
   */
  public RandomListNode copyRandomList(RandomListNode head) {
    if (head == null)
      return head;
    HashMap<RandomListNode, RandomListNode> map = new HashMap<RandomListNode, RandomListNode>();
    RandomListNode newHead = new RandomListNode(head.label);
    map.put(head, newHead);
    RandomListNode pre = newHead;
    RandomListNode node = head.next;
    while (node != null) {
      RandomListNode newNode = new RandomListNode(node.label);
      map.put(node, newNode);
      pre.next = newNode;
      pre = newNode;
      node = node.next;
    }
    node = head;
    RandomListNode copyNode = newHead;
    while (node != null) {
      copyNode.random = map.get(node.random);
      copyNode = copyNode.next;
      node = node.next;
    }
    return newHead;
  }

  public RandomListNode copyRandomList1(RandomListNode head) {
    if (head == null)
      return head;
    RandomListNode node = head;
    while (node != null) {
      RandomListNode newNode = new RandomListNode(node.label);
      newNode.next = node.next;
      node.next = newNode;
      node = newNode.next;
    }
    node = head;
    while (node != null) {
      if (node.random != null)
        node.next.random = node.random.next;
      node = node.next.next;
    }
    RandomListNode newHead = head.next;
    node = head;
    while (node != null) {
      RandomListNode newNode = node.next;
      node.next = newNode.next;
      if (newNode.next != null)
        newNode.next = newNode.next.next;
      node = node.next;
    }
    return newHead;
  }

  public static int longestValidParentheses(String s) {
    if (s == null)
      return 0;

    int longest = 0;
    int start = 0;
    LinkedList<Integer> stack = new LinkedList<Integer>();
    for (int i = 0; i < s.length(); i++) {
      if (s.charAt(i) == '(') {
        stack.push(i);
      } else {
        if (stack.isEmpty()) {
          start = i + 1;
        } else {
          stack.pop();
          if (stack.isEmpty()) {
            longest = Math.max(longest, i - start + 1);
          } else {
            longest = Math.max(longest, i - stack.peek());
          }
        }
      }

    }
    return longest;
  }


  public List<Integer> inorderTraversal(TreeNode root) {
    List<Integer> res = new ArrayList<Integer>();
    if (root == null)
      return res;
    LinkedList<TreeNode> stack = new LinkedList<TreeNode>();
    while (root != null || !stack.isEmpty()) {
      if (root != null) {
        stack.push(root);
        root = root.left;
      } else {
        root = stack.pop();
        res.add(root.val);
        root = root.right;

      }
    }

    return res;

  }

  public List<Integer> preorderTraversal(TreeNode root) {
    List<Integer> res = new ArrayList<Integer>();
    TreeNode cur = root;
    TreeNode pre = null;
    while (cur != null) {
      if (cur.left == null) {
        res.add(cur.val);
        cur = cur.right;

      } else {
        pre = cur.left;
        while (pre.right != null && pre.right != cur)
          pre = pre.right;
        if (pre.right == null) {
          res.add(cur.val);
          pre.right = cur;
          cur = cur.left;

        } else {
          pre.right = null;
          cur = cur.right;
        }

      }

    }
    return res;
  }
}
