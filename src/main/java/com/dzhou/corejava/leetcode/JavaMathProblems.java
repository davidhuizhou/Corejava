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

    /**
     * Divide Two Integers -- LeetCode
     * https://oj.leetcode.com/problems/divide-two-integers/
     * Divide two integers without using multiplication, division and mod operator.
     * If it is overflow, return MAX_INT.
     */
    public int divide(int dividend, int divisor) {
        if (divisor == 0)
            return Integer.MAX_VALUE;

        int res = 0;
        if (dividend == Integer.MIN_VALUE) {
            res = 1;
            dividend += Math.abs(divisor);
        }

        //return 0 if dividend != Integer.MIN_VALUE
        //return 1 if dividend == Integer.MIN_VALUE;

        if (divisor == Integer.MIN_VALUE)
            return res;

        //check if it is negative
        boolean isNeg = ((dividend ^ divisor) >>> 31 == 1);
        dividend = Math.abs(dividend);
        divisor = Math.abs(divisor);
        int digit = 0;
        //find the largest multiple of divisor which is <= dividend/2
        //the multiple is 1 << digit
        while (divisor <= (dividend >> 1)) {
            divisor <<= 1;  //multiple divisor by 2 until reach dividend/2
            digit++;
        }
        while (digit >= 0) {
            if (dividend >= divisor) {
                dividend -= divisor; //deduct the multiple of divisor from dividend
                res += 1 << digit; //add the multiple to result
            }
            divisor >>= 1;
            digit--;
        }
        return isNeg ? -res : res;
    }

    public static int multiplyOperator(int x, int y) {
        int sum = 0;
        while (x > 0) {
            if ((x & 1) == 1) {
                sum = addOperator(sum, y);

            }
            x >>= 1;
            y <<= 1;
        }
        return sum;
    }

    private static int addOperator(int a, int b) {
        int sum = 0, carryin = 0, k = 1, temp_a = a, temp_b = b;
        while (temp_a > 0 || temp_b > 0) {
            int ak = a & k, bk = b & k;
            int carryout = (ak & bk) | (ak & carryin) | (bk & carryin);
            sum |= (ak ^ bk ^ carryin);
            carryin = carryout << 1;
            temp_a >>= 1;
            temp_b >>= 1;
            k <<= 1;
        }

        return sum | carryin;

    }

    public int atoi(String s, int b) {
        if (s == null)
            return 0;
        s = s.trim();
        if (s.length() == 0)
            return 0;
        boolean isNeg = false;
        int i = 0;
        if (s.charAt(0) == '+' || s.charAt(i) == '-') {
            if (s.charAt(i) == '-')
                isNeg = true;
            i++;
        }
        int res = 0;
        while (i < s.length()) {
            int digit = getDigit(s.charAt(i));
            if (digit < 0)
                break;

            if (isNeg && (res > -(Integer.MIN_VALUE + digit) / b))
                return Integer.MIN_VALUE;
            else if (!isNeg && (res > (Integer.MAX_VALUE - digit) / b))
                return Integer.MAX_VALUE;
            else
                res = res * b + digit;

            i++;
        }
        return isNeg ? -res : res;

    }

    public String itoa(int n, int b) {
        StringBuilder sb = new StringBuilder();
        boolean isNeg = b < 0;

        while (n != 0) {
            int r = Math.abs(n % b);
            n /= b;
            sb.insert(0, getChar(r));
        }
        if (isNeg)
            sb.insert(0, '-');

        return sb.toString();

    }

    private int getDigit(char c) {
        if ('0' <= c && c <= '9')
            return c - '0';
        else if ('a' <= c && c <= 'f')
            return c - 'a' + 10;
        else if ('A' <= c && c <= 'F')
            return c - 'A' + 10;
        else
            return -1;
    }

    private char getChar(int digit) {
        if (digit >= 10)
            return (char) ('A' + digit - 10);
        else
            return (char) ('0' + digit);
    }

    public static void main(String[] args) {
        System.out.println(addOperator(11, 5));
        System.out.println(multiplyOperator(17, 3));
        System.out.println(43 % (-5));
        System.out.println(-43 % 5);
        System.out.println(-43 % (-5));
    }

}
