package datastructures.linkedlist.cases;

import datastructures.linkedlist.DoubleLinkedList;

public final class DoubleLinkedListCase {
  private DoubleLinkedListCase() {
  }

  public static void run() {
    DoubleLinkedList<String> topics = new DoubleLinkedList<>();
    System.out.println("Topics before adding: " + topics);
    topics.add("A");
    topics.add("C");
    topics.add(1, "B");
    topics.add(0, "Start");
    System.out.println("Topics after adding: " + topics);

    System.out.println("First topic: " + topics.get(0));
    System.out.println("Number of topics: " + topics.size());
    System.out.println("Contains B: " + topics.contains("B"));

    System.out.println("Topic at index 1 before set: " + topics.set(1, "Updated"));
    System.out.println("Topics after set: " + topics);

    System.out.println("Removed first topic: " + topics.remove(0));
    System.out.println("Removed last topic: " + topics.remove(topics.size() - 1));
    System.out.println("Topics after removing ends: " + topics);

    topics.clear();
    System.out.println("Topics after clear: " + topics);
  }
}