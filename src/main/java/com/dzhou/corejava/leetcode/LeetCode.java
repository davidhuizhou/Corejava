package com.dzhou.corejava.leetcode;

import com.dzhou.util.StringUtils;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by huizhou on 7/15/14.
 */
public class LeetCode {

    /**
     *  http://www.programcreek.com/2012/12/leetcode-solution-of-two-sum-in-java/
     */
    public static int[] twoSum(int[] numbers, int sum){
        int[] ret = {-1, -1};

        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        for(int i = 0; i < numbers.length; i++){
            if(map.containsKey(sum - numbers[i])){
                ret[0] = map.get(sum - numbers[i]) + 1;
                ret[1] = i + 1;
                return ret;
            } else {
                map.put(numbers[i], i);
            }
        }
        return ret;
    }

    /**
     *   http://www.programcreek.com/2012/12/leetcode-reverse-integer/
     **/
    public static int reverseInt(int x){
        int sign = 1;
        if(x < 0){
            sign = -1;
            x = -x;
        }

        int rev = 0;
        while(x > 0){
            rev = 10 * rev + x % 10;
            x /= 10;
        }
        return sign * rev;

    }

    /**
     *   http://www.programcreek.com/2012/12/leetcode-string-to-integer-atoi/
     **/
    public static int atoi(String s){
        if(s == null || s.trim().length() == 0)
            return 0;

        s = s.trim();

        int sign = 1;
        int i = 0;


        if(s.charAt(0) == '+'){
            i++;
        }
        if(s.charAt(0) == '-') {
            sign = -1;
            i++;
        }

        double result = 0.0;

        while(i < s.length() && s.charAt(i) >= '0' && s.charAt(i) <= '9'){
            result = 10.0 * result + (s.charAt(i) - '0');
            i++;
        }

        result = sign * result;

        if(result >= Integer.MAX_VALUE)
            return Integer.MAX_VALUE;

        if(result <= Integer.MIN_VALUE)
            return Integer.MIN_VALUE;

        return (int)result;

    }

    /**
     * http://www.programcreek.com/2013/02/leetcode-longest-substring-without-repeating-characters-java/
     * @param s
     */
    public static String longestNoneRepeatingSubString(String s) {
        if (s == null || s.length() <= 1)
            return s;

        HashMap<Character, Integer> map = new HashMap<Character, Integer>();
        String longest = "";
        int i = 0;

        for (int j = i; j < s.length(); j++) {
            char c = s.charAt(j);
            if (!map.containsKey(c)) {
                map.put(c, j);
            } else {
                if (j - i > longest.length())
                    longest = s.substring(i, j);

                i = map.get(c) + 1;
                j = map.get(c);
                map.clear();

            }
        }

        if(i < s.length() && s.substring(i).length() > longest.length())
            longest = s.substring(i);

        return longest;
    }

    public static int lengthOfLongestSubstring(String s) {

        char[] arr = s.toCharArray();
        int pre = 0;

        HashMap<Character, Integer> map = new HashMap<Character, Integer>();

        for (int i = 0; i < arr.length; i++) {
            if (!map.containsKey(arr[i])) {
                map.put(arr[i], i);
            } else {
                pre = pre > map.size() ? pre : map.size();
                i = map.get(arr[i]);
                map.clear();
            }
        }

        return Math.max(pre, map.size());
    }

    /**
     *
     * http://www.programcreek.com/2013/02/leetcode-longest-substring-without-repeating-characters-java/
     * @param s
     * @return
     */

    public static String longestSubstring(String s) {
        String longest = "";
        HashMap<Character, Integer> map = new HashMap<Character, Integer>();

        for (int i = 0; i < s.length(); i++) {
            if (!map.containsKey(s.charAt(i))) {
                map.put(s.charAt(i), i);
            } else {
                longest = longest.length() >= map.size() ? longest : s.substring(i - map.size(), i);
                i = map.get(s.charAt(i));
                map.clear();
            }
        }

        if(map.size() > longest.length())
            longest = s.substring(s.length() - map.size());

        return longest;
    }

    /**
     * http://www.programcreek.com/2013/12/leetcode-solution-of-longest-palindromic-substring-java/
     * @param
     */
    private static int expend(boolean[][] b, char[] a, int i, int j){
        if(j < i || (i < 0 || i >= a.length) || (j < 0 || j >= a.length) || a[i] != a[j])
            return -1;

        while(i >= 0 && j <= a.length - 1 && a[i] == a[j]){
            b[i][j] = true;
            i--;
            j++;
        }
        return j - i - 1;
    }

    public static Set<String> longestPalindrom(String s){
        Set<String> results = new HashSet<String>();
        if(s.length() <= 1){
            results.add(s);
            return results;
        }

        int longest = 1;
        char[] a = s.toCharArray();
        int len = a.length;
        boolean[][] b = new boolean[len][len];

        for(int i = 0; i < len - 1; i++)
            b[i][i] = true;

        for(int i = 0; i < len - 2; i++){
            int l = expend(b, a, i, i);
            if(l > longest)
                longest = l;

            l = expend(b, a, i, i + 1);
            if(l > longest)
                longest = l;

        }

        for(int i = 0; i < len -1; i++){
            if(i + longest <= len && b[i][i + longest - 1])
                results.add(s.substring(i, i + longest));
        }
        return results;

    }

    public static void main(String[] args){
        System.out.println("Test twoSum");
        int[] numbers = {1, 3, 5, 5, 7, 8, 9, 8, 4, 5, 3, 2, 1, 11};
        int[] e = twoSum(numbers, 20);
        System.out.println(StringUtils.arrayToString(e, 0, e.length - 1));

        System.out.println("Test reverseInt");
        System.out.println("Reverse 0 - " + reverseInt(0));
        System.out.println("Reverse 1 - " + reverseInt(1));
        System.out.println("Reverse 123456 - " + reverseInt(123456));
        System.out.println("Reverse -123456 - " + reverseInt(-123456));

        System.out.println("Test atoi");
        System.out.println("atoi()=" + atoi(""));
        System.out.println("atoi(  )=" + atoi(" "));
        System.out.println("atoi(12345)=" + atoi("12345"));
        System.out.println("atoi(+12345)=" + atoi("+12345"));
        System.out.println("atoi(-12345)=" + atoi("-12345"));

        System.out.println(longestNoneRepeatingSubString("a"));
        System.out.println(longestSubstring("a"));
        System.out.println(longestNoneRepeatingSubString("aa"));
        System.out.println(longestSubstring("aa"));
        System.out.println(longestNoneRepeatingSubString("ab"));
        System.out.println(longestSubstring("ab"));
        System.out.println(longestNoneRepeatingSubString("abc"));
        System.out.println(longestSubstring("abc"));
        System.out.println(longestNoneRepeatingSubString("aba"));
        System.out.println(longestSubstring("aba"));
        System.out.println(longestNoneRepeatingSubString("abca"));
        System.out.println(longestSubstring("abca"));
        System.out.println(longestNoneRepeatingSubString("abab"));
        System.out.println(longestSubstring("abab"));
        System.out.println(longestNoneRepeatingSubString("abcdae"));
        System.out.println(longestSubstring("abcdae"));

        System.out.println("Test expend");
        String s = "a";
        boolean[][] b = new boolean[s.length()][s.length()];
        System.out.println(expend(b, s.toCharArray(), 0, 0));

        b = new boolean[s.length()][s.length()];
        s = "a";
        System.out.println(expend(b, s.toCharArray(), 0, 1));

        s = "ab";
        b = new boolean[s.length()][s.length()];
        System.out.println(expend(b, s.toCharArray(), 0, 1));

        s = "aa";
        b = new boolean[s.length()][s.length()];
        System.out.println(expend(b, s.toCharArray(), 0, 1));

        s = "aba";
        b = new boolean[s.length()][s.length()];
        System.out.println(expend(b, s.toCharArray(), 1, 1));


        s = "abcbd";
        b = new boolean[s.length()][s.length()];
        System.out.println(expend(b, s.toCharArray(), 2, 2));

        s = "abccbdadb";
        b = new boolean[s.length()][s.length()];
        System.out.println(expend(b, s.toCharArray(), 2, 3));

        System.out.println("Test longestPalindroms");
        Set<String> results = longestPalindrom(s);
        StringUtils.printSet(results);

    }
}
