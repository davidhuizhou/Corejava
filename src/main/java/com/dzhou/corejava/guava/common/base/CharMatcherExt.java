package com.dzhou.corejava.guava.common.base;

import com.google.common.base.CharMatcher;

/**
 * Created by huizhou on 10/1/16.
 */
public abstract class CharMatcherExt extends CharMatcher {

  public static CharMatcher whitespace() {
    return WhitespaceExt.INSTANCE;
  }


  /**
   * Determines a true or false value for the give character.
   */
  public abstract boolean matches(char c);

  static final class WhitespaceExt extends CharMatcherExt {

    static final CharMatcher whitespace = CharMatcher.whitespace();

    static final WhitespaceExt INSTANCE = new WhitespaceExt();

    @Override
    public boolean matches(char c) {
      return whitespace.matches(c);
    }

  }

  public boolean matchesAllOf(CharSequence sequence) {
    for (int i = sequence.length() - 1; i >= 0; i--) {
      if (!matches(sequence.charAt(i))) {
        return false;
      }
    }
    return true;
  }


}
