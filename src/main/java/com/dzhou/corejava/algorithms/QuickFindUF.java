package com.dzhou.corejava.algorithms;

/**
 * Created by huizhou on 5/30/16.
 */
public class QuickFindUF {
  private int[] id;
  private int count;

  /**
   * Initializes an empty union-find data structure with N isolated components 0 through N-1.
   *
   * @param N the number of objects
   * @trhwos java.lang.IllegalArgumentsException if N < 0
   */
  public QuickFindUF(int N) {
    count = N;
    id = new int[N];
    for (int i = 0; i < N; i++) {
      id[i] = i;
    }
  }

  /**
   * Returns the number of components;
   *
   * @return the number of components (between 1 and N)
   */
  public int count() {
    return count;
  }

  /**
   * Returns the component identifier for the component containing site <tt>p</tt>.
   *
   * @param p the integer representing one site
   * @return the component identifier for the component containing site <tt>p</tt>
   * @throws java.lang.IndexOutOfBoundsException unless 0 <= p < N
   */
  public int find(int p) {
    return id[p];
  }

  /**
   * Are the two sites <tt>p</tt> and <tt>q</tt> in the same component?
   *
   * @param p the integer representing one site
   * @param q the integer representing the other site
   */
  public boolean connected(int p, int q) {
    return id[p] == id[q];
  }

  public void union(int p, int q) {
    if (connected(p, q))
      return;
    int pid = id[p];
    for (int i = 0; i < id.length; i++) {
      if (id[i] == pid) {
        id[i] = id[q];
      }
    }
    count--;
  }


}
