package com.dzhou.corejava.introcs.ch1;

/**
 * Created by huizhou on 3/28/16.
 */
public class LeapYear {
    public static void main(String[] args) {
        int year = 2016;
        boolean isLeapYear;

        // divisible by 4
        isLeapYear = (year % 4 == 0);

        // divisible by 4 and not 100
        isLeapYear = isLeapYear && (year % 100 != 0);

        // divisible by 4 and not 100 unless divisible by 400
        isLeapYear = isLeapYear || (year % 400 == 0);

        System.out.println(isLeapYear);
    }
}
