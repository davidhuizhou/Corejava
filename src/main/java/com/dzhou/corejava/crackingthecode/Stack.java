package com.dzhou.corejava.crackingthecode;

/**
 * Created by huizhou on 7/22/14.
 */
public class Stack {

    int size;
    Node first;
    Node min;

    public Stack() {
        this.size = 0;
        this.first = null;
        this.min = null;
    }

    public void push(Comparable e) {
        Node node = new Node(e);
        node.next = first;
        first = node;
        size++;

        if (min == null) {
            min = node;
            return;
        }

        if (e.compareTo(min.e) <= 0) {
            node.parent = min;
            min = node;
            return;
        }

        Node p = min;
        Node q = min.parent;
        while (q != null && q.e.compareTo(e) < 0) {
            p = p.parent;
            q = p.parent;
        }
        p.parent = node;
        node.parent = q;
        return;
    }

    public Comparable pop() {
        Node node = first;
        Comparable e = node.e;
        first = node.next;
        node.next = null;
        node.e = null;
        size--;

        if (min == node) {
            min = node.parent;
            node.parent = null;
            return e;
        }

        Node p = min;
        Node q = p.parent;
        while (q != node) {
            p = p.parent;
            q = q.parent;
        }

        p.parent = q.parent;
        node.parent = null;
        return e;

    }

    public Comparable min() {
        if (min != null)
            return min.e;

        else
            return null;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();

        Node n = first;
        while (n != null) {
            sb.append(n.e.toString()).append(" ");
            n = n.next;
        }

        sb.append("|");
        n = min;
        while (n != null) {
            sb.append(n.e.toString()).append(" ");
            n = n.parent;
        }
        sb.append("|");
        sb.append(min());

        return sb.toString();
    }

    private class Node {
        Comparable e;
        Node next;
        Node parent;

        Node(Comparable e) {
            this.e = e;
        }
    }
}
