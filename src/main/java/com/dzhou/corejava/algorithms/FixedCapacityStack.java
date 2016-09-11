package com.dzhou.corejava.algorithms;

import com.dzhou.corejava.guava.common.base.AbstractIterator;
import com.dzhou.corejava.guava.common.base.Joiner;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by huizhou on 9/10/16.
 */
public class FixedCapacityStack<Item> implements Stack<Item> {
  private Item[] a;
  private int N;

  public FixedCapacityStack(int capacity) {
    a = (Item[]) new Object[capacity];
    N = 0;
  }

  @Override
  public boolean isEmpty() {
    return N == 0;
  }

  @Override
  public int size() {
    return N;
  }

  @Override
  public void push(Item item) {
    a[N++] = item;
  }

  @Override
  public Item pop() {
    if (isEmpty()) {
      throw new NoSuchElementException();
    }
    Item item = a[N - 1];
    a[N - 1] = null;
    N--;
    return item;
  }

  @Override
  public Item peek() {
    if (isEmpty()) {
      throw new NoSuchElementException();
    }
    return a[N - 1];
  }

  @Override
  public Iterator<Item> iterator() {
    return new ReverseArrayIterator(N);
  }

  public class ReverseArrayIterator extends AbstractIterator<Item> {
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
    Stack<String> stack = new FixedCapacityStack(10);
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
