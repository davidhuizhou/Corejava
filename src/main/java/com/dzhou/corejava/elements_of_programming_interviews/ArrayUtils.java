package com.dzhou.corejava.elements_of_programming_interviews;

import com.google.common.base.Joiner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

/**
 * Created by huizhou on 7/10/16.
 */
public class ArrayUtils {

  /**
   * Problem 6.0, input is an array of integers, reodeer its entries so that the
   * even entries appear first.
   */
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

  /**
   * Problem 6.0, input is an array of integers, reodeer its entries so that the
   * even entries appear first.
   */
  public static void evenOddOne(Integer[] A) {
    int nextEven = 0;
    for (int i = 0; i < A.length; i++) {
      if (A[i] % 2 == 0) {
        swap(A, nextEven++, i);
      }
    }
  }

  /**
   * Probelm 6.1 three-way partioning resembling the Dutch national flag.
   * First pass move all the elements less than the pivot to the begining.
   * Second pass move the larger elements to the end.
   */
  public static void dutchFlagPartitionOne(Integer[] A, int index) {
    // Set the pivot to compare to.
    int pivot = A[index];

    // First pass move all the elements less than the pivot to the begining.
    int small = 0;
    for (int i = 0; i < A.length; i++) {
      if (A[i] < pivot) {
        swap(A, i, small++);
      }
    }

    // Second pass move all the elements greate than the pivot to the end.
    int large = A.length - 1;
    for (int j = large; j >= 0 && A[j] >= pivot; j--) {
      if (A[j] > pivot) {
        swap(A, j, large--);
      }
    }

  }


  /**
   * Probelm 6.1 three-way partioning resembling the Dutch national flag.
   */
  public static void dutchFlagPartition(Integer[] A, int index) {
    int pivot = A[index];

    /**
     * Keep the following invariants during partition:
     * elements less then pivot group: A[0, small - 1]
     * elements equal to pivot group: A[small, equal - 1]
     * elements unclassified group: A[equal, large - 1]
     * elements greater then pivot: A[large, A.length - 1]
     */
    int small = 0;
    int equal = 0;
    int large = A.length;
    while (equal < large) {
      if (A[equal] < pivot) {
        swap(A, small++, equal++);
      } else if (A[equal] == pivot) {
        equal++;
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

  /**
   * Problem 6.2 input an array of digits encoding the decimal number D and updates the array to
   * represent the number D + 1.
   */
  public static Integer[] plusOne(Integer[] A) {
    ++A[A.length - 1];
    for (int i = A.length - 1; i > 0 && A[i] == 10; --i) {
      A[i] = 0;
      ++A[i - 1];
    }
    if (A[0] == 10) {
      Integer[] B = new Integer[A.length + 1];
      B[0] = 1;
      for (int j = 1; j < B.length; j++) {
        B[j] = 0;
      }
      return B;
    }
    return A;

  }

  public static List<Integer> plusOne(List<Integer> A) {
    int n = A.size() - 1;
    A.set(n, A.get(n) + 1);
    for (int i = n; i > 0 && A.get(i) == 10; --i) {
      A.set(i, 0);
      A.set(i - 1, A.get(i - 1) + 1);
    }
    if (A.get(0) == 10) {
      A.set(0, 0);
      A.add(0, 1);
    }
    return A;
  }


  /**
   * Problem 6.3 multiply two arbitrary-precision integers.
   * The number of digits required for the product is at most n + m for n and m digit operands.
   */
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
    return c;

  }

  public static List<Integer> multiply(List<Integer> num1, List<Integer> num2) {
    final int sign = num1.get(0) < 0 ^ num2.get(0) < 0 ? -1 : 1;
    num1.set(0, Math.abs(num1.get(0)));
    num2.set(0, Math.abs(num2.get(0)));

    List<Integer> result = new ArrayList<Integer>(Collections.nCopies(num1.size() + num2.size(),
        0));

    for (int i = num1.size() - 1; i >= 0; --i) {
      for (int j = num2.size() - 1; j >= 0; --j) {
        result.set(i + j + 1, result.get(i + j + 1) + num1.get(i) * num2.get(j));
        result.set(i + j, result.get(i + j) + result.get(i + j + 1) / 10);
        result.set(i + j + 1, result.get(i + j + 1) % 10);
      }
    }
    int first_not_zero = 0;
    while (first_not_zero < result.size() && result.get(first_not_zero) == 0) {
      ++first_not_zero;
    }
    result = result.subList(first_not_zero, result.size());
    if (result.isEmpty()) {
      return Arrays.asList(0);
    }
    result.set(0, result.get(0) * sign);
    return result;
  }


  /**
   * Problem 6.4 an array of n integers, where A[i] denotes the maximum you can advance from
   * index i, and returns whether is it possible to advance to the last index starting from
   * the beginning of the array.
   */
  public static boolean canReachEnd(int[] a) {
    int furthestReachSoFar = 0;
    for (int i = 0; i <= furthestReachSoFar && furthestReachSoFar < a.length - 1; i++) {
      furthestReachSoFar = Math.max(furthestReachSoFar, i + a[i]);
    }
    return furthestReachSoFar >= a.length - 1;
  }


  public static boolean canReacheEnd(List<Integer> maxAdvanceSteps) {
    int furthestReachSoFar = 0;
    int lastIndex = maxAdvanceSteps.size() - 1;
    for (int i = 0; i <= furthestReachSoFar && furthestReachSoFar < lastIndex; ++i) {
      furthestReachSoFar = Math.max(furthestReachSoFar, i + maxAdvanceSteps.get(i));
    }
    return furthestReachSoFar >= lastIndex;
  }


  /**
   * Problem 6.5 Delete duplicates from a sorted array.
   */
  public static int deleteDuplicates(List<Integer> A) {
    if (A.isEmpty()) {
      return 0;
    }

    int writeIndex = 1;
    for (int i = 1; i < A.size(); ++i) {
      if (!A.get(writeIndex - 1).equals(A.get(i))) {
        A.set(writeIndex++, A.get(i));
      }
    }
    return writeIndex;
  }


  /**
   * Problem 6.6 Buy and sell stock once.
   */
  public static double computeMaxProfit(List<Double> prices) {
    double minPrice = Double.MAX_VALUE, maxProfit = 0.0;
    for (Double price : prices) {
      maxProfit = Math.max(maxProfit, price - minPrice);
      minPrice = Math.min(minPrice, price);
    }
    return maxProfit;
  }

  /**
   * Problem 6.7 Buy and sell a stock twice.
   */
  public static double buyAndSellStockTwice(List<Double> prices) {
    double maxTotalProfit = 0.0;
    List<Double> firstBuySellProfits = new ArrayList<Double>();
    double minPriceSoFar = Double.MAX_VALUE;

    // Forware phase.  For each day, we record maximum profit if we
    // sell on that day.
    for (int i = 0; i < prices.size(); ++i) {
      minPriceSoFar = Math.min(minPriceSoFar, prices.get(i));
      maxTotalProfit = Math.max(maxTotalProfit, prices.get(i) - minPriceSoFar);
      firstBuySellProfits.add(maxTotalProfit);
    }

    // Backared phase.  For each day, find the maximium profit if we make
    // the second buy on that day.
    double maxPriceSoFar = Double.MIN_VALUE;
    for (int i = prices.size() - 1; i > 0; --i) {
      maxPriceSoFar = Math.max(maxPriceSoFar, prices.get(i));
      maxTotalProfit = Math.max(maxTotalProfit, maxPriceSoFar - prices.get(i) +
          firstBuySellProfits.get(i - 1));
    }
    return maxTotalProfit;

  }

  /**
   * Problem 6.8 Enumerate all primes to n
   */
  public static List<Integer> generatePrimes(int n) {
    List<Integer> primes = new ArrayList<Integer>();
    // isPrime.get(p) represents if p is prime or not.  Initially, set each
    // to true, excepting 0 and 1.  Then use sieving to eleminate nonprimes.
    List<Boolean> isPrime = new ArrayList<>(Collections.nCopies(n + 1, true));
    isPrime.set(0, false);
    isPrime.set(1, false);
    for (int p = 2; p <= n; ++p) {
      if (isPrime.get(p)) {
        primes.add(p);
        // Sieve p's multiples.
        for (int j = p + p; j <= n; j += p) {
          isPrime.set(j, false);
        }
      }
    }
    System.out.println(isPrime);
    return primes;
  }

  /**
   * Problem 6.10 Compute the next permutation.
   */
  public static List<Integer> nextPermutation(List<Integer> perm) {
    int k = perm.size() - 2;
    while (k >= 0 && perm.get(k) >= perm.get(k + 1)) {
      --k;
    }
    if (k == -1) {
      // perm is the last permutation
      return Collections.emptyList();
    }
    // Swap the smllest entry after index k that is greater than perm(k).  We
    // exploit the fact that perm.subList(k + 1, per.size()) is decreasing so
    // if we search in reverse order, the first entry that is greater than
    // perm(k) is the smallest such entry.
    for (int i = perm.size() - 1; i > k; --i) {
      if (perm.get(i) > perm.get(k)) {
        Collections.swap(perm, k, i);
        break;
      }
    }
    // Since perm.subList(k + 1, perm.size()) is in decreasing order, we can
    // build the smallest dictionary ordering of this subarray by reversing it.
    Collections.reverse(perm.subList(k + 1, perm.size()));
    return perm;

  }

  /**
   * Problem 6. 11 Sample offline data. Randomly chose k out of n elements.
   */
  public static void randomSampling(int k, List<Integer> A) {
    Random gen = new Random();
    for (int i = 0; i < k; ++i) {
      // Generate a random int in [i, A.size() - 1]
      Collections.swap(A, i, i + gen.nextInt(A.size() - i));
    }
  }

  /**
   * Problem 6.12 Sample online data.
   */
  // Assumption:  there are at least k elements in the stream.
  public static List<Integer> onlineRandomSample(Iterator<Integer> sequence, int k) {
    List<Integer> runningSample = new ArrayList<Integer>(k);
    // Stores the first k elements.
    for (int i = 0; sequence.hasNext() && i < k; ++i) {
      runningSample.add(sequence.next());
    }

    // Have read the first k elements.
    int numSeenSoFar = k;
    Random randIdxGen = new Random();
    while (sequence.hasNext()) {
      Integer x = sequence.next();
      ++numSeenSoFar;
      //Generate a random num in [0, numSeenSoFar], and if this num is in
      // [0, k - 1], we replace that element from the sample with x.
      final int idxToReplace = randIdxGen.nextInt(numSeenSoFar);
      if (idxToReplace < k) {
        runningSample.set(idxToReplace, x);
      }
    }
    return runningSample;
  }


  private static boolean hasDuplicate(List<List<Integer>> partialAssignment, int startRow, int
      endRow, int startCol, int endCol) {
    List<Boolean> isPresent = new ArrayList<>(Collections.nCopies(partialAssignment.size() + 1,
        false));
    for (int i = startRow; i < endRow; ++i) {
      for (int j = startCol; j < endCol; ++j) {
        if (partialAssignment.get(i).get(j) != 0 && isPresent.get(partialAssignment.get(i).get(j)
        )) {
          return true;
        }
        isPresent.set(partialAssignment.get(i).get(j), true);
      }
    }
    return false;

  }

  public static List<List<Integer>> generatePascalTriangle(int numRows) {
    List<List<Integer>> pascalTriangle = new ArrayList<>();
    for (int i = 0; i < numRows; ++i) {
      List<Integer> currRow = new ArrayList<>();
      for (int j = 0; j <= i; ++j) {
        // Set this entry to the sum of the two above adject entries if they exists
        currRow.add((0 < j && j < i) ? pascalTriangle.get(i - 1).get(j - 1) + pascalTriangle.get(i -
            1).get(j) : 1);
      }
      pascalTriangle.add(currRow);
    }
    return pascalTriangle;
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
    assert (b[0] < 6);
    assert (b[1] < 6);
    assert (b[2] < 6);
    assert (b[3] < 6);

    assert (b[4] == 6);
    assert (b[5] == 6);
    assert (b[6] == 6);
    assert (b[7] == 6);

    assert (b[8] > 6);
    assert (b[9] > 6);
    assert (b[10] > 6);
    assert (b[11] > 6);


    Integer[] b1 = new Integer[]{2, 5, 4, 3, 6, 6, 7, 8, 6, 9, 6, 10};
    dutchFlagPartitionOne(b1, 5);
    System.out.println(Joiner.on(",").join(b1));

    assert (b1[0] < 6);
    assert (b1[1] < 6);
    assert (b1[2] < 6);
    assert (b1[3] < 6);

    assert (b1[4] == 6);
    assert (b1[5] == 6);
    assert (b1[6] == 6);
    assert (b1[7] == 6);

    assert (b1[8] > 6);
    assert (b1[9] > 6);
    assert (b1[10] > 6);
    assert (b1[11] > 6);

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

    Integer[] A = new Integer[]{2, 3, 5, 5, 7, 11, 11, 11, 13};
    System.out.println(deleteDuplicates(Arrays.asList(A)));

    Double[] prices = new Double[]{310.0, 315.0, 275.0, 295.0, 260.0, 270.0, 290.0, 255.0, 250.0};
    System.out.println(computeMaxProfit(Arrays.asList(prices)));

    prices = new Double[]{12.0, 11.0, 13.0, 9.0, 12.0, 8.0, 14.0, 13.0, 15.0};
    System.out.println(buyAndSellStockTwice(Arrays.asList(prices)));

    System.out.println(generatePrimes(10));

    List<Integer> perm = Arrays.asList(new Integer[]{6, 2, 3, 5, 4, 1, 0});
    System.out.println(perm);
    System.out.println(nextPermutation(perm));


  }


}
