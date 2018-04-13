package com.dzhou.corejava.elements_of_programming_interviews;

import java.util.Deque;
import java.util.LinkedList;

public class StackQueue {



  public static class Stack {

    private static class ElementWithCachedMax {
      public Integer element;
      public Integer max;

      public ElementWithCachedMax(Integer element, Integer max) {
        this.element = element;
        this.max = max;
      }
    }

    // Stores (element, cached maximum) pair
    private Deque<ElementWithCachedMax> elementWithCachedMax = new LinkedList<>();

    public boolean isEmpty() {
      return elementWithCachedMax.isEmpty();
    }

    public Integer max() {
      if (isEmpty())
        throw new IllegalStateException("max(): empty stack");

      return elementWithCachedMax.peek().max;

    }

    public Integer pop() {
      if (isEmpty())
        throw new IllegalStateException("pop(): empty statck");

      return elementWithCachedMax.removeFirst().element;

    }

    public void push(Integer x) {
      elementWithCachedMax
          .addFirst(new ElementWithCachedMax(x, Math.max(x, isEmpty() ? x : max())));
    }


  }


}


