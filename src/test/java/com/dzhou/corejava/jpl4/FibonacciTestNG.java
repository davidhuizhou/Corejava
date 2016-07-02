package com.dzhou.corejava.jpl4;

/**
 * Created by davidzhou on 1/5/14.
 */

import com.dzhou.corejava.InputOutputWrapper;
import com.dzhou.corejava.jpl4.Fibonacci;
import com.dzhou.util.StringUtils;
import org.testng.annotations.*;
import org.testng.Assert;

import java.util.Scanner;


public class FibonacciTestNG {
    @Test
    public void testPrintFibonacci() {
        Fibonacci.fibonacci(100);
    }

    @Test
    public void testGetInput() {
        String input = "This is good\nThat works\n.\n";
        Scanner scanner = new Scanner(input);
        InputOutputWrapper wrapper = new InputOutputWrapper(scanner, System.out);

        StringUtils util = new StringUtils();
        String s = util.getInput(wrapper);
        Assert.assertEquals(s, "This is good\n" +
                "That works\n");

    }



}
