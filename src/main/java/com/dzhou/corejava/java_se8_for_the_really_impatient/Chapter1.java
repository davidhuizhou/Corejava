package com.dzhou.corejava.java_se8_for_the_really_impatient;

import java.io.File;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.BiFunction;
import java.util.stream.Stream;

public class Chapter1 {
  public static void main(String[] args) {
    Comparator<String> cmp = (first, second)
      -> {
      if ((first.length() < second.length())) {
        return -1;
      } else if (first.length() > second.length()) {
        return 1;
      } else {
        return 0;
      }
    };

    BiFunction<String, String, Integer> com
      = (first, second) -> Integer.compare(first.length(), second.length());

    String[] strings = {"c", "a", "b"};
    Arrays.sort(strings, String::compareToIgnoreCase);
    System.out.println(strings);

    List<String> labels = Arrays.asList(strings);
    Stream<Button> stream = labels.stream().map(Button::new);
    Button[] buttons = stream.toArray(Button[]::new);

    labels.forEach(System.out::print);

    File[] hiddenFiles = new File(".").listFiles(File::isHidden);
    for(File f : hiddenFiles){
      System.out.println(f.getAbsolutePath());
    }
  }

  public static void repeatMessage(String text, int count) {
    Runnable r = () -> {
      for (int i = 0; i < count; i++) {
        System.out.println(text);
        Thread.yield();
      }
    };
    new Thread(r).start();
  }

}
