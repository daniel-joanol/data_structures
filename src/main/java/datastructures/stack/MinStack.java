package datastructures.stack;

import java.util.NoSuchElementException;
import java.util.Objects;

public final class MinStack<T extends Comparable<? super T>> {
  private Node<T> top;
  private int size;

  public void push(T element) {
    Objects.requireNonNull(element, "element");
    T minimum = top == null || element.compareTo(top.minimum) < 0
        ? element
        : top.minimum;
    top = new Node<>(element, minimum, top);
    size++;
  }

  public T pop() {
    checkNotEmpty();
    T element = top.element;
    Node<T> removedNode = top;
    top = top.next;
    removedNode.element = null;
    removedNode.minimum = null;
    removedNode.next = null;
    size--;
    return element;
  }

  public T peek() {
    checkNotEmpty();
    return top.element;
  }

  public T getMin() {
    checkNotEmpty();
    return top.minimum;
  }

  public int size() {
    return size;
  }

  public boolean isEmpty() {
    return size == 0;
  }

  public void clear() {
    Node<T> node = top;

    while (node != null) {
      Node<T> nextNode = node.next;
      node.element = null;
      node.minimum = null;
      node.next = null;
      node = nextNode;
    }

    top = null;
    size = 0;
  }

  @Override
  public String toString() {
    StringBuilder result = new StringBuilder("[");

    for (Node<T> node = top; node != null; node = node.next) {
      if (node != top) {
        result.append(", ");
      }

      result.append(node.element);
    }

    return result.append(']').toString();
  }

  private void checkNotEmpty() {
    if (isEmpty()) {
      throw new NoSuchElementException("Stack is empty");
    }
  }

  private static final class Node<T> {
    private T element;
    private T minimum;
    private Node<T> next;

    private Node(T element, T minimum, Node<T> next) {
      this.element = element;
      this.minimum = minimum;
      this.next = next;
    }
  }
}