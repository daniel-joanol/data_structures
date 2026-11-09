package datastructures.queue;

import java.util.Arrays;
import java.util.NoSuchElementException;

public final class Deque<T> {
  private static final int DEFAULT_CAPACITY = 8;

  private Object[] elements;
  private int head;
  private int size;

  public Deque() {
    this(DEFAULT_CAPACITY);
  }

  public Deque(int initialCapacity) {
    if (initialCapacity <= 0) {
      throw new IllegalArgumentException("Capacity must be positive");
    }

    elements = new Object[initialCapacity];
  }

  public void addFirst(T element) {
    ensureCapacity();
    head = (head - 1 + elements.length) % elements.length;
    elements[head] = element;
    size++;
  }

  public void addLast(T element) {
    ensureCapacity();
    elements[(head + size) % elements.length] = element;
    size++;
  }

  @SuppressWarnings("unchecked")
  public T removeFirst() {
    checkNotEmpty();
    T element = (T) elements[head];
    elements[head] = null;
    head = (head + 1) % elements.length;
    size--;
    return element;
  }

  @SuppressWarnings("unchecked")
  public T removeLast() {
    checkNotEmpty();
    int last = (head + size - 1) % elements.length;
    T element = (T) elements[last];
    elements[last] = null;
    size--;
    return element;
  }

  @SuppressWarnings("unchecked")
  public T peekFirst() {
    checkNotEmpty();
    return (T) elements[head];
  }

  @SuppressWarnings("unchecked")
  public T peekLast() {
    checkNotEmpty();
    return (T) elements[(head + size - 1) % elements.length];
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

  private void ensureCapacity() {
    if (size < elements.length) {
      return;
    }

    Object[] grown = new Object[elements.length * 2];
    for (int index = 0; index < size; index++) {
      grown[index] = elements[(head + index) % elements.length];
    }
    elements = grown;
    head = 0;
  }

  private void checkNotEmpty() {
    if (isEmpty()) {
      throw new NoSuchElementException("Deque is empty");
    }
  }
}
