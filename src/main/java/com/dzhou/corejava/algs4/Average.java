package com.dzhou.corejava.algs4;
/*************************************************************************
 *  Compilation:  javac com.dzhou.corejava.algs4.Average.java
 *  Execution:    java com.dzhou.corejava.algs4.Average < data.txt
 *  Dependencies: StdIn.java StdOut.java
 *  
 *  Reads in a sequence of real numbers, and computes their average.
 *
 *  % java com.dzhou.corejava.algs4.Average
 *  10.0 5.0 6.0
 *  3.0 7.0 32.0
 *  <Ctrl-d>
 *  com.dzhou.corejava.algs4.Average is 10.5

 *  Note <Ctrl-d> signifies the end of file on Unix.
 *  On windows use <Ctrl-z>.
 *
 *************************************************************************/

import com.dzhou.lib.algorithms.StdIn;
import com.dzhou.lib.algorithms.StdOut;

/**
 *  The <tt>com.dzhou.corejava.algs4.Average</tt> class provides a client for reading in a sequence
 *  of real numbers and printing out their average.
 *  <p>
 *  For additional documentation, see <a href="http://algs4.cs.princeton.edu/11model">Section 1.1</a> of
 *  <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
 *
 *  @author Robert Sedgewick
 *  @author Kevin Wayne
 */
import com.dzhou.lib.algorithms.*;

public class Average { 

   // this class should not be instantiated
   private Average() { }

    /**
     * Reads in a sequence of real numbers from standard input and prints
     * out their average to standard output.
     */
    public static void main(String[] args) { 
        int count = 0;       // number input values
        double sum = 0.0;    // sum of input values

        // read data and compute statistics
        while (!StdIn.isEmpty()) {
            double value = StdIn.readDouble();
            sum += value;
            count++;
        }

        // compute the average
        double average = sum / count;

        // print results
        StdOut.println("com.dzhou.corejava.algs4.Average is " + average);
    }
}
