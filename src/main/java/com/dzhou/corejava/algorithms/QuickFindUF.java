package com.dzhou.corejava.algorithms;

/**
 * Created by huizhou on 5/30/16.
 */
public class QuickFindUF {
  private int[] id;
  private int count;

  public QuickFindUF(int N) {
    count = N;
    id = new int[N];
    for (int i = 0; i < N; i++) {
      id[i] = i;
    }
  }

  public int find(int p) {
    return id[p];
  }

  public boolean connected(int p, int q) {
    return id[p] == id[q];
  }

  public void union(int p, int q) {
    if (connected(p, q)) {
      return;
    }
    int pid = id[p];
    for (int i = 0; i < id.length; i++) {
      if (id[i] == pid) {
        id[i] = id[q];
      }
    }
    count--;
  }
}
