package com.dzhou.corejava.pie.ch4;

/**
 * Created by huizhou on 6/30/14.
 */
public class ListElement<T> {

    private T data;
    private ListElement<T> next;

    public void setData(T data) {
        this.data = data;
    }

    public T getData() {
        return this.data;
    }

    public void setNext(ListElement<T> next) {
        this.next = next;
    }

    public ListElement<T> getNext() {
        return this.next;
    }


}
