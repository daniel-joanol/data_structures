package datastructures.linkedlist;

import java.util.Objects;

public final class CircularLinkedList<T> {
  private Node<T> head;
  private Node<T> tail;
  private int size;

  public void add(T element) {
    Node<T> newNode = new Node<>(element);

    if (tail == null) {
      head = newNode;
      tail = newNode;
      newNode.next = newNode;
    } else {
      newNode.next = head;
      tail.next = newNode;
      tail = newNode;
    }

    size++;
  }

  public void add(int index, T element) {
    checkIndexForAdd(index);

    if (index == size) {
      add(element);
      return;
    }

    Node<T> newNode = new Node<>(element);

    if (index == 0) {
      newNode.next = head;
      head = newNode;
      tail.next = head;
    } else {
      Node<T> previousNode = nodeAt(index - 1);
      newNode.next = previousNode.next;
      previousNode.next = newNode;
    }

    size++;
  }

  public T get(int index) {
    return nodeAt(index).element;
  }

  public T set(int index, T element) {
    Node<T> node = nodeAt(index);
    T previousElement = node.element;
    node.element = element;
    return previousElement;
  }

  public T remove(int index) {
    checkIndex(index);
    Node<T> removedNode;

    if (size == 1) {
      removedNode = head;
      head = null;
      tail = null;
    } else if (index == 0) {
      removedNode = head;
      head = head.next;
      tail.next = head;
    } else {
      Node<T> previousNode = nodeAt(index - 1);
      removedNode = previousNode.next;
      previousNode.next = removedNode.next;

      if (removedNode == tail) {
        tail = previousNode;
      }
    }

    size--;
    removedNode.next = null;
    return removedNode.element;
  }

  public boolean contains(T element) {
    return indexOf(element) >= 0;
  }

  public int indexOf(T element) {
    Node<T> node = head;

    for (int index = 0; index < size; index++) {
      if (Objects.equals(node.element, element)) {
        return index;
      }

      node = node.next;
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
    if (tail != null) {
      tail.next = null;
    }

    Node<T> node = head;

    while (node != null) {
      Node<T> nextNode = node.next;
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
    Node<T> node = head;

    for (int index = 0; index < size; index++) {
      if (index > 0) {
        result.append(", ");
      }

      result.append(node.element);
      node = node.next;
    }

    return result.append(']').toString();
  }

  private Node<T> nodeAt(int index) {
    checkIndex(index);
    Node<T> node = head;

    for (int currentIndex = 0; currentIndex < index; currentIndex++) {
      node = node.next;
    }

    return node;
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

  private static final class Node<T> {
    private T element;
    private Node<T> next;

    private Node(T element) {
      this.element = element;
    }
  }
}