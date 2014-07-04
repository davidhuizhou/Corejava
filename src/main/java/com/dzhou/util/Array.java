package com.dzhou.util;

import com.dzhou.corejava.dsaj.ch2.PhoneListing;


/**
 * Created by davidzhou on 7/3/14.
 */
public class Array<E> {
    private int size;
    private Object[] elementData;

    public Array() {
        size = 0;
        elementData = new Object[10];
    }

    public Array(int initialCapacity) {
        size = 0;
        elementData = new Object[initialCapacity];
    }

    private void rangeCheckForAdd(int index) {
        if (index < 0 || index > size)
            throw new ArrayIndexOutOfBoundsException("Index is out of the boundary 0 to " + size);
    }

    private boolean hasMoreRoom() {
        if (size >= elementData.length)
            return false;
        else
            return true;

    }

    private void rangeCheck(int index){
        if(index < 0 || index >= size)
            throw new ArrayIndexOutOfBoundsException("Index is out of the boundary 0 to " + (size - 1));
    }

    E elementData(int index) {
        return (E) elementData[index];
    }

    //Add e to the end of array
    public boolean add(E e) {
        if (!hasMoreRoom())
            return false;
        elementData[size++] = e;
        return true;
    }

    public void add(int index, E e) {
        if (!hasMoreRoom())
            throw new ArrayIsFullError("Array is full");

        rangeCheckForAdd(index);

        //shift the array to right
        for (int i = size; i > index; i--)
            elementData[i] = elementData[i - 1];

        elementData[index] = e;
        size++;


    }

    public E get(int index){
        rangeCheck(index);

        return elementData(index);

    }

    public int indexOf(Object o) {
        if (o == null) {
            for (int i = 0; i < size; i++) {
                if (elementData[i] == null)
                    return i;
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (o.equals(elementData[i]))
                    return i;

            }

        }
        return -1;
    }

    public E remove(int index) {
        rangeCheck(index);

        E e = elementData(index);

        for (int i = index; i < size ; i++)
            elementData[i] = elementData[i + 1];

        size--;
        return e;

    }


    public E set(int index, E element) {
        rangeCheck(index);

        E oldValue = elementData(index);
        elementData[index] = element;
        return oldValue;
    }

    public void showAll() {

        for (int i = 0; i < size; i++)
            System.out.print(elementData[i] == null ? "Array[" + i + "] == null ": elementData[i].toString());

        System.out.println("");
    }

    public static void main(String[] args) {
        Array<PhoneListing> NYC = new Array<PhoneListing>(10);

        PhoneListing bob = new PhoneListing("Bob", "23 1st Avenue",
                "133-4573");
        PhoneListing roy = new PhoneListing("Roy", "421 east 24th Street",
                "897-2232");

        PhoneListing david = new PhoneListing("David", "421 east 24th Street",
                "897-2232");

        PhoneListing stan = new PhoneListing("Stan", "421 east 24th Street",
                "897-2232");

        PhoneListing michael = new PhoneListing("Michael", "421 east 24th Street",
                "897-2232");

        NYC.add(bob);
        NYC.add(roy);
        NYC.add(1, david);
        NYC.add(2, stan);

        System.out.println("Should print  bob, david, stan, roy");
        NYC.showAll();

        System.out.println("Show print 0 - bob, 2 - stan");
        int index1 = NYC.indexOf(bob);
        PhoneListing l1 = NYC.get(index1);

        int index2 = NYC.indexOf(stan);
        PhoneListing l2 = NYC.get(index2);

        System.out.println(l1);
        System.out.println(l2);

        System.out.println("Call remove 1");
        PhoneListing l3 = NYC.remove(1);
        System.out.println(l3);

        System.out.println("Should print  bob, stan, roy");
        NYC.showAll();

        System.out.println("Should print  bob, stan, michael");
        NYC.set(2, michael);
        NYC.showAll();



    }

}
