package datastructures.arrays.cases;

import datastructures.arrays.MySparseMatrix;

public final class MySparseMatrixCase {
  private MySparseMatrixCase() {
  }

  public static void run() {
    MySparseMatrix<Integer> matrix = new MySparseMatrix<>(3, 4, 0);
    System.out.println("Matrix before adding: " + matrix);

    matrix.set(0, 3, 5);
    matrix.set(2, 0, 2);
    System.out.println("Matrix after adding values: " + matrix);
    System.out.println("Value at (0, 3): " + matrix.get(0, 3));
    System.out.println("Value at (1, 1): " + matrix.get(1, 1));
    System.out.println("Stored values: " + matrix.size());

    System.out.println("Previous value at (0, 3): " + matrix.set(0, 3, 7));
    System.out.println("Matrix after updating (0, 3): " + matrix);

    matrix.set(2, 0, 0);
    System.out.println("Matrix after resetting (2, 0): " + matrix);

    matrix.clear();
    System.out.println("Matrix after clear: " + matrix);
  }
}