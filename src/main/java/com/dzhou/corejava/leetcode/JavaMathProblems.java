package com.dzhou.corejava.leetcode;

/**
 * Created by davidzhou on 12/23/14.
 */
public class JavaMathProblems {

    /**
     * String to Integer (atoi) -- LeetCode
     * https://oj.leetcode.com/problems/string-to-integer-atoi/
     * Implement atoi to convert a string to an integer.
     */

    public int atoi(String str) {
        if (str == null) {
            return 0;
        }
        str = str.trim();
        if (str.length() == 0)
            return 0;
        boolean isNeg = false;
        int i = 0;
        if (str.charAt(0) == '-' || str.charAt(0) == '+') {
            i++;
            if (str.charAt(0) == '-')
                isNeg = true;
        }
        int res = 0;
        while (i < str.length()) {
            if (str.charAt(i) < '0' || str.charAt(i) > '9')
                break;
            int digit = (int) (str.charAt(i) - '0');
            if (isNeg && res > -((Integer.MIN_VALUE + digit) / 10))
                return Integer.MIN_VALUE;
            else if (!isNeg && res > (Integer.MAX_VALUE - digit) / 10)
                return Integer.MAX_VALUE;
            res = res * 10 + digit;
            i++;
        }
        return isNeg ? -res : res;
    }

    /**
     * Palindrome Number -- LeetCode
     * https://oj.leetcode.com/problems/palindrome-number/
     * Determine whether an integer is a palindrome. Do this without extra space.
     */
    public boolean isPalindrome(int x) {
        if (x < 0)
            return false;
        int div = 1;
        while (div <= x / 10)
            div *= 10;
        while (x > 0) {
            if (x / div != x % 10)
                return false;
            x = (x % div) / 10;
            div /= 100;
        }
        return true;
    }

    

}
