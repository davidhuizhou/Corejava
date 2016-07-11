package com.dzhou.corejava.elements_of_programming_interviews;

/**
 * Created by huizhou on 7/5/16.
 */
public class PrimitiveTypes {

  public static int countBits(int x) {
    int numBits = 0;
    int bitmask = 0x0001; // or int bitmask = 1;
    while (x != 0) {
      numBits += x & bitmask;
      x >>>= 1;           // have to use >>>, otherwise for negative number, infinit loop.
    }
    return numBits;
  }

  public static int parity(long l) {
    int result = 0;
    while (l != 0) {
      result ^= (l & 1);
      l >>>= 1;
    }
    return result;
  }

  public static int parity2(long l) {
    int result = 0;
    while (l != 0) {
      result ^= 1;
      l &= (l - 1);
    }
    return result;
  }


  public static void main(String[] args) {
    System.out.println(countBits(111));
    System.out.println(Integer.bitCount(111));
    System.out.println(countBits(-111));
    System.out.println(Integer.bitCount(-111));
    System.out.println(parity(111l));
    System.out.println(parity2(111l));
    System.out.println(parity(-111l));
    System.out.println(parity2(-111l));


  }
}
