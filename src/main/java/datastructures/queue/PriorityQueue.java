package datastructures.queue;

import java.util.Arrays;
import java.util.Comparator;
import java.util.NoSuchElementException;
import java.util.Objects;

public final class PriorityQueue<T> {
  private static final int DEFAULT_CAPACITY = 10;

  private final Comparator<? super T> comparator;
  private Object[] heap;
  private int size;

  /** Creates a min-priority queue using the natural ordering of elements. */
  public PriorityQueue() {
    this(DEFAULT_CAPACITY, null);
  }

  public PriorityQueue(Comparator<? super T> comparator) {
    this(DEFAULT_CAPACITY, comparator);
  }

  public PriorityQueue(int initialCapacity, Comparator<? super T> comparator) {
    if (initialCapacity < 1) {
      throw new IllegalArgumentException("Initial capacity must be at least 1");
    }

    this.comparator = comparator;
    heap = new Object[initialCapacity];
  }

  public void enqueue(T element) {
    Objects.requireNonNull(element, "Element cannot be null");
    if (size == heap.length) {
      heap = Arrays.copyOf(heap, heap.length * 2);
    }

    heap[size] = element;
    siftUp(size);
    size++;
  }

  @SuppressWarnings("unchecked")
  public T dequeue() {
    checkNotEmpty();
    T top = (T) heap[0];
    size--;
    heap[0] = heap[size];
    heap[size] = null;
    if (size > 0) {
      siftDown(0);
    }
    return top;
  }

  @SuppressWarnings("unchecked")
  public T peek() {
    checkNotEmpty();
    return (T) heap[0];
  }

  public int size() {
    return size;
  }

  public boolean isEmpty() {
    return size == 0;
  }

  public void clear() {
    Arrays.fill(heap, 0, size, null);
    size = 0;
  }

  /** Returns elements in heap order, not sorted order. */
  @Override
  public String toString() {
    return Arrays.toString(Arrays.copyOf(heap, size));
  }

  private void siftUp(int index) {
    while (index > 0) {
      int parent = (index - 1) / 2;
      if (compare(index, parent) >= 0) {
        return;
      }
      swap(index, parent);
      index = parent;
    }
  }

  private void siftDown(int index) {
    while (true) {
      int left = 2 * index + 1;
      int right = left + 1;
      int smallest = index;

      if (left < size && compare(left, smallest) < 0) {
        smallest = left;
      }
      if (right < size && compare(right, smallest) < 0) {
        smallest = right;
      }
      if (smallest == index) {
        return;
      }

      swap(index, smallest);
      index = smallest;
    }
  }

  @SuppressWarnings("unchecked")
  private int compare(int first, int second) {
    T a = (T) heap[first];
    T b = (T) heap[second];
    if (comparator != null) {
      return comparator.compare(a, b);
    }
    return ((Comparable<? super T>) a).compareTo(b);
  }

  private void swap(int first, int second) {
    Object temp = heap[first];
    heap[first] = heap[second];
    heap[second] = temp;
  }

  private void checkNotEmpty() {
    if (isEmpty()) {
      throw new NoSuchElementException("Queue is empty");
    }
  }
}
