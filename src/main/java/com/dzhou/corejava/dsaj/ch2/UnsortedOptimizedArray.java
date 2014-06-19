package com.dzhou.corejava.dsaj.ch2;

/**
 * Created by davidzhou on 6/19/14.
 */
public class UnsortedOptimizedArray {
    private int next;
    private int size;
    private Listing[] data;

    public UnsortedOptimizedArray(){
        next = 0;
        size = 100;
        data = new Listing[size];
    }

    public boolean insert(Listing newNode){
        if(next >= size)
            return false;

        data[next] = newNode.deepCopy();

        if(data[next] == null)  //insufficient system memory
            return false;

        ++next;
        return true;
    }

    public Listing fetch(String targetKey){
        Listing node;
        Listing temp;

        int i = 0;
        while (i < next && !(data[i].compareTo(targetKey) == 0))
            i++;

        if(i == next)  // node not found
            return null;

        node = data[i].deepCopy();

        //move the node up one position in the array. unless it is the first one
        if(i != 0){
            temp = data[i -1];
            data[i - 1] = data[i];
            data[i] = temp;
        }
        return node;
    }

    public boolean delete(String targetKey){
        int i = 0;
        while( i < next && !(data[i].compareTo(targetKey) == 0))
            i++;

        if(i == next)
            return false;

        //move the last node into the deleted node's position
        data[i] = data[next - 1];
        data[next -1] = null;
        next--;
        return true;
    }

    public boolean update(String targetKey, Listing newNode){
        if(delete(targetKey) == false)
            return false;

        else if(insert(newNode) == false)
            return false;

        else
            return true;
    }
}
