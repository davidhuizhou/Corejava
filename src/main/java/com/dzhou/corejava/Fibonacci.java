package com.dzhou.corejava;

/**
 * Created by davidzhou on 1/5/14.
 */

public class Fibonacci {

    public static void printFibonacci(int input) {
        int lo = 1;
        int hi = 1;


        System.out.println(lo);


        while (hi < input) {
            int d;
            System.out.println(hi);

            hi = lo + hi;
            lo = hi - lo;
        }
    }


}
