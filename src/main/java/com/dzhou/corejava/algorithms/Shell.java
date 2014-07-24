package com.dzhou.corejava.algorithms;

import java.util.Comparator;

/**
 * Created by davidzhou on 7/24/14.
 */
public class Shell {

    public static void sort(Comparable[] a){
        int N = a.length;

        int h = 1;
        while(h < N/3) h = h * 3 + 1;

        while(h > 0){
            for(int i = h; i < N; i++){
                for(int j = i; j >= h && less(a[j], a[j-h]); j -= h){
                    exch(a, j-h, j);
                }
            }
            h /= 3;
        }


    }

    // is v < w ?
    private static boolean less(Comparable v, Comparable w) {
        return (v.compareTo(w) < 0);
    }

    // is v < w ?
    private static boolean less(Comparator c, Object v, Object w) {
        return (c.compare(v, w) < 0);
    }

    // exchange a[i] and a[j]
    private static void exch(Object[] a, int i, int j) {
        Object swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }
}
