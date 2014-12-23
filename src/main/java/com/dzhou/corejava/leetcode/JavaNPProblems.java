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

}
