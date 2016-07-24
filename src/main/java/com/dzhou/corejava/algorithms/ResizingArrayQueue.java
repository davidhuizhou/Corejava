package com.dzhou.corejava.algorithms;

import com.dzhou.corejava.guava.common.base.AbstractIterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by huizhou on 7/23/16.
 */
public class ResizingArrayQueue<Item> implements Queue<Item> {
  private Item[] q;
  private int n;
  private int first;
  private int last;

  public ResizingArrayQueue() {
    q = (Item[]) new Object[2];
    n = 0;
    first = 0;
    last = 0;
  }

  @Override
  public boolean isEmpty() {
    return n == 0;
  }

  @Override
  public int size() {
    return n;
  }

  private void resize(int capacity) {
    assert capacity >= n;
    Item[] temp = (Item[]) new Object[capacity];
    for (int i = 0; i < n; i++) {
      temp[i] = q[(first + i) % q.length];
    }
    first = 0;
    last = n;
    q = temp;
  }

  @Override
  public void enqueue(Item item) {
    if (n == q.length) {
      resize(2 * q.length);
    }
    q[last++] = item;
    if (last == q.length) {
      last = 0;
    }
    n++;
  }

  @Override
  public Item dequeue() {
    if (isEmpty()) {
      throw new NoSuchElementException();
    }
    Item item = q[first];
    q[first] = null;
    first++;
    if (first == q.length) {
      first = 0;
    }
    n--;
    if (n > 0 && n == q.length / 4) {
      resize(q.length / 2);
    }
    return item;

  }

  @Override
  public Item peek() {
    if (isEmpty()) {
      throw new NoSuchElementException();
    }
    return q[first];
  }

  @Override
  public Iterator iterator() {
    return new ArrayIterator();
  }

  private class ArrayIterator extends AbstractIterator<Item> {
    int i = 0;

    protected ArrayIterator() {

    }

    @Override
    protected Item computeNext() {
      if (i < n) {
        Item item = q[(first + i) % q.length];
        i++;
        return item;
      }
      return endOfData();
    }
  }

}
