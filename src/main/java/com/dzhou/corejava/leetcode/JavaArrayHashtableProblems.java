package com.dzhou.corejava.leetcode;

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

}
