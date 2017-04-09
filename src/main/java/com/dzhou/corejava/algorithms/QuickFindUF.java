package com.dzhou.corejava.algorithms;

/**
 * Created by huizhou on 5/30/16.
 */
public class QuickFindUF {
  private int[] id;
  private int count;

  /**
   * Initializes an empty union-find data structure with {@code n} sites {@code 0} through {@code n
   * - 1}.
   */
  public QuickFindUF(int n) {
    count = n;
    id = new int[n];
    for (int i = 0; i < n; i++) {
      id[i] = i;
    }
  }

  public int count() {
    return count;
  }

  public int find(int p) {
    return id[p];
  }

  public boolean connected(int p, int q) {
    return id[p] == id[q];
  }

  /**
   * Merges the component containing site {@code p} with the component containing site {@code q}.
   */
  public void union(int p, int q) {
    int pId = id[p];
    int qId = id[q];

    if (pId == qId) {
      return;
    }

    for (int i = 0; i < id.length; i++) {
      if (id[i] == pId) {
        id[i] = qId;
      }
    }
    count--;
  }


}
