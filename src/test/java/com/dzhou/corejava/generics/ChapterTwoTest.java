package com.dzhou.corejava.generics;

/**
 * Created by huizhou on 12/10/15.
 */

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class ChapterTwoTest {

    @Test
    public void testSubType() {
        List<Number> nums = new ArrayList<Number>();
        nums.add(2);
        nums.add(3.14);
        assertThat(nums.toString(), is("[2, 3.14]"));

        List<Integer> ints = new ArrayList<Integer>();
        ints.add(1);
        ints.add(2);
        //nums = ints;     // compile-time error

    }

    //The Get and Put Principle: use an extends wildcard when you only get values out of a structure,
    // use a super wildcard when you only put values into a structure, and don’t use a wildcard when you both get and put.
    // exception - can put null into extends e.g. List<? extends Number>

    @Test
    public void testWidCardsWithExtends() {
        List<Number> nums = new ArrayList<Number>();
        List<Integer> ints = Arrays.asList(1, 2);
        List<Double> dbls = Arrays.asList(2.78, 3.14);
        nums.addAll(ints);
        nums.addAll(dbls);
        assertThat(nums.toString(), is("[1, 2, 2.78, 3.14]"));

        List<? extends Number> nums2 = ints;
        // nums2.add(3.14);    // compile-time error, can't put elements into the extends
        // nums2.addAll(ints);

    }

    @Test
    public void testWildCardsWithSuper() {
        List<Object> objs = Arrays.<Object>asList(2, 3.14, "four");
        List<Integer> ints = Arrays.asList(5, 6);
        ChapterTwoTest.copy(objs, ints);                            //copy(List<Integer> dst, List<Integer> src)
        assertThat(objs.toString(), is("[5, 6, four]"));

        ChapterTwoTest.<Object>copy(objs, ints);
        assertThat(objs.toString(), is("[5, 6, four]"));            // copy(List<Object> dst, List<? extends Object> src)

        ChapterTwoTest.<Number>copy(objs, ints);
        assertThat(objs.toString(), is("[5, 6, four]"));            // copy(List<? super Number> dst, List<? extends Number> src)

        ChapterTwoTest.<Integer>copy(objs, ints);
        assertThat(objs.toString(), is("[5, 6, four]"));            //  copy(List<? super Integer> dst, List<Integer> src)


    }

    @Test
    public void testSum() {
        List<Integer> ints = Arrays.asList(1, 2, 3);
        assertThat(sum(ints), is(6.0));

        List<Double> doubles = Arrays.asList(2.78, 3.14);
        assertThat(sum(doubles), is(5.92));

        List<Number> nums = Arrays.<Number>asList(1, 2, 2.78, 3.14);
        assertThat(sum(nums), is(8.92));

    }

    @Test
    public void testCount() {
        List<Integer> ints = new ArrayList<Integer>();
        count(ints, 5);
        assertThat(ints.toString(), is("[0, 1, 2, 3, 4]"));

        List<Number> nums = new ArrayList<Number>();
        count(nums, 5);
        nums.add(5.0);
        assertThat(nums.toString(), is("[0, 1, 2, 3, 4, 5.0]"));

        List<Object> objs = new ArrayList<Object>();
        count(objs, 5);
        objs.add("five");
        assertThat(objs.toString(), is("[0, 1, 2, 3, 4, five]"));
    }

    @Test
    public void testSumCount() {
        List<Number> nums = new ArrayList<Number>();
        double sum = sumCount(nums, 5);
        assertThat(sum, is(10d));

    }

    //The Get and Put Principle: use an extends wildcard when you only get values out of a structure,
    // use a super wildcard when you only put values into a structure, and don’t use a wildcard when you both get and put.
    // exception - can put null into extends e.g. List<? extends Number>
    // exception - can get Object from supertype

    //? extends T as containing every type in an interval bounded by the type of null below and by T above
    // (where the type of null is a subtype of every reference type). Similarly,
    // you may think of ? super T as containing every type in an interval bounded by T below and by Object above.
    @Test
    public void testGetPutPrinciple() {
        List<Integer> ints = new ArrayList<Integer>();
        ints.add(1);
        ints.add(2);
        List<? extends Number> nums = ints;
        double dbl = sum(nums);  // ok, get
        //nums.add(3.14);   // compile-time error
        nums.add(null);     // ok, exception - add null to extends

        List<Object> objs = new ArrayList<Object>();
        objs.add(1);
        objs.add(2);
        List<? super Integer> ints2 = objs;
        ints.add(3);  // ok
        //double dbls = sum(ints2);    // compile-time error

        List<Object> objs2 = Arrays.<Object>asList(1, "two");
        List<? super Integer> ints3 = objs2;
        String str = "";
        for (Object obj : ints3)
            str += obj.toString();  // ok, get object
        assertThat(str, is("1two"));

    }

    public static <T> void copy(List<? super T> dst, List<? extends T> src) {
        for (int i = 0; i < src.size(); i++) {
            dst.set(i, src.get(i));
        }
    }

    public static double sum(Collection<? extends Number> nums) {
        double s = 0.0;
        for (Number num : nums) {
            s += num.doubleValue();
        }
        return s;
    }

    public static void count(Collection<? super Integer> ints, int n) {
        for (int i = 0; i < n; i++)
            ints.add(i);
    }

    // Whenever you both put values into and get values out of the same structure, you should not use a wildcard.
    public static double sumCount(Collection<Number> nums, int n) {
        count(nums, n);
        return sum(nums);
    }

}
