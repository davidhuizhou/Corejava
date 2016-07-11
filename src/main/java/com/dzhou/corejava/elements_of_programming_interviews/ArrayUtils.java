package com.dzhou.corejava.elements_of_programming_interviews;

import com.google.common.base.Joiner;

/**
 * Created by huizhou on 7/10/16.
 */
public class ArrayUtils {
  public static void evenOdd(Integer[] A) {
    int nextEven = 0;
    int nextOdd = A.length - 1;
    while (nextEven < nextOdd) {
      if (A[nextEven] % 2 == 0) {
        ++nextEven;
      } else {
        swap(A, nextEven, nextOdd);
        nextOdd--;
      }
    }
  }

  public static void dutchFlagPartition(Integer[] A, int index) {
    int i = -1;
    int j = A.length;
    int k = 0;
    while (k < j) {
      if (A[k] == A[index]) {
        ++k;
      } else if (A[k] < A[index]) {
        swap(A, i + 1, k);
        ++i;
        ++k;
      } else {
        swap(A, k, j - 1);
        --j;
      }
    }
  }

  public static void swap(Integer[] A, int i, int j) {
    int temp = A[i];
    A[i] = A[j];
    A[j] = temp;
  }

  public static Integer[] plusOne(Integer[] A) {
    int carryOver = 1;
    for (int i = A.length - 1; i >= 0; i--) {
      if (A[i] + carryOver == 10) {
        A[i] = 0;
        carryOver = 1;
      } else {
        A[i] = A[i] + 1;
        carryOver = 0;
        break;
      }
    }
    if (carryOver == 1) {
      Integer[] B = new Integer[A.length + 1];
      B[0] = 1;
      for (int j = 1; j < B.length; j++) {
        B[j] = 0;
      }
      return B;
    }
    return A;

  }

  public static void main(String[] args) {

    Integer[] a = new Integer[]{1, 2, 4, 5, 6, 7, 7, 8, 10};
    evenOdd(a);
    System.out.println(Joiner.on(",").join(a));

    Integer[] b = new Integer[]{2, 5, 4, 3, 6, 6, 7, 8, 6, 9, 6, 10};
    dutchFlagPartition(b, 5);
    System.out.println(Joiner.on(",").join(b));

    Integer[] c = new Integer[]{1, 8, 8};
    System.out.println(Joiner.on(",").join(plusOne(c)));

    Integer[] d = new Integer[]{1, 8, 9};
    System.out.println(Joiner.on(",").join(plusOne(d)));

    Integer[] e = new Integer[]{9, 9, 9};
    System.out.println(Joiner.on(",").join(plusOne(e)));
  }


}
