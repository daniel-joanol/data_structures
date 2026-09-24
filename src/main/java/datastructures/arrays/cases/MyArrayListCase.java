package datastructures.arrays.cases;

import datastructures.arrays.MyArrayList;

public final class MyArrayListCase {
  private MyArrayListCase() {
  }

  public static void run() {
    MyArrayList<String> topics = new MyArrayList<>(2);
    System.out.println("Topics before adding: " + topics);
    topics.add("A");
    topics.add("B");
    System.out.println("Topics after adding: " + topics);
    topics.add(1, "C");
    System.out.println("Topics after adding C with index 1: " + topics);

    System.out.println("First topic: " + topics.get(0));
    System.out.println("Number of topics: " + topics.size());
    System.out.println("Contains C: " + topics.contains("C"));

    System.out.println("Topic at index 1 before set: " + topics.set(1, "D"));
    System.out.println("Topic at index 1 after set: " + topics.get(1));

    topics.remove(2);
    System.out.println("Number of topics after removing index 2: " + topics.size());
    System.out.println("Last topic after removal: " + topics.get(topics.size() - 1));
    System.out.println("Topics: " + topics);

    topics.clear();
    System.out.println("Topics after clear: " + topics);
  }
}