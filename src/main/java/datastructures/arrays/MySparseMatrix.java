package datastructures.arrays;

import java.util.Arrays;
import java.util.Objects;

public final class MySparseMatrix<T> {
  private static final int DEFAULT_CAPACITY = 10;

  private final int rows;
  private final int columns;
  private final T defaultValue;
  private Object[] entries;
  private int size;

  public MySparseMatrix(int rows, int columns, T defaultValue) {
    if (rows < 0 || columns < 0) {
      throw new IllegalArgumentException("Matrix dimensions cannot be negative");
    }

    this.rows = rows;
    this.columns = columns;
    this.defaultValue = defaultValue;
    entries = new Object[DEFAULT_CAPACITY];
  }

  public T get(int row, int column) {
    checkCoordinates(row, column);
    int entryIndex = findEntryIndex(row, column);
    return entryIndex >= 0 ? entryAt(entryIndex).value : defaultValue;
  }

  public T set(int row, int column, T value) {
    checkCoordinates(row, column);
    int entryIndex = findEntryIndex(row, column);

    if (entryIndex >= 0) {
      T previousValue = entryAt(entryIndex).value;

      if (Objects.equals(value, defaultValue)) {
        removeEntry(entryIndex);
      } else {
        entryAt(entryIndex).value = value;
      }

      return previousValue;
    }

    if (!Objects.equals(value, defaultValue)) {
      int insertionIndex = -entryIndex - 1;
      ensureCapacity(size + 1);
      System.arraycopy(entries, insertionIndex, entries, insertionIndex + 1, size - insertionIndex);
      entries[insertionIndex] = new Entry<>(row, column, value);
      size++;
    }

    return defaultValue;
  }

  public int rows() {
    return rows;
  }

  public int columns() {
    return columns;
  }

  public int size() {
    return size;
  }

  public boolean isEmpty() {
    return size == 0;
  }

  public void clear() {
    Arrays.fill(entries, 0, size, null);
    size = 0;
  }

  @Override
  public String toString() {
    return Arrays.toString(Arrays.copyOf(entries, size));
  }

  private int findEntryIndex(int row, int column) {
    int low = 0;
    int high = size - 1;

    // Binary-searches ordered coordinates and returns a negative insertion index when none exists.
    while (low <= high) {
      int middle = (low + high) >>> 1;
      Entry<T> entry = entryAt(middle);
      int comparison = compareCoordinates(row, column, entry.row, entry.column);

      if (comparison == 0) {
        return middle;
      }

      if (comparison < 0) {
        high = middle - 1;
      } else {
        low = middle + 1;
      }
    }

    return -low - 1;
  }

  private int compareCoordinates(int firstRow, int firstColumn, int secondRow, int secondColumn) {
    int rowComparison = Integer.compare(firstRow, secondRow);
    return rowComparison != 0 ? rowComparison : Integer.compare(firstColumn, secondColumn);
  }

  private void removeEntry(int entryIndex) {
    int entriesToMove = size - entryIndex - 1;

    if (entriesToMove > 0) {
      System.arraycopy(entries, entryIndex + 1, entries, entryIndex, entriesToMove);
    }

    entries[--size] = null;
  }

  private void ensureCapacity(int requiredCapacity) {
    if (requiredCapacity <= entries.length) {
      return;
    }

    int newCapacity = Math.max(requiredCapacity, entries.length * 2);
    entries = Arrays.copyOf(entries, newCapacity);
  }

  @SuppressWarnings("unchecked")
  private Entry<T> entryAt(int index) {
    return (Entry<T>) entries[index];
  }

  private void checkCoordinates(int row, int column) {
    if (row < 0 || row >= rows || column < 0 || column >= columns) {
      throw new IndexOutOfBoundsException(
        "Row: " + row + ", column: " + column + ", dimensions: " + rows + "x" + columns);
    }
  }

  private static final class Entry<T> {
    private final int row;
    private final int column;
    private T value;

    private Entry(int row, int column, T value) {
      this.row = row;
      this.column = column;
      this.value = value;
    }

    @Override
    public String toString() {
      return "(" + row + ", " + column + ") = " + value;
    }
  }
}