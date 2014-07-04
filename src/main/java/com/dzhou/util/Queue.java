package com.dzhou.util;

import java.util.NoSuchElementException;

/**
 * Created by huizhou on 7/4/14.
 */
public class Queue<E>{
    int size;
    private Node<E> first;
    private Node<E> last;

    public Queue(){
        first = null;
        last = null;
        size = 0;

    }

    public boolean isEmpty(){
        return first == null;
    }

    public int size() {
        return size;
    }

    /**
     * Adds the item to this queue.
     * @param item the item to add
     */
    public void enqueue(E item){
        Node<E> newNode = new Node(item, null);
        if(isEmpty()){
            first = newNode;

        }else{
            last.next = newNode;
        }
        last = newNode;
        size++;
    }

    /**
     * Removes and returns the item on this queue that was least recently added.
     * @return the item on this queue that was least recently added
     * @throws java.util.NoSuchElementException if this queue is empty
     */
    public E dequeue(){
        if(isEmpty())
            throw new NoSuchElementException();

        E e = first.item;
        Node<E> f = first;
        first = first.next;
        if(first == null)
            last = null;
        f.item = null;
        f.next = null;
        size--;
        return e;


    }

    private class Node<E> {
        E item;
        Node<E> next;

        Node(E e, Node<E> next){
            this.item = e;
            this.next = next;
        }
    }
}
