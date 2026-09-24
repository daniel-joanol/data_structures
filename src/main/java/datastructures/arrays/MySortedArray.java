package datastructures.arrays;

import java.util.Arrays;

public final class MySortedArray<T extends Comparable<? super T>> {
  private static final int DEFAULT_CAPACITY = 10;

  private Object[] elements;
  private int size;

  public MySortedArray() {
    this(DEFAULT_CAPACITY);
  }

  public MySortedArray(int initialCapacity) {
    if (initialCapacity < 0) {
      throw new IllegalArgumentException("Initial capacity cannot be negative");
    }

    elements = new Object[initialCapacity];
  }

  public void add(T element) {
    int insertionIndex = findInsertionIndex(element);
    ensureCapacity(size + 1);
    System.arraycopy(elements, insertionIndex, elements, insertionIndex + 1, size - insertionIndex);
    elements[insertionIndex] = element;
    size++;
  }

  @SuppressWarnings("unchecked")
  public T get(int index) {
    checkIndex(index);
    return (T) elements[index];
  }

  @SuppressWarnings("unchecked")
  public T remove(int index) {
    checkIndex(index);
    T removedElement = (T) elements[index];
    int elementsToMove = size - index - 1;

    if (elementsToMove > 0) {
      System.arraycopy(elements, index + 1, elements, index, elementsToMove);
    }

    elements[--size] = null;
    return removedElement;
  }

  public int size() {
    return size;
  }

  public boolean isEmpty() {
    return size == 0;
  }

  public void clear() {
    Arrays.fill(elements, 0, size, null);
    size = 0;
  }

  @Override
  public String toString() {
    return Arrays.toString(Arrays.copyOf(elements, size));
  }

  private int findInsertionIndex(T element) {
    int low = 0;
    int high = size;

    // Narrows the search range by half each iteration to find the element's insertion index.
    while (low < high) {
      int middle = (low + high) >>> 1;
      if (get(middle).compareTo(element) <= 0) {
        low = middle + 1;
      } else {
        high = middle;
      }
    }

    return low;
  }

  private void ensureCapacity(int requiredCapacity) {
    if (requiredCapacity <= elements.length) {
      return;
    }

    int newCapacity = Math.max(requiredCapacity, Math.max(DEFAULT_CAPACITY, elements.length * 2));
    elements = Arrays.copyOf(elements, newCapacity);
  }

  private void checkIndex(int index) {
    if (index < 0 || index >= size) {
      throw new IndexOutOfBoundsException("Index: " + index + ", size: " + size);
    }
  }
}