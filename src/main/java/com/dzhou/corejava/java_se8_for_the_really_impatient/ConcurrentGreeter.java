package com.dzhou.corejava.java_se8_for_the_really_impatient;

class ConcurrentGreeter extends Greeter {
  public void greet() {
    Thread t = new Thread(super::greet);
    t.start();
  }

  public static void main(String[] args){
    new ConcurrentGreeter().greet();
  }
}
