package datastructures;

import datastructures.arrays.cases.ArrayListCase;
import datastructures.arrays.cases.CircularArrayCase;
import datastructures.arrays.cases.SparseMatrixCase;
import datastructures.arrays.cases.SortedArrayCase;
import datastructures.linkedlist.cases.CircularLinkedListCase;
import datastructures.linkedlist.cases.DoubleLinkedListCase;
import datastructures.linkedlist.cases.SinglyLinkedListCase;
import datastructures.queue.cases.ArrayQueueCase;
import datastructures.stack.cases.ArrayStackCase;
import datastructures.stack.cases.LinkedStackCase;
import datastructures.stack.cases.MinStackCase;

public final class App {
  private App() {
  }

  public static void main(String[] args) {
    
    // ARRAYS
    //MyArrayListCase.run();
    //MySortedArrayCase.run();
    //MyCircularArrayCase.run();
    //MySparseMatrixCase.run();

    // LINKED LISTS
    //SinglyLinkedListCase.run();
    //DoubleLinkedListCase.run();
    //CircularLinkedListCase.run();

    // STACKS
    //ArrayStackCase.run();
    //LinkedStackCase.run();
    //MinStackCase.run();

    // QUEUES
    ArrayQueueCase.run();
  }
}