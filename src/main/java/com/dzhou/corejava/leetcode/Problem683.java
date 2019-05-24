package com.dzhou.corejava.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 683. K Empty Slots
 *
 * https://leetcode.com/problems/k-empty-slots/solution/
 */
public class Problem683 {


  public int kEmptySlots(int[] flowers, int k) {
    if (flowers == null || flowers.length == 0) {
      return -1;
    }

    int ans = flowers.length;

    int[] days = new int[flowers.length];
    for (int i = 0; i < flowers.length; i++) {
      days[flowers[i] - 1] = i + 1;
    }

    MinQueue<Integer> queue = new MinQueue<>();
    for (int i = 0; i < days.length - 1; i++) {
      queue.addLast(days[i]);

      if (k <= i) {
        queue.pollFirst();

        if (k == 0 || (days[i - k] < queue.min() && queue.min() > days[i + 1])) {
          ans = Math.min(ans, Math.max(days[i - k], days[i + 1]));

        }
      }
    }

    return ans < flowers.length ? ans : -1;

  }


  private static class MinQueue<E extends Comparable<E>> extends ArrayDeque<E> {
    Deque<E> mins;

    public MinQueue() {
      mins = new ArrayDeque<>();
    }

    @Override
    public void addLast(E e) {
      super.addLast(e);
      while (!mins.isEmpty() && e.compareTo(mins.peekLast()) < 0) {
        mins.pollLast();
      }
      mins.addLast(e);
    }

    @Override
    public E pollFirst() {
      E x = super.pollFirst();
      if (x == mins.peekFirst()) {
        mins.pollFirst();
      }
      return x;
    }

    public E min() {
      if (!this.mins.isEmpty()) {
        return this.mins.peekFirst();
      }
      return null;
    }

    public Deque<E> mins() {
      return this.mins;
    }

  }


  public int kEmptySlots2(int[] flowers, int k) {
    int[] days = new int[flowers.length];
    for (int i = 0; i < flowers.length; i++) {
      days[flowers[i] - 1] = i + 1;
    }

    int ans = Integer.MAX_VALUE;
    int left = 0, right = k + 1;

    search:
    while (right < days.length) {
      for (int i = left + 1; i < right; ++i) {
        if (days[i] < days[left] || days[i] < days[right]) {
          left = i;
          right = i + k + 1;
          continue search;
        }
      }
      ans = Math.min(ans, Math.max(days[left], days[right]));
      left = right;
      right = left + k + 1;
    }

    return ans < Integer.MAX_VALUE ? ans : -1;
  }

  public static void main(String[] args) {
    Problem683 problem = new Problem683();

    MinQueue<Integer> queue = new MinQueue<>();

    queue.addLast(1);
    System.out.println("queue=" + queue);
    System.out.println("mins=" + queue.mins());
    queue.addLast(3);
    System.out.println("queue=" + queue);
    System.out.println("mins=" + queue.mins());
    queue.addLast(6);
    System.out.println("queue=" + queue);
    System.out.println("mins=" + queue.mins());
    queue.addLast(2);
    System.out.println("queue=" + queue);
    System.out.println("mins=" + queue.mins());
    queue.addLast(4);
    System.out.println("queue=" + queue);
    System.out.println("mins=" + queue.mins());
    queue.addLast(8);
    System.out.println("queue=" + queue);
    System.out.println("mins=" + queue.mins());
    queue.addLast(5);
    System.out.println("queue=" + queue);
    System.out.println("mins=" + queue.mins());

    System.out.println("queue.pollFirst()=" + queue.pollFirst());
    System.out.println("queue=" + queue);
    System.out.println("mins=" + queue.mins());

    System.out.println("queue.pollFirst()=" + queue.pollFirst());
    System.out.println("queue=" + queue);
    System.out.println("mins=" + queue.mins());

    System.out.println("queue.pollFirst()=" + queue.pollFirst());
    System.out.println("queue=" + queue);
    System.out.println("mins=" + queue.mins());


    System.out.println("queue.pollFirst()=" + queue.pollFirst());
    System.out.println("queue=" + queue);
    System.out.println("mins=" + queue.mins());

    System.out.println("queue.pollFirst()=" + queue.pollFirst());
    System.out.println("queue=" + queue);
    System.out.println("mins=" + queue.mins());

    System.out.println(problem.kEmptySlots(new int[]{1, 3, 2}, 1));


  }

}
