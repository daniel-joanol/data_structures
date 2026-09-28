package datastructures.linkedlist;

import java.util.Objects;

public final class SinglyLinkedList<T> {
  private Node<T> head;
  private Node<T> tail;
  private int size;

  public void add(T element) {
    Node<T> newNode = new Node<>(element);

    if (tail == null) {
      head = newNode;
    } else {
      tail.next = newNode;
    }

    tail = newNode;
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

    if (index == 0) {
      removedNode = head;
      head = head.next;
    } else {
      Node<T> previousNode = nodeAt(index - 1);
      removedNode = previousNode.next;
      previousNode.next = removedNode.next;
    }

    if (removedNode == tail) {
      tail = index == 0 ? null : nodeAt(index - 1);
    }

    size--;
    removedNode.next = null;
    return removedNode.element;
  }

  public boolean contains(T element) {
    return indexOf(element) >= 0;
  }

  public int indexOf(T element) {
    int index = 0;

    for (Node<T> node = head; node != null; node = node.next) {
      if (Objects.equals(node.element, element)) {
        return index;
      }

      index++;
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

    for (Node<T> node = head; node != null; node = node.next) {
      if (node != head) {
        result.append(", ");
      }

      result.append(node.element);
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