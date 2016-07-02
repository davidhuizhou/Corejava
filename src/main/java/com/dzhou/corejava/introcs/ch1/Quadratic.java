package com.dzhou.corejava.introcs.ch1;

/**
 * Given b and c, solves for the roots of x*x + b*x +c. Assums both roots are real values.
 */
public class Quadratic {
    public static void main(String[] args) {
        double b = -1;
        double c = -1;

        calculateRoots(b, c);
    }

    public static void calculateRoots(double b, double c) {
        double discriminant = b * b - 4.0 * c;
        double sqroot = Math.sqrt(discriminant);

        double root1 = (-b + sqroot) / 2.0;
        double root2 = (-b - sqroot) / 2.0;

        System.out.println(root1);
        System.out.println(root2);
    }
}
