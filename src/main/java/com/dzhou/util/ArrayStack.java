package com.dzhou.util;

import com.dzhou.corejava.dsaj.ch2.PhoneListing;

/**
 * Created by davidzhou on 7/3/14.
 */
public class ArrayStack<E> {

    /**
     * Default initial capacity.
     */
    private static final int DEFAULT_CAPACITY = 3;

    private int size;
    private Object[] elementData;

    public ArrayStack() {
        size = 0;
        elementData = new Object[DEFAULT_CAPACITY];
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

    private void resize(int max){
        Object[] temp = new Object[max];
        for(int i = 0; i < size; i++)
            temp[i] = elementData[i];
        elementData = temp;
    }

    private void ensureCapacityInternal(int max){
        if(max == elementData.length)
            resize(2 * elementData.length);
        else if (max > DEFAULT_CAPACITY && max == elementData.length/4)
            resize(elementData.length/2);
    }

    public int size() {
        return size;
    }


    public boolean empty() {
        return size == 0;
    }

    public E push(E e) {
        ensureCapacityInternal(size+1);

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

        ensureCapacityInternal(size);

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
        ArrayStack<PhoneListing> s = new ArrayStack<PhoneListing>();
        PhoneListing l;
        PhoneListing l1 = new PhoneListing("Bill", "1st Avenue", "123 4567");
        PhoneListing l2 = new PhoneListing("Al", "2nd Avenue", "456 3232");
        PhoneListing l3 = new PhoneListing("Mike", "3rd Avenue", "333 3333");
        PhoneListing l4 = new PhoneListing("Carol", "4th Avenue", "444 4444");
        PhoneListing l5 = new PhoneListing("David", "1st Avenue", "123 4567");
        PhoneListing l6 = new PhoneListing("Rachel", "2nd Avenue", "456 3232");
        PhoneListing l7 = new PhoneListing("Mom", "3rd Avenue", "333 3333");
        PhoneListing l8 = new PhoneListing("Stan", "4th Avenue", "444 4444");


        // an attempt to perform a pop on an initialized (empty) stack will return null
        System.out.println("stack is empty is " + s.empty());
        System.out.println(s.pop());

        // perform three pushes to fill the stack and then output the stack
        System.out.println(s.push(l1));
        System.out.println(s.push(l2));
        System.out.println(s.push(l3));
        System.out.println(s.push(l4));

        System.out.println(s.push(l5));
        System.out.println(s.push(l6));
        System.out.println(s.push(l7));
        System.out.println(s.push(l8));

        s.showAll();

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
