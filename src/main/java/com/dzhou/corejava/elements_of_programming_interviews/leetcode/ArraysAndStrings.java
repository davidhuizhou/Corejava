package com.dzhou.corejava.elements_of_programming_interviews.leetcode;

import java.util.HashMap;
import java.util.Map;


public class ArraysAndStrings {

  /**
   * First Unique Character in a String -
   * https://leetcode.com/explore/interview/card/amazon/76/array-and-strings/480/
   */
  public static int firstUniqChar(String s) {
    Map<Character, Integer> map = new HashMap<>();
    for (int i = 0; i < s.length(); i++) {
      char c = s.charAt(i);
      if (map.containsKey(c)) {
        map.put(c, map.get(c) + 1);
      } else {
        map.put(c, 1);
      }
    }

    for (int i = 0; i < s.length(); i++) {
      char c = s.charAt(i);
      if (map.get(c) == 1) {
        return i;
      }
    }
    return -1;

  }

  /**
   * Reverse Words in a String II -
   * https://leetcode.com/explore/interview/card/amazon/76/array-and-strings/522/
   */
  public static void reverseWords(char[] str) {
    if (str == null) {
      return;
    }
    reverse(str, 0, str.length - 1);

    // for (int i = 0, j = 0; j < str.length; j++) {
    // // find the first none white character
    // while (i < str.length && str[i] == ' ')
    // i++;
    //
    // j = i;
    //
    // // find the first white character after i
    // while (j < str.length && str[j] != ' ')
    // j++;
    //
    // reverse(str, i, j - 1);
    //
    // i = j;
    // }
    int start = 0, end;
    while ((end = find(str, ' ', start)) != -1) {
      reverse(str, start, end - 1);
      start = end + 1;
    }
    reverse(str, start, str.length - 1);

  }

  private static int find(char[] array, char c, int start) {
    for (int i = start; i < array.length; i++) {
      if (array[i] == c) {
        return i;
      }
    }
    return -1;
  }

  private static void reverse(char[] a, int start, int end) {
    for (int i = start, j = end; i < j; i++, j--) {
      char temp = a[i];
      a[i] = a[j];
      a[j] = temp;
    }
  }



  /**
   * Compare Version Numbers -
   * https://leetcode.com/explore/interview/card/amazon/76/array-and-strings/502/
   * 
   */
  public static int compareVersion(String version1, String version2) {
    if (version1.equals("") && version2.equals("")) {
      return 0;
    }
    Version v1 = getVersion(version1);
    Version v2 = getVersion(version2);


    if (strToInt(v1.major) == strToInt(v2.major)) {
      return compareVersion(v1.minor, v2.minor);
    } else {
      // return strToInt(v1.major) > strToInt(v2.major) ? 1 : -1;
      return Integer.compare(strToInt(v1.major), strToInt(v2.major));
    }

  }

  private static int strToInt(final String s) {
    int sum = 0;
    for (int i = 0; i < s.length(); i++) {
      sum = 10 * sum + (s.charAt(i) - '0');
    }
    return sum;
  }

  private static Version getVersion(final String s) {
    String major = "";
    String minor = "";

    int index = s.indexOf(".");
    if (index >= 0) {
      major = s.substring(0, index);
      minor = index < s.length() - 1 ? s.substring(index + 1) : "";
    } else {
      major = s;
      minor = "";
    }
    return new Version(major, minor);
  }

  private static class Version {
    private String major;
    private String minor;

    private Version(String major, String minor) {
      this.major = major;
      this.minor = minor;
    }
  }



  /**
   * Maximum Size Subarray Sum Equals to k -
   * https://leetcode.com/explore/interview/card/amazon/76/array-and-strings/524/
   */
  public static int maxSubArrayLen(int[] nums, int k) {
    Map<Integer, Integer> map = new HashMap<>();
    int sum = 0, maxLen = 0;
    for (int i = 0; i < nums.length; i++) {
      sum += nums[i];

      if (!map.containsKey(sum)) {
        map.put(sum, i);
      }

      if (sum == k) {
        maxLen = i + 1;
      } else {
        int diff = sum - k;
        if (map.containsKey(diff)) {
          maxLen = Math.max(maxLen, i - map.get(diff));
        }
      }
    }
    return maxLen;
  }


  /**
   * Longest Palindromic Substring -
   * https://leetcode.com/explore/interview/card/amazon/76/array-and-strings/489/
   */
  public String longestPalindrome(String s) {
    int start = 0, end = 0;
    for (int i = 0; i < s.length(); i++) {
      int len1 = expand(s, i, i);
      int len2 = expand(s, i, i + 1);
      int len = Math.max(len1, len2);
      if (len > end - start) {
        start = i - (len - 1) / 2;
        end = i + len / 2;
      }
    }
    return s.substring(start, end + 1);
  }

  private int expand(String s, int left, int right) {
    while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
      left--;
      right++;
    }
    return right - left - 1;
  }

  /**
   * Product of Array Except self.
   * https://leetcode.com/explore/interview/card/amazon/76/array-and-strings/499/
   */
  private static int[] productExceptSelf(int[] nums) {
    int[] result = new int[nums.length];

    result[0] = 1;
    for (int i = 1; i < nums.length; i++) {
      result[i] = result[i - 1] * nums[i - 1];
    }

    int prodRight = 1;
    for (int j = nums.length - 2; j >= 0; j--) {
      prodRight *= nums[j + 1];
      result[j] = result[j] * prodRight;
    }

    return result;

  }

  private static String[] singleDigitsAndTeens = {"Zero", "One", "Two", "Three", "Four", "Five",
      "Six", "Seven", "Eight", "Nine", "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen",
      "Sixteen", "Seventeen", "Eighteen", "Nineteen"};

  private static String[] tenMultiples =
      {"", "", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};

  private final static int TEN = 10;
  private final static int TWENTY = 20;
  private final static int HUNDRED = 100;
  private final static int THOUSAND = 1000;
  private final static int MILLION = 1000 * THOUSAND;
  private final static int BILLION = 1000 * MILLION;

  /**
   * Integer to English Words -
   * https://leetcode.com/explore/interview/card/amazon/76/array-and-strings/481/
   */
  public static String numberToWords(int num) {
    if (num < TWENTY) {
      return singleDigitsAndTeens[num % TWENTY];
    } else if (num < HUNDRED) {
      return num % TEN != 0 ? tenMultiples[num / TEN] + " " + numberToWords(num % TEN)
          : tenMultiples[num / TEN];
    } else if (num < THOUSAND) {
      return num % HUNDRED != 0
          ? singleDigitsAndTeens[num / HUNDRED] + " " + "Hundred" + " "
              + numberToWords(num % HUNDRED)
          : singleDigitsAndTeens[num / HUNDRED] + " " + "Hundred";
    } else if (num < MILLION) {
      return num % THOUSAND != 0
          ? numberToWords(num / 1000) + " " + "Thousand" + " " + numberToWords(num % THOUSAND)
          : numberToWords(num / 1000) + " " + "Thousand";
    } else if (num < BILLION) {
      return num % MILLION != 0
          ? numberToWords(num / MILLION) + " " + "Million" + " " + numberToWords(num % MILLION)
          : numberToWords(num / MILLION) + " " + "Million";
    } else if (num <= Integer.MAX_VALUE) {
      return num % BILLION != 0
          ? numberToWords(num / BILLION) + " " + "Billion" + " " + numberToWords(num % BILLION)
          : numberToWords(num / BILLION) + " " + "Billion";
    }

    return "";
  }

  /**
   * Find the Duplicate Number -
   * https://leetcode.com/explore/interview/card/amazon/76/array-and-strings/496/
   *
   */
  public int findDuplicate(int[] nums) {
    int low = 1, high = nums.length - 1;
    while (low < high) {
      int mid = low + (high - low) / 2;
      int cnt = 0;
      for (int n : nums) {
        if (n <= mid) {
          cnt++;
        }
      }
      if (cnt <= mid) {
        low = mid + 1;
      } else {
        high = mid;
      }
    }
    return low;

  }


  public static void main(String[] args) {
    // System.out.println(firstUniqChar("leetcode"));
    // System.out.println(firstUniqChar("loveleetcode"));
    System.out.println(maxSubArrayLen(new int[]{1, -1, 5, -2, 3}, 3));
    System.out.println(maxSubArrayLen(new int[]{1, -1, 5, -2, 3}, 2));
    // char[] c = "the sky is blue".toCharArray();
    // reverseWords(c);
    // System.out.println(c);
    // System.out.println(compareVersion("1.0", "1.1"));
    //
    // System.out.println(compareVersion("0.1", "1.1"));
    // System.out.println(compareVersion("1.2", "1.1"));
    // System.out.println(compareVersion("1.2", "1.2"));
    // System.out.println(compareVersion("1.27", "1.3"));
    // System.out.println(compareVersion("1", "0"));
    // System.out.println(compareVersion("1", "01"));
    // System.out.println(compareVersion("1", "1.10"));
    // System.out.println(compareVersion("1.2", "1.10"));
    // System.out.println(compareVersion("1.0.1", "1"));
    // System.out.println(Integer.MAX_VALUE);
    // for (int num = 0; num < 1000000; num++) {
    // System.out.println(num + "=" + numberToWords(num));
    // }
//
//    for (int n : productExceptSelf(new int[] {1, 2, 3, 4})) {
//      System.out.print(n + " ");
//
//    }
//
//    System.out.println("");
//
//    for (int n : productExceptSelf(new int[] {1, 2})) {
//      System.out.print(n + " ");
//    }
//
//    System.out.println("");
//
//    for (int n : productExceptSelf(new int[] {0, 1})) {
//      System.out.print(n + " ");
//    }
//
//    System.out.println("");
//
//    for (int n : productExceptSelf(new int[] {1, 0})) {
//      System.out.print(n + " ");
//    }
//
//    System.out.println("");
//
//    for (int n : productExceptSelf(new int[] {0, 0})) {
//      System.out.print(n + " ");
//    }
//
//    System.out.println("");
    
  }


}
