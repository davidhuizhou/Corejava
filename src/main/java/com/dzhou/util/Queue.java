package com.dzhou.util;

import com.dzhou.corejava.dsaj.ch2.PhoneListing;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by huizhou on 7/4/14.
 */
public class Queue<E> implements Iterable<E> {
    int size;
    private Node<E> first;
    private Node<E> last;

    public Queue() {
        first = null;
        last = null;
        size = 0;

    }

    public boolean isEmpty() {
        return first == null;
    }

    public int size() {
        return size;
    }


    /**
     * Returns the item least recently added to this queue.
     *
     * @return the item least recently added to this queue
     * @throws java.util.NoSuchElementException if this queue is empty
     */
    public E peek() {
        if (isEmpty())
            throw new NoSuchElementException();

        return first.item;

    }

    /**
     * Adds the item to this queue.
     *
     * @param item the item to add
     */
    public void enqueue(E item) {
        Node<E> newNode = new Node(item, null);
        if (isEmpty()) {
            first = newNode;

        } else {
            last.next = newNode;
        }
        last = newNode;
        size++;
    }

    /**
     * Removes and returns the item on this queue that was least recently added.
     *
     * @return the item on this queue that was least recently added
     * @throws java.util.NoSuchElementException if this queue is empty
     */
    public E dequeue() {
        if (isEmpty())
            throw new NoSuchElementException();

        E e = first.item;
        Node<E> f = first;
        first = first.next;
        if (first == null)
            last = null;
        f.item = null;
        f.next = null;
        size--;
        return e;


    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (E e : this)
            sb.append(e.toString()).append(" ");

        return sb.toString();
    }

    public Iterator<E> iterator() {
        return new ListIterator<E>(first);
    }

    private class ListIterator<E> implements Iterator<E> {
        private Node<E> current;

        public ListIterator(Node<E> first) {
            current = first;
        }

        public boolean hasNext() {
            return current != null;
        }

        public E next() {
            if (!hasNext())
                throw new NoSuchElementException();

            E e = current.item;
            current = current.next;
            return e;
        }

        public void remove() {
            throw new UnsupportedOperationException();

        }

    }

    private class Node<E> {
        E item;
        Node<E> next;

        Node(E e, Node<E> next) {
            this.item = e;
            this.next = next;
        }
    }

    public static void main(String[] args) {
        Queue<PhoneListing> q = new Queue<PhoneListing>();
        PhoneListing l;
        PhoneListing l1 = new PhoneListing("Bill", "1st Avenue", "123 4567");
        PhoneListing l2 = new PhoneListing("Al", "2nd Avenue", "456 3232");
        PhoneListing l3 = new PhoneListing("Mike", "3rd Avenue", "333 3333");
        PhoneListing l4 = new PhoneListing("Carol", "4th Avenue", "444 4444");
        PhoneListing l5 = new PhoneListing("David", "3rd Avenue", "333 3333");
        PhoneListing l6 = new PhoneListing("Bob", "4th Avenue", "444 4444");
        // an attempt to perform a dequeue on an initialized (empty) queue will return null
        try {
            System.out.println(q.dequeue());
        } catch (NoSuchElementException exc) {
            System.out.println(exc.getMessage());
        }

        // perform three enqueue to fill the queue and then output the queue
        q.enqueue(l1);
        q.enqueue(l2);
        q.enqueue(l3);
        q.enqueue(l4);

        System.out.println(q.toString());
        // perform three dequeue operations to empty the queue
        l = q.dequeue();
        System.out.println(l.toString());
        l = q.dequeue();
        System.out.println(l.toString());
        System.out.println(q.toString());


        q.enqueue(l5);
        q.enqueue(l6);
        System.out.println(q.toString());

        l = q.dequeue();
        System.out.println(l.toString());
        l = q.dequeue();
        System.out.println(l);

        System.out.println(q.toString());
        System.exit(0);
    }
}
