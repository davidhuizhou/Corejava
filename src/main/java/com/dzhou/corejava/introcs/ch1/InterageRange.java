package com.dzhou.corejava.introcs.ch1;

/**
 * Created by huizhou on 9/7/15.
 */
public class InterageRange {
    public static void main(String[] args) {
        int a = Integer.MAX_VALUE;
        System.out.println(a);
        System.out.println(Integer.toBinaryString(a));
        System.out.println(a + 1);
        System.out.println(Integer.toBinaryString(a + 1));
        System.out.println(Integer.toBinaryString(-a));
        System.out.println(2 - a);
        System.out.println(Integer.toBinaryString(2 - a));
        System.out.println(2 * a);
        System.out.println(Integer.toBinaryString(2 * a));
        System.out.println(4 * a);
        System.out.println(Integer.toBinaryString(4 * a));

        for (int i = 0; i < 10; i++)
            for (int j = 10; j < 100; j++) {
                System.out.println(i + j);
                break;
            }

    }
}
