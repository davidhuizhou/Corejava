package com.dzhou.util;

import com.dzhou.corejava.dsaj.ch2.Listing;
import com.dzhou.corejava.dsaj.ch2.PhoneListing;

import java.util.EmptyStackException;

/**
 * Created by davidzhou on 7/3/14.
 */
public class Stack <E> {
    private int size;
    private Object[] elementData;

    public Stack(){
        size = 0;
        elementData = new Object[10];
    }

    public Stack(int initialCapacity){
        size = 0;
        elementData = new Object[initialCapacity];
    }

    private boolean hasMoreRoom() {
        if (size >= elementData.length)
            return false;
        else
            return true;

    }

    private E elementData(int index) {
        return (E) elementData[index];
    }

    private int lastIndexOf(Object o, int index) {
        if(index >= size)
            throw new IndexOutOfBoundsException(index + " >= " + size);

        if(o == null){
            for(int i = index; i >= 0; i--)
                if(elementData(i) == null)
                    return i;

        } else {
            for(int i = index; i >= 0; i--)
                if(o.equals(elementData(i)))
                    return i;
        }
        return -1;
    }

    private int lastIndexOf(Object o) {

        return lastIndexOf(o, size - 1);
    }

    public int size(){
        return size;
    }

    public E push(E e) {
        if(!hasMoreRoom())
            throw new OutOfMemoryError("Stack is full");

        elementData[size++] = e;
        return e;

    }

    public E peek(){
        if(empty())
            return null;

        return elementData(size - 1);
    }

    public E pop(){
        E e = peek();

        if(e == null)
            return null;

        elementData[size - 1] = null;
        size--;
        return e;

    }


    //Return 1-base position where an object is on the stack
    //topmost item is at 1
    // reture -1 if object is not on the stack
    public int search(Object o){
        int i = lastIndexOf(o);

        if(i >= 0)
            return size() - i;

        else
            return -1;
    }

    public boolean empty(){
        return size == 0;
    }

    public void showAll() {

        for (int i = 0; i < size; i++)
            System.out.print(elementData[i] == null ? "null at " + i : elementData[i].toString());

        System.out.println("");
    }

    public static void main(String[] args) {
        Stack<PhoneListing> s = new Stack<PhoneListing>(3);
        PhoneListing l;
        PhoneListing l1 = new PhoneListing("Bill", "1st Avenue", "123 4567");
        PhoneListing l2 = new PhoneListing("Al", "2nd Avenue", "456 3232");
        PhoneListing l3 = new PhoneListing("Mike", "3rd Avenue", "333 3333");
        PhoneListing l4 = new PhoneListing("Carol", "4th Avenue", "444 4444");


        // an attempt to perform a pop on an initialized (empty) stack will return null
        System.out.println("stack is empty is " + s.empty());
        System.out.println(s.pop());

        // perform three pushes to fill the stack and then output the stack
        System.out.println(s.push(l1));
        System.out.println(s.push(l2));
        System.out.println(s.push(l3));
        s.showAll();

        try {
            s.push(l4);
        } catch(OutOfMemoryError e){
            e.printStackTrace();
        }

        System.out.println("Test pop");
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
