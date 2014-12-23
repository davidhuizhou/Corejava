package com.dzhou.corejava.leetcode;

/**
 * Created by huizhou on 11/10/14.
 */
public class Tower {
    private java.util.Stack<Integer> disks;
    private int index;

    public Tower(int index) {
        disks = new java.util.Stack<Integer>();
        this.index = index;
    }

    public int index() {
        return this.index;
    }

    public void add(int d) {
        if (!disks.isEmpty() && disks.peek() <= d) {
            System.out.println("Error placing disk " + d);
        } else {
            disks.push(d);
        }
    }

    public void moveTopTo(Tower t) {
        int top = disks.pop();
        t.add(top);
        System.out.println("Move disk " + top + " from " + index() + " to " + t.index());
    }

    public void moveDisks(int n, Tower destination, Tower buffer) {
        if (n > 0) {
            moveDisks(n - 1, buffer, destination);
            moveTopTo(destination);
            moveDisks(n - 1, destination, this);
        }
    }
}

