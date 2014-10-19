package com.dzhou.corejava.leetcode;

/**
 * Created by huizhou on 10/18/14.
 */
public class LeetCode2 {
    /**
     * https://oj.leetcode.com/problems/candy/
     * https://oj.leetcode.com/submissions/detail/13372726/
     *
     */
    public static int candy(int[] ratings) {
        if (ratings==null||ratings.length==0){
            return 0;
        }

        int[] candies=new int[ratings.length];
        // every child should has at least one candy
        for (int i=0; i<candies.length; i++){
            candies[i]=1;
        }
        // if child i has rating higher than i-1, which should 1 bigger than its left neighbour
        for (int i=1; i<ratings.length; i++){
            if (ratings[i]>ratings[i-1]){
                candies[i]=candies[i-1]+1;
            }
        }
        // if child i has rating higher than its right neighbour, but the candies array did not
        // represented this situation correctly, then correct it.

        for (int i=ratings.length-2; i>=0; i--){
            if (ratings[i]>ratings[i+1] && candies[i]<=candies[i+1]){
                candies[i]=candies[i+1]+1;
            }
        }

        int total=0;
        // calculate the total candies needed
        for (int i=0; i<candies.length; i++){
            total+=candies[i];
        }

        return total;
    }

    public static void main(String[] args){
      int[] ratings = new int[12000];
        for(int i = 0; i < 12000; i++)
            ratings[i] = 12000 - i;

//        ratings = new int[]{5,1,1,1,10,2,1,1,1,3};
        ratings = new int[] {1, 2, 2};

        long l1 = System.currentTimeMillis();
        System.out.println(candy(ratings));
        long l2 = System.currentTimeMillis();
        System.out.println("l2 - l1 = " + (l2 - l1));
    }
}
