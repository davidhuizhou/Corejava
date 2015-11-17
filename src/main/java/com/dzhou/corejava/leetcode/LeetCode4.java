package com.dzhou.corejava.leetcode;

/**
 * Created by huizhou on 11/15/14.
 */

import java.util.*;

public class LeetCode4 {


    public List<String> letterCombinations(String digits) {
        ArrayList<String> res = new ArrayList<String>();
        res.add("");
        if (digits == null || digits.length() == 0)
            return res;
        for (int i = 0; i < digits.length(); i++) {
            String letters = getLetters(digits.charAt(i));
            ArrayList<String> newRes = new ArrayList<String>();
            for (int j = 0; j < res.size(); j++) {
                for (int k = 0; k < letters.length(); k++) {
                    newRes.add(res.get(j) + Character.toString(letters.charAt(k)));
                }
            }
            res = newRes;
        }
        return res;
    }

    private String getLetters(char digit) {
        switch (digit) {
            case '2':
                return "abc";
            case '3':
                return "def";
            case '4':
                return "ghi";
            case '5':
                return "jkl";
            case '6':
                return "mno";
            case '7':
                return "pqrs";
            case '8':
                return "tuv";
            case '9':
                return "wxyz";
            case '0':
                return " ";
            default:
                return "";
        }
    }

    public static int minDistance(String word1, String word2) {
        int len1 = word1.length();
        int len2 = word2.length();

        // len1+1, len2+1, because finally return dp[len1][len2]
        int[][] dp = new int[len1 + 1][len2 + 1];

        for (int i = 0; i <= len1; i++) {
            dp[i][0] = i;
        }

        for (int j = 0; j <= len2; j++) {
            dp[0][j] = j;
        }

        //iterate though, and check last char
        for (int i = 0; i < len1; i++) {
            char c1 = word1.charAt(i);
            for (int j = 0; j < len2; j++) {
                char c2 = word2.charAt(j);

                //if last two chars equal
                if (c1 == c2) {
                    //update dp value for +1 length
                    dp[i + 1][j + 1] = dp[i][j];
                } else {
                    int replace = dp[i][j] + 1;
                    int insert = dp[i][j + 1] + 1;
                    int delete = dp[i + 1][j] + 1;

                    int min = replace > insert ? insert : replace;
                    min = delete > min ? min : delete;
                    dp[i + 1][j + 1] = min;
                }
            }
        }

        return dp[len1][len2];
    }

    public class Solution {
        public boolean isOneEditDistance(String s, String t) {
            int m = s.length(), n = t.length();
            if (m > n) return isOneEditDistance(t, s);
            if (n - m > 1) return false;
            int i = 0, shift = n - m;
            while (i < m && s.charAt(i) == t.charAt(i)) ++i;
            if (i == m) return shift > 0; // if two string are the same (shift==0), return false
            if (shift == 0) i++; // if n==m skip current char in s (modify operation in s)
            while (i < m && s.charAt(i) == t.charAt(i + shift)) i++; // use shift to skip one char in t
            return i == m;
        }
    }

    public static String findMaxKCharSubstring(String s, int k) {
        int n = s.length();
        String longest = "";
        if (s == null || s.length() == 0 || k == 0)
            return longest;

        int distinct = 0;
        int start = 0;
        Map<Character, Integer> countMap = new HashMap<Character, Integer>();
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            Integer count = countMap.get(c);
            if (count == null || count == 0) {
                while (distinct == k) {
                    char lc = s.charAt(start);
                    countMap.put(lc, countMap.get(lc) - 1);
                    if (countMap.get(lc) == 0)
                        distinct--;
                    start++;
                }
                distinct++;
                countMap.put(c, 1);
            } else {
                countMap.put(c, countMap.get(c) + 1);
            }
            if (i - start + 1 > longest.length())
                longest = s.substring(start, i + 1);

        }
        return longest;
    }

    public static boolean isInterleave(String s1, String s2, String s3) {
        if (s1.length() + s2.length() != s3.length())
            return false;
        boolean[][] res = new boolean[s1.length() + 1][s2.length() + 1];
        res[0][0] = true;

        for (int j = 0; j < s2.length(); j++) {
            res[0][j + 1] = res[0][j] && s2.charAt(j) == s3.charAt(j);
        }


        for (int i = 0; i < s1.length(); i++) {
            res[i + 1][0] = res[i][0] && s1.charAt(i) == s3.charAt(i);
            for (int j = 0; j < s2.length(); j++) {
                res[i + 1][j + 1] = res[i][j + 1] && s1.charAt(i) == s3.charAt(i + j + 1)
                        || res[i + 1][j] && s2.charAt(j) == s3.charAt(i + j + 1);
            }
        }

        return res[s1.length()][s2.length()];
    }

    public static void main(String[] args) {
        System.out.println(findMaxKCharSubstring("abcbbbbcccbdddadacb", 3));

        System.out.println(isInterleave("aabcc", "dbbca", "aadbbcbcac"));
        System.out.println(isInterleave("aabcc", "dbbca", "aadbbbaccc"));
    }


}





