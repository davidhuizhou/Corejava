package com.dzhou.corejava.generics;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * Created by huizhou on 12/5/15.
 */
public class ChapterOneTest {

    @Test
    public void testSum() {
        List<Integer> ints = Arrays.asList(1, 2, 3);
        int s = sum(ints);
        assertEquals (6, s);
    }

    @Test
    public void testRemoveNegative() {
        List<Double> doubles = (new ArrayList<Double>());//Arrays.asList(1.1, -2.0, -3.1, 4.5, 6.5);
        doubles.add(1.1);
        doubles.add(-2.0);
        doubles.add(-3.1);
        doubles.add(4.5);
        doubles.add(6.5);

        removeNegative(doubles);
        for(double d : doubles){
            assertTrue(d >= 0);
        }
    }

    @Test
    public void testToList() {
        List<Integer> ints = Lists.toList(new Integer[] {1, 2, 3});
        List<String> words = Lists.toList((new String[] {"hello", "world"}));
        ints = Lists.toList(1, 2, 3);
        words = Lists.toList("hello", "world");
        List<Object> objs = Lists.<Object>toList(1, "two");
    }

    @Test
    public void testAddAll(){
        List<Integer> ints = new ArrayList<Integer>();
        Lists.addAll(ints, 1, 2);
        Lists.addAll(ints, new Integer[] {3, 4});
        assertThat(ints.toString(), is("[1, 2, 3, 4]"));

    }

    private int sum(List<Integer> ints) {
        int s = 0;
        for(int n : ints){
            s += n;
        }
        return s;
    }

    private void removeNegative(List<Double> v){
        for(Iterator<Double> it = v.iterator(); it.hasNext(); ) {
            if(it.next() < 0)
                it.remove();
        }
    }


}
