package com.dzhou.corejava.generics;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by huizhou on 12/5/15.
 */
public class Lists {
    public static <T> List<T> toList(T... arr) {
        List<T> list = new ArrayList<T>();
        for(T element : arr){
            list.add(element);
        }
        return list;
    }

    public static <T> void addAll(List<T> list, T... arr){
        for(T element : arr){
            list.add(element);
        }
    }

}
