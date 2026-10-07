# Queues

A queue stores elements in first-in, first-out (FIFO) order: the oldest element is the first one removed. `ArrayQueue<T>` uses a resizable circular array.

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