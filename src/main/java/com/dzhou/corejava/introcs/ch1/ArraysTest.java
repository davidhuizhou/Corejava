package com.dzhou.corejava.introcs.ch1;

/**
 * Created by huizhou on 11/6/15.
 */
public class ArraysTest {
    public static void main(String[] args) {
        int[] a = new int[10];
        for (int i = 0; i < 10; i++)
            a[i] = 9 - i;
        for (int i = 0; i < 10; i++)
            a[i] = a[a[i]];
        for (int i = 0; i < 10; i++)
            System.out.print(a[i] + " ");

        System.out.println("");

        int[][] a1 = {
                {1, 2, 3},
                {4, 5},
                {6},
                {7, 8, 9, 10},
                {11, 12},
                {13}
        };

        int[][] b = new int[a1.length][];

        System.out.println("{");
        for (int i = 0; i < a1.length; i++) {
            b[i] = new int[a1[i].length];
            System.out.print("  {");
            for (int j = 0; j < a1[i].length; j++) {
                if (j < a1[i].length - 1)
                    System.out.print(a1[i][j] + ", ");
                else
                    System.out.print(a1[i][j]);
                b[i][j] = a1[i][j];
            }
            System.out.println("}");
        }
        System.out.println("}");

        System.out.println("{");
        for (int i = 0; i < a1.length; i++) {
            System.out.print("  {");
            for (int j = 0; j < b[i].length; j++) {
                if (j < b[i].length - 1)
                    System.out.print(b[i][j] + ", ");
                else
                    System.out.print(b[i][j]);
            }
            System.out.println("}");
        }
        System.out.println("}");


        int N = 5;
        int[][] a2 = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                a2[i][j] = N * i + j;

            }
        }

        System.out.println("Before");
        System.out.println("-----");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.printf("%4d", a2[i][j]);
            }
            System.out.println();
        }

        //transpose in-place
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                int temp = a2[i][j];
                a2[i][j] = a2[j][i];
                a2[j][i] = temp;
            }
        }

        System.out.println("After");
        System.out.println("-----");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.printf("%4d", a2[i][j]);
            }
            System.out.println();
        }

        System.out.println("\n\n");
        int[][] a3 = {
                {99, 98, 92, 94, 99, 90, 76, 92, 97, 89},
                {85, 57, 77, 32, 34, 46, 59, 66, 71, 29},
                {98, 78, 76, 11, 22, 54, 88, 89, 24, 38}
        };

        for (int i = 0; i < a3.length; i++) {
            for (int j = 0; j < a3[i].length; j++) {
                System.out.printf("%4d", a3[i][j]);
            }
            System.out.println();
        }

        int[][] b3 = new int[a3[0].length][a3.length];

        for (int i = 0; i < a3.length; i++) {
            for (int j = 0; j < a3[i].length; j++) {
                b3[j][i] = a3[i][j];
            }
        }
        System.out.println("\n\n");
        for (int i = 0; i < b3.length; i++) {
            for (int j = 0; j < b3[i].length; j++) {
                System.out.printf("%4d", b3[i][j]);
            }
            System.out.println();
        }
    }
}
