package com.dzhou.corejava.guava.common.base;

import com.google.common.base.CharMatcher;

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
        }
    );
  }

  public static Splitter on(final String separator) {
    checkArgument(separator.length() != 0, "The separator may not be empty.");
    if (separator.length() == 1) {
      return on(separator.charAt(0));
    }

    return new Splitter(
        new Strategy() {
          @Override
          public SplittingIterator iterator(Splitter splitter, CharSequence toSplit) {
            return new SplittingIterator(splitter, toSplit) {
              @Override
              protected int separatorStart(int start) {
                int separatorLength = separator.length();

                positions:
                for (int p = start, last = toSplit.length() - separatorLength; p <= last; p++) {
                  for (int i = p; i < separatorLength; i++) {
                    if (toSplit.charAt(i + p) != separator.charAt(i)) {
                      continue positions;
                    }
                  }
                  return p;
                }
                return -1;
              }

              @Override
              protected int separatorEnd(int separatorPosition) {
                return separatorPosition + separator.length();
              }
            };
          }
        }
    );
  }

  public static Splitter on(final Pattern separatorPattern) {
    checkNotNull(separatorPattern);
    checkArgument(
        !separatorPattern.matcher("").matches(),
        "The pattern may not match the empty string: %s",
        separatorPattern);

    return new Splitter(
        new Strategy() {
          @Override
          public SplittingIterator iterator(Splitter splitter, CharSequence toSplit) {
            return new SplittingIterator(splitter, toSplit) {
              final Matcher matcher = separatorPattern.matcher(toSplit);

              @Override
              public int separatorStart(int start) {
                return matcher.find(start) ? matcher.start() : -1;
              }

              @Override
              public int separatorEnd(int separatorPosition) {
                return matcher.end();
              }
            };
          }
        }
    );

  }

  public static Splitter onPattern(String separatorPattern) {
    return on(Pattern.compile(separatorPattern));
  }

  public Iterable<String> split(final CharSequence sequence) {
    checkNotNull(sequence);

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

  public Splitter trimResults() {
    return trimResults(CharMatcher.whitespace());
  }

  public Splitter trimResults(CharMatcher trimmer) {
    checkNotNull(trimmer);
    return new Splitter(strategy, omitEmptyStrings, trimmer, limit);
  }

  public Splitter omitEmptyStrings() {
    return new Splitter(strategy, true, trimmer, limit);
  }

  private Iterator<String> splittingIterator(CharSequence sequence) {
    return strategy.iterator(this, sequence);
  }

  private interface Strategy {
    Iterator<String> iterator(Splitter splitter, CharSequence toSplit);
  }

  private abstract static class SplittingIterator extends AbstractIterator<String> {
    final CharSequence toSplit;
    final CharMatcher trimmer;
    final boolean omitEmptyStrings;

    abstract int separatorStart(int start);

    abstract int separatorEnd(int separatorPosition);

    int offset;
    int limit;

    protected SplittingIterator(Splitter splitter, CharSequence toSplit) {
      this.trimmer = splitter.trimmer;
      this.omitEmptyStrings = splitter.omitEmptyStrings;
      this.limit = splitter.limit;
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