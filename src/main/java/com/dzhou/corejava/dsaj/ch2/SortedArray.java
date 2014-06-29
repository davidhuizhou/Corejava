package com.dzhou.corejava.dsaj.ch2;

/**
 * Created by huizhou on 6/28/14.
 */
public class SortedArray <T> {
    private int next;
    private int size;
    private T[] data;

    public SortedArray(){
        next = 0;
        size = 100;
        data = (T[]) new Object[100];
    }

    public SortedArray (int s){
        next = 0;
        size = s;
        data = (T[]) new Object[s];
    }

    public boolean insert(T newNode){
        KeyMode node = (KeyMode)newNode;
        if(next >= size)
            return false;

        int low = 0;
        int high = next - 1;
        int i;

        if(next == 0) {
            i = 0;
        } else if(node.compareTo(data[0]) <= 0){
            i = 0;
        } else if (node.compareTo(data[high]) >= 0){
            i = high + 1;
        } else {
            i = (low + high) / 2;

            while (!( node.compareTo(data[i]) <= 0 && node.compareTo(data[i - 1]) >= 0)) {
                if (node.compareTo(data[i]) < 0){
                    high = i - 1;
                } else {
                    low = i + 1;
                }
                i = (low + high) / 2;
            }

        }

        for(int j = next; j > i; j--) {
            data[j] = data[j - 1];
        }
        data[i] = (T)node.deepCopy();
        next++;
        return true;

    }

    public KeyMode fetch(Object targetKey){
        KeyMode node = (KeyMode) data[0];

        T temp;
        int i = 0;
        while (i < next && node.compareTo(targetKey) != 0)
        {
            i++;
            node = (KeyMode) data[i];
        }
        if(i == next)
            return null;

        node = node.deepCopy();

        if(i != 0){
            temp = data[i - 1];
            data[i - 1] = data[i];
            data[i] = temp;
        }
        return node;

    }

    public boolean delete(Object targetKey) {// access the node using a sequential search
        int i = 0;
        while (i < next && !(((KeyMode)data[i]).compareTo(targetKey) == 0)) {
            i++;
        }
        if (i == next) // node not found
            return false;
        //move the last node into the deleted node's position
        data[i] = data[next - 1];
        data[next - 1] = null;
        next = next - 1;
        return true; // node found and deleted
    }//e

    public boolean update(Object targetKey, T newNode) {
        if (delete(targetKey) == false)  // node not in the structure
            return false;
        else if (insert(newNode) == false)  // insufficient memory
            return false;
        else
            return true;  // node found and updated
    }// end of update method

    public void showAll() {
        for (int i = 0; i < next; i++)
            System.out.println(data[i].toString());
    }// end showAll method

}
