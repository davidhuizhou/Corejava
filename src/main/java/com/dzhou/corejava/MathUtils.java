package com.dzhou.corejava;

import java.util.Arrays;

/**
 * Created by huizhou on 5/10/14.
 */
public class MathUtils {
    public static final String ABOVETOPHISTOGRAM = "          ";
    public static final String TOPHISTOGRAM = " ___      ";
    public static final String BELOWTOPHISTOGRAM = "|   |     ";


    public static double sqrt(double c) {
        if (c < 0)
            return Double.NaN;

        double err = 1e-15;
        double t = c;

        while (Math.abs(t - c / t) > err * t) {
            System.out.println("t=" + t);
            t = (c / t + t) / 2.0;
        }
        return t;
    }

    public static boolean isPrime(int N) {
        if (N < 2)
            return false;

        for (int i = 2; i * i <= N; i++) {
            if (N % i == 0)
                return false;

        }
        return true;
    }

    public static double harmonicNumber(int N) {
        double sum = 0.0;
        for (int i = 1; i <= N; i++) {
            sum += 1.0 / i;
        }
        return sum;
    }

    public static String getBinaryString(int N) {
        String s = "";
        for (int i = N; i > 0; i = i / 2) {
            s = (i % 2) + s;
        }
        return s;
    }

    public static int lg(int N) {
        if (N <= 0)
            return Integer.MIN_VALUE;

        else if (N == 1)
            return 0;
        else if (N == 2)
            return 1;

        else {
//            int result = 1;
//            int i;
//            for (i = 1; i <= N; i++){
//                result *= 2;
//                if(result > N)
//                    break;
//            }
//            return i - 1;
            int n = N, i = 0;
            while (n > 1) {
                n = n / 2;
                i++;
            }
            return i;
        }

    }

    public static int[] histogram(int[] a, int M) {
        int[] result = new int[M];

        if (a.length == 0)
            return result;

        for (int i = 1; i <= M; i++) {
            for (int j = 0; j < a.length; j++) {
                if (a[j] == i)
                    ++result[i];
            }
        }

        return result;

    }

    public static String getHistogram(int number, int position) {

        if (number == 0 || number < position)
            return ABOVETOPHISTOGRAM;
        else if (number == position)
            return TOPHISTOGRAM;
        else
            return BELOWTOPHISTOGRAM;


    }

    public static void printHistogram(int[] a) {
        int max = a[0];

        for (int i = 0; i < 10; i++) {
            if (a[i] > max)
                max = a[i];
        }


        for (int i = max + 2; i >= -1; i--) {

            if (i == -1) {
                for (int j = 0; j < 120; j++)
                    System.out.print("-");
            } else {
                System.out.print("|         ");

                for (int j = 0; j < 10; j++) {
                    System.out.print(getHistogram(a[j], i));
                }

            }
            System.out.println("");


        }
        System.out.print("\n          ");
        System.out.print("zero      ");
        System.out.print("one       ");
        System.out.print("two       ");
        System.out.print("three     ");
        System.out.print("four      ");
        System.out.print("five      ");
        System.out.print("six       ");
        System.out.print("seven     ");
        System.out.print("eight     ");
        System.out.print("nine      ");


    }

    public static void main(String[] args) {
        System.out.println(sqrt(2.0));
        System.out.println(sqrt(0.5));
        System.out.println(isPrime(7));
        System.out.println(isPrime(23));
        System.out.println(isPrime(81));
        System.out.println(harmonicNumber(81));
        System.out.println("&&&&&&");
        System.out.println(getBinaryString(7));
        System.out.println(getBinaryString(16));

        int[] a = new int[10];
        for (int i = 0; i < 10; i++)
            a[i] = 9 - i;
        for (int i = 0; i < 10; i++)
            System.out.print(a[i] + " ");

        System.out.println("\n");

        for (int i = 0; i < 10; i++)
            a[i] = a[a[i]];

        for (int i = 0; i < 10; i++)
            System.out.print(a[i] + " ");

        System.out.println();
        System.out.println(lg(0));
        System.out.println(lg(1));
        System.out.println(lg(2));
        System.out.println(lg(3));
        System.out.println(lg(4));
        System.out.println(lg(7));
        System.out.println(lg(8));
        System.out.println(lg(31));
        System.out.println(lg(64));

        int[] b = {1, 2, 2, 3, 3, 3, 4, 4, 4, 4, 5, 6, 6, 7, 7, 7, 8, 8, 8, 8, 8, 8, 8, 9, 9};
        int[] c = histogram(b, 12);
        System.out.println(Arrays.toString(c));

        printHistogram(c);
    }
}
