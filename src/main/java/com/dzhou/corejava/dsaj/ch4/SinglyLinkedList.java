package com.dzhou.corejava.dsaj.ch4;

/**
 * Created by huizhou on 6/30/14.
 */
public class SinglyLinkedList<T> {

    private Node<T> h;

    public SinglyLinkedList() {
        h = new Node<T>();
        h.l = null;
        h.next = null;

    }

    public boolean insert(T newNode){
        Node<T> n = new Node();
        if(n == null)
            return false;
        else{
            KeyMode k = ((KeyMode)newNode).deepCopy();
            n.next = h.next;
            h.next = n;
            n.l = (T)k;
            return true;

        }
    }


    public T fetch(Object targetKey) {
        Node<T> n = h.next;
        while (n != null && ((KeyMode) n.l).compareTo(targetKey) != 0) {
            n = n.next;

        }

        if (n != null) {
            KeyMode k = ((KeyMode) n.l).deepCopy();
            return (T) k;

        } else {
            return null;

        }

    }

    public boolean delete(Object targetKey) {
        Node<T> p = h;
        Node<T> n = h.next;

        while (n != null && ((KeyMode) n.l).compareTo(targetKey) != 0) {
            p = n;
            n = n.next;

        }

        if (n != null) {
            p.next = n.next;
            return true;
        } else {
            return false;
        }


    }

    public boolean update(Object targetKey, T newNode) {
        if (!delete(targetKey))
            return false;
        if (!insert(newNode))
            return false;

        return true;
    }

    public T pop() {
        Node<T> n = h.next;

        if (n == null)
            return null;

        else {
            h.next = n.next;
            return n.l;

        }


    }

    public void showAll(){

        Node<T> n = h.next;
        while(n != null){
            System.out.println(n.l.toString());
            n = n.next;
        }

    }


    public class Node<T> {
        private T l;
        private Node<T> next;

        public Node() {

        }
    }

    public static void main(String[] args){
        SinglyLinkedList<PhoneListing> boston = new SinglyLinkedList();
        PhoneListing l1 = new PhoneListing("X", "1st Avenue", "123 4567" );
        PhoneListing l2 = new PhoneListing("X", "2nd Avenue", "456 3232");
        PhoneListing l3 = new PhoneListing("X", "3rd Avenue", "333 3333");
        // three ìpushî operations
        boston.insert(l1);
        boston.insert(l2);
        boston.insert(l3);
        //Three ìpopî operations
        l3 = boston.fetch("X");   // first ìpopî
        boston.delete("X");
        System.out.println(l3); // (Java automatically invokes the toString method)
        l3 = boston.fetch("X");   // second ìpopî
        boston.delete("X");
        System.out.println(l3); // (Java automatically invokes the toString method)
        l3 = boston.fetch("X");   // third ìpopî
        boston.delete("X");
        System.out.println(l3); //
        System.exit(0);

    }
}
