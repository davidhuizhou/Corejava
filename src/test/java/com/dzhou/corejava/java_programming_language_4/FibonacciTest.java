package com.dzhou.corejava.java_programming_language_4;

/**
 * Created by davidzhou on 1/5/14.
 */

import org.junit.Test;

import java.text.DecimalFormat;

public class FibonacciTest {

    @Test
    public void testPrintFibonacci() {
        Fibonacci.fibonacci(50);

        int duration = 30;
        System.out.println(getDurationString(duration));

        duration = 86;
        System.out.println(getDurationString(duration));

        duration = 1355;
        System.out.println(getDurationString(duration));

        duration = 3705;
        System.out.println(getDurationString(duration));


    }

    private String getDurationString(int duration) {
        int seconds = 0;
        int minutes = 0;
        int hours = 0;

        hours = duration / 3600;
        minutes = (duration % 3600) / 60;
        seconds = duration % 60;

        DecimalFormat formatter = new DecimalFormat("00");
        String aFormatted = formatter.format(hours) + ":" + formatter.format(minutes) + ":" + formatter.format(seconds);
        return aFormatted;
    }

}
