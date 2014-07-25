package com.dzhou.corejava.algorithms;

/**
 * Created by davidzhou on 7/24/14.
 */
public class Merge {

    public static void sort(Comparable[] a){
        Comparable[] aux = new Comparable[a.length];
        sort(a, aux, 0, a.length - 1);

    }

    private static void sort(Comparable[] a, Comparable[] aux, int lo, int hi){
        if(hi <= lo) return;
        int mid = lo + (hi - lo)/2;
        sort(a, aux, lo, mid);
        sort(a, aux, mid + 1, hi);
        merge(a, aux, lo, mid, hi);

    }

    private static void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi){
        int i = lo, j = mid + 1;

        for(int k = lo; k <= hi; k++) {
            aux[k] = a[k];
        }

        for(int k = lo; k <= hi; k++){
            if(i > mid) a[k] = a[j++];
            else if (j > hi) a[k] = a[i++];
            else  if(less(a[j], a[i])) a[k] = a[j++];
            else a[k] = a[i++];
        }

    }



    // is v < w ?
    private static boolean less(Comparable v, Comparable w) {
        return (v.compareTo(w) < 0);
    }

    // exchange a[i] and a[j]
    private static void exch(Object[] a, int i, int j) {
        Object swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }

    public static void main(String[] args){

    }

}
