package datastructures.queue.cases;

import datastructures.queue.CircularQueue;

public final class CircularQueueCase {
  private CircularQueueCase() {
  }

  public static void run() {
    CircularQueue<String> buffer = new CircularQueue<>(3);
    buffer.enqueue("A");
    buffer.enqueue("B");
    buffer.enqueue("C");

    System.out.println("Buffer: " + buffer + " (full: " + buffer.isFull() + ")");
    System.out.println("Dequeued: " + buffer.dequeue());
    buffer.enqueue("D");
    System.out.println("Buffer after wraparound: " + buffer);
    System.out.println("Next: " + buffer.peek());

    try {
      buffer.enqueue("E");
    } catch (IllegalStateException exception) {
      System.out.println("Enqueue rejected: " + exception.getMessage());
    }
  }
}
