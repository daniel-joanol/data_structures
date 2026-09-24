package datastructures.arrays.cases;

import datastructures.arrays.MySortedArray;

public final class MySortedArrayCase {
  private MySortedArrayCase() {
  }

  public static void run() {
    MySortedArray<Integer> numbers = new MySortedArray<>(2);
    System.out.println("Numbers before adding: " + numbers);

    numbers.add(30);
    numbers.add(10);
    numbers.add(20);
    numbers.add(20);
    System.out.println("Numbers after adding out of order: " + numbers);

    System.out.println("First number: " + numbers.get(0));
    System.out.println("Number of values: " + numbers.size());
    System.out.println("Removed number from index 1: " + numbers.remove(1));
    System.out.println("Numbers after removal: " + numbers);

    numbers.clear();
    System.out.println("Numbers after clear: " + numbers);
  }
}