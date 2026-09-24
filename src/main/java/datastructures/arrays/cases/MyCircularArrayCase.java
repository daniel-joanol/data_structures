package datastructures.arrays.cases;

import datastructures.arrays.MyCircularArray;

public final class MyCircularArrayCase {
  private MyCircularArrayCase() {
  }

  public static void run() {
    MyCircularArray<String> topics = new MyCircularArray<>(5);
    topics.add("A");
    topics.add("B");
    topics.add("C");
    System.out.println("Topics after adding: " + topics);

    System.out.println("Removed topic: " + topics.remove());
    topics.add("D");
    topics.add("E");
    System.out.println("Topics after wraparound: " + topics);

    System.out.println("First topic: " + topics.peek());
    System.out.println("Topic at index 2: " + topics.get(2));
    System.out.println("Contains D: " + topics.contains("D"));
    System.out.println("Circular array is full: " + topics.isFull());
    topics.add("E");
    System.out.println("Circular array is full: " + topics.isFull());
    System.out.println("Topics full: " + topics);

    try {
      topics.add("F");
    } catch (IllegalStateException e) {
      System.out.println("Exception caught: " + e.getMessage());
    }
  
    topics.clear();
    System.out.println("Topics after clear: " + topics);
    System.out.println("Circular array is empty: " + topics.isEmpty());
  }
}