package com.dzhou.corejava.head_first_java;

/**
 * Created by huizhou on 6/3/17.
 */
public class SimpleDotComTestDrive {
  public static void main(String[] args) {
    SimpleDotCom dot = new SimpleDotCom();
    int[] locations = {2, 3, 4};
    dot.setLocationCells(locations);
    String userGuess = "2";
    String result = dot.checkYourself(userGuess);
  }
}
