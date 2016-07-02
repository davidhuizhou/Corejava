package com.dzhou.corejava.introcs.ch1;

/**
 * Created by huizhou on 3/28/16.
 */
public class Trig {

    public static void main(String[] args) {
        double degrees = 37.0;
        double radians = Math.toRadians(degrees);

        System.out.println("sin(" + degrees + ") = " + Math.sin(radians));
        System.out.println("cos(" + degrees + ") = " + Math.cos(radians));
        System.out.println("tan(" + degrees + ") = " + Math.tan(radians));
        System.out.println("sin/cos(" + degrees + ") = " + Math.sin(radians) / Math.cos(radians));
        System.out.println("sin*sin + cos*cos(" + degrees + ") = " + (Math.sin(radians) * Math.sin
                (radians) + Math.cos(radians) * Math.cos(radians)));
    }
}
