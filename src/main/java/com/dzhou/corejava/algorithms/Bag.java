package com.dzhou.corejava.algorithms;

/**
 * Created by huizhou on 7/19/16.
 */
public interface Bag<Item> extends Iterable<Item> {
  void add(Item item);

  boolean isEmpty();

  int size();
}
