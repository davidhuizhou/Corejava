package com.dzhou.util;

import com.dzhou.corejava.dsaj.ch2.PhoneListing;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by huizhou on 7/12/14.
 */
public class QueueWithCircularLinkedList<E> implements Iterable<E> {
    int size;
    //dummy node
    private Node<E> last;

    public QueueWithCircularLinkedList() {
        this.size = 0;
        this.last = new Node(null, null);
        this.last.next = last;

    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public E peek(){
        if(isEmpty())
            throw new NoSuchElementException();

        return last.next.item;

    }

    public void enqueue(E item){
        Node<E> newNode = new Node(item, last);

        if(isEmpty()){
            last.next = newNode;
        } else {
            Node<E> next = last.next;
            while(next.next != last){
                next = next.next;
            }
            next.next = newNode;
        }
        size++;

    }

    public E dequeue(){
        if(isEmpty())
            throw new NoSuchElementException();

        Node<E> next = last.next;
        E e = next.item;
        last.next = next.next;

        next.item = null;
        next.next = null;
        size--;
        return e;
    }

    private E unlink(Node<E> p) {
        E e = p.next.item;
        p.next = p.next.next;
        size--;
        return e;
    }

    public String josephus(int m) {
        StringBuilder sb = new StringBuilder();

        Node<E> p = last;
        int i = 1;
        while (size >= 1) {
            if (p.next == last) {
                p = p.next;
            }
            if (i % m == 0) {
                E e = unlink(p);
                sb.append(e).append(" ");

            } else {
                p = p.next;
            }
            i++;

        }
        return sb.toString();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (E e : this)
            sb.append(e.toString()).append(" ");

        return sb.toString();
    }

    public Iterator<E> iterator() {
        return new ListIterator<E>(last.next);
    }

    private class ListIterator<E> implements Iterator<E> {
        private Node<E> current;

        public ListIterator(Node<E> first) {
            current = first;
        }

        public boolean hasNext() {
            return current != last;
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
        QueueWithCircularLinkedList<PhoneListing> q = new QueueWithCircularLinkedList<PhoneListing>();
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

        QueueWithCircularLinkedList<Integer> q1 = new QueueWithCircularLinkedList<Integer>();
        q1.enqueue(0);
        q1.enqueue(1);
        q1.enqueue(2);
        q1.enqueue(3);
        q1.enqueue(4);
        q1.enqueue(5);
        q1.enqueue(6);

        System.out.println(q1.toString());
        System.out.println(q1.josephus(2));

        System.exit(0);
    }
}
