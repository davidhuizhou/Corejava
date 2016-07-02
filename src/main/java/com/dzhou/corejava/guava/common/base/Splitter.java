package com.dzhou.corejava.guava.common.base;


import com.google.common.base.CharMatcher;

import java.util.Iterator;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by huizhou on 12/11/15.
 */
public final class Splitter {
    private final CharMatcher trimmer;
    private final boolean omitEmptyStrings;
    private final int limit;
    private final Strategy strategy;

    private Splitter(Strategy strategy) {
        this(strategy, CharMatcher.none(), false, Integer.MAX_VALUE);
    }

    private Splitter(Strategy strategy, CharMatcher trimmer, boolean omitEmptyStrings, int limit) {
        this.strategy = strategy;
        this.trimmer = trimmer;
        this.omitEmptyStrings = omitEmptyStrings;
        this.limit = limit;
    }

    public static Splitter on(char c) {
        return on(CharMatcher.is(c));
    }

    public static Splitter on(CharMatcher separatorMatcher) {
        checkNotNull(separatorMatcher);
        return new Splitter(
                new Strategy() {
                    public SplittingIterator iterator(Splitter splitter, CharSequence
                            toSplit) {
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
                        .appendTo(new StringBuilder().append('['), this)
                        .append(']')
                        .toString();
            }
        };
    }

    private Iterator<String> splittingIterator(CharSequence sequence) {
        return strategy.iterator(this, sequence);
    }


    private interface Strategy {
        Iterator<String> iterator(Splitter splitter, CharSequence toSplit);
    }

    private abstract static class SplittingIterator extends AbstractIterator<String> {
        final CharMatcher trimmer;
        final boolean omitEmptyStrings;
        final CharSequence toSplit;

        abstract int separatorStart(int start);

        abstract int separatorEnd(int separatorPosition);

        int offset = 0;
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
                    offset = -1;
                    while (end > start && trimmer.matches(toSplit.charAt(end))) {
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
