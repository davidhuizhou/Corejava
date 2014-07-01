package com.dzhou.corejava.dsaj.ch4;

/**
 * Created by davidzhou on 7/1/14.
 */
public class Iterator <T> {
    private Node<T> h;
    private Node<T> ip;

    public Iterator(){
        h = null;
        ip = null;
    }

    public Iterator(Node<T> h){
        this.h = h;
        ip = h;
    }

    public boolean hasNext(){
        if (ip.next != null)
            return true;
        else
            return false;
    }

    public T next(){
        if(ip.next != null){
            ip = ip.next;
            KeyMode k = (KeyMode)ip.l;
            return (T)k.deepCopy();
        } else {
            return null;
        }
    }

    public void set(T newNode){
        KeyMode k = ((KeyMode)newNode).deepCopy();
        T n = (T)k;
        ip.l = n;

    }

}
