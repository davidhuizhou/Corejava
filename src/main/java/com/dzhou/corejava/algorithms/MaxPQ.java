package com.dzhou.corejava.algorithms;

import com.dzhou.lib.algorithms.StdOut;

/**
 * Created by huizhou on 8/5/14.
 */
public class MaxPQ<Key extends Comparable<Key>> {
    private Key[] pq;
    private int N = 0;

    public MaxPQ(int maxN){
        pq = (Key[]) new Comparable[maxN+1];
    }

    public int size(){
        return N;
    }

    private void swim(int k){
        while(k > 1 && less(k/2, k)){
            exch(k/2, k);
            k = k/2;
        }
    }

    private void sink(int k){
        while(2 * k <= N){
            int j = 2*k;
            if(j < N && less(j, j+1))
                j++;
            if(!less(k, j)) break;
            exch(k, j);
            k = j;
        }
    }

    private static void sink(Comparable[] a, int k, int N){
        while(2 * (k+1) <= N + 1) {
            int j = 2*(k+1)-1;
            if(j < N && less(a, j, j+1))
                j++;
            if(!less(a, k, j)) break;
            exch(a, k, j);
            k = j;

        }
    }


    public void insert(Key v){
        pq[++N] = v;
        swim(N);
    }

    public Key delMax(){
        Key max = pq[1];
        exch(1, N--);
        pq[N+1] = null;
        sink(1);
        return max;
    }

    public static void sort(Comparable[] a){
        int N = a.length - 1;
        for(int k = N/2; k >= 0; k--)
            sink(a, k, N);

        while(N > 0){
            exch(a, 0, N--);
            sink(a, 0, N);
        }

    }


    private boolean less(int i, int j){
        return pq[i].compareTo(pq[j]) < 0;
    }

    private static boolean less(Comparable[] a, int i, int j){
        return a[i].compareTo(a[j]) < 0;
    }

    private void exch(int i, int j){
        Key swap = pq[i];
        pq[i] = pq[j];
        pq[j] = swap;
    }

    private static void exch(Comparable[] a, int i, int j){
        Comparable swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }

    private static void show(Integer[] a) {
        for (int i = 0; i < a.length; i++) {
            StdOut.print(a[i] + " ");
        }
        System.out.println("");
    }

    public static void main(String[] args){
        Integer[] b = {-1, 7, 10, 9, 1,3,4,5, 8, -3, -7, 11, -5};
        MaxPQ.sort(b);
        show(b);
    }
}
