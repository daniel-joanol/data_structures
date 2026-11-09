# Queues

A queue stores elements in first-in, first-out (FIFO) order: the oldest element is the first one removed. `ArrayQueue<T>` uses a resizable circular array, `CircularQueue<T>` uses a fixed-capacity ring buffer, `LinkedQueue<T>` uses a singly linked list, and `Deque<T>` is a double-ended queue backed by a resizable circular array. `PriorityQueue<T>` is the exception to FIFO order: it removes the element with the highest priority first.

## ArrayQueue

- `enqueue(element)` adds an element to the back in amortized $O(1)$ time. An occasional resize takes $O(n)$.
- `dequeue()` removes and returns the front element in $O(1)$ time.
- `peek()` returns the front element without removing it in $O(1)$ time.
- `size()`, `isEmpty()`, and `clear()` report or reset the queue state.

The queue starts with a default capacity of 10, or a capacity supplied to its constructor. When it fills, its backing array grows while preserving FIFO order. A negative initial capacity throws `IllegalArgumentException`. Calling `dequeue()` or `peek()` on an empty queue throws `NoSuchElementException`.

### When It Is Used

A printer can use an `ArrayQueue` to process submitted jobs in arrival order. Enqueue each job as it arrives, then dequeue the next job to print.

```java
ArrayQueue<String> printJobs = new ArrayQueue<>();
printJobs.enqueue("Report");
printJobs.enqueue("Invoice");

System.out.println(printJobs.dequeue()); // Report
System.out.println(printJobs.peek()); // Invoice
```

### Pros

- Enqueue and dequeue are fast; the common operations take $O(1)$ time.
- Array storage is compact and cache-friendly, without a separate node per element.
- It grows automatically, so callers do not need to choose a fixed maximum size.

### Cons

- Growing the backing array occasionally copies its elements, making an individual `enqueue` take $O(n)$ time.
- Spare capacity can use more memory than the current number of elements requires.

See `datastructures.queue.cases.ArrayQueueCase` for a runnable example.

## CircularQueue

- `enqueue(element)` adds an element to the back in $O(1)$ time.
- `dequeue()` removes and returns the front element in $O(1)$ time.
- `peek()` returns the front element without removing it in $O(1)$ time.
- `size()`, `capacity()`, `isEmpty()`, `isFull()`, and `clear()` report or reset the queue state.

The capacity is fixed at construction and must be positive, otherwise `IllegalArgumentException` is thrown. The head index and the size determine where the next element goes, so positions wrap around to the start of the array and freed slots are reused. Calling `enqueue()` on a full queue throws `IllegalStateException`; calling `dequeue()` or `peek()` on an empty queue throws `NoSuchElementException`.

### When It Is Used

A fixed-size buffer, such as a stream of sensor readings or a bounded task backlog, can use a `CircularQueue` so memory use stays constant.

```java
CircularQueue<String> buffer = new CircularQueue<>(3);
buffer.enqueue("A");
buffer.enqueue("B");
buffer.enqueue("C");

System.out.println(buffer.dequeue()); // A
buffer.enqueue("D"); // reuses the freed slot
System.out.println(buffer.peek()); // B
```

### Pros

- Every operation takes $O(1)$ time, with no resizing.
- Memory use is fixed and predictable.

### Cons

- The capacity cannot change, so `enqueue` fails when the queue is full.

See `datastructures.queue.cases.CircularQueueCase` for a runnable example.

## LinkedQueue

- `enqueue(element)` adds an element to the back in $O(1)$ time.
- `dequeue()` removes and returns the front element in $O(1)$ time.
- `peek()` returns the front element without removing it in $O(1)$ time.
- `size()`, `isEmpty()`, and `clear()` report or reset the queue state.

The queue keeps references to both the head and the tail of a singly linked list, so adding to the back and removing from the front never shift or copy elements. Calling `dequeue()` or `peek()` on an empty queue throws `NoSuchElementException`.

### When It Is Used

A customer service line can use a `LinkedQueue` when the number of waiting customers is unpredictable and has no fixed upper bound.

```java
LinkedQueue<String> customers = new LinkedQueue<>();
customers.enqueue("Alice");
customers.enqueue("Bob");

System.out.println(customers.dequeue()); // Alice
System.out.println(customers.peek()); // Bob
```

### Pros

- Every operation takes $O(1)$ time, with no resizing or copying.
- It grows one node at a time, so no capacity needs to be chosen.

### Cons

- Each element needs a separate node, which uses more memory and is less cache-friendly than an array.

See `datastructures.queue.cases.LinkedQueueCase` for a runnable example.

## Deque

- `addFirst(element)` and `addLast(element)` add an element to the front or back in amortized $O(1)$ time. An occasional resize takes $O(n)$.
- `removeFirst()` and `removeLast()` remove and return the front or back element in $O(1)$ time.
- `peekFirst()` and `peekLast()` return the front or back element without removing it in $O(1)$ time.
- `size()`, `isEmpty()`, and `clear()` report or reset the deque state.

The deque keeps a head index into a circular array, so inserting or removing at either end wraps around instead of shifting elements. The default capacity is 8, or a positive capacity supplied to its constructor, otherwise `IllegalArgumentException` is thrown. When it fills, the backing array doubles. Calling a remove or peek method on an empty deque throws `NoSuchElementException`.

### When It Is Used

An undo history that also discards its oldest entries can use a `Deque`: push new actions at the back, undo from the back, and drop old actions from the front.

```java
Deque<String> history = new Deque<>();
history.addLast("Type");
history.addLast("Delete");
history.addFirst("Open");

System.out.println(history.removeLast()); // Delete
System.out.println(history.peekFirst()); // Open
```

### Pros

- Both ends support $O(1)$ insertion and removal, so it works as a queue or a stack.
- Array storage is compact and cache-friendly.

### Cons

- Growing the backing array occasionally copies its elements, making an individual add take $O(n)$ time.
- Access to the middle is not supported.

See `datastructures.queue.cases.DequeCase` for a runnable example.

## PriorityQueue

- `enqueue(element)` adds an element in $O(\log n)$ time.
- `dequeue()` removes and returns the highest-priority element in $O(\log n)$ time.
- `peek()` returns the highest-priority element without removing it in $O(1)$ time.
- `size()`, `isEmpty()`, and `clear()` report or reset the queue state.

The queue is a binary min-heap stored in an array. The children of index $i$ are at $2i + 1$ and $2i + 2$, and its parent is at $\lfloor (i - 1) / 2 \rfloor$. Adding an element sifts it up toward the root, and removing the root moves the last element there and sifts it down. By default, the smallest element by natural ordering has the highest priority; pass a `Comparator` to change that, for example a reversed comparator for a max-priority queue. The initial capacity must be at least 1 (default 10) and doubles when full, otherwise `IllegalArgumentException` is thrown. `enqueue(null)` throws `NullPointerException`, and calling `dequeue()` or `peek()` on an empty queue throws `NoSuchElementException`. Elements with equal priority are not guaranteed to leave in insertion order, and `toString()` shows heap order, not sorted order.

### When It Is Used

A task scheduler can use a `PriorityQueue` to always run the most urgent task next, regardless of arrival order.

```java
PriorityQueue<Integer> tasks = new PriorityQueue<>();
tasks.enqueue(5);
tasks.enqueue(1);
tasks.enqueue(3);

System.out.println(tasks.peek()); // 1
System.out.println(tasks.dequeue()); // 1
System.out.println(tasks.dequeue()); // 3
```

### Pros

- Finding the highest-priority element is $O(1)$, and insertion and removal stay $O(\log n)$.
- Array storage is compact, without a node per element.

### Cons

- It does not preserve FIFO order, and equal priorities are not stable.
- Searching for or removing an arbitrary element is not supported.

See `datastructures.queue.cases.PriorityQueueCase` for a runnable example.
