package com.dzhou.corejava.algorithms;

import java.util.Comparator;

/**
 * Created by huizhou on 7/22/14.
 */
public class Insertion {

    public static void sort(Comparable[] a){
        int N = a.length;

        for(int i = 1; i < N; i++){
            for(int j = i; j > 0 && less(a[j], a[j-1]); j--){
                exch(a, j-1, j);
            }

        }

    }

    public static void sort(Object[] a, Comparator c){
        int N = a.length;
        for(int i = 0; i < N; i++){
            for(int j = i; j > 0 && less(c, a[j], a[j-1]); j--){
                exch(a, j, j-1);
            }
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
