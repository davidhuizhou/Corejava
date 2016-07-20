package com.dzhou.corejava.algorithms;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by huizhou on 7/19/16.
 */
public class LinkedStack<Item> implements Stack<Item> {
  private Node<Item> first;
  private int n;

  private static class Node<Item> {
    private Item item;
    private Node<Item> next;
  }

  public LinkedStack() {
    first = null;
    n = 0;
  }

  @Override
  public boolean isEmpty() {
    return first == null;
  }

  @Override
  public int size() {
    return n;
  }

  @Override
  public void push(Item item) {
    Node oldFirst = first;
    first = new Node<Item>();
    first.item = item;
    first.next = oldFirst;
    n++;
  }

  @Override
  public Item pop() {
    if (isEmpty()) {
      throw new NoSuchElementException();
    }
    Item item = first.item;
    first = first.next;
    n--;
    return item;
  }

  @Override
  public Item peek() {
    if (isEmpty()) {
      throw new NoSuchElementException();
    }
    return first.item;
  }

  @Override
  public Iterator<Item> iterator(){
    return new ListIterator<Item>(first);
  }

  private class ListIterator<Item> implements Iterator<Item> {
    private Node<Item> current;

    public ListIterator(Node<Item> first) {
      current = first;
    }

    @Override
    public boolean hasNext() {
      return current != null;
    }

    @Override
    public Item next() {
      if (!hasNext()) {
        throw new NoSuchElementException();
      }
      Item item = current.item;
      current = current.next;
      return item;
    }

    @Override
    public void remove() {
      throw new UnsupportedOperationException();
    }
  }
}
