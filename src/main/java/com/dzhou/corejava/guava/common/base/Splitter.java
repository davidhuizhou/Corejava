package com.dzhou.corejava.guava.common.base;

import com.google.common.base.CharMatcher;
import com.google.common.collect.*;

import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

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
    this.omitEmptyStrings = omitEmptyStrings;
    this.trimmer = trimmer;
    this.limit = limit;
  }

  public static Splitter on(char separator) {
    return on(CharMatcher.is(separator));
  }

  public static Splitter on(final CharMatcher separatorMatcher) {
    checkNotNull(separatorMatcher);

    return new Splitter(
        new Strategy() {
          @Override
          public SplittingIterator iterator(Splitter splitter, CharSequence toSplit) {
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

  public static Splitter on(final String separator) {
    checkArgument(separator.length() != 0, "The separator may not be the empty string.");
    if (separator.length() == 1) {
      return Splitter.on(separator.charAt(0));
    }
    return new Splitter(
        new Strategy() {
          @Override
          public SplittingIterator iterator(Splitter splitter, CharSequence toSplit) {
            return new SplittingIterator(splitter, toSplit) {
              @Override
              int separatorStart(int start) {
                int separatorLength = separator.length();

                positions:
                for (int p = start, last = toSplit.length() - separatorLength; p <= last; p++) {
                  for (int i = 0; i < separatorLength; i++) {
                    if (toSplit.charAt(p + i) != separator.charAt(i)) {
                      continue positions;
                    }
                  }
                  return p;
                }
                return -1;
              }

              @Override
              int separatorEnd(int separatorPosition) {
                return separatorPosition + separator.length();
              }
            };
          }
        });
  }

  private interface Strategy {
    Iterator<String> iterator(Splitter splitter, CharSequence toSplit);
  }

  private abstract static class SplittingIterator extends com.google.common.collect.AbstractIterator<String> {
    final CharSequence toSplit;
    final CharMatcher trimmer;
    final boolean omitEmptyStrings;

    abstract int separatorStart(int start);

    abstract int separatorEnd(int separatorPosition);

    int offset = 0;
    int limit;

    protected SplittingIterator(Splitter splitter, CharSequence toSplit) {
      this.trimmer = splitter.trimmer;
      this.omitEmptyStrings = splitter.omitEmptyStrings;
      this.limit = limit;
      this.toSplit = toSplit;
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
          if (offset > toSplit.length()) {
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