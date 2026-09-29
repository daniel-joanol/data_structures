package datastructures.linkedlist;

import java.util.Objects;

public final class DoubleLinkedList<T> {
  private Node<T> head;
  private Node<T> tail;
  private int size;

  public void add(T element) {
    Node<T> newNode = new Node<>(element);

    if (tail == null) {
      head = newNode;
    } else {
      tail.next = newNode;
      newNode.previous = tail;
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

    Node<T> nextNode = nodeAt(index);
    Node<T> newNode = new Node<>(element);
    newNode.next = nextNode;
    newNode.previous = nextNode.previous;

    if (nextNode.previous == null) {
      head = newNode;
    } else {
      nextNode.previous.next = newNode;
    }

    nextNode.previous = newNode;
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
    Node<T> removedNode = nodeAt(index);
    Node<T> previousNode = removedNode.previous;
    Node<T> nextNode = removedNode.next;

    if (previousNode == null) {
      head = nextNode;
    } else {
      previousNode.next = nextNode;
    }

    if (nextNode == null) {
      tail = previousNode;
    } else {
      nextNode.previous = previousNode;
    }

    size--;
    removedNode.previous = null;
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
      node.previous = null;
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

    if (index < size / 2) {
      Node<T> node = head;

      for (int currentIndex = 0; currentIndex < index; currentIndex++) {
        node = node.next;
      }

      return node;
    }

    Node<T> node = tail;

    for (int currentIndex = size - 1; currentIndex > index; currentIndex--) {
      node = node.previous;
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
    private Node<T> previous;
    private Node<T> next;

    private Node(T element) {
      this.element = element;
    }
  }
}