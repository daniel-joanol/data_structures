package datastructures.arrays;

import java.util.Arrays;
import java.util.Objects;

public final class MyArrayList<T> {
  private static final int DEFAULT_CAPACITY = 10;

  private Object[] elements;
  private int size;

  public MyArrayList() {
    this(DEFAULT_CAPACITY);
  }

  public MyArrayList(int initialCapacity) {
    if (initialCapacity < 0) {
      throw new IllegalArgumentException("Initial capacity cannot be negative");
    }

    elements = new Object[initialCapacity];
  }

  public void add(T element) {
    ensureCapacity(size + 1);
    elements[size++] = element;
  }

  public void add(int index, T element) {
    checkIndexForAdd(index);
    ensureCapacity(size + 1);
    System.arraycopy(elements, index, elements, index + 1, size - index);
    elements[index] = element;
    size++;
  }

  @SuppressWarnings("unchecked")
  public T get(int index) {
    checkIndex(index);
    return (T) elements[index];
  }

  @SuppressWarnings("unchecked")
  public T set(int index, T element) {
    checkIndex(index);
    T previousElement = (T) elements[index];
    elements[index] = element;
    return previousElement;
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

  public boolean contains(T element) {
    return indexOf(element) >= 0;
  }

  public int indexOf(T element) {
    for (int index = 0; index < size; index++) {
      if (Objects.equals(elements[index], element)) {
        return index;
      }
    }

    return -1;
  }

  public int size() {
    return size;
  }

  public boolean isEmpty() {
    return size == 0;
  }

  public void clear() {
    for (int index = 0; index < size; index++) {
      elements[index] = null;
    }

    size = 0;
  }

  @Override
  public String toString() {
    return Arrays.toString(Arrays.copyOf(elements, size));
  }

  private void ensureCapacity(int requiredCapacity) {
    if (requiredCapacity <= elements.length) {
      return;
    }

    int newCapacity = Math.max(requiredCapacity, Math.max(DEFAULT_CAPACITY, elements.length * 2));
    Object[] expandedElements = new Object[newCapacity];
    System.arraycopy(elements, 0, expandedElements, 0, size);
    elements = expandedElements;
  }

  private void checkIndex(int index) {
    if (index < 0 || index >= size) {
      throw new IndexOutOfBoundsException("Index: " + index + ", size: " + size);
    }
  }

  private void checkIndexForAdd(int index) {
    if (index < 0 || index > size) {
      throw new IndexOutOfBoundsException("Index: " + index + ", size: " + size);
    }
  }
}