package com.dzhou.corejava;


import com.dzhou.lib.algorithms.In;
import com.dzhou.lib.algorithms.StdIn;
import com.dzhou.lib.algorithms.StdOut;

import java.util.Arrays;

/**
 * Created by davidzhou on 5/8/14.
 */

public class BinarySearch {
    public static int rank(int key, int[] a) {
        int lo = 0;
        int hi = a.length - 1;

        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (key < a[mid])
                hi = mid - 1;
            else if (key > a[mid])
                lo = mid + 1;
            else
                return mid;
        }
        return -1;
    }

    public static void main(String[] args) {

        // read the integers from a file
        In in = new In(args[0]);
        int[] whitelist = in.readAllInts();

        long l1, l2, l3, l4;
        // sort the array
        l1 = System.currentTimeMillis();
        Arrays.sort(whitelist);
        l2 = System.currentTimeMillis();

        // read integer key from standard input; print if not in whitelist
//        System.out.println("Please give a number");
//
        long numberOfKeys = 0;
        long numberFound = 0;

        l3 = System.currentTimeMillis();
        while (!StdIn.isEmpty()) {
            int key = StdIn.readInt();
            ++numberOfKeys;
            if (rank(key, whitelist) == -1) {


//                StdOut.println("Could not find " + key);
            } else {
                    ++numberFound;
//                StdOut.println("Found " + key);
            }

//            System.out.println("Please give a number");
        }
        l4 = System.currentTimeMillis();


        StdOut.println("WhiteList size is " + whitelist.length);
        StdOut.println("Search size is " + numberOfKeys);
        StdOut.println("Number found is " + numberFound);

        StdOut.println("Sort took " + (l2 - l1) + " milli sceonds");
        StdOut.println("Search took " + (l4 - l3) + " milli sceonds");



    }
}
