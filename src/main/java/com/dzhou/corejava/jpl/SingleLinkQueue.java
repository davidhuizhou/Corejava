package com.dzhou.corejava.jpl;

/**
 * Created by davidzhou on 10/31/14.
 */
class SingleLinkQueue<E> {
    static class Cell<E> {
        private Cell<E> next;
        private E element;

        public Cell(E element) {
            this.element = element;
        }

        public Cell(E element, Cell<E> next) {
            this.element = element;
            this.next = next;
        }

        public E getElement() {
            return element;
        }

        public void setElement(E element) {
            this.element = element;
        }

        public Cell<E> getNext() {
            return next;
        }

        public void setNext(Cell<E> next) {
            this.next = next;
        }
    }

    //    class Cell {
    //        private Cell next;
    //        private E element;
    //
    //        public Cell(E element) {
    //            this.element = element;
    //        }
    //
    //        public Cell(E element, Cell next) {
    //            this.element = element;
    //            this.next = next;
    //        }
    //
    //        public E getElement() {
    //            return element;
    //        }
    //
    //        public void setElement(E element) {
    //            this.element = element;
    //        }
    //
    //        public Cell getNext() {
    //            return next;
    //        }
    //
    //        public void setNext(Cell next) {
    //            this.next = next;
    //        }
    //    }

    protected Cell head;
    protected Cell tail;

    public void add(E item) {
        Cell<E> cell = new Cell<E>(item);
        if (tail == null) {
            head = tail = cell;
        } else {
            tail.setNext(cell);
            tail = cell;
        }
    }

    public E remove() {
        if (head == null) {
            return null;
        }
        Cell<E> cell = head;
        head = head.getNext();
        if (head == null) {
            tail = null;  // empty queue
        }
        return cell.getElement();
    }

    public <T> T[] toArray(T[] arr) {
        Object[] tmp = arr;
        int i = 0;
        for (Cell<E> c = head; c != null && i < arr.length; c = c.getNext())
            tmp[i++] = c.getElement();
        return arr;
    }

    public E[] toArray_v1(E[] arr) { // too restrictive!
        int i = 0;
        for (Cell<E> c = head;
             c != null && i < arr.length;
             c = c.getNext())
            arr[i++] = c.getElement();
        return arr;
    }
}