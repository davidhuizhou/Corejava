package com.dzhou.corejava.crackingthecode;

import java.util.HashSet;

/**
 * Created by huizhou on 7/15/14.
 */
public class LinkedList<E> {

    int size;
    Node<E> first;

    LinkedList() {
        size = 0;
        first = null;
    }

    LinkedList(Node<E> first){
        this.first = first;
        Node<E> p = first;
        while(p != null){
            size++;
            p = p.next;
        }
    }

    void addFirst(E e) {
        Node<E> newNode = new Node<E>(e);
        newNode.next = first;
        first = newNode;
        size++;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        Node<E> p = first;
        while (p != null) {
            sb.append(p.e.toString()).append(" ");
            p = p.next;
        }
        return sb.toString();
    }

    /**
     *  2.1 Remove duplicates from unsorted linked list
     */
    public void removeDuplicates() {
        Node<E> n = first;
        Node<E> p = n;
        while (n != null && n.next != null) {
            while (p != null) {
                if (p.next != null && p.next.e.equals(n.e)) {
                    p.next = p.next.next;
                    size--;
                } else {
                    p = p.next;
                }
            }
            n = n.next;
            p = n;
        }

    }

    /**
     *  2.1 Remove duplicates from unsorted linked list
     */
    public void removeDuplicates1() {
        if (first == null)
            return;
        HashSet<E> set = new HashSet<E>();
        set.add(first.e);
        Node<E> p = first;

        while (p.next != null) {
            if (!set.contains(p.next.e)) {
                set.add(p.e);
                p = p.next;
            } else {
                p.next = p.next.next;
                size--;
            }
        }

    }

    /**
     *
     * @param k k = 1 will be the last node
     * @return
     */
    public Node<E> kthToLast(int k) {
        if (k <= 0) return null;

        Node<E> p1 = first;
        Node<E> p2 = first;

        for(int i = 0; i <  k -1; i++){
            if(p2 == null) return null;
            p2 = p2.next;
        }
        if(p2 == null) return null;

        while(p2.next != null){
            p2 = p2.next;
            p1 = p1.next;
        }
        return p1;

    }

    /**
     *
     * @param node 2.3 deleteNode
     *
     */
    public boolean deleteNode(Node<E> node){
        if(node == null)
            return false;

        if(node.next == null){
            node = null;
        } else {
            node.e = node.next.e;
            node.next = node.next.next;
        }
        return true;
    }


    /**
     *
     * 2.5 add number list
     */
     Node<Integer> addNodes(Node<Integer> l1, Node<Integer> l2){
        int carry = 0;
        Node<Integer> sentinel = new Node<Integer>(0);

        Node<Integer> n1 = l1;
        Node<Integer> n2 = l2;
        Node n3 = sentinel;

        while(n1 != null || n2 != null){
            if(n1 != null) {
                carry += n1.e;
                n1 = n1.next;
            }

            if(n2 != null){
                carry += n2.e;
                n2 = n2.next;
            }

            n3.next = new Node<Integer>(carry % 10);
            n3 = n3.next;
            carry = carry / 10;

        }
        if(carry == 1)
            n3.next = new Node<Integer>(1);

        return sentinel.next;
    }

    public LinkedList<Integer> addList(LinkedList<Integer> l1, LinkedList<Integer> l2){
        Node<Integer> n1 = (Node<Integer>)l1.first;
        Node<Integer> n2 = (Node<Integer>)l2.first;

        Node<Integer> n3 = addNodes(n1, n2);
        LinkedList l3 = new LinkedList(n3);

        return l3;
    }

    public LinkedList<Integer> addList1(LinkedList<Integer> l1, LinkedList<Integer> l2){
        l1.reverse();
        l2.reverse();
        LinkedList l3 = addList(l1, l2);
        l3.reverse();
        return l3;
    }

    public void reverse(){
        Node<E> rev = null;
        Node<E> n = null;

        while(first != null) {
            n = first;
            first = first.next;
            n.next = rev;
            rev = n;
        }
        first = rev;

    }


    private class Node<E> {
        E e;
        Node<E> next;

        Node(E e) {
            this.e = e;
            next = null;
        }

        public String toString(){
            return e.toString();
        }

    }

}
