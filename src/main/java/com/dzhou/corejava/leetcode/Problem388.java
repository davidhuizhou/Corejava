package com.dzhou.corejava.leetcode;

import java.util.Stack;

/**
 * 388. Longest Absolute File Path
 *
 * https://leetcode.com/problems/longest-absolute-file-path/submissions/1
 */

public class Problem388 {


  public int lengthLongestPath(String input) {

    int ans = 0;

    if (input == null || input.length() == 0) {
      return ans;
    }

    Stack<Node> stack = new Stack();
    String[] paths = input.split("\n");

    for (String p : paths) {

      int level = getLevel(p);

      while (!stack.isEmpty() && level <= stack.peek().level) {
        stack.pop();
      }

      if (p.contains(".")) {
        if (!stack.isEmpty()) {
          ans = Math.max(ans, stack.peek().len + p.length() - level);
        } else {
          ans = Math.max(ans, p.length() - level);
        }
      } else {

        if (!stack.isEmpty()) {
          stack.push(new Node(level,
            stack.peek().len + p.length() - level + 1));
        } else {
          stack.push(new Node(level, p.length() - level + 1));
        }

      }
    }

    return ans;


  }


  private int getLevel(String s) {
    int level = 0;
    for (int i = 0; i < s.length(); i++) {
      if (s.charAt(i) == '\t') {
        level++;
      } else {
        break;
      }
    }
    return level;
  }

  private class Node {
    int level;
    int len;

    Node(int level, int len) {
      this.level = level;
      this.len = len;
    }
  }

  public static void main(String[] args) {
    Problem388 lp = new Problem388();

    String input = "dir\n\tsubdir1\n\tsubdir2\n\t\tfile.ext";
    System.out.println("lengthLongestPath=" + lp.lengthLongestPath(input));
  }


}
