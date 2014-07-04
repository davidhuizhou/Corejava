package com.dzhou.util;

import com.dzhou.corejava.dsaj.ch2.PhoneListing;

import java.util.NoSuchElementException;

/**
 * Created by davidzhou on 7/3/14.
 */
public class ArrayQueue <E> {
    private E[] elementData;
    private int size;
    private int head;
    private int tail;

    public ArrayQueue(){
        size = 0;
        head = 0;
        tail = 0;
        elementData = (E[]) new Object[10];
    }

    public ArrayQueue(int initialCapacity){
        size = 0;
        head = 0;
        tail = 0;
        elementData = (E[]) new Object[initialCapacity];
    }

    private boolean hasMoreRoom() {
        if (size >= elementData.length)
            return false;
        else
            return true;

    }

    /**
     * Inserts the specified element at the end of this deque.
     *
     * <p>This method is equivalent to {@link #add}.
     *
     * @param e the element to add
     * @throws NullPointerException if the specified element is null
     */
    private void addLast(E e) {
        if(e == null)
            throw new NullPointerException();

        if(!hasMoreRoom())
            throw new ArrayIsFullError("");

        elementData[tail] = e;
        tail = (tail + 1) % elementData.length;
        size++;

    }


    private boolean empty() {
        return size == 0;
    }

    public boolean add(E e){
        addLast(e);
        return true;
    }

    /**
     * Inserts the specified element at the end of this deque.
     *
     * @param e the element to add
     * @return <tt>true</tt>
     * @throws NullPointerException if the specified element is null
     */
    public boolean offer(E e) {
        addLast(e);
        return true;
    }

    /**
     * Retrieves and removes the head of this queue.  This method differs
     * @return the head of this queue
     * @throws java.util.NoSuchElementException if this queue is empty
     */
    public E remove(){
        if(empty())
            throw new NoSuchElementException();

        E e = elementData[head];
        elementData[head] = null;
        head = (head + 1) % elementData.length;
        size--;
        return e;


    }

    /**
     *
     * @return the head of the queue represented by this deque, or
     *         <tt>null</tt> if this deque is empty
     */
    public E poll() {
        if(empty())
            return null;

        E e = elementData[head];
        elementData[head] = null;
        head = (head + 1) % elementData.length;
        size--;
        return e;
    }

    /**
     * Retrieves, but does not remove, the head of the queue represented by
     * this deque.  This method differs from peek only in
     * that it throws an exception if this deque is empty.
     *
     * <p>This method is equivalent to
     *
     * @return the head of the queue represented by this deque
     * @throws NoSuchElementException {@inheritDoc}
     */
    public E element() {
        if(empty())
            throw new NoSuchElementException();
        return elementData[head];
    }

    public E peek(){
        if(empty())
            return null;
        return elementData[head];
    }

    public void showAll() {

        System.out.print("head=" + head + ",tail=" + tail +",size=" + size);

        if(head < tail)
            for (int i = head; i < tail; i++)
                System.out.print(elementData[i] == null ? "ArrayQueue[" + i + "] == null " : elementData[i].toString());
        else{
            for(int i = head; i < elementData.length; i++)
                System.out.print(elementData[i] == null ? "ArrayQueue[" + i + "] == null " : elementData[i].toString());

            for(int i = 0; i < tail; i++)
                System.out.print(elementData[i] == null ? "ArrayQueue[" + i + "] == null " : elementData[i].toString());
        }
        System.out.println("");
    }

    public static void main(String[] args){
        ArrayQueue<PhoneListing> q = new ArrayQueue<PhoneListing>(4);
        PhoneListing l;
        PhoneListing l1 = new PhoneListing("Bill",  "1st Avenue", "123 4567" );
        PhoneListing l2 = new PhoneListing("Al",    "2nd Avenue", "456 3232");
        PhoneListing l3 = new PhoneListing("Mike",  "3rd Avenue", "333 3333");
        PhoneListing l4 = new PhoneListing("Carol", "4th Avenue", "444 4444");
        PhoneListing l5 = new PhoneListing("David",  "3rd Avenue", "333 3333");
        PhoneListing l6 = new PhoneListing("Bob", "4th Avenue", "444 4444");
        // an attempt to perform a dequeue on an initialized (empty) queue will return null
        System.out.println(q.poll());
        // perform three enqueue to fill the queue and then output the queue
        System.out.println(q.offer(l1));
        System.out.println(q.offer(l2));
        System.out.println(q.offer(l3));
        System.out.println(q.offer(l4));
        q.showAll();
        // perform three dequeue operations to empty the queue
        l = q.poll();
        System.out.println(l.toString( ));
        l = q.poll();
        System.out.println(l.toString( ));
        q.showAll();


        System.out.println(q.offer(l5));
        System.out.println(q.offer(l6));
        q.showAll();

        l = q.poll();
        System.out.println(l.toString( ));
        l = q.poll();
        System.out.println(l);

        q.showAll();
        System.exit(0);
    }
}
