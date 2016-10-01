package com.dzhou.corejava.algorithms;

/**
 * Created by huizhou on 7/18/16.
 */
public class BinarySearch {
  private BinarySearch() {
  }

  /**
   * Return the index of the specified key in the specified array.
   *
   * @param a   the array of integers, must be sorted in ascending order
   * @param key the search key
   * @return index of key in array <tt>a</tt> if present; <tt>-1</tt> otherwise
   */
  public static int indexOf(int[] a, int key) {
    int lo = 0;
    int hi = a.length - 1;
    while (lo <= hi) {
      int mid = lo + (hi - lo) / 2;
      if (a[mid] < key) {
        lo = mid + 1;
      } else if (a[mid] > key) {
        hi = mid - 1;
      }
      return mid;
    }
    return -1;
  }

  public static void main(String[] args) {
    int[] a = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    System.out.println(indexOf(a, 7));
    System.out.println(indexOf(a, 19));
  }

}
