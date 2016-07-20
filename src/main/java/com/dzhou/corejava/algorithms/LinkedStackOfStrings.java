package com.dzhou.corejava.algorithms;

import com.dzhou.corejava.guava.common.base.AbstractIterator;
import com.dzhou.corejava.guava.common.base.Joiner;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by huizhou on 7/10/16.
 */
public class LinkedStackOfStrings implements StackOfStrings {
  private Node first;
  private int N;

  private static class Node {
    private String item;
    private Node next;
  }

  public LinkedStackOfStrings() {
    first = null;
    N = 0;
  }

  @Override
  public boolean isEmpty() {
    return first == null;
  }

  @Override
  public int size() {
    return N;
  }

  @Override
  public void push(String item) {
    Node oldFirst = first;
    first = new Node();
    first.item = item;
    first.next = oldFirst;
    N++;
  }

  @Override
  public String pop() {
    if (isEmpty()) {
      throw new NoSuchElementException();
    }
    String item = first.item;
    first = first.next;
    N--;
    return item;
  }

  @Override
  public String peek() {
    if (isEmpty()) {
      throw new NoSuchElementException();
    }
    return first.item;
  }

  @Override
  public Iterator<String> iterator() {
    return new StackIterator();
  }

  private class StackIterator extends AbstractIterator<String> {
    Node n = first;

    @Override
    protected String computeNext() {
      if (n != null) {
        String result = n.item;
        n = n.next;
        return result;
      }
      return endOfData();
    }

  }

  public static void main(String[] args) {
    StackOfStrings stack = new LinkedStackOfStrings();
    stack.push("a");
    stack.push("b");
    stack.pop();
    stack.push("c");
    stack.push("d");
    stack.peek();
    stack.pop();
    stack.push("e");

    System.out.println(Joiner.on(",").join(stack));

  }
}
