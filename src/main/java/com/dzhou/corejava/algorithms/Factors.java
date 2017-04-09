package com.dzhou.corejava.algorithms;

/**
 * Created by huizhou on 4/8/17.
 */
public class Factors {

  public static void printPrimeFactors(long n) {
    for (long factor = 2; factor * factor <= n; factor++) {
      while (n % factor == 0) {
        System.out.print(factor + " ");
        n = n / factor;
      }
    }

    if (n > 1) {
      System.out.println(n);
    } else {
      System.out.println();
    }
  }

  public static void main(String[] args) {
    printPrimeFactors(977);
    printPrimeFactors(999983);
    printPrimeFactors(100);
  }
}
