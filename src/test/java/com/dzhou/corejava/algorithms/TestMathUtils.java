package com.dzhou.corejava.algorithms;

import org.junit.Test;

import static com.google.common.truth.Truth.assertThat;

/**
 * Created by huizhou on 2/24/16.
 */
public class TestMathUtils {
    @Test
    public void testSqrt() {
        double c = 12;
        double err = 1e-15;
        assertThat(Math.abs(MathUtils.sqrt(c) - Math.sqrt(c))).isWithin(err);
    }

    @Test
    public void testIsPrime(){
        int N = 100;
        for(int n = 1; n < N; n++){
            System.out.printf("%6d  %s%n", n, MathUtils.isPrime(n));
        }
    }
}
