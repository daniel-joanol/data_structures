package datastructures.stack.cases;

import datastructures.stack.MinStack;

public final class MinStackCase {
  private MinStackCase() {
  }

  public static void run() {
    MinStack<Integer> values = new MinStack<>();
    values.push(5);
    values.push(2);
    values.push(2);
    values.push(8);

    System.out.println("Stack (top first): " + values);
    System.out.println("Minimum: " + values.getMin());
    values.pop();
    values.pop();
    System.out.println("After removing one minimum: " + values.getMin());
    values.pop();
    System.out.println("After removing both minima: " + values.getMin());
  }
}