package datastructures.stack;

import java.util.NoSuchElementException;

public final class LinkedStack<T> {
  private Node<T> top;
  private int size;

  public void push(T element) {
    Node<T> newNode = new Node<>(element);
    newNode.next = top;
    top = newNode;
    size++;
  }

  public T pop() {
    checkNotEmpty();
    T element = top.element;
    Node<T> removedNode = top;
    top = top.next;
    removedNode.element = null;
    removedNode.next = null;
    size--;
    return element;
  }

  public T peek() {
    checkNotEmpty();
    return top.element;
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
    private Node<T> next;

    private Node(T element) {
      this.element = element;
    }
  }
}