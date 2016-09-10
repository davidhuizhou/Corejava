package com.dzhou.corejava.algorithms;

/**
 * Created by huizhou on 7/23/16.
 */
public interface Queue<Item> extends Iterable<Item> {
  boolean isEmpty();

  int size();

  void enqueue(Item item);

  Item dequeue();

  Item peek();
}
