package com.dzhou.corejava.generics;

import java.util.Arrays;
import java.util.List;

/**
 * Created by huizhou on 12/5/15.
 */
public class ChapterOne {
    public static void main(String[] args) {

    }

    private static void testOne() {
        List<Integer> ints = Arrays.asList(1, 2, 3);
        int s = 0;
        for (int n : ints){
            s += n;
        }

    }
}
