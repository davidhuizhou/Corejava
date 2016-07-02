package com.dzhou.corejava.jpl4;

/**
 * Created by davidzhou on 1/5/14.
 */

public class Fibonacci {

    public static void fibonacci(int input) {
        int lo = 1;
        int hi = 1;

        System.out.println(lo);
        while (hi < input) {
            System.out.println(hi);
            hi = lo + hi;
            lo = hi - lo;
        }
    }

    public static void main(String[] args){
        fibonacci(10);
    }


}
