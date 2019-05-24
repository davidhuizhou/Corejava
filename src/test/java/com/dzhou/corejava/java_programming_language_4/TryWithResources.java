package com.dzhou.corejava.java_programming_language_4;

public class TryWithResources {
  public static void main(String[] args) {
    try (One one = new One(); Two two = new Two()) {
      System.out.println("try");
      throw new Exception("try exception");
    } catch (Exception e) {
      System.err.println(e.getMessage());

      for (Throwable t : e.getSuppressed()) {
        System.err.println("supressed: " + t);
      }
    } finally {
      System.out.println("finally");
    }
  }


  private static class One implements AutoCloseable {
    public void close() throws Exception {
      System.out.println("Close - One");
      throw new Exception("Close - One exception");
    }
  }

  private static class Two implements AutoCloseable {
    public void close() throws Exception {
      System.out.println("Close - Two");
      throw new Exception("Close - Two exception");
    }
  }

}
