package com.dzhou.corejava.elements_of_programming_interviews;

public class LinkedLists<T> {


  private static class Node<T> {
    private T data;
    private Node<T> next;

    public Node(T data, Node<T> next) {
      this.data = data;
      this.next = next;
    }
  }

  private static class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
      val = x;
    }
  }

  /**
   * Flatten Binary Tree to linked list. Each node's right child points to the next node in
   * pre-order travel;
   * 
   */
  private static void flatten(TreeNode root) {

    toList(root);

  }

  private static TreeNode toList(TreeNode root) {
    if (root == null) {
      return null;
    }
    if (root.left == null && root.right == null) {
      return root;
    }

    TreeNode rightTree = toList(root.right);
    root.right = toList(root.left);
    root.left = null;
    TreeNode curr = root;
    while (curr.right != null) {
      curr = curr.right;
    }
    curr.right = rightTree;
    return root;
  }

  


  // Search for a key
  public static Node<Integer> search(Node<Integer> L, int key) {
    while (L != null && L.data != key) {
      L = L.next;
    }
    return L;
  }

  // Insert a new node after a specified node
  public static void insertAfter(Node<Integer> node, Node<Integer> newNode) {
    newNode.next = node.next;
    node.next = newNode;
  }

  // Delete the node immediately following a node.
  public static void delete(Node<Integer> node) {
    if (node.next != null) {
      node.next = node.next.next;
    }
  }

  /**
   * Problem 8.1, Merge two sorted lists
   */
  public static Node<Integer> mergeTwoSortedList(Node<Integer> L1, Node<Integer> L2) {
    // Create a placeholder for the result
    Node<Integer> dummyHead = new Node<>(0, null);
    Node<Integer> current = dummyHead;
    Node<Integer> p = L1, q = L2;

    while (p != null && q != null) {
      if (p.data <= q.data) {
        current.next = p;
        p = p.next;
      } else {
        current.next = q;
        q = q.next;
      }
      current = current.next;
    }

    current.next = p != null ? p : q;
    return dummyHead.next;
  }


  /**
   * Problem 8.2 Reverse a single sublist. index starts at 1.
   */
  public static Node<Integer> reverseSublist(Node<Integer> L, int start, int finish) {
    if (start == finish)
      return L;

    Node<Integer> dummyHead = new Node<>(0, L);
    Node<Integer> sublistHead = dummyHead;
    int k = 1;
    while (k++ < start) {
      sublistHead = sublistHead.next;
    }

    // Reverse sublist.
    Node<Integer> sublistIter = sublistHead.next;
    while (start++ < finish) {
      Node<Integer> temp = sublistIter.next;
      if (temp == null)
        break;
      sublistIter.next = temp.next;
      temp.next = sublistHead.next;
      sublistHead.next = temp;
    }
    return dummyHead.next;

  }

  /**
   * Problem 8.3 Test for cycle
   * 
   */
  public static Node<Integer> hasCycle(Node<Integer> head) {
    Node<Integer> fast = head, slow = head;

    while (fast != null && fast.next != null) {
      slow = slow.next;
      fast = fast.next.next;

      if (slow == fast) {
        // There is a cycle, let's find out the cycle length
        int cycleLen = 0;
        do {
          ++cycleLen;
          fast = fast.next;

        } while (slow != fast);

        // Find the start of cycle
        Node<Integer> cycleLenIter = head;
        // advance the cycle length first
        while (cycleLen-- > 0) {
          cycleLenIter = cycleLenIter.next;
        }

        Node<Integer> iter = head;
        while (iter != cycleLenIter) {
          iter = iter.next;
          cycleLenIter = cycleLenIter.next;
        }
        return iter;

      }

    }

    // no cycle
    return null;
  }

  public static Node<Integer> addTwoNumbers(Node<Integer> L1, Node<Integer> L2) {

    Node<Integer> dummyHead = new Node<>(0, null);
    Node<Integer> iter = dummyHead;
    int carry = 0;
    while (L1 != null || L2 != null) {
      int sum = carry;
      if (L1 != null) {
        sum += L1.data;
        L1 = L1.next;
      }

      if (L2 != null) {
        sum += L2.data;
        L2 = L2.next;
      }

      iter.next = new Node<>(sum % 10, null);
      carry = sum / 10;
      iter = iter.next;

    }

    if (carry > 0) {
      iter.next = new Node<>(carry, null);
    }
    return dummyHead.next;

  }


  public static void main(String[] args) {
    TreeNode root = new TreeNode(1);
    root.left = new TreeNode(2);

    flatten(root);


  }



}
