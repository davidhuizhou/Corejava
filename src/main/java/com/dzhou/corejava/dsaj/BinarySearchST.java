package com.dzhou.corejava.dsaj;

import com.dzhou.corejava.algs4.Queue;

/**
 * Created by davidzhou on 8/27/14.
 */
public class BinarySearchST<Key extends Comparable<Key>, Value> {
    private static final int INIT_CAPACITY = 2;
    private Key[] keys;
    private Value[] vals;
    private int N = 0;

    public BinarySearchST() {
        this(INIT_CAPACITY);
    }

    public BinarySearchST(int capacity){
        keys = (Key[]) new Comparable[capacity];
        vals = (Value[]) new Object[capacity];
    }

    private void resize(int capacity) {
        Key[] tempk = (Key[]) new Comparable[capacity];
        Value[] tempv = (Value[]) new Object[capacity];

        for (int i = 0; i < N; i++) {
            tempk[i] = keys[i];
            tempv[i] = vals[i];
        }
        keys = tempk;
        vals = tempv;
    }

    public boolean contains(Key key) {
        return get(key) != null;
    }

    public int size() {
        return N;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public Value get(Key key) {
        if(isEmpty()) return null;
        int i = rank(key);
        if(i < N && keys[i].compareTo(key) == 0) return vals[i];
        return null;

    }

    public int rank(Key key) {
        int lo = 0, hi = N - 1;
        while (lo <= hi) {
            int m = lo + (hi - lo) / 2;
            int cmp = key.compareTo(keys[m]);
            if (cmp < 0) hi = m - 1;
            else if (cmp > 0) lo = m + 1;
            else return m;
        }
        return lo;
    }

    public void put(Key key, Value val) {
        if (val == null) {
            delete(key);
            return;
        }

        int i = rank(key);
        if (i < N && keys[i].compareTo(key) == 0) {
            vals[i] = val;
            return;
        }

        if (N == keys.length) resize(2 * keys.length);

        for (int j = N; j > i; j--) {
            keys[j] = keys[j - 1];
            vals[j] = vals[j - 1];
        }
        keys[i] = key;
        vals[i] = val;
        N++;

    }

    public void delete(Key key) {
        if(isEmpty()) return;

        int i = rank(key);
        if(i == N || keys[i].compareTo(key) != 0) return;

        for(int j = i; j < N - 1; j++){
            keys[j] = keys[j+1];
            vals[j] = vals[j+1];
        }
        keys[N-1] = null;
        vals[N-1] = null;
        N--;

        if(N > 0 && N == keys.length/4) resize(keys.length/2);
        
    }

    public Key floor(Key key){
        int i = rank(key);
        if(i < N && keys[i].compareTo(key) == 0) return keys[i];
        if(i == 0) return null;
        else return keys[i-1];
    }

    public Key ceiling(Key key) {
        int i = rank(key);
        if(i == N) return null;
        else return keys[i];

    }

    public int size(Key lo, Key hi){
        if(lo.compareTo(hi) > 0) return 0;
        if(contains(hi)) return rank(hi) - rank(lo) + 1;
        else return rank(hi) - rank(lo);
    }


}
