package com.dzhou.corejava.crackingthecode;


import java.util.HashSet;

/**
 * Created by davidzhou on 7/16/14.
 */
public class LinkedListNode<E> {
    E e;
    LinkedListNode<E> next;

    public LinkedListNode(E e) {
        this.e = e;
    }

    public int length() {
        int length = 0;
        LinkedListNode<E> n = this;

        while (n != null) {
            length++;
            n = n.next;
        }
        return length;
    }

    public E value() {
        return e;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        LinkedListNode<E> n = this;
        while (n != null) {
            sb.append(n.e).append(" ");
            n = n.next;
        }
        return sb.toString();
    }

    public LinkedListNode<E> addFirst(E e) {
        LinkedListNode<E> n = new LinkedListNode<E>(e);
        n.next = this;
        return n;
    }

    public void addLast(E e) {
        LinkedListNode<E> n = this;
        while (n.next != null)
            n = n.next;
        n.next = new LinkedListNode<E>(e);

    }

    /**
     * 2.1 Remove duplicates from unsorted linked list
     */
    public void removeDuplicates() {
        LinkedListNode<E> n = this;

        while (n != null) {

            LinkedListNode<E> p = n;
            LinkedListNode<E> q = p.next;

            while (q != null) {
                if (q.e.equals(n.e)) {
                    p.next = q.next;
                    q = p.next;
                } else {
                    p = p.next;
                    q = q.next;
                }
            }
            n = n.next;
        }


    }

    /**
     * 2.1 Remove duplicates from unsorted linked list
     */
    public void removeDuplicates1() {
        HashSet<E> set = new HashSet<E>();
        set.add(this.e);
        LinkedListNode<E> n = this;

        while (n.next != null) {
            if (!set.contains(n.next.e)) {
                set.add(n.next.e);
                n = n.next;
            } else {
                n.next = n.next.next;
            }
        }
    }

    /**
     * @param k k = 1 will be the last node
     * @return
     */
    public LinkedListNode<E> kthToLast(int k) {
        if (k <= 0)
            return null;

        LinkedListNode<E> p1 = this;
        LinkedListNode<E> p2 = this;

        for (int i = 0; i < k - 1; i++) {
            if (p2 == null)
                return null;
            p2 = p2.next;
        }

        if (p2 == null)
            return null;

        while (p2.next != null) {
            p2 = p2.next;
            p1 = p1.next;
        }
        return p1;

    }

    public LinkedListNode<E> reverse() {
        LinkedListNode<E> r = null;
        LinkedListNode<E> p1 = this;

        while (p1 != null) {
            LinkedListNode<E> p2 = p1.next;
            p1.next = r;
            r = p1;
            p1 = p2;
        }
        return r;
    }

    public static void main(String[] args) {
        LinkedListNode<Integer> l = new LinkedListNode<Integer>(1);
        System.out.println(l.length() + " - " + l);

        l = l.addFirst(2);
        System.out.println(l.length() + " - " + l);

        l = l.addFirst(3);
        System.out.println(l.length() + " - " + l);

        l.addLast(4);
        System.out.println(l.length() + " - " + l);

        l.addLast(5);
        System.out.println(l.length() + " - " + l);


        l.addLast(1);
        l.addLast(2);
        l.addLast(3);
        System.out.println(l.length() + " - " + l);

        l.removeDuplicates();
        System.out.println(l.length() + " - " + l);

    }
}
