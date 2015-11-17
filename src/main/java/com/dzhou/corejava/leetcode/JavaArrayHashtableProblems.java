package com.dzhou.corejava.leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by davidzhou on 12/24/14.
 */
public class JavaArrayHashtableProblems {

    /**
     * Trapping Rain Water -- LeetCode
     * https://oj.leetcode.com/problems/trapping-rain-water/
     * Given n non-negative integers representing an elevation map where the width of each bar is 1,
     * compute how much water it is able to trap after raining.
     */
    public int trap(int[] A) {
        if (A == null || A.length == 0)
            return 0;
        int l = 0;
        int r = A.length - 1;
        int res = 0;
        while (l < r) {
            int min = Math.min(A[l], A[r]);
            if (A[l] == min) {
                l++;
                while (l < r && A[l] < min) {
                    res += min - A[l];
                    l++;
                }
            } else {
                r--;
                while (l < r && A[r] < min) {
                    res += min - A[r];
                    r--;
                }
            }
        }
        return res;
    }

    /**
     * Container With Most Water -- LeetCode
     * https://oj.leetcode.com/problems/container-with-most-water/
     * Given n non-negative integers a1, a2, ..., an, where each represents a point at coordinate (i, ai).
     * n vertical lines are drawn such that the two endpoints of line i is at (i, ai) and (i, 0).
     * Find two lines, which together with x-axis forms a container, such that the container contains the most water.
     */
    public int maxArea(int[] height) {
        if (height == null || height.length == 0)
            return 0;
        int l = 0;
        int r = height.length - 1;
        int maxArea = 0;
        while (l < r) {
            maxArea = Math.max(maxArea, Math.min(height[l], height[r]) * (r - l));
            if (height[l] < height[r])
                l++;
            else
                r--;
        }
        return maxArea;
    }

    /**
     * Rotate Image -- LeetCode
     * https://oj.leetcode.com/problems/rotate-image/
     * You are given an n x n 2D matrix representing an image.
     * Rotate the image by 90 degrees (clockwise).
     */
    public void rotate(int[][] matrix) {
        if (matrix == null || matrix[0].length <= 1)
            return;
        int N = matrix[0].length;
        for (int layer = 0; layer < N / 2; layer++) {
            int rowStart = layer;
            int rowEnd = N - 1 - layer;
            int colStart = layer;
            int colEnd = N - 1 - layer;
            rotate(matrix, rowStart, rowEnd, colStart, colEnd);
        }

    }

    private static void rotate(int[][] matrix, int rowStart, int rowEnd, int colStart, int colEnd) {
        for (int i = 0; i <= colEnd - colStart - 1; i++) {
            int temp = matrix[rowStart][colStart + i];
            matrix[rowStart][colStart + i] = matrix[rowEnd - i][colStart];
            matrix[rowEnd - i][colStart] = matrix[rowEnd][colEnd - i];
            matrix[rowEnd][colEnd - i] = matrix[rowStart + i][colEnd];
            matrix[rowStart + i][colEnd] = temp;
        }
    }

    /**
     * Spiral Matrix -- LeetCode
     * https://oj.leetcode.com/problems/spiral-matrix/
     * Given a matrix of m x n elements (m rows, n columns), return all elements of the matrix in spiral order.
     * For example,
     * Given the following matrix:
     * [
     * [ 1, 2, 3 ],
     * [ 4, 5, 6 ],
     * [ 7, 8, 9 ]
     * ]
     * You should return [1,2,3,6,9,8,7,4,5].
     */
    public ArrayList<Integer> spiralOrder(int[][] matrix) {
        ArrayList<Integer> res = new ArrayList<Integer>();
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0)
            return res;
        int min = Math.min(matrix.length, matrix[0].length);
        int levelNum = min / 2;
        for (int level = 0; level < levelNum; level++) {
            for (int i = level; i < matrix[0].length - level - 1; i++) {
                res.add(matrix[level][i]);
            }
            for (int i = level; i < matrix.length - level - 1; i++) {
                res.add(matrix[i][matrix[0].length - 1 - level]);
            }
            for (int i = matrix[0].length - 1 - level; i > level; i--) {
                res.add(matrix[matrix.length - 1 - level][i]);
            }
            for (int i = matrix.length - 1 - level; i > level; i--) {
                res.add(matrix[i][level]);
            }
        }
        if (min % 2 == 1) {
            if (matrix.length < matrix[0].length) {
                for (int i = levelNum; i < matrix[0].length - levelNum; i++) {
                    res.add(matrix[levelNum][i]);
                }
            } else {
                for (int i = levelNum; i < matrix.length - levelNum; i++) {
                    res.add(matrix[i][levelNum]);
                }
            }
        }
        return res;
    }

    /**
     * Merge Intervals -- LeetCode
     * https://oj.leetcode.com/problems/merge-intervals/
     * Given a collection of intervals, merge all overlapping intervals.
     * For example,
     * Given [1,3],[2,6],[8,10],[15,18],
     * return [1,6],[8,10],[15,18].
     */

    public ArrayList<Interval> merge(ArrayList<Interval> intervals) {
        // Handling special cases
        if (intervals == null)
            return null;
        ArrayList<Interval> result = new ArrayList<Interval>();
        if (intervals.size() == 0)
            return result;
        // Sort the intervals by their "start"s and "end"s
        Collections.sort(intervals, new Comparator<Interval>() {
            public int compare(Interval i1, Interval i2) {
                // Compare their "start"s first, then their
                // "end"s if their "start"s are equal
                return (i1.start == i2.start) ? i1.end - i2.end : i1.start - i2.start;
            }
        });
        // Traverse the sorted intervals and merge adjacent intervals if needed
        for (Interval interval : intervals) {
            if (result.isEmpty() || interval.start > result.get(result.size() - 1).end)
                // No interval added before, or no overlap between adjacent intervals
                result.add(interval);
            else {
                // Merge the current interval and its previous one
                Interval temp = result.get(result.size() - 1);
                temp.end = Math.max(temp.end, interval.end);
            }
        }

        return result;
    }

    /**
     * nsert Interval -- LeetCode
     * https://oj.leetcode.com/problems/insert-interval/
     * Given a set of non-overlapping intervals, insert a new interval into the intervals (merge if necessary).
     * You may assume that the intervals were initially sorted according to their start times.
     */
    public ArrayList<Interval> insert(ArrayList<Interval> intervals, Interval newInterval) {
        ArrayList<Interval> result = new ArrayList<Interval>();
        // No interval in the list
        if (intervals == null || intervals.size() == 0) {
            result.add(newInterval);
            return result;
        }

        boolean added = false;      // Indicate whether "newInterval" has been added
        // Traverse the (sorted) intervals and merge intervals
        // overlapping with "newInterval" if needed
        for (Interval interval : intervals) {
            if (interval.end < newInterval.start)   // Non-overlapping intervals ahead "newInterval"
                result.add(interval);
            else if (interval.start > newInterval.end) {    // Non-overlapping intervals behind "newInterval"
                // If "newInterval" has not been added, add it before the current interval
                if (!added) {
                    result.add(newInterval);
                    added = true;
                }
                result.add(interval);
            } else {    // Overlapping intervals
                // Merge the current interval with "newInterval", and reflect it in "newInterval"
                newInterval.start = Math.min(newInterval.start, interval.start);
                newInterval.end = Math.max(newInterval.end, interval.end);
            }
        }
        // In case "newInterval" has not been added in the loop
        if (!added)
            result.add(newInterval);

        return result;
    }

    /**
     * https://oj.leetcode.com/problems/spiral-matrix-ii/
     * Given an integer n, generate a square matrix filled with elements from 1 to n2 in spiral order.
     * For example,
     * Given n = 3,
     */
    public int[][] generateMatrix(int n) {
        if (n < 0)
            return null;
        int[][] res = new int[n][n];
        int levelNum = n / 2;
        int num = 1;
        for (int l = 0; l < levelNum; l++) {
            for (int i = l; i < n - l; i++) {
                res[l][i] = num++;
            }
            for (int i = l + 1; i < n - l; i++) {
                res[i][n - 1 - l] = num++;
            }
            for (int i = n - 2 - l; i >= l; i--) {
                res[n - 1 - l][i] = num++;
            }
            for (int i = n - 2 - l; i > l; i--) {
                res[i][l] = num++;
            }
        }
        if (n % 2 == 1) {
            res[levelNum][levelNum] = num;
        }
        return res;
    }

    /**
     * Plus One -- LeetCode
     * https://oj.leetcode.com/problems/plus-one/
     * Given a non-negative number represented as an array of digits, plus one to the number.
     * The digits are stored such that the most significant digit is at the head of the list.
     */
    public int[] plusOne(int[] digits) {
        if (digits == null || digits.length == 0)
            return digits;
        int carry = 1;
        for (int i = digits.length - 1; i >= 0; i--) {
            int digit = (digits[i] + carry) % 10;
            carry = (digits[i] + carry) / 10;
            digits[i] = digit;
            if (carry == 0)
                return digits;
        }
        int[] res = new int[digits.length + 1];
        res[0] = 1;
        return res;
    }

    public int[] plusOne2(int[] digits) {
        if (digits == null)
            return null;
        // Process the digits in reverse order
        for (int i = digits.length - 1; i >= 0; i--) {
            if (digits[i] < 9) {    // Adding 1 ends here
                digits[i] += 1;
                return digits;
            } else {        // Add 1 to a higher position
                digits[i] = 0;
            }
        }
        // No return from the above; the digits are in the form of 9...9,
        // and adding one makes it 10...0
        int[] result = new int[digits.length + 1];
        result[0] = 1;
        return result;
    }

    public String strStr(String haystack, String needle) {
        if (haystack == null || needle == null)
            return null;
        if (haystack.length() == 0) {
            return needle.length() == 0 ? "" : null;
        }
        if (needle.length() == 0)
            return haystack;
        if (haystack.length() < needle.length())
            return null;

        int base = 29;
        long patternHash = 0;
        long tempBase = 1;

        for (int i = needle.length() - 1; i >= 0; i--) {
            patternHash += (int) needle.charAt(i) * tempBase;
            tempBase *= base;
        }

        long hayHash = 0;
        tempBase = 1;
        for (int i = needle.length() - 1; i >= 0; i--) {
            hayHash += (int) haystack.charAt(i) * tempBase;
            tempBase *= base;
        }
        tempBase /= base;

        if (hayHash == patternHash) {
            return haystack;
        }

        for (int i = needle.length(); i < haystack.length(); i++) {
            hayHash =
                    (hayHash - (int) haystack.charAt(i - needle.length()) * tempBase) * base + (int) haystack.charAt(i);
            if (hayHash == patternHash) {
                return haystack.substring(i - needle.length() + 1);
            }
        }
        return null;
    }

    public int divide(int dividend, int divisor) {
        if (divisor == 0)
            return Integer.MAX_VALUE;
        int res = 0;
        if (dividend == Integer.MIN_VALUE) {
            res = 1;
            dividend += Math.abs(divisor);
        }
        if (divisor == Integer.MIN_VALUE)
            return res;
        boolean isNeg = ((dividend ^ divisor) >>> 31 == 1);
        dividend = Math.abs(dividend);
        divisor = Math.abs(divisor);

        while (dividend > divisor) {
            int power = 1;
            while ((divisor << power) >= (divisor << (power - 1)) && (divisor << power) <= dividend)
                ++power;

            res += 1 << (power - 1);
            dividend -= divisor << (power - 1);

        }
        return isNeg ? -res : res;

    }

    public boolean isPalindrome(int x) {
        if (x < 0)
            return false;
        int power = 1;
        while (x / power >= 10)
            power *= 10;

        while (x > 0) {
            if (x % 10 != x / power)
                return false;
            x %= power;
            x /= 10;
            power /= 100;
        }
        return true;

    }

    public List<List<String>> partition(String s) {
        List<List<String>> res = new ArrayList<List<String>>();
        if (s == null || s.length() == 0)
            return res;
        partition(s, 0, new ArrayList<String>(), res);
        return res;
    }

    private void partition(String s, int start, List<String> item, List<List<String>> res) {
        if (start >= s.length()) {
            res.add(item);
            return;
        }
        boolean[][] palin = getDict(s);

        for (int i = start + 1; i < s.length(); i++) {
            if (palin[start][i]) {
                item.add(s.substring(start, i + 1));
                partition(s, i + 1, new ArrayList<String>(item), res);
                item.remove(item.size() - 1);
            }
        }

    }

    private boolean[][] getDict(String s) {
        boolean[][] palin = new boolean[s.length()][s.length()];
        for (int i = s.length() - 1; i >= 0; i--) {
            for (int j = i; j < s.length(); j++) {
                if (s.charAt(i) == s.charAt(j) && (j - i < 2 || palin[i + 1][j - 1]))
                    palin[i][j] = true;
            }
        }
        return palin;
    }


}


