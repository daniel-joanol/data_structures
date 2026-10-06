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

## Use Case: Undo History

An editor can keep actions on a stack as they happen. Undo removes the latest action first, which is exactly LIFO order. The same structure is useful for browser history, expression parsing, and tracking nested function calls.

The runnable demonstration is `datastructures.stack.cases.ArrayStackCase`:

```java
ArrayStack<String> actions = new ArrayStack<>();
actions.push("Type title");
actions.push("Insert image");
System.out.println(actions.pop()); // Insert image
System.out.println(actions.peek()); // Type title
```
