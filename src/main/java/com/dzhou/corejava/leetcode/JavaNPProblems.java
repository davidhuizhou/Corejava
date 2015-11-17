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

    /**
     * Permutation Sequence -- LeetCode
     * https://oj.leetcode.com/problems/permutation-sequence/
     * The set [1,2,3,…,n] contains a total of n! unique permutations.
     * By listing and labeling all of the permutations in order,
     * We get the following sequence (ie, for n = 3):
     * "123"
     * "132"
     * "213"
     * "231"
     * "312"
     * "321"
     * Given n and k, return the kth permutation sequence.
     * Note: Given n will be between 1 and 9 inclusive.
     */
    public String getPermutation(int n, int k) {
        if (n <= 0)
            return "";
        k--;
        StringBuilder res = new StringBuilder();
        int factorial = 1;
        ArrayList<Integer> nums = new ArrayList<Integer>();
        //factorial = (n-1)!
        for (int i = 2; i < n; i++) {
            factorial *= i;
        }
        //nums = [1..n]
        for (int i = 1; i <= n; i++) {
            nums.add(i);
        }
        //round start with n-1
        int round = n - 1;
        while (round >= 0) {
            //find out which group k is and there are n groups which permutation starts with 1 .. n
            int index = k / factorial;
            //Get the position in the group index - starting nums is num.get(index)
            k %= factorial;
            res.append(nums.get(index));
            nums.remove(index);
            //next round - factorial becomes (n-2)!
            if (round > 0)
                factorial /= round;
            round--;
        }
        return res.toString();
    }

    /**
     * Gas Station -- LeetCode
     * https://oj.leetcode.com/problems/gas-station/
     * There are N gas stations along a circular route, where the amount of gas at station i is gas[i].
     * You have a car with an unlimited gas tank and it costs cost[i] of gas to travel from station i to its next station (i+1). You begin the journey with an empty tank at one of the gas stations.
     * Return the starting gas station's index if you can travel around the circuit once, otherwise return -1.
     */
    public int canCompleteCircuit(int[] gas, int[] cost) {
        if (gas == null || gas.length == 0 || cost == null || cost.length == 0 || gas.length != cost.length)
            return -1;
        int sum = 0;
        int total = 0;
        int pointer = -1;
        for (int i = 0; i < gas.length; i++) {
            int diff = gas[i] - cost[i];
            sum += diff;
            total += diff;
            if (sum < 0) {
                sum = 0;
                pointer = i;
            }
        }
        return total >= 0 ? pointer + 1 : -1;
    }

    public ArrayList<String[]> solveQueens(int n){
        ArrayList<String[]> res = new ArrayList<String[]>();
        if(n <= 0)
            return res;

        int[] col = new int[n];
        solveQueens(n, col, 0, res);
        return res;


    }

    private void solveQueens(int n, int[] col, int row, ArrayList<String[]> res){
        if(row >= n){
            String[] item = new String[n];
            for(int i = 0; i < n; i++){
                StringBuilder sb = new StringBuilder();
                for(int j = 0; j < n; j++){
                    if(j == col[i])
                        sb.append("Q");
                    else
                        sb.append(".");
                }
                item[i] = sb.toString();
            }
            res.add(item);
            return;
        }
        for(int j = 0; j < n; j++){
            col[row] = j;
            if(isValid(col, row));
            solveQueens(n, col, row+1, res);
        }
    }

    private boolean isValid(int[] col, int row){
        for(int i = 0; i < row; i++){
            if(col[i] == col[row] || Math.abs(col[i] - col[row]) == (row - i))
                return false;
        }
        return true;
    }







}

