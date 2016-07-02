package com.dzhou.corejava.introcs.ch1;

/**
 * Created by huizhou on 5/1/16.
 */
public class RandomInt {
    public static void main(String[] args) {
        int N = 100;

        for (int i = 0; i < 9; i++) {
            // a pseudo-random real between 0.0 and 1.0
            double r = Math.random();

            // a pseudo-random integer between 0 and N - 1
            int n = (int) (r * N);

            System.out.println("Your random integer is: " + n);
        }
    }
}
