package com.dzhou.corejava.leetcode.old;

/**
 * Created by huizhou on 10/18/14.
 */

import com.dzhou.corejava.leetcode.ListNode;
import com.dzhou.corejava.leetcode.Point;
import com.dzhou.corejava.leetcode.RandomListNode;
import com.dzhou.corejava.leetcode.TreeNode;

import java.util.*;

public class LeetCode2 {
    /**
     * https://oj.leetcode.com/problems/candy/
     * https://oj.leetcode.com/submissions/detail/13372726/
     */
    public static int candy(int[] ratings) {
        if (ratings == null || ratings.length == 0) {
            return 0;
        }

        int[] candies = new int[ratings.length];
        // every child should has at least one candy
        for (int i = 0; i < candies.length; i++) {
            candies[i] = 1;
        }
        // if child i has rating higher than i-1, which should 1 bigger than its left neighbour
        for (int i = 1; i < ratings.length; i++) {
            if (ratings[i] > ratings[i - 1]) {
                candies[i] = candies[i - 1] + 1;
            }
        }
        // if child i has rating higher than its right neighbour, but the candies array did not
        // represented this situation correctly, then correct it.

        for (int i = ratings.length - 2; i >= 0; i--) {
            if (ratings[i] > ratings[i + 1] && candies[i] <= candies[i + 1]) {
                candies[i] = candies[i + 1] + 1;
            }
        }

        int total = 0;
        // calculate the total candies needed
        for (int i = 0; i < candies.length; i++) {
            total += candies[i];
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

        for (int i = 0; i < A.length; ++i) {
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


    /**
     * https://oj.leetcode.com/problems/linked-list-cycle/
     * http://www.programcreek.com/2012/12/leetcode-linked-list-cycle/
     * https://oj.leetcode.com/submissions/detail/13801206/
     */
    public boolean hasCycle(ListNode head) {
        if (head == null)
            return false;
        ListNode p = head;
        ListNode q = head;

        while (p != null && q != null) {
            p = p.next;
            q = q.next;
            if (q != null)
                q = q.next;

            if (p != null && p == q) // has to check for null
                return true;
        }
        return false;


    }

    /**
     * https://oj.leetcode.com/problems/linked-list-cycle-ii/
     * https://oj.leetcode.com/submissions/detail/13802485/
     */
    public ListNode detectCycle(ListNode head) {
        if (head == null)
            return null;
        boolean hasCycle = false;
        ListNode p = head;
        ListNode q = head;
        while (p != null && q != null) {
            p = p.next;
            q = q.next;
            if (q != null)
                q = q.next;

            if (p != null && p == q) {
                hasCycle = true;
                break;
            }
        }

        if (hasCycle) {
            p = head;
            while (p != q) {
                p = p.next;
                q = q.next;
            }
            return p;
        }

        return null;
    }

    /**
     * https://oj.leetcode.com/problems/reorder-list/
     * http://www.programcreek.com/2013/12/in-place-reorder-a-singly-linked-list-in-java/
     * https://oj.leetcode.com/submissions/detail/13805311/
     */
    public static void reorderList(ListNode head) {
        if (head == null || head.next == null || head.next.next == null)
            return;

        int count = countList(head);

        if (count <= 2)
            return;

        ListNode p = head;
        for (int i = 0; i < (count + 1) / 2 - 1; i++) {
            p = p.next;
        }
        ListNode q = p.next;
        p.next = null;

        q = reverseList(q);
        mergeList(head, q);


    }


    private static int countList(ListNode head) {
        int count = 0;
        while (head != null) {
            count++;
            head = head.next;
        }
        return count;
    }

    private static ListNode reverseList(ListNode head) {
        if (head == null)
            return head;

        ListNode r = head;
        ListNode p = head.next;
        r.next = null;

        while (p != null) {
            ListNode q = p.next;
            p.next = r;
            r = p;
            p = q;
        }
        return r;
    }

    private static void mergeList(ListNode head1, ListNode head2) {
        ListNode p = head1;
        ListNode q = head2;
        while (p != null && q != null) {
            ListNode m = p.next;
            ListNode n = q.next;
            p.next = q;
            q.next = m;
            p = m;
            q = n;
        }

    }

    /**
     * https://oj.leetcode.com/problems/binary-tree-preorder-traversal/
     * https://oj.leetcode.com/submissions/detail/13806713/
     * https://oj.leetcode.com/submissions/detail/13807003/
     *
     * @return
     */
    public List<Integer> preorderTraversal1(TreeNode root) {
        List<Integer> result = new ArrayList<Integer>();
        if (root == null)
            return result;
        Stack<TreeNode> stack = new Stack<TreeNode>();
        stack.push(root);

        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            result.add(node.val);
            if (node.right != null)
                stack.push(node.right);
            if (node.left != null)
                stack.push(node.left);
        }
        return result;

    }

    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<Integer>();
        preorderTraversal(root, list);
        return list;
    }

    private void preorderTraversal(TreeNode root, List<Integer> list) {
        if (root == null)
            return;
        list.add(root.val);
        preorderTraversal(root.left, list);
        preorderTraversal(root.right, list);

    }

    /**
     * https://oj.leetcode.com/problems/binary-tree-postorder-traversal/
     * https://oj.leetcode.com/submissions/detail/13807410/
     * https://oj.leetcode.com/submissions/detail/13807508/
     */
    public List<Integer> postorderTraversal1(TreeNode root) {
        List<Integer> result = new ArrayList<Integer>();
        if (root == null)
            return result;

        Stack<TreeNode> stack = new Stack<TreeNode>();
        stack.push(root);

        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            if (node.left == null && node.right == null)
                result.add(node.val);
            else {
                TreeNode left = node.left;
                TreeNode right = node.right;
                TreeNode r = new TreeNode(node.val);
                stack.push(r);
                if (right != null)
                    stack.push(right);
                if (left != null)
                    stack.push(left);
            }
        }
        return result;

    }

    public List<Integer> postrderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<Integer>();
        postrderTraversal(root, list);
        return list;
    }

    private void postrderTraversal(TreeNode root, List<Integer> list) {
        if (root == null)
            return;

        postrderTraversal(root.left, list);
        postrderTraversal(root.right, list);
        list.add(root.val);

    }

    /**
     * https://oj.leetcode.com/problems/insertion-sort-list/
     * https://oj.leetcode.com/submissions/detail/14238603/
     */
    public ListNode insertionSortList(ListNode head) {
        if (head == null || head.next == null)
            return head;

        ListNode sentinel = new ListNode(0);
        sentinel.next = head;

        ListNode p = head;
        ListNode q = head.next;

        while (q != null) {
            ListNode h = sentinel;
            while (h != p && h.next.val < q.val) {
                h = h.next;
            }
            if (h != p) {
                p.next = q.next;
                q.next = h.next;
                h.next = q;
                q = p.next;
            } else {
                p = p.next;
                q = p.next;
            }
            h = sentinel;
        }
        return sentinel.next;

    }


    /**
     * https://oj.leetcode.com/problems/sort-list/
     * https://oj.leetcode.com/submissions/detail/14243070/
     */
    public static ListNode sortList(ListNode head) {
        ListNode h = head;
        int count = 0;
        while (h != null) {
            count++;
            h = h.next;
        }
        if (count <= 1)
            return head;
        int mid = (count + 1) / 2;

        ListNode list1 = head;
        ListNode p = head;
        ListNode list2 = p.next;
        for (int i = 0; i < mid - 1; i++) {
            p = p.next;
            list2 = p.next;
        }
        p.next = null;
        list1 = sortList(list1);
        list2 = sortList(list2);
        return merge(list1, list2);
    }

    private static ListNode merge(ListNode list1, ListNode list2) {
        if (list1 == null)
            return list2;
        if (list2 == null)
            return list1;
        ListNode sentinel = new ListNode(0);
        ListNode h = sentinel;
        while (list1 != null && list2 != null) {
            if (list1.val <= list2.val) {
                ListNode p = list1;
                list1 = list1.next;
                h.next = p;
                p.next = null;
                h = p;
            } else {
                ListNode p = list2;
                list2 = list2.next;
                h.next = p;
                p.next = null;
                h = p;
            }

        }
        if (list1 != null)
            h.next = list1;
        if (list2 != null) {
            h.next = list2;
        }
        return sentinel.next;
    }


    /**
     * https://oj.leetcode.com/problems/max-points-on-a-line/
     * http://yucoding.blogspot.com/2013/12/leetcode-question-max-points-on-line.html
     * https://oj.leetcode.com/submissions/detail/14266775/
     */
    public static int maxPoints(Point[] points) {
        if (points == null)
            return 0;
        if (points.length <= 2)
            return points.length;

        int N = points.length;
        boolean[][] c = new boolean[N][N];

        int max = 0;
//        String sss = "";
//        List<Integer> lll = new ArrayList<Integer>();


        for (int i = 0; i < N; i++) {

            Map<String, List<Integer>> map = new HashMap<String, List<Integer>>();
            int sameCount = 1;
            for (int j = i + 1; j < N; j++) {
                //If point i, j have the same x and y, should count it even c[i][j] == true
                if (points[i].x == points[j].x && points[i].y == points[j].y) {
                    sameCount++;
                    if (sameCount > max) {
                        max = sameCount;
                    }
                    for (List<Integer> list : map.values()) {
                        if (list.size() + sameCount > max)
                            max = list.size() + sameCount;
                    }

                } else if (!c[i][j]) {
                    c[i][j] = true;
                    //c[j][i] = true;


                    String slop = slop(points[i], points[j]);
                    List<Integer> list = map.get(slop);

                    if (list == null) {
                        list = new ArrayList<Integer>();
                        list.add(j);
                        map.put(slop, list);
                    } else {
                        for (int p : list) {
                            c[p][j] = true;
                            //c[j][p] = true;
                        }

                        list.add(j);

                    }
                    if (list.size() + sameCount > max) {
                        max = list.size() + sameCount;
//                        sss = slop;
//                        lll = list;
                    }

                }

            }
        }

//        System.out.println("max=" + max);
//        System.out.println("slop=" + sss);
//        for (int ll : lll) {
//            System.out.print(ll + " ");
//        }
        return max;


    }

    private static String slop(Point p1, Point p2) {
        if (p1.x == p2.x) {
            return "INFINITY";
        } else if (p1.y == p2.y) {
            return "0";
        }
        {
            double d = (p2.y - p1.y) * 1.0d / (p2.x - p1.x);
            String prefix = "";
            if (d < 0) {
                prefix = "-";
            }
//            String s = String.format("%.30f", d);
            String s = "" + d;
            return prefix + s;
        }
    }


    /**
     * https://oj.leetcode.com/problems/evaluate-reverse-polish-notation/
     * https://oj.leetcode.com/problems/evaluate-reverse-polish-notation/
     *
     */
    public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<Integer>();

        for (String s : tokens) {
            if (s.equals("+")) {
                stack.push(stack.pop() + stack.pop());
            } else if (s.equals("-")) {
                int a = stack.pop();
                int b = stack.pop();
                stack.push(b - a);
            } else if (s.equals("*")) {
                stack.push(stack.pop() * stack.pop());
            } else if (s.equals("/")) {
                int a = stack.pop();
                int b = stack.pop();
                stack.push(b / a);
            } else {
                stack.push(Integer.valueOf(s));
            }
        }
        return stack.pop();

    }

    /**
     *  https://oj.leetcode.com/problems/reverse-words-in-a-string/
     *  http://www.programcreek.com/2014/02/leetcode-reverse-words-in-a-string-java/
     *  https://oj.leetcode.com/submissions/detail/14311788/
     */
    public static String reverseWords(String s) {
        if (s == null)
            return null;

        s = s.trim();
        if (s.length() == 0)
            return s;

        Stack<String> stack = new Stack<String>();
        int start = 0;
        for (int i = 0; i <= s.length(); i++) {
            while (i < s.length() && !Character.isSpaceChar(s.charAt(i)))
                i++;
            if (i > start) {
                stack.push(s.substring(start, i));
            }
            while (i < s.length() && Character.isSpaceChar(s.codePointAt(i)))
                i++;
            start = i;


        }


        StringBuilder sb = new StringBuilder("");
        if (!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        while (!stack.isEmpty()) {
            sb.append(" ").append(stack.pop());
        }
        return sb.toString();


    }





    public static void main(String[] args) {
        int[] ratings = new int[12000];
        for (int i = 0; i < 12000; i++)
            ratings[i] = 12000 - i;

//        ratings = new int[]{5,1,1,1,10,2,1,1,1,3};
        ratings = new int[]{1, 2, 2};

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
        dict.add("cats");
        dict.add("and");
        dict.add("sand");
        dict.add("dog");
//        dict.clear();
        s = "catsanddog";

//        s = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaab";
//        Collections.addAll(dict, new String[]{"a","aa","aaa","aaaa","aaaaa","aaaaaa","aaaaaaa","aaaaaaaa","aaaaaaaaa","aaaaaaaaaa"});


        l1 = System.currentTimeMillis();
        List<String> list = wordBreak(s, dict);
        l2 = System.currentTimeMillis();
        System.out.println("l2 - l1=" + (l2 - l1));

        for (String ss : list)
            System.out.println(ss);


        int[] nodes = {1, 2, 3, 4, 5};
        ListNode head = new ListNode(nodes[0]);
        ListNode p = head;

        for (int i = 1; i < nodes.length; i++) {
            p.next = new ListNode(nodes[i]);
            p = p.next;
        }

        reorderList(head);
        p = head;

        while (p != null) {
            System.out.print(p.val + " ");
            p = p.next;
        }

        System.out.println();
        LRUCache lru = new LRUCache(2);
        lru.set(2, 1);
        lru.set(1, 1);
        System.out.println(lru.get(2));
        lru.set(4, 1);
        System.out.println(lru.get(1));
        System.out.println(lru.get(2));

        System.out.println("Test sortList");

        nodes = new int[]{3, 2, 4};
        head = new ListNode(nodes[0]);
        p = head;

        for (int i = 1; i < nodes.length; i++) {
            p.next = new ListNode(nodes[i]);
            p = p.next;
        }

        head = sortList(head);
        p = head;

        while (p != null) {
            System.out.print(p.val + " ");
            p = p.next;
        }

        System.out.println("\nTest maxPoints");

//        Point[] points = new Point [3];
//        points[0] = new Point(1, 1);
//        points[1] = new Point(1, 1);
//        points[2] = new Point(1, 1);


//        points[3] = new Point(0, 1);
//        points[4] = new Point(2, 2);
//        points[5] = new Point(-1, 2);
//        points[6] = new Point(-2, 3);
//        points[7] = new Point(99, 100);

//
//        Point[] points = new Point[]{new Point(84,250),new Point(0,0),new Point(1,0),new Point(0,-70),new Point(0,-70),
//                                                new Point(1,-1), new Point(21,10),new Point(42,90),new Point(-42,-230)};

        Point[] points = new Point[]{new Point(29, 87), new Point(145, 227), new Point(400, 84), new Point(800, 179), new Point(60, 950), new Point(560, 122), new Point(-6, 5), new Point(-87, -53), new Point(-64, -118), new Point(-204, -388), new Point(720, 160), new Point(-232, -228), new Point(-72, -135), new Point(-102, -163), new Point(-68, -88), new Point(-116, -95), new Point(-34, -13), new Point(170, 437), new Point(40, 103), new Point(0, -38), new Point(-10, -7), new Point(-36, -114), new Point(238, 587), new Point(-340, -140), new Point(-7, 2), new Point(36, 586), new Point(60, 950), new Point(-42, -597), new Point(-4, -6), new Point(0, 18), new Point(36, 586), new Point(18, 0), new Point(-720, -182), new Point(240, 46), new Point(5, -6), new Point(261, 367), new Point(-203, -193), new Point(240, 46), new Point(400, 84), new Point(72, 114), new Point(0, 62), new Point(-42, -597), new Point(-170, -76), new Point(-174, -158), new Point(68, 212), new Point(-480, -125), new Point(5, -6), new Point(0, -38), new Point(174, 262), new Point(34, 137), new Point(-232, -187), new Point(-232, -228), new Point(232, 332), new Point(-64, -118), new Point(-240, -68), new Point(272, 662), new Point(-40, -67), new Point(203, 158), new Point(-203, -164), new Point(272, 662), new Point(56, 137), new Point(4, -1), new Point(-18, -233), new Point(240, 46), new Point(-3, 2), new Point(640, 141), new Point(-480, -125), new Point(-29, 17), new Point(-64, -118), new Point(800, 179), new Point(-56, -101), new Point(36, 586), new Point(-64, -118), new Point(-87, -53), new Point(-29, 17), new Point(320, 65), new Point(7, 5), new Point(40, 103), new Point(136, 362), new Point(-320, -87), new Point(-5, 5), new Point(-340, -688), new Point(-232, -228), new Point(9, 1), new Point(-27, -95), new Point(7, -5), new Point(58, 122), new Point(48, 120), new Point(8, 35), new Point(-272, -538), new Point(34, 137), new Point(-800, -201), new Point(-68, -88), new Point(29, 87), new Point(160, 27), new Point(72, 171), new Point(261, 367), new Point(-56, -101), new Point(-9, -2), new Point(0, 52), new Point(-6, -7), new Point(170, 437), new Point(-261, -210), new Point(-48, -84), new Point(-63, -171), new Point(-24, -33), new Point(-68, -88), new Point(-204, -388), new Point(40, 103), new Point(34, 137), new Point(-204, -388), new Point(-400, -106)};

//        points = new Point[] {new Point(2,3),new Point(3,3),new Point(-5,3)};
        points = new Point[]{new Point(40, -23), new Point(9, 138), new Point(429, 115), new Point(50, -17), new Point(-3, 80), new Point(-10, 33), new Point(5, -21), new Point(-3, 80), new Point(-6, -65),
                new Point(-18, 26), new Point(-6, -65), new Point(5, 72), new Point(0, 77), new Point(-9, 86), new Point(10, -2), new Point(-8, 85), new Point(21, 130), new Point(18, -6), new Point(-18, 26), new Point(-1, -15), new Point(10, -2), new Point(8, 69), new Point(-4, 63), new Point(0, 3), new Point(-4, 40), new Point(-7, 84), new Point(-8, 7), new Point(30, 154), new Point(16, -5), new Point(6, 90), new Point(18, -6), new Point(5, 77), new Point(-4, 77), new Point(7, -13), new Point(-1, -45), new Point(16, -5), new Point(-9, 86), new Point(-16, 11), new Point(-7, 84), new Point(1, 76), new Point(3, 77), new Point(10, 67), new Point(1, -37), new Point(-10, -81), new Point(4, -11), new Point(-20, 13), new Point(-10, 77), new Point(6, -17), new Point(-27, 2), new Point(-10, -81), new Point(10, -1), new Point(-9, 1), new Point(-8, 43), new Point(2, 2), new Point(2, -21), new Point(3, 82), new Point(8, -1), new Point(10, -1), new Point(-9, 1), new Point(-12, 42), new Point(16, -5), new Point(-5, -61), new Point(20, -7), new Point(9, -35), new Point(10, 6), new Point(12, 106), new Point(5, -21), new Point(-5, 82), new Point(6, 71), new Point(-15, 34), new Point(-10, 87), new Point(-14, -12), new Point(12, 106), new Point(-5, 82), new Point(-46, -45), new Point(-4, 63), new Point(16, -5), new Point(4, 1), new Point(-3, -53), new Point(0, -17), new Point(9, 98), new Point(-18, 26), new Point(-9, 86), new Point(2, 77), new Point(-2, -49), new Point(1, 76), new Point(-3, -38), new Point(-8, 7), new Point(-17, -37), new Point(5, 72), new Point(10, -37), new Point(-4, -57), new Point(-3, -53), new Point(3, 74), new Point(-3, -11), new Point(-8, 7), new Point(1, 88), new Point(-12, 42), new Point(1, -37), new Point(2, 77), new Point(-6, 77), new Point(5, 72), new Point(-4, -57), new Point(-18, -33), new Point(-12, 42), new Point(-9, 86), new Point(2, 77), new Point(-8, 77), new Point(-3, 77), new Point(9, -42), new Point(16, 41), new Point(-29, -37), new Point(0, -41), new Point(-21, 18), new Point(-27, -34), new Point(0, 77), new Point(3, 74), new Point(-7, -69), new Point(-21, 18), new Point(27, 146), new Point(-20, 13), new Point(21, 130), new Point(-6, -65), new Point(14, -4), new Point(0, 3), new Point(9, -5), new Point(6, -29), new Point(-2, 73), new Point(-1, -15), new Point(1, 76), new Point(-4, 77), new Point(6, -29)};

        l1 = System.currentTimeMillis();
        System.out.println(maxPoints(points));
        l2 = System.currentTimeMillis();
        System.out.println("l2 - l1=" + (l2 - l1));


        for (int i = 0; i < points.length; i++) {
            if (i != 11 && points[i].x == points[11].x && points[i].y == points[11].y)
                System.out.println("points[" + i + "] is equals to points[11]");
            if (i != 11 && "--1.0".equals(slop(points[11], points[i])))
                System.out.println("points[" + i + "]");

        }
        System.out.println(points[11].x + "," + points[11].y);
        System.out.println(points[101].x + ',' + points[101].y);

        System.out.println(reverseWords("   a   b "));




    }
}
