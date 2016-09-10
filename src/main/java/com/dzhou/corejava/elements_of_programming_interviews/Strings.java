package com.dzhou.corejava.elements_of_programming_interviews;

/**
 * Created by huizhou on 7/24/16.
 */
public class Strings {
  public static boolean isPalindromic(String s) {
    for (int i = 0, j = s.length() - 1; i < j; i++, j--) {
      if (s.charAt(i) != s.charAt(j)) {
        return false;
      }
    }
    return true;
  }

  public static String reverse(String s) {
    char[] a = s.toCharArray();
    for (int i = 0, j = a.length - 1; i < j; i++, j--) {
      swap(a, i, j);
    }
    return String.valueOf(a);
  }

  private static void swap(char[] a, int i, int j) {
    char temp = a[i];
    a[i] = a[j];
    a[j] = temp;
  }

  public static String intToString(int x) {
    boolean isNegative = false;
    if (x < 0) {
      x = -x;
      isNegative = true;
    }
    StringBuilder sb = new StringBuilder();
    while (x > 0) {
      sb.append(x % 10);
      x /= 10;
    }

    if (isNegative) {
      sb.append("-");
    }
    return reverse(sb.toString());
  }

  public static int stringToInt(String s) {
    boolean isNegative = s.charAt(0) == '-';
    int result = 0;
    for (int i = isNegative ? 1 : 0; i < s.length(); i++) {
      int digit = s.charAt(i) - '0';
      result = result * 10 + digit;
    }
    return isNegative ? -result : result;
  }

  public static String convertBase(String s, int b1, int b2) {
    boolean isNegative = s.charAt(0) == '-';
    int x = 0;
    for (int i = isNegative ? 1 : 0; i < s.length(); i++) {
      x *= b1;
      x += Character.isDigit(s.charAt(i)) ? s.charAt(i) - '0' : s.charAt(i) - 'A' + 10;
    }
    return (isNegative ? "-" : "") + (x == 0 ? "0" : constructFromBase(x, b2));
  }

  private static String constructFromBase(int x, int base) {
    return x == 0 ? "" : constructFromBase(x / base, base) + (char) (x % base >= 10 ? 'A' + x % base
        - 10 : '0' + x % base);
  }

  public static void main(String[] args) {
    System.out.println(isPalindromic("abcba"));
    System.out.println(intToString(1234));
    System.out.println(intToString(-1234));
    System.out.println(stringToInt("1234"));
    System.out.println(stringToInt("-1234"));
    System.out.println(convertBase("615", 7, 13));
    System.out.println(convertBase("-615", 7, 13));
  }

}
