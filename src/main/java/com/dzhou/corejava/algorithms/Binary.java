package com.dzhou.corejava.algorithms;

/**
 * Created by huizhou on 4/8/17.
 *
 * Prints out n in binary
 */
public class Binary {
  private static void printBinary(final int n) {
    int number = n;
    int power = 1;
    while (power <= n / 2) {
      power *= 2;
    }
    while (power > 0) {
      if (number < power) {
        System.out.print("0");
      } else {
        System.out.print("1");
        number -= power;
      }
      power /= 2;
    }
  }

  public static void main(String[] args) {
    printBinary(123);
  }
}
