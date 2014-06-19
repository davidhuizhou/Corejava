package com.dzhou.corejava;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * Created by davidzhou on 5/7/14.
 */
public class InputOutputWrapper {

    private Scanner scanner = new Scanner(System.in);
    private PrintStream out = System.out;

    public InputOutputWrapper(Scanner scanner, PrintStream out){
        this.scanner = scanner;
        this.out = out;
    }

    public String nextLine() {
        return scanner.nextLine();
    }

    public void printLine(String s){
        out.println(s);
    }

    public void printChar(char c){
        out.print(c);
    }

}
