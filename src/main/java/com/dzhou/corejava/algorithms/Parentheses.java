package com.dzhou.corejava.algorithms;

/**
 * Created by huizhou on 7/24/16.
 */
public class Parentheses {
  private static final char LEFT_PAREN = '(';
  private static final char RIGHT_PAREN = ')';
  private static final char LEFT_BRACE = '{';
  private static final char RIGHT_BRACE = '}';
  private static final char LEFT_BRACKET = '[';
  private static final char RIGHT_BRACKET = ']';

  public static boolean isBalanced(String s) {
    Stack<Character> stack = new LinkedStack<Character>();
    for (int i = 0; i < s.length(); i++) {
      if (s.charAt(i) == LEFT_PAREN)
        stack.push(s.charAt(i));
      if (s.charAt(i) == LEFT_BRACE)
        stack.push(s.charAt(i));
      if (s.charAt(i) == LEFT_BRACKET)
        stack.push(s.charAt(i));

      if (s.charAt(i) == RIGHT_PAREN) {
        if (stack.isEmpty()) {
          return false;
        }
        if (stack.pop() != LEFT_PAREN) {
          return false;
        }
      } else if (s.charAt(i) == RIGHT_BRACE) {
        if (stack.isEmpty()) {
          return false;
        }
        if (stack.pop() != LEFT_BRACE) {
          return false;
        }
      } else if (s.charAt(i) == RIGHT_BRACKET) {
        if (stack.isEmpty()) {
          return false;
        }
        if (stack.pop() != LEFT_BRACKET) {
          return false;
        }
      }
    }
    return stack.isEmpty();
  }

  public static void main(String[] args) {
    String input = "[()]{}{[()()]()}";
    System.out.println(isBalanced(input));
    input = "[(])";
    System.out.println(isBalanced(input));
  }

}
