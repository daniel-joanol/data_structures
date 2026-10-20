package datastructures.queue.cases;

import datastructures.queue.LinkedQueue;

public final class LinkedQueueCase {
  private LinkedQueueCase() {
  }

  public static void run() {
    LinkedQueue<String> customers = new LinkedQueue<>();
    customers.enqueue("Alice");
    customers.enqueue("Bob");
    customers.enqueue("Carol");

    System.out.println("Customer line: " + customers);
    System.out.println("Now serving: " + customers.dequeue());
    customers.enqueue("Dave");
    System.out.println("Next customer: " + customers.peek());
    System.out.println("Line size: " + customers.size());
    System.out.println("Remaining line: " + customers);
  }
}
