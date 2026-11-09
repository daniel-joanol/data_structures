package datastructures.queue.cases;

import datastructures.queue.PriorityQueue;
import java.util.Comparator;

public final class PriorityQueueCase {
  private PriorityQueueCase() {
  }

  public static void run() {
    PriorityQueue<Integer> tasks = new PriorityQueue<>(2, Comparator.naturalOrder());
    tasks.enqueue(5);
    tasks.enqueue(1);
    tasks.enqueue(3);
    tasks.enqueue(2);

    System.out.println("Heap layout: " + tasks);
    System.out.println("Highest priority (lowest number): " + tasks.peek());
    while (!tasks.isEmpty()) {
      System.out.println("Processing priority: " + tasks.dequeue());
    }

    PriorityQueue<String> longestFirst =
        new PriorityQueue<>(Comparator.comparingInt(String::length).reversed());
    longestFirst.enqueue("art");
    longestFirst.enqueue("algorithm");
    longestFirst.enqueue("heap");
    System.out.println("Longest word first: " + longestFirst.dequeue());
  }
}
