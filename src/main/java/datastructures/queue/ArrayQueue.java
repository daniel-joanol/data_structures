package datastructures.queue;

import java.util.Arrays;
import java.util.NoSuchElementException;

public final class ArrayQueue<T> {
  private static final int DEFAULT_CAPACITY = 10;

  private Object[] elements;
  private int head;
  private int size;

  public ArrayQueue() {
    this(DEFAULT_CAPACITY);
  }

  public ArrayQueue(int initialCapacity) {
    if (initialCapacity < 0) {
      throw new IllegalArgumentException("Initial capacity cannot be negative");
    }

    elements = new Object[initialCapacity];
  }

  public void enqueue(T element) {
    ensureCapacity(size + 1);
    elements[(head + size) % elements.length] = element;
    size++;
  }

  @SuppressWarnings("unchecked")
  public T dequeue() {
    checkNotEmpty();
    T element = (T) elements[head];
    elements[head] = null;
    head = (head + 1) % elements.length;
    size--;
    return element;
  }

  @SuppressWarnings("unchecked")
  public T peek() {
    checkNotEmpty();
    return (T) elements[head];
  }

  public int size() {
    return size;
  }

  public boolean isEmpty() {
    return size == 0;
  }

  public void clear() {
    Arrays.fill(elements, null);
    head = 0;
    size = 0;
  }

  @Override
  public String toString() {
    Object[] values = new Object[size];
    for (int index = 0; index < size; index++) {
      values[index] = elements[(head + index) % elements.length];
    }
    return Arrays.toString(values);
  }

  private void ensureCapacity(int requiredCapacity) {
    if (requiredCapacity <= elements.length) {
      return;
    }

    int newCapacity = Math.max(requiredCapacity, Math.max(DEFAULT_CAPACITY, elements.length * 2));
    Object[] expanded = new Object[newCapacity];
    for (int index = 0; index < size; index++) {
      expanded[index] = elements[(head + index) % elements.length];
    }
    elements = expanded;
    head = 0;
  }

  private void checkNotEmpty() {
    if (isEmpty()) {
      throw new NoSuchElementException("Queue is empty");
    }
  }
}