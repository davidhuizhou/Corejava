package com.dzhou.corejava.leetcode.backup;

/**
 * Created by huizhou on 1/3/15.
 */
public class JavaGreedyProblems {
    /**
     * Jump Game II -- LeetCode
     * https://oj.leetcode.com/problems/jump-game-ii/
     * <p/>
     * Given an array of non-negative integers, you are initially positioned at the first index of the array.
     * <p/>
     * Each element in the array represents your maximum jump length at that position.
     * <p/>
     * Your goal is to reach the last index in the minimum number of jumps.
     * <p/>
     * For example:
     * Given array A = [2,3,1,1,4]
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
     * Gas Station -- LeetCode
     * https://oj.leetcode.com/problems/gas-station/
     * There are N gas stations along a circular route, where the amount of gas at station i is gas[i].
     * <p/>
     * You have a car with an unlimited gas tank and it costs cost[i] of gas to travel from station i to its next station (i+1). You begin the journey with an empty tank at one of the gas stations.
     * <p/>
     * Return the starting gas station's index if you can travel around the circuit once, otherwise return -1.
     * <p/>
     * Note:
     * The solution is guaranteed to be unique.
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
}
