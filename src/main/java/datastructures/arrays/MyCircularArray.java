package datastructures.arrays;

import java.util.Arrays;
import java.util.Objects;

public final class MyCircularArray<T> {
  private final Object[] elements;
  private int head;
  private int tail;
  private int size;

  public MyCircularArray(int capacity) {
    if (capacity <= 0) {
      throw new IllegalArgumentException("Capacity must be greater than zero");
    }

    elements = new Object[capacity];
  }

  public void add(T element) {
    if (size == elements.length) {
      throw new IllegalStateException("Circular array is full");
    }

    elements[tail] = element;
    tail = nextIndex(tail);
    size++;
  }

  @SuppressWarnings("unchecked")
  public T remove() {
    if (isEmpty()) {
      throw new IllegalStateException("Circular array is empty");
    }

    T removedElement = (T) elements[head];
    elements[head] = null;
    head = nextIndex(head);
    size--;
    return removedElement;
  }

  @SuppressWarnings("unchecked")
  public T peek() {
    if (isEmpty()) {
      throw new IllegalStateException("Circular array is empty");
    }

    return (T) elements[head];
  }

  @SuppressWarnings("unchecked")
  public T get(int index) {
    checkIndex(index);
    return (T) elements[(head + index) % elements.length];
  }

  public boolean contains(T element) {
    for (int index = 0; index < size; index++) {
      if (Objects.equals(get(index), element)) {
        return true;
      }
    }

    return false;
  }

  public int size() {
    return size;
  }

  public boolean isEmpty() {
    return size == 0;
  }

  public boolean isFull() {
    return size == elements.length;
  }

  public void clear() {
    Arrays.fill(elements, null);
    head = 0;
    tail = 0;
    size = 0;
  }

  @Override
  public String toString() {
    Object[] values = new Object[size];

    for (int index = 0; index < size; index++) {
      values[index] = get(index);
    }

    return String.format("%s (head: %d, tail: %d)", Arrays.toString(values), head, tail);
  }

  private int nextIndex(int index) {
    return (index + 1) % elements.length;
  }

  private void checkIndex(int index) {
    if (index < 0 || index >= size) {
      throw new IndexOutOfBoundsException("Index: " + index + ", size: " + size);
    }
  }
}