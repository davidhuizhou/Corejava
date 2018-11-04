package com.dzhou.corejava.elements_of_programming_interviews;

import com.google.common.base.Joiner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by huizhou on 7/24/16.
 */
public class Strings {

  /**
   * Problem 7.0 - Check if a string is a palindromic, which reads the same when it is
   * reversed.
   */
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
    do {
      sb.append((char) ('0' + Math.abs(x % 10)));
      x /= 10;
    } while (x != 0);

    if (isNegative) {
      sb.append("-");
    }
    sb.reverse();
    return sb.toString();
  }

  public static int stringToInt(String s) {
    boolean isNegative = s.charAt(0) == '-';
    int result = 0;
    for (int i = isNegative ? 1 : 0; i < s.length(); i++) {
      final int digit = s.charAt(i) - '0';
      result = result * 10 + digit;
    }
    return isNegative ? -result : result;
  }

  /**
   * Problem 7.2 Base conversion.
   */
  public static String convertBase(String s, int b1, int b2) {
    boolean isNegative = s.startsWith("-");
    int numAsInt = 0;
    for (int i = (isNegative ? 1 : 0); i < s.length(); i++) {
      numAsInt *= b1;
      numAsInt += Character.isDigit(s.charAt(i))
          ? s.charAt(i) - '0'
          : s.charAt(i) - 'A' + 10;
    }
    return (isNegative ? "-" : "")
        + (numAsInt == 0 ? "0" : constructFromBase(numAsInt, b2));
  }

  private static String constructFromBase(int numAsInt, int base) {
    return numAsInt == 0
        ? ""
        : constructFromBase(numAsInt / base, base)
        + (char) (numAsInt % base >= 10 ? 'A' + numAsInt % base - 10
        : '0' + numAsInt % base);
  }

  /**
   * Problem 7.3 Compute the spreadshift column encoding.
   * "A", "B", .. "Z", "AA", "AB", ..., "AZ"
   * "ZZ" - 26 ^ 2 + 26
   */
  public static int ssDecodeColId(String col) {
    int ret = 0;
    for (char c : col.toCharArray()) {
      ret = ret * 26 + c - 'A' + 1;
    }
    return ret;
  }

  /**
   * Problem 7.4 Replace and remove.
   * Replace each 'a' by two 'd's.
   * Delete each 'b'
   */
  public static void replaceAndRemove(int size, Character[] s) {
    // Forward iteration: remove "b"s and count the number of "a"s.
    int writeIdx = 0, aCount = 0;
    for (int i = 0; i < size; ++i) {
      if (s[i] != 'b') {
        s[writeIdx++] = s[i];
      }

      if (s[i] == 'a') {
        ++aCount;
      }

    }

    // Backward iteration: replace "a"s with "dd"s starting from the end.
    int curIdx = writeIdx - 1;
    writeIdx = writeIdx + aCount - 1;
    while (curIdx >= 0) {
      if (s[curIdx] == 'a') {
        s[writeIdx--] = 'd';
        s[writeIdx--] = 'd';
      } else {
        s[writeIdx--] = s[curIdx];
      }
      --curIdx;
    }

  }


  /**
   * Problem 7.5 Test Palindromicity.
   * Ignore case and remove nonalphanumeric.
   */
  public static boolean isPalindrome(final String s) {
    // i moves forward, and j moves backward.
    int i = 0, j = s.length() - 1;
    while (i < j) {
      // i and j both skip non-alphanumeric characters.
      while (!Character.isAlphabetic(s.charAt(i)) && i < j) {
        i++;
      }
      while (!Character.isAlphabetic(s.charAt(j)) && i < j) {
        j--;
      }
      if (Character.toLowerCase(s.charAt(i)) != Character.toLowerCase(s.charAt(j))) {
        return false;
      }
      i++;
      j--;

    }
    return true;
  }


  /**
   * Problem 7.6 Reverse all the words in sentence.
   * "Alice likes Bob" to "Bob likds Alice".
   * First rever s and reversing the individual words.
   */
  public static String reverseWords(final String s) {
    char[] array = s.toCharArray();
    reverseWords(array);
    return new String(array);
  }


  public static void reverseWords(char[] input) {
    // Reverse the whole string first
    reverse(input, 0, input.length);

    int start = 0, end;
    while ((end = find(input, ' ', start)) != -1) {
      // Reverse each work in the string
      reverse(input, start, end);
      start = end + 1;
    }
    // Reverse the last word
    reverse(input, start, input.length);
  }

  public static void reverse(char[] array, int start, int stopIndex) {
    if (start >= stopIndex) {
      return;
    }
    int last = stopIndex - 1;
    for (int i = start; i <= start + (last - start) / 2; i++) {
      char tmp = array[i];
      array[i] = array[last - i + start];
      array[last - i + start] = tmp;
    }
  }

  public static int find(char[] array, char c, int start) {
    for (int i = start; i < array.length; i++) {
      if (array[i] == c) {
        return i;
      }
    }
    return -1;
  }

  /**
   * Problem 7.7 Compute all mnemonics for a phone number.
   */
  private static final String[] MAPPING = new String[]{"0", "1", "ABC", "DEF", "GHI", "JKL", "MNO",
      "PQRS", "TUV", "WXYZ"};

  public static List<String> phoneMnemonic(final String phoneNumber) {
    char[] partialMnemonic = new char[phoneNumber.length()];
    List<String> mnemonics = new ArrayList<String>();
    phoneMnemonicHelper(phoneNumber, 0, partialMnemonic, mnemonics);
    return mnemonics;
  }

  private static void phoneMnemonicHelper(final String phoneNumber, int digit, char[]
      partialMnemonic, List<String> mnemonics) {

    if (digit == phoneNumber.length()) {
      // All digits are processed, so add partialMnemonic to mnemonics.
      // We add a copy since subsequent calls modify partialMnemonic.
      mnemonics.add(new String(partialMnemonic));

    } else {
      for (char c : MAPPING[phoneNumber.charAt(digit) - '0'].toCharArray()) {
        partialMnemonic[digit] = c;
        phoneMnemonicHelper(phoneNumber, digit + 1, partialMnemonic, mnemonics);
      }
    }

  }

  /**
   * Problem 7.8 The look-and-say problem.
   */
  public static String lookAndSay(int n) {
    String s = "1";
    for (int i = 1; i < n; ++i) {
      s = nextNumber(s);
    }
    return s;
  }

  private static String nextNumber(final String s) {
    String ret = "";
    for (int i = 0; i < s.length(); i++) {
      int count = 1;
      while (i + 1 < s.length() && s.charAt(i) == s.charAt(i + 1)) {
        count++;
        i++;
      }
      ret += String.valueOf(count) + s.charAt(i);
    }
    return ret;
  }

  /**
   * Problem 7.9 Convert from Roman to Decimal
   */
  public static int RomanToInteger(final String s) {
    Map<String, Integer> T = new HashMap<String, Integer>();
    T.put("I", 1);
    T.put("V", 5);
    T.put("X", 10);
    T.put("L", 50);
    T.put("C", 100);
    T.put("D", 500);
    T.put("M", 1000);

    int sum = T.get(s.charAt(s.length() - 1));
    for (int i = s.length() - 2; i >= 0; i--) {
      if (T.get(s.charAt(i)) < T.get(s.charAt(i + 1))) {
        sum -= T.get(s.charAt(i));
      } else {
        sum += T.get(s.charAt(i));
      }
    }
    return sum;
  }

  /**
   * Integer to Roman.
   */
  public static String intToRoman(int num) {
    if (num <= 0) {
      return "";
    }

    int[] nums = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
    String[] symbols = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
    StringBuilder res = new StringBuilder();

    int digit = 0;
    while (num > 0) {
      int times = num / nums[digit];
      num -= nums[digit] * times;
      for (; times > 0; times--) {
        res.append(symbols[digit]);
      }
      digit++;
    }
    return res.toString();
  }
  
  /**
   * Run-length encoding
   *
   */
  public static String decoding(String s) {
    int count = 0;
    StringBuilder result = new StringBuilder();
    for (int i = 0; i < s.length(); i++) {
      char c = s.charAt(i);
      if (Character.isDigit(c)) {
        count = count * 10 + c - '0';
      } else {
        while (count > 0) {
          result.append(c);
          count--;
        }
      }
    }
    return result.toString();

  }

  public static String encoding(String s) {
    int count = 1;
    StringBuilder result = new StringBuilder();
    for (int i = 1; i <= s.length(); i++) {
      if (i == s.length() || s.charAt(i) != s.charAt(i - 1)) {
        result.append(count);
        result.append(s.charAt(i - 1));
      } else {
        ++count;
      }
    }
    return result.toString();
  }
    
  
  

  public static void main(String[] args) {
    System.out.println(isPalindromic("abcba"));
    System.out.println(intToString(1234));
    System.out.println(intToString(-1234));
    System.out.println(stringToInt("1234"));
    System.out.println(stringToInt("-1234"));
    System.out.println(convertBase("615", 7, 13));
    System.out.println(convertBase("-615", 7, 13));
    // ssDecodeColId
    System.out.println(ssDecodeColId("A"));
    System.out.println(ssDecodeColId("B"));
    System.out.println(ssDecodeColId("Y"));
    System.out.println(ssDecodeColId("Z"));
    System.out.println(ssDecodeColId("AA"));
    System.out.println(ssDecodeColId("AB"));
    System.out.println(ssDecodeColId("AY"));
    System.out.println(ssDecodeColId("AZ"));
    System.out.println(ssDecodeColId("M"));
    System.out.println(ssDecodeColId("BZ"));
    System.out.println(ssDecodeColId("CCC"));
    System.out.println(ssDecodeColId("ZZZ"));

    // replaceAndRemove
    Character[] s = new Character[10];
    s[0] = 'a';
    s[1] = 'c';
    s[2] = 'd';
    s[3] = 'b';
    s[4] = 'b';
    s[5] = 'c';
    s[6] = 'a';
    System.out.println(Joiner.on(",").skipNulls().join(s));
    replaceAndRemove(7, s);
    System.out.println(Joiner.on(",").skipNulls().join(s));

    System.out.println(isPalindrome("Able was I, ere I saw Elba!"));
    System.out.println(isPalindrome("Ray a Ray"));

    System.out.println(reverseWords("ram is costly"));

    List<String> mnemonic = phoneMnemonic("2276696");
    System.out.println(Joiner.on(",").join(mnemonic));

    System.out.println(lookAndSay(8));

  }

}
