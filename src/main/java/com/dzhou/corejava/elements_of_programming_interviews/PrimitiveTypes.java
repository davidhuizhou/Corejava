package com.dzhou.corejava.elements_of_programming_interviews;

/**
 * Created by huizhou on 7/5/16.
 */
public class PrimitiveTypes {

  public static int countBits(long x) {
    int numBits = 0;
    int bitmask = 0x0001; // or int bitmask = 1;
    while (x != 0) {
      numBits += x & bitmask;
      x >>>= 1;           // have to use >>>, otherwise for negative number, infinit loop.
    }
    return numBits;
  }

  public static int parity(long x) {
    int result = 0;
    while (x != 0) {
      result ^= (x & 1);
      x >>>= 1;
    }
    return result;
  }

  public static int parity2(long x) {
    int result = 0;
    while (x != 0) {
      result ^= 1;
      x &= (x - 1);
    }
    return result;
  }

  public static int parity3(long x) {
    final int kWordSize = 16;
    final int kBitMask = 0xFFFF;
    return preComputedParity[(int) (x >>> (3 * kWordSize))] ^
        preComputedParity[(int) (x >>> (2 * kWordSize) & kBitMask)] ^
        preComputedParity[(int) (x >>> (kWordSize) & kBitMask)] ^
        preComputedParity[(int) (x & kBitMask)];

  }

  public static int parity4(long x) {
    x ^= x >>> 32;
    x ^= x >>> 16;
    x ^= x >>> 8;
    x ^= x >>> 4;
    x ^= x >>> 2;
    x ^= x >>> 1;
    return (int) x & 0x0001;
  }

  public static long swapBits(long x, int i, int j) {
    // Extract the i-th and j-th bits, and see if they differ
    if (((x >>> i) & 1) != ((x >>> j) & 1)) {
      long bitMask = (1L << i) | (1L << j);
      x ^= bitMask;
    }
    return x;
  }

  public static long reverseBits(long x) {
    int i = 0;
    int j = 63;
    while (i < j) {
      x = swapBits(x, i++, j--);
    }
    return x;

  }

  public static int[] preComputedParity = new int[(int) (Math.pow(2, 16))];

  static {
    for (int i = 0; i < (int) Math.pow(2, 16); ++i) {
      preComputedParity[i] = parity2(i);
    }
  }


  public static void main(String[] args) {
    System.out.println(countBits(111));
    System.out.println(Integer.bitCount(111));
    System.out.println(countBits(-111));
    System.out.println(Integer.bitCount(-111));
    System.out.println(parity(111l));
    System.out.println(parity2(111l));
    System.out.println(parity3(111l));
    System.out.println(parity4(111l));
    System.out.println(parity(-111l));
    System.out.println(parity2(-111l));
    System.out.println(parity3(-111l));
    System.out.println(parity4(-111l));

    System.out.println("swapBits(1, 8)");
    long x1 = 1234L;
    System.out.println(Long.toBinaryString(x1));
    long x2 = swapBits(x1, 1, 8);
    System.out.println(Long.toBinaryString(x2));

    long x3 = 12344;

    System.out.println("x3 - 1");
    System.out.println(Long.toBinaryString(x3));
    System.out.println(Long.toBinaryString(x3 - 1));
    System.out.println(Long.toBinaryString(x3 & (x3 - 1)));
    System.out.println("~(x3 - 1)");
    System.out.println(Long.toBinaryString(x3 - 1));
    System.out.println(Long.toBinaryString(~(x3 - 1)));
    System.out.println(Long.toBinaryString(x3 & ~(x3 - 1)));

  }
}
