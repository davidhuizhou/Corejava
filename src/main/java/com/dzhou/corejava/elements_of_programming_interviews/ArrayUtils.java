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
        swap(A, nextEven, nextOdd--);
      }
    }
  }

  public static void evenOddOne(Integer[] A) {
    int even = 0;
    for (int i = 0; i < A.length; i++) {
      if (A[i] % 2 == 0) {
        swap(A, i, even++);
      }
    }
  }

  public static void dutchFlagPartitionOne(Integer[] A, int index) {
    int pivot = A[index];
    int small = 0;
    for (int i = 0; i < A.length; ++i) {
      if (A[i] < pivot) {
        swap(A, i, small++);
      }
    }

    int large = A.length - 1;
    for (int j = large; j >= 0 && A[j] >= pivot; --j) {
      if (A[j] > pivot) {
        swap(A, j, large--);
      }
    }

  }

  public static void dutchFlagPartition(Integer[] A, int index) {
    int pivot = A[index];

    /**
     * Keep the following invariants during partition:
     * elements less then pivot group: A[0, small - 1]
     * elements equal to pivot group: A[small, equal - 1]
     * elements unclassified group: A[equal, large - 1]
     * elements greater then pivot: A[large, A.length - 1]
     */
    int smaller = 0;
    int equal = 0;
    int large = A.length;
    // Keep iterating as long as there is unclassfied element.
    while (equal < large) {
      if (A[equal] < pivot) {
        swap(A, smaller++, equal++);
      } else if (A[equal] == pivot) {
        ++equal;
      } else {
        swap(A, equal, --large);
      }
    }
  }

  public static void swap(Integer[] A, int i, int j) {
    int temp = A[i];
    A[i] = A[j];
    A[j] = temp;
  }

  public static Integer[] plusOne(Integer[] A) {
    ++A[A.length - 1];
    for (int i = A.length - 1; i > 0 && A[i] == 10; --i) {
      A[i] = 0;
      ++A[i - 1];
    }
    if (A[0] == 10) {
      Integer[] B = new Integer[A.length + 1];
      B[0] = 1;
      for (int i = 1; i < B.length; i++) {
        B[i] = 0;
      }
      return B;
    }
    return A;

  }

  public static Integer[] multiple(Integer[] a, Integer[] b) {
    int sign = a[0] < 0 ^ b[0] < 0 ? -1 : 1;
    a[0] = Math.abs(a[0]);
    b[0] = Math.abs(b[0]);

    Integer[] c = new Integer[a.length + b.length];
    for (int i = 0; i < c.length; i++) {
      c[i] = 0;
    }

    for (int i = a.length - 1; i >= 0; --i) {
      for (int j = b.length - 1; j >= 0; --j) {
        c[i + j + 1] += a[i] * b[j];
        c[i + j] += c[i + j + 1] / 10;
        c[i + j + 1] = c[i + j + 1] % 10;
      }
    }

    c[0] *= sign;
    System.out.println(Joiner.on(",").join(c));

    return c;

  }

  public static boolean canReachEnd(int[] a) {
    int furthestReachSoFar = 0;
    int lastIndex = a.length - 1;
    for (int i = 0; i <= furthestReachSoFar && furthestReachSoFar < lastIndex; ++i) {
      furthestReachSoFar = Math.max(furthestReachSoFar, a[i] + i);
    }
    return furthestReachSoFar >= lastIndex;
  }



  public static void main(String[] args) {

    Integer[] a = new Integer[]{1, 2, 4, 5, 6, 7, 7, 8, 10};
    evenOdd(a);
    System.out.println(Joiner.on(",").join(a));

    Integer[] a1 = new Integer[]{1, 2, 4, 5, 6, 7, 7, 8, 10};
    evenOddOne(a1);
    System.out.println(Joiner.on(",").join(a1));

    Integer[] b = new Integer[]{2, 5, 4, 3, 6, 6, 7, 8, 6, 9, 6, 10};
    dutchFlagPartition(b, 5);
    System.out.println(Joiner.on(",").join(b));

    Integer[] b1 = new Integer[]{2, 5, 4, 3, 6, 6, 7, 8, 6, 9, 6, 10};
    dutchFlagPartitionOne(b1, 5);
    System.out.println(Joiner.on(",").join(b1));

    Integer[] c = new Integer[]{1, 8, 8};
    System.out.println(Joiner.on(",").join(plusOne(c)));

    Integer[] d = new Integer[]{1, 8, 9};
    System.out.println(Joiner.on(",").join(plusOne(d)));

    Integer[] e = new Integer[]{9, 9, 9};
    System.out.println(Joiner.on(",").join(plusOne(e)));

    Integer[] m1 = new Integer[]{1, 0, 0};
    Integer[] m2 = new Integer[]{-1, 1};
    System.out.println(Joiner.on(",").join(multiple(m2, m1)));
    System.out.println(100 * 11);

    Integer[] m3 = new Integer[]{8, 3, 5};
    Integer[] m4 = new Integer[]{-6, 1};
    System.out.println(Joiner.on(",").join(multiple(m3, m4)));
    System.out.println(835 * 61);

    int[] s = new int[]{3, 2, 0, 0, 2, 0, 1};
    System.out.println(canReachEnd(s));

    int[] s1 = new int[]{3, 3, 1, 0, 2, 0, 1};
    System.out.println(canReachEnd(s1));



  }


}
