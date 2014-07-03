package com.dzhou;

/**
 * Created by davidzhou on 7/3/14.
 */
public class ArrayQueue <E> {
    private E[] elementData;
    private int head;
    private int tail;

    public ArrayQueue(){
        head = 0;
        tail = 0;
        elementData = (E[]) new Object[10];
    }

    public ArrayQueue(int initialCapacity){
        head = 0;
        tail = 0;
        elementData = (E[]) new Object[initialCapacity];
    }

    

    public boolean add(E e) {

    }

}
