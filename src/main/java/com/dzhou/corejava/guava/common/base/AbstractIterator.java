package com.dzhou.corejava.guava.common.base;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static com.google.common.base.Preconditions.checkState;

public abstract class AbstractIterator<T> implements Iterator<T> {
  private State state = State.NOT_READY;

  protected AbstractIterator() {

  }

  private enum State {
    READY,
    NOT_READY,
    DONE,
    FAILED,
  }

  private T next;

  protected abstract T computeNext();

  protected final T endOfData() {
    state = State.DONE;
    return null;
  }

  @Override
  public final boolean hasNext() {
    checkState(state != State.FAILED);
    switch (state) {
      case READY:
        return true;
      case DONE:
        return false;
      default:
    }
    return tryToComputeNext();
  }

  private boolean tryToComputeNext() {
    state = State.FAILED;
    next = computeNext();
    if (state != State.DONE) {
      state = State.READY;
      return true;
    }
    return false;
  }

  @Override
  public final T next() {
    if (!hasNext()) {
      throw new NoSuchElementException();
    }
    state = State.NOT_READY;
    T result = next;
    next = null;
    return result;
  }

}

