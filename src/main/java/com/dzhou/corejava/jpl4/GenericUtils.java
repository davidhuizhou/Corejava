package com.dzhou.corejava.jpl4;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by davidzhou on 10/31/14.
 */
public class GenericUtils {
    public static double sum(List<? extends Number> list) {
        double sum = 0.0;
        for (Number n : list) {
            sum += n.doubleValue();
        }
        return sum;
    }

    public static String toString(List<? super Integer> list) {
        StringBuilder sb = new StringBuilder();
        for (Object n : list) {
            sb.append(n.toString());
        }
        return sb.toString();
    }

    <T> T passThrough(T obj){
        return obj;
    }


    public static <T> T passThrough1(T obj){
        return obj;
    }

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<Integer>();
        list.add(1);
        list.add(2);
        double sum = sum(list);
        System.out.println(sum);

        List<Number> list1 = new ArrayList<Number>();
        list1.add(3);
        list1.add(5);
        System.out.println(toString(list1));

        SingleLinkQueue<? super Integer> queue1 = new SingleLinkQueue<Integer>();
        queue1.add(1);

        SingleLinkQueue<String> queue2 = new SingleLinkQueue<String>();
        queue2.add("A");
        queue2.add("B");
        queue2.add("C");
        Object[] arr = new Object[2];
        queue2.toArray(arr);
        for(Object o : arr)
            System.out.println(o);

        String[] arr1 = new String[3];
        queue2.toArray_v1(arr1);
        for(String s : arr1)
            System.out.println(s);

        GenericUtils util = new GenericUtils();
        String s1 = "Hello";
        String s2 = util.<String>passThrough(s1);
        System.out.println(s2);
        System.out.println(util.passThrough(s1));

        Object o1 = util.passThrough(s1);          // T => String
        Object o2 = util.passThrough((Object) s1); // T => Object


        s2 = (String)util.passThrough((Object) s1); // T => Object

        s2 = GenericUtils.passThrough1(s1);


    }

}


