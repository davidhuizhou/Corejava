package com.dzhou.corejava.elements_of_programming_interviews;

import org.apache.commons.lang3.StringUtils;

/**
 * Created by huizhou on 7/5/16.
 */
public class PrimitiveTypes {

  public static short countBits(int x) {
    short numBits = 0;
    while (x != 0) {
      numBits += x & 1;
      x >>>= 1;
    }
    return numBits;
  }


  public static void testBitWiseOperations() {
    System.out.println(StringUtils.leftPad(Integer.toBinaryString(8), 32, '0'));
    System.out.println(StringUtils.leftPad(Integer.toBinaryString(8 >> 1), 32, '0'));

    System.out.println(StringUtils.leftPad(Integer.toBinaryString(-8), 32, '0'));
    System.out.println(StringUtils.leftPad(Integer.toBinaryString(-8 >> 1), 32, '0'));
    System.out.println(StringUtils.leftPad(Integer.toBinaryString(-8 >>> 1), 32, '0'));
    System.out.println(StringUtils.leftPad(Integer.toBinaryString(-8 >>> 2), 32, '0'));

    System.out.println(StringUtils.leftPad(Integer.toBinaryString(1 << 10), 32, '0'));
    System.out.println(StringUtils.leftPad(Integer.toBinaryString(~0), 32, '0'));

    System.out.println(StringUtils.leftPad(Integer.toBinaryString(15), 32, '0'));
    System.out.println(StringUtils.leftPad(Integer.toBinaryString(16), 32, '0'));
    System.out.println(StringUtils.leftPad(Integer.toBinaryString(15 ^ 16), 32, '0'));

    System.out.println(StringUtils.leftPad(Integer.toBinaryString(123), 32, '0'));
    System.out.println(StringUtils.leftPad(Integer.toBinaryString(122), 32, '0'));
    System.out.println(StringUtils.leftPad(Integer.toBinaryString(123 & 122), 32, '0'));
    System.out.println(StringUtils.leftPad(Integer.toBinaryString(~122), 32, '0'));
    System.out.println(StringUtils.leftPad(Integer.toBinaryString(123 & ~122), 32, '0'));

  }

  public static int parity(long x) {
    int result = 0;
    while (x != 0) {
      result ^= x & 0x0001;
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
    final int keyWordSize = 16;
    final int kBitMask = 0xFFFF;
    return preComputedParity[(int) (x >>> (3 * keyWordSize))]
        ^ preComputedParity[(int) (x >>> (2 * keyWordSize) & kBitMask)]
        ^ preComputedParity[(int) (x >>> keyWordSize & kBitMask)]
        ^ preComputedParity[(int) (x & kBitMask)];

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
    if ((x >>> i & 0x0001) != (x >>> j & 0x0001)) {
      long bitMask = (1 << i) | (1 << j);
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

  public static long reverseBits2(long x) {
    int i = 0;
    int j = 15;
    while (i < j) {
      swapBits(x, i++, j--);
    }
    return x;
  }

  public static long reverseBits3(long x) {
    final int keyWordSize = 16;
    final int kBitMask = 0xFFFF;
    return preComputedParity[(int) x & kBitMask] << (3 * keyWordSize) |
        preComputedReverse[(int) (x >>> keyWordSize) & kBitMask] << (2 * keyWordSize) |
        preComputedReverse[(int) (x >>> (2 * keyWordSize) & kBitMask)] << keyWordSize |
        preComputedReverse[(int) x >>> (3 * keyWordSize)];


  }

  public static int[] preComputedParity = new int[(int) (Math.pow(2, 16))];
  public static long[] preComputedReverse = new long[(int) (Math.pow(2, 16))];

  static {
    for (int i = 0; i < (int) Math.pow(2, 16); ++i) {
      preComputedParity[i] = parity2(i);
      preComputedReverse[i] = reverseBits2(i);
    }
  }


  static final int NUM_UNSIGN_BITS = 63;

  /**
   * Problem 5.4 - Find a closest integer with the same weight.
   *
   * Solution: Swap the two rightmost consecutive bits that differ.
   *
   * @see <a href="https://play.google.com/books/reader?printsec=frontcover&output=reader&id=ux3PCwAAQBAJ&pg=GBS.PA50">Find
   * a closest integer with the same weight.</a>
   */
  public static long closetIntSameBitCount(long x) {
    // x is assumed to be non-negative so we know the leading bit is 0.
    // We restrict to our attention to 63 LSBs.
    for (int i = 0; i < NUM_UNSIGN_BITS; ++i) {
      if (((x >>> i) & 1) != ((x >>> (i + 1) & 1))) {
        x ^= (1L << i) | (1L << (i + 1)); //Swap bit-i and bit-(i+1)
        return x;
      }
    }
    // Throw error if all bits of x are 0 or 1.
    throw new IllegalArgumentException("All bits are 0 or 1");
  }


  /**
   * Problem 5.5 - Compute x * y without arithmetical operators.
   *
   * The only operators allowed to use are:
   * assigment
   * the bitwise operators
   * equality checks and boolean combinations.
   *
   * @see <a href="https://play.google.com/books/reader?printsec=frontcover&output=reader&id=ux3PCwAAQBAJ&pg=GBS.PA51">Compute
   * x * y without arithmetical operators.</a>
   */
  public static long multiple(long x, long y) {
    long sum = 0;
    while (x != 0) {
      // Examines each bit of x
      if ((x & 1) != 0) {
        sum = add(sum, y);
        y <<= 1;
        x >>>= 1;
      }
    }
    return sum;
  }

  public static long add(long a, long b) {
    long sum = 0, carryin = 0, k = 1, tempA = a, tempB = b;
    while (tempA != 0 || tempB != 0) {
      long ak = a & k, bk = b & k;
      long carryout = (ak & bk) | (ak & carryin) | (bk & carryin);
      sum |= (ak ^ bk ^ carryin);
      carryin = carryout << 1;
      k <<= 1;
      tempA >>>= 1;
      tempB >>>= 1;
    }
    return sum | carryin;
  }

  /**
   * Problem 5.6 Compute x/y
   *
   * using only the addition, sub-traction, and shifting operators.
   */
  public static long divide(long x, long y) {
    long result = 0;
    int power = 32;
    long yPower = y << power;
    while (x >= y) {
      while (yPower > x) {
        yPower >>>= 1;
        --power;
      }

      result += 1L << power;
      x -= yPower;

    }
    return result;
  }

  /**
   * Problem 5.7 Compute x^y.  x is double and y is integer.
   * If the least signification bit of y is 0, x^y = (x ^ y/2)^2 = (x * x) ^y/2.
   * Otherwise, it is x * (x ^ y/2)^2 = x * (x * x) ^ y/2.
   */
  public static double power(double x, int y) {
    double result = 1.0;
    int power = y;
    if (y < 0) {
      power = -power;
      x = 1.0 / x;
    }

    while (power != 0) {
      if ((power & 1) != 0) {
        result *= x;
      }
      x *= x;
      power >>>= 1;
    }
    return result;
  }


  /**
   * Problem 5.8 Reverse digit.
   */
  public static long reverse(long x) {
    long result = 0;
    long xRemaining = Math.abs(x);
    while (xRemaining != 0) {
      result = result * 10 + xRemaining % 10;
    }
    return x < 0 ? -result : result;
  }

  /**
   * Problem 5.9 Check is a digit is palindrome.
   */
//  public static boolean isPalindromeNumber(int x) {
//    if (x < 0) {
//      return false;
//    }
//    final int numDigits = (int) (Math.floor(Math.log10(x))) + 1;
//    int msdMask = (int) Math.pow(10, numDigits - 1);
//    if (x / msdMask != x % 10) {
//      return false;
//    }
//    for (int i = 0; i < (numDigits / 2); ++i) {
//      x %= msdMask; // Remove the most significant digit of x.
//      x /= 10;      // Remove the least significant digit of x.
//      msdMask /= 100;
//    }
//    return true;
//  }
  public static boolean isPalindromeNumber(int x) {
    if (x < 0) return false;
    if (x < 10) return true;

    int div = 10;
    while (x / div >= 10) {
      div *= 10;
    }

    while (div >= 10) {
      int left = x / div;
      int right = x % 10;
      if (left != right) return false;

      x = (x % div) % 10;
      div /= 100;

    }
    return true;


  }

//  /**
//   * Problem 5. 10 Generate uniform random number between lowerBound to upperBound inclusively.
//   */
//  public static int uniformRandom(int lowerBound, int upperBound) {
//    int numberOfOutcomes = upperBound - lowerBound + 1, result;
//    do {
//      result = 0;
//      for (int i = 0; (1 << i) < numberOfOutcomes; ++i) {
//        result = (result << 1) | zeroOneRandom();
//      }
//    } while (result >= numberOfOutcomes);
//    return result + lowerBound;
//  }


  /**
   * Problem 5.11 Rectangle intersection.
   */
  private static class Rectangle {
    int x, y, width, height;

    public Rectangle(int x, int y, int width, int height) {
      this.x = x;
      this.y = y;
      this.width = width;
      this.height = height;
    }
  }


  public static Rectangle intersectRectangle(Rectangle R1, Rectangle R2) {
    if (!isIntersect(R1, R2)) {
      return null;
    }

    return new Rectangle(
        Math.max(R1.x, R2.x), Math.max(R1.y, R2.y),
        Math.min(R1.x + R1.width, R2.x + R2.width) - Math.max(R1.x, R2.x),
        Math.min(R1.y + R1.height, R2.y + R2.height) - Math.max(R1.y, R2.y)
    );
  }

  public static boolean isIntersect(Rectangle R1, Rectangle R2) {
    return R1.x <= R2.x + R2.width && R1.x + R1.width >= R2.x
        && R1.y <= R2.y + R2.height && R1.y + R1.height >= R2.y;
  }


  public static void main(String[] args) {
    // test countBits
    assert countBits(111) == Integer.bitCount(111);
    assert countBits(-111) == Integer.bitCount(-111);
    assert countBits(12345) == Integer.bitCount(12345);

    // test add
    assert add(1L, 2L) == (1L + 2L);
    assert add(123L, 789L) == (123L + 789L);
    assert add(9L, 789L) == (9L + 789L);

    // test multiple
    assert multiple(1L, 2L) == (1L * 2L);
    assert multiple(123L, 456L) == (123L * 456L);
    assert multiple(37L, 789L) == (37L * 789L);

    // test divid
    assert divide(1L, 1L) == 1L / 1L;
    assert divide(6L, 3L) == 6L / 3L;
    assert divide(12345L, 37L) == 12345L / 37L;

    // test power
    assert power(1.0d, 1) == Math.pow(1.0, 1L);
    assert power(12.3d, 3) == Math.pow(12.3d, 3L);

    // test reverse
    assert reverse(123L) == 321L;
    assert reverse(-123L) == -321L;
    assert reverse(0L) == 0L;

    // test palindrome number
    assert !isPalindromeNumber(-1);
    assert isPalindromeNumber(5);
    assert isPalindromeNumber(11);
    assert isPalindromeNumber(111);
    assert isPalindromeNumber(1223);
    assert !isPalindromeNumber(123);
    assert isPalindromeNumber(1234321);


//    System.out.println(parity(111l));
//    System.out.println(parity2(111l));
//    System.out.println(parity3(111l));
//    System.out.println(parity4(111l));
//    System.out.println(parity(-111l));
//    System.out.println(parity2(-111l));
//    System.out.println(parity3(-111l));
//    System.out.println(parity4(-111l));
//
//    System.out.println("swapBits(1, 8)");
//    long x1 = 1234L;
//    System.out.println(Long.toBinaryString(x1));
//    long x2 = swapBits(x1, 1, 8);
//    System.out.println(Long.toBinaryString(x2));
//
//    long x3 = 12344;
//
//    System.out.println("x3 - 1");
//    System.out.println(Long.toBinaryString(x3));
//    System.out.println(Long.toBinaryString(x3 - 1));
//    System.out.println(Long.toBinaryString(x3 & (x3 - 1)));
//    System.out.println("~(x3 - 1)");
//    System.out.println(Long.toBinaryString(x3 - 1));
//    System.out.println(Long.toBinaryString(~(x3 - 1)));
//    System.out.println(Long.toBinaryString(x3 & ~(x3 - 1)));
//
//    // Problem 5.5 - Compute x * y without arithmetical operators.
//    System.out.println(multiple(12L, 23L));
//    System.out.println(12 * 23);
//
//
//    // Problem 5.6 Compute x/y
//    System.out.println(divide(100, 9));
//
//    System.out.println("testBitWiseOperations");
//    testBitWiseOperations();
  }
}
