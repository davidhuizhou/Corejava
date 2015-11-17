package com.dzhou.corejava.leetcode;

/**
 * Created by davidzhou on 12/26/14.
 */
public class JavaBitProblems {
    /**
     * https://oj.leetcode.com/problems/single-number-ii/
     * Given an array of integers, every element appears three times except for one. Find that single one.
     */
    public int singleNumber2(int[] A) {
        int one = 0, two = 0, three = ~0;

        for (int i = 0; i < A.length; ++i) {
            int cur = A[i];
            int one_next = (one & (~cur)) | (cur & three);
            int two_next = (two & (~cur)) | (cur & one);
            int three_next = (three & (~cur)) | (cur & two);
            one = one_next;
            two = two_next;
            three = three_next;
        }

        return one;

    }

    public int singleNumber22(int[] A) {
        int[] digits = new int[32];
        for (int i = 0; i < 32; i++) {
            for (int j = 0; j < A.length; j++) {
                digits[i] += (A[j] >> i) & 1;
            }
        }
        int res = 0;
        for (int i = 0; i < 32; i++) {
            res += (digits[i] % 3) << i;
        }
        return res;
    }

    /**
     * Single Number -- LeetCode
     * https://oj.leetcode.com/problems/single-number/
     * Given an array of integers, every element appears twice except for one. Find that single one.
     */
    public static int singleNumber(int[] A) {
        int x = 0;

        for (int a : A) {
            x = x ^ a;
        }

        return x;
    }

    public int singleNumber3(int[] A) {
        int one = 0, two = ~0;

        for (int i = 0; i < A.length; ++i) {
            int cur = A[i];
            int one_next = (one & (~cur)) | (cur & two);
            int two_next = (two & (~cur)) | (cur & one);
            one = one_next;
            two = two_next;
        }

        return one;

    }

    public int singleNumber4(int[] A) {
        int[] digits = new int[32];
        for (int i = 0; i < 32; i++) {
            for (int j = 0; j < A.length; j++) {
                digits[i] += (A[j] >> i) & 1;
            }
        }
        int res = 0;
        for (int i = 0; i < 32; i++) {
            res += (digits[i] % 2) << i;
        }
        return res;
    }
}
