package com.dzhou.corejava.leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

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

}
