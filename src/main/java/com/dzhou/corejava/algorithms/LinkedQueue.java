package com.dzhou.corejava.algorithms;

import com.dzhou.corejava.guava.common.base.AbstractIterator;
import com.dzhou.corejava.guava.common.base.Joiner;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by huizhou on 7/24/16.
 */
public class LinkedQueue<Item> implements Queue<Item> {
  private Node<Item> first;
  private Node<Item> last;
  private int n;

  public LinkedQueue() {
    first = null;
    last = null;
    n = 0;
  }

  private static class Node<Item> {
    Item item;
    Node<Item> next;
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
  public void enqueue(Item item) {
    Node oldLast = last;
    last = new Node<Item>();
    last.item = item;
    last.next = null;
    if (isEmpty()) {
      first = last;
    } else {
      oldLast.next = last;
    }
    n++;
  }

  @Override
  public Item peek() {
    if (isEmpty()) {
      throw new NoSuchElementException();
    }
    return first.item;
  }

  @Override
  public Item dequeue() {
    if (isEmpty()) {
      throw new NoSuchElementException();
    }
    Item item = first.item;
    first = first.next;
    if (isEmpty()) {
      last = null;
    }
    n--;
    return item;
  }

  @Override
  public Iterator iterator() {
    return new ListIterator(first);
  }

  private class ListIterator extends AbstractIterator<Item> {
    Node<Item> current;

    protected ListIterator(Node<Item> first) {
      current = first;
    }

    @Override
    public Item computeNext() {
      if (current != null) {
        Item item = current.item;
        current = current.next;
        return item;
      }
      return endOfData();
    }
  }

  public static void main(String[] args) {
    Queue<String> queue = new LinkedQueue<String>();
    queue.enqueue("a");
    System.out.println(Joiner.on(",").join(queue));
    queue.enqueue("b");
    System.out.println(Joiner.on(",").join(queue));
    queue.dequeue();
    System.out.println(Joiner.on(",").join(queue));
    queue.enqueue("c");
    System.out.println(Joiner.on(",").join(queue));
    queue.enqueue("d");
    System.out.println(Joiner.on(",").join(queue));
    queue.peek();
    System.out.println(Joiner.on(",").join(queue));
    queue.dequeue();
    System.out.println(Joiner.on(",").join(queue));
    queue.enqueue("e");
    System.out.println(Joiner.on(",").join(queue));

  }
}
