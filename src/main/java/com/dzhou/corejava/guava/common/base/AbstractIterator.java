package com.dzhou.corejava.guava.common.base;


import java.util.Iterator;
import java.util.NoSuchElementException;

import static com.dzhou.corejava.guava.common.base.Preconditions.checkState;

/**
 * Created by huizhou on 12/11/15.
 */
abstract class AbstractIterator<T> implements Iterator<T> {
    private State state = State.NOT_READY;

    protected AbstractIterator() {

    }

    private enum State {
        READY, NOT_READY, DONE, FAILED;
    }

    private T next;

    protected abstract T computeNext();

    protected final T endOfData() {
        state = State.DONE;
        return null;
    }


    public final boolean hasNext() {
        checkState(state != State.FAILED);
        switch (state) {
            case DONE:
                return false;
            case READY:
                return true;
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

    public final T next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        state = State.NOT_READY;
        T result = next;
        next = null;
        return result;
    }

    public final void remove() {
        throw new UnsupportedOperationException();
    }

}
