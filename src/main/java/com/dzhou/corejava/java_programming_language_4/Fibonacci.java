package com.dzhou.corejava.java_programming_language_4;

/**
 * Created by davidzhou on 1/5/14.
 */

public class Fibonacci {

  public static void fibonacci(int input) {
    int lo = 1;
    int hi = 1;
    String mark;

    System.out.println("1: " + lo);
    for (int i = 2; i <= input; i++) {
      if (hi % 2 == 0) {
        mark = "*";
      } else {
        mark = "";
      }
      System.out.println(i + ": " + hi + mark);
      hi = lo + hi;
      lo = hi - lo;
    }
  }

  public static void main(String[] args) {
    fibonacci(10);

    System.out.printf("The value of Math.PI is %.20f %n", Math.PI);
    System.out.printf("The value of math.PI is %e %n", Math.PI * 1000.0f);
    System.out.printf("The integer 12345 is %d, %n", 12345);
    System.out.printf("The integer 12345 is %x, %n", 255);

  }


}
