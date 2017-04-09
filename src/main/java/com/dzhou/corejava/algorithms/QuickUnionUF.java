package com.dzhou.corejava.algorithms;

/**
 * Created by huizhou on 7/4/16.
 */
public class QuickUnionUF {
  private int[] parent;
  private int count;

  public QuickUnionUF(int n) {
    count = n;
    parent = new int[n];
    for (int i = 0; i < n; i++) {
      parent[i] = i;
    }
  }

  public int find(int p) {
    validate(p);
    while (p != parent[p]) {
      p = parent[p];
    }
    return p;
  }

  private void validate(int p) {
    int n = parent.length;
    if (p < 0 | p >= n) {
      throw new IndexOutOfBoundsException("index " + p + " is not between 0 and " + (n - 1));
    }
  }

  public boolean connected(int p, int q) {
    return find(p) == find(q);
  }

  public void union(int p, int q) {
    int rootP = find(p);
    int rootQ = find(q);
    if (rootP == rootQ) {
      return;
    }
    parent[p] = rootQ;
    count--;
  }
}
