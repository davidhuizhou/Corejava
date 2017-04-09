package com.dzhou.corejava.algorithms;

import com.google.common.base.Joiner;

import java.util.Comparator;

/**
 * Created by huizhou on 7/22/14.
 */
public class Insertion {

  /**
   * Rearranges the array in ascending order, using the natural order.
   */
  public static void sort(Comparable[] a) {
    int n = a.length;
    for (int i = 0; i < n; i++) {
      for (int j = i; j > 0 && a[j].compareTo(a[j - 1]) < 0; j--) {
        exch(a, j - 1, j);
      }
      assert isSorted(a, 0, i);
    }
    assert isSorted(a);
  }

  /**
   * Rearranges the subarray a[lo..hi] is ascending order, using natural order.
   */
  public static void sort(Comparable[] a, int lo, int hi) {
    for (int i = lo; i <= hi; i++) {
      for (int j = i; j > lo && less(a[j], a[j - 1]); j--) {
        exec(a, j, j - 1);
      }
    }
    assert isSorted(a, lo, hi);
  }

  public static void sort(Object[] a, Comparator c) {
    int N = a.length;
    for (int i = 0; i < N; i++) {
      for (int j = i; j > 0 && less(c, a[j], a[j - 1]); j--) {
        exch(a, j, j - 1);
      }
    }
  }

  /**
   * Return a permutation that gives the elements in a[] in ascending order
   * do not change the original array a[].
   */
  public static int[] indexSort(Comparable[] a) {
    int n = a.length;
    int[] index = new int[n];

    for (int i = 0; i < n; i++) {
      index[i] = i;
    }

    for (int i = 0; i < n; i++) {
      for (int j = i; j > 0 && less(a[j], a[j - 1]); j--) {
        exec(index, j, j - 1);
      }
    }
    return index;
  }

  /***************************************************************************
   *  Helper sorting functions.
   ***************************************************************************/

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

  // exchange a[i] and a[j] for indirect sort.
  private static void exec(Object[] a, int i, int j) {
    Object swap = a[i];
    a[i] = a[j];
    a[j] = swap;
  }


  // exchange a[i] and a[j] for indirect sort.
  private static void exec(int[] a, int i, int j) {
    int swap = a[i];
    a[i] = a[j];
    a[j] = swap;
  }

  /***************************************************************************
   *  Check if array is sorted - useful for debugging.
   ***************************************************************************/
  // is the array sorted.
  private static boolean isSorted(Comparable[] a) {
    return isSorted(a, 0, a.length - 1);
  }


  // is the array sorted from a[lo] to a[hi]
  private static boolean isSorted(Comparable[] a, int lo, int hi) {
    for (int i = lo + 1; i <= hi; i++) {
      if (less(a[i], a[i - 1])) {
        return false;
      }
    }
    return true;
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
