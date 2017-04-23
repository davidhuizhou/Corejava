package com.dzhou.corejava.algorithms;

import com.google.common.base.Joiner;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Created by huizhou on 11/6/16.
 */
public class TestOne {

  @Test
  public void testBinarySearch() {
    int[] a = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    assertEquals(6, BinarySearch.indexOf(a, 7));
    assertEquals(-1, BinarySearch.indexOf(a, 11));
  }

  @Test
  public void testShuffle() {
    String[] a = new String[]{"a", "b", "c", "d", "e", "f", "g"};
    for (int i = 0; i < 5; i++) {
      Knuth.shuffle(a);
      System.out.println(Joiner.on(",").join(a));
    }
  }

//  @Test
//  public void testQuickFindUF() {
//    int n = 10;
//    QuickFindUF uf = new QuickFindUF(n);
//    System.out.println(uf.count() + " components");
//    uf.union(0, 2);
//    System.out.println(uf.count() + " components");
//    uf.union(1, 5);
//    System.out.println(uf.count() + " components");
//    uf.union(4, 8);
//    System.out.println(uf.count() + " components");
//    uf.union(6, 9);
//    System.out.println(uf.count() + " components");
//    uf.union(2, 5);
//    System.out.println(uf.count() + " components");
//    assertEquals(5, uf.count());
//  }
//
//  @Test
//  public void testQuickUnionUF() {
//    int n = 10;
//    QuickUnionUF uf = new QuickUnionUF(n);
//    System.out.println(uf.count() + " components");
//    uf.union(0, 2);
//    System.out.println(uf.count() + " components");
//    uf.union(1, 5);
//    System.out.println(uf.count() + " components");
//    uf.union(4, 8);
//    System.out.println(uf.count() + " components");
//    uf.union(6, 9);
//    System.out.println(uf.count() + " components");
//    uf.union(2, 5);
//    System.out.println(uf.count() + " components");
//    assertEquals(5, uf.count());
//  }
}
