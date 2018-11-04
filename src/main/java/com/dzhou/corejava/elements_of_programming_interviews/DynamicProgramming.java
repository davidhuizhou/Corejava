package com.dzhou.corejava.elements_of_programming_interviews;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DynamicProgramming {

  /**
   * Find the maximum subarray.
   * 
   * @param a
   * @return
   */
  public static int findMaxmumSubarray(final int[] a) {
    int minSum = 0, sum = 0, maxSum = 0;
    for (int i = 0; i < a.length; i++) {
      sum += a[i];
      minSum = Math.min(minSum, sum);
      maxSum = Math.max(maxSum, sum - minSum);
    }
    return maxSum;
  }

  /**
   * Count the number of score combinations.
   */
  public static int[][] numCombinationsForFinalScore(int finalScore,
      List<Integer> individualScores) {
    int[][] numCombinationsForScore = new int[individualScores.size()][finalScore + 1];
    for (int i = 0; i < individualScores.size(); i++) {
      numCombinationsForScore[i][0] = 1;
      for (int j = 1; j <= finalScore; j++) {
        int withOutThisScore = i - 1 >= 0 ? numCombinationsForScore[i - 1][j] : 0;
        int withThisScore = j - individualScores.get(i) >= 0
            ? numCombinationsForScore[i][j - individualScores.get(i)]
            : 0;
        numCombinationsForScore[i][j] = withOutThisScore + withThisScore;
      }
    }
    return numCombinationsForScore;

  }


  /**
   * Compute the Levenshtein distance.
   * 
   * @param A
   * @param B
   * @return
   */
  public static int levenshteinDistance(String A, String B) {
    int[][] distanceBetweenPrefixes = new int[A.length()][B.length()];
    for (int[] row : distanceBetweenPrefixes) {
      Arrays.fill(row, -1);
    }
    return computeDistanceBetweenPrefixes(A, A.length() - 1, B, B.length() - 1,
        distanceBetweenPrefixes);
  }

  private static int computeDistanceBetweenPrefixes(String A, int A_idx, String B, int B_idx,
      int[][] distanceBetweenPrefixes) {
    if (A_idx < 0) {
      return B_idx + 1;
    }

    if (B_idx < 0) {
      return A_idx + 1;
    }

    if (distanceBetweenPrefixes[A_idx][B_idx] == -1) {
      if (A.charAt(A_idx) == B.charAt(B_idx)) {
        distanceBetweenPrefixes[A_idx][B_idx] =
            computeDistanceBetweenPrefixes(A, A_idx - 1, B, B_idx - 1, distanceBetweenPrefixes);
      }
    } else {
      int substituteLast =
          computeDistanceBetweenPrefixes(A, A_idx - 1, B, B_idx - 1, distanceBetweenPrefixes);
      int addLast = computeDistanceBetweenPrefixes(A, A_idx, B, B_idx - 1, distanceBetweenPrefixes);
      int deleteLast =
          computeDistanceBetweenPrefixes(A, A_idx - 1, B, B_idx, distanceBetweenPrefixes);
      distanceBetweenPrefixes[A_idx][B_idx] =
          1 + Math.min(substituteLast, Math.min(addLast, deleteLast));
    }
    return distanceBetweenPrefixes[A_idx][B_idx];

  }

  public static int numberOfWays(int n, int m) {
    return computeNumberOfWaysToXY(n - 1, m - 1, new int[n][m]);

  }

  private static int computeNumberOfWaysToXY(int x, int y, int[][] numOfWays) {
    if (x == 0 || y == 0) {
      numOfWays[x][y] = 1;
    }

    if (numOfWays[x][y] == 0) {
      int waysTop = x == 0 ? 0 : computeNumberOfWaysToXY(x - 1, y, numOfWays);
      int waysLeft = y == 0 ? 0 : computeNumberOfWaysToXY(x, y - 1, numOfWays);
      numOfWays[x][y] = waysTop + waysLeft;
    }
    return numOfWays[x][y];
  }

  
  
  
//  private static int optimumSubjectToItemAndCapacity
  
  
  public static void main(String[] args) {
    int[] a = {904, 40, 523, 12, -335, -385, -124, 481, -31};
    System.out.println("findMaxmumSubarray=" + findMaxmumSubarray(a));

    List<Integer> individualScores = new ArrayList<>();
    individualScores.add(2);
    individualScores.add(3);
    individualScores.add(7);

    int[][] numCombinations = numCombinationsForFinalScore(12, individualScores);
    for (int i = 0; i < numCombinations.length; i++) {
      for (int j = 0; j <= 12; j++) {
        System.out.print(numCombinations[i][j] + " ");
      }
      System.out.println("");
    }

  }
}
