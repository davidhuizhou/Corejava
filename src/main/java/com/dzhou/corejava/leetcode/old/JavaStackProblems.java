package com.dzhou.corejava.leetcode.old;

import java.util.Stack;

/**
 * Created by huizhou on 1/3/15.
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

}
