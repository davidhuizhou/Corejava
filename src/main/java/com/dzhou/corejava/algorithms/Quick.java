package com.dzhou.corejava.algorithms;

/**
 * Created by huizhou on 7/28/14.
 */
import com.dzhou.lib.algorithms.StdIn;
import com.dzhou.lib.algorithms.StdOut;
import com.dzhou.lib.algorithms.StdRandom;

import java.util.Random;

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

    private static Random random;    // pseudo-random number generator
    private static long seed;        // pseudo-random number generator seed

    // static initializer
    static {
        // this is how the seed was set in Java 1.4
        seed = System.currentTimeMillis();
        random = new Random(seed);
    }

    // This class should not be instantiated.
    private Quick() { }

    /**
     * Rearranges the array in ascending order, using the natural order.
     * @param a the array to be sorted
     */
    public static void sort(Comparable[] a) {
//        StdRandom.shuffle(a);
        shuffle(a);
        sort(a, 0, a.length - 1);
    }

    private static void shuffle(Object[] a){
        int N = a.length;
        Random random = new Random(System.currentTimeMillis());
        for(int i = 0; i < N; i++){

            int r = random.nextInt(N - i);
            exch(a, i, i + r);
        }

    }

    // quicksort the subarray from a[lo] to a[hi]
    private static void sort(Comparable[] a, int lo, int hi) {
        if(lo >= hi) return;
        int j = partition(a, lo, hi);
        sort(a, lo, j-1);
        sort(a, j+1, hi);

    }

    // partition the subarray a[lo..hi] so that a[lo..j-1] <= a[j] <= a[j+1..hi]
    // and return the index j.
    private static int partition(Comparable[] a, int lo, int hi) {
        int i = lo, j = hi + 1;
        Comparable v = a[lo];
        while(true){
            while(less(a[++i], v)) if (i == hi) break;
            while(less(v, a[--j])) if (j == lo) break;

            if(i >= j) break;
            exch(a, i, j);
        }
        exch(a, lo, j);
        return j;

    }

    private static void sortThreeWay(Comparable[] a, int lo, int hi){
        if(lo >= hi) return;
        int lt = lo, i = lo + 1, gt = hi;
        Comparable v = a[lo];
        while(i <= gt) {
            if(less(a[i], v)) exch(a, lt++, i++);
            else if (less(v, a[i])) exch(a, i, gt--);
            else i++;
        }
        sortThreeWay(a, lo, lt - 1);
        sortThreeWay(a, gt + 1, hi);

    }
    /**
     * Rearranges the array so that a[k] contains the kth smallest key;
     * a[0] through a[k-1] are less than (or equal to) a[k]; and
     * a[k+1] through a[N-1] are greater than (or equal to) a[k].
     * @param a the array
     * @param k find the kth smallest
     */
    public static Comparable select(Comparable[] a, int k) {
        if(k < 0 || k >= a.length)
            throw new IndexOutOfBoundsException("Selected element out of bounds");
        shuffle(a);
        int lo = 0, hi = a.length - 1;
        while(lo < hi) {
            int i = partition(a, lo, hi);
            if(i > k) hi = i - 1;
            else if(i < k) lo = i + 1;
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
    private static void exch(Object[] a, int i, int j) {
        Object swap = a[i];
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
    private static void show(Object[] a) {
        for (int i = 0; i < a.length; i++) {
            StdOut.print(a[i] + " ");
        }
        System.out.println("");
    }



    /**
     * Reads in a sequence of strings from standard input; quicksorts them;
     * and prints them to standard output in ascending order.
     * Shuffles the array and then prints the strings again to
     * standard output, but this time, using the select method.
     */
    public static void main(String[] args) {
        Integer[] a = {4, 10,10,8,9,9,5,4,4,4,4,8,9,10, 7, 3, 2, 1, 4, 4, 1, 6, 4, 4, 4, 12,3,2,2,2,1,1,3,3,3, 10};
        shuffle(a);
        show(a);
        System.out.println("");
        Quick.sort(a);
        show(a);
        shuffle(a);
        show(a);
        sortThreeWay(a, 0, a.length -1);
        show(a);

        shuffle(a);
        show(a);





    }

}