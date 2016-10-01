package com.dzhou.corejava.guava.common.base;


import com.google.common.base.CharMatcher;

import java.util.Iterator;

/**
 * Created by huizhou on 12/11/15.
 */
public final class Splitter {
  private final CharMatcher trimmer;
  private final boolean omitEmptyStrings;
  private final Strategy strategy;
  private final int limit;

  private Splitter(Strategy strategy) {
    this(strategy, false, CharMatcher.none(), Integer.MAX_VALUE);
  }

  private Splitter(Strategy strategy, boolean omitEmptyStrings, CharMatcher trimmer, int limit) {
    this.strategy = strategy;
    this.trimmer = trimmer;
    this.omitEmptyStrings = omitEmptyStrings;
    this.limit = limit;
  }

  private interface Strategy {
    Iterator<String> iterator(Splitter splitter, CharSequence toSplit);
  }

  public static Splitter on(char separator) {
    return on(CharMatcher.is(separator));
  }

  public static Splitter on(final CharMatcher separatorMatcher) {
    return new Splitter(
        new Strategy() {
          @Override
          public SplittingIterator iterator(Splitter splitter, final CharSequence toSplit) {
            return new SplittingIterator(splitter, toSplit) {
              @Override
              int separatorStart(int start) {
                return separatorMatcher.indexIn(toSplit, start);
              }

              @Override
              int separatorEnd(int separatorPosition) {
                return separatorPosition + 1;
              }
            };
          }
        });
  }

  public Iterable<String> split(final CharSequence sequence) {
    return new Iterable<String>() {
      @Override
      public Iterator<String> iterator() {
        return splittingIterator(sequence);
      }

      @Override
      public String toString() {
        return Joiner.on(",")
            .appendTo(new StringBuilder("["), this)
            .append("]")
            .toString();
      }
    };
  }

  private Iterator<String> splittingIterator(final CharSequence sequence) {
    return strategy.iterator(this, sequence);
  }

  private abstract static class SplittingIterator extends AbstractIterator<String> {
    final CharSequence toSplit;
    final CharMatcher trimmer;
    final boolean omitEmptyStrings;

    abstract int separatorStart(int start);

    abstract int separatorEnd(int separatorPosition);

    int offset = 0;
    int limit;

    protected SplittingIterator(Splitter splitter, CharSequence toSplit) {
      this.toSplit = toSplit;
      this.trimmer = splitter.trimmer;
      this.omitEmptyStrings = splitter.omitEmptyStrings;
      this.limit = splitter.limit;
    }

    @Override
    protected String computeNext() {
      int nextStart = offset;
      while (offset != -1) {
        int start = nextStart;
        int end;
        int separatorPosition = separatorStart(offset);
        if (separatorPosition == -1) {
          end = toSplit.length();
          offset = -1;
        } else {
          end = separatorPosition;
          offset = separatorEnd(separatorPosition);
        }
        if (offset == nextStart) {
          offset++;
          if (offset >= toSplit.length()) {
            offset = -1;
          }
          continue;
        }
        while (start < end && trimmer.matches(toSplit.charAt(start))) {
          start++;
        }
        while (end > start && trimmer.matches(toSplit.charAt(end - 1))) {
          end--;
        }
        if (omitEmptyStrings && start == end) {
          nextStart = offset;
          continue;
        }
        if (limit == 1) {
          end = toSplit.length();
          offset = -1;
          while (end > start && trimmer.matches(toSplit.charAt(end - 1))) {
            end--;
          }
        } else {
          limit--;
        }
        return toSplit.subSequence(start, end).toString();
      }
      return endOfData();
    }

  }
}