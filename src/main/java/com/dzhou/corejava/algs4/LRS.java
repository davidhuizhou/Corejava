package com.dzhou.corejava.algs4;

/*************************************************************************
 *  Compilation:  javac com.dzhou.corejava.algs4.LRS.java
 *  Execution:    java com.dzhou.corejava.algs4.LRS < file.txt
 *  Dependencies: StdIn.java com.dzhou.corejava.algs4.SuffixArray.java
 *  Data files:   http://algs4.cs.princeton.edu/63suffix/tinyTale.txt
 *                http://algs4.cs.princeton.edu/63suffix/mobydick.txt
 *  
 *  Reads a text string from stdin, replaces all consecutive blocks of
 *  whitespace with a single space, and then computes the longest
 *  repeated substring in that text using a suffix array.
 * 
 *  % java com.dzhou.corejava.algs4.LRS < tinyTale.txt
 *  'st of times it was the '
 *
 *  % java com.dzhou.corejava.algs4.LRS < mobydick.txt
 *  ',- Such a funny, sporty, gamy, jesty, joky, hoky-poky lad, is the Ocean, oh! Th'
 * 
 *  % java com.dzhou.corejava.algs4.LRS
 *  aaaaaaaaa
 *  'aaaaaaaa'
 *
 *  % java com.dzhou.corejava.algs4.LRS
 *  abcdefg
 *  ''
 *
 *************************************************************************/
import com.dzhou.lib.algorithms.*;

public class LRS {

    public static void main(String[] args) {
        String text = StdIn.readAll().replaceAll("\\s+", " ");
        SuffixArray sa = new SuffixArray(text);

        int N = sa.length();

        String lrs = "";
        for (int i = 1; i < N; i++) {
            int length = sa.lcp(i);
            if (length > lrs.length()) {
                // lrs = sa.select(i).substring(0, length);
                lrs = text.substring(sa.index(i), sa.index(i) + length);
            }
        }
        
        StdOut.println("'" + lrs + "'");
    }
}
