package com.dzhou.corejava.leetcode;

import com.dzhou.corejava.crackingthecode.LinkedListNode;
import com.dzhou.util.StringUtils;

import java.util.*;

/**
 * Created by huizhou on 7/15/14.
 */
public class LeetCode {

    /**
     * http://www.programcreek.com/2012/12/leetcode-solution-of-two-sum-in-java/
     * https://oj.leetcode.com/submissions/detail/9009004/
     */

    public static int[] twoSum(int[] numbers, int target) {
        int[] result = {-1, -1};
        if (numbers == null)
            return result;
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i < numbers.length; i++) {
            if (map.containsKey(numbers[i])) {
                result[0] = map.get(numbers[i]) + 1;
                result[1] = i + 1;
                break;
            } else {
                map.put(target - numbers[i], i);
            }
        }
        return result;

    }

    /**
     * https://oj.leetcode.com/problems/median-of-two-sorted-arrays/
     * https://oj.leetcode.com/submissions/detail/9009719/
     */
    public static double findMedianSortedArrays(int A[], int B[]) {
        int aLen = A.length;
        int bLen = B.length;
        int len = aLen + bLen;
        if (len % 2 == 0)
            return (kthElement(A, B, len / 2 - 1, 0, aLen - 1, 0, bLen - 1)
                    + kthElement(A, B, len / 2, 0, aLen - 1, 0, bLen - 1)) * 0.5;
        else
            return kthElement(A, B, len / 2, 0, aLen - 1, 0, bLen - 1);
    }

    private static int kthElement(int[] A, int[] B, int k, int aStart, int aEnd, int bStart, int bEnd) {
        int aLen = aEnd - aStart + 1;
        int bLen = bEnd - bStart + 1;

        if (aLen <= 0 || A.length <= 0)
            return B[bStart + k];
        if (bLen <= 0 || B.length <= 0)
            return A[aStart + k];
        if (k == 0)
            return Math.min(A[aStart + k], B[bStart + k]);

        int aMid = k * aLen / (aLen + bLen);
        int bMid = k - aMid - 1;
        aMid = aStart + aMid;
        bMid = bStart + bMid;

        if (A[aMid] >= B[bMid]) {
            k = k - (bMid - bStart + 1);
            aEnd = aMid;
            bStart = bMid + 1;
        } else {
            k = k - (aMid - aStart + 1);
            bEnd = bMid;
            aStart = aMid + 1;
        }
        return kthElement(A, B, k, aStart, aEnd, bStart, bEnd);

    }

    /**
     * https://oj.leetcode.com/problems/longest-substring-without-repeating-characters/
     * https://oj.leetcode.com/submissions/detail/9010153/
     */
    public static int lengthOfLongestSubstring(String s) {
        if (s == null)
            return 0;
        if (s.length() <= 1)
            return s.length();
        int longest = 1;
        int i = 0;
        int j = 1;
        for (j = 1; j < s.length(); j++) {
            int index = s.substring(i, j).indexOf(s.charAt(j));
            if (index >= 0) {
                if (j - i > longest)
                    longest = j - i;
                i = i + index + 1;
            }
        }
        if (j - i > longest)
            longest = j - i;
        return longest;

    }

    /**
     * https://oj.leetcode.com/problems/add-two-numbers/
     * https://oj.leetcode.com/submissions/detail/9011366/
     */
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode sentinel = new ListNode(0);
        ListNode p = sentinel;
        int carry = 0;
        while (l1 != null || l2 != null) {
            if (l1 != null) {
                carry += l1.val;
                l1 = l1.next;
            }
            if (l2 != null) {
                carry += l2.val;
                l2 = l2.next;
            }
            ListNode n = new ListNode(carry % 10);
            p.next = n;
            p = n;
            carry /= 10;


        }
        if (carry == 1) {
            p.next = new ListNode(carry);
        }

        return sentinel.next;

    }

    /**
     * https://oj.leetcode.com/problems/longest-palindromic-substring/
     * https://oj.leetcode.com/submissions/detail/9012368/
     */
    public static String longestPalindrome(String s) {
        if (s == null)
            return null;
        if (s.length() == 1)
            return s;
        String longest = s.substring(0, 1);
        for (int i = 0; i <= s.length() - 2; i++) {
            String pStr = expend(s, i, i);
            String qStr = expend(s, i, i + 1);
            if (pStr.length() > longest.length())
                longest = pStr;
            if (qStr.length() > longest.length())
                longest = qStr;
        }
        return longest;

    }

    private static String expend(String s, int i, int j) {
        if (s == null || i < 0 || i > s.length() - 1 || j < 0 || j > s.length() - 1 || i > j || s.charAt(i) != s.charAt(j))
            return "";

        while (i >= 0 && j < s.length() && s.charAt(i) == s.charAt(j)) {
            i--;
            j++;
        }
        return s.substring(i + 1, j);
    }

    public static Set<String> longestPalindromSubStrings1(String s) {
        Set<String> results = new HashSet<String>();

        if (s.isEmpty())
            return null;

        if (s.length() == 1) {
            results.add(s);
            return results;
        }

        int longest = 1;

        for (int i = 0; i < s.length(); i++) {
            String l = expend(s, i, i);
            if (l.length() == longest) {
                results.add(l);
            } else if (l.length() > longest) {
                results.clear();
                results.add(l);
                longest = l.length();
            }

            l = expend(s, i, i + 1);
            if (l.length() == longest) {
                results.add(l);
            } else if (l.length() > longest) {
                results.clear();
                results.add(l);
                longest = l.length();
            }

        }
        return results;

    }

    /**
     * https://oj.leetcode.com/problems/reverse-integer/
     * https://oj.leetcode.com/submissions/detail/9012870/
     */
    public static int reverse(int x) {
        int sign = 1;
        if (x < 0) {
            sign = -1;
            x = x * (-1);
        }
        int result = 0;
        while (x > 0) {
            result = 10 * result + x % 10;
            x /= 10;
        }
        return sign * result;

    }


    /**
     * http://www.programcreek.com/2012/12/leetcode-string-to-integer-atoi/
     * https://oj.leetcode.com/submissions/detail/9014031/
     */
    public static int atoi(String str) {
        if (str == null || str.trim().length() == 0)
            return 0;

        str = str.trim();

        int sign = 1;
        int i = 0;


        if (str.charAt(0) == '+') {
            i++;
        }
        if (str.charAt(0) == '-') {
            sign = -1;
            i++;
        }

        double result = 0.0;

        while (i < str.length() && str.charAt(i) >= '0' && str.charAt(i) <= '9') {
            result = 10.0 * result + (str.charAt(i) - '0');
            i++;
        }

        result = sign * result;

        if (result >= Integer.MAX_VALUE)
            return Integer.MAX_VALUE;

        if (result <= Integer.MIN_VALUE)
            return Integer.MIN_VALUE;

        return (int) result;

    }

    /**
     * https://oj.leetcode.com/problems/palindrome-number/
     * https://oj.leetcode.com/submissions/detail/9014691/
     */
    public static boolean isPalindrome(int x) {
        if (x < 0)
            return false;
        if (x < 10)
            return true;
        int div = 10;
        while (x / div >= 10)
            div *= 10;
        while (div >= 10) {
            int left = x / div;
            int right = x % 10;
            if (left != right)
                return false;
            x = (x % div) / 10;
            div /= 100;
        }
        return true;

    }

    /**
     * https://oj.leetcode.com/problems/regular-expression-matching/
     * https://oj.leetcode.com/submissions/detail/9016055/
     */
    public static boolean isMatch(String s, String p) {
        if (s == null)
            return p == null;
        if (p == null)
            return s == null;

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
     * https://oj.leetcode.com/problems/longest-common-prefix/
     * https://oj.leetcode.com/submissions/detail/9113380/
     */
    public static String longestCommonPrefix(String[] strs) {
        if (strs.length == 0)
            return "";

        String s = strs[0];
        int lo = 0, hi = s.length() - 1;

        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (isCommonPrefix(s.substring(lo, hi + 1), strs, lo))
                break;
            else if (isCommonPrefix(s.substring(lo, mid + 1), strs, lo))
                lo = mid + 1;
            else
                hi = mid - 1;
        }
        if (hi >= 0)
            return s.substring(0, hi + 1);
        else
            return "";

    }

    private static boolean isCommonPrefix(String s, String[] strs, int start) {
        for (String str : strs) {
            if (str.substring(start).indexOf(s) != 0)
                return false;
        }
        return true;

    }

    /**
     * https://oj.leetcode.com/problems/3sum/
     * https://oj.leetcode.com/submissions/detail/9259432/
     */
    public static List<List<Integer>> threeSum(int[] num) {
        Set<List<Integer>> set = new HashSet<List<Integer>>();
        Arrays.sort(num);

        for (int i = 0; i <= num.length - 3; i++) {
            int j = i + 1, k = num.length - 1;
            while (j < k) {
                int sum = num[i] + num[j] + num[k];
                if (sum < 0) {
                    j++;
                } else if (sum > 0) {
                    k--;
                } else {
                    List<Integer> r = new ArrayList<Integer>();
                    r.add(num[i]);
                    r.add(num[j]);
                    r.add(num[k]);
                    set.add(r);
                    j++;
                    k--;

                }
            }
        }
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        for (List<Integer> s : set) {
            result.add(s);
        }
        return result;

    }

    /**
     * https://oj.leetcode.com/problems/3sum-closest/
     * https://oj.leetcode.com/submissions/detail/9260441/
     */
    public static int threeSumClosest(int[] num, int target) {
        Arrays.sort(num);
        int min = Integer.MAX_VALUE;
        int result = 0;

        for (int i = 0; i <= num.length - 3; i++) {
            int j = i + 1;
            int k = num.length - 1;

            while (j < k) {
                int sum = num[i] + num[j] + num[k];
                int diff = Math.abs(sum - target);

                if (diff < min) {
                    min = diff;
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
     * https://oj.leetcode.com/problems/swap-nodes-in-pairs/
     * https://oj.leetcode.com/submissions/detail/9273396/
     */
    public ListNode swapPairs(ListNode head) {
        if (head == null)
            return head;
        ListNode p = head;
        while (p != null) {
            ListNode q = p.next;

            if (q != null) {
                int temp = p.val;
                p.val = q.val;
                q.val = temp;
                p = q.next;

            } else {
                break;
            }


        }
        return head;


    }


    /**
     * https://oj.leetcode.com/problems/4sum/
     * https://oj.leetcode.com/problems/4sum/
     */
    public static List<List<Integer>> fourSum(int[] num, int target) {
        Set<List<Integer>> set = new HashSet<List<Integer>>();
        Arrays.sort(num);


        for (int i = 0; i <= num.length - 4; i++) {
            for (int j = i + 1; j <= num.length - 3; j++) {
                int m = j + 1;
                int n = num.length - 1;

                while (m < n) {
                    int sum = num[i] + num[j] + num[m] + num[n];

                    if (sum == target) {
                        List<Integer> l = new ArrayList<Integer>();
                        l.add(num[i]);
                        l.add(num[j]);
                        l.add(num[m]);
                        l.add(num[n]);
                        set.add(l);
                        m++;
                        n--;
                    } else if (sum < target) {
                        m++;
                    } else {
                        n--;
                    }
                }
            }
        }
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        for (List<Integer> s : set) {
            result.add(s);
        }
        return result;

    }

    /**
     * https://oj.leetcode.com/problems/remove-nth-node-from-end-of-list/
     * https://oj.leetcode.com/submissions/detail/9265681/
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null)
            return head;
        if (n <= 0)
            return head;

        ListNode p = head;
        ListNode q = head;

        int count = 0;
        for (int i = 0; i <= n - 1 && q != null; i++) {
            q = q.next;
            count++;
        }

        if (count < n)
            return head;

        else {
            if (q == null)
                return head.next;
            else {
                while (q.next != null) {
                    p = p.next;
                    q = q.next;
                }
                if (p.next != null)
                    p.next = p.next.next;
            }
        }
        return head;

    }

    /**
     * https://oj.leetcode.com/problems/valid-parentheses/
     * https://oj.leetcode.com/submissions/detail/9269784/
     */
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<Character>();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (c == '{' || c == '(' || c == '[')
                stack.push(c);
            else if (c == '}' && (stack.isEmpty() || stack.pop() != '{')) {
                return false;
            } else if (c == ')' && (stack.isEmpty() || stack.pop() != '(')) {
                return false;
            } else if (c == ']' && (stack.isEmpty() || stack.pop() != '[')) {
                return false;
            }

        }
        return stack.isEmpty();

    }

    /**
     * https://oj.leetcode.com/problems/generate-parentheses/
     * https://oj.leetcode.com/submissions/detail/9270187/
     */
    public static List<String> generateParenthesis(int n) {
        List<String> parenthesis = new ArrayList<String>();
        if (n == 0) {
            parenthesis.add("");
            return parenthesis;
        }

        generateParenthesisHelper(parenthesis, n, 0, 0, "");
        return parenthesis;

    }

    private static void generateParenthesisHelper(List<String> parenthesis, int n, int left, int right, String s) {
        if (left == n) {
            for (int i = 0; i < n - right; i++)
                s += ")";

            parenthesis.add(s);


        } else if (left > right) {
            generateParenthesisHelper(parenthesis, n, left + 1, right, s + "(");
            generateParenthesisHelper(parenthesis, n, left, right + 1, s + ")");
        } else if (left == right) {
            generateParenthesisHelper(parenthesis, n, left + 1, right, s + "(");
        }
    }

    /**
     * https://oj.leetcode.com/problems/merge-k-sorted-lists/
     * https://oj.leetcode.com/submissions/detail/9271178/
     */
    public ListNode mergeKLists(List<ListNode> lists) {
        if (lists == null)
            return null;

        PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
        for (ListNode node : lists) {
            while (node != null) {
                pq.add(node.val);
                node = node.next;
            }
        }


        ListNode res = new ListNode(0);
        ListNode tail = res;

        while (!pq.isEmpty()) {
            tail.next = new ListNode(pq.poll());
            tail = tail.next;
        }

        return res.next;

    }

    /**
     * https://oj.leetcode.com/problems/reverse-nodes-in-k-group/
     * https://oj.leetcode.com/submissions/detail/9355999/
     */
    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || k <= 1)
            return head;

        int count = 0;
        ListNode p = head;
        while (count < k && p != null) {
            count++;
            p = p.next;
        }

        if (count < k)
            return head;

        p = head;
        ListNode q = head;
        ListNode r = null;

        for (int i = 0; i < k; i++) {
            p = p.next;
            q.next = r;
            r = q;
            q = p;
        }
        head.next = reverseKGroup(p, k);
        return r;

    }

    /**
     * https://oj.leetcode.com/problems/remove-duplicates-from-sorted-array/
     * https://oj.leetcode.com/submissions/detail/9356997/
     */
    public static int removeDuplicates(int[] A) {
        if (A.length <= 1)
            return A.length;

        int k = 0;
        for (int i = 1; i < A.length; i++) {

            if (A[i] != A[k])
                A[++k] = A[i];
        }
        return k + 1;
    }

    /**
     * https://oj.leetcode.com/problems/remove-duplicates-from-sorted-array-ii/
     * https://oj.leetcode.com/submissions/detail/10762488/
     *
     */
    public static int removeDuplicates2(int[] A) {
        if (A.length <= 2)
            return A.length;

        int k = 1;
        for (int i = 2; i < A.length; i++) {

            if (!(A[i] == A[k] && A[i] == A[k-1]))
                A[++k] = A[i];
        }
        return k + 1;
    }


    /**
     * https://oj.leetcode.com/problems/remove-element/
     * https://oj.leetcode.com/submissions/detail/9357121/
     */
    public int removeElement(int[] A, int elem) {
        int i = 0, j = 0;
        while (j < A.length) {
            if (A[j] != elem) {
                A[i] = A[j];
                i++;
            }
            j++;

        }
        return i;

    }

    /**
     * https://oj.leetcode.com/problems/implement-strstr/
     * https://oj.leetcode.com/submissions/
     */
    public static String strStr(String haystack, String needle) {

        if (haystack == null || needle == null)
            return null;

        int haystackLen = haystack.length();
        int needleLen = needle.length();

        if (haystackLen < needleLen)
            return null;

        if (needleLen == 0)
            return haystack;

        for (int i = 0; i <= haystackLen - needleLen; i++) {
            int j = 0;
            while (j < needleLen && i + j < haystackLen
                    && needle.charAt(j) == haystack.charAt(i + j))
                j++;

            if (j == needleLen)
                return haystack.substring(i);

        }
        return null;

    }

    public static String strStr2(String haystack, String needle) {

        int needleLen = needle.length();
        int haystackLen = haystack.length();

        if (needleLen == haystackLen && needleLen == 0)
            return "";

        if (needleLen == 0)
            return haystack;

        for (int i = 0; i < haystackLen; i++) {
            // make sure in boundary of needle
            if (haystackLen - i + 1 < needleLen)
                return null;

            int k = i;
            int j = 0;

            while (j < needleLen && k < haystackLen && needle.charAt(j) == haystack.charAt(k)) {
                j++;
                k++;
                if (j == needleLen)
                    return haystack.substring(i);
            }

        }

        return null;
    }

    /**
     * https://oj.leetcode.com/problems/divide-two-integers/
     * https://oj.leetcode.com/submissions/detail/9400097/
     */
    public static int divide(int dividend, int divisor) {
        int result = 0;
        int sign = 1;

        long lDividend = dividend;
        long lDivisor = divisor;

        if (dividend == 0)
            return 0;

        if (divisor == 1)
            return dividend;

        if (dividend < 0) {
            sign = 0 - sign;
            lDividend = 0 - lDividend;
        }

        if (divisor < 0) {
            sign = 0 - sign;
            lDivisor = 0 - lDivisor;
        }

        while (lDividend >= lDivisor) {
            long mDivisor = lDivisor;

            int factor = 0;
            while (mDivisor <= lDividend && mDivisor > 0) {
                mDivisor = mDivisor << 1;
                factor++;
            }
            factor--;
            result += (1 << factor);
            lDividend -= (lDivisor << factor);

        }

        if (sign == -1)
            result = 0 - result;
        return result;

    }


    private static void permute(Set<String> result, String[] L, String p, int count, int total) {
        if (count == total)
            result.add(p);

        if (L == null)
            return;

        int l = L.length;
        if (l == 0)
            return;

        else {
            for (int i = 0; i < l; i++) {
                String w = L[i];
                String[] temp = new String[l - 1];
                for (int j = 0; j < i; j++)
                    temp[j] = L[j];

                for (int j = i; j < l - 1; j++)
                    temp[j] = L[j + 1];
                permute(result, temp, p + w, count + 1, total);

            }
        }

    }

    /**
     * https://oj.leetcode.com/problems/substring-with-concatenation-of-all-words/
     * https://oj.leetcode.com/submissions/detail/9464973/
     */
    public static List<Integer> findSubstring(String S, String[] L) {
        List<Integer> result = new ArrayList<Integer>();
        if (S == null || L == null) return result;

        int n = L.length;
        if (n == 0) return result;
        int m = L[0].length();
        int l = S.length();

        Map<String, Integer> countMap = new HashMap<String, Integer>();
        for (String w : L) {
            Integer count = countMap.get(w);
            if (count == null)
                countMap.put(w, 1);
            else
                countMap.put(w, count + 1);

        }

        for (int i = 0; i <= l - n * m; i++) {
            Map<String, Integer> temp = new HashMap<String, Integer>(countMap);
            boolean isSubStr = true;
            for (int j = 0; j < n; j++) {
                String w = S.substring(i + j * m, i + (j + 1) * m);
                Integer count = temp.get(w);
                if (count == null || count == 0) {
                    isSubStr = false;
                    break;
                } else {
                    count--;
                    temp.put(w, count);
                }
            }
            if (isSubStr)
                result.add(i);


        }


        return result;

    }

    /**
     * https://oj.leetcode.com/problems/next-permutation/
     * https://oj.leetcode.com/submissions/detail/9469583/
     */
    public static void nextPermutation(int[] num) {
        if (num == null) return;

        int len = num.length;
        if (len <= 1) return;

        for (int i = len - 2; i >= 0; i--) {
            int min = findMin(num, i, len - 1);
            if (i < min) {
                exch(num, i, min);
                sort(num, i + 1, len - 1);
                return;
            }
        }
        sort(num, 0, len - 1);
    }

    private static int findMin(int[] num, int start, int end) {

        if (start == end)
            return start;

        int min = start;
        int i = start;

        while (i <= end && num[i] <= num[start])
            i++;

        if (i <= end)
            min = i;

        for (i = min + 1; i <= end; i++) {
            if (num[i] <= num[min] && num[i] > num[start])
                min = i;
        }
        return min;

    }

    private static void exch(int[] a, int i, int j) {
        int swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }

    private static void sort(int[] num, int start, int end) {
        if (start == end) return;

        for (int i = start + 1; i <= end; i++) {
            for (int j = i; j > start && num[j] < num[j - 1]; j--) {
                exch(num, j - 1, j);
            }

        }

    }

    /**
     * https://oj.leetcode.com/problems/longest-valid-parentheses/
     * https://oj.leetcode.com/submissions/detail/9511539/
     */
    public static int longestValidParentheses1(String s) {
        int res = 0;
        if (s == null || s.length() == 0)
            return 0;

        int left = 0, right = 0;

        for (int i = 0; i <= s.length() - 1; i++) {
            if (s.charAt(i) == '(') {
                left++;
            } else if (s.charAt(i) == ')') {
                right++;
                if (right == left) {
                    if (2 * left > res) {
                        res = 2 * left;
                    }
                } else if (right > left) {
                    left = 0;
                    right = 0;
                }
            }
        }

        left = 0;
        right = 0;

        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == ')') {
                right++;
            } else if (s.charAt(i) == '(') {
                left++;
                if (left == right) {
                    if (2 * left > res) {
                        res = 2 * left;
                    }
                } else if (left > right) {
                    left = 0;
                    right = 0;
                }
            }
        }

        return res;

    }

    /**
     * http://www.darrensunny.me/leetcode-longest-valid-parentheses/
     *
     * @param s
     * @return
     */
    public static int longestValidParentheses(String s) {
        if (s == null || s.length() == 0)
            return 0;
        int longestLength = 0;      // Length of the longest valid parentheses
        int start = 0;  // The start index of the possibly longest valid parentheses
        Stack<Integer> stack = new Stack<Integer>();
        // One-pass scan
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {   // Opening parenthesis
                stack.push(i);          // Push its index
            } else {        // Closing parenthesis
                if (stack.empty()) {    // No opening parenthesis to match
                    start = i + 1;      // i+1 is the start of next possibly LVP
                } else {
                    stack.pop();    // The index of the opening parenthesis matched by s[i]
                    if (stack.empty())  // s[start...i] is matched
                        longestLength = Math.max(longestLength, i - start + 1);
                    else    // s[stack.peek()] is unmatched; s[stack.peek()+1...i] is matched
                        longestLength = Math.max(longestLength, i - stack.peek());
                }
            }
        }

        return longestLength;
    }

    /**
     * https://oj.leetcode.com/problems/search-in-rotated-sorted-array/
     * http://www.darrensunny.me/leetcode-search-in-rotated-sorted-array/
     * http://www.darrensunny.me/leetcode-search-in-rotated-sorted-array-ii/
     * https://oj.leetcode.com/submissions/detail/9551838/
     * https://oj.leetcode.com/submissions/detail/9552605/
     */
    public int search1(int[] A, int target) {
        if (A == null || A.length == 0) return -1;
        return searchHelper(A, target, 0, A.length - 1);

    }

    private int searchHelper(int[] A, int target, int start, int end) {
        if (start > end)
            return -1;

        int mid = start + (end - start) / 2;

        if (A[mid] == target)
            return mid;
        else if (target > A[mid]) {
            if (A[end] >= A[mid] && target > A[end])
                return searchHelper(A, target, start, mid - 1);
            else
                return searchHelper(A, target, mid + 1, end);


        } else {
            if (A[start] <= A[mid] && target < A[start])
                return searchHelper(A, target, mid + 1, end);
            else
                return searchHelper(A, target, start, mid - 1);


        }
    }


    public int search(int[] A, int target) {
        if (A == null || A.length == 0) return -1;
        int left = 0, right = A.length - 1;

        while (left <= right) {
            int mid = (left + right) / 2;
            if (A[mid] == target)
                return mid;
            else if (A[mid] > A[left]) {
                if (target >= A[left] && target < A[mid])
                    right = mid - 1;
                else
                    left = mid + 1;
            } else if (A[mid] < A[left]) {
                if (target > A[mid] && target <= A[right])
                    left = mid + 1;
                else
                    right = mid - 1;
            } else {
                left++;
            }

        }
        return -1;
    }

    /**
     * https://oj.leetcode.com/problems/search-in-rotated-sorted-array-ii/
     * https://oj.leetcode.com/submissions/detail/10902114/
     * @param A
     * @param target
     * @return
     */
    //Allow duplicated
    public boolean search2(int[] A, int target) {
        if (A == null || A.length == 0) return false;
        int left = 0, right = A.length - 1;

        while (left <= right) {
            int mid = (left + right) / 2;
            if (A[mid] == target)
                return true;
            else if (A[mid] > A[left]) {
                if (target >= A[left] && target < A[mid])
                    right = mid - 1;
                else
                    left = mid + 1;
            } else if (A[mid] < A[left]) {
                if (target > A[mid] && target <= A[right])
                    left = mid + 1;
                else
                    right = mid - 1;
            } else {
                left++;
            }

        }
        return false;
    }

    /**
     * https://oj.leetcode.com/problems/search-for-a-range/
     * http://www.darrensunny.me/leetcode-search-for-a-range/
     * https://oj.leetcode.com/submissions/detail/9556992/
     */
    public static int[] searchRange(int[] A, int target) {
        if (A == null || A.length == 0)
            return new int[]{-1, -1};

        int left = 0, right = A.length - 1;

        //Find left matching index
        while (left < right) {

            if (A[left] > target || A[right] < target)
                return new int[]{-1, -1};

            int mid = (left + right) / 2;

            if (A[mid] < target)
                left = mid + 1;
            else
                right = mid;


        }

        if (A[left] != target)
            return new int[]{-1, -1};

        int index1 = left;

        right = A.length - 1;
        while (left < right) {
            int mid = (left + right + 1) / 2;

            if (A[mid] > target)
                right = mid - 1;
            else
                left = mid;
        }
        return new int[]{index1, right};
    }


    /**
     * https://oj.leetcode.com/problems/search-insert-position/
     * http://www.programcreek.com/2013/01/leetcode-search-insert-position/
     * https://oj.leetcode.com/submissions/detail/9557823/
     */
    public int searchInsert(int[] A, int target) {
        if (A == null || A.length == 0) return 0;
        int left = 0, right = A.length - 1;
        while (left <= right) {
            if (target < A[left]) return left;
            if (target > A[right]) return right + 1;

            int mid = (left + right) / 2;
            if (A[mid] == target) return mid;
            else if (A[mid] < target) left = mid + 1;
            else right = mid - 1;
        }
        return left;
    }

    /**
     * https://oj.leetcode.com/problems/valid-sudoku/
     * https://oj.leetcode.com/submissions/detail/9559909/
     */
    public static boolean isValidSudoku(char[][] board) {
        for (int i = 0; i < 9; i++) {
            char[] row = board[i];
            if (!isValidSudoku(row))
                return false;
        }

        char[] column = new char[9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++)
                column[j] = board[j][i];

            if (!isValidSudoku(column))
                return false;


        }

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {

                char[] box = new char[9];
                for (int m = 0; m < 3; m++) {
                    int ii = i * 3 + m;
                    for (int n = 0; n < 3; n++) {
                        int jj = j * 3 + n;
                        box[m * 3 + n] = board[ii][jj];
                    }
                }
                if (!isValidSudoku(box))
                    return false;

            }
        }

        return true;

    }

    private static boolean isValidSudoku(char[] c) {
        Map<Character, Integer> map = new HashMap<Character, Integer>();
        for (int i = 0; i < c.length; i++) {
            if (c[i] == '.')
                continue;
            else {
                if (c[i] >= '1' && c[i] <= '9' && map.get(c[i]) == null)
                    map.put(c[i], 1);
                else
                    return false;
            }
        }
        return true;

    }

    /**
     * https://oj.leetcode.com/problems/sudoku-solver/
     * http://rleetcode.blogspot.com/2014/01/sudoku-solver-java.html
     * https://oj.leetcode.com/submissions/detail/9560489/
     */
    public void solveSudoku(char[][] board) {
        if (board == null || board.length == 0) {
            return;
        }

        solved(board);


    }

    private boolean solved(char[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == '.') {
                    for (char num = '1'; num <= '9'; num++) {

                        if (isValid(board, i, j, num)) {
                            // no conflict
                            board[i][j] = num;

                            if (solved(board)) {
                                return true;
                            } else {
                                board[i][j] = '.';
                            }

                        }

                    }
                    // if no proper number found, return false
                    return false;
                }
            }
        }

        return true;
    }

    private boolean isValid(char[][] board, int i, int j, char c) {

        // check column
        for (int row = 0; row < 9; row++) {
            if (board[row][j] == c) {
                return false;
            }


        }

        // check row
        for (int col = 0; col < 9; col++) {
            if (board[i][col] == c) {
                return false;
            }

        }

        // check block
        for (int row = i / 3 * 3; row < i / 3 * 3 + 3; row++) {
            for (int col = j / 3 * 3; col < j / 3 * 3 + 3; col++) {
                if (board[row][col] == c) {
                    return false;
                }

            }
        }

        return true;

    }

    /**
     * https://oj.leetcode.com/problems/count-and-say/
     * https://oj.leetcode.com/submissions/detail/9651625/
     */
    public String countAndSay(int n) {
        if (n <= 0) return null;
        if (n == 1) return "1";
        StringBuilder sb = new StringBuilder("1");
        for (int i = 2; i <= n; i++) {
            String s = sb.toString();
            sb = new StringBuilder();
            int count = 1, j = 1;
            for (j = 1; j < s.length(); j++) {
                if (s.charAt(j) == s.charAt(j - 1)) {
                    count++;

                } else {
                    sb.append(count).append(s.charAt(j - 1));
                    count = 1;
                }
            }
            sb.append(count).append(s.charAt(j - 1));
        }
        return sb.toString();

    }

    /**
     * https://oj.leetcode.com/problems/combination-sum/
     * http://www.darrensunny.me/leetcode-combination-sum/
     * https://oj.leetcode.com/submissions/detail/9655670/
     */
    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> r = new ArrayList<List<Integer>>();

        if (candidates == null || candidates.length == 0)
            return r;

        Arrays.sort(candidates);
        int N = 0;
        while (N <= candidates.length - 1 && candidates[N] <= target)
            N++;

        return combinationSum(candidates, N, target);

    }

    private static List<List<Integer>> combinationSum(int[] c, int N, int target) {
        List<List<Integer>> r = new ArrayList<List<Integer>>();

        if (N == 0) {

        } else if (N == 1) {
            if (target % c[0] == 0) {
                List<Integer> l = new ArrayList<Integer>();
                for (int n = 0; n < target / c[0]; n++)
                    l.add(c[0]);
                r.add(l);
            }
        } else {
            int d = c[N - 1];
            if (d <= target) {
                for (int n = 0; n <= target / d; n++) {
                    int newTarget = target - n * d;

                    if (newTarget > 0) {
                        List<List<Integer>> l = combinationSum(c, N - 1, newTarget);
                        for (List<Integer> b : l) {
                            for (int j = 0; j < n; j++)
                                b.add(d);
                            r.add(b);
                        }
                    } else {
                        List<Integer> l = new ArrayList<Integer>();
                        for (int m = 0; m < n; m++) {
                            l.add(d);
                        }
                        r.add(l);
                    }
                }


            } else {
                r = combinationSum(c, N - 1, target);

            }
        }
        return r;


    }

    /**
     * https://oj.leetcode.com/problems/combination-sum-ii/
     * https://oj.leetcode.com/submissions/detail/9701592/
     */
    public static List<List<Integer>> combinationSum2(int[] num, int target) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if (num == null || num.length == 0)
            return result;

        Arrays.sort(num);        // Sort the candidate in non-descending order
        ArrayList<Integer> current = new ArrayList<Integer>();
        recursiveAppend(num, target, 0, current, result);

        return result;

    }

    private static void recursiveAppend(int[] num, int target, int startIndex,
                                        List<Integer> current, List<List<Integer>> result) {
        if (target < 0)
            return;
        if (target == 0) {     // The current array is an solution
            result.add(new ArrayList<Integer>(current));
            return;
        }
        for (int i = startIndex; i < num.length; i++) {
            if (num[i] > target)    // No need to try the remaining candidates
                break;
            // Add candidate[i] to the current array
            ArrayList<Integer> copy = new ArrayList(current);
            copy.add(num[i]);

            while (i <= num.length - 2 && num[i + 1] == num[i])
                i++;
            // Recursively append the current array to compose a solution
            recursiveAppend(num, target - num[i], i + 1, copy, result);

        }
    }

    /**
     * https://oj.leetcode.com/problems/first-missing-positive/
     * https://oj.leetcode.com/submissions/detail/9702757/
     */
    public int firstMissingPositive(int[] A) {
        if (A == null || A.length == 0) return 1;
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i < A.length; i++)
            map.put(A[i], 1);

        int missing = 1;
        while (map.get(missing) != null) missing++;
        return missing;

    }

    /**
     * https://oj.leetcode.com/problems/trapping-rain-water/
     * https://oj.leetcode.com/submissions/detail/9704165/
     */
    public int trap(int[] A) {
        if (A == null || A.length <= 2)
            return 0;

        int result = 0;

        int N = A.length;

        HashMap<Integer, LeftAndRight> map = new HashMap<Integer, LeftAndRight>();
        map.put(1, new LeftAndRight(A[0], 0));

        for (int i = 2; i <= N - 2; i++) {
            int left = map.get(i - 1).left;
            int a = Math.max(left, A[i - 1]);
            map.put(i, new LeftAndRight(a, 0));
        }

        LeftAndRight l = map.get(N - 2);
        l.right = A[N - 1];
        map.put(N - 2, l);

        for (int i = A.length - 3; i >= 1; i--) {
            int right = map.get(i + 1).right;
            int b = Math.max(A[i + 1], right);
            LeftAndRight r = map.get(i);
            r.right = b;
            map.put(i, r);
        }

        for (int i = 1; i <= N - 2; i++) {
            LeftAndRight m = map.get(i);
            int k = Math.min(m.left, m.right);
            if (k > A[i]) result += (k - A[i]);
        }

        return result;

    }

    private class LeftAndRight {
        int left;
        int right;

        LeftAndRight(int left, int right) {
            this.left = left;
            this.right = right;
        }
    }

    /**
     * https://oj.leetcode.com/problems/multiply-strings/
     * http://www.darrensunny.me/leetcode-multiply-strings/
     * https://oj.leetcode.com/submissions/detail/9825634/
     */
    public String multiply1(String num1, String num2) {
        if (num1 == null || num1.length() == 0 ||
                num2 == null || num2.length() == 0)
            return "";
        if (num1.equals("0") || num2.equals("0"))   // Either one is 0
            return "0";
        int m = num1.length(), n = num2.length();
        // Multiply single digit of each number and add up products at each position
        int[] prods = new int[m + n];
        for (int i = n - 1; i >= 0; i--)
            for (int j = m - 1; j >= 0; j--)
                prods[i + j + 1] += (num2.charAt(i) - '0') * (num1.charAt(j) - '0');
        // Keep a single digit at each position and add carry to a higher position
        StringBuilder result = new StringBuilder();
        for (int i = n + m - 1; i >= 0; i--) {
            result.insert(0, prods[i] % 10);
            if (i > 0)
                prods[i - 1] += prods[i] / 10;    // Carry
        }
        // Get rid of one leaing "0" (if any)
        if (result.charAt(0) == '0')
            result.deleteCharAt(0);

        return result.toString();
    }

    public static String multiply(String num1, String num2) {
        if (num1 == null && num2 == null) return null;
        if (num1 != null && num2 == null) return num1;
        if (num1 == null && num2 != null) return num2;
        if (num1.equals("0") || num2.equals("0")) return "0";

        int N1 = num1.length();
        int N2 = num2.length();

        String result = "";

        if (N1 > N2) {
            for (int i = 0; i < N2; i++) {
                String s = "";
                int c = num2.charAt(i) - '0';

                for (int j = 0; j < c; j++) {
                    s = add(s, num1);
                }
                for (int j = 0; j < (N2 - 1 - i); j++)
                    s += "0";
                result = add(result, s);

            }
        } else {
            for (int i = 0; i < N1; i++) {
                String s = "";
                int c = num1.charAt(i) - '0';

                for (int j = 0; j < c; j++) {
                    s = add(s, num2);
                }
                for (int j = 0; j < (N1 - 1 - i); j++)
                    s += "0";

                result = add(result, s);

            }
        }
        return result;

    }

    private static String add(String num1, String num2) {
        if (num1 == null && num2 == null) return null;
        if (num1 != null && (num2 == null || num2.length() == 0 || num2.equals("0"))) return num1;
        if ((num1 == null || num1.length() == 0 || num1.equals("0")) && num2 != null) return num2;

        Stack<Character> s1 = new Stack<Character>();
        Stack<Character> s2 = new Stack<Character>();
        Stack<Character> s3 = new Stack<Character>();

        for (int i = 0; i < num1.length(); i++)
            s1.push(num1.charAt(i));

        for (int i = 0; i < num2.length(); i++)
            s2.push(num2.charAt(i));

        int carryOver = 0;

        while (!s1.isEmpty() || !s2.isEmpty()) {
            Character c1 = null;
            Character c2 = null;

            if (!s1.isEmpty())
                c1 = s1.pop();
            if (!s2.isEmpty())
                c2 = s2.pop();

            if (c1 != null)
                carryOver += c1 - '0';
            if (c2 != null)
                carryOver += c2 - '0';

            s3.push(toCharacter(carryOver % 10));
            carryOver /= 10;
        }
        if (carryOver == 1)
            s3.push(toCharacter(carryOver));

        StringBuilder sb = new StringBuilder();
        while (!s3.isEmpty())
            sb.append(s3.pop());
        return sb.toString();


    }

    private static Character toCharacter(int c) {
        if (c == 0) return '0';
        else if (c == 1) return '1';
        else if (c == 2) return '2';
        else if (c == 3) return '3';
        else if (c == 4) return '4';
        else if (c == 5) return '5';
        else if (c == 6) return '6';
        else if (c == 7) return '7';
        else if (c == 8) return '8';
        else if (c == 9) return '9';
        else return null;
    }

    /**
     * https://oj.leetcode.com/problems/wildcard-matching/
     * http://www.darrensunny.me/leetcode-wildcard-matching-2/
     * http://www.programering.com/a/MTN0kzMwATU.html isMatch4 (working one)
     * https://oj.leetcode.com/submissions/detail/9830224/
     */
    public static boolean isMatch2(String s, String p) {
        if (s == null) return p == null;
        if (p == null) return s == null;
        if (p.length() == 0) return s.length() == 0;
        if (p.equals("*")) return true;
        if (s.length() == 0) return p.length() == 0;
        char c = p.charAt(p.length() - 1);
        if (c != '?' && c != '*' && c != s.charAt(s.length() - 1)) return false;
        if (p.charAt(0) == '?') return isMatch2(s.substring(1), p.substring(1));
        if (p.charAt(0) == '*') {
            int j = 0;
            while (j < p.length() && p.charAt(j) == '*')
                j++;

            if (j >= p.length())
                return true;

            int i = 0;
            while (i < s.length()) {
                if (isMatch2(s.substring(i), p.substring(j)))
                    return true;
                i++;
            }
            return false;
        }
        return s.charAt(0) == p.charAt(0) && isMatch2(s.substring(1), p.substring(1));


    }

    public static boolean isMatch3(String s, String p) {
        if (s == null || p == null)
            return false;
        int m = s.length(), n = p.length();
        boolean[] match = new boolean[m + 1];     // Used for dynamic programming
        match[0] = true;
        // After each round, the information whether p[0...i] matches s[0...m-1]
        // is updated in match[1...m]
        for (int i = 0; i < n; i++) {
            if (p.charAt(i) != '*') {   // Not wildcard
                for (int j = m; j > 0; j--)     // Update match backwards
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

    public static boolean isMatch4(String s, String p) {
        if (s == null || p == null) return false;
        if (s.equals(p)) return true;
        int m = s.length();
        int n = p.length();
        int posS = 0;
        int posP = 0;
        int posStar = -1;
        int posOfS = -1;
        //if posS == posP || posP == '?', ++posS and ++posP.
        //posOfS, posStar, record the positon of '*' in s and p, ++posP and go on.
        //if not match, go back to star, ++posOfS
        while (posS < m) {
            if (posP < n && (s.charAt(posS) == p.charAt(posP) || p.charAt(posP) == '?')) {
                ++posS;
                ++posP;
            } else if (posP < n && p.charAt(posP) == '*') {
                posStar = posP;
                posOfS = posS;
                ++posP;
                continue;
            } else if (posStar != -1) {
                posS = posOfS;
                posP = posStar + 1;
                ++posOfS;
            } else {
                return false;
            }
        }
        while (posP < n && p.charAt(posP) == '*') {
            ++posP;
        }

        return posS == m && posP == n;
    }

    /**
     * https://oj.leetcode.com/problems/jump-game-ii/
     * http://www.darrensunny.me/leetcode-jump-game-ii/
     * https://oj.leetcode.com/submissions/detail/9860022/
     */
    public int jump(int[] A) {
        if (A == null || A.length == 1) return 0;
        int N = A.length;
        if (N == 2 || A[0] >= N - 1) return 1;

        int[] B = new int[N];
        B[N - 1] = 0;
        B[N - 2] = 1;

        for (int i = N - 3; i >= 0; i--) {
            if (A[i] >= N - 1 - i)
                B[i] = 1;
            else {
                int min = N;
                for (int j = i + 1; j <= i + A[i]; j++) {
                    if (B[j] < min)
                        min = B[j];
                }
                B[i] = min + 1;
            }
        }
        return B[0];

    }

    public int jump2(int[] A) {
        if (A == null) return -1;
        int n = A.length;
        if (n == 1) return 0;

        int jump = 0;       // The number of jumps
        // The beginning and ending indices of elements that are "jump" jumps
        // away from the first element
        int begin = 0, end = 0;
        // For each outer loop, find the largest index of the element that are
        // "jump+1" jumps from the first
        while (true) {
            jump++;
            int temp = 0;   // Used to keep track of the largest index
            // The elements "jump+1" jumps away are approachable by the elements
            // that are "jump" jumps away
            for (int i = begin; i <= end; i++) {
                temp = Math.max(temp, A[i] + i);
                if (temp >= n - 1)    // Once the largest index exceeds the array, we are done
                    return jump;
            }
            // Update "begin" and "end" as the indices of the elements "jump" jumps away
            begin = end + 1;
            end = temp;
        }
    }

    /**
     * https://oj.leetcode.com/problems/permutations/
     * http://www.programcreek.com/2013/02/leetcode-permutations-java/
     * http://www.darrensunny.me/leetcode-permutations/
     * https://oj.leetcode.com/submissions/detail/9861658/
     */
    public static List<List<Integer>> permute(int[] num) {
        List<List<Integer>> r = new ArrayList<List<Integer>>();
        Set<List<Integer>> set = new HashSet<List<Integer>>();
        List<Integer> list = new ArrayList<Integer>();
        permuteHelper(set, num, list, num.length);
        for (List<Integer> l : set)
            r.add(l);
        return r;
    }

    private static void permuteHelper(Set<List<Integer>> set, int[] num, List<Integer> list, int total) {
        if (list.size() == total)
            set.add(list);
        if (num == null || num.length == 0) return;

        int N = num.length;
        for (int i = 0; i < N; i++) {
            int n = num[i];
            List<Integer> copyList = new ArrayList<Integer>(list);
            copyList.add(n);
            int[] copyNum = new int[N - 1];
            for (int j = 0; j < i; j++) copyNum[j] = num[j];
            for (int j = i; j < N - 1; j++) copyNum[j] = num[j + 1];
            permuteHelper(set, copyNum, copyList, total);

        }

    }

    /**
     * http://www.programcreek.com/2013/02/leetcode-permutations-java
     */
    public static List<List<Integer>> permute1(int[] num) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        result.add(new ArrayList<Integer>());

        for (int i = 0; i < num.length; i++) {
            List<List<Integer>> current = new ArrayList<List<Integer>>();

            for (List<Integer> l : result) {
                for (int j = 0; j <= l.size(); j++) {
                    l.add(j, num[i]);

                    List<Integer> temp = new ArrayList<Integer>(l);
                    current.add(temp);

                    l.remove(j);
                }

            }

            result = new ArrayList<List<Integer>>(current);

        }
        return result;
    }

    /**
     * http://www.programcreek.com/2013/02/leetcode-permutations-java/
     */
    public List<List<Integer>> permute2(int[] num) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        permute(num, 0, result);
        return result;
    }

    void permute(int[] num, int start, List<List<Integer>> result) {

        if (start >= num.length) {
            ArrayList<Integer> item = convertArrayToList(num);
            result.add(item);
        }

        for (int j = start; j <= num.length - 1; j++) {
            swap(num, start, j);
            permute(num, start + 1, result);
            swap(num, start, j);
        }
    }

    /**
     * http://www.darrensunny.me/leetcode-permutations/
     */
    public List<List<Integer>> permute3(int[] num) {
        if (num == null)
            return null;
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if (num.length == 0)
            return result;
        recursivePermute(num, new boolean[num.length], new ArrayList<Integer>(), result);
        return result;
    }

    // If "current" is already a permutation of "num", add it to "result";
// otherwise, append each unused number to "current", and recursively try next unused number
    private void recursivePermute(int[] num, boolean[] used, ArrayList<Integer> current,
                                  ArrayList<ArrayList<Integer>> result) {
        if (current.size() == num.length) {     // "current" is already a permutation of "num"
            result.add(new ArrayList<Integer>(current));
            return;
        }
        // Append each unused number to "current", and recursively try next unused number
        for (int i = 0; i < num.length; i++) {
            if (!used[i]) {
                // Append an unused number
                used[i] = true;
                current.add(num[i]);
                // Recursively append next unused number
                recursivePermute(num, used, current, result);
                // Get back to original state, get ready for appending another unused number
                current.remove(current.size() - 1);
                used[i] = false;
            }
        }
    }

    /**
     * https://oj.leetcode.com/problems/permutations-ii/
     * https://oj.leetcode.com/submissions/detail/9863455/
     */

    public static List<List<Integer>> permuteUnique(int[] num) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        permuteUnique(num, 0, result);
        return result;
    }

    private static void permuteUnique(int[] num, int start, List<List<Integer>> result) {

        if (start >= num.length) {
            ArrayList<Integer> item = convertArrayToList(num);
            result.add(item);
            return;
        }

        permuteUnique(num, start + 1, result);
        Set<Integer> set = new HashSet<Integer>();
        for (int j = start + 1; j <= num.length - 1; j++) {
            if (num[start] != num[j] && !set.contains(num[j])) {
                set.add(num[j]);
                swap(num, start, j);
                permuteUnique(num, start + 1, result);
                swap(num, start, j);
            }

        }
    }

    private static ArrayList<Integer> convertArrayToList(int[] num) {
        ArrayList<Integer> item = new ArrayList<Integer>();
        for (int h = 0; h < num.length; h++) {
            item.add(num[h]);
        }
        return item;
    }

    private static void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }


    public static List<List<Integer>> permuteUnique2(int[] num) {
        if (num == null)
            return null;
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if (num.length == 0)
            return result;
        Arrays.sort(num);       // Sort the array in non-descending order
        recursivePermute(num, new boolean[num.length], new ArrayList<Integer>(), result);
        return result;
    }

    // If "current" is already a permutation of "num", add it to "result";
    // otherwise, append each unused number to "current", and recursively try next unused number
    private static void recursivePermute(int[] num, boolean[] used, List<Integer> current,
                                         List<List<Integer>> result) {
        if (current.size() == num.length) {     // "current" is already a permutation of "num"
            result.add(new ArrayList<Integer>(current));
            return;
        }
        // Append each unused number to "current", and recursively try next unused number
        for (int i = 0; i < num.length; i++) {
            if (i > 0 && !used[i - 1] && num[i] == num[i - 1])
                // Do not consider a duplicate number if its earlier appearance has
                // not been considered yet
                continue;
            if (!used[i]) {
                // Append an unused number
                used[i] = true;
                current.add(num[i]);
                // Recursively append next unused number
                recursivePermute(num, used, current, result);
                // Get back to original state, get ready for appending another unused number
                current.remove(current.size() - 1);
                used[i] = false;
            }
        }
    }

    /**
     * https://oj.leetcode.com/problems/permutation-sequence/
     * https://oj.leetcode.com/submissions/detail/10252180/
     *
     */
    public static String getPermutation(int n, int k) {
        int[] N = new int[n];
        for(int i = 0; i < n; i++) {
            N[i] = i + 1;
        }

        return getPermutation(N, k);

    }

    private static String getPermutation(int[] N, int k){

        if(k == 1) {
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < N.length; i++){
                sb.append(N[i]);
            }
            return sb.toString();

        }

        int startIndex = (k - 1) / factorial(N.length -1);
        String start = "" + N[startIndex];
        k = k - startIndex * factorial(N.length - 1);

        int[] M = new int[N.length - 1];
        for(int i = 0; i < startIndex; i++){
            M[i] = N[i];

        }
        for(int i = startIndex; i <= N.length - 2; i++){
            M[i] = N[i+1];
        }

        return start + getPermutation(M, k);


    }

    private static int factorial(int n){
        int result = 1;
        for(int i = 1; i <= n; i++){
            result *= i;
        }
        return result;
    }


    /**
     * https://oj.leetcode.com/problems/rotate-list/
     * https://oj.leetcode.com/submissions/detail/10253951/
     */
    public ListNode rotateRight(ListNode head, int n) {

        if(n == 0 || head == null) return head;

        //Get the length of the list
        int len = 1;
        ListNode node = head;
        while(node.next != null){
            node = node.next;
            len++;
        }

        if(len == 1 || n % len == 0) return head;

        int offset = len - n % len;
        ListNode prev = head;
        while(offset > 1){
            prev = prev.next;
            offset--;
        }
        ListNode newHead = prev.next;
        prev.next = null;
        node.next = head;
        return newHead;


    }




    /**
     * https://oj.leetcode.com/problems/rotate-image/
     * https://oj.leetcode.com/submissions/detail/9903296/
     */
    public void rotate(int[][] matrix) {
        if (matrix == null || matrix[0].length <= 1) return;
        int N = matrix[0].length;
        for (int layer = 0; layer < N / 2; layer++) {
            int rowStart = layer;
            int rowEnd = N - 1 - layer;
            int colStart = layer;
            int colEnd = N - 1 - layer;
            rotate(matrix, rowStart, rowEnd, colStart, colEnd);
        }

    }

    private static void rotate(int[][] matrix, int rowStart, int rowEnd, int colStart, int colEnd) {
        for (int i = 0; i <= colEnd - colStart - 1; i++) {
            int temp = matrix[rowStart][colStart + i];
            matrix[rowStart][colStart + i] = matrix[rowEnd - i][colStart];
            matrix[rowEnd - i][colStart] = matrix[rowEnd][colEnd - i];
            matrix[rowEnd][colEnd - i] = matrix[rowStart + i][colEnd];
            matrix[rowStart + i][colEnd] = temp;
        }
    }

    /**
     * https://oj.leetcode.com/problems/anagrams/
     * https://oj.leetcode.com/submissions/detail/9905057/
     */
    public static List<String> anagrams(String[] strs) {
        List<String> result = new ArrayList<String>();
        if (strs == null || strs.length <= 1) return result;

        Map<String, List<String>> map = new HashMap<String, List<String>>();
        for (int i = 0; i < strs.length; i++) {
            String s = strs[i];
            if (s != null) {
                String key = s;
                if (s.trim().length() > 0) {
                    char[] cArray = s.toCharArray();
                    Arrays.sort(cArray);
                    key = arrayToString(cArray);
                }

                if (!map.containsKey(key)) {
                    List<String> list = new ArrayList<String>();
                    list.add(s);
                    map.put(key, list);
                } else {
                    List<String> list = map.get(key);
                    list.add(s);
                }
            }


        }

        for (String key : map.keySet()) {
            List<String> list = map.get(key);
            if (list != null && list.size() >= 2) {
                result.addAll(list);
            }
        }

        return result;


    }

    private static String arrayToString(char[] cArray) {
        StringBuilder sb = new StringBuilder();

        for (char c : cArray)
            sb.append(c);

        return sb.toString();
    }


    public static List<String> anagrams2(String[] strs) {
        List<String> result = new ArrayList<String>();
        if (strs == null || strs.length <= 1) return result;
        boolean[] isAnagram = new boolean[strs.length];

        for (int i = 0; i <= strs.length - 2; i++) {
            if (isAnagram[i]) continue;
            String s = strs[i];

            for (int j = i + 1; j < strs.length; j++) {
                if (isAnagram[j]) continue;
                if (isAnagram(s, strs[j])) {
                    isAnagram[i] = true;
                    isAnagram[j] = true;
                }
            }
        }

        for (int i = 0; i < strs.length; i++) {
            if (isAnagram[i]) result.add(strs[i]);
        }
        return result;


    }

    private static boolean isAnagram(String s1, String s2) {
        if (s1 == null || s2 == null) return false;
        if (s1.length() != s2.length()) return false;
        if (s1.equals(s2)) return true;

        Map<Character, Integer> countMap = new HashMap<Character, Integer>();
        for (int i = 0; i < s1.length(); i++) {
            char c = s1.charAt(i);
            if (countMap.get(c) == null) countMap.put(c, 1);
            else countMap.put(c, countMap.get(c) + 1);
        }

        for (int i = 0; i < s2.length(); i++) {
            char c = s2.charAt(i);
            if (countMap.get(c) == null || countMap.get(c) == 0) return false;
            else countMap.put(c, countMap.get(c) - 1);
        }
        return true;
    }


    /**
     * https://oj.leetcode.com/problems/powx-n/
     * https://oj.leetcode.com/submissions/detail/9905406/
     */
    public double pow(double x, int n) {
        if (n == 0) return 1;
        if (n == 1) return x;
        if (n == 2) return x * x;

        int m = Math.abs(n);
        double y = pow(x, m / 2);

        if (m % 2 == 0) y = y * y;
        else y = y * y * x;


        if (n < 0)
            return 1.0 / y;
        else
            return y;

    }

    /**
     * https://oj.leetcode.com/problems/n-queens/
     * http://www.darrensunny.me/leetcode-n-queens/
     * https://oj.leetcode.com/problems/n-queens-ii/
     * https://oj.leetcode.com/submissions/detail/9980530/
     * https://oj.leetcode.com/submissions/detail/10196846/
     * https://oj.leetcode.com/submissions/detail/10197004/
     */
    public static int totalNQueens(int n) {
        int[] solutions = new int[1];
        if (n <= 0) return solutions[0];
        solved2(solutions, newBoard(n), 0);
        return solutions[0];

    }


    private static void solved2(int[] solutions, char[][] board, int row) {
        int N = board.length;

        if (row == N)
            solutions[0]++;
        else {
            for (int j = 0; j < N; j++) {
                if (isValid(board, row, j)) {
                    board[row][j] = 'Q';
                    solved2(solutions, board, row + 1);
                }
                board[row][j] = '.';

            }

        }


    }

    public static List<String[]> solveNQueens(int n) {
        List<String[]> result = new ArrayList<String[]>();
        if (n <= 0) return result;
        solved(result, newBoard(n), 0);
        return result;

    }

    private static char[][] newBoard(int n) {
        char[][] board = new char[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                board[i][j] = '.';
        return board;
    }


    private static String[] toStringArray(char[][] board) {
        int N = board.length;
        String[] strs = new String[N];
        for (int i = 0; i < N; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < N; j++) {
                sb.append(board[i][j]);
            }
            strs[i] = sb.toString();
        }
        return strs;
    }

    private static void solved(List<String[]> result, char[][] board, int row) {
        int N = board.length;

        if (row == N)
            result.add(toStringArray(board));
        else {
            for (int j = 0; j < N; j++) {
                if (isValid(board, row, j)) {
                    board[row][j] = 'Q';
                    solved(result, board, row + 1);
                }
                board[row][j] = '.';

            }

        }


    }


    private static boolean isValid(char[][] board, int i, int j) {
        int N = board.length;

        //check column
        for (int row = 0; row < N; row++) {
            if (board[row][j] == 'Q')
                return false;
        }

        //check row
        for (int col = 0; col < N; col++) {
            if (board[i][col] == 'Q')
                return false;
        }

        //check diagnal
        int row = i - 1;
        int col = j - 1;
        while (row >= 0 && col >= 0) {
            if (board[row][col] == 'Q')
                return false;
            row--;
            col--;
        }

        row = i + 1;
        col = j + 1;
        while (row < N && col < N) {
            if (board[row][col] == 'Q')
                return false;
            row++;
            col++;
        }

        row = i - 1;
        col = j + 1;
        while (row >= 0 && col < N) {
            if (board[row][col] == 'Q')
                return false;
            row--;
            col++;
        }

        row = i + 1;
        col = j - 1;
        while (row < N && col >= 0) {
            if (board[row][col] == 'Q')
                return false;
            row++;
            col--;
        }

        return true;
    }


    /**
     * https://oj.leetcode.com/problems/maximum-subarray/
     * http://www.darrensunny.me/leetcode-maximum-subarray/
     * https://oj.leetcode.com/submissions/detail/10198030/
     */
    public static int maxSubArray(int[] A) {
        return maxSubArray(A, 0, A.length - 1);
    }

    public static int maxSubArray(int[] A, int lo, int hi) {
        if (A == null || A.length == 0 || lo > hi) return Integer.MIN_VALUE;
        if (A.length == 1 || lo == hi) return A[lo];

        int mid = (lo + hi) / 2;

        int max1 = maxSubArray(A, lo, mid - 1);
        int max2 = maxSubArray(A, mid + 1, hi);

        int max3 = A[mid];
        int max = max3;

        for (int i = mid - 1; i >= lo; i--) {
            max3 += A[i];
            max = Math.max(max, max3);
        }

        max3 = max;

        for (int i = mid + 1; i <= hi; i++) {
            max3 += A[i];
            max = Math.max(max, max3);
        }

        max = Math.max(max, max1);
        max = Math.max(max, max2);

        return max;
    }

    public static int maxSubArray2(int[] A) {
        if (A == null || A.length == 0)
            return 0;
        int sum = 0, maxSum = Integer.MIN_VALUE;
        // Find maximum sum of all subarrays with the properties:
        // 1. Any prefix of it is non-negative;
        // 2. Any suffix of its preceding numbers are non-positive
        // 3. Any prefix of its following numbers are non-positive
        for (int i = 0; i < A.length; i++) {
            sum += A[i];
            maxSum = Math.max(sum, maxSum);
            if (sum < 0)
                // Preceding numbers make negative contribution;
                // no need to take them into consideration
                sum = 0;
        }

        return maxSum;
    }

    /**
     * https://oj.leetcode.com/problems/spiral-matrix/
     * https://oj.leetcode.com/submissions/detail/10200955/
     */
    public static List<Integer> spiralOrder(int[][] matrix) {
        if (matrix == null) return null;

        List<Integer> result = new ArrayList<Integer>();
        int m = matrix.length;
        if (m == 0)
            return result;

        int n = matrix[0].length;
        if (n == 0)
            return result;

        int N = Math.min(m, n);

        for (int offset = 0; offset <= (N + 1) / 2 - 1; offset++) {
            int rowStart = offset, rowEnd = m - 1 - offset, colStart = offset, colEnd = n - 1 - offset;
            spiral(result, matrix, rowStart, rowEnd, colStart, colEnd);
        }

        return result;

    }

    private static void spiral(List<Integer> result, int[][] matrix, int rowStart, int rowEnd, int colStart, int colEnd) {

        if (rowStart == rowEnd)
            for (int i = colStart; i <= colEnd; i++)
                result.add(matrix[rowStart][i]);
        else if (colStart == colEnd)
            for (int i = rowStart; i <= rowEnd; i++)
                result.add(matrix[i][colStart]);
        else if (rowStart < rowEnd && colStart < colEnd) {

            for (int i = colStart; i <= colEnd; i++)
                result.add(matrix[rowStart][i]);

            for (int i = rowStart + 1; i <= rowEnd; i++)
                result.add(matrix[i][colEnd]);

            for (int i = colEnd - 1; i >= colStart; i--)
                result.add(matrix[rowEnd][i]);

            for (int i = rowEnd - 1; i >= rowStart + 1; i--)
                result.add(matrix[i][colStart]);
        }

    }

    /**
     * https://oj.leetcode.com/problems/spiral-matrix-ii/
     * https://oj.leetcode.com/submissions/detail/10248635/
     *
     */
    public int[][] generateMatrix(int n) {
        if(n < 0) return null;
        int[][] matrix = new int[n][n];

        if(n == 0) return matrix;

        int[] count = new int[1];
        count[0] = 1;

        for (int offset = 0; offset <= (n + 1) / 2 - 1; offset++) {
            int rowStart = offset, rowEnd = n - 1 - offset, colStart = offset, colEnd = n - 1 - offset;
            spiralMatrix(matrix, rowStart, rowEnd, colStart, colEnd, count);
        }

        return matrix;



    }

    private static void spiralMatrix(int[][] matrix, int rowStart, int rowEnd, int colStart, int colEnd, int[] count) {

        if (rowStart == rowEnd)
            for (int i = colStart; i <= colEnd; i++) {
                matrix[rowStart][i] = count[0];
                count[0]++;
            }
        else if (colStart == colEnd)
            for (int i = rowStart; i <= rowEnd; i++) {
                matrix[i][colStart] = count[0];
                count[0]++;
            }
        else if (rowStart < rowEnd && colStart < colEnd) {

            for (int i = colStart; i <= colEnd; i++) {
                matrix[rowStart][i] = count[0];
                count[0]++;
            }

            for (int i = rowStart + 1; i <= rowEnd; i++) {
                matrix[i][colEnd] = count[0];
                count[0]++;
            }

            for (int i = colEnd - 1; i >= colStart; i--) {
                matrix[rowEnd][i] = count[0];
                count[0]++;
            }

            for (int i = rowEnd - 1; i >= rowStart + 1; i--) {
                matrix[i][colStart] = count[0];
                count[0]++;
            }
        }

    }

    /**
     * https://oj.leetcode.com/problems/jump-game/
     * https://oj.leetcode.com/submissions/detail/10203444/
     */
    public static boolean canJump(int[] A) {
        if (A == null || A.length == 0) return true;
        int max = A[0];
        if (max >= A.length - 1) return true;

        while (true) {
            for (int i = 1; i <= max; i++) {
                if (i + A[i] >= A.length - 1) return true;
                if (i + A[i] > max) max = i + A[i];

            }

            return false;

        }

    }

    /**
     * https://oj.leetcode.com/problems/merge-intervals/
     * http://www.programcreek.com/2012/12/leetcode-merge-intervals/
     * http://www.darrensunny.me/leetcode-merge-intervals/
     * https://oj.leetcode.com/submissions/detail/10215552/
     */
    public List<Interval> merge(List<Interval> intervals) {
        if (intervals == null || intervals.size() <= 1) return intervals;

        Interval[] items = intervals.toArray(new Interval[intervals.size()]);
        Arrays.sort(items, new IntervalStartComparator());

        List<Interval> result = new ArrayList<Interval>();

        Interval item = items[0];
        for (int i = 1; i < items.length; i++) {
            if (items[i].start <= item.end) {
                item.end = Math.max(item.end, items[i].end);
            } else {
                result.add(item);

                item = items[i];
            }
        }

        result.add(item);

        return result;


    }

    static class IntervalStartComparator implements Comparator<Interval> {
        public int compare(Interval a, Interval b) {
            return new Integer(a.start).compareTo(new Integer(b.start));
        }
    }


    public static class Interval {
        int start;
        int end;

        Interval() {
            start = 0;
            end = 0;
        }

        Interval(int s, int e) {
            start = s;
            end = e;
        }
    }

    /**
     * https://oj.leetcode.com/problems/insert-interval/
     * http://www.programcreek.com/2012/12/leetcode-insert-interval/
     * http://www.darrensunny.me/leetcode-insert-interval/
     * https://oj.leetcode.com/submissions/detail/10217871/
     *
     */
    public static List<Interval> insert(List<Interval> intervals, Interval newInterval) {
        if(newInterval == null) return intervals;

        if(intervals == null) {
            intervals = new ArrayList<Interval>();
        }
        if(intervals.isEmpty()){
            intervals.add(newInterval);
            return intervals;

        }

        Collections.sort(intervals, new IntervalStartComparator());

        int size = intervals.size();

        if(newInterval.end < intervals.get(0).start){
            intervals.add(0, newInterval);
            return intervals;
        }

        if(newInterval.start > intervals.get(size - 1).end){
            intervals.add(size, newInterval);
            return intervals;
        }

        int i = 0, j = 0;

        while(i <= size - 1 && intervals.get(i).end < newInterval.start){
            i++;
            j++;

        }

        while(j <= size - 1 && intervals.get(j).start <= newInterval.end){
            j++;
        }

        for(int k = i; k <= j - 1; k++){
            newInterval.start = Math.min(newInterval.start, intervals.get(i).start);
            newInterval.end = Math.max(newInterval.end, intervals.get(i).end);
            intervals.remove(i);
        }

        intervals.add(i, newInterval);

        return intervals;

    }

    /**
     * https://oj.leetcode.com/problems/length-of-last-word/
     * https://oj.leetcode.com/submissions/detail/10218507/
     *
     */
    public int lengthOfLastWord(String s) {
        int len = 0;

        if(s == null || s.length() == 0)
            return 0;

        for(int i = s.length() - 1; i >= 0; i--){
            if(s.charAt(i) != ' ')
                len++;
            else {
                if(len > 0)
                    return len;
            }

        }
        return len;
    }

    /**
     * https://oj.leetcode.com/problems/merge-two-sorted-lists/
     * https://oj.leetcode.com/submissions/detail/10316971/
     */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;

        ListNode head = new ListNode(0);
        ListNode n = head;

        while (l1 != null || l2 != null) {
            if (l1 == null) {
                n.next = l2;
                break;
            } else if (l2 == null) {
                n.next = l1;
                break;
            } else {
                if (l1.val <= l2.val) {
                    n.next = l1;
                    l1 = l1.next;
                } else {
                    n.next = l2;
                    l2 = l2.next;
                }
                n = n.next;
                n.next = null;
            }
        }
        return head.next;


    }

    /**
     * https://oj.leetcode.com/problems/add-binary/
     * https://oj.leetcode.com/submissions/detail/10317701/
     */
    public String addBinary(String a, String b) {
        if (a == null || a.length() == 0) return b;
        if (b == null || b.length() == 0) return a;

        String c = "";
        int index1 = a.length() - 1;
        int index2 = b.length() - 1;
        int carryOver = 0;
        while (index1 >= 0 || index2 >= 0) {
            if (index1 >= 0) {
                carryOver += a.charAt(index1) - '0';
                index1--;
            }
            if (index2 >= 0) {
                carryOver += b.charAt(index2) - '0';
                index2--;
            }
            c = carryOver % 2 + c;
            carryOver = carryOver / 2;
        }

        if(carryOver > 0){
            c = carryOver + c;
        }
        return c;

    }

    /**
     * https://oj.leetcode.com/problems/valid-number/
     * http://www.darrensunny.me/leetcode-valid-number/
     * https://oj.leetcode.com/submissions/detail/10320818/
     *
     */
    public static boolean isNumber(String s) {
        if (s == null || s.trim().length() == 0) return false;
        s = s.trim();
        s = s.replaceAll(" ", "");
        s = s.replaceAll("\t", "");
        int signCount = 0;
        int dotCount = 0;
        int eCount = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if ('0' <= c && '9' >= c)
                continue;
            else if (c == '+' || c == '-') {
                if (signCount >= 2) return false;

                if (i == 0 || s.charAt(i - 1) == 'e' || s.charAt(i - 1) == 'E') {
                    signCount++;
                    continue;
                } else
                    return false;

            } else if (c == '.') {
                if (dotCount > 0 || i == s.length() - 1)
                    return false;
                else {
                    dotCount++;
                    continue;
                }
            } else if (c == 'e' || c == 'E') {
                if (eCount > 0 || i == 0 || i == s.length() - 1)
                    return false;
                else if (i > 0 && s.charAt(i - 1) == '.') {
                    return false;
                } else {
                    eCount++;
                    continue;
                }
            } else {
                return false;
            }
        }
        return true;


    }


    /**
     * https://oj.leetcode.com/problems/sqrtx/
     * https://oj.leetcode.com/submissions/detail/10323137/
     *
     */
    public static int sqrt(int x) {
        double sRoot = 1.0;

        while (Math.abs(x - sRoot * sRoot) >= 0.1) {
            sRoot = (sRoot + x / sRoot) / 2.0;
        }

        return (int)sRoot;

    }


    /**
     * https://oj.leetcode.com/problems/climbing-stairs/
     * http://www.darrensunny.me/leetcode-climbing-stairs/
     * https://oj.leetcode.com/submissions/detail/10544378/
     *
     */
    public static int climbStairs(int n) {
        if(n <= 1) return 1;

        int r0 = 1;
        int r1 = 1;
        int r2 = r0 + r1;

        for(int i = 2; i <= n; i++){
            r2 = r0 + r1;
            r0 = r1;
            r1 = r2;
        }
        return r2;

    }

    public static int climbStairs2(int n) {
        if (n <= 1) return 1;

        List<String> result = new ArrayList<String>();
        climbStairs(n, "", result);
        return result.size();
    }


    private static void climbStairs(int n, String steps, List<String> result) {
        if (n == 0) {
            result.add(steps);
            return;
        } else if (n == 1) {
            result.add(steps + "1");
            return;
        } else {
            climbStairs(n - 1, steps + "1", result);
            climbStairs(n - 2, steps + "2", result);
        }

    }

    /**
     * https://oj.leetcode.com/problems/simplify-path/
     * http://www.darrensunny.me/leetcode-simplify-path/
     * https://oj.leetcode.com/submissions/detail/10547086/
     *
     */
    public static String simplifyPath(String path) {
        if (path == null || path.trim().length() == 0)
            return path;

        path = path.trim();
        Stack<String> stack = new Stack<String>();

        String dir = "";
        for (int i = 0; i < path.length(); i++) {
            char c = path.charAt(i);

            if (c == '/') {
                if (dir.equals("..")) {
                    if (!stack.isEmpty())
                        stack.pop();

                } else if (dir.length() > 0) {
                    if (!dir.equals("."))
                        stack.push(dir);
                }
                dir = "";

            } else {
                dir += c;
            }
        }

        if (dir.equals("..")) {
            if (!stack.isEmpty())
                stack.pop();
        } else if (dir.length() > 0) {
            if (!dir.equals("."))
                stack.push(dir);
        }

        String sPath = "";
        while (!stack.isEmpty()) {
            if (sPath.length() > 0) {
                sPath = stack.pop() + "/" + sPath;
            } else {
                sPath = stack.pop();
            }
        }
        sPath = "/" + sPath;

        return sPath;


    }

    /**
     * https://oj.leetcode.com/problems/set-matrix-zeroes/
     * http://www.programcreek.com/2012/12/leetcode-set-matrix-zeroes-java/
     *
     */
    public static void setZeroes(int[][] matrix) {
        boolean firstRowZero = false;
        boolean firstColumnZero = false;

        //set first row and column zero or not
        for(int i=0; i<matrix.length; i++){
            if(matrix[i][0] == 0){
                firstColumnZero = true;
                break;
            }
        }

        for(int i=0; i<matrix[0].length; i++){
            if(matrix[0][i] == 0){
                firstRowZero = true;
                break;
            }
        }

        //mark zeros on first row and column
        for(int i=1; i<matrix.length; i++){
            for(int j=1; j<matrix[0].length; j++){
                if(matrix[i][j] == 0){
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }

        //use mark to set elements
        for(int i=1; i<matrix.length; i++){
            for(int j=1; j<matrix[0].length; j++){
                if(matrix[i][0] == 0 || matrix[0][j] == 0){
                    matrix[i][j] = 0;
                }
            }
        }

        //set first column and row
        if(firstColumnZero){
            for(int i=0; i<matrix.length; i++)
                matrix[i][0] = 0;
        }

        if(firstRowZero){
            for(int i=0; i<matrix[0].length; i++)
                matrix[0][i] = 0;
        }



    }

    /**
    * https://oj.leetcode.com/problems/search-a-2d-matrix/
     * http://www.programcreek.com/2013/01/leetcode-search-a-2d-matrix-java/
     * https://oj.leetcode.com/submissions/detail/10560534/
    */
    public static boolean searchMatrix(int[][] matrix, int target) {
        if(matrix==null || matrix.length==0 || matrix[0].length==0)
            return false;

        int m = matrix.length;
        int n = matrix[0].length;

        int start = 0;
        int end = m*n-1;

        while(start<=end){
            int mid=(start+end)/2;
            int midX=mid/n;
            int midY=mid%n;

            if(matrix[midX][midY]==target)
                return true;

            if(matrix[midX][midY]<target){
                start=mid+1;
            }else{
                end=mid-1;
            }
        }

        return false;
    }

    public static boolean searchMatrix2(int[][] matrix, int target) {
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0)
            return false;

        int m = matrix.length, n = matrix[0].length;
        int lo = 0, hi = m - 1;

        while(lo <= hi) {
            if(target < matrix[lo][0] || target > matrix[hi][n-1])
                return false;

            int mid = lo + (hi - lo)/2;
            if(target < matrix[mid][0]) hi = mid -1;
            else if (target > matrix[mid][n-1]) lo = mid + 1;
            else {
                int clo = 0, chi = n - 1;
                while(clo <= chi) {
                    int cmid = clo + (chi - clo)/2;
                    if(target < matrix[mid][cmid]) chi = cmid - 1;
                    else if (target > matrix[mid][cmid]) clo = cmid + 1;
                    else return true;
                }
                return false;
            }
        }
        return false;
    }

    /**
     * https://oj.leetcode.com/problems/sort-colors/
     * http://www.darrensunny.me/leetcode-sort-colors/
     * https://oj.leetcode.com/submissions/detail/10588033/
     *
     */
    public static void sortColors(int[] A) {
        if (A == null || A.length == 0) return;

        int startZero = 0, startOne = 0, startTwo = 0, countZero = 0, countOne = 0, countThree = 0;
        for (int i = 0; i < A.length; i++) {
            if (A[i] == 0) {
                int temp = A[i];
                A[i] = A[startTwo];
                A[startTwo] = A[startOne];
                A[startOne] = temp;
                countZero++;
                startTwo++;
                startOne++;
            } else if (A[i] == 1) {
                int temp = A[i];
                A[i] = A[startTwo];
                A[startTwo] = temp;
                countOne++;
                startTwo++;
            } else {
                countThree++;

            }
        }
    }

    /**
     * https://oj.leetcode.com/problems/combinations/
     * http://www.darrensunny.me/leetcode-combinations/
     * https://oj.leetcode.com/submissions/detail/10591173/
     *
     */
    public static List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        List<Integer> list = new ArrayList<Integer>();
        combine(result, list, 1, k, n);
        return result;
    }

    private static void combine(List<List<Integer>> result, List<Integer> list, int start, int k, int n) {
        if (k == 0) {
            result.add(list);
            return;
        }

        for (int i = start; i <= n - k + 1; i++) {
            list.add(i);
            combine(result, new ArrayList<Integer>(list), i + 1, k - 1, n);
            list.remove(new Integer(i));
        }

    }

    /**
     * https://oj.leetcode.com/problems/subsets/
     * http://www.darrensunny.me/leetcode-subsets/
     * https://oj.leetcode.com/submissions/detail/10592291/
     */
    public static List<List<Integer>> subsets(int[] S) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        List<Integer> list = new ArrayList<Integer>();
        result.add(list);

        if (S == null || S.length == 0) return result;

        Arrays.sort(S);

        for (int i = 1; i <= S.length; i++) {

            combine(result, new ArrayList<Integer>(), S, 1, i, S.length);
        }
        return result;

    }

    //Create the set by choose k (1 to S.length) out of n numbers.
    private static void combine(List<List<Integer>> result, List<Integer> list, int[] S, int start, int k, int n) {
        if (k == 0) {
            result.add(list);
            return;
        }

        //For a certain k, the first number can only go up to index n - k, because there need to be k number ascending order
        // and (n-1) - (n-k+1) + 1 = k numbers, if you start after n - k, there will not be enough numbers.
        for (int i = start; i <= n - k + 1; i++) {
            list.add(S[i - 1]);
            combine(result, new ArrayList<Integer>(list), S, i + 1, k - 1, n);
            list.remove(new Integer(S[i - 1]));
        }

    }

    /**
     * https://oj.leetcode.com/problems/subsets-ii/
     * https://oj.leetcode.com/submissions/detail/11293407/
     *
     */
    public static List<List<Integer>> subsetsWithDup(int[] num) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        List<Integer> list = new ArrayList<Integer>();
        result.add(list);

        if(num == null || num.length == 0)
            return result;

        Arrays.sort(num);
        for(int i = 1; i <= num.length; i++){
            combineWithDup(result, list, num, 1, i, num.length);
        }
        return result;

    }



    private static void combineWithDup(List<List<Integer>> result, List<Integer> list, int[] S, int start, int k, int n) {
        if (k == 0) {
            result.add(list);
            return;
        }

        //if i > start, S[i-1] == S[i-2] continue
        for (int i = start; i <= n - k + 1; i++) {
            if(i > start && S[i- 1] == S[i - 2])
                continue;
            else {
                list.add(S[i - 1]);
                combineWithDup(result, new ArrayList<Integer>(list), S, i + 1, k - 1, n);
                list.remove(new Integer(S[i - 1]));
            }
        }

    }


    /**
     * https://oj.leetcode.com/problems/minimum-window-substring/
     * http://leetcode.com/2010/11/finding-minimum-window-in-s-which.html
     * https://oj.leetcode.com/submissions/detail/10653929/
     *
     */
    public static String minWindow(String S, String T) {
        String result = "";
        if (S == null || T == null || S.length() == 0 || T.length() == 0)
            return "";
        Map<Character, Integer> countMap = countMap(T);
        Map<Character, Integer> map = new HashMap<Character, Integer>();
        Map<Character, Integer> temp = new HashMap<Character, Integer>(countMap);

        int i = 0, j = 0;

        //Advanced i j to the first letter that is in the T
        while (i < S.length() && j < S.length() && !countMap.containsKey(S.charAt(i))) {
            i++;
            j++;

        }

        // Advanced j to to the position that S.substring(i, j) contains T
        while (!temp.isEmpty()) {
            //If j reaches the end of S return
            if (j == S.length()) return result;
            char c = S.charAt(j);
            if (countMap.containsKey(c)) {
                if (map.containsKey(c))
                    map.put(c, map.get(c) + 1);
                else
                    map.put(c, 1);

                if (temp.containsKey(c)) {

                    if (temp.get(c) > 1)
                        temp.put(c, temp.get(c) - 1);
                    else
                        temp.remove(c);
                }

            }
            j++;
        }
        result = S.substring(i, j);


        while (true) {
            //Advance i to the position that S.substring(i, j) still has all characters in T
            //until count of S.charAt(i) is less than it is in countMap
            while (i < j) {
                char c = S.charAt(i);
                if (map.containsKey(c)) {
                    map.put(c, map.get(c) - 1);
                    if (map.get(c) < countMap.get(c)) {
                        if (j - i < result.length()) {
                            result = S.substring(i, j);
                        }
                        break;
                    }
                }
                i++;
            }

            char c = S.charAt(i);
            i++;
            while (i < j && !countMap.containsKey(S.charAt(i))) i++;

            while (j < S.length() && S.charAt(j) != c) {
                char b = S.charAt(j);
                if (map.containsKey(b)) {
                    map.put(b, map.get(b) + 1);
                }
                j++;
            }

            if (j < S.length()) {
                map.put(c, map.get(c) + 1);
                j++;
                if (j - i < result.length()) {
                    result = S.substring(i, j);
                }
            } else {
                break;
            }


        }

        return result;


    }

    private static Map<Character, Integer> countMap(String s) {
        Map<Character, Integer> map = new HashMap<Character, Integer>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (map.containsKey(c))
                map.put(c, map.get(c) + 1);
            else
                map.put(c, 1);
        }
        return map;
    }

    /**
     * https://oj.leetcode.com/problems/word-search/
     * https://oj.leetcode.com/submissions/detail/10721616/
     *
     */
    public static boolean exist(char[][] board, String word) {
        if(board == null || word == null || board.length == 0 || board[0].length == 0 || word.length() == 0)
            return false;

        List<Position> startPositions = getStartPositions(board, word);
        for(Position p : startPositions) {
            Map<Position, Boolean> map = new HashMap<Position, Boolean>();
            map.put(p, Boolean.TRUE);
            if(exist(board, p, map, word.substring(1)))
                return true;
        }
        return false;

    }

    private static boolean exist(char[][] board, Position p, Map<Position, Boolean> map, String word) {
        if(word.length() == 0) return true;
        char c = word.charAt(0);
        int i = p.i, j = p.j;

        Position nextP = new Position(i, j - 1);
        if(isAvailable(board, nextP, map, c)) {
            map.put(nextP, Boolean.TRUE);
            if(exist(board, nextP, map, word.substring(1)))
                return true;
            map.remove(nextP);
        }

        nextP = new Position(i, j + 1);
        if(isAvailable(board, nextP, map, c)) {
            map.put(nextP, Boolean.TRUE);
            if(exist(board, nextP, map, word.substring(1)))
                return true;
            map.remove(nextP);
        }

        nextP = new Position(i - 1, j);
        if(isAvailable(board, nextP, map, c)) {
            map.put(nextP, Boolean.TRUE);
            if(exist(board, nextP, map, word.substring(1)))
                return true;
            map.remove(nextP);
        }

        nextP = new Position(i + 1, j);
        if(isAvailable(board, nextP, map, c)) {
            map.put(nextP, Boolean.TRUE);
            if(exist(board, nextP, map, word.substring(1)))
                return true;
            map.remove(nextP);
        }

        return false;


    }

    private static boolean isAvailable(char[][] board, Position p, Map<Position, Boolean> map, char c){
        return p.i >= 0 && p.i < board.length && p.j >= 0 && p.j < board[0].length
                && !map.containsKey(p) && board[p.i][p.j] == c;
    }

    private static List<Position> getStartPositions(char[][] board, String word) {
        List<Position> positions = new ArrayList<Position>();
        char c = word.charAt(0);
        for(int i = 0; i < board.length; i++)
            for(int j = 0; j < board[0].length; j++)
                if(board[i][j] == c)
                    positions.add(new Position(i, j));
        return positions;
    }

    private static class Position {
        int i;
        int j;

        Position(int i, int j) {
            this.i = i;
            this.j = j;
        }

        public boolean equals(Object p) {
            if(p instanceof Position)
                return this.i == ((Position)p).i && this.j == ((Position)p).j;
            else
                return false;
        }

        public int hashCode()
        {
            return "row".hashCode() * i + "col".hashCode() * j;
        }


    }

    /**
     * https://oj.leetcode.com/problems/remove-duplicates-from-sorted-list/
     * https://oj.leetcode.com/submissions/detail/10902450/
     *
     */
    public ListNode deleteDuplicates(ListNode head) {
        if(head == null || head.next == null) return head;
        ListNode n = head;
        while(n != null) {
            while(n.next != null && n.next.val == n.val)
                n.next = n.next.next;
            n = n.next;

        }
        return head;
    }

    /**
     * https://oj.leetcode.com/problems/remove-duplicates-from-sorted-list-ii/
     * https://oj.leetcode.com/submissions/detail/10903116/
     *
     */
    public ListNode deleteDuplicates2(ListNode head) {
        if(head == null || head.next == null) return head;

        ListNode s = new ListNode(0);
        ListNode p = s;
        ListNode n = head;
        while(n != null) {
            int count = 1;
            while(n.next != null && n.next.val == n.val){
                n.next = n.next.next;
                count++;
            }
            if(count == 1){
                p.next = n;
                p = n;
                n = n.next;
                p.next = null;
            } else {
                n = n.next;
            }

        }
        return s.next;

    }

    /**
     * https://oj.leetcode.com/problems/largest-rectangle-in-histogram/
     * http://www.geeksforgeeks.org/largest-rectangle-under-histogram/
     * https://oj.leetcode.com/submissions/detail/10906717/
     *
     */
    public static int largestRectangleArea(int[] height) {
        if(height == null || height.length == 0)
            return 0;
        if(height.length == 1)
            return height[0];

        Rectangle[] rectangles = new Rectangle[height.length];
        rectangles[0] = new Rectangle(height[0]);
        rectangles[0].left = -1;

        for(int i = 1; i < height.length; i++){
            rectangles[i] = new Rectangle(height[i]);
            if(height[i] == 0)
                rectangles[i].left = i - 1;

            int j = i - 1;
            while(j>= 0 && height[j] > height[i])
                j--;

            if(j == -1 || height[j] != height[i])
                rectangles[i].left = j;
            else
                rectangles[i].left = rectangles[j].left;

        }

        rectangles[height.length-1].right = height.length;
        for(int i = height.length-2; i>=0; i--){
            if(height[i] == 0)
                rectangles[i].right = i;

            int j = i + 1;
            while(j <= height.length -1 && height[j] > height[i])
                j++;

            if(j == height.length || height[j] != height[i])
                rectangles[i].right = j;
            else
                rectangles[i].right = rectangles[j].right;

        }

        int max = 0;
        for(int i = 0; i < height.length; i++){
            int area = rectangles[i].height * (rectangles[i].right - rectangles[i].left - 1);
            if(area > max)
                max = area;
        }
        return max;


    }

    private static class Rectangle {
        private int left;
        private int right;
        private int height;

        Rectangle(int height) {
            this.height = height;
        }
    }

    /**
     * https://oj.leetcode.com/problems/maximal-rectangle/
     * https://oj.leetcode.com/submissions/detail/10961591/
     *
     *
     */
    public static int maximalRectangle(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0)
            return 0;

        return maxRect(matrix, 0);
    }

    private static void populateOnes(char[][] matrix, boolean[] a, int nextRow) {
        for (int i = 0; i < a.length; i++) {
            if (!(a[i] && matrix[nextRow][i] == '1'))
                a[i] = false;

        }
    }

    private static int longestOnes(boolean[] a) {
        int width = 0, max = 0;
        boolean IN = false;

        for (int i = 0; i < a.length; i++) {
            if (a[i]) {
                if (!IN) {
                    IN = true;
                    width = 1;

                } else {
                    width++;
                }

                if (width > max)
                    max = width;

            } else {
                if (IN) {
                    IN = false;
                    if (width > max)
                        max = width;

                }
                width = 0;

            }

        }
        return max;

    }

    private static int maxRect(char[][] matrix, int startRow) {
        if (startRow == matrix.length) return 0;

        boolean[] a = new boolean[matrix[0].length];
        for (int i = 0; i < a.length; i++)
            a[i] = true;

        int max = 0;
        for (int j = startRow; j < matrix.length; j++) {
            populateOnes(matrix, a, j);
            int width = longestOnes(a);
            int area = width * (j - startRow + 1);
            max = Math.max(max, area);
        }
        return Math.max(max, maxRect(matrix, startRow + 1));
    }


    /**
     * https://oj.leetcode.com/problems/partition-list/
     * https://oj.leetcode.com/submissions/detail/10963337/
     *
     */
    public ListNode partition(ListNode head, int x) {
        if(head == null || head.next == null)
            return head;

        ListNode l1 = new ListNode(0);
        ListNode l2 = new ListNode(0);
        ListNode t1 = l1;
        ListNode t2 = l2;
        ListNode p = head;
        ListNode q = head;
        while(p != null) {
            if(p.val < x) {
                while(q.next != null && q.next.val < x){
                    q = q.next;
                }
                t1.next = p;
                t1 = q;
                q = q.next;
                t1.next = null;

            } else {
                while(q.next != null && q.next.val >= x) {
                    q = q.next;
                }
                t2.next = p;
                t2 = q;
                q = q.next;
                t2.next = null;
            }
            p = q;
        }
        t1.next = l2.next;
        return l1.next;
    }

    /**
     * https://oj.leetcode.com/problems/scramble-string/
     * https://oj.leetcode.com/submissions/detail/10967982/
     */
    public static boolean isScramble(String s1, String s2) {
        if (s1 == null || s2 == null)
            return false;

        if (s1.length() != s2.length())
            return false;

        if (s1.equals(s2))
            return true;


        if(!isAnagram2(s1, s2))
            return false;

        for (int i = 1; i < s1.length(); i++) {

            String s1Left=s1.substring(0, i);
            String s1Right=s1.substring(i);

            String s2Left=s2.substring(0,i);
            String s2Right=s2.substring(i);

            if (isScramble(s1Left, s2Left)&&isScramble(s1Right,s2Right)){
                return true;
            }

            s2Left=s2.substring(s2.length()-i);
            s2Right=s2.substring(0, s2.length()-i);

            if (isScramble(s1Left, s2Left)&&isScramble(s1Right,s2Right)){
                return true;
            }


        }
        return false;

    }

    private static boolean isAnagram2(String s1, String s2) {
        char[] a1 = s1.toCharArray();
        Arrays.sort(a1);
        char[] a2 = s2.toCharArray();
        Arrays.sort(a2);
        for (int i = 0; i < a1.length; i++) {
            if (a1[i] != a2[i])
                return false;
        }
        return true;
    }



    public static boolean isScramble3(String s1, String s2) {
        if( s1==null||s2==null){
            return false;
        }

        if (s1.length()==0){
            return s2.length()==0;
        }
        if (s1.length()!=s2.length()){
            return false;
        }
        if (s1.equals(s2)){
            return true;
        }


        if(!isAnagram2(s1, s2))
            return false;

        for (int i =1; i<s1.length(); i++){
            String s1Left=s1.substring(0, i);
            String s1Right=s1.substring(i);

            String s2Left=s2.substring(0,i);
            String s2Right=s2.substring(i);

            if (isScramble3(s1Left, s2Left)&&isScramble3(s1Right,s2Right)){
                return true;
            }

            s2Left=s2.substring(s2.length()-i);
            s2Right=s2.substring(0, s2.length()-i);

            if (isScramble3(s1Left, s2Left)&&isScramble3(s1Right,s2Right)){
                return true;
            }
        }

        return false;
    }

    /**
     * https://oj.leetcode.com/problems/merge-sorted-array/
     * https://oj.leetcode.com/submissions/detail/11054578/
     */
    public void merge(int A[], int m, int B[], int n) {
        if(A == null || B == null || n == 0) return;

        for(int k = m + n -1; k >= n; k--)
            A[k] = A[k - n];

        int i = n, j = 0;
        for(int k = 0; k < m + n; k++){
            if(i > m + n -1) A[k] = B[j++];
            else if(j > n - 1) A[k] = A[i++];
            else if (A[i] <= B[j]) A[k] = A[i++];
            else A[k] = B[j++];
        }
    }

    /**
     * https://oj.leetcode.com/problems/gray-code/
     * https://oj.leetcode.com/submissions/detail/11056385/
     */
    public static List<Integer> grayCode(int n) {
        List<Integer> result = new ArrayList<Integer>();
        if(n < 0) return result;

        if(n == 0){
            result.add(0);
            return result;
        } else if(n == 1){
            result.add(0);
            result.add(1);
            return result;
        } else {

            result = grayCode(n - 1);
            Stack<Integer> stack = new Stack<Integer>();
            for (Integer x : result)
                stack.push(x);
;
            int mask = 1 << (n-1);
            while (!stack.isEmpty())
                result.add(mask | stack.pop());

            return result;
        }

    }

    private static int numberOfBits(int n){
        int r = 1, x = 1;
        while(x < n){
            x <<= 1;
            r++;
        }
        return r;
    }

    /**
     * https://oj.leetcode.com/problems/decode-ways/
     * https://oj.leetcode.com/submissions/detail/11121184/
     * https://oj.leetcode.com/submissions/detail/11122075/
     *
     */
    public static int numDecodings(String s) {
        if (s == null || s.length() == 0)
            return 0;

        int r1 = 0, r2 = 0, r = 0;
        r1 = checkOne(s.charAt(0));

        if (r1 == 0 || s.length() == 1)
            return r1;

        char c1 = s.charAt(0), c2 = s.charAt(1);
        r2 = checkOne(c1) * checkOne(c2) + checkTwo(c1, c2);

        if (r2 == 0 || s.length() == 2)
            return r2;

        for (int i = 3; i <= s.length(); i++) {

            int num1 = checkTwo(s.charAt(i - 2), s.charAt(i - 1));
            int num2 = checkOne(s.charAt(i - 1));

            r = num1 * r1 + num2 * r2;
            //            System.out.println(
            //                    "decoding i=" + i + ", " + s.substring(i - 2, i) + ", " + s.substring(i - 1, i) + ",num1=" + num1
            //                            + ",num2=" + num2 + ",r1=" + r1 + ",r2=" + r2 + ",r=" + r);

            r1 = r2;
            r2 = r;

        }

        return r;

    }

    private static int checkOne(char c) {
        return (c >= '1' && c <= '9') ? 1 : 0;
    }

    private static int checkTwo(char c1, char c2) {
        return (c1 == '1' || (c1 == '2' && c2 <= '6')) ? 1 : 0;
    }


    /**
     * https://oj.leetcode.com/problems/reverse-linked-list-ii/
     * https://oj.leetcode.com/submissions/detail/11297124/
     *
     */
    public static ListNode reverseBetween(ListNode head, int m, int n) {
        if(head == null || m == n) return head;

        if(m == 1) {
            return reverseBetweenHelper(head, 1, n);
        } else {
            ListNode oldHead = head;
            ListNode last = head;
            head = head.next;
            int i = 2;
            while(i < m){
                last = last.next;
                head = head.next;
                i++;
            }
            last.next = reverseBetweenHelper(head, m, n);
            return oldHead;
        }
    }

    private static ListNode reverseBetweenHelper(ListNode head, int i, int n) {
        ListNode r = null;
        ListNode last = head;
        while(i <= n){
            ListNode p = head;
            head = head.next;
            p.next = r;
            r = p;
            i++;
        }
        last.next = head;
        return r;

    }

    /**
     * https://oj.leetcode.com/problems/restore-ip-addresses/
     * https://oj.leetcode.com/submissions/detail/11300516/
     * validate ip 0, 0 - 255, can not have digits starts with 0 unless it is single digit 0
     *
     */

    public static List<String> restoreIpAddresses(String s) {
        List<String> result = new ArrayList<String>();
        if(s == null || s.length() < 4)
            return result;
        restoreIp(result, "", s, 3);
        return result;

    }

    private static void restoreIp(List<String> result, String ip, String s, int n){
        if(s.length() == 0 || s.length() > (n+1) * 3) return;
        if(n == 0) {
            if(s.length() > 1 && s.charAt(0) == '0')
                return;
            else if(Integer.parseInt(s) >= 0 && Integer.parseInt(s) <= 255)
                result.add(ip + "." + s);

            return;
        }

        for(int i = 1; i <=3; i++){
            if(i > 1 && s.charAt(0) == '0'){
                return;
            } else if(s.length() >= i + 1 && Integer.parseInt(s.substring(0, i)) >= 0 && Integer.parseInt(s.substring(0, i)) <= 255) {
                if(ip.length() > 0)
                    restoreIp(result, ip + "." + s.substring(0, i), s.substring(i), n - 1);
                else
                    restoreIp(result, s.substring(0, i), s.substring(i), n - 1);
            }
        }

    }

    /**
     * https://oj.leetcode.com/problems/binary-tree-inorder-traversal/
     * http://www.programcreek.com/2012/12/leetcode-solution-of-binary-tree-inorder-traversal-in-java/
     * https://oj.leetcode.com/submissions/detail/11302672/
     *
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<Integer>();
        inorder(root, list);
        return list;

    }

    private static void printTreeNode(TreeNode root) {
        if(root == null) return;
        System.out.print(root.val + " ");
        printTreeNode(root.left);
        printTreeNode(root.right);
    }

    private static void inorder(TreeNode root, List<Integer> list){
        if(root == null) return;
        inorder(root.left, list);
        list.add(root.val);
        inorder(root.right, list);

    }

    /**
     * https://oj.leetcode.com/problems/unique-binary-search-trees/
     * http://www.lifeincode.net/programming/leetcode-unique-binary-search-trees-i-and-ii-java/
     * https://oj.leetcode.com/submissions/detail/11304512/
     *
     */
    public static int numTrees(int n) {
        if (n <= 0) return 0;
        if (n == 1) return 1;
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();

        map.put(0, 1);
        map.put(1, 1);
        for (int i = 2; i <= n; i++) {
            int num = 0;

            for (int j = 1; j <= i; j++) {
                int left = map.get(j - 1);
                int right = map.get(i - j);
                num += left * right;
            }
            map.put(i, num);

        }
        return map.get(n);

    }

    /**
     * https://oj.leetcode.com/problems/unique-binary-search-trees-ii/
     * http://www.lifeincode.net/programming/leetcode-unique-binary-search-trees-i-and-ii-java/
     * https://oj.leetcode.com/submissions/detail/11313452/
     * https://oj.leetcode.com/submissions/detail/11313492/
     *
     */
    public static List<TreeNode> generateTrees(int n) {
        List<TreeNode> result = new ArrayList<TreeNode>();

        if (n <= 0) {
            result.add(null);
            return result;
        }
        ;

        result.add(new TreeNode(1));
        if (n == 1) return result;

        Map<Integer, List<TreeNode>> map = new HashMap<Integer, List<TreeNode>>();
        map.put(1, result);

        for (int i = 2; i <= n; i++) {
            List<TreeNode> list = new ArrayList<TreeNode>();
            for (int j = 1; j <= i; j++) {
                List<TreeNode> temp = map.get(j - 1);
                List<TreeNode> left = new ArrayList<TreeNode>();
                if (temp != null) {
                    left.addAll(temp);
                } else {
                    left.add(null);
                }

                temp = map.get(i - j);

                List<TreeNode> right = new ArrayList<TreeNode>();

                if (temp != null) {
                    for (TreeNode t : temp) {
                        right.add(adjustTreeNode(t, j));
                    }
                } else {
                    right.add(null);
                }

                for (TreeNode l : left) {
                    for (TreeNode r : right) {
                        TreeNode node = new TreeNode(j);
                        node.left = l;
                        node.right = r;
                        list.add(node);

                    }
                }

            }
            map.put(i, list);
        }

        return map.get(n);

    }


    private static TreeNode adjustTreeNode(TreeNode n, int x) {
        if (n == null) return n;
        TreeNode root = new TreeNode(n.val + x);
        root.left = adjustTreeNode(n.left, x);
        root.right = adjustTreeNode(n.right, x);
        return root;
    }

    /**
     * https://oj.leetcode.com/problems/interleaving-string/
     * http://yucoding.blogspot.com/2013/01/leetcode-question-27-interleaving-string.html
     * https://oj.leetcode.com/submissions/detail/11435610/
     *
     */
    public static boolean isInterleave(String s1, String s2, String s3) {
        if (s1 == null || s2 == null || s3 == null)
            return false;

        int len1 = s1.length();
        int len2 = s2.length();
        int len3 = s3.length();

        if (len1 + len2 != len3)
            return false;

        if (s1.length() == 0)
            return s2.equals(s3);

        if (s2.length() == 0)
            return s1.equals(s3);

        if (s1.charAt(len1 - 1) != s3.charAt(len3 - 1) && s2.charAt(len2 - 1) != s3.charAt(len3 - 1))
            return false;

        Map<String, Boolean> map = new HashMap<String, Boolean>();
        return isInterleave(map, s1, s2, s3);

    }

    public static boolean isInterleave(Map<String, Boolean> map, String s1, String s2, String s3) {
        int len1 = s1.length();
        int len2 = s2.length();

        Boolean b = false;

        if (s1.length() == 0)
            return s2.equals(s3);

        if (s2.length() == 0)
            return s1.equals(s3);

        if (s1.charAt(0) == s3.charAt(0)) {
            String key = (len1 - 1) + "_" + len2;
            b = map.get(key);
            if (b == null) {
                b = isInterleave(map, s1.substring(1), s2, s3.substring(1));
                map.put(key, b);
            }
            if (b)
                return true;
        }

        if (s2.charAt(0) == s3.charAt(0)) {
            String key = len1 + "_" + (len2 - 1);
            b = map.get(key);
            if (b == null) {
                b = isInterleave(map, s1, s2.substring(1), s3.substring(1));
                map.put(key, b);
            }
            if (b)
                return true;
        }

        map.put(len1 + "_" + len2, false);
        return false;
    }

    /**
     * https://oj.leetcode.com/problems/validate-binary-search-tree/
     * http://www.programcreek.com/2012/12/leetcode-validate-binary-search-tree-java/
     * https://oj.leetcode.com/submissions/detail/11691349/
     *
     */
    public boolean isValidBST(TreeNode root) {
        if (root == null)
            return true;

        if (root.left != null) {
            if (root.left.val >= root.val)
                return false;
            if (max(root.left) >= root.val)
                return false;
            if (!isValidBST(root.left))
                return false;
        }

        if (root.right != null) {
            if (root.right.val <= root.val)
                return false;
            if (min(root.right) <= root.val)
                return false;
            if (!isValidBST(root.right))
                return false;
        }
        return true;
    }

    private int max(TreeNode root) {
        int max = root.val;
        while (root.right != null) {
            max = Math.max(root.right.val, max);
            root = root.right;
        }
        return max;
    }

    private int min(TreeNode root) {
        int min = root.val;
        while (root.left != null) {
            min = Math.min(root.left.val, min);
            root = root.left;
        }
        return min;
    }


    /**
     * https://oj.leetcode.com/problems/recover-binary-search-tree/
     * https://oj.leetcode.com/submissions/detail/11718530/
     *
     */
    public static void recoverTree(TreeNode root) {
        if (root == null)
            return;

        TreeNode[] nodes = new TreeNode[4];
        recoverTree(root, nodes);
        swap(nodes[2], nodes[3]);

    }

    /*
    *   nodes nodes[0] prev, nodes[1] next
    *   nodes[2] first bad node (prev > next)
    *   nodes[3] second bad node (prev > next)
    */
    private static void recoverTree(TreeNode root, TreeNode[] nodes) {
        if (root == null)
            return;

        recoverTree(root.left, nodes);

        nodes[0] = nodes[1];
        nodes[1] = root;

        if (nodes[2] == null) {
            if (nodes[0] != null && nodes[1] != null && nodes[0].val > nodes[1].val) {
                nodes[2] = nodes[0];
                nodes[3] = nodes[1];
            }
        } else {
            if (nodes[0] != null && nodes[1] != null && nodes[0].val > nodes[1].val)
                nodes[3] = nodes[1];
        }

        recoverTree(root.right, nodes);

    }

    private static void swap(TreeNode n1, TreeNode n2) {
        if (n1 != null && n2 != null) {
            int temp = n1.val;
            n1.val = n2.val;
            n2.val = temp;
        }
    }

    /**
     * https://oj.leetcode.com/problems/same-tree/
     * https://oj.leetcode.com/submissions/detail/12095539/
     *
     */
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if(p == q)
            return true;
        if(p == null && q == null)
            return true;
        if(p == null && q != null)
            return false;
        if(p != null && q == null)
            return false;
        if(p.val != q.val)
            return false;

        if(!isSameTree(p.left, q.left))
            return false;
        if(!isSameTree(p.right, q.right))
            return false;

        return true;

    }

    /**
     * https://oj.leetcode.com/problems/symmetric-tree/
     * https://oj.leetcode.com/submissions/detail/12096090/
     */
    public boolean isSymmetric(TreeNode root) {
        if(root == null)
            return true;
        return isSymmetric(root.left, root.right);

    }

    private boolean isSymmetric(TreeNode p, TreeNode q) {
        if (p == null && q == null)
            return true;

        if (!equals(p, q))
            return false;

        if (!isSymmetric(p.left, q.right))
            return false;
        if (!isSymmetric(p.right, q.left))
            return false;

        return true;

    }
    private boolean equals(TreeNode p, TreeNode q){
        if (p == null && q == null)
            return true;
        if ((p == null && q != null) || (p != null && q == null))
            return false;
        return (p.val == q.val);
    }

    /**
     * https://oj.leetcode.com/problems/binary-tree-level-order-traversal/
     * https://oj.leetcode.com/submissions/detail/12096507/
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if (root == null)
            return result;

        List<TreeNode> parents = new ArrayList<TreeNode>();
        parents.add(root);
        levelOrder(result, parents);
        return result;

    }

    private void levelOrder(List<List<Integer>> result, List<TreeNode> parents) {
        if (parents.size() == 0)
            return;

        List<Integer> list = new ArrayList<Integer>();
        List<TreeNode> children = new ArrayList<TreeNode>();
        for (TreeNode p : parents) {
            list.add(p.val);
            if (p.left != null)
                children.add(p.left);
            if (p.right != null)
                children.add(p.right);
        }
        result.add(list);
        levelOrder(result, children);
    }

    /**
     * https://oj.leetcode.com/problems/binary-tree-zigzag-level-order-traversal/
     * https://oj.leetcode.com/submissions/detail/12097056/
     */
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if (root == null)
            return result;

        List<TreeNode> parents = new ArrayList<TreeNode>();
        parents.add(root);
        zigzagLevelOrder(result, parents, true);
        return result;

    }

    private void zigzagLevelOrder(List<List<Integer>> result, List<TreeNode> parents, boolean leftToRight) {
        if (parents.isEmpty())
            return;
        ArrayList<Integer> list = new ArrayList<Integer>();
        List<TreeNode> children = new ArrayList<TreeNode>();

        for (TreeNode p : parents) {
            list.add(p.val);
            if (p.left != null)
                children.add(p.left);
            if (p.right != null)
                children.add(p.right);
        }
        if (!leftToRight)
            reverseList(list);
        result.add(list);
        zigzagLevelOrder(result, children, !leftToRight);


    }

    private void reverseList(ArrayList<Integer> list) {
        if (list.isEmpty())
            return;

        int i = 0, j = list.size() - 1;
        while (i < j) {
            int temp = list.get(i);
            list.set(i, list.get(j));
            list.set(j, temp);
            i++;
            j--;
        }

    }

    /**
     * https://oj.leetcode.com/problems/maximum-depth-of-binary-tree/
     * https://oj.leetcode.com/submissions/detail/12097436/
     */
    public int maxDepth(TreeNode root) {
        if(root == null)
            return 0;

        return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));

    }

    /**
     * https://oj.leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/
     * https://oj.leetcode.com/submissions/detail/12099693/
     */
    public static TreeNode buildTree1(int[] preorder, int[] inorder) {
        if (preorder.length == 0 || inorder.length == 0)
            return null;

        if(preorder.length != inorder.length)
            return null;

        int val = preorder[0];
        TreeNode root = new TreeNode(val);

        int index = rootIndex(val, inorder);
        if (index >= 0) {
            int[] leftPreorder = new int[index];
            int[] leftInorder = new int[index];

            for (int i = 0; i <= index - 1; i++) {
                leftPreorder[i] = preorder[i + 1];
                leftInorder[i] = inorder[i];
            }

            int[] rightPreorder = new int[preorder.length - index - 1];
            int[] rightInorder = new int[preorder.length - index - 1];

            for (int i = index + 1; i <= preorder.length - 1; i++) {
                rightPreorder[i - index - 1] = preorder[i];
                rightInorder[i - index - 1] = inorder[i];
            }

            root.left = buildTree1(leftPreorder, leftInorder);
            root.right = buildTree1(rightPreorder, rightInorder);

        }

        return root;
    }

    private static int rootIndex(int val, int[] inorder) {
        int index = 0;
        for (int i = 0; i <= inorder.length - 1; i++) {
            if (inorder[i] == val)
                return i;

        }
        return -1;
    }


    /**
     * https://oj.leetcode.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal/
     * https://oj.leetcode.com/submissions/detail/12101977/
     */
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        if (inorder == null || postorder == null)
            return null;
        if (inorder.length == 0 || postorder.length == 0)
            return null;
        if (inorder.length != postorder.length)
            return null;

        int size = postorder.length;
        int val = postorder[size - 1];
        TreeNode root = new TreeNode(val);

        int index = rootIndex(val, inorder);

        if (index >= 0) {
            int[] leftInorder = new int[index];
            int[] leftPostorder = new int[index];

            for (int i = 0; i <= index - 1; i++) {
                leftInorder[i] = inorder[i];
                leftPostorder[i] = postorder[i];
            }

            int[] rightInorder = new int[size - index - 1];
            int[] rightPostorder = new int[size - index - 1];

            for (int i = index + 1; i <= size - 1; i++) {
                rightInorder[i - index - 1] = inorder[i];
                rightPostorder[i - index - 1] = postorder[i - 1];
            }

            root.left = buildTree(leftInorder, leftPostorder);
            root.right = buildTree(rightInorder, rightPostorder);
        }

        return root;


    }

    /**
     * https://oj.leetcode.com/problems/binary-tree-level-order-traversal-ii/
     * https://oj.leetcode.com/submissions/detail/12102902/
     */
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();

        if (root == null)
            return result;

        Stack<List<Integer>> stack = new Stack<List<Integer>>();
        List<TreeNode> parents = new ArrayList<TreeNode>();
        parents.add(root);
        levelOrderBottom(stack, parents);

        while (!stack.isEmpty()) {
            result.add(stack.pop());
        }
        return result;

    }

    private void levelOrderBottom(Stack<List<Integer>> stack, List<TreeNode> parents) {
        if (parents == null || parents.isEmpty())
            return;

        List<Integer> list = new ArrayList<Integer>();
        List<TreeNode> children = new ArrayList<TreeNode>();

        for (TreeNode p : parents) {
            list.add(p.val);
            if (p.left != null)
                children.add(p.left);

            if (p.right != null)
                children.add(p.right);
        }
        stack.push(list);
        levelOrderBottom(stack, children);

    }

    /**
     * https://oj.leetcode.com/problems/convert-sorted-array-to-binary-search-tree/
     * https://oj.leetcode.com/submissions/detail/12119843/
     */
    public TreeNode sortedArrayToBST(int[] num) {
        if(num == null || num.length == 0)
            return null;
        return sortedArrayToBST(num, 0, num.length - 1);

    }

    private TreeNode sortedArrayToBST(int[] num, int start, int end) {
        if (start > end)
            return null;

        if (start < 0 || start >= num.length)
            return null;

        if (end < 0 || end >= num.length)
            return null;

        if (start == end)
            return new TreeNode(num[start]);

        int mid = start + (end - start) / 2;
        TreeNode root = new TreeNode(num[mid]);

        int leftStart = start;
        int leftEnd = mid - 1;
        int rightStart = mid + 1;
        int rightEnd = end;


        root.left = sortedArrayToBST(num, leftStart, leftEnd);
        root.right = sortedArrayToBST(num, rightStart, rightEnd);
        return root;

    }

    /**
     * https://oj.leetcode.com/problems/convert-sorted-list-to-binary-search-tree/
     * https://oj.leetcode.com/submissions/detail/12120843/
     */
    public TreeNode sortedListToBST(ListNode head) {
        if (head == null)
            return null;

        int length = 0;
        ListNode n = head;

        while (n != null) {
            n = n.next;
            length++;
        }
        return sortedListToBST(head, length);


    }

    private TreeNode sortedListToBST(ListNode head, int length) {
        if (head == null)
            return null;

        if (length <= 0)
            return null;

        if (length == 1)
            return new TreeNode(head.val);

        int mid = length / 2;

        ListNode p = head;
        ListNode q = head.next;

        for (int i = 0; i < mid - 1; i++) {
            p = q;
            q = q.next;
        }

        p.next = null;
        TreeNode root = new TreeNode(q.val);
        root.left = sortedListToBST(head, mid);
        root.right = sortedListToBST(q.next, length - mid - 1);
        return root;

    }



    /////////////////////////////////////////////

    /////////////////////////////////////////////


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

    public static int lengthOfLongestSubstring1(String s) {
        if(s == null || s.length() == 0)
            return 0;

        int longest = 1;
        char[] c = s.toCharArray();
        int i = 0, j = 1;

        for(j = 1; j < c.length; j++){
            int index = s.substring(i, j).indexOf(c[j]);
            if(index >= 0){
                if(j - i > longest)
                    longest = j - i;
                i += index + 1;

            }
        }
        return Math.max(j - i, longest);

    }


    public static double findMedianOfTwoSortedArray(int[] a, int[] b){
        int aLen = a.length;
        int bLen = b.length;

        if((aLen + bLen) % 2 == 0){
            return (kthElement(a, b, (aLen + bLen)/2 -1, 0, aLen - 1, 0, bLen - 1) + kthElement(a, b, (aLen + bLen)/2, 0, aLen -1, 0, bLen - 1)) * 0.5;

        } else{
            return kthElement(a, b, (aLen + bLen)/2, 0, aLen - 1, 0, bLen -1);
        }

    }


    /**
     *
     * http://www.programcreek.com/2012/12/leetcode-regular-expression-matching-in-java/
     */
    //Get the starting string with the same characters of the s.charAt(0)
    public static boolean isMatch1(String s, String p) {
        if (p.length() == 0)
            return s.length() == 0;

        if (p.length() == 1 || p.charAt(1) != '*') {
            if (s.length() < 1 || (p.charAt(0) != '.' && p.charAt(0) != s.charAt(0)))
                return false;
            return isMatch1(s.substring(1), p.substring(1));
        } else {
            int i = -1;
            while (i < s.length() && (i < 0 || p.charAt(0) == '.' || p.charAt(0) == s.charAt(i))) {
                if (isMatch1(s.substring(i + 1), p.substring(2)))
                    return true;
                i++;
            }
            return false;

        }
    }


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

    public Set<Result> threeSum1(int[] a) {
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

    public static ArrayList<String> generateParenthesis1(int n) {
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



    /**
     *
     * https://oj.leetcode.com/problems/remove-nth-node-from-end-of-list/
     */
    public ListNode removeNthFromEnd1(ListNode head, int n) {
        if (n <= 0) return head;

        ListNode p = head;
        ListNode q = head;

        for (int i = 0; i < n; i++) {
            if (q != null)
                q = q.next;
        }

        if (q == null) {
            head = p.next;
        } else {
            while (q.next != null) {
                p = p.next;
                q = q.next;
            }
            p.next = p.next.next;

        }
        return head;

    }

    /**
     * https://oj.leetcode.com/problems/swap-nodes-in-pairs/
     * Definition for singly-linked list.
     * public class ListNode {
     *     int val;
     *     ListNode next;
     *     ListNode(int x) {
     *         val = x;
     *         next = null;
     *     }
     * }
     *  ABCDEFGHIJKLMNOPQRSTUVWXYZ 1234567890
     *  abcdefghijklmnopqrstuvwxyz 1234567890
     */
    public ListNode swapParis(ListNode head) {
        ListNode p = head;
        while (p != null) {
            ListNode q = p.next;
            if (q == null) {
                return head;
            } else {
                int temp = p.val;
                p.val = q.val;
                q.val = temp;
                p = q.next;
            }

        }
        return head;
    }

    /**
     *
     * https://oj.leetcode.com/problems/reverse-nodes-in-k-group/
     */
    public ListNode reverseKGroup2(ListNode head, int k) {
        if (k <= 1)
            return head;

        ListNode rev = null;  //reversed list
        ListNode tail = null; //tail of the reversed list
        ListNode list = head;

        while (head != null) {
            ListNode p = head;
            ListNode q = head;
            ListNode t = head;  //tail of the reversed sub list
            ListNode r = null;

            //Check if the number of nodes is a multiple of k
            for (int i = 0; i < k - 1; i++) {
                if (head != null)
                    head = head.next;
            }

            if (head != null) {
                head = head.next;

                //Revere the sub list
                for (int i = 0; i < k; i++) {
                    p = p.next;
                    q.next = r;
                    r = q;
                    q = p;
                }
                if (rev == null)
                    rev = r;
                else
                    tail.next = r;

                t.next = null;
                tail = t;
            } else {
                if (tail != null)
                    tail.next = p;
            }

        }
        return rev == null ? list : rev;

    }

    public ListNode reverseKGroup1(ListNode head, int k) {
        if (k <= 1)
            return head;

        int count = 0;
        ListNode p = head;
        while(count < k && p != null){
            count++;
            p = p.next;
        }

        if(count < k)
            return head;

        p = head;
        ListNode q = head;
        ListNode r = null;

        for(int i = 0; i < k; i++){
            p = p.next;
            q.next = r;
            r = q;
            q = p;
        }
        head.next = reverseKGroup1(p, k);
        return r;
    }





    public static void main(String[] args){
        System.out.println("Test twoSum");
        int[] numbers = {1, 3, 5, 5, 7, 8, 9, 8, 4, 5, 3, 2, 1, 11};
        int[] e = twoSum(numbers, 20);
        System.out.println(StringUtils.arrayToString(e, 0, e.length - 1));

        numbers = new int[]{2, 7, 11, 15};
        e = twoSum(numbers, 9);
        System.out.println(StringUtils.arrayToString(e, 0, e.length - 1));

        System.out.println("Test reverseInt");
        System.out.println("Reverse 0 - " + reverse(0));
        System.out.println("Reverse 1 - " + reverse(1));
        System.out.println("Reverse 123456 - " + reverse(123456));
        System.out.println("Reverse -123456 - " + reverse(-123456));

        System.out.println("Test atoi");
        System.out.println("atoi()=" + atoi(""));
        System.out.println("atoi(  )=" + atoi(" "));
        System.out.println("atoi(12345)=" + atoi("12345"));
        System.out.println("atoi(+12345)=" + atoi("+12345"));
        System.out.println("atoi(-12345)=" + atoi("-12345"));

        long t1 = System.currentTimeMillis();
        System.out.println(lengthOfLongestSubstring1("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCD"));
        long t2 = System.currentTimeMillis();
        System.out.println("t2 - t1=" + (t2 - t1));

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
        Set<String> results = longestPalindromSubStrings1(s);
        StringUtils.printSet(results);

        s = "abccbddbb";
        System.out.println(expend(s, 2, 3));

        System.out.println("Test longestPalindroms");
        results = longestPalindromSubStrings1(s);
        StringUtils.printSet(results);

        int[] a = {};
        int[] b = {1, 2, 3, 4, 5};
        System.out.println(kthElement(a, b, 0, 0, 1, 0, b.length - 1));
        System.out.println(kthElement(a, b, 1, 0, a.length - 1, 0, b.length - 1));
        System.out.println(kthElement(a, b, 2, 0, a.length - 1, 0, b.length - 1));
        System.out.println(kthElement(a, b, 3, 0, a.length - 1, 0, b.length - 1));
        System.out.println(kthElement(a, b, 4, 0, a.length - 1, 0, b.length - 1));

        a = new int[]{1, 2, 4, 7, 9, 10, 13, 17};
        b = new int[] {1, 2, 3, 8, 8, 11, 12};
        System.out.println(kthElement(a, b, 0, 0, a.length - 1, 0, b.length - 1));
        System.out.println(kthElement(a, b, 3, 0, a.length - 1, 0, b.length - 1));
        System.out.println(kthElement(a, b, 0, 0, a.length - 1, 0, b.length - 1));
        System.out.println(kthElement(a, b, 1, 0, a.length - 1, 0, b.length - 1));
        System.out.println(kthElement(a, b, 2, 0, a.length - 1, 0, b.length - 1));
        System.out.println(kthElement(a, b, 3, 0, a.length - 1, 0, b.length - 1));
        System.out.println(kthElement(a, b, 4, 0, a.length - 1, 0, b.length - 1));
        System.out.println(kthElement(a, b, 5, 0, a.length - 1, 0, b.length - 1));
        System.out.println(kthElement(a, b, 6, 0, a.length - 1, 0, b.length - 1));
        System.out.println(kthElement(a, b, 7, 0, a.length - 1, 0, b.length - 1));
        System.out.println(kthElement(a, b, 8, 0, a.length - 1, 0, b.length - 1));
        System.out.println(kthElement(a, b, 9, 0, a.length - 1, 0, b.length - 1));
        System.out.println(kthElement(a, b, 10, 0, a.length - 1, 0, b.length - 1));
        System.out.println(kthElement(a, b, 11, 0, a.length - 1, 0, b.length - 1));
        System.out.println(kthElement(a, b, 12, 0, a.length - 1, 0, b.length - 1));
        System.out.println(kthElement(a, b, 13, 0, a.length - 1, 0, b.length - 1));
        System.out.println(kthElement(a, b, 14, 0, a.length - 1, 0, b.length - 1));

        System.out.println("Test findMedianOfTwoSortedArray");
        System.out.println(findMedianOfTwoSortedArray(a, b));

        a = new int[]{1, 2, 3, 4, 5, 6};
        b = new int[] {7, 8, 9, 10, 11, 12, 13, 14, 15, 16};
        System.out.println(kthElement(a, b, 0, 0, a.length - 1, 0, b.length - 1));
        System.out.println(kthElement(a, b, 3, 0, a.length - 1, 0, b.length - 1));
        System.out.println(kthElement(a, b, 0, 0, a.length - 1, 0, b.length - 1));
        System.out.println(kthElement(a, b, 1, 0, a.length - 1, 0, b.length - 1));
        System.out.println(kthElement(a, b, 2, 0, a.length - 1, 0, b.length - 1));
        System.out.println(kthElement(a, b, 3, 0, a.length - 1, 0, b.length - 1));
        System.out.println(kthElement(a, b, 4, 0, a.length - 1, 0, b.length - 1));
        System.out.println(kthElement(a, b, 5, 0, a.length - 1, 0, b.length - 1));
        System.out.println(kthElement(a, b, 6, 0, a.length - 1, 0, b.length - 1));
        System.out.println(kthElement(a, b, 7, 0, a.length - 1, 0, b.length - 1));
        System.out.println(kthElement(a, b, 8, 0, a.length - 1, 0, b.length - 1));
        System.out.println(kthElement(a, b, 9, 0, a.length - 1, 0, b.length - 1));
        System.out.println(kthElement(a, b, 10, 0, a.length - 1, 0, b.length - 1));
        System.out.println(kthElement(a, b, 11, 0, a.length - 1, 0, b.length - 1));
        System.out.println(kthElement(a, b, 12, 0, a.length - 1, 0, b.length - 1));
        System.out.println(kthElement(a, b, 13, 0, a.length - 1, 0, b.length - 1));
        System.out.println(kthElement(a, b, 14, 0, a.length - 1, 0, b.length - 1));

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
        System.out.println(isMatch("a", "ab*"));


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


        List<String> parenthesis = generateParenthesis(3);
        for(String p : parenthesis){
            System.out.print(p + "|");
        }

        System.out.println("Test longestCommonPrefix");
        String[] strs = {"abca", "abc"};
        System.out.println(longestCommonPrefix(strs));

        strs = new String[] {"a", "a", "a"};
        System.out.println(longestCommonPrefix(strs));

        strs = new String[] {"aa", "ab"};
        System.out.println(longestCommonPrefix(strs));

        strs = new String[] {"a", "b", "a"};
        System.out.println(longestCommonPrefix(strs));


        strs = new String[] {"a", "ab", "ac"};
        System.out.println(longestCommonPrefix(strs));


        strs = new String[] {"abcde", "abcde", "abcde"};
        System.out.println(longestCommonPrefix(strs));

        strs = new String[] {"abcdefg", "abc", "abckilmko"};
        System.out.println(longestCommonPrefix(strs));

        System.out.println("test removeDuplicates");
        int[] A = {1, 2};
        removeDuplicates(A);
        StringUtils.printArray(A);


        System.out.println("Test threeSumClosest");
        int[] num = {1, 1, 1, 0};
        System.out.println("threeCumCloset - " + threeSumClosest(num, -100));

        System.out.println("test strStr");


        String hay =    "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
        String needle = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaab";

        long s1 = System.currentTimeMillis();
        System.out.println(strStr(hay, needle));
        long s2 = System.currentTimeMillis();

        System.out.println("strStr took " + (s2 - s1));

        s1 = System.currentTimeMillis();
        System.out.println(strStr2(hay, needle));
        s2 = System.currentTimeMillis();

        System.out.println("strStr2 took " + (s2 - s1));

        System.out.println("test divid");
        s1 = System.currentTimeMillis();
        System.out.println(divide(-1010369383, -2147483648));
        s2 = System.currentTimeMillis();
        System.out.println("divid took " + (s2 - s1));


        s1 = System.currentTimeMillis();
        System.out.println(divide(-2147483648, -2));
        s2 = System.currentTimeMillis();
        System.out.println("divid took " + (s2 - s1));

        s1 = System.currentTimeMillis();
        System.out.println(divide(1, 1));
        s2 = System.currentTimeMillis();
        System.out.println("divid took " + (s2 - s1));

        s1 = System.currentTimeMillis();
        System.out.println(divide(-1, 1));
        s2 = System.currentTimeMillis();
        System.out.println("divid took " + (s2 - s1));

        s1 = System.currentTimeMillis();
        System.out.println(divide(1, -1));
        s2 = System.currentTimeMillis();
        System.out.println("divid took " + (s2 - s1));

        System.out.println("Test permute");
        String[] L = {};
        Set<String> result = new HashSet<String>();
        permute(result, L, "", 0, 0);
        StringUtils.printSet(result);

        L = new String[]{"foo"};
        result = new HashSet<String>();
        permute(result, L, "", 0, 1);
        StringUtils.printSet(result);

        L = new String[]{"foo", "bar"};
        result = new HashSet<String>();
        permute(result, L, "", 0, 2);
        StringUtils.printSet(result);

        L = new String[]{"foo", "bar", "car"};
        result = new HashSet<String>();
        permute(result, L, "", 0, 3);
        StringUtils.printSet(result);


        String S = "pjzkrkevzztxductzzxmxsvwjkxpvukmfjywwetvfnujhweiybwvvsrfequzkhossmootkmyxgjgfordrpapjuunmqnxxdrqrfgkrsjqbszgiqlcfnrpjlcwdrvbumtotzylshdvccdmsqoadfrpsvnwpizlwszrtyclhgilklydbmfhuywotjmktnwrfvizvnmfvvqfiokkdprznnnjycttprkxpuykhmpchiksyucbmtabiqkisgbhxngmhezrrqvayfsxauampdpxtafniiwfvdufhtwajrbkxtjzqjnfocdhekumttuqwovfjrgulhekcpjszyynadxhnttgmnxkduqmmyhzfnjhducesctufqbumxbamalqudeibljgbspeotkgvddcwgxidaiqcvgwykhbysjzlzfbupkqunuqtraxrlptivshhbihtsigtpipguhbhctcvubnhqipncyxfjebdnjyetnlnvmuxhzsdahkrscewabejifmxombiamxvauuitoltyymsarqcuuoezcbqpdaprxmsrickwpgwpsoplhugbikbkotzrtqkscekkgwjycfnvwfgdzogjzjvpcvixnsqsxacfwndzvrwrycwxrcismdhqapoojegggkocyrdtkzmiekhxoppctytvphjynrhtcvxcobxbcjjivtfjiwmduhzjokkbctweqtigwfhzorjlkpuuliaipbtfldinyetoybvugevwvhhhweejogrghllsouipabfafcxnhukcbtmxzshoyyufjhzadhrelweszbfgwpkzlwxkogyogutscvuhcllphshivnoteztpxsaoaacgxyaztuixhunrowzljqfqrahosheukhahhbiaxqzfmmwcjxountkevsvpbzjnilwpoermxrtlfroqoclexxisrdhvfsindffslyekrzwzqkpeocilatftymodgztjgybtyheqgcpwogdcjlnlesefgvimwbxcbzvaibspdjnrpqtyeilkcspknyylbwndvkffmzuriilxagyerjptbgeqgebiaqnvdubrtxibhvakcyotkfonmseszhczapxdlauexehhaireihxsplgdgmxfvaevrbadbwjbdrkfbbjjkgcztkcbwagtcnrtqryuqixtzhaakjlurnumzyovawrcjiwabuwretmdamfkxrgqgcdgbrdbnugzecbgyxxdqmisaqcyjkqrntxqmdrczxbebemcblftxplafnyoxqimkhcykwamvdsxjezkpgdpvopddptdfbprjustquhlazkjfluxrzopqdstulybnqvyknrchbphcarknnhhovweaqawdyxsqsqahkepluypwrzjegqtdoxfgzdkydeoxvrfhxusrujnmjzqrrlxglcmkiykldbiasnhrjbjekystzilrwkzhontwmehrfsrzfaqrbbxncphbzuuxeteshyrveamjsfiaharkcqxefghgceeixkdgkuboupxnwhnfigpkwnqdvzlydpidcljmflbccarbiegsmweklwngvygbqpescpeichmfidgsjmkvkofvkuehsmkkbocgejoiqcnafvuokelwuqsgkyoekaroptuvekfvmtxtqshcwsztkrzwrpabqrrhnlerxjojemcxel";
        L = new String[]{"dhvf","sind","ffsl"}; //,"yekr","zwzq","kpeo","cila","tfty","modg"};//,"ztjg","ybty","heqg","cpwo","gdcj","lnle","sefg","vimw","bxcb"};

        result = new HashSet<String>();
        permute(result, L, "", 0, L.length);

        StringUtils.printSet(result);
        System.out.println(result.size());

        S = "abababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababab";
        L = new String[]{"ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba"};

        S = "abababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababab";
        L = new String[]{"ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba"};

//        S = "barfoothefoobarman";
//        L = new String[]{"foo", "bar"};

//        S = "aaa";
//        L = new String[]{"a","a"};
        s1 = System.currentTimeMillis();

        List<Integer> list = findSubstring(S, L);
        s2 = System.currentTimeMillis();
        System.out.println("findSubstring took " + (s2 - s1));
        StringUtils.printList(list);

        int[] nums = {2, 3, 1};
        nextPermutation(nums);
        StringUtils.printArray(nums);

        System.out.println("Test longestValidParentheses");
        String p = "(()";
        System.out.println(longestValidParentheses(p));

        p = "";
        System.out.println(longestValidParentheses(p));

        p = "()";
        System.out.println(longestValidParentheses(p));

        p = "()()";
        System.out.println(longestValidParentheses(p));

        p = ")()())";
        System.out.println(longestValidParentheses(p));


        p = "()(()";
        System.out.println(longestValidParentheses(p));

        p = ")()(((())))(";
        System.out.println(longestValidParentheses(p));

        searchRange(new int[]{2,2}, 2);

        char[][] board = {  ".87654321".toCharArray(),
                            "2........".toCharArray(),
                            "3........".toCharArray(),
                            "4........".toCharArray(),
                            "5........".toCharArray(),
                            "6........".toCharArray(),
                            "7........".toCharArray(),
                            "8........".toCharArray(),
                            "9........".toCharArray()};
        System.out.println(isValidSudoku(board));


        System.out.println("Test combineSum");
        int[] candidates = new int[]{1, 2};
        int target = 3;
        List<List<Integer>> r = combinationSum(candidates, target);
        System.out.println(r);


        candidates = new int[]{1, 1};
        target = 1;
        r = combinationSum2(candidates, target);
        System.out.println(r);



        System.out.println("test multiply");
        //System.out.println(add("0", "0"));
        System.out.println(multiply("0", "0"));

//
        System.out.println("test isMatch2");
        s1  = System.currentTimeMillis();
        //System.out.println(isMatch2("babbbbaabababaabbababaababaabbaabababbaaababbababaaaaaabbabaaaabababbabbababbbaaaababbbabbbbbbbbbbaabbb", "b**bb**a**bba*b**a*bbb**aba***babbb*aa****aabb*bbb***a"));
        //System.out.println(isMatch4("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", "*aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa*"));

        System.out.println(isMatch4("abcdefaaahicg", "ab*aaaa"));
        s2 = System.currentTimeMillis();
        System.out.println("s2 - s1 = " + (s2 - s1));

        System.out.println("Test permute");
        int[] num2 = new int[]{0, 1};
        List<List<Integer>> list2 = permute(num2);



        num2 = new int[] {2,2,1,1};
        list2 = permuteUnique2(num2);
        System.out.println("End");


        System.out.println("Test isAnagrams");
        String[] strs1 = {"nozzle","punjabi","waterlogged","imprison","crux","numismatists","sultans","rambles","deprecating","aware","outfield","marlborough","guardrooms","roast","wattage","shortcuts","confidential","reprint","foxtrot","dispossession","floodgate","unfriendliest","semimonthlies","dwellers","walkways","wastrels","dippers","engrossing","undertakings","unforeseen","oscilloscopes","pioneers","geller","neglects","cultivates","mantegna","elicit","couriered","shielded","shrew","heartening","lucks","teammates","jewishness","documentaries","subliming","sultan","redo","recopy","flippancy","rothko","conductor","e","carolingian","outmanoeuvres","gewgaw","saki","sarah","snooping","hakka","highness","mewling","spender","blockhead","detonated","cognac","congaing","prissy","loathes","bluebell","involuntary","aping","sadly","jiving","buffalo","chided","instalment","boon","ashikaga","enigmas","recommenced","snell","parsley","buns","abracadabra","forewomen","persecuted","carsick","janitorial","neonate","expeditiously","porterhouse","bussed","charm","tinseled","pencils","inherits","crew","estimate","blacktop","mythologists","essequibo","dusky","fends","pithily","positively","participants","brew","tows","pentathlon","misdiagnoses","paraphrase","telephoning","engining","anglo","duisburg","shorthorns","physical","enquiries","grudging","floodlit","safflower","asphalts","representing","airbrush","bedevilling","fulminations","peacefuller","hurl","unequalled","wiser","vinson","paglia","doggones","optimist","rulering","katmandu","flutists","sterling","oregonians","boosts","slaver","straightedges","stendhal","defaulters","stylize","chucking","adulterate","partaking","omelettes","monochrome","bitched","foxhound","tapir","vocalizing","manifolding","northerner","ineptly","dunce","matchbook","locutions","docudrama","sinkers","paralegal","sip","maliced","lechers","zippy","tillman","penknives","olympias","designates","mossiest","leanne","lavishing","understate","underwriting","showered","belittle","propounded","gristly","toxicity","trike","baudelaire","sheers","annmarie","poultices","therapeutics","inputs","bailed","minutest","pynchon","jinx","jackets","subsections","harmonizes","caesareans","freshened","haring","disruption","buckle","per","pined","solemnity","recombined","chamber","tangling","pitiful","authoritarians","oort","ingratiate","refreshed","bavarian","generically","rescheduled","typewritten","level","magnetism","socialists","oligocene","resentful","lambast","counteroffer","firefight","phil","attenuates","teary","demarcated","moralities","electrified","pettiness","unpacking","hungary","heavies","tenancies","tirade","solaria","scarcity","prettiest","carrillo","yodel","cantilever","ridiculously","tagalog","schismatics","ossification","hezbollah","downscaling","calking","tapped","girl","alba","lavishness","stepparents","integrator","overact","father","fobbing","pb","require","toes","sweats","prisoners","mbabane","hatch","motleyer","worlds","decentralize","ingrained","shekels","directorship","negotiating","hiawatha","busying","reciprocate","spinsterhood","supervened","scrimmage","decolonized","buildups","sedative","swats","despotic","driblets","redoubting","stoic","xeroxes","satellited","exteriors","deregulates","lawful","flunk","broached","energetics","moodily","popinjays","shoshone","misleads","abduct","nonevent","flees","harry","cleverness","manipulative","shoplifts","tom","junk","poniard","transmute","stricter","trochees","snack","relations","edger","culminate","implication","carjacked","kissers","federate","offsetting","sutures","wakened","axis","boxcars","grinds","scenting","cordoba","lumberyards","incendiary","antiphonal","decipherable","gilliam","redecorates","plum","nitpickers","linefeed","awakes","embittering","spewing","annul","filial","scarlet","connors","sanctum","scotsman","isobar","activity","overthrowing","unseasoned","tarantulae","outtake","diego","mars","stunted","hunted","sublimation","barbadian","barbarisms","epidemic","assesses","imposture","freaks","detroit","bloodiest","avails","prenatal","connecticut","guardsmen","betwixt","windsock","neutralized","psychoanalysis","rubberized","overproduces","narcissism","tallow","cringes","resinous","paintbrushes","duality","paints","deactivated","expertly","speedsters","coward","bass","psychiatrist","curies","betrays","bubble","mellow","showy","retarding","radishes","coy","unreservedly","larks","apportioned","flaccid","relabelling","alphabeted","anointment","helms","gillian","trophying","breakage","underbrush","directest","wingtips","pretence","preshrink","remarries","addle","brouhaha","mobbing","g","dings","gains","stockade","ouch","particulates","listens","habituation","kill","crouped","hyperbolae","hutching","stoney","rightness","davids","questioned","ethiopians","courtliness","delays","navahos","devils","keeling","accelerators","investigator","spindling","illegality","extremer","revlon","purity","bradly","jut","machs","liquidated","informant","smartly","disfigure","parliaments","croup","teletypes","impression","trainee","implications","embed","podiatrists","jewelled","brokenhearted","spaceman","unsteadier","kitchen","twirling","conurbations","pygmies","lourdes","watertight","reassessing","dempsey","matriarch","alas","abscissae","decanter","commentated","sandy","idler","soybean","cutoff","dictate","credibility","indeterminable","release","blank","curitiba","pericardia","probably","indisposition","hesitantly","duff","ratty","derivative","decals","explication","cockier","monoxides","hyperventilate","genially","polluter","divan","may","convalesces","morpheme","pupa","prospered","tagging","nerdier","detached","spearing","hilbert","russeted","amanuensis","periwinkles","jujube","guarantors","premises","descanting","baned","deviance","sidearms","lamentable","barristers","climes","succulence","mauve","oceanographers","migraine","bisexual","peruvians","fatheads","parsimony","pilaf","portly","conniving","insidiously","inventing","constabulary","cling","stunting","accessioned","deadliness","overthrow","expectorant","agamemnon","blindfold","striker","shrugging","jibes","appropriateness","annihilates","hairs","proselytes","goads","rankling","predominated","hart","enemies","culpability","drank","martinets","prospering","dominick","complemented","invention","foolscap","tolerances","lorelei","profits","awarer","ungodlier","victoriously","mistrusts","princes","drudge","moderator","transversed","disco","japed","loomed","incense","ponds","gumbel","disarranges","coaxes","technology","hyde","debentures","confidantes","hankered","savant","styes","croupy","unapproachable","cisterns","unto","duds","conglomerating","clio","negroid","looked","methodism","hilario","balloon","thesauruses","integuments","thermometer","slacks","wigwagging","gaping","incensed","misquotes","chocking","patrols","upcoming","insists","livings","thoth","uselessness","vibrated","potluck","starboard","uniquer","boone","scintillates","darker","massey","arbitrariness","miniaturized","rousseau","chiffon","consortia","coral","finesses","half","biked","unlikeliest","hilarious","acrid","twinkles","galileo","outsmarted","ostentation","cradle","frats","misidentifies","uncleaner","bacardi","smoothest","antwan","warren","jingling","stocks","daumier","paranoid","pantaloons","dishing","receive","underpays","kane","variation","beset","disobliged","dreadlocks","psychics","twofers","lieutenants","pebbling","interposes","shingles","profanes","machining","dysfunctions","wolfram","brut","nebraskan","truculently","copeland","devonian","fuller","silvia","philosophers","cali","adores","disquiet","savvies","minatory","blooms","radiotelephones","paradoxically","competitions","gandhi","weddell","occludes","retracing","kari","dead","lagoons","menfolk","abundant","enacts","conferences","procreation","steadier","cols","rabble","unquestioned","stupefying","whodunit","dizzier","paula","riposte","elixirs","discontented","zimbabweans","assemblage","unsalted","genders","caldwell","pulleys","pureness","kingston","vests","hearken","abuses","scull","hussar","solace","gondwanaland","surfacing","vivienne","subculture","reciprocal","expediencies","projectiles","segregationist","prickle","pooped","telecommutes","axes","scenery","peppery","parenthesizing","checked","trademarked","unreasonable","curtly","dynamically","vulcanize","airtight","blotch","edmund","stoicism","scrambles","whirled","chasing","millstones","helplessly","permalloy","remanding","duplicate","broadsided","readjustment","buggers","quaked","grapples","democrats","landfalls","apprehensively","turmoiling","railing","lothario","modishly","faggoted","deflecting","interment","dodo","recreants","baywatch","frescoes","temblor","brigade","handgun","bradstreet","caspar","godsend","cochleae","queered","unevenness","hairnet","millimeters","flawless","plumbing","disciplinarian","orbiting","foothill","serviettes","peseta","windmills","myrdal","provides","slowdowns","clouting","gainsays","dishpans","mediates","weaker","shoestrings","gerunds","potsdam","chips","disqualifications","focus","quarry","dwarfs","laurels","coverall","reconsidered","exploded","distending","bronzes","apollonian","sweeper","couperin","gourmets","irreconcilable","goldbricking","emotes","demilitarizes","lambkin","grouper","anyways","hugs","quizzed","misstatement","spectrums","frigates","plenipotentiaries","parasites","tacitly","savvying","treks","dissociating","departing","resins","psychiatric","tablespoonfuls","aught","makeup","copping","interwove","selling","fantasize","flamingos","smolders","stript","laverne","extremely","chattering","imminent","vaulting","slackly","pasteurizes","goody","pearls","conceptualization","fins","brogues","muskogee","naziism","stromboli","sunflower","tosca","luridness","booing","zaniness","creel","bootblacks","attendants","swordplay","impinging","premiere","sedimentation","traffickers","carrel","observatories","telltales","cuckolded","ampler","alternately","shovel","tasmania","whooping","methodologies","pickling","overseer","sunnier","sanchez","supervening","viscus","cramped","santayana","utopias","intimated","pianists","computerizing","interpolating","reggie","horseshoe","preeminent","qantas","standish","flagpoles","thievery","admiring","palefaces","overflows","gaea","monique","sheepskin","bestiaries","beethoven","fleming","convalescing","moldier","snobby","jewry","hoodwinking","hope","henri","listlessly","doggoning","anointed","notable","talented","uric","towards","flue","arbitrated","ingredients","academy","clutches","novelle","parallelling","confabbed","synthesized","frontally","underexpose","ulcerates","injuring","stimulant","catalytic","ogle","throatily","ions","chores","spyglasses","metabolic","statesmanlike","tingles","ossifies","forge","coiffing","transepts","autopsy","colorfast","winery","procyon","sequesters","amended","putted","huff","fliers","harpooning","protecting","shipboard","dwindled","collations","stonewalls","criticism","thigh","quarried","knelling","knitted","redeemable","berm","seventy","misguides","schlemiel","pawn","ineligibility","lathe","bosses","temperance","haywire","everglade","confections","gruelings","mindful","paracelsus","quarreled","furtively","airdropped","clodhopper","transmuting","whilst","moldavia","exploiting","chicories","unrolling","shorthand","antigens","satirically","earner","primmer","jolly","perch","nonplussing","circulars","hanks","fingerprinted","syllogism","adulate","nominally","telecasted","quelled","accustoming","backslide","culminates","spiraled","compactor","gatorade","cornballs","investor","cupboards","deign","induced","ewe","snoopers","supposed","glitters","overlie","ambassadorial","chancel","accumulations","strictest","thalami","shops","moos","applicators","corncob","dona","apostrophes","kibitzes","rinses","kemerovo","confide","clemenceau","centenarians","memorialized","windburn","nominate","obscene","equivocations","arts","karloff","projected","scorned","limping","lava","sanitaria","clementine","brandies","unionize","compacted","griming","trilogies","babysit","congas","glittery","pimientos","phototypesetter","multivitamin","carbohydrates","lode","photographs","iniquity","micrometer","freemasonry","burros","marchers","percentiles","werewolf","weightlifting","valedictories","gacrux","senselessly","stoppage","monolithic","handy","overspent","nymphomaniac","seasick","misogynistic","coltrane","coeval","inversion","darkliest","landfills","barbers","suppurate","cavern","submerge","illumination","hesitates","lashes","covenants","thomism","aneurism","disappointed","gnarls","sprint","abash","frightens","undoings","pa","helicopters","reexamines","vassal","blessing","devaluation","purports","urinals","adjudged","garaging","pacific","infomercials","whitewashed","fawned","baptisms","concede","cornflakes","fostered","clewed","tiller","dalmatians","signification","boneless","chunkiness","omar","paramedicals","professor","unionizing","scripted","anchors","tabloid","alton","redrafted","reflexive","luddite","lamb","bidirectional","seaports","christendom","gets","chaperoning","tchaikovsky","wasters","dioxin","nuke","apologized","queasily","fujiwara","prearranges","abdul","upraising","sparklers","signposting","comparison","sb","cherokees","ungentlemanly","typing","waisted","sputter","biographers","waltz","stanches","upbringings","smithereens","tutor","young","eloy","sourdoughs","clingier","hoisting","blazon","homosexuality","lorries","kippering","abacus","specks","congressional","auditing","lash","eternal","carve","facade","defrauds","neighbored","musses","dismount","lope","lawbreaker","deed","japes","repeal","factorization","impetuosity","sitters","disorganizing","fussing","vale","epitomized","executrixes","deprivations","woodcarvings","miscalls","skateboard","pedicured","cloakroom","vassaled","innumerable","knelt","cellulose","beams","uniform","metatarsals","meteorologist","column","burnishes","dentists","quids","toasts","tableland","archivist","gladiolas","replica","lording","viewed","polisher","trooping","indistinctly","resisters","flycatchers","toughed","regor","insolvent","ninnies","truckled","birthplaces","telescopic","abelson","puritanism","leanings","disturbingly","transmission","mortify","upshot","newlywed","adam","ballplayer","lockwood","quirking","blocs","theatre","palliatives","smudges","marvelled","ramble","offside","indissoluble","droplets","fencing","hubbard","estimation","incorrect","malarial","confucian","games","sacraments","trivets","gammas","nastiest","merrymakers","sealskin","overkilled","bosser","strafe","exclusives","bouldered","antiwar","guitarists","jerry","earthly","oscilloscope","edmonton","merger","laminated","surmountable","casually","backspaced","charcoals","overheating","caramel","oldened","asterisked","tun","peafowl","purplest","skippering","prep","congregating","glaringly","crummiest","noreen","bromide","nomenclatures","kristin","purportedly","vamoosing","busybody","crucify","capote","milliners","veils","windsurf","reconnecting","layering","ossified","noble","tiptoeing","smiles","swain","perihelion","bagels","obfuscation","spreadsheets","buddy","flints","planting","hogwarts","abusers","welfare","mouses","lament","auras","unrelieved","cougars","cattails","chubby","handstands","woolly","concealment","mediterraneans","judas","electrocardiographs","skulks","puttered","crimean","liven","odds","warehousing","lifeguard","deepness","clowns","blossomed","constriction","honest","noisemakers","whist","overcame","sulphured","vertebras","commiseration","jolted","adjourns","bungles","sonnies","housekeeper","buddha","bolsters","warlords","banjarmasin","militated","anywheres","lula","weirdos","raymond","sections","taoisms","pay","latest","bights","carousel","sups","lavatory","conciser","lon","beefburger","clinically","snakes","backslash","developmental","squibb","smote","mastectomy","genius","sallying","niagara","guild","altai","ascetics","marts","misbehaved","desired","pagodas","platypus","freemen","lovemaking","transfers","brewing","absorbents","unions","lite","wilder","popovers","yamaha","faultier","supplements","forsythia","rummy","propagation","motorbikes","velez","unequivocally","lend","silliness","idiosyncratic","disseminated","carter","washed","dizzying","bedsore","pawned","lr","nubile","galloped","subservience","marlin","chance","schooners","faction","clutters","transmits","weathercocks","illustrations","quell","senegalese","touchiest","psychs","joshes","shallots","garrottes","coifs","glaswegian","hydrated","smirch","strutting","arnold","coughing","tangier","olympics","overexposing","benefactor","reputably","snootier","smuggles","bogus","priories","chandra","diplomatics","muskrat","forbad","monasticism","outshone","farewell","thomas","epaulets","nectarines","affording","buckles","concordance","lebesgue","pawed","lackey","sweden","confirmatory","humble","wizards","controlling","scoffing","worthy","homely","lexical","batteries","chorusing","inboard","cotton","lustrous","devalued","herbart","travestied","veneered","maxillas","omelet","ptarmigans","alnilam","submerging","bucks","niceties","yong","gender","toileting","biding","caffeine","lubricant","dashikis","balm","filings","series","paraguayan","fatefully","craggier","oversexed","milkweed","passels","concretely","rapiers","channeled","multifaceted","tenth","conflagration","pivoted","horribles","tugs","fireman","hull","semifinalist","odorous","carats","uncomfortably","clappers","chaffinches","demagnetize","limits","ups","wimp","reserved","busyness","illuminate","autocratic","zips","sculley","vainest","conciliators","vacillations","daughter","beau","stashes","mini","shallow","divided","invert","caesurae","vibrantly","germination","winching","curmudgeon","hurts","battens","lovable","redistricted","neighing","nonexistence","foghorns","manifesting","retrofitting","fictitious","gracefully","shetlands","transistors","rectitude","shea","acquisitions","predetermines","huts","blintze","cortland","murdered","leninism","ninetieths","fractal","inveighs","compelled","ringer","mistiest","snorting","transportation","dictionaries","khyber","misspelling","bifocal","playmates","disputant","neuters","epidemics","vijayawada","ambles","splashdowns","cants","bandannas","millennia","glows","fowled","cupped","laramie","occupied","gelatine","scorches","sidelights","beagling","campused","doer","gunfighting","tsitsihar","marin","fireside","yellow","seagram","strap","arrivals","sixty","lipscomb","wares","awfullest","servants","dreamer","crockery","mahavira","brad","outpourings","dusty","shrubs","briefest","segments","cartilages","excommunicated","generator","placket","berate","emulations","suspicious","fortnights","sped","griding","panting","trimaran","suspend","retract","adversity","regimental","hammett","wallflower","tyrannize","cinemascope","ambassador","humerus","litter","trying","sinkable","descents","coveralls","region","landslid","bowed","zigzagging","upholstery","parch","scratchy","altitude","angling","scaldings","plussed","babel","cirrus","haberdasher","gayle","chillest","catboat","battling","bellini","extincts","appendices","unmade","footstool","deaths","secretively","erasmus","agra","soothsayers","stillest","despoils","affiliated","mumble","aleut","gyp","purchased","kory","cesspool","interacted","demarcates","fanciness","defines","absorbed","fireplugs","pluckiest","misstates","grenades","untidiness","ventricles","overexpose","dryad","tumbler","chengdu","thistledown","dork","unauthorized","holmes","downgrading","syphilises","trammelled","sending","afflict","ornithologist","serried","much","emoluments","wilfully","strayed","canada","dachshund","compost","glamorized","gerrymandering","senate","allots","arsonists","coloratura","borderlines","cartooned","evert","continents","profiteers","merritt","cyclical","quickens","funkier","dourer","salmonellae","seemingly","cheeky","showering","proses","imperturbably","gush","intolerable","wozniak","vegetables","neglectful","aesculapius","whimsicality","unfolds","conceited","junkyards","immanent","norbert","pollsters","ruse","gymnosperms","toothbrush","accommodate","multitudinous","blessedest","squeezers","portal","newspaperwoman","polytheistic","affectioning","rhiannon","holing","johanna","gregariousness","fishtail","tainting","wasteful","aeneas","flycatcher","salem","ventilator","sat","hiroshima","breed","housewarming","migrate","smocking","plethora","fathead","mussy","communion","foxhole","renters","telecommunications","obtusest","puncture","preparing","encyclopaedias","footholds","violets","megalomaniac","dakotas","accentuating","joanna","gentleman","dardanelles","aeration","gerrymander","liker","chatters","questioningly","guesswork","lunar","upgrading","mara","consultancy","sanguines","sunks","polyethylene","injected","aneurysms","caterwaul","eventfully","aquariums","yowl","valeria","suicides","emptiness","pajamas","uphill","myles","playfully","palestine","agape","chichi","vatican","exponentiation","shipper","planes","reprints","dieters","involving","dimwitted","cranach","homestretch","fitfully","fluoridation","esthetics","coworkers","cannibalize","lipids","heartsick","flatware","rage","hazier","overburdening","psychosis","wage","debasements","personalized","itaipu","valletta","rhythm","cyclic","hillocks","badlands","proffer","cretin","sexuality","hefts","rehabilitate","disfranchisement","skews","rundowns","unrolls","dividing","boomerang","gigglier","respects","layette","heads","devised","franny","therapists","ballsy","inkier","passer","capone","housebreak","rachel","portered","palpate","bugles","marionettes","werewolves","primitive","powerboat","polio","wylie","industrial","convenes","sweep","wear","peaks","trudging","manuring","yttrium","hometown","graving","latency","warrant","tinsmiths","preheats","withered","priestley","dipper","overtakes","thurber","softwood","renounced","turfed","immensely","pilgrim","neophyte","mendicant","trampolining","detachable","corralling","haydn","victimizing","pockmarks","goya","triumphs","sleet","whiplashes","inconsistent","slaked","handled","nissan","housecleaned","gentling","alexandra","dallying","kiddos","rayleigh","oxen","boarders","demonstrator","mollusk","micawber","earthshaking","burials","consecration","algeria","sinister","bloodcurdling","garrotted","byte","dungeon","ferrets","trunk","finery","dodos","staid","champagnes","gelt","hedonists","barrier","trikes","lock","scaly","hibernation","lopsidedly","snake","ellison","runabout","rhapsodizing","divinity","bugged","entomological","kazoo","powdered","smeared","shoestring","knocker","galbraith","clomp","rudders","infused","sale","silicone","underfoot","chirruped","beatific","preached","commingles","enchanters","nonflammable","vacillating","autistic","uganda","intruding","expatiating","coddles","attendant","races","paying","confuting","guyed","expanded","tolstoy","contestants","judd","comediennes","pansy","upstage","schoolmistresses","lesion","rollback","rapt","sicked","locomotion","flint","interactions","vaginae","intonation","comedic","totaled","sickles","immensities","leastwise","brothers","rinking","worksheet","impending","mussed","reinterpretation","backtrack","thrummed","devastate","plaiding","accusatory","rafts","stew","infringe","impulses","shuffleboard","decaffeinates","marquez","milksop","artisan","attlee","decide","italic","catalyst","squeamish","transmuted","coffer","sadness","forays","slipperier","bella","dandy","singletons","inflating","pittance","shearer","spilling","acid","mortgager","proverbs","wily","headwinds","murmured","humidified","cramping","herrick","phoneticians","boas","bucksaw","play","falters","overbites","dill","renovators","mush","clockworks","diaspora","favorites","stomachaches","flinch","boston","scrounges","hubert","utters","million","weekended","touchdown","reactor","postcards","brown","snide","banjoist","recourse","deuced","scurrying","chihuahuas","abutments","proliferate","carillonned","saucier","kaposi","amanda","fillet","demographics","eldon","skyed","abut","bernadette","downplays","alluvia","trappers","insofar","breathlessness","insistently","imitative","cede","embargoed","boisterousness","edicts","braise","impacted","ukrainian","lankiest","bounden","trilling","pinnate","unconvincing","kent","indulging","stagnant","piston","cookery","commits","caucasoid","fronds","bootless","clustered","giggled","lugubriousness","intents","elms","boorishly","stalemate","blaine","proportionality","sampson","ethnologists","francisco","headmistress","propel","knapsack","misapplied","ensnare","subtotal","intersected","maud","shooing","pouts","messed","schmidt","liberated","continuations","impossible","unsatisfied","flanneling","unscrupulous","comity","scopes","incised","venial","takes","auction","bashfully","bremen","televangelists","eyesight","ineptness","hecate","sweller","carrot","presidency","hook","nerveless","mastication","abstractnesses","precognition","indent","sombre","fieriness","quiescent","indispositions","shiftiness","caveatted","gulps","best","spread","chews","prevaricating","shoon","manicures","privier","yawns","surnames","solacing","tattering","contrail","downsizing","unnerve","avenger","misanthropists","retiring","roughest","canard","dowdiness","corinne","hilton","summon","whacky","luncheonettes","morals","hunches","ibices","waded","meany","valving","articulations","typefaces","born","wretches","reflexes","tickled","showier","reddest","scallop","rethought","registries","behaves","abnegates","sharping","helm","rapscallions","manifestation","elected","mulligan","unmanageable","exude","centerfolds","unbind","enshrine","skeptically","pained","yeah","frostbiting","nonessential","bakers","exorcist","tonalities","timmy","provisional","bugatti","whisper","nonfiction","snippet","quest","jabberer","mittens","metatarsal","quotient","sponsorship","ferociously","brig","meditation","scotch","mess","saith","appropriately","smithson","emacs","bookending","misses","warmonger","subvert","pretties","ficklest","inaugurated","teetotal","shipmate","irony","canaan","jeopardized","lanes","sloan","connexion","ideals","uprising","sited","lamebrains","patchwork","vocalizes","maura","taller","patronage","barbarians","midshipmen","granddaughter","grapefruits","sideways","mistreating","pensive","foreseen","manageability","lammer","affiliation","aguilar","aunts","ghats","tricycles","privileged","athlete","ls","colonnades","constrictors","stickied","ritualism","catharses","scrappiest","almoravid","tiaras","habituate","merino","witches","handbag","steamroll","conics","marion","forte","rectangles","australoid","gaborone","invidiously","fugues","nervelessly","p","plunderers","newspaperwomen","embolism","splendider","lathes","pleistocene","acceleration","caterers","hauled","fluent","severus","dubbed","rollicked","cal","hastily","scourged","homogenization","leakages","teaches","vocabulary","interpolate","bacterium","ambiguous","virtual","flory","apostrophe","bellamy","lushest","misconstrued","obsessives","romanticizes","feeblest","encodes","anthropologists","clinked","hammerstein","duckbill","expurgations","coroner","unclasping","inconsistently","default","skullcaps","inverses","toadstool","raga","cristina","malady","nonrenewable","zonal","suva","trothed","clarifies","moravia","waggles","flyspeck","blanker","overstays","admired","speckles","fumigates","languished","dieseled","president","tuition","escapees","meyerbeer","futilely","pills","afghans","homeboys","blankness","hackles","footman","spies","horsed","arctics","sunder","mcluhan","secures","withdrawn","bellicose","quibbles","chastens","episcopate","shenanigans","salinity","mending","handsome","tweed","ladle","condorcet","metric","tattletale","rashly","nurtures","contemplation","tucker","moroni","nickolas","mcclellan","phonics","copyright","overwhelms","swahilis","chessmen","amusingly","conjoint","abstrusest","canonicals","ono","callouses","jiggled","typecast","memoranda","tuxes","stalwart","ennobling","nuked","gaggle","followings","beeper","hacksawing","acclimatize","orient","declaims","rising","enamor","doxologies","waistcoats","gustatory","kitchening","overpower","subordination","bone","ricking","yenisei","harvesters","junior","elector","simulcasted","clarinettists","modulators","happily","critic","damage","ineffectual","arbitrates","portrayed","animists","monasteries","abductor","dissolve","befuddled","emergence","laments","naturally","ankle","glastonbury","woodpile","zoroastrian","near","orneriest","temperated","icon","stiffed","penis","reapplied","getting","specializes","bushy","complaisant","slocum","unreadier","stiffest","fives","clumsiest","engineers","plumped","waistline","judgeship","cryptographers","bordello","babar","monthly","possiblest","libeler","cartwheeling","stiffer","monickers","digraphs","salinger","carping","wrigglers","assyrians","sprawls","visits","saunaed","preambling","flairs","obtuse","unbounded","septuagenarians","syllabi","roulette","conjure","clones","aristocratic","orleans","discountenancing","primordials","expropriations","patellas","prurient","walloon","electroencephalographs","contribution","dwarfed","caryatides","billy","presumed","gnarly","chinchilla","shinning","gripped","sigurd","ruff","circumcising","shriveled","cysts","bernstein","storming","calibrations","suffuse","stodginess","brownian","colorblind","aberration","cornered","droppers","heartbreaks","becker","suffusion","ambassadorships","souses","arizonian","oarlocks","accountants","elephants","surer","citadels","betroths","gentian","individualism","venezuelan","tour","rancorously","cabral","constants","parochialism","fustian","coxswains","ironing","rusted","babbling","inapplicable","sedans","leukocyte","opaqueness","bluffs","tuneless","capsized","brochures","severance","hyperventilated","len","shortstop","adoringly","damming","credits","accentuates","lurch","illegitimacy","martens","bleeders","rankle","mortarboard","unify","remark","thermometers","provoked","bittersweet","dame","clarinets","workers","misinterpreted","surveillance","thickset","assaulted","intervening","swede","rustbelt","suns","gasoline","clifton","energized","indiscreetly","soundless","notepad","uneaten","cicatrixes","rhythms","inimitably","subjectively","drifter","snowdrop","moonstone","olga","sabled","eightieths","yonder","inaccurately","madams","flautist","surrealist","persimmons","davenport","retinues","markets","cursing","femora","sharpers","fishermen","cores","clarifying","lakes","sake","sync","impinged","smartness","retarded","grubby","okayed","rustles","croupiers","civilizing","suffocatings","blackmails","recessives","substantives","profitable","delicatessens","familiars","terence","frumpier","maximums","shiners","regals","stieglitz","newsagents","confirmation","inebriation","alva","snowplowing","crudity","tams","gobs","sourpusses","pursuant","busts","angrily","betaking","subsumed","uniformity","mote","censuring","sir","sifting","yachts","sexists","orientation","forewarned","brigs","gyro","knickknacks","retrains","klondiked","nonpluses","contemplative","sears","trammed","serializes","brigands","stratified","burial","sumter","malplaquet","upholsters","parenthesized","dinkies","classy","jugulars","contradicts","sunup","terracing","bulletined","postponements","teenier","afterburners","greenback","dependants","hardy","unbelievers","surrealistic","chaises","snapple","dowry","writhes","expurgates","magically","toolkit","boondoggled","timorously","fuselages","corine","deploying","neckerchiefs","oz","sequoya","waives","dives","reprocessing","clearly","discontinuations","mezzanines","antibiotic","likeness","squabbles","navigable","temples","described","libretto","tanking","perilous","drowsiness","pilferers","beakers","ingram","prevaricate","voiced","procrastinated","vanilla","salween","han","marquises","bewails","cocks","ledger","panty","dupont","typewriter","blackfoot","possession","conglomerated","collects","escutcheon","titting","condiments","smouldered","marquees","interjected","element","outlet","briefing","liquidator","shrieks","nanking","salting","reopens","glasgow","arabians","transgressing","reasoning","bleated","bearable","criticize","whereat","bureaus","dismounts","roxie","tussled","yeomen","equine","cyclist","hugely","felted","messy","desist","shenandoah","sushi","storeroom","hindquarter","opposed","benevolently","archives","luaus","diskette","chiefer","hangover","sparta","faxed","hedge","eddington","pins","bushiest","loco","rooking","lifer","trespassed","swears","husked","darrell","completed","shirring","interactively","places","injured","stolid","secondly","explores","committees","airwaves","littler","sprawling","hydroplaned","uglied","depended","mortared","gloaming","shinbones","outlaw","proselytize","moralling","worn","extricates","exclamatory","layperson","frolicsome","conjugates","arrest","guttural","paycheck","phyllis","alphonse","drumming","multiply","wed","actuating","sidled","sapient","fingers","wantonness","habitually","emulsion","handwork","indeterminate","prig","pareto","wartime","nuthatches","squalider","selflessness","chip","uvulars","debonair","replenishing","naughtiest","renounce","anemic","schoolgirl","dilation","slipping","tape","ginning","crucifixions","indecisiveness","formidably","pokes","resigned","chitchatted","holograms","coppices","dialects","vicissitudes","penologist","shirtwaist","heeds","hybridizes","calliope","likable","adequately","wight","heehawed","exhibits","undressing","seditious","collapses","perishes","peevish","foregoings","harsher","effectuate","halls","petitioned","afrikaners","bright","holidayed","subtitled","comfortable","whoa","aspens","mops","islams","valedictorians","jellies","jewel","gouging","leftmost","breaches","minimal","overcharged","porting","smith","predominates","motorbike","enigmatically","equable","trickery","streetcar","flautists","countrysides","disregarding","pakistanis","grapevine","chicana","maneuverable","diction","falsified","peppering","murmurs","abated","radiance","maharajas","pediments","entrants","friedman","discombobulated","misprints","reimbursements","andropov","fizzle","stress","sifters","establishments","chippers","boomed","describable","contused","moscow","stonewall","meander","kaleidoscopes","subcontract","physiognomies","bayeux","telegraphic","emotional","govs","crotches","jayne","morison","funguses","schlepped","rhubarbs","bump","banged","shrieked","petal","phooey","piercings","defending","lapidaries","muffle","halcyon","transmitting","smallest","tottered","bossily","ruling","sequencers","lowest","overtaxing","misalignment","plutonium","paroling","windsurfs","annals","nasser","gomorrah","dispute","intensity","unwillingly","witt","stockiest","addends","jacklyn","kind","giauque","reborn","discontinued","headdress","favorite","vivaces","keith","descriptions","secondarily","whinnier","kismet","underrates","restated","guessed","inestimable","irritable","managua","deft","blockbusters","ascribes","melodramatics","cormorants","deliquescent","unpins","twits","confers","bargained","tide","floundering","censorious","gouges","magistrates","reynolds","sequels","as","incinerator","swathes","candelabras","spelling","scratchier","dejecting","implementation","reaffirms","blanches","western","ebony","reason","appending","polluters","jove","gambits","corpuses","grittier","rhetoricians","distils","explicate","jansen","jolt","curing","medicine","tender","supplanted","andres","scrofula","glopped","indulgently","pilchards","scuffs","harmonize","hoagies","xmases","witness","caller","detail","mayo","misidentify","bath","disports","filets","cheapest","scorpions","prevent","promptness","looping","authenticating","cab","reamers","protestation","outgrown","devoting","naphthalene","quarrels","rambler","garment","councilor","chronic","acceptances","resurrection","southpaw","raceway","diffidence","uneventful","mecca","cheri","scarceness","nebraska","elfin","bookies","dietary","auditory","eggo","articulateness","steadying","boulders","impressing","misdo","greta","comae","horace","pedalled","tram","sahib","fetishists","dumbness","belonged","rotunda","moons","falconers","rail","dispels","redeemer","petitioners","forewent","onshore","precincts","detoxes","quintupling","befouling","knit","balls","eddy","poetess","divines","candor","tomcats","weapons","unsnaps","viaducts","chatted","susie","reconvened","condemns","thickeners","collect","rock","apothecary","clue","mulberries","territories","pups","earthing","pledged","coffers","wells","extractors","mistiness","sterility","microfilming","anesthesiologists","miss","volleys","nabs","foamier","wrested","minefields","empathizes","lakshmi","victualling","huffiest","shirrs","centralizes","inapt","besieging","babbitt","psychoanalysts","gybe","enlisting","discomfits","helicoptered","sukkot","skidded","avoirdupois","horsewhipping","khulna","plateaus","rwandan","emerson","copycatted","herald","bemuses","hesitate","gleefully","siestas","tonsure","generals","flunky","eyes","indivisibly","hydrangea","berne","gag","redesigned","insinuating","reenters","remote","douche","budget","afterlives","shootout","shortbread","eatables","omens","abelard","waxen","presaging","platforms","beachheads","redeveloped","praia","peppy","indonesians","plated","abouts","sunbathed","mobiles","playacted","files","verlaine","deceptively","townsmen","acetic","resorting","ritually","rhinestone","neologisms","opposition","cauterizing","sprinkling","theoreticians","khoikhoi","peahens","compensated","shrive","roughage","drastically","objector","manliness","lanyard","espied","saturate","canards","launderer","hightailed","hauler","frosty","blithely","astrophysicist","squirrel","derailing","huffily","suture","mahatma","egocentric","reverting","doctors","anubis","flatly","minuter","staterooms","delineate","rive","offbeat","principals","micra","invoices","menominee","cartographer","ujungpandang","notoriously","reapplying","totalled","whiskers","crassness","incoherently","grassiest","sponging","protects","numeration","preppy","ceased","powering","recreates","musicians","header","bounteous","negligently","berenice","trustfully","methinks","preface","disproportionately","comeuppances","firefighting","synced","disturbing","tuscan","robles","factored","aircraft","nazca","shrank","panhandler","oversleeps","huckleberry","countersank","hopper","paneled","kayaking","billowy","inhalers","sapped","rarefy","thunderclouds","rockets","imperiously","dregs","hockshops","stemming","nymphomaniacs","tightening","refreshing","ryder","persecutor","win","darted","benchmark","abuzz","incises","brunei","scuttled","astronauts","pizazz","covetously","maltreating","barbarism","manila","zuni","humanizes","microcosms","fellow","exonerate","geckoes","foregone","cuttings","butterflying","underflow","saintliness","slather","toddle","isobars","spelunkers","rigmaroles","offings","interning","representative","luann","pursuit","tennis","marijuana","reconvening","shapelessly","strum","kilts","brutalized","gloom","rajas","courses","poising","disclaimers","baluchistan","brief","recognizable","gore","outermost","ascertainable","pitfalls","posers","vegetable","inglorious","mottos","hurtle","heartbreak","chichier","bolshevist","cockscomb","delineates","spouted","asides","chrystal","presentiment","pekings","teargas","classes","foamed","distinguished","sherlock","purling","apace","goalies","specifiable","enfranchised","cycle","crunchiest","ericson","membership","sheraton","reaffirmed","lassie","readily","gunfire","archaism","bulged","watercraft","internal","dishonors","caviled","volcanic","blahs","instances","yammers","colombians","mussier","allegheny","contraband","heinrich","dogged","impossibility","syntactic","infuriate","max","specifically","brent","eugenics","ankhs","icelander","woodshed","emblazoning","thru","overhearing","ultrasuede","hillock","twitching","dishonoring","gridlocking","grouches","sequestering","pressurized","freeloads","ursula","carpet","formulation","aliens","warbling","frazzling","furnishes","abridged","theocracies","karamazov","abridgements","adipose","wideness","lacing","adversaries","prohibitions","nosy","inopportune","radiotherapists","extortionist","relabels","bullish","diagraming","bracing","scrounged","seniority","congregationalists","incompatibles","cavalcade","allergist","stippled","salaciousness","crazily","snuggles","deserve","squashiest","waterfowls","carjacking","trampolined","sister","swankier","kazakhstan","blaspheming","kidnappers","disorganizes","elongated","glopping","windbag","buckeye","rebellious","leaf","biographer","verily","downpours","baxter","qaddafi","suffocated","drowns","doses","rebated","amplifying","paprika","played","midwifed","nightfall","rocketing","solemnizing","pacifiers","alaska","snicker","small","hotshots","jumbos","leagued","subpoena","sivan","overbalanced","rasalhague","washbasin","imponderables","packers","consecrate","majorettes","clarendon","fain","pettifog","bessie","reword","genoa","storey","ezra","shutting","legitimately","obsequies","leggy","diodes","mechanizes","buggies","disavowal","jaunties","johnnie","ladyship","epiphany","probabilities","bucketing","tomboys","slackens","womanizer","venn","toddling","hustling","tattle","bikes","mouthe","daryl","succumb","geffen","honeymoons","denmark","caucasians","introverts","verdure","plausible","conjuror","insetting","aftermath","withers","miller","interdepartmental","decalogue","liquefies","recreated","holographic","median","pidgin","matchless","skinnier","milligram","psychic","titted","consecutive","reject","inhales","colossal","tintinnabulation","virgil","fiduciary","friday","resumptions","rambling","ilene","bordeaux","swaying","giacometti","succeeded","hobbies","photocopying","reinvests","chiselers","respire","dodoes","vaporous","gloried","unproductive","salami","argots","allege","mutinous","mirfak","keepers","hocked","primness","tubers","improvidence","soil","discontinuing","landslides","mollycoddling","smokier","corolla","ripper","splenetic","convening","twangs","snootiest","rousing","stablest","ordinarier","windiest","quizes","tallahassee","hutchinson","becomingly","attentively","sourcing","guppy","demands","lupins","ladders","liechtenstein","emancipated","toughness","maladjustment","bringing","clopping","acrylic","undervalues","bogy","juggles","proprietor","assertions","snobbishness","overcautious","bernie","pylons","enmity","westerns","hobbyhorse","edgy","probing","footsore","cagney","rumpling","engorges","holdover","lyman","ambiguously","boding","adulterous","intriguing","capstan","punitive","stiletto","monotony","weathercocking","torturing","sixtieths","inkling","nutritionist","akron","habits","eyries","ukraine","curlicues","sate","rosendo","seclude","commended","truthfulness","fender","shekel","unrelated","crouch","crueler","kiss","intimately","encumbered","shell","spotters","nosegay","goatherds","pasadena","dropouts","forgathers","predestination","gigglers","invulnerable","negros","workman","onomatopoeia","leaking","immediate","retraces","subheading","fanciers","wield","sacrosanct","sauerkraut","chinatowns","bimonthlies","mistrial","watchtowers","muddle","copernicus","nonuser","amassing","infantries","lead","tantalus","anemones","callahan","bombastic","noise","giantesses","damien","nietzsche","sects","limbs","attire","thickest","deducible","space","fenders","blackballs","slashing","signatories","bast","mousetrapping","motor","bothers","trunks","destine","coursing","kipper","dressmaker","ghanaian","sheathed","sward","mishandle","ferrari","georgian","nearsightedness","chestnuts","basketballs","suffered","yardarms","verses","apothecaries","tequilas","excavated","beauregard","floating","unruliness","certificates","tens","literate","watchfully","tartars","moaning","currant","instincts","codify","sachet","looks","bedrock","roundelay","candidacy","tragicomedies","hollyhocks","pummels","sheldon","swayed","misfired","botany","vane","retrospect","bully","divots","tailgating","outbreaking","varmints","sorcerer","terminal","stein","inland","suggesting","octavia","catalysts","cover","pulsated","incommensurate","northrop","cranked","preemption","sturdily","melons","unicorns","shadiest","mythology","kaboom","orate","soppings","fountainheads","chitchatting","jo","idealism","accordions","constrains","steakhouses","acquited","underlying","tunes","patagonian","untilled","thankfulness","pummeling","undergarment","rodger","walkout","casualties","slews","hiking","funded","catchall","critiquing","flirtatiously","soberly","cherry","disbelieve","longhorn","scuttle","yell","fit","discomforts","cheeriest","masterly","vapors","sulkiness","sign","obsequiously","tryout","stethoscopes","radiators","wrangled","subverting","grievances","typographical","oahu","kaolin","constellations","transnational","efrain","pylon","committal","disables","climatic","taoist","moldiness","scrutiny","mulatto","misstatements","brontosaur","viticulture","waistbands","sharks","trowel","hall","scuppers","stations","buggiest","sole","slyest","london","smokies","shuttlecocks","irreverently","sunfish","befogs","arraigns","uneasy","cadavers","brights","photojournalism","commissioners","gate","idealizes","chaparral","veronese","misguidedly","maker","biophysics","maced","excite","carousals","apes","cruel","bohemian","epigrams","allocate","goldie","grams","porfirio","wears","senecas","weirdly","synthesizer","creditor","chowed","smithies","omnivore","cocktail","isis","derailment","outspokenness","immigrating","snivel","outgo","stopwatches","vibrators","dustbin","gentlest","commune","unregulated","vaccinates","stern","coagulates","pyramidal","ill","intensifier","sweltering","maritza","needlework","stolidly","bantams","missive","relearning","honeycombs","straddles","portraits","active","ceiling","repackaging","malfunctions","bavarians","emanates","flaunts","revision","ceded","hous"};
        strs1 = new String[] {"", ""};
        s1  = System.currentTimeMillis();

        List<String> list3 = anagrams(strs1);
        s2  = System.currentTimeMillis();
        System.out.println(list3.size());
        System.out.println("s2 - s1 = " + (s2 - s1));

        System.out.println("solve Queen");
        List<String[]> result1 = solveNQueens(4);
        for(String[] queens : result1){
            System.out.println("");
            System.out.print("{");
            for(String q : queens){
                System.out.print("\"");
                System.out.print(q);
                System.out.print("\",");

            }
            System.out.println("}\n");
        }

        int numberOfSolution = totalNQueens(4);
        System.out.println("numberOfSolution=" + numberOfSolution);

        numberOfSolution = totalNQueens(5);
        System.out.println("numberOfSolution=" + numberOfSolution);

        System.out.println("Test maxSubArray");
        System.out.println(maxSubArray2(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4}));

        System.out.println("Test sprial");
        int[][] matrix =  new int[][] {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        matrix = null;
        matrix =  new int[][] {};
        matrix =  new int[][] {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}, {10, 11, 12}};
        List<Integer> list4 = spiralOrder(matrix);

        for(int n : list4){
            System.out.print(n + " ");
        }


        System.out.println("Test canJump");
        System.out.println(canJump(new int[]{1,1,1,0}));


        System.out.println("Test insert");
        List<Interval> intervals = new ArrayList<Interval>();
        intervals.add(new Interval(2, 4));
        intervals.add(new Interval(5, 7));
        intervals.add(new Interval(8, 10));
        intervals.add(new Interval(11, 13));
        Interval newInterval = new Interval(3, 8);

        intervals = insert(intervals, newInterval);

        System.out.println("Test getPermutation");
//        System.out.println(getPermutation(3, 1));
        System.out.println(getPermutation(9, factorial(7)));
//        System.out.println(getPermutation(3, 3));
//        System.out.println(getPermutation(3, 4));
//        System.out.println(getPermutation(3, 5));
//        System.out.println(getPermutation(3, 6));

        System.out.println("Test isNumber");
        System.out.println(isNumber("e9"));

        System.out.println(sqrt(9));

        System.out.println("Test climbSteps");
        System.out.println(climbStairs(15));

        System.out.println("Test simplifyPath");
        System.out.println(simplifyPath("/home/"));
        System.out.println(simplifyPath("/a/./b/../../c/"));
        System.out.println(simplifyPath("/../"));
        System.out.println(simplifyPath("/home//foo/"));
        System.out.println(simplifyPath("/..."));
        System.out.println(simplifyPath("/home/../../.."));
        System.out.println(simplifyPath("/home/foo/./bar/"));


        System.out.println("Test setZeroes");

        //int[][] matrix1 = {{Integer.MAX_VALUE, Integer.MIN_VALUE, 160,16,488}};

        int[][] matrix1 = {{Integer.MAX_VALUE, Integer.MIN_VALUE, 160,16,488, -1, 2, 3}};
        StringUtils.printMatrix(matrix1);
        setZeroes(matrix1);
        StringUtils.printMatrix(matrix1);

        System.out.println("Test searchMatrix");
        int[][] matrix2 = {{1,3}};
        System.out.println(searchMatrix(matrix2, 2));

        System.out.println("Test sortColors");
        //int[] B = {2,2,2};
        int[] B = {0,1,2, 2,1,1,0, 2,1, 0, 2, 1, 0};
        StringUtils.printArray(B);
        sortColors(B);
        StringUtils.printArray(B);
//
//        System.out.println("Test combine");
//        List<List<Integer>> list5 = combine(4, 0);
//        for(List<Integer> list55 : list5){
//            StringUtils.printList(list55);
//        }

        System.out.println("Test subsets");
        int[] S1 = {4, 1, 3};
        List<List<Integer>>list5 = subsets(S1);
        for(List<Integer> list55 : list5){
            StringUtils.printList(list55);
        }

        System.out.println("Test minWindow");
        System.out.println(minWindow("adobecodebancbbcaa", "abc"));


        System.out.println("Test exist");
        char[][] board1 = { {'A', 'B', 'C', 'E'},
                            {'S', 'F', 'C', 'S'},
                            {'A', 'D', 'E', 'E'}};
        String word = "ABCCED";
        System.out.println(exist(board1, word));


        System.out.println("Test removeDuplicates2");
        System.out.println(removeDuplicates2(new int[]{1,1,1,2,2,3}));


        System.out.println("Test largestRectangleArea");
        System.out.println(largestRectangleArea(new int[]{4,2,0,3,2,4,3,4}));


        System.out.println("Test maximalRectangle");
        char[][] matrix3 = {{'1'}};
        System.out.println(maximalRectangle(matrix3));

        System.out.println("Test isScramble");
        t1 = System.currentTimeMillis();
//        System.out.println(isScramble("tqxpxeknttgwoppemjkivrulaflayn", "afaylnlurvikjmeppowgttnkexpxqt"));
//        System.out.println(isScramble("abcd", "bdac"));
        System.out.println(isScramble("tqxpxeknttgwoppemjkivrulaflayn", "afaylnlurvikjmeppowgttnkexpxqt"));
        System.out.println(isScramble3("a", "b"));
//        t2 = System.currentTimeMillis();

        System.out.println("t2 - t1=" + (t2 - t1));


        System.out.println("Test grayCode");
        System.out.println(numberOfBits(5));
        List<Integer> result2 = grayCode(5);
        for(Integer x : result2){
            System.out.print(x + " ");
        }

        System.out.println("\nTest numDecodings");
        t1 = System.currentTimeMillis();
        System.out.println(numDecodings("101"));
//        System.out.println(numDecodings("9371597631128776948387197132267188677349946742344217846154932859125134924241649584251978418763151253"));
//        System.out.println(numDecodings("0"));
//        System.out.println(numDecodings("3"));
//        System.out.println(numDecodings("20"));
//        System.out.println(numDecodings("30"));
//        System.out.println(numDecodings("12"));
        t2 = System.currentTimeMillis();

        System.out.println("t2 - t1=" + (t2 - t1));


        System.out.println("Test subsetsWithDup");
        int[] S2 = {2, 1, 2};
        List<List<Integer>>list6 = subsetsWithDup(S2);
        for(List<Integer> list66 : list6){
            StringUtils.printList(list66);
        }


        System.out.println("Test reverseBetween");
        //1->2->3->4->5->NULL

        ListNode head = new ListNode(5);


        for(int i = 4; i >= 1; i--) {
            ListNode h1 = new ListNode(i);
            h1.next = head;
            head = h1;
        }
        head = reverseBetween(head, 2, 2);
        while(head != null){
            System.out.print(head.val + " ");
            head = head.next;
        }


        System.out.println("Test restoreIpAddresses");
        List<String> ips = restoreIpAddresses("010010");
        for(String ip : ips){
            System.out.print(ip + " ");
        }


        System.out.println("Test numTrees");
        System.out.println(numTrees(10));

        List<TreeNode> trees = generateTrees(0);
        System.out.println(trees.size());


        for(TreeNode n : trees){
            printTreeNode(n);
            System.out.println("");
        }


        String s11 = "bbbbbabbbbabaababaaaabbababbaaabbabbaaabaaaaababbbababbbbbabbbbababbabaabababbbaabababababbbaaababaa";
        String s22 = "babaaaabbababbbabbbbaabaabbaabbbbaabaaabaababaaaabaaabbaaabaaaabaabaabbbbbbbbbbbabaaabbababbabbabaab";
        String s33 = "babbbabbbaaabbababbbbababaabbabaabaaabbbbabbbaaabbbaaaaabbbbaabbaaabababbaaaaaabababbababaababbababbbababbbbaaaabaabbabbaaaaabbabbaaaabbbaabaaabaababaababbaaabbbbbabbbbaabbabaabbbbabaaabbababbabbabbab";


//        String s11 = "aabcc";
//        String s22 = "dbbca";
//        String s33 = "aadbbcbcac";
//        s33 = "aadbbbaccc";
//
//        s11 = "aabcc";
//        s22 = "dbbca";
//        s33 = "aadbbcbacc";

        System.out.println("Test isInterleave");
        t1 = System.currentTimeMillis();
        System.out.println(isInterleave(s11, s22, s33));
        t2 = System.currentTimeMillis();
        System.out.println("t2 - t1=" + (t2 - t1));


//        TreeNode root = new TreeNode(146);
//        root.left = new TreeNode(71);
//        root.left.left = new TreeNode(55);
//        root.left.left.left = new TreeNode(321);
//        root.left.left.left.left = new TreeNode(-33);
//
//        root.right = new TreeNode(-13);
//        root.right.left = new TreeNode(231);
//        root.right.right = new TreeNode(399);

//        TreeNode root = new TreeNode(1);
//        root.left = new TreeNode(3);
//        root.left.left = new TreeNode(2);
//        root.left.left.left = new TreeNode(4);

        TreeNode root = new TreeNode(0);
        root.left = new TreeNode(1);


        recoverTree(root);



        System.out.println("Test buildTree");
//        int[] preorder = {-77,24,-74,84,93,28,83,6,95,58,59,66,22,-3,-66,-68,-22,3,-80,-79,-85,17,32,9,-88,-99,14,-60,13,-93,-63,91,82,21,26,-11,-32,-16,-100,-94,-31,-62,-89,49,-9,-8,87,-33,-81,80,0,69,-7,52,67,-5,-65,31,-30,37,-57,27,23,38,-28,7,-82,-42,11,-55,-36,-58,-24,89,56,73,41,18,-87,-70,4,-64,20,-52,-39,79,19,30,65,25,-71,-76,-1,62,-69,98,39,-25,-73,70,88,-17,-20,-75,55,34,57,81,-10,94,48,-35,5,-23,-44,40,-51,-61,-13,-86,63,71,-97,45,43,51,75,33,-34,92,47,-78,85,-26,97,-29,-92,-83,-59,74,96,68,77,16,-4,10,60,64,-21,-2,1,-91,86,46,76,-37,-19,-96,36,-98,29,-72,61,50,15,-95,-40,-43,-53,90,-15,-48,-27,-90,-54,72,-50,-49,-18,78,54,35,-38,99,44,-67,53,-12,-41,2,8,-14,-84,-56,-6,12,-45,42,-47,-46};
//        int[] inorder =  {93,28,84,83,-74,59,58,66,-66,-3,-79,-80,3,-22,-68,22,-85,-99,14,-88,9,32,17,-60,95,-93,82,21,91,-63,26,13,-16,-32,-11,-100,6,-62,49,-89,-31,87,-8,69,0,80,-7,-81,-65,-5,67,-30,31,52,-33,37,-57,-9,7,-28,-42,-82,38,-55,11,23,-36,27,56,89,73,-24,41,-58,-70,-87,20,-64,-52,4,18,-94,19,30,-76,-1,-71,62,-69,25,-73,-25,70,39,88,98,-20,-17,65,55,-75,79,34,-39,48,94,-23,5,-44,-35,40,-10,-61,-51,-13,81,63,-97,71,-86,57,45,24,-34,85,97,-26,-78,-83,-92,74,-59,96,-29,68,47,77,92,10,-4,16,60,33,-21,1,86,76,46,-37,-91,-2,64,75,51,-19,-96,43,-98,29,61,-72,50,36,-95,-40,-43,15,90,-15,-53,-77,-54,-90,-49,-50,72,-27,35,54,-38,78,-67,44,53,99,-41,-12,-18,8,2,-48,-56,-84,-14,-45,12,42,-6,-46,-47};

        int[] preorder = {1, 2};
        int[] inorder =  {2, 1};
        TreeNode root1 = buildTree1(preorder, inorder);

        System.out.println("End");
    }
}
