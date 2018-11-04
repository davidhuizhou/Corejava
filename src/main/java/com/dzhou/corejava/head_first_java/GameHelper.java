package com.dzhou.corejava.head_first_java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by huizhou on 6/3/17.
 */
public class GameHelper {
  public String getUserInput(String prompt) {
    String inputLine = null;
    System.out.print(prompt + " ");
    try {
      BufferedReader is = new BufferedReader(
          new InputStreamReader(System.in));

      inputLine = is.readLine();

    } catch (IOException e) {
      System.out.println("IOException: " + e);
    }
    return inputLine;
  }
}
