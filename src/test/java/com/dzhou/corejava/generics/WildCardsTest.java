package com.dzhou.corejava.generics;

/**
 * Created by huizhou on 11/14/15.
 */

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;


public class WildCardsTest {

    private <T> void copy(List<? super T> dst, List<? extends T> src) {
        for (int i = 0; i < src.size(); i++) {
            dst.set(i, src.get(i));
        }
    }

    private double sum(Collection<? extends Number> nums) {
        double s = 0.0;
        for (Number num : nums) {
            s += num.doubleValue();
        }
        return s;
    }

    private void count(Collection<? super Integer> ints, int n) {
        for (int i = 0; i < n; i++) ints.add(i);
    }

    private <T> void rev(List<T> list) {
        List<T> tmp = new ArrayList<T>(list);
        for (int i = 0; i < list.size(); i++) {
            list.set(i, tmp.get(list.size() - i - 1));
        }
    }

    private void reverse(List<?> list) {
        rev(list);
    }

    @Test
    public void testCopyList() {
        List<Object> objs = Arrays.<Object>asList(2, 3.14, "four");
        List<Integer> ints = Arrays.asList(5, 6);
        copy(objs, ints);
        assertEquals("[5, 6, four]", objs.toString());
    }

    @Test
    public void testSum() {
        List<Integer> ints = Arrays.asList(1, 2, 3);
        assertEquals(6.0, sum(ints), 0.0);

        List<Double> doubles = Arrays.asList(2.78, 3.14);
        assertEquals(5.92, sum(doubles), 0.0);

        List<Number> nums = Arrays.<Number>asList(1, 2, 2.78, 3.14);
        assertEquals(8.92, sum(nums), 0.0);

    }

    @Test
    public void testCount() {
        List<Integer> ints = new ArrayList<Integer>();
        count(ints, 5);
        assertEquals("[0, 1, 2, 3, 4]", ints.toString());

        List<Number> nums = new ArrayList<Number>();
        count(nums, 5);
        nums.add(5.0);
        assertEquals("[0, 1, 2, 3, 4, 5.0]", nums.toString());

        List<Object> objs = new ArrayList<Object>();
        count(objs, 5);
        objs.add("five");
        assertEquals("[0, 1, 2, 3, 4, five]", objs.toString());

    }

    @Test
    public void testContains() {
        Object obj = "one";
        List<Object> objs = Arrays.<Object>asList("one", 2, 3.14, 4);
        List<Integer> ints = Arrays.asList(2, 4);
        assert objs.contains(obj);
        assert objs.containsAll(ints);
        assert !ints.contains(obj);
        assert !ints.containsAll(objs);
    }

    @Test
    public void testReverse() {
        List<Integer> ints = Arrays.asList(1, 2, 3, 4, 5);
        reverse(ints);
        for (int num : ints) {
            System.out.printf("%4d", num);
        }
    }

    @Test
    public void testWildCardRestriction() {
        List<List<?>> lists = new ArrayList<List<?>>();
        lists.add(Arrays.asList(1, 2, 3));
        lists.add(Arrays.asList("four", "five"));
        assertThat(lists.toString(), is("[[1, 2, 3], [four, five]]"));
    }
}
