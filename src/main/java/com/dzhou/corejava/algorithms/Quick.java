package com.dzhou.corejava.algorithms;

/**
 * Created by huizhou on 7/28/14.
 */
import com.dzhou.lib.algorithms.StdIn;
import com.dzhou.lib.algorithms.StdOut;
import com.dzhou.lib.algorithms.StdRandom;

/**
 *  The <tt>com.dzhou.corejava.algs4.Quick</tt> class provides static methods for sorting an
 *  array and selecting the ith smallest element in an array using quicksort.
 *  <p>
 *  For additional documentation, see <a href="http://algs4.cs.princeton.edu/21elementary">Section 2.1</a> of
 *  <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
 *
 *  @author Robert Sedgewick
 *  @author Kevin Wayne
 */
public class Quick {

    // This class should not be instantiated.
    private Quick() { }

    /**
     * Rearranges the array in ascending order, using the natural order.
     * @param a the array to be sorted
     */
    public static void sort(int[] a) {
//        StdRandom.shuffle(a);
        sort(a, 0, a.length - 1);
    }

    // quicksort the subarray from a[lo] to a[hi]
    private static void sort(int[] a, int lo, int hi) {
        if (hi <= lo) return;
        int j = partition(a, lo, hi);
        sort(a, lo, j-1);
        sort(a, j+1, hi);

    }

    // partition the subarray a[lo..hi] so that a[lo..j-1] <= a[j] <= a[j+1..hi]
    // and return the index j.
    private static int partition(int[] a, int lo, int hi) {
        int i = lo;
        int j = hi + 1;
        int v = a[lo];
        while (true) {

            // find item on lo to swap
            while (a[++i] < v)
                if (i == hi) break;

            // find item on hi to swap
            while (a[--j] > v)
                if (j == lo) break;      // redundant since a[lo] acts as sentinel

            // check if pointers cross
            if (i >= j) break;

            exch(a, i, j);
        }

        // put partitioning item v at a[j]
        exch(a, lo, j);

        // now, a[lo .. j-1] <= a[j] <= a[j+1 .. hi]
        return j;
    }

    /**
     * Rearranges the array so that a[k] contains the kth smallest key;
     * a[0] through a[k-1] are less than (or equal to) a[k]; and
     * a[k+1] through a[N-1] are greater than (or equal to) a[k].
     * @param a the array
     * @param k find the kth smallest
     */
    public static Comparable select(int[] a, int k) {
        if (k < 0 || k >= a.length) {
            throw new IndexOutOfBoundsException("Selected element out of bounds");
        }
        StdRandom.shuffle(a);
        int lo = 0, hi = a.length - 1;
        while (hi > lo) {
            int i = partition(a, lo, hi);
            if      (i > k) hi = i - 1;
            else if (i < k) lo = i + 1;
            else return a[i];
        }
        return a[lo];
    }



    /***********************************************************************
     *  Helper sorting functions
     ***********************************************************************/

    // is v < w ?
    private static boolean less(Comparable v, Comparable w) {
        return (v.compareTo(w) < 0);
    }

    // exchange a[i] and a[j]
    private static void exch(int[] a, int i, int j) {
        int swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }


    /***********************************************************************
     *  Check if array is sorted - useful for debugging
     ***********************************************************************/
    private static boolean isSorted(Comparable[] a) {
        return isSorted(a, 0, a.length - 1);
    }

    private static boolean isSorted(Comparable[] a, int lo, int hi) {
        for (int i = lo + 1; i <= hi; i++)
            if (less(a[i], a[i-1])) return false;
        return true;
    }


    // print array to standard output
    private static void show(int[] a) {
        for (int i = 0; i < a.length; i++) {
            StdOut.print(a[i] + " ");
        }
    }

    /**
     * Reads in a sequence of strings from standard input; quicksorts them;
     * and prints them to standard output in ascending order.
     * Shuffles the array and then prints the strings again to
     * standard output, but this time, using the select method.
     */
    public static void main(String[] args) {
        int[] a = {4, 10,10,8,9,9,5,4,4,4,4,8,9,10, 7, 3, 2, 1, 4, 4, 1, 6, 4, 4, 4, 12,3,2,2,2,1,1,3,3,3, 10};
        Quick.sort(a);
        show(a);


    }

}