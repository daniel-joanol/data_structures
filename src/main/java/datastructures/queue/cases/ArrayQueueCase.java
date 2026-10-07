package datastructures.queue.cases;

import datastructures.queue.ArrayQueue;

public final class ArrayQueueCase {
  private ArrayQueueCase() {
  }

  public static void run() {
    ArrayQueue<String> printJobs = new ArrayQueue<>(3);
    printJobs.enqueue("Report");
    printJobs.enqueue("Invoice");
    printJobs.enqueue("Presentation");

    System.out.println("Print queue: " + printJobs);
    System.out.println("Next job: " + printJobs.dequeue());
    printJobs.enqueue("Photo album");
    printJobs.enqueue("Shipping labels");
    System.out.println("Queue after wraparound and growth: " + printJobs);
    System.out.println("Next job: " + printJobs.peek());
    System.out.println("Completed job: " + printJobs.dequeue());
    System.out.println("Remaining jobs: " + printJobs);
  }
}