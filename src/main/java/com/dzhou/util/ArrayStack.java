package com.dzhou.util;

import com.dzhou.corejava.dsaj.ch2.PhoneListing;

/**
 * Created by davidzhou on 7/3/14.
 */
public class ArrayStack<E> {
    private int size;
    private Object[] elementData;

    public ArrayStack() {
        size = 0;
        elementData = new Object[10];
    }

    public ArrayStack(int initialCapacity) {
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

    //Last index in the elementData array which equals to 0 starting backwards from index
    private int lastIndexOf(Object o, int index) {
        if (index >= size)
            throw new IndexOutOfBoundsException(index + " >= " + size);

        if (o == null) {
            for (int i = index; i >= 0; i--)
                if (elementData(i) == null)
                    return i;

        } else {
            for (int i = index; i >= 0; i--)
                if (o.equals(elementData(i)))
                    return i;
        }
        return -1;
    }

    //last index on the elementData array which equals to object o
    private int lastIndexOf(Object o) {

        return lastIndexOf(o, size - 1);
    }

    public int size() {
        return size;
    }


    public boolean empty() {
        return size == 0;
    }

    public E push(E e) {
        if (!hasMoreRoom())
            throw new ArrayIsFullError("Stack is full");

        elementData[size++] = e;
        return e;

    }

    public E peek() {
        if (empty())
            return null;

        return elementData(size - 1);
    }

    public E pop() {
        E e = peek();

        if (!empty())
            elementData[--size] = null;

        return e;

    }


    //Return 1-base position where an object is on the stack
    //topmost item is at 1
    // reture -1 if object is not on the stack
    public int search(Object o) {
        int i = lastIndexOf(o);

        if (i >= 0)
            return size() - i;

        else
            return -1;
    }


    public void showAll() {

        for (int i = 0; i < size; i++)
            System.out.print(elementData[i] == null ? "ArrayStack[" + i + "] == null " : elementData[i].toString());

        System.out.println("");
    }

    public static void main(String[] args) {
        ArrayStack<PhoneListing> s = new ArrayStack<PhoneListing>(3);
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
        } catch (ArrayIsFullError e) {
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
