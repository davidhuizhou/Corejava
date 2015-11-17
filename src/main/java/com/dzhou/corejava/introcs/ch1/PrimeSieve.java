package com.dzhou.corejava.introcs.ch1;

/**
 * Created by huizhou on 11/7/15.
 */
public class PrimeSieve {
    public static void main(String[] args) {
        int N = Integer.parseInt(args[0]);

        boolean[] isPrime = new boolean[N + 1];
        for (int i = 2; i <= N; i++) {
            isPrime[i] = true;
        }

        for (int i = 2; i * i <= N; i++) {
            if (isPrime[i]) {
                for (int j = i; i * j <= N; j++) {
                    isPrime[i * j] = false;
                }
            }
        }

        for (int i = 2; i <= N; i++) {
            if (isPrime[i])
                System.out.printf("%6d", i);
        }

    }
}
