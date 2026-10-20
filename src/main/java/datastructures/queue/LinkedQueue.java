package datastructures.queue;

import java.util.NoSuchElementException;

public final class LinkedQueue<T> {
  private Node<T> head;
  private Node<T> tail;
  private int size;

  public void enqueue(T element) {
    Node<T> newNode = new Node<>(element);

    if (tail == null) {
      head = newNode;
    } else {
      tail.next = newNode;
    }

    tail = newNode;
    size++;
  }

  public T dequeue() {
    checkNotEmpty();
    Node<T> removedNode = head;
    T element = removedNode.element;
    head = head.next;

    if (head == null) {
      tail = null;
    }

    removedNode.element = null;
    removedNode.next = null;
    size--;
    return element;
  }

  public T peek() {
    checkNotEmpty();
    return head.element;
  }

  public int size() {
    return size;
  }

  public boolean isEmpty() {
    return size == 0;
  }

  public void clear() {
    Node<T> node = head;

    while (node != null) {
      Node<T> nextNode = node.next;
      node.element = null;
      node.next = null;
      node = nextNode;
    }

    head = null;
    tail = null;
    size = 0;
  }

  @Override
  public String toString() {
    StringBuilder result = new StringBuilder("[");

    for (Node<T> node = head; node != null; node = node.next) {
      if (node != head) {
        result.append(", ");
      }

      result.append(node.element);
    }

    return result.append(']').toString();
  }

  private void checkNotEmpty() {
    if (isEmpty()) {
      throw new NoSuchElementException("Queue is empty");
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
