package com.dzhou.corejava.dsaj.ch4;

/**
 * Created by davidzhou on 6/20/14.
 */
public interface KeyMode {

    public abstract KeyMode deepCopy();

    public abstract int compareTo(Object targetKey);

    public abstract String toString();


}
