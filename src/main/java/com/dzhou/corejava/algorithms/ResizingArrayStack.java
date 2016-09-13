package com.dzhou.corejava.algorithms;

import com.dzhou.corejava.guava.common.base.AbstractIterator;
import com.dzhou.corejava.guava.common.base.Joiner;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by huizhou on 7/19/16.
 */
public class ResizingArrayStack<Item> implements Stack<Item> {
  private Item[] a;
  private int n;

  public ResizingArrayStack() {
    a = (Item[]) new Object[2];
    n = 0;
  }

  @Override
  public boolean isEmpty() {
    return n == 0;
  }

  @Override
  public int size() {
    return n;
  }

  @Override
  public void push(Item item) {
    if (n == a.length) {
      resize(2 * a.length);
    }
    a[n++] = item;
  }

  @Override
  public Item pop() {
    if (isEmpty()) {
      throw new NoSuchElementException();
    }
    Item item = a[n - 1];
    a[n - 1] = null;
    n--;
    if (n > 0 && n == a.length / 4) {
      resize(a.length / 2);
    }
    return item;

  }

  @Override
  public Item peek() {
    if (isEmpty()) {
      throw new NoSuchElementException();
    }
    return a[n - 1];
  }

  private void resize(int capacity) {
    assert capacity >= n;

    Item[] temp = (Item[]) new Object[capacity];
    for (int i = 0; i < n; i++) {
      temp[i] = a[i];
    }
    a = temp;
  }

  @Override
  public Iterator<Item> iterator() {
    return new ReverseArrayIterator(n);
  }

  private class ReverseArrayIterator extends AbstractIterator<Item> {
    private int i;

    public ReverseArrayIterator(int n) {
      i = n - 1;
    }

    @Override
    public Item computeNext() {
      if (i >= 0) {
        return a[i--];
      }
      return endOfData();
    }
  }

  public static void main(String[] args) {
    Stack<String> stack = new ResizingArrayStack<String>();
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
