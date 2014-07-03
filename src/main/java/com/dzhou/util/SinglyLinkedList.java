package com.dzhou.util;

import com.dzhou.corejava.dsaj.ch4.Node;
import com.dzhou.corejava.dsaj.ch4.PhoneListing;

/**
 * Created by davidzhou on 7/2/14.
 */
public class SinglyLinkedList<E>
        implements Cloneable, java.io.Serializable {

    int size;
    Node<E> first;

    public SinglyLinkedList(){

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
        Node<E> n = first;
        for(int i = 0; i < index; i++)
            n = n.next;
        return n;
    }

    private void linkFirst(E e) {
        Node<E> f = first;
        Node<E> newNode = new Node<E>(e, first);
        first = newNode;
        size++;

    }

    private void linkAfter(E e, Node<E> prev){
        Node<E> next = prev.next;
        Node<E> newNode = new Node<E>(e, next);
        prev.next = newNode;
        size++;
    }


    public void addFirst(E e){
        linkFirst(e);
    }

    public void add(int index, E element) {
        checkPositionIndex(index);

        if(index == 0 || size == 0)
            linkFirst(element);
        else {
            linkAfter(element, node(index - 1));

        }
    }

    public void showAll(){
        Node n = first;
        while(n != null){
            System.out.println(n.item.toString());
            n = n.next;
        }

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

        SinglyLinkedList<PhoneListing> boston = new SinglyLinkedList();
        PhoneListing l1 = new PhoneListing("X", "1st Avenue", "123 4567" );
        PhoneListing l2 = new PhoneListing("X", "2nd Avenue", "456 3232");
        PhoneListing l3 = new PhoneListing("X", "3rd Avenue", "333 3333");
        // three ìpushî operations
        boston.addFirst(l1);
        boston.add(1, l2);
        boston.add(2, l3);

        boston.showAll();
    }
}
