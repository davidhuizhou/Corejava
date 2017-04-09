package com.dzhou.corejava.guava.common.base;

import com.google.common.base.CharMatcher;
import com.google.common.base.Predicate;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by huizhou on 10/1/16.
 */
public abstract class CharMatcherExt extends CharMatcher {

  public static CharMatcher whitespace() {
    return Whitespace.INSTANCE;
  }

  public static CharMatcher is(final char match) {
    return new Is(match);
  }

  public static CharMatcher isNot(final char match) {
    return new isNot(match);
  }

  public CharMatcher negate() {
    return new Negated(this);
  }

  /**
   * Determines a true or false value for the give character.
   */
  public abstract boolean matches(char c);

  /**
   * Implementation of {@link #whitespace()}.
   */
  static final class Whitespace extends CharMatcherExt {
    static final Whitespace INSTANCE = new Whitespace();

    @Override
    public boolean matches(char c) {
      return CharMatcher.whitespace().matches(c);
    }

  }

  /**
   * Implementation of {@link #is(char)}.
   */
  private static final class Is extends CharMatcherExt {
    private final char match;

    Is(char match) {
      this.match = match;
    }

    @Override
    public boolean matches(char c) {
      return c == match;
    }


  }

  /**
   * Implementation of {@link #isNot(char)}.
   */
  private static final class isNot extends CharMatcherExt {
    private final char match;

    isNot(char match) {
      this.match = match;
    }

    @Override
    public boolean matches(char c) {
      return c != match;
    }

  }

  /**
   * Implementation of {@link #negate()}.
   */
  private static class Negated extends CharMatcherExt {
    final CharMatcherExt original;

    Negated(CharMatcherExt original) {
      this.original = checkNotNull(original);
    }

    @Override
    public boolean matches(char c) {
      return !original.matches(c);
    }

    @Override
    public boolean matchesAllOf(CharSequence sequence) {
      return original.matchesNoneOf(sequence);
    }

    @Override
    public boolean matchesNoneOf(CharSequence sequence) {
      return original.matchesAllOf(sequence);
    }
  }


  /**
   * @deprecated Provided only to satisfy the {@link Predicate} interface; use {@link #matches}
   * instead.
   */
  @Deprecated
  @Override
  public boolean apply(Character character) {
    return matches(character);
  }

  @Override
  public boolean test(Character input) {
    return apply(input);
  }

  public boolean matchesNoneOf(CharSequence sequence) {
    return this.indexIn(sequence) == -1;
  }

  @Override
  public boolean matchesAllOf(CharSequence sequence) {
    for (int i = sequence.length() - 1; i >= 0; i--) {
      if (!matches(sequence.charAt(i))) {
        return false;
      }
    }
    return true;
  }

  public int indexIn(CharSequence sequence) {
    return this.indexIn(sequence, 0);
  }

  /**
   * Returns the index of the first matching character in a character sequence, starting from a
   * given position, or {@code -1} if no character matches after that position.
   */
  public int indexIn(CharSequence sequence, int start) {
    int length = sequence.length();
    this.checkPositionIndex(start, length);
    for (int i = start; i < length; i++) {
      if (matches(sequence.charAt(i))) {
        return i;
      }
    }
    return -1;
  }

  public static int checkPositionIndex(int index, int size) {
    return checkPositionIndex(index, size, "index");
  }

  public static int checkPositionIndex(int index, int size, String desc) {
    if (index < 0 || index > size) {
      throw new IndexOutOfBoundsException();
    }
    return index;
  }

  /**
   * Return a string containing all non-matchig characters of a character sequence, in order. For
   * example: <pre>   {@code
   *
   *    CharMatcher.is('a').removeFrom("bazaar")}</pre>
   *
   * ... returns {@code "bzr"}.
   */
  public String removeFromOriginal(CharSequence sequence) {
    String string = sequence.toString();
    int pos = indexIn(string);
    if (pos == -1) {
      return string;
    }
    char[] chars = string.toCharArray();
    int spread = 1;

    OUT:
    while (true) {
      pos++;
      while (true) {
        if (pos == chars.length) {
          break OUT;
        }
        if (matches(chars[pos])) {
          break;
        }
        chars[pos - spread] = chars[pos];
        pos++;
      }
      spread++;
    }
    return new String(chars, 0, pos - spread);
  }

  public String removeFrom(CharSequence sequence) {
    String string = sequence.toString();
    int pos = indexIn(string);
    if (pos == -1) {
      return string;
    }
    char[] chars = string.toCharArray();
    int curr = pos;
    for (int i = pos + 1; i < chars.length; i++) {
      if (!matches(chars[i])) {
        chars[curr++] = chars[i];
      }
    }
    return new String(chars, 0, curr);

  }

  /**
   * Returns a string containing all matching characters of a character sequence, in order. For
   * example: <pre> {@code
   *
   *    CharMatcher.is('a', retaioFrom("bazaar")}</pre>
   *
   * ... returns {@code "aaa"}.
   */
  public String retainFrom(CharSequence sequence) {
    return negate().removeFrom(sequence);
  }

  /**
   * Returns a string copy of the input character sequence, with each character that matches this
   * matcher replaced by a give replacement character.  For example: <pre>  {@code
   *    CharMatcher.is('a').replaceFrom("radar", 'o')}</pre>
   *
   * returns {@code "rodor"}.
   */
  public String replaceFrom(CharSequence sequence, char replacement) {
    String string = sequence.toString();
    int pos = indexIn(sequence);
    if (pos == -1) {
      return string;
    }
    char[] chars = string.toCharArray();
    chars[pos] = replacement;
    for (int i = pos + 1; i < chars.length; i++) {
      if (matches(chars[i])) {
        chars[i] = replacement;
      }
    }
    return new String(chars);
  }

  /**
   * Returns a string copy of the input character sequence, with each character that matches this
   * matcher replaced by a given replacement sequence. For example: <pre> {@code
   *  CharMatcher.is('a').replaceFrom("yaha", ""oo")}</pre>
   *
   * ... returns {@code "yoohoo"}.
   *
   * @param sequence    the character sequence to replace matching characters in
   * @param replacement the characters to append to the result string in place of each matching
   *                    character in {@code sequence}
   * @return the new string
   */
  public String replaceFrom(CharSequence sequence, CharSequence replacement) {
    int replacementLen = replacement.length();
    if (replacementLen == 0) {
      return removeFrom(sequence);
    }
    if (replacementLen == 1) {
      return replaceFrom(sequence, replacement.charAt(0));
    }

    String string = sequence.toString();
    int pos = indexIn(string);
    if (pos == -1) {
      return string;
    }
    int len = string.length();
    StringBuilder buf = new StringBuilder((len * 3 / 2) + 16);

    int oldpos = 0;
    do {
      buf.append(string, oldpos, pos);
      buf.append(replacement);
      oldpos = pos + 1;
      pos = indexIn(string, oldpos);
    } while (pos != -1);
    buf.append(string, oldpos, len);
    return buf.toString();
  }


  public String collapseFrom(CharSequence sequence, char replacement) {
    int len = sequence.length();
    for (int i = 0; i < len; i++) {
      char c = sequence.charAt(i);
      if (matches(c)) {
        if (c == replacement && (i == len - 1 || !matches(sequence.charAt(i + 1)))) {
          i++;
        } else {
          StringBuilder builder = new StringBuilder(len).append(sequence, 0, i).append(replacement);
          return finishCollapseFrom(sequence, i + 1, len, replacement, builder, true);
        }
      }
    }
    // no replacement needed
    return sequence.toString();
  }

  private String finishCollapseFrom(
      CharSequence sequence,
      int start,
      int end,
      char replacement,
      StringBuilder builder,
      boolean inMatchingGroup) {
    for (int i = start; i < end; i++) {
      char c = sequence.charAt(i);
      if (matches(c)) {
        if (!inMatchingGroup) {
          builder.append(replacement);
          inMatchingGroup = true;
        }
      } else {
        builder.append(sequence.charAt(i));
        inMatchingGroup = false;
      }
    }
    return builder.toString();
  }

}
