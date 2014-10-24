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

    /**
     * https://oj.leetcode.com/problems/copy-list-with-random-pointer/
     * http://yucoding.blogspot.com/2013/12/leetcode-question-copy-list-with-random.html
     * https://oj.leetcode.com/submissions/detail/13753091/
     */
    public RandomListNode copyRandomList(RandomListNode head) {

        Map<Integer, RandomListNode> map = new HashMap<Integer, RandomListNode>();
        return copyRandomList(head, map);

    }

    public RandomListNode copyRandomList(RandomListNode node, Map<Integer, RandomListNode> map) {
        if (node == null)
            return null;

        RandomListNode newNode = new RandomListNode(node.label);
        map.put(newNode.label, newNode);

        if (node.next == null) {
            newNode.next = null;
        } else if (map.containsKey(node.next.label)) {
            newNode.next = map.get(node.next.label);
        } else {
            newNode.next = copyRandomList(node.next, map);
        }

        if (node.random == null) {
            newNode.random = null;
        } else if (map.containsKey(node.random.label)) {
            newNode.random = map.get(node.random.label);
        } else {
            newNode.random = copyRandomList(node.random, map);
        }

        return newNode;

    }

    /**
     * https://oj.leetcode.com/problems/word-break/
     * http://www.programcreek.com/2012/12/leetcode-solution-word-break/
     * https://oj.leetcode.com/submissions/detail/13755384/
     */
    public static boolean wordBreak1(String s, Set<String> dict) {
        if (s == null || dict == null)
            return false;
        if (dict.contains(s))
            return true;

        int n = s.length();
        boolean[] c = new boolean[n];

        c[0] = dict.contains(s.substring(0, 1));

        for (int i = 1; i < n; i++) {
            if (dict.contains(s.substring(0, i + 1)))
                c[i] = true;
            else {
                for (int k = 0; k < i; k++) {
                    if (c[k] && dict.contains(s.substring(k + 1, i + 1))) {
                        c[i] = true;
                        break;
                    }

                }
            }
        }

        return c[n - 1];

    }

    /**
     * https://oj.leetcode.com/problems/word-break-ii/
     * http://yucoding.blogspot.com/2014/01/leetcode-question-word-break-ii.html
     * https://oj.leetcode.com/submissions/detail/13760046/
     * https://oj.leetcode.com/submissions/detail/13760300/
     */
    public static List<String> wordBreak(String s, Set<String> dict) {
        List<String> r = new ArrayList<String>();
        if (s == null || dict == null || s.length() == 0 || !shouldProceed(s, dict))
            return r;

        if (dict.contains(s))
            r.add(s);

        List<String> words = findFirstWords(s, dict);
        for (String w : words) {
            List<String> l = wordBreak(s.substring(w.length()), dict);
            for (String str : l) {
                r.add(w + " " + str);
            }
        }
        return r;
    }

    private static List<String> findFirstWords(String s, Set<String> dict) {
        List<String> words = new ArrayList<String>();
        for (int i = 0; i < s.length(); i++) {
            if (dict.contains(s.substring(0, i)))
                words.add(s.substring(0, i));
        }
        return words;
    }

    public static List<String> wordBreak2(String s, Set<String> dict) {
        if (s == null || dict == null || !shouldProceed(s, dict))
            return new ArrayList<String>();

        int n = s.length();
        HashMap<Integer, List<String>> map = new HashMap<Integer, List<String>>();
        map.put(0, new ArrayList<String>());

        for (int i = 1; i <= n; i++) {
            List<String> newList = new ArrayList<String>();
            if (dict.contains(s.substring(0, i)))
                newList.add(s.substring(0, i));

            for (int k = 1; k < i; k++) {
                String t = s.substring(k, i);
                if (dict.contains(t)) {
                    List<String> list = map.get(k);
                    for (String ll : list) {
                        newList.add(ll + " " + t);
                    }

                }

            }
            map.put(i, newList);
        }

        return map.get(n);

    }

    private static boolean shouldProceed(String s, Set<String> dict) {
        boolean r = false;
        for (int i = s.length() - 1; i >= 0; i--) {
            if (dict.contains(s.substring(i)))
                return true;
        }
        return false;
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

        String s = "leetcode";
        Set<String> dict = new HashSet<String>();
        dict.add("leet");
        dict.add("code");

        System.out.println(wordBreak1(s, dict));

        dict.add("cat");
        dict.add ("cats");
        dict.add ("and");
        dict.add ("sand");
        dict.add("dog");
//        dict.clear();
        s = "catsanddog";

//        s = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaab";
//        Collections.addAll(dict, new String[]{"a","aa","aaa","aaaa","aaaaa","aaaaaa","aaaaaaa","aaaaaaaa","aaaaaaaaa","aaaaaaaaaa"});



        l1 = System.currentTimeMillis();
        List<String> list = wordBreak(s, dict);
        l2 = System.currentTimeMillis();
        System.out.println("l2 - l1=" + (l2 - l1));

        for(String ss : list)
            System.out.println(ss);


    }
}
