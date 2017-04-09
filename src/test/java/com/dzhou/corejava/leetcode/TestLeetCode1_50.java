package com.dzhou.corejava.leetcode;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Created by huizhou on 11/6/16.
 */
public class TestLeetCode1_50 {

  @Test
  public void testTwoSum() {
    int[] nums = new int[]{2, 7, 11, 15};
    int[] result = LeetCode1_50.twoSum(nums, 9);
    assertEquals(0, result[0]);
    assertEquals(1, result[1]);

    try{
      result = LeetCode1_50.twoSum(nums, 14);
    } catch(Exception e){

    }
  }

  @Test
  public void testAddTwoNumbers() {
    ListNode l1 = new ListNode(2);
    l1.next = new ListNode(4);
    l1.next.next = new ListNode(3);

    ListNode l2 = new ListNode(5);
    l2.next = new ListNode(6);
    l2.next.next = new ListNode(4);

    ListNode l = LeetCode1_50.addTwoNumbers(l1, l2);
    assertEquals(7, l.val);
    assertEquals(0, l.next.val);
    assertEquals(8, l.next.next.val);


  }

  @Test
  public void testLengthOfLongestSubstring() {
    assertEquals(3, LeetCode1_50.lengthOfLongestSubstring("abcabcbb"));
    assertEquals(3, LeetCode1_50.lengthOfLongestSubstring1("abcabcbb"));

    assertEquals(1, LeetCode1_50.lengthOfLongestSubstring("bbbbbb"));
    assertEquals(1, LeetCode1_50.lengthOfLongestSubstring1("bbbbbb"));

  }

  @Test
  public void testFindMedianSortedArrays() {
    int[] A = new int[]{1, 3};
    int[] B = new int[]{2};
    int[] C = new int[]{4, 5};
    assertEquals(2.0, LeetCode1_50.findMedianSortedArrays(A, B), 0.001);
    assertEquals(3.5, LeetCode1_50.findMedianSortedArrays(A, C), 0.001);
  }

  @Test
  public void testLongestPalindrome() {

    assertEquals("a", LeetCode1_50.longestPalindrome("a"));
    assertEquals("aa", LeetCode1_50.longestPalindrome("aa"));
    assertEquals("aba", LeetCode1_50.longestPalindrome("aba"));
    assertEquals("b", LeetCode1_50.longestPalindrome("ab"));
    assertEquals("abba", LeetCode1_50.longestPalindrome("abba"));

    assertEquals("a", LeetCode1_50.longestPalindrome1("a"));
    assertEquals("aa", LeetCode1_50.longestPalindrome1("aa"));
    assertEquals("aba", LeetCode1_50.longestPalindrome1("aba"));
    assertEquals("a", LeetCode1_50.longestPalindrome1("ab"));
    assertEquals("abba", LeetCode1_50.longestPalindrome1("abba"));

    assertEquals("a", LeetCode1_50.longestPalindrome2("a"));
    assertEquals("aa", LeetCode1_50.longestPalindrome2("aa"));
    assertEquals("aba", LeetCode1_50.longestPalindrome2("aba"));
    assertEquals("a", LeetCode1_50.longestPalindrome2("ab"));
    assertEquals("abba", LeetCode1_50.longestPalindrome2("abba"));
  }


}
