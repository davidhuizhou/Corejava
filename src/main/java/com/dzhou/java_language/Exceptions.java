package com.dzhou.java_language;

import java.io.IOException;
import java.sql.SQLException;

public class Exceptions {
  public static void main(String[] args) throws Exception {
    rethrow();
  }

  // Add another exception to the method signature to handle the
  // new exception that can be thrown.
  public static void rethrow() throws SQLException, IOException {
    try {
      throw new SQLException("Caught SQLException");
    } catch (Exception e) {
      e.printStackTrace();
      throw e;
    }
  }
}
