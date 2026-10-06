# Stacks

A stack stores elements in last-in, first-out (LIFO) order: the most recently added element is the first one removed. `ArrayStack<T>` implements this behavior with a resizable array.

## ArrayStack

- `push(element)` adds an element to the top in amortized $O(1)$ time. An occasional resize takes $O(n)$.
- `pop()` removes and returns the top element in $O(1)$ time.
- `peek()` returns the top element without removing it in $O(1)$ time.
- `size()`, `isEmpty()`, and `clear()` report or reset the stack state.

The stack starts with a default capacity of 10, or a capacity supplied to its constructor. When it fills, its backing array grows to make room. A negative initial capacity throws `IllegalArgumentException`. Calling `pop()` or `peek()` on an empty stack throws `NoSuchElementException`.

## Pros and Cons

**Pros**

- `push`, `pop`, and `peek` are fast; the common operations take $O(1)$ time.
- Array storage is compact and cache-friendly, with no separate node object for each element.
- It grows automatically, so callers do not need to choose a fixed maximum size.

**Cons**

- Growing the backing array occasionally copies its elements, making an individual `push` take $O(n)$ time.
- Spare capacity can use more memory than the current number of elements requires.
- Resizing can cause a temporary memory spike while both old and new arrays exist.

---

## LinkedStack

`LinkedStack<T>` stores each element in a node that points to the next element. The top of the stack is the head of the chain, so no traversal is needed for stack operations.

- `push(element)` adds an element to the top in $O(1)$ time.
- `pop()` removes and returns the top element in $O(1)$ time.
- `peek()` returns the top element without removing it in $O(1)$ time.
- `size()`, `isEmpty()`, and `clear()` report or reset the stack state.

Calling `pop()` or `peek()` on an empty stack throws `NoSuchElementException`.

**Pros**

- Push and pop remain $O(1)$ without copying existing elements.
- The stack grows one node at a time, without reserving unused array capacity.

**Cons**

- Each element requires a node and reference, increasing memory overhead.
- Nodes are separately allocated, which is generally less cache-friendly than an array.
- Push requires allocating a node, which can add allocation and garbage-collection overhead.

## Use Case: Undo History

An editor can keep actions on a stack as they happen. Undo removes the latest action first, which is exactly LIFO order. The runnable linked-stack demonstration is `datastructures.stack.cases.LinkedStackCase`.

The same use case can use either implementation:

```java
LinkedStack<String> actions = new LinkedStack<>();
actions.push("Type title");
actions.push("Insert image");
System.out.println(actions.pop()); // Insert image
System.out.println(actions.peek()); // Type title
```
