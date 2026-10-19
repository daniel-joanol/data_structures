package datastructures.queue;

import java.util.Arrays;
import java.util.NoSuchElementException;

public final class CircularQueue<T> {
  private final Object[] elements;
  private int head;
  private int size;

  public CircularQueue(int capacity) {
    if (capacity <= 0) {
      throw new IllegalArgumentException("Capacity must be positive");
    }

    elements = new Object[capacity];
  }

  public void enqueue(T element) {
    if (isFull()) {
      throw new IllegalStateException("Queue is full");
    }

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

  public int capacity() {
    return elements.length;
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

  private void checkNotEmpty() {
    if (isEmpty()) {
      throw new NoSuchElementException("Queue is empty");
    }
  }
}
