package com.dzhou.corejava.java8_in_action;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class Chapter1 {

  public static void main(String[] args) {
    testListFiles();

    List<Apple> inventory = Arrays.asList(new Apple("green"),
      new Apple("red"));
    List<Apple> apples = filterApples(inventory, Apple::isGreenApple);
    System.out.println(apples.size());

    apples = filterApples(inventory,
      (Apple a) -> "red".equals(a.getColor()));
    System.out.println(apples.size());

    apples = filterApples(inventory,
      (Apple a) -> "red".equals(a.getColor()) || "green".equals(a.getColor()));
    System.out.println(apples.size());


  }

  private static void testListFiles() {
    File[] hiddenFiles = new File(".").listFiles(File::isHidden);
    for (File f : hiddenFiles) {
      System.out.println(f.getAbsolutePath());
    }
  }


  private static List<Apple> filterApples(List<Apple> inventory,
                                          Predicate<Apple> p) {
    List<Apple> result = new ArrayList<>();
    for (Apple apple : inventory) {
      if (p.test(apple)) {
        result.add(apple);
      }
    }
    return result;
  }

  private static class Apple {
    private String color;

    Apple(String color) {
      this.color = color;
    }

    String getColor() {
      return this.color;
    }

    private static boolean isGreenApple(Apple apple) {
      return "green".equals(apple.getColor());
    }

  }
}
