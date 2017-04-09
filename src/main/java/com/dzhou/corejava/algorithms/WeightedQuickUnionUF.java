package com.dzhou.corejava.algorithms;

/**
 * Created by huizhou on 7/22/14.
 */
public class WeightedQuickUnionUF {
  private int[] parent;
  private int[] size;
  private int count;

  public WeightedQuickUnionUF(int n) {
    parent = new int[n];
    size = new int[n];
    count = n;
    for (int i = 0; i < n; i++) {
      parent[i] = i;
      size[i] = 1;
    }
  }

  public int find(int p) {
    while (p != parent[p]) {
      p = parent[p];
    }
    return p;
  }

  public boolean connected(int p, int q) {
    return find(p) == find(q);
  }

  public void union(int p, int q) {
    int rootP = find(p);
    int rootQ = find(q);

    if (size[rootP] < size[rootQ]) {
      parent[rootP] = rootQ;
      size[rootQ] += size[rootP];
    } else {
      parent[rootQ] = rootP;
      size[rootP] += size[rootQ];
    }
    count--;
  }
}
