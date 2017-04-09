package com.dzhou.corejava.algorithms;

import com.google.common.base.Joiner;

import java.util.Comparator;

/**
 * Created by davidzhou on 7/22/14.
 */
public class Selection {
  private Selection() {
  }

  /**
   * Rearanges the array in ascending order, using the natural order.
   */
  public static void sort(Comparable[] a) {
    int n = a.length;
    for (int i = 0; i < n; ++i) {
      int min = i;
      for (int j = i + 1; j < n; ++j) {
        if (a[j].compareTo(a[min]) < 0) {
          min = j;
        }
      }
      exch(a, i, min);
    }
  }

  /**
   * Rearranges the array in ascending order, using a comparator.
   */
  public static void sort(Object[] a, Comparator comparator) {
    int n = a.length;
    for (int i = 0; i < n; ++i) {
      int min = i;
      for (int j = i + 1; j < n; ++j) {
        if (comparator.compare(a[j], a[min]) < 0) {
          min = j;
        }
      }
      exch(a, i, min);
    }

  }

  private static boolean less(Comparable v, Comparable w) {
    return (v.compareTo(w) < 0);

  }

  private static boolean less(Comparator c, Object v, Object w) {
    return (c.compare(v, w) < 0);
  }

  private static void exch(Object[] a, int i, int j) {
    Object swap = a[i];
    a[i] = a[j];
    a[j] = swap;

  }

  private static boolean isSorted(Comparable[] a, int lo, int hi) {
    for (int i = lo + 1; i <= hi; i++) {
      if (less(a[i], a[i - 1])) return false;

    }
    return true;
  }

  private static class IntegerComparator implements Comparator<Integer> {
    public int compare(Integer o1, Integer o2) {
      return o1.compareTo(o2);
    }
  }

  public static void main(String[] args) {
    Integer[] a = new Integer[]{5, 4, 2, 3, 1, 9, 6, 7, 8, 10};
    sort(a);
    System.out.println(Joiner.on(",").join(a));

    Integer[] b = new Integer[]{5, 4, 2, 3, 1, 9, 6, 7, 8, 10};
    sort(b, new IntegerComparator());
    System.out.println(Joiner.on(",").join(b));


  }
}
