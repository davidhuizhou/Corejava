package com.dzhou.corejava.algs4;

/*************************************************************************
 *  Compilation:  javac com.dzhou.corejava.algs4.GREP.java
 *  Execution:    java com.dzhou.corejava.algs4.GREP regexp < input.txt
 *  Dependencies: com.dzhou.corejava.algs4.NFA.java
 *  Data files:   http://algs4.cs.princeton.edu/54regexp/tinyL.txt
 *
 *  This program takes an RE as a command-line argument and prints
 *  the lines from standard input having some substring that
 *  is in the language described by the RE. 
 *
 *  % more tinyL.txt
 *  AC
 *  AD
 *  AAA
 *  ABD
 *  ADD
 *  BCD
 *  ABCCBD
 *  BABAAA
 *  BABBAAA
 *
 *  %  java com.dzhou.corejava.algs4.GREP "(A*B|AC)D" < tinyL.txt
 *  ABD
 *  ABCCBD
 *
 *************************************************************************/
import com.dzhou.lib.algorithms.*;

public class GREP {
    public static void main(String[] args) { 
        String regexp = "(.*" + args[0] + ".*)";
        NFA nfa = new NFA(regexp);
        while (StdIn.hasNextLine()) { 
            String txt = StdIn.readLine();
            if (nfa.recognizes(txt)) {
                StdOut.println(txt);
            }
        }
    } 
} 
