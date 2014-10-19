package com.dzhou.corejava.leetcode;

/**
 * Created by huizhou on 10/18/14.
 */
import java.util.*;

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

    /**
     * https://oj.leetcode.com/problems/single-number/
     * http://www.programcreek.com/2012/12/leetcode-solution-of-single-number-in-java/
     * http://yucoding.blogspot.com/2013/10/leetcode-question-single-number-i.html
     * https://oj.leetcode.com/submissions/detail/13409711/
     * https://oj.leetcode.com/submissions/detail/13415495/
     */
    public static int singleNumber(int[] A) {
        int x = 0;


        for (int a : A) {
            x = x ^ a;
        }


        return x;
    }

    public int singleNumber3(int[] A) {
        int one = 0, two = ~0;

        for (int i = 0; i < A.length; ++i) {
            int cur = A[i];
            int one_next = (one & (~cur)) | (cur & two);
            int two_next = (two & (~cur)) | (cur & one);
            one = one_next;
            two = two_next;
        }

        return one;

    }

    /**
     * https://oj.leetcode.com/problems/single-number-ii/
     * http://stackoverflow.com/questions/21297067/single-number-ii-from-leetcode
     * https://oj.leetcode.com/submissions/detail/13414864/
     */
    /*
    The idea is to reinterpret the numbers as vectors over GF(3). Each bit of the original number becomes a component of the vector.
    The important part is that for each vector v in a GF(3) vector space the summation v+v+v yields 0.
    Thus the sum over all vectors will leave the unique vector and cancel all others.
    Then the result is interpreted again as a number which is the desired single number.
    Each component of a GF(3) vector may have the values 0, 1, 2 with addition being performed mod 3. The "one" captures the low bits and the "two" captures the high bits of the result. So althoug the algorithm looks complicated all that it does is "digitwise addition modulo 3 without carry".
     */

    public int singleNumber2(int[] A) {
        int one = 0, two = 0, three = ~0;

        for(int i = 0; i < A.length; ++i) {
            int cur = A[i];
            int one_next = (one & (~cur)) | (cur & three);
            int two_next = (two & (~cur)) | (cur & one);
            int three_next = (three & (~cur)) | (cur & two);
            one = one_next;
            two = two_next;
            three = three_next;
        }

        return one;

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


        int[] numbers = {11, 11, 11, 12};

    }
}
