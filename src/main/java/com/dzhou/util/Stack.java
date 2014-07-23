package com.dzhou.util;

import com.dzhou.corejava.dsaj.ch2.PhoneListing;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by huizhou on 7/4/14.
 */
public class Stack <E> implements Iterable<E>{
    int size;
    Node<E> first;

    public Stack(){

    }

    public int size(){
        return size;
    }

    public boolean isEmpty(){
        return first == null;
    }

    /**
     * Adds the item to this stack.
     * @param e the item to add
     */
    public void push(E e){
        Node n = new Node(e, first);
        first = n;
        size++;

    }

    /**
     * Removes and returns the item most recently added to this stack.
     * @return the item most recently added
     * @throws java.util.NoSuchElementException if this stack is empty
     */
    public E pop(){
        if(isEmpty())
            throw new NoSuchElementException();
        E e = first.item;
        Node<E> f = first;
        first = first.next;
        f.next = null;
        f.item = null;
        size--;
        return e;

    }

    /**
     * Returns (but does not remove) the item most recently added to this stack.
     * @return the item most recently added to this stack
     * @throws java.util.NoSuchElementException if this stack is empty
     */
    public E peek(){
        if(isEmpty())
            throw new NoSuchElementException();
        return first.item;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();

        Node n = first;
        while(n != null){
            sb.append(n.item.toString());
            n = n.next;
        }
        return sb.toString();
    }


    private class Node<E> {
        E item;
        Node<E> next;

        Node(E e, Node<E> next){
            this.item = e;
            this.next = next;
        }
    }

    public Iterator<E> iterator(){
        return new ListIterator<E>(first);
    }

    private class ListIterator<E> implements Iterator<E>{
        private Node<E> current;

        public ListIterator(Node<E> first){
            this.current = first;
        }
        public boolean hasNext(){
            return current != null;
        }
        public E next(){
            if(!hasNext())
                throw new NoSuchElementException();
            E e = current.item;
            current = current.next;
            return e;
        }

        public void remove(){
            throw new UnsupportedOperationException();
        }

    }

    public static void main(String[] args) {
        Stack<PhoneListing> s = new Stack<PhoneListing>();
        PhoneListing l;
        PhoneListing l1 = new PhoneListing("Bill", "1st Avenue", "123 4567");
        PhoneListing l2 = new PhoneListing("Al", "2nd Avenue", "456 3232");
        PhoneListing l3 = new PhoneListing("Mike", "3rd Avenue", "333 3333");
        PhoneListing l4 = new PhoneListing("Carol", "4th Avenue", "444 4444");


        // an attempt to perform a pop on an initialized (empty) stack will return null
        System.out.println("stack is empty is " + s.isEmpty());
        try {
            s.pop();
        } catch(NoSuchElementException exc){
            System.out.println(exc.getMessage());
        }

        // perform three pushes to fill the stack and then output the stack
        s.push(l1);
        s.push(l2);
        s.push(l3);
        System.out.println(s.toString());
        s.push(l4);
        System.out.println(s.toString());

        System.out.println("Test iterator -");
        Iterator<PhoneListing> iterator = s.iterator();
        while(iterator.hasNext()){
            System.out.print(iterator.next().toString());
        }

        System.out.println("\nTest pop");
        // perform three pop operations to empty the stack
        l = s.pop();
        System.out.println(l.toString());
        l = s.pop();
        System.out.println(l.toString());
        l = s.pop();
        System.out.println(l.toString());
        // an attempt to perform a pop on an empty stack will return null
        l = s.pop();
        System.out.println(l);
        System.exit(0);
    }



}
