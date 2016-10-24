package com.dzhou.corejava.algorithms;

import com.google.common.base.Joiner;

/**
 * Created by huizhou on 10/1/16.
 */
public class Knuth {
  private Knuth() {

  }

  public static void shuffle(Object[] a) {
    int len = a.length;
    for (int i = 0; i < len; i++) {
      int r = i + (int) (Math.random() * (len - i));
      Object swap = a[r];
      a[r] = a[i];
      a[i] = swap;
    }
  }

  public static void main(String[] args) {
    String[] a = new String[]{"a", "b", "c", "d", "e", "f", "g"};
    for (int i = 0; i < 5; i++) {
      shuffle(a);
      System.out.println(Joiner.on(",").join(a));
    }
  }

}
