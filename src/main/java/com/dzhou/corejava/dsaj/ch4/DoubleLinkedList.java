package com.dzhou.corejava.dsaj.ch4;

/**
 * Created by davidzhou on 7/1/14.
 */
public class DoubleLinkedList <E> {

    int size;
    Node<E> first;
    Node<E> last;

    public DoubleLinkedList(){

    }

    private void linkFirst(E e) {
        Node<E> f = first;
        Node newNode = new Node(null, e, first);
        first = newNode;

        if(f == null)
            last = newNode;
        else
            f.prev = newNode;

        size++;

    }

    private void linkLast(E e){
        Node<E> l = last;
        Node newNode = new Node(first, e, null);
        last = newNode;

        if(l == null)
            first = newNode;
        else
            l.next = newNode;

        size++;
    }

    private E unlinkFirst(Node<E> f) {
        E element = f.item;
        Node<E> next = f.next;

        f.item = null;
        f.next = null;

        first = next;
        if(next == null)
            last = null;
        else
            next.prev = null;

        size--;
        return element;



    }

    public E unlinkLast(Node<E> l){
        E element = l.item;
        Node<E> prev = l.prev;

        l.item = null;
        l.prev = null;

        if(prev == null)
            first = null;
        else
            prev.next = null;
        size--;
        return element;

    }

    public void addFirst(E e){
        linkFirst(e);
    }

    public void addLast(E e){
        linkLast(e);
    }

    public E removeFirst(){
        if(first == null)
            return null;
        else
            return unlinkFirst(first);
    }

    public E removeLast(){
        if(last == null)
            return null;
        else
            return unlinkLast(last);
    }


    private class Node<E>{
        E item;
        Node<E> prev;
        Node<E> next;

        Node(Node<E> prev, E element, Node<E> next){
            this.prev = prev;
            this.item = element;
            this.next = next;

        }

    }
}
