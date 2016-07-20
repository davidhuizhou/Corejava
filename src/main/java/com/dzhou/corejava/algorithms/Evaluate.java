package com.dzhou.corejava.algorithms;

import com.google.common.base.CharMatcher;
import com.google.common.base.Splitter;

/**
 * Created by huizhou on 7/19/16.
 */
public class Evaluate {
  public static double evaluateExpression(final String expression) {
    Stack<String> ops = new LinkedStack<String>();
    Stack<Double> vals = new LinkedStack<Double>();

    Iterable<String> e = Splitter.on(CharMatcher.whitespace()).split(expression);

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
  }
}

