package datastructures.arrays.cases;

import datastructures.arrays.MyArrayList;

public final class MyArrayListCase {
  private MyArrayListCase() {
  }

  public static void run() {
    MyArrayList<String> topics = new MyArrayList<>(2);
    System.out.println("Topics before adding: " + topics);
    topics.add("Arrays");
    topics.add("Linked lists");
    System.out.println("Topics after adding: " + topics);
    topics.add(1, "Stacks");
    System.out.println("Topics after adding with index 1: " + topics);

    System.out.println("First topic: " + topics.get(0));
    System.out.println("Number of topics: " + topics.size());
    System.out.println("Contains stacks: " + topics.contains("Stacks"));

    System.out.println("Topic at index 1 before set: " + topics.set(1, "Set value"));
    System.out.println("Topic at index 1 after set: " + topics.get(1));

    topics.remove(2);
    System.out.println("Number of topics after removal: " + topics.size());
    System.out.println("Last topic after removal: " + topics.get(topics.size() - 1));
    System.out.println("Topics: " + topics);

    topics.clear();
    System.out.println("Topics after clear: " + topics);
  }
}