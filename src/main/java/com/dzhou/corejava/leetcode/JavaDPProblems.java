package com.dzhou.corejava.leetcode;

/**
 * Created by davidzhou on 12/24/14.
 */
public class JavaDPProblems {

    /**
     * https://oj.leetcode.com/problems/regular-expression-matching/
     * Implement regular expression matching with support for '.' and '*'.
     * '.' Matches any single character.
     * '*' Matches zero or more of the preceding element.
     */
    public boolean isMatch(String s, String p) {
        return helper(s, p, 0, 0);
    }

    private boolean helper(String s, String p, int i, int j) {
        if (j == p.length())
            return i == s.length();

        if (j == p.length() - 1 || p.charAt(j + 1) != '*') {
            if (i == s.length() || s.charAt(i) != p.charAt(j) && p.charAt(j) != '.')
                return false;
            else
                return helper(s, p, i + 1, j + 1);
        }
        //p.charAt(j+1)=='*'
        while (i < s.length() && (p.charAt(j) == '.' || s.charAt(i) == p.charAt(j))) {
            if (helper(s, p, i, j + 2))
                return true;
            i++;
        }
        return helper(s, p, i, j + 2);
    }

    public boolean isMatch1(String s, String p) {
        if (s.length() == 0 && p.length() == 0)
            return true;
        if (p.length() == 0)
            return false;
        boolean[][] res = new boolean[s.length() + 1][p.length() + 1];
        res[0][0] = true;
        for (int j = 0; j < p.length(); j++) {
            if (p.charAt(j) == '*') {
                if (j > 0 && res[0][j - 1])
                    res[0][j + 1] = true;
                if (j < 1)
                    continue;
                if (p.charAt(j - 1) != '.') {
                    for (int i = 0; i < s.length(); i++) {
                        if (res[i + 1][j] || j > 0 && res[i + 1][j - 1]
                                || i > 0 && j > 0 && res[i][j + 1] && s.charAt(i) == s.charAt(i - 1)
                                && s.charAt(i - 1) == p.charAt(j - 1))
                            res[i + 1][j + 1] = true;
                    }
                } else {
                    int i = 0;
                    while (j > 0 && i < s.length() && !res[i + 1][j - 1] && !res[i + 1][j])
                        i++;
                    for (; i < s.length(); i++) {
                        res[i + 1][j + 1] = true;
                    }
                }
            } else {
                for (int i = 0; i < s.length(); i++) {
                    if (s.charAt(i) == p.charAt(j) || p.charAt(j) == '.')
                        res[i + 1][j + 1] = res[i][j];
                }
            }
        }
        return res[s.length()][p.length()];
    }

    /**
     * Wildcard Matching -- LeetCode
     * https://oj.leetcode.com/problems/wildcard-matching/
     * '?' Matches any single character.
     * '*' Matches any sequence of characters (including the empty sequence).
     * The matching should cover the entire input string (not partial).
     */
    public static boolean isMatch2(String s, String p) {
        if (s == null || p == null)
            return false;
        int m = s.length(), n = p.length();
        boolean[] match = new boolean[m + 1];     // Used for dynamic programming
        match[0] = true;
        // After each round, the information whether p[0...i] matches s[0...m-1]
        // is updated in match[1...m]
        for (int i = 0; i < n; i++) {
            if (p.charAt(i) != '*') {   // Not wildcard
                for (int j = m; j > 0; j--)     // Update match backwards, it has to be backwards,
                    // otherwise match[j-1] become true,
                    // but it's newly updated true should not be used for match[j]
                    match[j] = match[j - 1] &&
                            (p.charAt(i) == '?' || s.charAt(j - 1) == p.charAt(i));
            } else {    // Wildcard
                int j = 0;
                // Find the minimum j with p[0...i-1] matching s[0...j]
                while (j <= m && !match[j])
                    j++;
                // Since p[i] is '*' that matches any sequence, p[0...i] matches with
                // s[0...j+1], s[0...j+2] ...
                for (; j <= m; j++)
                    match[j] = true;
            }
            match[0] = match[0] && p.charAt(i) == '*';
        }

        return match[m];

    }

}
