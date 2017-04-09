package com.dzhou.corejava.algorithms;

import com.google.common.base.Joiner;

import java.util.Comparator;

/**
 * Created by davidzhou on 7/24/14.
 */
public class Shell {

  public static void sort(Comparable[] a) {
    int n = a.length;

    // 3x + 1 increment sequence: 1, 4, 13 ...
    int h = 1;
    while (h < n / 3) {
      h = 3 * h + 1;
    }

    while (h >= 1) {
      // h-sort the array
      for (int i = h; i < n; i++) {
        for (int j = i; j >= h && a[j].compareTo(a[j - h]) < 0; j++) {
          exch(a, j - h, j);
        }
      }
      assert isHSorted(a, h);
      h /= 3;

    }
    assert isSorted(a);
  }

  private static boolean isSorted(Comparable[] a) {
    for (int i = 1; i < a.length; i++) {
      if (less(a[i], a[i - 1])) {
        return false;
      }
    }
    return true;
  }

  private static boolean isHSorted(Comparable[] a, int h) {
    for (int i = h; i < a.length; i++) {
      if (less(a[i], a[i - h])) {
        return false;
      }
    }
    return true;
  }

  // is v < w ?
  private static boolean less(Comparable v, Comparable w) {
    return (v.compareTo(w) < 0);
  }

  // is v < w ?
  private static boolean less(Comparator c, Object v, Object w) {
    return (c.compare(v, w) < 0);
  }

  // exchange a[i] and a[j]
  private static void exch(Object[] a, int i, int j) {
    Object swap = a[i];
    a[i] = a[j];
    a[j] = swap;
  }

  public static void main(String[] args) {
    Integer[] a = new Integer[]{5, 4, 2, 3, 1, 9, 6, 7, 8, 10};
    sort(a);
    System.out.println(Joiner.on(",").join(a));

//    Integer[] b = new Integer[]{5, 4, 2, 3, 1, 9, 6, 7, 8, 10};
//    sort(b, new Selection.IntegerComparator());
//    System.out.println(Joiner.on(",").join(b));


  }
}
