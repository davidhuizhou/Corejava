package com.dzhou.corejava.algs4;

/*************************************************************************
 *  Compilation:  javac com.dzhou.corejava.algs4.Genome.java
 *  Execution:    java com.dzhou.corejava.algs4.Genome - < input.txt   (compress)
 *  Execution:    java com.dzhou.corejava.algs4.Genome + < input.txt   (expand)
 *  Dependencies: BinaryIn.java BinaryOut.java
 *
 *  Compress or expand a genomic sequence using a 2-bit code.
 *
 *  % more genomeTiny.txt
 *  ATAGATGCATAGCGCATAGCTAGATGTGCTAGC
 *
 *  % java com.dzhou.corejava.algs4.Genome - < genomeTiny.txt | java com.dzhou.corejava.algs4.Genome +
 *  ATAGATGCATAGCGCATAGCTAGATGTGCTAGC
 *
 *************************************************************************/
import com.dzhou.lib.algorithms.*;

public class Genome {

    public static void compress() { 
        Alphabet DNA = new Alphabet("ACTG");
        String s = BinaryStdIn.readString();
        int N = s.length();
        BinaryStdOut.write(N);

        // Write two-bit code for char. 
        for (int i = 0; i < N; i++) {
            int d = DNA.toIndex(s.charAt(i));
            BinaryStdOut.write(d, 2);
        }
        BinaryStdOut.close();
    } 

    public static void expand() {
        Alphabet DNA = new Alphabet("ACTG");
        int N = BinaryStdIn.readInt();
        // Read two bits; write char. 
        for (int i = 0; i < N; i++) {
            char c = BinaryStdIn.readChar(2);
            BinaryStdOut.write(DNA.toChar(c), 8);
        }
        BinaryStdOut.close();
    }


    public static void main(String[] args) {
        if      (args[0].equals("-")) compress();
        else if (args[0].equals("+")) expand();
        else throw new IllegalArgumentException("Illegal command line argument");
    }

}
