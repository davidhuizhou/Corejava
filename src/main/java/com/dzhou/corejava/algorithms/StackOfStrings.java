package com.dzhou.corejava.algorithms;

/**
 * Created by huizhou on 7/12/16.
 */
public interface StackOfStrings extends Iterable<String> {
  boolean isEmpty();

  int size();

  void push(String item);

  String pop();

  String peek();
}
