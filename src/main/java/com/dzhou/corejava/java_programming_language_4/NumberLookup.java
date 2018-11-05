package com.dzhou.corejava.java_programming_language_4;

public class NumberLookup<T> implements Lookup<T> {
  private String[] names;
  private T[] values;

  public NumberLookup(String[] names, T[] values) {
    this.names = names;
    this.values = values;
  }

  public T find(String name) {
    for (int i = 0; i < names.length; i++) {
      if (names[i].equals(name)) {
        return values[i];
      }
    }
    return null;
  }

  public static void processValues(String[] names, Lookup<? extends Number> table) {
    for (int i = 0; i < names.length; i++) {
      Object value = table.find(names[i]);
      if (value != null) {
        System.out.println("Call processValue of " + value);
      }
    }
  }

  public static void main(String[] args) {

    String[] names = new String[]{"One", "Two", "Three"};
    Integer[] values = new Integer[]{1, 2, 3};

    Lookup<Integer> lookup = new NumberLookup(names, values);
    System.out.printf("One is %d %n", lookup.find("One"));
    System.out.printf("Four is %d %n", lookup.find("Four"));
    processValues(names, lookup);

    Double[] doubleValues = new Double[]{1d, 2d, 3d};
    Lookup<Double> doubleLookup = new NumberLookup(names, doubleValues);
    System.out.printf("One is %f %n", doubleLookup.find("One"));
    System.out.printf("Four is %f %n", doubleLookup.find("Four"));
    processValues(names, doubleLookup);


  }
}
