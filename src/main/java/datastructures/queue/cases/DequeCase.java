package datastructures.queue.cases;

import datastructures.queue.Deque;

public final class DequeCase {
  private DequeCase() {
  }

  public static void run() {
    Deque<String> deque = new Deque<>(2);
    deque.addLast("B");
    deque.addLast("C");
    deque.addFirst("A");
    deque.addFirst("Z");

    System.out.println("Deque: " + deque);
    System.out.println("First: " + deque.peekFirst() + ", last: " + deque.peekLast());
    System.out.println("Removed first: " + deque.removeFirst());
    System.out.println("Removed last: " + deque.removeLast());
    System.out.println("Deque after removals: " + deque);
  }
}
