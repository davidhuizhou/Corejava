package com.dzhou.corejava.algorithms;

import com.dzhou.corejava.guava.common.base.AbstractIterator;
import com.dzhou.corejava.guava.common.base.Joiner;

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
    Node<Item> oldFirst = first;
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
  public Iterator<Item> iterator() {
    return new ListIterator(first);
  }


  private class ListIterator extends AbstractIterator<Item> {
    private Node<Item> node;

    public ListIterator(Node first) {
      node = first;
    }

    @Override
    public Item computeNext() {
      if (node != null) {
        Item item = node.item;
        node = node.next;
        return item;
      }
      return endOfData();
    }
  }

  public static void main(String[] args) {
    Stack<String> stack = new LinkedStack<String>();
    stack.push("a");
    System.out.println(Joiner.on(",").join(stack));
    stack.push("b");
    System.out.println(Joiner.on(",").join(stack));
    stack.pop();
    System.out.println(Joiner.on(",").join(stack));
    stack.push("c");
    System.out.println(Joiner.on(",").join(stack));
    stack.push("d");
    System.out.println(Joiner.on(",").join(stack));
    stack.peek();
    System.out.println(Joiner.on(",").join(stack));
    stack.pop();
    System.out.println(Joiner.on(",").join(stack));
    stack.push("e");
    System.out.println(Joiner.on(",").join(stack));

  }
}
