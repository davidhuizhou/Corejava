package com.dzhou.corejava.algorithms;

/**
 * Created by huizhou on 9/6/14.
 */
public interface Stack<Item> extends Iterable<Item> {
  boolean isEmpty();

  int size();

  void push(Item item);

  Item pop();

  Item peek();
}

