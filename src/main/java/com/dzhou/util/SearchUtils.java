package com.dzhou.util;

/**
 * Created by huizhou on 7/3/14.
 */
public class SearchUtils {

    public static int binarySearch(int key, int[] data){
        int lo = 0;
        int hi = data.length - 1;

        while(lo <= hi) {
            int middle = (lo + hi) / 2;
            if (key == data[middle])
                return middle;
            else if (key < data[middle])
                hi = middle - 1;
            else
                lo = middle + 1;
        }
        return -1;
    }

    public static void main(String[] args){
        int[] data = {1, 2, 3, 4, 6, 8, 9, 10};

        for(int key = -1; key <= 15; key++) {

            int i = binarySearch(key, data);
            System.out.println(key + " is at " + i);
        }




    }


}
