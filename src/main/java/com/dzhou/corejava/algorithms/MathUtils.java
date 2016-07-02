package com.dzhou.corejava.algorithms;

/**
 * Created by huizhou on 2/24/16.
 */
public class MathUtils {
    public static double sqrt(final double c) {
        if(c < 0)
            return Double.NaN;
        double err = 1e-15;
        double t = c;
        while(Math.abs(t - c/t) > err){
            t = (c/t + t) / 2;
        }
        return t;
    }

    public static boolean isPrime(final int N){
        if(N < 2)
            return false;
        for(int i = 2; i*i <= N; i++) {
            if(N % i == 0)
                return false;
        }
        return true;

    }

}
