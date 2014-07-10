package com.dzhou.util;

import com.dzhou.corejava.dsaj.ch4.Node;
import com.dzhou.corejava.dsaj.ch4.PhoneListing;

import java.util.NoSuchElementException;

/**
 * Created by davidzhou on 7/2/14.
 */
public class SinglyLinkedList<E>
        implements Cloneable, java.io.Serializable {

    int size;
    Node<E> first;

    public SinglyLinkedList(){

    }

    /**
     * Tells if the argument is the index of a valid position for an
     * iterator or an add operation.
     */
    private boolean isPositionIndex(int index){
        return (0 <= index && index <= size);
    }

    private String outOfBoundsMsg(int index) {
        return "Index: "+index+", Size: "+size;
    }


    private void checkPositionIndex(int index) {
        if (!isPositionIndex(index))
            throw new ArrayIndexOutOfBoundsException(outOfBoundsMsg(index));
    }


    /**
     * Tells if the argument is the index of an existing element.
     */
    private boolean isElementIndex(int index) {
        return index >= 0 && index < size;
    }

    private void checkElementIndex(int index) {
        if (!isElementIndex(index))
            throw new ArrayIndexOutOfBoundsException(outOfBoundsMsg(index));
    }


    /**
     * Returns the (non-null) Node at the specified element index.
     */
    Node<E> node(int index){
        Node<E> n = first;
        for(int i = 0; i < index; i++)
            n = n.next;
        return n;
    }

    /**
     * Links e as first element.
     */
    private void linkFirst(E e) {
        Node<E> f = first;
        Node<E> newNode = new Node<E>(e, first);
        first = newNode;
        size++;

    }

    /**
     * Links e as last element.
     */
    private void linkLast(E e) {
        if(size == 0)
            linkFirst(e);
        else
            linkAfter(e, node(size - 1));
    }

    /**
     * Inserts element e after non-null Node prev.
     */
    private void linkAfter(E e, Node<E> prev){
        Node<E> next = prev.next;
        Node<E> newNode = new Node<E>(e, next);
        prev.next = newNode;
        size++;
    }


    /**
     * Unlinks non-null first node f.
     */
    private E unlinkFirst(Node<E> f){
        E element = f.item;
        Node<E> next = first.next;
        f.next = null;
        f.item = null;
        first = next;
        size--;
        return element;

    }

    /**
     * Unlinks non-null last node l
     */
    private E unlinkLast(Node<E> l) {
        if(size == 1)
            return unlinkFirst(l);

        Node<E> prev = node(size - 2);
        return unlinkNext(l, prev);
    }

    /**
     * Unlinks non-null node which after the node prev.
     */
    private E unlinkNext(Node<E>x, Node<E> prev){
        E element = x.item;
        Node<E> next = x.next;
        prev.next = next;
        x.next = null;
        x.item = null;
        size--;
        return element;

    }


    public void addFirst(E e){
        linkFirst(e);
    }

    /**
     * Appends the specified element to the end of this list.
     *
     * <p>This method is equivalent to {@link #add}.
     *
     * @param e the element to add
     */
    public void addLast(E e) {
        linkLast(e);
    }


    /**
     * Inserts the specified element at the specified position in this list.
     * Shifts the element currently at that position (if any) and any
     * subsequent elements to the right (adds one to their indices).
     *
     * @param index index at which the specified element is to be inserted
     * @param element element to be inserted
     * @throws IndexOutOfBoundsException {@inheritDoc}
     */

    public void add(int index, E element) {
        checkPositionIndex(index);

        if(index == 0)
            linkFirst(element);
        else {
            linkAfter(element, node(index - 1));

        }
    }

    /**
     * Returns the element at the specified position in this list.
     *
     * @param index index of the element to return
     * @return the element at the specified position in this list
     * @throws IndexOutOfBoundsException {@inheritDoc}
     */
    public E get(int index) {
        checkElementIndex(index);
        return node(index).item;
    }

    /**
     * Returns the first element in this list.
     *
     * @return the first element in this list
     * @throws NoSuchElementException if this list is empty
     */
    public E getFirst() {
        Node<E> f = first;

        if(f == null)
            throw new NoSuchElementException();

        return f.item;
    }

    /**
     * Returns the last element in this list.
     *
     * @return the last element in this list
     * @throws NoSuchElementException if this list is empty
     */
    public E getLast() {
        if(size == 0)
            throw new NoSuchElementException();

        Node<E> n = node(size - 1);
        return n.item;

    }


    /**
     * Removes and returns the first element from this list.
     *
     * @return the first element from this list
     * @throws NoSuchElementException if this list is empty
     */
    public E removeFirst() {
        Node<E> f = first;
        if(f == null)
            throw new NoSuchElementException();

        return unlinkFirst(f);

    }

    /**
     * Removes and returns the last element from this list.
     *
     * @return the last element from this list
     * @throws NoSuchElementException if this list is empty
     */
    public E removeLast() {
        if(size == 0)
            throw new NoSuchElementException();

        return unlinkLast(node(size - 1));
    }

    /**
     * Removes the element at the specified position in this list.  Shifts any
     * subsequent elements to the left (subtracts one from their indices).
     * Returns the element that was removed from the list.
     *
     * @param index the index of the element to be removed
     * @return the element previously at the specified position
     * @throws IndexOutOfBoundsException {@inheritDoc}
     */
    public E remove(int index){
        checkElementIndex(index);

        if(size == 1)
            return unlinkFirst(first);

        return unlinkNext(node(index), node(index - 1));
    }

    public void removeAll(E e) {
        if (e == null || first == null)
            return;

        Node<E> prev = null;
        Node<E> ip = first;

        while (ip != null) {

            if (ip.item.equals(e)) {
                if (prev == null) {
                    removeFirst();
                    ip = first;
                } else {
                    unlinkNext(ip, prev);
                    ip = prev.next;

                }

            } else {
                prev = ip;
                ip = ip.next;
            }


        }
    }

    /**
     * Replaces the element at the specified position in this list with the
     * specified element.
     *
     * @param index index of the element to replace
     * @param element element to be stored at the specified position
     * @return the element previously at the specified position
     * @throws IndexOutOfBoundsException {@inheritDoc}
     */
    public E set(int index, E element) {
        checkElementIndex(index);
        Node<E> x = node(index);
        E oldValue = x.item;
        x.item = element;
        return oldValue;

    }

    public void showAll(){
        Node n = first;

        System.out.print("size=" + size + " ");
        while(n != null){
            System.out.print(n.item.toString() + " ");
            n = n.next;
        }
        System.out.println("");

    }


    private class Node<E> {
        E item;
        Node<E> next;

        Node(E element, Node<E> next){
            this.item = element;
            this.next = next;
        }
    }

    public static void main(String[] args) {

        SinglyLinkedList<PhoneListing> boston = new SinglyLinkedList<PhoneListing>();
        PhoneListing l1 = new PhoneListing("Bill", "1st Avenue", "123 4567" );
        PhoneListing l2 = new PhoneListing("Al", "2nd Avenue", "456 3232");
        PhoneListing l3 = new PhoneListing("Mike", "3rd Avenue", "333 3333");
        PhoneListing l4 = new PhoneListing("David", "3rd Avenue", "333 3333");
        boston.addFirst(l1);  // test insert
        boston.addLast(l2);
        boston.add(1, l3);
        boston.showAll();


        boston.set(1, l4);
        boston.showAll();

        l3 = boston.getFirst(); // test fetch of Mike
        System.out.println(l3.toString());

        l3 = boston.getLast(); // test fetch of Mike
        System.out.println(l3.toString());

        l3 = boston.get(1); // test fetch of Mike
        System.out.println(l3.toString());

        boston.remove(1);  // test delete of Al
        boston.showAll();

        boston.removeFirst();
        boston.showAll();

        boston.removeLast();
        boston.showAll();

        System.out.println("test revmoeAll");
        SinglyLinkedList<Integer> list = new SinglyLinkedList<Integer>();
        list.showAll();

        list.removeAll(1);
        list.showAll();

        list.addLast(1);
        list.showAll();

        list.removeAll(0);
        list.showAll();

        list.removeAll(1);
        list.showAll();


        list.addLast(1);
        list.addLast(10);
        list.addLast(3);
        list.addLast(1);
        list.addLast(11);
        list.addLast(4);
        list.addLast(2);
        list.addLast(20);
        list.addLast(3);
        list.addLast(1);
        list.addLast(30);
        list.addLast(5);
        list.showAll();

        list.removeAll(1);
        list.showAll();
        list.removeAll(3);
        list.showAll();
        list.removeAll(10);
        list.showAll();

        System.exit(0);
    }
}
