package com.dzhou.corejava.algorithms;

/**
 * Created by huizhou on 4/8/17.
 */
public class Prime {
  private static boolean isPrime(final int n) {
    boolean isPrime = true;

    for (long factor = 2; factor * factor <= n; factor++) {
      if (n % factor == 0) {
        return false;
      }
    }
    return true;
  }

  public static void main(String[] args){

  }
}
