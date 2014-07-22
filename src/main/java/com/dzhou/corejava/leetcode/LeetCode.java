package com.dzhou.corejava.leetcode;

import com.dzhou.corejava.crackingthecode.LinkedListNode;
import com.dzhou.util.StringUtils;

import java.util.*;

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

    public static Set<String> longestSubstring(String s) {
        Set<String> set = new HashSet<String>();
        if(s.isEmpty())
            return set;
        if(s.length() == 1){
            set.add(s);
            return set;
        }

        int longest = 0;
        HashMap<Character, Integer> map = new HashMap<Character, Integer>();

        int begin = 0;

        for (int i = 0; i < s.length(); i++) {
            if (!map.containsKey(s.charAt(i))) {
                map.put(s.charAt(i), i);
            } else {
                if (map.size() == longest) {
                    set.add(s.substring(begin, i));
                } else if (map.size() > longest) {
                    set.clear();
                    set.add(s.substring(begin, i));
                    longest = map.size();

                }
                i = map.get(s.charAt(i));
                begin = i + 1;
                map.clear();

            }
        }

        if (map.size() == longest)
            set.add(s.substring(begin));
        else if (map.size() > longest) {
            set.clear();
            set.add(s.substring(begin));
        }


        return set;
    }

    /**
     * http://www.programcreek.com/2013/12/leetcode-solution-of-longest-palindromic-substring-java/
     * @param
     */
    private static String expend(String s, int begin, int end){
        if(end < begin || (begin < 0 || begin >= s.length()) || (end < 0 || end >= s.length()) || s.charAt(begin) != s.charAt(end))
            return "";

        while(begin >= 0 && end < s.length() && s.charAt(begin) == s.charAt(end)){
            begin--;
            end++;
        }
        return s.substring(begin + 1, end);
    }

    public static Set<String> longestPalindromSubStrings(String s){
        Set<String> results = new HashSet<String>();

        if(s.isEmpty())
            return null;

        if(s.length() == 1){
            results.add(s);
            return results;
        }

        int longest = 1;

        for(int i = 0; i < s.length(); i++){
            String l = expend(s, i, i);
            if(l.length() == longest){
                results.add(l);
            }else if(l.length() > longest){
                results.clear();
                results.add(l);
                longest = l.length();
            }

            l = expend(s, i, i + 1);
            if(l.length() == longest){
                results.add(l);
            }else if(l.length() > longest){
                results.clear();
                results.add(l);
                longest = l.length();
            }

        }
        return results;

    }

    public static double findMedianOfTwoSortedArray(int[] a, int[] b){
        int aLen = a.length;
        int bLen = b.length;

        if((aLen + bLen) % 2 == 0){
            return (kThElement(a, b, (aLen + bLen)/2 -1, 0, aLen - 1, 0, bLen - 1) + kThElement(a, b, (aLen + bLen)/2, 0, aLen -1, 0, bLen - 1)) * 0.5;

        } else{
            return kThElement(a, b, (aLen + bLen)/2, 0, aLen - 1, 0, bLen -1);
        }

    }

    public static int kThElement(int[] a, int[] b, int k, int aStart, int aEnd, int bStart, int bEnd){
        int aLen = aEnd - aStart + 1;
        int bLen = bEnd - bStart + 1;

        if(aLen <= 0 || a.length <= 0)
            return b[bStart + k];

        if(bLen <= 0 || b.length <= 0)
            return a[aStart + k];

        if(k == 0)
            return Math.min(a[aStart + k], b[bStart + k]);

        int aMid = k * aLen / (aLen + bLen);
        int bMid = k - aMid - 1;
        aMid = aStart + aMid;
        bMid = bStart + bMid;

        if(a[aMid] > b[bMid]){
            k = k - (bMid - bStart + 1);
            aEnd = aMid;
            bStart = bMid + 1;
        } else if (a[aMid] < b[bMid]){
            k = k - (aMid - aStart + 1);
            bEnd = bMid;
            aStart = aMid + 1;
        } else {
            return a[aMid];
        }
        return kThElement(a, b, k, aStart, aEnd, bStart, bEnd);
    }

    /**
     *
     * @param x - http://www.programcreek.com/2013/02/leetcode-palindrome-number-java/
     */
    public static boolean isPalindrome(int x) {
        if (x < 0)
            return false;

        int div = 1;
        while (x / div >= 10) {
            div *= 10;
        }

        while (x != 0) {
            int right = x % 10;
            int left = x / div;

            if (right != left)
                return false;

            x = (x % div) / 10;
            div /= 100;

        }
        return true;

    }



    /**
     *
     * http://www.programcreek.com/2012/12/leetcode-regular-expression-matching-in-java/
     */
    //Get the starting string with the same characters of the s.charAt(0)
    public static boolean isMatch(String s, String p) {
        if (p.length() == 0)
            return s.length() == 0;

        if (p.length() == 1 || p.charAt(1) != '*') {
            if (s.length() < 1 || (p.charAt(0) != '.' && p.charAt(0) != s.charAt(0)))
                return false;
            return isMatch(s.substring(1), p.substring(1));
        } else {
            int i = -1;
            while (i < s.length() && (i < 0 || p.charAt(0) == '.' || p.charAt(0) == s.charAt(i))) {
                if (isMatch(s.substring(i + 1), p.substring(2)))
                    return true;
                i++;
            }
            return false;

        }
    }

    /**
     *
     * https://oj.leetcode.com/problems/longest-common-prefix/
     */

    /**
     *
     * http://www.programcreek.com/2012/12/leetcode-3sum/
     */
    private class Result {
        int a;
        int b;
        int c;

        Result(int a, int b, int c) {
            this.a = a;
            this.b = b;
            this.c = c;
        }

        public boolean equals(Result r) {
            return a == r.a && b == r.b && c == r.c;
        }

        public String toString() {
            return "(" + a + "," + b + "," + c + ")";
        }
    }

    public Set<Result> threeSum(int[] a) {
        Set<Result> results = new HashSet<Result>();
        Arrays.sort(a);

        for (int i = 0; i <= a.length - 2; i++) {
            int k = i + 1;
            int l = a.length - 1;

            while (k < l) {
                int sum = a[i] + a[k] + a[l];
                if (sum == 0) {
                    results.add(new Result(a[i], a[k], a[l]));
                    k++;
                    l--;
                } else if (sum < 0) {
                    k++;
                } else {
                    l--;
                }
            }

        }
        return results;
    }

    /**
     * http://www.programcreek.com/2013/02/leetcode-3sum-closest-java/
     */
    public int threeSumCloest(int[] a, int target) {
        Arrays.sort(a);
        int min = Integer.MAX_VALUE;
        int result = 0;

        for (int i = 0; i <= a.length - 3; i++) {
            int j = i + 1;
            int k = a.length - 1;

            while (j < k) {
                int sum = a[i] + a[j] + a[k];
                int diff = Math.abs(sum - target);

                if (diff < min) {
                    result = sum;
                }

                if (sum == target) {
                    return sum;
                } else if (sum < target) {
                    j++;
                } else {
                    k--;
                }


            }


        }
        return result;
    }

    /**
     *
     * leetcode-merge-two-sorted-lists-java/
     */
    public static LinkedListNode<Integer> mergetList(LinkedListNode<Integer> l1, LinkedListNode<Integer> l2) {
        LinkedListNode<Integer> list = new LinkedListNode<Integer>(0);
        LinkedListNode<Integer> p = list;

        while (l1 != null && l2 != null) {

            if (l1.e <= l2.e) {
                p.next = l1;
                l1 = l1.next;

            } else {
                p.next = l2;
                l2 = l2.next;

            }
            p = p.next;
            p.next = null;
        }

        if (l1 == null)
            p.next = l2;

        if (l2 == null)
            p.next = l1;

        return list.next;
    }


    /**
     *
     * http://www.programcreek.com/2014/01/leetcode-generate-parentheses-java/
     */
    public static Set<String> generateParentheses(int n) {
        Set<String> set = new HashSet<String>();

        if (n == 0)
            return set;
        if (n == 1) {
            set.add("()");
            return set;
        }

        set = generateParentheses(n - 1);
        Set<String> set1 = set;
        set = new HashSet<String>();

        for (String s : set1) {
            for (int i = 0; i < 2 * (n - 1); i++) {
                StringBuilder sb = new StringBuilder();
                sb.append(s.substring(0, i)).append("(").append(s.substring(i));
                String s1 = sb.toString();
                for (int j = i + 1; j < 2 * n - 1; j++) {
                    sb = new StringBuilder();
                    sb.append(s1.substring(0, j)).append(")").append(s1.substring(j));
                    set.add(sb.toString());
                }

            }
        }
        return set;


    }

    public static ArrayList<String> generateParenthesis(int n) {
        ArrayList<String> res = new ArrayList<String>();
        if(n==0) {
            res.add("");
            return res;
        }

        helper(n,0,0,res,"");
        return res;
    }

    public static void helper(int n, int left, int right, ArrayList<String> res, String temp){
        // exit: all ( appeared
        if(left == n){
            for (int i=0; i<n-right; i++)
                temp = temp + ")";
            res.add(temp);
            return;
        }

        // case1: number of ( > number of )
        if(left>right){
            helper(n, left+1, right, res, temp + "(");
            helper(n, left, right+1, res, temp + ")");
        }

        // case2: number of ( == number of )
        else helper(n, left+1, right, res, temp + "(");
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

        System.out.println(lengthOfLongestSubstring("a"));
        StringUtils.printSet(longestSubstring("a"));
        System.out.println(lengthOfLongestSubstring("aa"));
        StringUtils.printSet(longestSubstring("aa"));
        System.out.println(lengthOfLongestSubstring("ab"));
        StringUtils.printSet(longestSubstring("ab"));
        System.out.println(lengthOfLongestSubstring("abc"));
        StringUtils.printSet(longestSubstring("abc"));
        System.out.println(lengthOfLongestSubstring("aba"));
        StringUtils.printSet(longestSubstring("aba"));
        System.out.println(lengthOfLongestSubstring("abca"));
        StringUtils.printSet(longestSubstring("abca"));
        System.out.println(lengthOfLongestSubstring("abab"));
        StringUtils.printSet(longestSubstring("abab"));
        System.out.println(lengthOfLongestSubstring("abcdae"));
        StringUtils.printSet(longestSubstring("abcdae"));

        System.out.println("Test expend");
        String s = "a";
        System.out.println(expend(s, 0, 0));

        s = "a";
        System.out.println(expend(s, 0, 1));

        s = "ab";
        System.out.println(expend(s, 0, 1));

        s = "aa";
        System.out.println(expend(s, 0, 1));

        s = "aba";
        System.out.println(expend(s, 1, 1));


        s = "abcbd";
        System.out.println(expend(s, 2, 1));

        s = "abccbdadb";
        System.out.println(expend(s, 2, 3));

        System.out.println("Test longestPalindroms");
        Set<String> results = longestPalindromSubStrings(s);
        StringUtils.printSet(results);

        s = "abccbddbb";
        System.out.println(expend(s, 2, 3));

        System.out.println("Test longestPalindroms");
        results = longestPalindromSubStrings(s);
        StringUtils.printSet(results);

        int[] a = {};
        int[] b = {1, 2, 3, 4, 5};
        System.out.println(kThElement(a, b, 0, 0, 1, 0, b.length - 1));
        System.out.println(kThElement(a, b, 1, 0, a.length - 1, 0, b.length - 1));
        System.out.println(kThElement(a, b, 2, 0, a.length - 1, 0, b.length - 1));
        System.out.println(kThElement(a, b, 3, 0, a.length - 1, 0, b.length - 1));
        System.out.println(kThElement(a, b, 4, 0, a.length - 1, 0, b.length - 1));

        a = new int[]{1, 2, 4, 7, 9, 10, 13, 17};
        b = new int[] {1, 2, 3, 8, 8, 11, 12};
        System.out.println(kThElement(a, b, 0, 0, a.length - 1, 0, b.length - 1));
        System.out.println(kThElement(a, b, 3, 0, a.length - 1, 0, b.length - 1));
        System.out.println(kThElement(a, b, 0, 0, a.length - 1, 0, b.length - 1));
        System.out.println(kThElement(a, b, 1, 0, a.length - 1, 0, b.length - 1));
        System.out.println(kThElement(a, b, 2, 0, a.length - 1, 0, b.length - 1));
        System.out.println(kThElement(a, b, 3, 0, a.length - 1, 0, b.length - 1));
        System.out.println(kThElement(a, b, 4, 0, a.length - 1, 0, b.length - 1));
        System.out.println(kThElement(a, b, 5, 0, a.length - 1, 0, b.length - 1));
        System.out.println(kThElement(a, b, 6, 0, a.length - 1, 0, b.length - 1));
        System.out.println(kThElement(a, b, 7, 0, a.length - 1, 0, b.length - 1));
        System.out.println(kThElement(a, b, 8, 0, a.length - 1, 0, b.length - 1));
        System.out.println(kThElement(a, b, 9, 0, a.length - 1, 0, b.length - 1));
        System.out.println(kThElement(a, b, 10, 0, a.length - 1, 0, b.length - 1));
        System.out.println(kThElement(a, b, 11, 0, a.length - 1, 0, b.length - 1));
        System.out.println(kThElement(a, b, 12, 0, a.length - 1, 0, b.length - 1));
        System.out.println(kThElement(a, b, 13, 0, a.length - 1, 0, b.length - 1));
        System.out.println(kThElement(a, b, 14, 0, a.length - 1, 0, b.length - 1));

        System.out.println("Test findMedianOfTwoSortedArray");
        System.out.println(findMedianOfTwoSortedArray(a, b));

        a = new int[]{1, 2, 3, 4, 5, 6};
        b = new int[] {7, 8, 9, 10, 11, 12, 13, 14, 15, 16};
        System.out.println(kThElement(a, b, 0, 0, a.length - 1, 0, b.length - 1));
        System.out.println(kThElement(a, b, 3, 0, a.length - 1, 0, b.length - 1));
        System.out.println(kThElement(a, b, 0, 0, a.length - 1, 0, b.length - 1));
        System.out.println(kThElement(a, b, 1, 0, a.length - 1, 0, b.length - 1));
        System.out.println(kThElement(a, b, 2, 0, a.length - 1, 0, b.length - 1));
        System.out.println(kThElement(a, b, 3, 0, a.length - 1, 0, b.length - 1));
        System.out.println(kThElement(a, b, 4, 0, a.length - 1, 0, b.length - 1));
        System.out.println(kThElement(a, b, 5, 0, a.length - 1, 0, b.length - 1));
        System.out.println(kThElement(a, b, 6, 0, a.length - 1, 0, b.length - 1));
        System.out.println(kThElement(a, b, 7, 0, a.length - 1, 0, b.length - 1));
        System.out.println(kThElement(a, b, 8, 0, a.length - 1, 0, b.length - 1));
        System.out.println(kThElement(a, b, 9, 0, a.length - 1, 0, b.length - 1));
        System.out.println(kThElement(a, b, 10, 0, a.length - 1, 0, b.length - 1));
        System.out.println(kThElement(a, b, 11, 0, a.length - 1, 0, b.length - 1));
        System.out.println(kThElement(a, b, 12, 0, a.length - 1, 0, b.length - 1));
        System.out.println(kThElement(a, b, 13, 0, a.length - 1, 0, b.length - 1));
        System.out.println(kThElement(a, b, 14, 0, a.length - 1, 0, b.length - 1));

        System.out.println("Test findMedianOfTwoSortedArray");
        System.out.println(findMedianOfTwoSortedArray(a, b));

        System.out.println("Test isParlindrome");
        System.out.println(isPalindrome(0));
        System.out.println(isPalindrome(1));
        System.out.println(isPalindrome(10));
        System.out.println(isPalindrome(11));
        System.out.println(isPalindrome(123));
        System.out.println(isPalindrome(121));
        System.out.println(isPalindrome(123454321));

        s = "";

        System.out.println("Test isMatch");


        System.out.println(isMatch("", "abc"));
        System.out.println(isMatch("a", ""));
        System.out.println(isMatch("a", "aac"));
        System.out.println(isMatch("a", "aba"));
        System.out.println(isMatch("ab", "abc"));
        System.out.println(isMatch("abc", "abc"));
        System.out.println(isMatch("abc", "abd"));
        System.out.println(isMatch("a", "bac"));
        System.out.println(isMatch("abc", "defabc"));
        System.out.println(isMatch("abcdefg", "abcdef"));
        System.out.println(isMatch("abcdefg", "abcdefg"));
        System.out.println(isMatch("abcdefg", "abcdefghijk"));

        System.out.println(isMatch("a", "*"));
        System.out.println(isMatch("a", ".c"));
        System.out.println(isMatch("ab", "a."));
        System.out.println(isMatch("ab", ".bc"));
        System.out.println(isMatch("abc", "ab."));
        System.out.println(isMatch("aac", ".*ceee"));
        System.out.println(isMatch("aaaabcd", "a*b.d"));
        System.out.println(isMatch("abcdefg", "abcdefg"));
        System.out.println(isMatch("abcdefg", "abcdefghijk"));


        System.out.println("Test match expression");
        System.out.println(isMatch("aa", "a"));
        System.out.println(isMatch("aa", "aa"));
        System.out.println(isMatch("aaa", "aa"));
        System.out.println(isMatch("aa", "a*"));
        System.out.println(isMatch("aa", ".*"));
        System.out.println(isMatch("ab", ".*"));
        System.out.println(isMatch("aab", "c*a*b"));


        a = new int[]{1, 3, 5, 6, 9, 13};
        b = new int[] {1, 2, 4, 5, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16};

        LinkedListNode<Integer> l1 = new LinkedListNode(1);
        l1.addLast(3);
        l1.addLast(5);
        l1.addLast(6);
        l1.addLast(9);
        l1.addLast(13);
        l1.addLast(20);


        LinkedListNode<Integer> l2 = new LinkedListNode(1);
        l2.addLast(2);
        l2.addLast(3);
        l2.addLast(5);
        l2.addLast(7);
        l2.addLast(8);
        l2.addLast(9);
        l2.addLast(10);
        l2.addLast(11);
        l2.addLast(12);
        l2.addLast(13);
        l2.addLast(14);
        l2.addLast(15);

        System.out.println("Test mergeLit");
        System.out.println(l1.toString());
        System.out.println(l2.toString());
        LinkedListNode<Integer> l3 = mergetList(l1, l2);
        System.out.println(l3.toString());

        System.out.println("Test generateParentheses");
        Set<String> set = generateParentheses(1);
        StringUtils.printSet(set);

        set = generateParentheses(2);
        StringUtils.printSet(set);

        set = generateParentheses(3);
        StringUtils.printSet(set);


        ArrayList<String> parenthesis = generateParenthesis(3);
        for(String p : parenthesis){
            System.out.print(p + "|");
        }



    }
}
