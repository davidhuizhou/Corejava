package com.dzhou.corejava.dsaj;

import com.dzhou.corejava.algs4.Queue;
import com.sun.tools.corba.se.idl.constExpr.Not;

import java.util.NoSuchElementException;

/**
 * Created by davidzhou on 8/28/14.
 */
public class BST<Key extends Comparable<Key>, Value> {
    private Node root;

    private class Node {
        private Key key;
        private Value val;
        private Node left, right;
        private int N;

        public Node(Key key, Value val, int N) {
            this.key = key;
            this.val = val;
            this.N = N;
        }
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public int size() {
        return size(root);
    }

    private int size(Node x){
        if(x == null) return 0;
        else return x.N;
    }

    public boolean contains(Key key){
        return get(key) != null;
    }

    public Value get(Key key) {
        return get(root, key);
    }

    private Value get(Node x, Key key) {
        if(x == null) return null;
        int cmp = key.compareTo(x.key);
        if(cmp < 0) return get(x.left, key);
        else if (cmp > 0) return get(x.right, key);
        else return x.val;
    }

    public void put(Key key, Value val) {
        if(val == null) {
//            delete(key);
//            return;
        }
        put(root, key, val);
    }

    private Node put(Node x, Key key, Value val) {
        if(x == null) return new Node(key, val, 1);
        int cmp = key.compareTo(x.key);
        if(cmp < 0) x.left = put(x.left, key, val);
        else if (cmp > 0) x.right = put(x.right, key, val);
        else x.val = val;
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }


//    private Node delete(Node x, Key key) {
//        if (x == null) return null;
//        int cmp = key.compareTo(x.key);
//        if (cmp < 0) x.left = delete(x.left, key);
//        else if (cmp > 0) x.right = delete(x.right, key);
//        return x;
//    }

    public Key min() {
        if(isEmpty()) return null;
        return min(root).key;
    }

    private Node min(Node x) {
        if(x.left == null) return x;
        else return min(x.left);
    }

    public Key max(){
        if(isEmpty()) return null;
        return max(root).key;
    }

    private Node max(Node x) {
        if(x.right == null) return x;
        else return max(x.right);
    }

    public Key floor(Key key) {
        Node x = floor(root, key);
        if(x == null) return null;
        else return x.key;
    }

    private Node floor(Node x, Key key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp == 0) return x;
        if (cmp < 0) return floor(x.left, key);

        Node t = floor(x.right, key);
        if (t != null) return t;
        else return x;

    }

    public Key celing(Key key) {
        Node x = celing(root, key);
        if(x == null) return null;
        else return x.key;
    }

    private Node celing(Node x, Key key) {
        if(x == null) return null;
        int cmp = key.compareTo(x.key);
        if(cmp == 0) return x;
        if(cmp > 0) return celing(x.right, key);
        Node t = celing(x.left, key);
        if (t != null) return t;
        else return x;
    }

    public Key select(int k) {
        if(k < 0 || k >= size()) return null;
        Node x = select(root, k);
        return x.key;

    }
    private Node select(Node x, int k){
        if(x == null) return null;
        int t = size(x.left);
        if(t > k) return select(x.left, k);
        else if(t < k) return select(x.right, k - t -1);
        else return x;
    }

    public int rank(Key key) {
        return rank(key, root);
    }

    private int rank(Key key, Node x) {
        if(x == null) return 0;
        int cmp = key.compareTo(x.key);
        if(cmp < 0) return rank(key, x.left);
        else if(cmp > 0) return 1 + size(x.left) + rank(key, x.right);
        else return size(x.left);

    }

    public void deleteMin() {
        if(isEmpty()) throw new NoSuchElementException("System table underflow");
        root = deleteMin(root);

    }

    private Node deleteMin(Node x) {
        if(x.left == null) return x.right;
        x.left = deleteMin(x.left);
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }

    private Node deleteMax(Node x) {
        if(x.right == null) return x.left;
        x.right = deleteMax(x.right);
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }

    public void delete(Key key) {
        root = delete(root, key);

    }

    private Node delete(Node x, Key key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp < 0) x.left = delete(x.left, key);
        else if (cmp > 0) x.right = delete(x.right, key);
        else {
            if (x.left == null) return x.right;
            if (x.right == null) return x.left;
            Node t = x;
            x = min(t.right);
            x.right = deleteMin(x.right);
            x.left = t.left;

        }
        x.N = size(x.left) + size(x.right) + 1;
        return x;

    }

    public Iterable<Key> keys() {
        return keys(min(), max());
    }

    public Iterable<Key> keys(Key lo, Key hi){
        Queue<Key> queue = new Queue<Key>();
        keys(root, queue, lo, hi);
        return queue;
    }

    private void keys(Node x, Queue<Key> queue, Key lo, Key hi) {
        if(x == null) return;
        int cmplo = lo.compareTo(x.key);
        int cmphi = hi.compareTo(x.key);
        if(cmplo < 0) keys(x.left, queue, lo, hi);
        if(cmplo <=0 && cmphi >= 0) queue.enqueue(x.key);
        if(cmphi > 0) keys(x.right, queue, lo, hi);
    }

    public Iterable<Key> inOrderKeys(){
        Queue<Key> queue = new Queue<Key>();
        inOrderKeys(root, queue);
        return queue;
    }

    private void inOrderKeys(Node x, Queue<Key> queue) {
        if(x == null) return;
        inOrderKeys(x.left, queue);
        queue.enqueue(x.key);
        inOrderKeys(x.right, queue);

    }

    private void levelOrderKeys(Node x, Queue<Key> queue){
        if(x == null) return;
        queue.enqueue(x.key);
        levelOrderKeys(x.left, queue);
        levelOrderKeys(x.right, queue);

    }

}
