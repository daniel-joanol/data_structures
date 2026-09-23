package datastructures.arrays;

public final class ArrayCase {
  private ArrayCase() {
  }

  public static void run() {
    MyArrayList<String> topics = new MyArrayList<>(2);
    topics.add("Arrays");
    topics.add("Linked lists");
    topics.add(1, "Stacks");

    System.out.println("First topic: " + topics.get(0));
    System.out.println("Number of topics: " + topics.size());
    System.out.println("Contains stacks: " + topics.contains("Stacks"));
  }
}