# Stacks

A stack stores elements in last-in, first-out (LIFO) order: the most recently added element is the first one removed. `ArrayStack<T>` implements this behavior with a resizable array.

## ArrayStack

- `push(element)` adds an element to the top in amortized $O(1)$ time. An occasional resize takes $O(n)$.
- `pop()` removes and returns the top element in $O(1)$ time.
- `peek()` returns the top element without removing it in $O(1)$ time.
- `size()`, `isEmpty()`, and `clear()` report or reset the stack state.

The stack starts with a default capacity of 10, or a capacity supplied to its constructor. When it fills, its backing array grows to make room. A negative initial capacity throws `IllegalArgumentException`. Calling `pop()` or `peek()` on an empty stack throws `NoSuchElementException`.

### When It Is Used

An editor can use an `ArrayStack` to keep an undo history. Push each edit as it happens, then pop the newest edit to undo it. This works well when the history should grow as needed and compact array storage is useful.

```java
ArrayStack<String> undoHistory = new ArrayStack<>();
undoHistory.push("Type title");
undoHistory.push("Insert image");

System.out.println(undoHistory.pop()); // Insert image
System.out.println(undoHistory.peek()); // Type title
```

### Pros

- `push`, `pop`, and `peek` are fast; the common operations take $O(1)$ time.
- Array storage is compact and cache-friendly, with no separate node object for each element.
- It grows automatically, so callers do not need to choose a fixed maximum size.

### Cons

- Growing the backing array occasionally copies its elements, making an individual `push` take $O(n)$ time.
- Spare capacity can use more memory than the current number of elements requires.
- Resizing can cause a temporary memory spike while both old and new arrays exist.

See `datastructures.stack.cases.ArrayStackCase` for a runnable example.

---

## LinkedStack

`LinkedStack<T>` stores each element in a node that points to the next element. The top of the stack is the head of the chain, so no traversal is needed for stack operations.

- `push(element)` adds an element to the top in $O(1)$ time.
- `pop()` removes and returns the top element in $O(1)$ time.
- `peek()` returns the top element without removing it in $O(1)$ time.
- `size()`, `isEmpty()`, and `clear()` report or reset the stack state.

Calling `pop()` or `peek()` on an empty stack throws `NoSuchElementException`.

### When It Is Used

An editor can keep actions on a stack as they happen. Undo removes the latest action first, which is exactly LIFO order. The runnable linked-stack demonstration is `datastructures.stack.cases.LinkedStackCase`.

The same use case can use either implementation:

```java
LinkedStack<String> actions = new LinkedStack<>();
actions.push("Type title");
actions.push("Insert image");
System.out.println(actions.pop()); // Insert image
System.out.println(actions.peek()); // Type title
```

### Pros

- Push and pop remain $O(1)$ without copying existing elements.
- The stack grows one node at a time, without reserving unused array capacity.

### Cons

- Each element requires a node and reference, increasing memory overhead.
- Nodes are separately allocated, which is generally less cache-friendly than an array.
- Push requires allocating a node, which can add allocation and garbage-collection overhead.

---

## MinStack

`MinStack<T>` is a stack for comparable, non-null values. Each node stores the minimum value at that point in the stack, so the minimum can be read without traversing the elements.

- `push(element)`, `pop()`, `peek()`, and `getMin()` take $O(1)$ time.
- `size()`, `isEmpty()`, and `clear()` report or reset the stack state.
- Popping a minimum restores the minimum recorded by the next node; duplicate minimum values are handled independently.
- Calling `pop()`, `peek()`, or `getMin()` on an empty stack throws `NoSuchElementException`.
- Pushing `null` throws `NullPointerException`.

Each node stores one extra reference for the current minimum, so storage remains $O(n)$.

### When It Is Used

Use a minimum stack when values are added and removed in LIFO order but the current minimum is also needed. For example, a backtracking algorithm can push each active score or cost, inspect the minimum along the current path, and pop values when it backs up. The cached minimum avoids rescanning the path after each change.

```java
MinStack<Integer> pathCosts = new MinStack<>();
pathCosts.push(12);
pathCosts.push(4);
pathCosts.push(9);

System.out.println(pathCosts.getMin()); // 4
pathCosts.pop(); // backtrack one step
pathCosts.pop(); // remove the step that contributed the minimum
System.out.println(pathCosts.getMin()); // 12
```

### Pros

- `getMin()` takes $O(1)$ time without scanning the stack.
- Popping restores the previous minimum automatically, including when the minimum appears more than once.
- Push, pop, peek, and minimum lookup all take $O(1)$ time.

### Cons

- Every node stores an additional reference, increasing memory use compared with a basic linked stack.
- Values must be non-null and implement `Comparable`.
- Removal is limited to the top; use a different structure if values must be removed arbitrarily or other ordered queries are needed.

```java
MinStack<Integer> values = new MinStack<>();
values.push(5);
values.push(2);
values.push(8);

System.out.println(values.getMin()); // 2
values.pop();
System.out.println(values.getMin()); // 2
```

See `datastructures.stack.cases.MinStackCase` for a runnable example.
