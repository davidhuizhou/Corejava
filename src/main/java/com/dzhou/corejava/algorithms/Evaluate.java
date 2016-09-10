package com.dzhou.corejava.algorithms;

import com.google.common.base.CharMatcher;
import com.google.common.base.Splitter;

/**
 * Created by huizhou on 7/19/16.
 */
public class Evaluate {
  public static String infixToPostfix(String input) {
    StringBuilder sb = new StringBuilder();
    Stack<String> stack = new LinkedStack<String>();
    Iterable<String> e = Splitter.on(CharMatcher.whitespace()).omitEmptyStrings().split(input);
    for (String s : e) {
//      System.out.println("s=" + s);
      if (s.equals("+")) stack.push(s);
      else if (s.equals("*")) stack.push(s);
      else if (s.equals("-")) stack.push(s);
      else if (s.equals("/")) stack.push(s);
      else if (s.equals(")")) sb.append(stack.pop() + " ");
      else if (s.equals("(")) sb.append("");
      else sb.append(s + " ");
    }
    return sb.toString();
  }

  public static Double evaluatePostFix(String input) {
    Stack<Double> stack = new LinkedStack<Double>();
    Iterable<String> e = Splitter.on(CharMatcher.whitespace()).omitEmptyStrings().split(input);
    for (String s : e) {
//      System.out.println("s=" + s);
      if (s.equals("+")) {
        stack.push(stack.pop() + stack.pop());
      } else if (s.equals("*")) {
        stack.push(stack.pop() * stack.pop());
      } else if (s.equals("-")) {
        double d = stack.pop();
        stack.push(stack.pop() - d);
      } else if (s.equals("/")) {
        double d = stack.pop();
        stack.push(stack.pop() / d);
      } else {
        stack.push(Double.parseDouble(s));
      }
    }
    return stack.pop();
  }


  public static double evaluateExpression(final String expression) {
    Stack<String> ops = new LinkedStack<String>();
    Stack<Double> vals = new LinkedStack<Double>();

    Iterable<String> e = Splitter.on(CharMatcher.whitespace()).omitEmptyStrings().split(expression);

    for (String s : e) {
      if (s.equals("(")) {
        ;
      } else if (s.equals("+")) {
        ops.push(s);
      } else if (s.equals("-")) {
        ops.push(s);
      } else if (s.equals("*")) {
        ops.push(s);
      } else if (s.equals("/")) {
        ops.push(s);
      } else if (s.equals(")")) {
        String op = ops.pop();
        double v = vals.pop();
        if (op.equals("+")) {
          v = vals.pop() + v;
        } else if (op.equals("-")) {
          v = vals.pop() - v;
        } else if (op.equals("*")) {
          v = vals.pop() * v;
        } else if (op.equals("/")) {
          v = vals.pop() / v;
        }
        vals.push(v);
      } else {
        vals.push(Double.parseDouble(s));
      }

    }
    return vals.pop();
  }


  public static void main(String[] args) {
    String s1 = "( 1 + ( ( 2 + 3 ) * ( 4 * 5 ) ) )";
    System.out.println(evaluateExpression(s1));

    String s2 = "( 1 ( ( 2 3 + ) ( 4 5 * ) * ) + )";
    System.out.println(evaluateExpression(s2));
    System.out.println(evaluatePostFix(infixToPostfix(s2)));

    String input = "( 2 + ( ( 3 + 4 ) * ( 5 * 6 ) ) )";
    System.out.println("infixToPostfix(" + input + ")=" + infixToPostfix(input));
    System.out.println(evaluatePostFix(infixToPostfix(input)));
    System.out.println(Evaluate.evaluateExpression(input));
    input = "( 2 + ( ( 3 - 4 ) * ( 5 / 6 ) ) )";
    System.out.println("infixToPostfix(" + input + ")=" + infixToPostfix(input));
    System.out.println(evaluatePostFix(infixToPostfix(input)));
    System.out.println(Evaluate.evaluateExpression(input));
  }
}

