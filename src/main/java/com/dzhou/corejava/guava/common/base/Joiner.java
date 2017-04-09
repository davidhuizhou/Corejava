package com.dzhou.corejava.guava.common.base;

import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by huizhou on 11/14/15.
 */
public class Joiner {

  public static Joiner on(String separator) {
    return new Joiner(separator);
  }

  public static Joiner on(char separator) {
    return new Joiner(String.valueOf(separator));
  }

  private final String separator;

  private Joiner(String separator) {
    this.separator = checkNotNull(separator);
  }

  private Joiner(Joiner prototype) {
    this.separator = prototype.separator;
  }

  public final String join(Object[] parts) {
    return join(Arrays.asList(parts));
  }

  public final String join(Iterable<?> parts) {
    return join(parts.iterator());
  }

  public final String join(Iterator<?> parts) {
    return appendTo(new StringBuilder(), parts).toString();
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


  public <A extends Appendable> A appendTo(A appendable, Iterable<?> parts) throws IOException {
    return appendTo(appendable, parts.iterator());
  }


  public <A extends Appendable> A appendTo(A appendable, Iterator<?> parts) throws IOException {
    checkNotNull(appendable);
    if (parts.hasNext()) {
      appendable.append(toString(parts.next()));
      while (parts.hasNext()) {
        appendable.append(separator);
        appendable.append(toString(parts.next()));
      }
    }
    return appendable;
  }

  public final <A extends Appendable> A appendTo(A appendable, Object[] parts) throws IOException {
    return appendTo(appendable, Arrays.asList(parts));
  }


  CharSequence toString(Object part) {
    checkNotNull(part);
    return (part instanceof CharSequence) ? (CharSequence) part : part.toString();
  }

  public Joiner skipNulls() {
    return new Joiner(this) {
      @Override
      public <A extends Appendable> A appendTo(A appendable, Iterator<?> parts) throws IOException {
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
            appendable.append(separator);
            appendable.append(Joiner.this.toString(part));
          }
        }
        return appendable;

      }
    };
  }

  public Joiner useForNull(final String nullText) throws IOException {
    checkNotNull(nullText);
    return new Joiner(this) {
      @Override
      public <A extends Appendable> A appendTo(A appendable, Iterator<?> parts) {
        return null;

      }
    };
  }

  public static final class MapJoiner {
    private final Joiner joiner;
    private final String keyValueSeparator;

    private MapJoiner(Joiner joiner, String keyValueSeparator) {
      this.joiner = joiner;
      this.keyValueSeparator = checkNotNull(keyValueSeparator);

    }

    public <A extends Appendable> A appendTo(A appendable, Iterator<? extends Map.Entry<?, ?>>
      parts) throws IOException {
      checkNotNull(appendable);
      if (parts.hasNext()) {
        Map.Entry<?, ?> entry = parts.next();
        appendable.append(joiner.toString(entry.getKey()));
        appendable.append(keyValueSeparator);
        appendable.append(joiner.toString(entry.getValue()));

      }
      while (parts.hasNext()) {
        appendable.append(joiner.separator);
        Map.Entry<?, ?> entry = parts.next();
        appendable.append(joiner.toString(entry.getKey()));
        appendable.append(keyValueSeparator);
        appendable.append(joiner.toString(entry.getValue()));
      }
      return appendable;


    }


  }

}
