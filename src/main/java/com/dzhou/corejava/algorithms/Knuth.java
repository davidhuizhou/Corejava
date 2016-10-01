package com.dzhou.corejava.algorithms;

/**
 * Created by huizhou on 10/1/16.
 */
public class Knuth {

  public static void shuffle(Object[] a){
    int n = a.length;
    for(int i = 0; i < n; i++){
      int r = i + (int)(Math.random() * (n - i));
      Object swap = a[r];
      a[r] = a[i];
      a[i] = swap;
    }
  }

}
