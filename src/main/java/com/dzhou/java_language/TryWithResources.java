package com.dzhou.java_language;

/**
 * https://www.baeldung.com/java-try-with-resources
 *
 * OCP Java SE 8 Programmer II Exam Guide - Chapter 3.
 */
public class TryWithResources {
  public static void main(String[] args) throws Exception {

    orderOfClosingResources();

    supressExceptions();
  }

  private static void orderOfClosingResources() throws Exception {
    try (AutoCloseableResourcesFirst af = new AutoCloseableResourcesFirst();
         AutoCloseableResourcesSecond as = new AutoCloseableResourcesSecond()) {

      af.doSomething();
      as.doSomething();
    }
  }

  private static void supressExceptions() {
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

  public static class AutoCloseableResourcesFirst implements AutoCloseable {

    public AutoCloseableResourcesFirst() {
      System.out.println("Constructor -> AutoCloseableResources_First");
    }

    public void doSomething() {
      System.out.println("Something -> AutoCloseableResources_First");
    }

    @Override
    public void close() throws Exception {
      System.out.println("Closed AutoCloseableResources_First");
    }
  }

  public static class AutoCloseableResourcesSecond implements AutoCloseable {

    public AutoCloseableResourcesSecond() {
      System.out.println("Constructor -> AutoCloseableResources_Second");
    }

    public void doSomething() {
      System.out.println("Something -> AutoCloseableResources_Second");
    }

    @Override
    public void close() throws Exception {
      System.out.println("Closed AutoCloseableResources_Second");
    }
  }


}
