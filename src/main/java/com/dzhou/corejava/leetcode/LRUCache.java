package com.dzhou.corejava.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Created by davidzhou on 12/24/14.
 */

public class LRUCache {
    class Node {
        Node pre, next;
        int key;
        int val;

        public Node(int key, int value) {
            this.key = key;
            this.val = value;
        }
    }

    private int capacity;
    private int num;
    private HashMap<Integer, Node> map;
    private Node first, last;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        num = 0;
        map = new HashMap<Integer, Node>();
        first = null;
        last = null;
    }

    public int get(int key) {
        Node node = map.get(key);
        if (node == null)
            return -1;
        else if (node != last) {
            if (node == first)
                first = first.next;
            else
                node.pre.next = node.next;
            node.next.pre = node.pre;
            last.next = node;
            node.pre = last;
            node.next = null;
            last = node;
        }
        return node.val;
    }

    public void set(int key, int value) {
        Node node = map.get(key);
        if (node != null) {
            node.val = value;
            if (node != last) {
                if (node == first)
                    first = first.next;
                else
                    node.pre.next = node.next;
                node.next.pre = node.pre;
                last.next = node;
                node.pre = last;
                node.next = null;
                last = node;
            }
        } else {
            Node newNode = new Node(key, value);

            if (num >= capacity) {
                map.remove(first.key);
                first = first.next;
                if (first != null)
                    first.pre = null;
                else
                    last = null;
                num--;
            }
            if (first == null || last == null) {
                first = newNode;
            } else {
                last.next = newNode;
            }
            newNode.pre = last;
            last = newNode;
            map.put(key, newNode);
            num++;
        }

    }

    /**
     * Permutations II -- LeetCode
     * https://oj.leetcode.com/problems/permutations-ii/
     * Given a collection of numbers that might contain duplicates, return all possible unique permutations.
     * For example,
     * [1,1,2] have the following unique permutations:
     * [1,1,2], [1,2,1], and [2,1,1].
     */
    public static List<List<Integer>> permuteUnique(int[] num) {
        if (num == null)
            return null;
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if (num.length == 0)
            return result;
        Arrays.sort(num);       // Sort the array in non-descending order
        recursivePermute(num, new boolean[num.length], new ArrayList<Integer>(), result);
        return result;
    }

    // If "current" is already a permutation of "num", add it to "result";
    // otherwise, append each unused number to "current", and recursively try next unused number
    private static void recursivePermute(int[] num, boolean[] used, List<Integer> current,
            List<List<Integer>> result) {
        if (current.size() == num.length) {     // "current" is already a permutation of "num"
            result.add(new ArrayList<Integer>(current));
            return;
        }
        // Append each unused number to "current", and recursively try next unused number
        for (int i = 0; i < num.length; i++) {
            if (i > 0 && !used[i - 1] && num[i] == num[i - 1])
                // Do not consider a duplicate number if its earlier appearance has
                // not been considered yet
                continue;
            if (!used[i]) {
                // Append an unused number
                used[i] = true;
                current.add(num[i]);
                // Recursively append next unused number
                recursivePermute(num, used, current, result);
                // Get back to original state, get ready for appending another unused number
                current.remove(current.size() - 1);
                used[i] = false;
            }
        }
    }
}