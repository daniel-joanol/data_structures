package datastructures.stack.cases;

import datastructures.stack.ArrayStack;

public final class ArrayStackCase {
  private ArrayStackCase() {
  }

  public static void run() {
    ArrayStack<String> undoHistory = new ArrayStack<>();
    undoHistory.push("Type title");
    undoHistory.push("Insert image");
    undoHistory.push("Apply bold formatting");

    System.out.println("Undo history: " + undoHistory);
    System.out.println("Next action to undo: " + undoHistory.peek());
    System.out.println("Undoing: " + undoHistory.pop());
    System.out.println("Undoing: " + undoHistory.pop());
    System.out.println("Remaining history: " + undoHistory);
    System.out.println("Remaining actions: " + undoHistory.size());
  }
}