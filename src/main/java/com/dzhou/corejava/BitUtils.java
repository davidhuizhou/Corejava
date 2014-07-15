package com.dzhou.corejava;

/**
 * Created by huizhou on 7/10/14.
 */
public class BitUtils {
    private static String patchWithZeros(String s){
        int len = s.length();
        if(len == 64)
            return s;

        for(int i = 0; i < 64 - len; i++){
            s = "0" + s;
        }

        return s;
    }

    private static void printBits(String s){
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < s.length(); i++){
            sb.append(s.charAt(i));
            if(i < 63 && (i + 1) % 8 == 0)  {
                sb.append(" ");

            }

        }
        System.out.println(sb.toString());
    }

    private static String invertBinary(String s){
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < s.length(); i++){
            if(s.charAt(i) == '0')
                sb.append("1");
            else
                sb.append("0");
        }
        return sb.toString();
    }

    private static String addBinary(String s1, String s2){
        String s = "";
        s1 = patchWithZeros(s1);
        s2 = patchWithZeros(s2);

        int carryOver = 0;
        for(int i = 63; i >= 0; i--){
            int v1 = Integer.parseInt(s1.charAt(i) + "");
            int v2 = Integer.parseInt(s2.charAt(i) + "");

            s = (v1 + v2 + carryOver) % 2 + s;
            carryOver = (v1 + v2 + carryOver) / 2;
        }
        return s;
    }

    public static String longToBinary(long L){
        String s = "";
        if(L >= 0){
            while(L > 0){
                s = (L % 2) + s;
                L /= 2;
            }
            return patchWithZeros(s);
        } else {
            s = longToBinary((-1) * L);
            s = invertBinary(s);
            String s1 = longToBinary(1L);
            return addBinary(s, s1);
        }
    }

    public static long binaryToLong(String s){
        s = patchWithZeros(s);

        if(s.charAt(0) == '0'){
            long l = 0;
            for(int i = 0; i < s.length(); i++){
                l = 2 * l + (s.charAt(i) - '0');
            }
            return l;
        }else {
            String s1 = longToBinary(-1L);
            s = addBinary(s, s1);
            s = invertBinary(s);
            long l = (-1L) *  binaryToLong(s);
            return l;
        }
    }

    public static String doubleToBinary(double d, int percision){
        long l = (long)d;
        double f = d - l;
        String s1 = longToBinary(l);

        StringBuilder sb = new StringBuilder();
        int numOfBits = 0;
        while(numOfBits < percision){
            f = f * 2;
            if(f >= 1){
                sb.append("1");
                f -= 1.0;
            } else {
                sb.append("0");
            }
            numOfBits++;
        }
        return s1 + "." + sb.toString();

    }

    public static String toBitString(final double d) {
        final char[] bit = new char[64];
        final long dd = Double.doubleToLongBits(d);
        long mask = 1L;
        for(int i = 0; i < 64; i++) {
            final long bitval = dd & mask;
            if(bitval == 0) {
                bit[63 - i] = '0';
            } else {
                bit[63 - i] = '1';
            }
            mask <<= 1;
        }
        return String.valueOf(bit);
    }

    public static int getNumberOfOnesInBinary(long L){
//        int retVal = 0;
//        while(L > 0){
//            retVal += L %2;
//            L /= 2;
//        }
//        return retVal;
        int b = 0;
        if(L >= 0) {
            while (L != 0) {
                ++b;
                L &= (L - 1);
            }
            return b;

        } else {
//            return (64 - getNumberOfOnesInBinary(~L));
            if(L == -1)
                return 64;

            while (L != -1) {
                ++b;
                L |= (L + 1);
            }
            return 64 - b;

        }



    }

    private static long getBit(long L, int index){
        return (L >> index) & 1L;
    }

    private static long setBit(long L, int index, long bit){
        bit = bit << index;
        long mask = L & (~(1L << index));
        return (L & mask) | bit;
    }

    //swap i and j bit, i < j
    public static long swapBits(Long L, int i, int j){

        if(i == j)
            return L;

        long bitI = getBit(L, i);
        long bitJ = getBit(L, j);

        L = setBit(L, j, bitI);
        L = setBit(L, i, bitJ);

        return L;


    }

    public static int getMaxInt(){
        int i = ~(1 << 31);
        return i;
    }

    public static int getMinInt(){
        int i = (1 << 31);
        return i;
    }

    public static void main(String[] args){
        printBits(patchWithZeros("101"));
        printBits(invertBinary(patchWithZeros("101")));
        printBits(addBinary(patchWithZeros("101"), patchWithZeros("1101")));
        printBits(longToBinary(0L));
        printBits(longToBinary(1L));
        printBits(longToBinary(2L));
        printBits(longToBinary(3L));
        printBits(longToBinary(4L));
        printBits(longToBinary(5L));
        printBits(longToBinary(6L));
        printBits(longToBinary(7L));
        printBits(longToBinary(8L));


        System.out.println("100L");

        printBits(patchWithZeros(Long.toBinaryString(100L)));
        printBits(longToBinary(100L));
//        printBits(longToBinary(getBit(100L, 0)));
//        printBits(longToBinary(getBit(100L, 1)));
//        printBits(longToBinary(getBit(100L, 2)));
//        printBits(longToBinary(getBit(100L, 3)));
//        printBits(longToBinary(setBit(100L, 2, 0L)));
//        printBits(longToBinary(setBit(100L, 2, 1L)));
        printBits(longToBinary(swapBits(100L, 0, 1)));
        printBits(longToBinary(swapBits(100L, 0, 2)));
        printBits(longToBinary(swapBits(100L, 2, 5)));


        System.out.println("-100L");
        printBits(patchWithZeros(Long.toBinaryString(-100L)));
        printBits(longToBinary(-100L));
//        printBits(longToBinary(getBit(-100L, 0)));
//        printBits(longToBinary(getBit(-100L, 1)));
//        printBits(longToBinary(getBit(-100L, 2)));
//        printBits(longToBinary(getBit(-100L, 3)));
//        printBits(longToBinary(setBit(-100L, 2, 0L)));
//        printBits(longToBinary(setBit(-100L, 2, 1L)));
        printBits(longToBinary(swapBits(-100L, 0, 1)));
        printBits(longToBinary(swapBits(-100L, 0, 2)));
        printBits(longToBinary(swapBits(-100L, 5, 2)));
//
//
//
        printBits(patchWithZeros(Long.toBinaryString(-1L)));
//        printBits(longToBinary(-1L));
//
//
//        System.out.println(getNumberOfOnesInBinary(7L));
//        System.out.println(getNumberOfOnesInBinary(100L));
//        System.out.println(getNumberOfOnesInBinary(-100L));
//
//        System.out.println(getBit(-100L, 3));
//        System.out.println(getNumberOfOnesInBinary(-1L));

        System.out.println(binaryToLong("000"));
        System.out.println(binaryToLong("001"));
        System.out.println(binaryToLong("111"));
        System.out.println(binaryToLong(longToBinary(100L)));
        System.out.println(binaryToLong(longToBinary(-100L)));
        System.out.println(binaryToLong(longToBinary(10101L)));
        System.out.println(binaryToLong(longToBinary(-12345L)));

        System.out.println(doubleToBinary(3.14159, 10));
        System.out.println(toBitString(3.14159));

        System.out.println(Integer.MAX_VALUE);
        System.out.println(getMaxInt());
        System.out.println(Integer.MIN_VALUE);
        System.out.println(Integer.toBinaryString(Integer.MIN_VALUE));
        System.out.println(getMinInt());





    }
}
