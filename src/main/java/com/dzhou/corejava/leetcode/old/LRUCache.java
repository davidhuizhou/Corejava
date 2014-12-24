package com.dzhou.corejava.leetcode.old;

import java.util.*;

/**
 * Created by huizhou on 10/25/14.
 */

/**
 * https://oj.leetcode.com/problems/lru-cache/
 * https://oj.leetcode.com/submissions/detail/13833496/
 *
 */

public class LRUCache {
    private int accessed = 0;
    private int N = 0;
    private Entry[] pq;
    private Map<Integer, Entry> map;

    public LRUCache(int capacity) {
        pq = new Entry[capacity + 1];
        map = new HashMap<Integer, Entry>();
        N = 0;
    }

    synchronized public int get(int key) {
        Entry e = map.get(key);
        if (e == null) {
            return -1;
        } else {
            e.lastAccessed = ++accessed;
            sink(e.index);

        }
        return e.value;
    }

    synchronized public void set(int key, int value) {
        Entry e = map.get(key);
        if (e == null) {
            if (N < pq.length - 1) {
                e = new Entry(++N, key, value, ++accessed);
                pq[N] = e;

            } else {
                map.remove(pq[1].key);
                e = new Entry(1, key, value, ++accessed);
                pq[1] = e;
                sink(1);


            }
            map.put(key, e);
        } else {
            e.lastAccessed = ++accessed;
            e.value = value;
            sink(e.index);
        }
    }

    synchronized private void sink(int k) {
        while (2 * k <= N) {
            int j = 2 * k;
            if (j < N && greater(j, j + 1)) j++;
            if (!greater(k, j)) break;
            exch(k, j);
            k = j;
        }
    }

    private boolean greater(int i, int j) {
        return pq[i].compareTo(pq[j]) > 0;
    }

    synchronized private void exch(int i, int j) {
        Entry swap = pq[i];
        pq[i] = pq[j];
        pq[j] = swap;
        pq[i].index = i;
        pq[j].index = j;
    }


    private class Entry implements Comparable<Entry> {
        int index;
        int key;
        int value;
        int lastAccessed;

        Entry(int index, int key, int value, int lastAccessed) {
            this.index = index;
            this.key = key;
            this.value = value;
            this.lastAccessed = lastAccessed;
        }

        public int compareTo(Entry entry) {
            if (this.lastAccessed == entry.lastAccessed)
                return 0;
            else
                return this.lastAccessed > entry.lastAccessed ? 1 : -1;
        }
    }

}
