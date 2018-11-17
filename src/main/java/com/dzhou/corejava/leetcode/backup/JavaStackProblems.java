package com.dzhou.corejava.leetcode.backup;

import java.util.LinkedList;
import java.util.Stack;

/**
 * Created by davidzhou on 12/28/14.
 */
public class JavaStackProblems {
    /**
     * Longest Valid Parentheses -- LeetCode
     * https://oj.leetcode.com/problems/longest-valid-parentheses/
     * Given a string containing just the characters '(' and ')', find the length of the longest valid (well-formed) parentheses substring.
     * For "(()", the longest valid parentheses substring is "()", which has length = 2.
     * Another example is ")()())", where the longest valid parentheses substring is "()()", which has length = 4.
     */
    public static int longestValidParentheses(String s) {
        if (s == null || s.length() == 0)
            return 0;
        int longestLength = 0;      // Length of the longest valid parentheses
        int start = 0;  // The start index of the possibly longest valid parentheses
        Stack<Integer> stack = new Stack<Integer>();
        // One-pass scan
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {   // Opening parenthesis
                stack.push(i);          // Push its index
            } else {        // Closing parenthesis
                if (stack.empty()) {    // No opening parenthesis to match
                    start = i + 1;      // i+1 is the start of next possibly LVP
                } else {
                    stack.pop();    // The index of the opening parenthesis matched by s[i]
                    if (stack.empty())  // s[start...i] is matched                            (())
                        longestLength = Math.max(longestLength, i - start + 1);
                    else    // s[stack.peek()] is unmatched; s[stack.peek()+1...i] is matched (()()
                        longestLength = Math.max(longestLength, i - stack.peek());
                }
            }
        }

        return longestLength;
    }

    /**
     * Largest Rectangle in Histogram -- LeetCode
     * https://oj.leetcode.com/problems/largest-rectangle-in-histogram/
     * Given n non-negative integers representing the histogram's bar height where the width of each bar is 1, find the
     */
    public int largestRectangleArea(int[] height) {
        if (height == null || height.length == 0)
            return 0;
        int max = 0;
        LinkedList<Integer> stack = new LinkedList<Integer>();
        for (int i = 0; i < height.length; i++) {
            while (!stack.isEmpty() && height[i] <= height[stack.peek()]) {
                int index = stack.pop();
                //check the width for height[index] not i
                //如果栈已经为空，说明到目前为止所有元素（当前下标元素除外）都比出栈元素高度要大（否则栈中肯定还有元素）
                int curArea = stack.isEmpty() ? i * height[index] : (i - stack.peek() - 1) * height[index];
                max = Math.max(max, curArea);
            }
            stack.push(i);
        }
        while (!stack.isEmpty()) {
            int index = stack.pop();
            int curArea = stack.isEmpty() ?
                    height.length * height[index] :
                    (height.length - stack.peek() - 1) * height[index];
            max = Math.max(max, curArea);
        }
        return max;
    }
}
