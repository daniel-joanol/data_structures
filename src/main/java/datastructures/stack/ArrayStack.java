package datastructures.stack;

import java.util.Arrays;
import java.util.NoSuchElementException;

public final class ArrayStack<T> {
  private static final int DEFAULT_CAPACITY = 10;

  private Object[] elements;
  private int size;

  public ArrayStack() {
    this(DEFAULT_CAPACITY);
  }

  public ArrayStack(int initialCapacity) {
    if (initialCapacity < 0) {
      throw new IllegalArgumentException("Initial capacity cannot be negative");
    }

    elements = new Object[initialCapacity];
  }

  public void push(T element) {
    ensureCapacity(size + 1);
    elements[size++] = element;
  }

  @SuppressWarnings("unchecked")
  public T pop() {
    checkNotEmpty();
    T element = (T) elements[--size];
    elements[size] = null;
    return element;
  }

  @SuppressWarnings("unchecked")
  public T peek() {
    checkNotEmpty();
    return (T) elements[size - 1];
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

  private void ensureCapacity(int requiredCapacity) {
    if (requiredCapacity <= elements.length) {
      return;
    }

    int newCapacity = Math.max(requiredCapacity, Math.max(DEFAULT_CAPACITY, elements.length * 2));
    elements = Arrays.copyOf(elements, newCapacity);
  }

  private void checkNotEmpty() {
    if (isEmpty()) {
      throw new NoSuchElementException("Stack is empty");
    }
  }
}