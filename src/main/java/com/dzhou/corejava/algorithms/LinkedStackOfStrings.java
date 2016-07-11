package com.dzhou.corejava.algorithms;

/**
 * Created by huizhou on 7/10/16.
 */
public class LinkedStackOfStrings {
  private Node first = null;

  private class Node {
    String item;
    Node next;
  }

  public boolean isEmpty() {
    return first == null;
  }

  public void push(String item) {
    Node oldFirst = first;
    first = new Node();
    first.item = item;
    first.next = oldFirst;
  }

  public String pop() {
    if (isEmpty())
      return null;
    String item = first.item;
    first = first.next;
    return item;

  }


}
