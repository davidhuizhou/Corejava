package com.dzhou.corejava.leetcode.backup;

import java.util.*;

/**
 * Created by davidzhou on 12/24/14.
 */
public class JavaStringProblems {
    /**
     * Longest Substring Without Repeating Characters -- LeetCode
     * <p/>
     * https://oj.leetcode.com/problems/longest-substring-without-repeating-characters/
     * Given a string, find the length of the longest substring without repeating characters. For example, the longest substring without repeating letters for "abcabcbb" is "abc", which the length is 3. For "bbbbb" the longest substring is "b", with the length of 1.
     */
    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0)
            return 0;
        HashSet<Character> set = new HashSet<Character>();
        int max = 0;
        int walker = 0;
        int runner = 0;
        while (runner < s.length()) {
            if (set.contains(s.charAt(runner))) {
                //max is walker to runner - 1
                if (max < runner - walker) {
                    max = runner - walker;
                }
                while (s.charAt(walker) != s.charAt(runner)) {
                    set.remove(s.charAt(walker));
                    walker++;
                }
                walker++;
            } else {
                set.add(s.charAt(runner));
            }
            runner++;
        }
        //need to handle runner reaches the end
        max = Math.max(max, runner - walker);
        return max;
    }


    /**
     * Longest Common Prefix -- LeetCode
     * https://oj.leetcode.com/problems/longest-common-prefix/
     * Write a function to find the longest common prefix string amongst an array of strings.
     */
    public String longestCommonPrefix(String[] strs) {
        StringBuilder res = new StringBuilder();
        if (strs == null || strs.length == 0)
            return res.toString();
        boolean sameFlag = true;
        int idx = 0;
        while (sameFlag) {
            for (int i = 0; i < strs.length; i++) {
                if (strs[i].length() <= idx || strs[i].charAt(idx) != strs[0].charAt(idx)) {
                    sameFlag = false;
                    break;
                }
            }
            if (sameFlag)
                res.append(strs[0].charAt(idx));
            idx++;
        }
        return res.toString();
    }

    /**
     * Anagrams -- LeetCode
     * https://oj.leetcode.com/problems/anagrams/
     * Given an array of strings, return all groups of strings that are anagrams.
     * Note: All inputs will be in lower-case.
     */
    public ArrayList<String> anagrams(String[] strs) {
        ArrayList<String> res = new ArrayList<String>();
        if (strs == null || strs.length == 0)
            return res;
        HashMap<String, ArrayList<String>> map = new HashMap<String, ArrayList<String>>();
        for (int i = 0; i < strs.length; i++) {
            char[] charArr = strs[i].toCharArray();
            Arrays.sort(charArr);
            String str = new String(charArr);
            if (map.containsKey(str)) {
                map.get(str).add(strs[i]);
            } else {
                ArrayList<String> list = new ArrayList<String>();
                list.add(strs[i]);
                map.put(str, list);
            }
        }
        Iterator iter = map.values().iterator();
        while (iter.hasNext()) {
            ArrayList<String> item = (ArrayList<String>) iter.next();
            if (item.size() > 1)
                res.addAll(item);
        }
        return res;
    }

    /**
     * Length of Last Word -- LeetCode
     * https://oj.leetcode.com/problems/length-of-last-word/
     * Given a string s consists of upper/lower-case alphabets and empty space characters ' ', return the length of last word in the string.
     * If the last word does not exist, return 0.
     * Note: A word is defined as a character sequence consists of non-space characters only.
     * For example,
     * Given s = "Hello World",
     * return 5.
     */
    public int lengthOfLastWord(String s) {
        if (s == null || s.length() == 0)
            return 0;
        int idx = s.length() - 1;
        while (idx >= 0 && s.charAt(idx) == ' ')
            idx--;
        int idx2 = idx;
        while (idx2 >= 0 && s.charAt(idx2) != ' ')
            idx2--;
        return idx - idx2;
    }

}



