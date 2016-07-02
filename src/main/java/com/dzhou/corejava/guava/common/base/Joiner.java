package com.dzhou.corejava.guava.common.base;

import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;

import static com.dzhou.corejava.guava.common.base.Preconditions.checkNotNull;

/**
 * Created by huizhou on 11/14/15.
 */
public class Joiner {

    private final String seperator;

    private Joiner(String seperator) {
        this.seperator = checkNotNull(seperator);
    }

    private Joiner(Joiner prototype) {
        this.seperator = prototype.seperator;
    }

    public static Joiner on(String seperator) {
        return new Joiner(seperator);
    }

    public static Joiner on(char seperator) {
        return new Joiner(String.valueOf(seperator));
    }

    /**
     * return a string containing the string representation of each of {@code parts}, using the
     * previously configured separator between each.
     */
    public final String join(Object[] parts) {
        return join(Arrays.asList(parts));
    }

    public final String join(Iterable<?> parts) {
        return join(parts.iterator());
    }

    public final String join(Iterator<?> parts) {
        return appendTo(new StringBuilder(), parts).toString();
    }

    public final StringBuilder appendTo(StringBuilder builder, Object[] parts) {
        return appendTo(builder, Arrays.asList(parts));
    }

    public final StringBuilder appendTo(StringBuilder builder, Iterable<?> parts) {
        return appendTo(builder, parts.iterator());
    }

    public final StringBuilder appendTo(StringBuilder builder, Iterator<?> parts) {
        try {
            appendTo((Appendable) builder, parts);
        } catch (IOException impossible) {
            throw new AssertionError(impossible);
        }
        return builder;
    }

    public <A extends Appendable> A appendTo(A appendable, Iterator<?> parts) throws IOException {
        checkNotNull(appendable);
        if (parts.hasNext()) {
            appendable.append(toString(parts.next()));
            while (parts.hasNext()) {
                appendable.append(seperator);
                appendable.append(toString(parts.next()));
            }
        }
        return appendable;
    }

    public final <A extends Appendable> A appendTo(A appendable, Object[] parts) throws
            IOException {
        return appendTo(appendable, Arrays.asList(parts));
    }

    public <A extends Appendable> A appendTo(A appendable, Iterable<?> parts) throws IOException {
        return appendTo(appendable, parts.iterator());
    }

    CharSequence toString(Object part) {
        checkNotNull(part);
        return (part instanceof CharSequence) ? (CharSequence) part : part.toString();
    }

    public Joiner skipNulls() {
        return new Joiner(this) {
            @Override
            public <A extends Appendable> A appendTo(A appendable, Iterator<?> parts)
                    throws IOException {
                checkNotNull(appendable, "appendable");
                checkNotNull(parts, "parts");
                while (parts.hasNext()) {
                    Object part = parts.next();
                    if (part != null) {
                        appendable.append(Joiner.this.toString(part));
                        break;
                    }
                }
                while (parts.hasNext()) {
                    Object part = parts.next();
                    if (part != null) {
                        appendable.append(seperator);
                        appendable.append(Joiner.this.toString(part));
                    }
                }
                return appendable;
            }
        };

    }

    public Joiner userForNull(final String nullText) {
        checkNotNull(nullText);
        return new Joiner(this) {
            @Override
            CharSequence toString(Object part) {
                return (part == null) ? nullText : Joiner.this.toString(part);
            }
        };
    }

    public MapJoiner withKeyValueSeparator(String keyValueSeparator) {
        return new MapJoiner(this, keyValueSeparator);
    }

    public static final class MapJoiner {
        private final Joiner joiner;
        private final String keyValueSeparator;

        private MapJoiner(Joiner joiner, String keyValueSeparator) {
            this.joiner = joiner;
            this.keyValueSeparator = checkNotNull(keyValueSeparator);
        }


        public String join(Map<?, ?> map) {
            return join(map.entrySet());
        }

        public String join(Iterable<? extends Map.Entry<?, ?>> entries) {
            return join(entries.iterator());
        }

        public String join(Iterator<? extends Map.Entry<?, ?>> entries) {
            return appendTo(new StringBuilder(), entries).toString();
        }

        public StringBuilder appendTo(StringBuilder builder, Iterator<? extends Map.Entry<?, ?>>
                entries) {
            try {
                appendTo((Appendable) builder, entries);
            } catch (IOException impossible) {
                throw new AssertionError(impossible);
            }
            return builder;
        }

        public <A extends Appendable> A appendTo(A appendable, Map<?, ?> map) throws IOException {
            return appendTo(appendable, map.entrySet());
        }


        public <A extends Appendable> A appendTo(A appendable, Iterable<? extends Map.Entry<?,
                ?>> entries)
                throws IOException {
            return appendTo(appendable, entries.iterator());
        }

        public <A extends Appendable> A appendTo(A appendable, Iterator<? extends Map.Entry<?,
                ?>> parts) throws IOException {
            checkNotNull(appendable);
            if (parts.hasNext()) {
                Map.Entry<?, ?> entry = parts.next();
                appendable.append(joiner.toString(entry.getKey()));
                appendable.append(keyValueSeparator);
                appendable.append(joiner.toString(entry.getValue()));
                while (parts.hasNext()) {
                    appendable.append(joiner.seperator);
                    Map.Entry<?, ?> e = parts.next();
                    appendable.append(joiner.toString(e.getKey()));
                    appendable.append(keyValueSeparator);
                    appendable.append(joiner.toString(e.getValue()));
                }
            }
            return appendable;
        }


    }


}
