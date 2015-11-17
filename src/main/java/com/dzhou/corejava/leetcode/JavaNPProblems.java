package com.dzhou.corejava.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * Created by davidzhou on 12/23/14.
 */
public class JavaNPProblems {

    /**
     * Combination Sum -- LeetCode
     * https://oj.leetcode.com/problems/combination-sum/
     * Given a set of candidate numbers (C) and a target number (T), find all unique combinations in C where the candidate numbers sums to T.
     * <p/>
     * The same repeated number may be chosen from C unlimited number of times.
     */
    public ArrayList<ArrayList<Integer>> combinationSum(int[] candidates, int target) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
        if (candidates == null || candidates.length == 0)
            return res;
        Arrays.sort(candidates);
        helper(candidates, 0, target, new ArrayList<Integer>(), res);
        return res;
    }

    private void helper(int[] candidates, int start, int target, ArrayList<Integer> item,
                        ArrayList<ArrayList<Integer>> res) {
        if (target < 0)
            return;
        if (target == 0) {
            res.add(new ArrayList<Integer>(item));
            return;
        }
        for (int i = start; i < candidates.length; i++) {
            //The same number can be used unlimited times
            if (i > 0 && candidates[i] == candidates[i - 1])
                continue;
            item.add(candidates[i]);
            //start is still i
            helper(candidates, i, target - candidates[i], item, res);
            item.remove(item.size() - 1);
        }
    }


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
    public ArrayList<ArrayList<Integer>> combine(int n, int k) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
        if (n <= 0 || n < k)
            return res;
        helper(n, k, 1, new ArrayList<Integer>(), res);
        return res;
    }

    private void helper(int n, int k, int start, ArrayList<Integer> item, ArrayList<ArrayList<Integer>> res) {
        if (item.size() == k) {
            res.add(new ArrayList<Integer>(item));
            return;
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


    public ArrayList<ArrayList<Integer>> permute1(int[] num) {
        if (num == null)
            return null;
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        if (num.length == 0)
            return result;
        recursivePermute(num, new boolean[num.length], new ArrayList<Integer>(), result);
        return result;
    }

    // If "current" is already a permutation of "num", add it to "result";
    // otherwise, append each unused number to "current", and recursively try next unused number
    private void recursivePermute(int[] num, boolean[] used, ArrayList<Integer> current,
                                  ArrayList<ArrayList<Integer>> result) {
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

    /*
    https://oj.leetcode.com/problems/subsets/

    Given a set of distinct integers, S, return all possible subsets.

Note:
Elements in a subset must be in non-descending order.
The solution set must not contain duplicate subsets.
For example,
If S = [1,2,3], a solution is:

[
  [3],
  [1],
  [2],
  [1,2,3],
  [1,3],
  [2,3],
  [1,2],
  []
]

     */
    public ArrayList<ArrayList<Integer>> subsets(int[] num) {
        if (num == null)
            return null;
        Arrays.sort(num);
        return helper(num, num.length - 1);
    }

    private ArrayList<ArrayList<Integer>> helper(int[] num, int index) {
        if (index == -1) {
            ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
            ArrayList<Integer> elem = new ArrayList<Integer>();
            res.add(elem);
            return res;
        }
        ArrayList<ArrayList<Integer>> res = helper(num, index - 1);
        int size = res.size();
        for (int i = 0; i < size; i++) {
            ArrayList<Integer> elem = new ArrayList<Integer>(res.get(i));
            elem.add(num[index]);
            res.add(elem);
        }
        return res;
    }

    public ArrayList<ArrayList<Integer>> subsets1(int[] S) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
        res.add(new ArrayList<Integer>());
        if (S == null || S.length == 0)
            return res;
        Arrays.sort(S);
        for (int i = 0; i < S.length; i++) {
            int size = res.size();
            for (int j = 0; j < size; j++) {
                ArrayList<Integer> item = new ArrayList<Integer>(res.get(j));
                item.add(S[i]);
                res.add(item);
            }
        }
        return res;
    }

    /**
     * Gray Code -- LeetCode
     * https://oj.leetcode.com/problems/gray-code/
     * The gray code is a binary numeral system where two successive values differ in only one bit.
     * <p/>
     * Given a non-negative integer n representing the total number of bits in the code, print the sequence of gray code. A gray code sequence must begin with 0.
     * <p/>
     * For example, given n = 2, return [0,1,3,2]. Its gray code sequence is:
     * <p/>
     * 00 - 0
     * 01 - 1
     * 11 - 3
     * 10 - 2
     */
    public static List<Integer> grayCode(int n) {
        List<Integer> result = new ArrayList<Integer>();
        if (n < 0) return result;

        if (n == 0) {
            result.add(0);
            return result;
        } else if (n == 1) {
            result.add(0);
            result.add(1);
            return result;
        } else {

            result = grayCode(n - 1);
            Stack<Integer> stack = new Stack<Integer>();
            for (Integer x : result)
                stack.push(x);
            ;
            int mask = 1 << (n - 1);
            while (!stack.isEmpty())
                result.add(mask | stack.pop());

            return result;
        }

    }

    /**
     * Restore IP Addresses -- LeetCode
     * https://oj.leetcode.com/problems/restore-ip-addresses/
     * Given a string containing only digits, restore it by returning all possible valid IP address combinations.
     * <p/>
     * For example:
     * Given "25525511135",
     * <p/>
     * return ["255.255.11.135", "255.255.111.35"]. (Order does not matter)
     */
    public ArrayList<String> restoreIpAddresses(String s) {
        ArrayList<String> res = new ArrayList<String>();
        if (s == null || s.length() == 0)
            return res;
        helper(s, 0, 1, "", res);
        return res;
    }

    private void helper(String s, int index, int segment, String item, ArrayList<String> res) {
        if (index >= s.length())
            return;
        if (segment == 4) {
            String str = s.substring(index);
            if (isValid(str)) {
                res.add(item + "." + str);
            }
            return;
        }
        for (int i = 1; i < 4 && (i + index <= s.length()); i++) {
            String str = s.substring(index, index + i);
            if (isValid(str)) {
                if (segment == 1)
                    helper(s, index + i, segment + 1, str, res);
                else
                    helper(s, index + i, segment + 1, item + "." + str, res);
            }
        }
    }

    private boolean isValid(String str) {
        if (str == null || str.length() > 3)
            return false;
        int num = Integer.parseInt(str);
        if (str.charAt(0) == '0' && str.length() > 1)
            return false;
        if (num >= 0 && num <= 255)
            return true;
        return false;
    }


    /**
     * Sum Root to Leaf Numbers -- LeetCode
     * https://oj.leetcode.com/problems/sum-root-to-leaf-numbers/
     * Given a binary tree containing digits from 0-9 only, each root-to-leaf path could represent a number.
     * <p/>
     * An example is the root-to-leaf path 1->2->3 which represents the number 123.
     * <p/>
     * Find the total sum of all root-to-leaf numbers.
     */
    public int sumNumber(TreeNode root) {
        return helper(root, 0);
    }

    private int helper(TreeNode root, int sum) {
        if (root == null)
            return 0;
        if (root.left == null && root.right == null)
            return 10 * sum + root.val;

        return helper(root.left, 10 * sum + root.val) + helper(root.right, 10 * sum + root.val);
    }


    /**
     * Binary Tree Maximum Path Sum -- LeetCode
     * https://oj.leetcode.com/problems/binary-tree-maximum-path-sum/
     * Given a binary tree, find the maximum path sum.
     * <p/>
     * The path may start and end at any node in the tree.
     */
    public int maxPathSum(TreeNode root) {
        if (root == null)
            return 0;
        ArrayList<Integer> res = new ArrayList<Integer>();
        res.add(Integer.MIN_VALUE);
        helper(root, res);
        return res.get(0);
    }

    private int helper(TreeNode root, ArrayList<Integer> res) {
        if (root == null)
            return 0;
        int left = helper(root.left, res);
        int right = helper(root.right, res);
        int cur = root.val + (left > 0 ? left : 0) + (right > 0 ? right : 0);
        if (cur > res.get(0))
            res.set(0, cur);
        return root.val + Math.max(left, Math.max(right, 0));
    }


    /**
     * https://oj.leetcode.com/problems/path-sum/
     * Given a binary tree and a sum, determine if the tree has a root-to-leaf path such that adding up all the values along the path equals the given sum.
     */
    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null)
            return false;
        if (root.left == null && root.right == null && root.val == sum)
            return true;
        return hasPathSum(root.left, sum - root.val) || hasPathSum(root.right, sum - root.val);
    }

}

