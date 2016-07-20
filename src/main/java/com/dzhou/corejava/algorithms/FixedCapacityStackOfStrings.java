package com.dzhou.corejava.algorithms;

import com.dzhou.corejava.guava.common.base.AbstractIterator;
import com.dzhou.corejava.guava.common.base.Joiner;

import java.util.Iterator;

/**
 * Created by huizhou on 7/10/16.
 */
public class FixedCapacityStackOfStrings implements StackOfStrings {
  private String[] a;
  private int N;

  public FixedCapacityStackOfStrings(int capacity) {
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
  public void push(String item) {
    a[N++] = item;
  }

  @Override
  public String pop() {
    return a[--N];
  }

  @Override
  public String peek() {
    return a[N - 1];
  }

  @Override
  public Iterator<String> iterator() {
    return new ReverseIterator();
  }

  private class ReverseIterator extends AbstractIterator<String> {
    int i = N - 1;

    @Override
    protected String computeNext() {
      if (i >= 0) {
        return a[i--];
      }
      return endOfData();
    }
  }

  public static void main(String[] args) {
    StackOfStrings stack = new FixedCapacityStackOfStrings(10);
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
