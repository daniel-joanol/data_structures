package datastructures.stack.cases;

import datastructures.stack.LinkedStack;

public final class LinkedStackCase {
  private LinkedStackCase() {
  }

  public static void run() {
    LinkedStack<String> undoHistory = new LinkedStack<>();
    undoHistory.push("Type title");
    undoHistory.push("Insert image");
    undoHistory.push("Apply bold formatting");

    System.out.println("Undo history (top first): " + undoHistory);
    System.out.println("Next action to undo: " + undoHistory.peek());
    System.out.println("Undoing: " + undoHistory.pop());
    System.out.println("Undoing: " + undoHistory.pop());
    System.out.println("Remaining history: " + undoHistory);
    System.out.println("Remaining actions: " + undoHistory.size());
  }
}