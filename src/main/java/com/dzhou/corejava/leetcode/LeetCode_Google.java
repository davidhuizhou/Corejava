package com.dzhou.corejava.leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class LeetCode_Google {

  /**
   * Problem 681 - Next Closest Time
   *
   * https://leetcode.com/problems/next-closest-time
   */
  public String nextClosestTime(String time) {
    int cur = 60 * Integer.parseInt(time.substring(0, 2));
    cur += Integer.parseInt(time.substring(3));
    Set<Integer> allowed = new HashSet();
    for (char c : time.toCharArray())
      if (c != ':') {
        allowed.add(c - '0');
      }

    while (true) {
      cur = (cur + 1) % (24 * 60);
      int[] digits = new int[]{cur / 60 / 10, cur / 60 % 10, cur % 60 / 10,
        cur % 60 % 10};
      search:
      {
        for (int d : digits) if (!allowed.contains(d)) break search;
        return String.format("%02d:%02d", cur / 60, cur % 60);
      }
    }
  }

  public String nextClosestTime2(String time) {

    String minutes = time.trim().substring(3);
    String hours = time.trim().substring(0, 2);

    for (int nextMinutes = this.strToInt(minutes) + 1; nextMinutes <= 59;
         nextMinutes++) {
      if (this.canCreateNumberFromStr(nextMinutes, time)) {
        return hours + ":" + (nextMinutes < 10 ? "0" :
          "") + String.valueOf(nextMinutes);
      }
    }

    for (int nextHour = this.strToInt(hours) + 1; nextHour <= 23; nextHour++) {
      if (this.canCreateNumberFromStr(nextHour, time)) {
        return (nextHour < 10 ? "0" : "") + String.valueOf(nextHour)
          + ":" + getSmallestNumber(time) + getSmallestNumber(time);
      }
    }

    return getSmallestNumber(time) + getSmallestNumber(time)
      + ":" + getSmallestNumber(time) + getSmallestNumber(time);


  }

  private int strToInt(final String s) {
    int result = 0;
    for (int i = 0; i < s.length(); i++) {
      result = 10 * result + s.charAt(i) - '0';
    }
    return result;
  }

  private String getSmallestNumber(final String s) {
    int result = Integer.MAX_VALUE;
    for (int i = 0; i < s.length(); i++) {
      if (Character.isDigit(s.charAt(i))) {
        result = Math.min(result, s.charAt(i) - '0');
      }
    }
    return String.valueOf(result);
  }

  private boolean canCreateNumberFromStr(int num, final String s) {
    while (num > 0) {
      if (!s.contains(String.valueOf(num % 10))) {
        return false;
      }
      num /= 10;
    }
    return true;
  }


  /**
   * Problem 904 - Fruit Into Baskets
   *
   * https://leetcode.com/problems/fruit-into-baskets
   */
  public int totalFruit(int[] tree) {
    int ans = 0, i = 0;
    Counter count = new Counter();
    for (int j = 0; j < tree.length; j++) {
      count.add(tree[j], 1);
      while (count.size() >= 3) {
        count.add(tree[i], -1);
        if (count.get(tree[i]) == 0) {
          count.remove(tree[i]);
        }
        i++;
      }
      ans = Math.max(ans, j - i + 1);
    }
    return ans;
  }

  private class Counter extends HashMap<Integer, Integer> {
    public int get(int k) {
      return this.containsKey(k) ? super.get(k) : 0;
    }

    public void add(int k, int v) {
      this.put(k, get(k) + v);
    }
  }


  public static void main(String[] args) {
    LeetCode_Google lc = new LeetCode_Google();

    int[] tree = new int[]{1, 0, 1, 4, 1, 4, 1, 2, 3};
    System.out.println(lc.totalFruit(tree));

    System.out.println(lc.strToInt("19"));


    System.out.println(lc.nextClosestTime("22:37"));


  }


}
