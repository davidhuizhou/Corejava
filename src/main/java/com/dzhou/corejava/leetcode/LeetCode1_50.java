package com.dzhou.corejava.leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by huizhou on 10/3/16.
 */
public class LeetCode1_50 {

  /**
   * Given an array of integers, return indices of the two numbers such that they add up to a
   * specific target.
   *
   * You may assume that each input would have exactly one solution.
   *
   * Example: Given nums = [2, 7, 11, 15], target = 9,
   *
   * Because nums[0] + nums[1] = 2 + 7 = 9, return [0, 1].
   */
  public int[] twoSum(int[] nums, int target) {
    Map<Integer, Integer> map = new HashMap<>();
    for (int i = 0; i < nums.length; i++) {
      int complement = target - nums[i];
      if (map.containsKey(complement)) {
        return new int[]{map.get(complement), i};
      }
      map.put(nums[i], i);
    }
    throw new IllegalArgumentException("No two sum solution");
  }

  /**
   * You are given two linked lists representing two non-negative numbers. The digits are stored
   * in reverse order and each of their nodes contain a single digit. Add the two numbers and
   * return it as a linked list.
   *
   * Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
   * Output: 7 -> 0 -> 8
   */
  public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
    ListNode dummyHead = new ListNode(0);
    ListNode p = l1, q = l2, curr = dummyHead;
    int carry = 0;
    while (p != null || q != null) {
      int x = (p != null) ? p.val : 0;
      int y = (q != null) ? q.val : 0;
      int sum = carry + x + y;
      carry = sum / 10;
      curr.next = new ListNode(sum % 10);
      curr = curr.next;
      if (p != null) p = p.next;
      if (q != null) q = q.next;
    }
    if (carry > 0) {
      curr.next = new ListNode(carry);
    }
    return dummyHead.next;
  }

  /**
   * Given a string, find the length of the longest substring without repeating characters.
   *
   * Examples:
   *
   * Given "abcabcbb", the answer is "abc", which the length is 3.
   *
   * Given "bbbbb", the answer is "b", with the length of 1.
   *
   * Given "pwwkew", the answer is "wke", with the length of 3. Note that the answer must be a
   * substring, "pwke" is a subsequence and not a substring.
   */

  public int lengthOfLongestSubstring(String s) {
    int n = s.length();
    Set<Character> set = new HashSet<>();
    int ans = 0, i = 0, j = 0;
    while (i < n && j < n) {
      // try to extend the range [i, j]
      if (!set.contains(s.charAt(j))) {
        set.add(s.charAt(j++));
        ans = Math.max(ans, j - i);
      } else {
        set.remove(s.charAt(i++));
      }
    }
    return ans;
  }

  public int lengthOfLongestSubstring2(String s) {
    int n = s.length(), ans = 0;
    Map<Character, Integer> map = new HashMap<>(); // current index of character
    // try to extend the range [i, j]
    for (int j = 0, i = 0; j < n; j++) {
      if (map.containsKey(s.charAt(j))) {
        i = Math.max(map.get(s.charAt(j)), i);
      }
      ans = Math.max(ans, j - i + 1);
      map.put(s.charAt(j), j + 1);
    }
    return ans;
  }


  /**
   * There are two sorted arrays nums1 and nums2 of size m and n respectively.
   *
   * Find the median of the two sorted arrays. The overall run time complexity should be O(log
   * (m+n)).
   *
   * Example 1: nums1 = [1, 3] nums2 = [2]
   *
   * The median is 2.0 Example 2: nums1 = [1, 2] nums2 = [3, 4]
   *
   * The median is (2 + 3)/2 = 2.5
   */
  public double findMedianSortedArrays(int A[], int B[]) {
    int len = A.length + B.length;
    if (len % 2 == 1) {
      return findKth(A, 0, B, 0, len / 2 + 1);
    } else {
      return (findKth(A, 0, B, 0, len / 2) + findKth(A, 0, B, 0, len / 2 + 1)) / 2.0;
    }
  }


  public static int findKth(int[] A, int A_start, int[] B, int B_start, int k) {
    if (A_start >= A.length) {
      return B[B_start + k - 1];
    }
    if (B_start >= B.length) {
      return A[A_start + k - 1];
    }
    if (k == 1) {
      return Math.min(A[A_start], B[B_start]);
    }
    int A_key = A_start + k / 2 - 1 < A.length ? A[A_start + k / 2 - 1] : Integer.MAX_VALUE;
    int B_key = B_start + k / 2 - 1 < B.length ? B[B_start + k / 2 - 1] : Integer.MAX_VALUE;

    if (A_key < B_key) {
      return findKth(A, A_start + k / 2, B, B_start, k - k / 2);
    } else {
      return findKth(A, A_start, B, B_start + k / 2, k - k / 2);
    }

  }

  public static void main(String[] args) {
    LeetCode1_50 leetCode = new LeetCode1_50();

    int[] A = new int[]{1, 3};
    int[] B = new int[]{2};

    System.out.println(leetCode.findMedianSortedArrays(A, B));
  }

}
