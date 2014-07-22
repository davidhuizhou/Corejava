package com.dzhou.corejava.algorithms;

/**
 * Created by huizhou on 7/22/14.
 */
public class WeightedQuickUnionUF {
    private int[] id;
    private int[] sz;
    private int count;

    public WeightedQuickUnionUF(int N) {
        count = N;
        id = new int[N];
        for(int i = 0; i < N; i++) id[i] = i;
        sz= new int[N];
        for(int i = 0; i < N; i++) sz[i] = 1;

    }

    public int count(){
        return count;
    }

    public int find(int i){
        while( i != id[i]){
            id[i] = id[id[i]];
            i = id[i];
        }
        return i;
    }

    public boolean connected(int p, int q){
        return find(p) == find(q);
    }

    public void union(int p, int q) {
        int i = find(p);
        int j = find(q);

        if(i == j) return;
        if(sz[i] < sz[j]){
            id[i] = j;
            sz[j] += sz[i];
        } else {
            id[j] = i;
            sz[i] += sz[j];
        }
        count--;
    }
}
