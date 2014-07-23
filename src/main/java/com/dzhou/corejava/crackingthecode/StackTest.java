package com.dzhou.corejava.crackingthecode;

/**
 * Created by huizhou on 7/22/14.
 */
public class StackTest {

    private static void testMin(){
        System.out.println("StackTest - testMin");
        Stack s = new Stack();
        s.push(2);
        System.out.println(s.toString());

        s.push(1);
        System.out.println(s.toString());


        s.push(3);
        System.out.println(s.toString());


        s.push(7);
        System.out.println(s.toString());

        s.push(8);
        System.out.println(s.toString());

        s.push(5);
        System.out.println(s.toString());

        s.push(4);
        System.out.println(s.toString());

        System.out.println(s.pop() + "|" + s.toString());
        System.out.println(s.pop() + "|" + s.toString());
        System.out.println(s.pop() + "|" + s.toString());
        System.out.println(s.pop() + "|" + s.toString());
        System.out.println(s.pop() + "|" + s.toString());
        System.out.println(s.pop() + "|" + s.toString());
        System.out.println(s.pop() + "|" + s.toString());

    }

    public static void test(){
        testMin();
    }
}
