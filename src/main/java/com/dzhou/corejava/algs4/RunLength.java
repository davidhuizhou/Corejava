package com.dzhou.corejava.algs4;

/*************************************************************************
 *  Compilation:  javac com.dzhou.corejava.algs4.RunLength.java
 *  Execution:    java com.dzhou.corejava.algs4.RunLength - < input.txt   (compress)
 *  Execution:    java com.dzhou.corejava.algs4.RunLength + < input.txt   (expand)
 *  Dependencies: BinaryIn.java BinaryOut.java
 *
 *  Compress or expand binary input from standard input using
 *  run-length encoding.
 *
 *  % java com.dzhou.corejava.algs4.BinaryDump 40 < 4runs.bin
 *  0000000000000001111111000000011111111111
 *  40 bits
 *
 *  This has runs of 15 0s, 7 1s, 7 0s, and 11 1s.
 *
 *  % java com.dzhou.corejava.algs4.RunLength - < 4runs.bin | java com.dzhou.corejava.algs4.HexDump
 *  0f 07 07 0b
 *  4 bytes
 *
 *************************************************************************/
import com.dzhou.lib.algorithms.*;

public class RunLength {
    private static final int R   = 256;
    private static final int lgR = 8;

    public static void expand() { 
        boolean b = false; 
        while (!BinaryStdIn.isEmpty()) {
            int run = BinaryStdIn.readInt(lgR);
            for (int i = 0; i < run; i++)
                BinaryStdOut.write(b);
            b = !b;
        }
        BinaryStdOut.close();
    }

    public static void compress() { 
        char run = 0; 
        boolean old = false;
        while (!BinaryStdIn.isEmpty()) { 
            boolean b = BinaryStdIn.readBoolean();
            if (b != old) {
                BinaryStdOut.write(run, lgR);
                run = 1;
                old = !old;
            }
            else { 
                if (run == R-1) { 
                    BinaryStdOut.write(run, lgR);
                    run = 0;
                    BinaryStdOut.write(run, lgR);
                }
                run++;
            } 
        } 
        BinaryStdOut.write(run, lgR);
        BinaryStdOut.close();
    }


    public static void main(String[] args) {
        if      (args[0].equals("-")) compress();
        else if (args[0].equals("+")) expand();
        else throw new IllegalArgumentException("Illegal command line argument");
    }

}
