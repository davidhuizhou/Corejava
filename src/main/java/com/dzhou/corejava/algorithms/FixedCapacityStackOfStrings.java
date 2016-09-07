package com.dzhou.corejava.algorithms;

import com.dzhou.corejava.guava.common.base.AbstractIterator;
import com.dzhou.corejava.guava.common.base.Joiner;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by huizhou on 7/10/16.
 */
public class FixedCapacityStackOfStrings implements Stack<String> {
  private String[] a;
  private int N;

  public FixedCapacityStackOfStrings(final int capacity) {
    a = new String[capacity];
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
  public void push(final String item) {
    a[N++] = item;
  }

  @Override
  public String pop() {
    if (isEmpty()) {
      throw new NoSuchElementException();
    }

    String item = a[N - 1];
    a[N - 1] = null;
    N--;
    return item;
  }

  @Override
  public String peek() {
    if (isEmpty()) {
      throw new NoSuchElementException();
    }
    return a[N - 1];
  }

  @Override
  public Iterator<String> iterator() {
    return new ReverseIterator(this.N);
  }


  private class ReverseIterator extends AbstractIterator<String> {
    int last;

    protected ReverseIterator(int n) {
      this.last = n - 1;
    }

    @Override
    protected String computeNext() {
      if (last >= 0) {
        return a[last--];
      }
      return endOfData();
    }

  }


  public static void main(String[] args) {
    Stack<String> stack = new FixedCapacityStackOfStrings(10);
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
