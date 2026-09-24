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

    System.out.println("Topic at index 1 before set: " + topics.set(1, "Set value"));
    System.out.println("Topic at index 1 after set: " + topics.get(1));

    topics.remove(2);
    System.out.println("Number of topics after removal: " + topics.size());
    System.out.println("Last topic after removal: " + topics.get(topics.size() - 1));

    topics.clear();
    System.out.println("Number of topics after clearing: " + topics.size());
  }
}