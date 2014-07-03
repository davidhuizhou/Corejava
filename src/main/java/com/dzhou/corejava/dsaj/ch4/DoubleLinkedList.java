package com.dzhou.corejava.dsaj.ch4;

import java.util.NoSuchElementException;

/**
 * Created by davidzhou on 7/1/14.
 */
public class DoubleLinkedList <E> {

    int size;
    Node<E> first;
    Node<E> last;

    public DoubleLinkedList(){

    }

    private boolean isPositionIndex(int index){
        return (0 <= index && index <= size);
    }

    private String outOfBoundsMsg(int index) {
        return "Index: "+index+", Size: "+size;
    }

    private void checkPositionIndex(int index) {
        if (!isPositionIndex(index))
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
    }

    private boolean isElementIndex(int index) {
        return index >= 0 && index < size;
    }

    private void checkElementIndex(int index) {
        if (!isElementIndex(index))
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
    }



    Node<E> node(int index){
        if(index < (size >> 1)){
            Node<E> n = first;
            for(int i = 0; i < index; i++)
                n = n.next;

            return n;
        } else {
            Node<E> n = last;
            for(int i = size - 1; i > index; i--)
                n = n.prev;
            return n;
        }

    }
    private void linkFirst(E e) {
        Node<E> f = first;
        Node<E> newNode = new Node<E>(null, e, first);
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

    private void linkBefore(E e, Node<E> succ){
        Node<E> prev = succ.prev;
        Node<E> newNode = new Node(prev, e, succ);
        succ.prev = newNode;

        if(prev == null)
            first = newNode;
        else
            prev.next = newNode;
        size++;
    }


    private E unlinkFirst(Node<E> f) {
        E element = f.item;
        Node<E> next = f.next;
        first = next;

        if(next == null)
            last = null;
        else {
            next.prev = null;
            f.next = null;
        }

        f.item = null;
        size--;
        return element;



    }

    private E unlinkLast(Node<E> l){
        E element = l.item;
        Node<E> prev = l.prev;
        last = prev;

        if(prev == null)
            first = null;
        else{
            prev.next = null;
            l.prev = null;
        }
        l.item = null;
        size--;
        return element;


    }

    E unlink(Node<E> x) {
        E element = x.item;
        Node<E> prev = x.prev;
        Node<E> next = x.next;

        if(prev == null)
            first = next;
        else {
            prev.next = next;
            x.prev = null;
        }

        if(next == null)
            last = prev;
        else {
            next.prev = prev;
            x.next = null;
        }
        size--;
        return element;

    }

    public void addFirst(E e){
        linkFirst(e);
    }

    public void addLast(E e){
        linkLast(e);
    }

    public boolean add(E e){
        linkLast(e);
        return true;
    }

    public void add(int index, E element) {
        checkPositionIndex(index);

        if(index == size)
            linkLast(element);
        else
            linkBefore(element, node(index));
    }


    public E getFirst() {
        Node<E> f = first;
        if(f == null)
            throw new NoSuchElementException();
        return f.item;
    }

    public E getLast() {
        Node<E> l = last;
        if(l == null)
            throw new NoSuchElementException();
        return l.item;
    }

    public E get(int index) {
        checkElementIndex(index);
        return node(index).item;
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

    public E remove(int index){
        checkElementIndex(index);
        return unlink(node(index));
    }

    public E set(E e, int index){
        checkElementIndex(index);
        Node<E> n = node(index);
        E oldElement = n.item;
        n.item = e;
        return oldElement;
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
