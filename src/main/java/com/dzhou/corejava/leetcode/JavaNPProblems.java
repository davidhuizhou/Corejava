package com.dzhou.corejava.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by davidzhou on 12/23/14.
 */
public class JavaNPProblems {

    /*
    *   Combinations -- LeetCode
    *   https://oj.leetcode.com/problems/combinations/
    *   Given two integers n and k, return all possible combinations of k numbers out of 1 ... n.

            For example,
    If n = 4 and k = 2, a solution is:

            [
            [2,4],
            [3,4],
            [2,3],
            [1,2],
            [1,3],
            [1,4],
            ]
    */
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if (n <= 0 || n < k)
            return res;

        helper(n, k, 0, new ArrayList<Integer>(), res);
        return res;

    }

    private void helper(int n, int k, int start, List<Integer> item, List<List<Integer>> res) {
        if (item.size() == k) {
            res.add(new ArrayList<Integer>(item));
        }
        for (int i = start; i <= n; i++) {
            item.add(i);
            helper(n, k, i + 1, item, res);
            item.remove(item.size() - 1);
        }
    }

    /**
     * https://oj.leetcode.com/problems/permutations/
     * Given a collection of numbers, return all possible permutations.
     * For example,
     * [1,2,3] have the following permutations:
     * [1,2,3], [1,3,2], [2,1,3], [2,3,1], [3,1,2], and [3,2,1].
     */
    public static List<List<Integer>> permute(int[] num) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        result.add(new ArrayList<Integer>());

        for (int i = 0; i < num.length; i++) {
            List<List<Integer>> current = new ArrayList<List<Integer>>();

            for (List<Integer> l : result) {
                for (int j = 0; j <= l.size(); j++) {
                    l.add(j, num[i]);

                    List<Integer> temp = new ArrayList<Integer>(l);
                    current.add(temp);

                    l.remove(j);
                }

            }

            result = new ArrayList<List<Integer>>(current);

        }
        return result;
    }

    public List<List<Integer>> permute1(int[] num) {
        if (num == null)
            return null;
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if (num.length == 0)
            return result;
        recursivePermute(num, new boolean[num.length], new ArrayList<Integer>(), result);
        return result;
    }

    // If "current" is already a permutation of "num", add it to "result";
    // otherwise, append each unused number to "current", and recursively try next unused number
    private void recursivePermute(int[] num, boolean[] used, ArrayList<Integer> current,
            List<List<Integer>> result) {
        if (current.size() == num.length) {     // "current" is already a permutation of "num"
            result.add(new ArrayList<Integer>(current));
            return;
        }
        // Append each unused number to "current", and recursively try next unused number
        for (int i = 0; i < num.length; i++) {
            if (!used[i]) {
                // Append an unused number
                used[i] = true;
                current.add(num[i]);
                // Recursively append next unused number
                recursivePermute(num, used, current, result);
                // Get back to original state, get ready for appending another unused number
                current.remove(current.size() - 1);
                used[i] = false;
            }
        }
    }

    /**
     * Maximum Subarray -- LeetCode
     * https://oj.leetcode.com/problems/maximum-subarray/
     * Find the contiguous subarray within an array (containing at least one number) which has the largest sum.
     * For example, given the array [−2,1,−3,4,−1,2,1,−5,4],
     * the contiguous subarray [4,−1,2,1] has the largest sum = 6.
     */
    public int maxSubArray(int[] A) {
        if (A == null || A.length == 0)
            return 0;
        int global = A[0];
        int local = A[0];
        for (int i = 1; i < A.length; i++) {
            local = Math.max(A[i], local + A[i]);
            global = Math.max(local, global);
        }
        return global;
    }

    /**
     * Jump Game -- LeetCode
     * https://oj.leetcode.com/problems/jump-game/
     * Given an array of non-negative integers, you are initially positioned at the first index of the array.
     * Each element in the array represents your maximum jump length at that position.
     * Determine if you are able to reach the last index.
     * For example:
     * A = [2,3,1,1,4], return true.
     * A = [3,2,1,0,4], return false.
     */
    public boolean canJump(int[] A) {
        if (A == null || A.length == 0)
            return false;
        int reach = 0;
        for (int i = 0; i <= reach && i < A.length; i++) {
            reach = Math.max(A[i] + i, reach);
        }
        if (reach < A.length - 1)
            return false;
        return true;
    }

    /**
     * Jump Game II -- LeetCode
     * https://oj.leetcode.com/problems/jump-game-ii/
     * Given an array of non-negative integers, you are initially positioned at the first index of the array.
     * Each element in the array represents your maximum jump length at that position.
     * Your goal is to reach the last index in the minimum number of jumps.
     * For example:
     * Given array A = [2,3,1,1,4]
     * The minimum number of jumps to reach the last index is 2. (Jump 1 step from index 0 to 1, then 3 steps to the last index.)
     */
    public int jump(int[] A) {
        if (A == null || A.length == 0)
            return 0;
        int lastReach = 0;
        int reach = 0;
        int step = 0;
        for (int i = 0; i <= reach && i < A.length; i++) {
            if (i > lastReach) {
                step++;
                lastReach = reach;
            }
            reach = Math.max(reach, A[i] + i);
        }
        if (reach < A.length - 1)
            return 0;
        return step;
    }

}
