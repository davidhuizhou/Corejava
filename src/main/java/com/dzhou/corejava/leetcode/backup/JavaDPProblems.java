package com.dzhou.corejava.leetcode.backup;

import java.util.ArrayList;
import java.util.Set;

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

    /**
     * Unique Paths -- LeetCode
     * https://oj.leetcode.com/problems/unique-paths/
     * A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).
     */
    public int uniquePaths(int m, int n) {
        int[][] c = new int[m][n];
        c[0][0] = 1;
        for (int i = 0; i < m; i++)
            c[i][0] = 1;
        for (int j = 0; j < n; j++)
            c[0][j] = 1;

        for (int i = 1; i < m; i++)
            for (int j = 1; j < n; j++)
                c[i][j] = c[i][j - 1] + c[i - 1][j];

        return c[m - 1][n - 1];

    }

    public int uniquePaths2(int m, int n) {
        if (m <= 0 || n <= 0)
            return 0;
        int[] res = new int[n];
        res[0] = 1;
        for (int i = 0; i < m; i++) {
            for (int j = 1; j < n; j++) {
                res[j] += res[j - 1];
            }
        }
        return res[n - 1];
    }

    /**
     * Unique Paths II -- LeetCode
     * https://oj.leetcode.com/problems/unique-paths-ii/
     * Follow up for "Unique Paths":
     * Now consider if some obstacles are added to the grids. How many unique paths would there be?
     * An obstacle and empty space is marked as 1 and 0 respectively in the grid.
     */
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;

        int[][] c = new int[m][n];

        if (obstacleGrid[0][0] == 1)
            return 0;

        c[0][0] = 1;

        for (int i = 0; i < m; i++) {
            if (obstacleGrid[i][0] == 1)
                break;
            c[i][0] = 1;
        }
        for (int j = 0; j < n; j++) {
            if (obstacleGrid[0][j] == 1)
                break;
            c[0][j] = 1;

        }

        for (int i = 1; i < m; i++)
            for (int j = 1; j < n; j++) {
                if (obstacleGrid[i][j] == 1)
                    c[i][j] = 0;
                else
                    c[i][j] = c[i][j - 1] + c[i - 1][j];
            }

        return c[m - 1][n - 1];

    }

    public int uniquePathsWithObstacles2(int[][] obstacleGrid) {
        if (obstacleGrid == null || obstacleGrid.length == 0 || obstacleGrid[0].length == 0)
            return 0;
        int[] res = new int[obstacleGrid[0].length];
        res[0] = 1;
        for (int i = 0; i < obstacleGrid.length; i++) {
            for (int j = 0; j < obstacleGrid[0].length; j++) {
                if (obstacleGrid[i][j] == 1) {
                    res[j] = 0;
                } else {
                    if (j > 0)
                        res[j] += res[j - 1];
                }
            }
        }
        return res[obstacleGrid[0].length - 1];
    }

    /**
     * Minimum Path Sum -- LeetCode
     * https://oj.leetcode.com/problems/minimum-path-sum/
     * Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right which minimizes the sum of all numbers along its path.
     * Note: You can only move either down or right at any point in time.
     */
    public int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        int[][] c = new int[m][n];
        c[0][0] = grid[0][0];

        for (int i = 1; i < m; i++)
            c[i][0] = grid[i][0] + c[i - 1][0];
        for (int j = 1; j < n; j++)
            c[0][j] = grid[0][j] + c[0][j - 1];

        for (int i = 1; i < m; i++)
            for (int j = 1; j < n; j++)
                c[i][j] = grid[i][j] + Math.min(c[i][j - 1], c[i - 1][j]);

        return c[m - 1][n - 1];

    }

    public int minPathSum2(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0)
            return 0;
        int m = grid.length, n = grid[0].length;
        int[] dp = new int[n];
        // Find the minimum sum of all numbers along a path to grid[i][j]
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0)   // The first cell
                    dp[j] = grid[i][j];
                else if (i == 0)        // Cells in the first row
                    dp[j] = dp[j - 1] + grid[i][j];
                else if (j == 0)        // Cells in the first column
                    dp[j] += grid[i][j];
                else                    // Others
                    dp[j] = Math.min(dp[j - 1], dp[j]) + grid[i][j];
            }
        }

        return dp[n - 1];
    }

    public int minPathSum3(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0)
            return 0;
        int[] res = new int[grid[0].length];
        res[0] = grid[0][0];
        for (int i = 1; i < grid[0].length; i++) {
            res[i] = res[i - 1] + grid[0][i];
        }
        for (int i = 1; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (j == 0)
                    res[j] += grid[i][j];
                else
                    res[j] = Math.min(res[j - 1], res[j]) + grid[i][j];
            }
        }
        return res[grid[0].length - 1];
    }

    /**
     * Word Break -- LeetCode
     * https://oj.leetcode.com/problems/word-break/
     * Given a string s and a dictionary of words dict, determine if s can be segmented into a space-separated sequence of one or more dictionary words.
     * For example, given
     * s = "leetcode",
     * dict = ["leet", "code"].
     * Return true because "leetcode" can be segmented as "leet code”.
     */
    public static boolean wordBreak(String s, Set<String> dict) {
        if (s == null || dict == null)
            return false;
        if (dict.contains(s))
            return true;

        int n = s.length();
        boolean[] c = new boolean[n];

        c[0] = dict.contains(s.substring(0, 1));

        for (int i = 1; i < n; i++) {
            if (dict.contains(s.substring(0, i + 1)))
                c[i] = true;
            else {
                for (int k = 0; k < i; k++) {
                    if (c[k] && dict.contains(s.substring(k + 1, i + 1))) {
                        c[i] = true;
                        break;
                    }

                }
            }
        }

        return c[n - 1];

    }

    public boolean wordBreak2(String s, Set<String> dict) {
        if (s == null || s.length() == 0)
            return true;
        boolean[] res = new boolean[s.length() + 1];
        res[0] = true;
        for (int i = 0; i < s.length(); i++) {
            StringBuilder str = new StringBuilder(s.substring(0, i + 1));
            for (int j = 0; j <= i; j++) {
                if (res[j] && dict.contains(str.toString())) {
                    res[i + 1] = true;
                    break;
                }
                str.deleteCharAt(0);
            }
        }
        return res[s.length()];
    }

    /**
     * Word Break II -- LeetCode
     * https://oj.leetcode.com/problems/word-break-ii/
     * Given a string s and a dictionary of words dict, add spaces in s to construct a sentence where each word is a valid dictionary word.
     * Return all such possible sentences.
     * For example, given
     * s = "catsanddog",
     * dict = ["cat", "cats", "and", "sand", "dog"].
     * A solution is ["cats and dog", "cat sand dog”].
     */
    public ArrayList<String> wordBreak3(String s, Set<String> dict) {
        ArrayList<String> res = new ArrayList<String>();
        if (s == null || s.length() == 0)
            return res;
        helper(s, dict, 0, "", res);
        return res;
    }

    private void helper(String s, Set<String> dict, int start, String item, ArrayList<String> res) {
        if (start >= s.length()) {
            res.add(item);
            return;
        }
        StringBuilder str = new StringBuilder();
        for (int i = start; i < s.length(); i++) {
            str.append(s.charAt(i));
            if (dict.contains(str.toString())) {
                String newItem = item.length() > 0 ? (item + " " + str.toString()) : str.toString();
                helper(s, dict, i + 1, newItem, res);
            }
        }
    }


    /**
     * Edit Distance -- LeetCode
     * https://oj.leetcode.com/problems/edit-distance/
     * <p/>
     * Given two words word1 and word2, find the minimum number of steps required to convert word1 to word2. (each operation is counted as 1 step.)
     * <p/>
     * You have the following 3 operations permitted on a word:
     * <p/>
     * a) Insert a character
     * b) Delete a character
     * c) Replace a character
     */
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

    public boolean isOneEditDistance(String s, String t) {
        int m = s.length(), n = t.length();
        if (m > n) return isOneEditDistance(t, s);
        if (n - m > 1) return false;

        int index = 0;
        while (index < m && s.charAt(index) == t.charAt(index))
            index++;

        if (n == m) {

            if (index == m)
                return false;

            index++;
            while (index < m && s.charAt(index) == t.charAt(index))
                index++;

        } else {

            if (index == m)
                return true;

            while (index < m && s.charAt(index) == t.charAt(index + 1))
                index++;

        }
        return index == m;
    }


    public String longestPalindrome(String s) {
        if (s == null || s.length() == 0)
            return "";
        boolean[][] palin = new boolean[s.length()][s.length()];
        String res = "";
        int maxLen = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            for (int j = i; j < s.length(); j++) {
                if (s.charAt(i) == s.charAt(j) && (j - i <= 2 || palin[i + 1][j - 1])) {
                    palin[i][j] = true;
                    if (maxLen < j - i + 1) {
                        maxLen = j - i + 1;
                        res = s.substring(i, j + 1);
                    }
                }
            }
        }
        return res;
    }

    public int minCut(String s) {
        if (s == null || s.length() == 0)
            return 0;
        boolean[][] dict = getDict(s);
        int[] res = new int[s.length() + 1];
        res[0] = 0;
        for (int i = 0; i < s.length(); i++) {
            res[i + 1] = i + 1;
            for (int j = 0; j <= i; j++) {
                if (dict[j][i]) {
                    res[i + 1] = Math.min(res[i + 1], res[j] + 1);
                }
            }
        }
        return res[s.length()] - 1;
    }

    private boolean[][] getDict(String s) {
        boolean[][] dict = new boolean[s.length()][s.length()];
        for (int i = s.length() - 1; i >= 0; i--) {
            for (int j = i; j < s.length(); j++) {
                if (s.charAt(i) == s.charAt(j) && (j - i < 2 || dict[i + 1][j - 1]))
                    dict[i][j] = true;
            }
        }
        return dict;
    }


    public boolean workBreak(String s, Set<String> dict) {
        if (s == null || dict == null)
            return false;
        if (dict.contains(s))
            return true;

        int n = s.length();
        boolean[] c = new boolean[n];

        c[0] = dict.contains(s.substring(0, 1));
        for (int i = 0; i < n; i++) {
            if (dict.contains(s.substring(0, i + 1)))
                return true;
            else {
                for (int k = 0; k < i; k++) {
                    if (c[k] && dict.contains(s.substring(k + 1, i + 1))) {
                        c[i] = true;
                        break;
                    }

                }
            }
        }
        return c[n - 1];
    }






}
