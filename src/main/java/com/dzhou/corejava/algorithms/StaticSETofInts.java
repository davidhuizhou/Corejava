package com.dzhou.corejava.algorithms;

import java.util.Arrays;

/**
 * Created by huizhou on 10/1/16.
 */
public class StaticSETofInts {
  private int[] a;

  public StaticSETofInts(int[] keys) {
    a = new int[keys.length];
    for (int i = 0; i < keys.length; i++) {
      a[i] = keys[i];
    }
    Arrays.sort(a);
  }

  public boolean contains(int key) {
    return rank(key) != -1;
  }

  public int rank(int key) {
    int lo = 0;
    int hi = a.length - 1;
    while (lo <= hi) {
      int mid = lo + (hi - lo) / 2;
      if (a[mid] < key) {
        lo = mid + 1;
      } else if (a[mid] > key) {
        hi = mid - 1;
      } else {
        return mid;
      }
    }
    return -1;
  }

  public static void main(String[] args) {
    int[] keys = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    StaticSETofInts st = new StaticSETofInts(keys);
    System.out.println(st.contains(7));
    System.out.println(st.contains(11));
  }

}
