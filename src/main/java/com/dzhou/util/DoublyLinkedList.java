package com.dzhou.util;

import com.dzhou.corejava.dsaj.ch4.PhoneListing;

import java.util.NoSuchElementException;

/**
 * Created by davidzhou on 7/1/14.
 */
public class DoublyLinkedList<E> {

    int size;
    Node<E> first;
    Node<E> last;

    public DoublyLinkedList(){

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
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
    }

    /**
     * Tells if the argument is the index of an existing element.
     */
    private boolean isElementIndex(int index) {
        return index >= 0 && index < size;
    }

    private void checkElementIndex(int index) {
        if (!isElementIndex(index))
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
    }


    /**
     * Returns the (non-null) Node at the specified element index.
     */
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

    /**
     * Links e as first element.
     */
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

    /**
     * Links e as last element.
     */
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

    /**
     * Inserts element e before non-null Node succ.
     */
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


    /**
     * Unlinks non-null first node f.
     */
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

    /**
     * Unlinks non-null last node l.
     */
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

    /**
     * Unlinks non-null node x.
     */
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

        if(index == size)
            linkLast(element);
        else
            linkBefore(element, node(index));
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
        Node<E> l = last;
        if(l == null)
            throw new NoSuchElementException();
        return l.item;
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
     * Removes and returns the first element from this list.
     *
     * @return the first element from this list
     * @throws NoSuchElementException if this list is empty
     */
    public E removeFirst(){
        if(first == null)
            throw new NoSuchElementException();
        else
            return unlinkFirst(first);
    }

    /**
     * Removes and returns the last element from this list.
     *
     * @return the last element from this list
     * @throws NoSuchElementException if this list is empty
     */
    public E removeLast(){
        if(last == null)
            throw new NoSuchElementException();
        else
            return unlinkLast(last);
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
        return unlink(node(index));
    }

    /**
     * Replaces the element at the specified position in this list with the
     * specified element.
     *
     * @param index index of the element to replace
     * @param e element to be stored at the specified position
     * @return the element previously at the specified position
     * @throws IndexOutOfBoundsException {@inheritDoc}
     */
    public E set(int index, E e){
        checkElementIndex(index);
        Node<E> n = node(index);
        E oldElement = n.item;
        n.item = e;
        return oldElement;
    }


    public void showAll(){
        Node n = first;

        System.out.print("size=" + size + " ");
        while(n != null){
            System.out.print(n.item.toString());
            n = n.next;
        }
        System.out.println("");

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

    public static void main(String[] args) {

        DoublyLinkedList<PhoneListing> boston = new DoublyLinkedList<PhoneListing>();
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

        System.exit(0);
    }
}
