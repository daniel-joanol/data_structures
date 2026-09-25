# Array-Based Data Structures

This package contains custom data structures backed by Java arrays. The implementation classes use the `My` prefix, such as `MyArrayList`, to distinguish them from Java standard-library classes.

## ArrayList

An array list is a resizable array. Unlike a fixed-size Java array, it grows automatically when there is no room for another element.

```text
[Arrays, Stacks, Linked lists, null, null]
 size = 3
```

Only the elements before `size` belong to the list. The remaining array slots are unused capacity reserved for later additions.

### How It Works

- Store elements in an internal `Object[]` array and track the number of stored values with `size`.
- Add an element at `elements[size]`, then increase `size`.
- When the array is full, allocate a larger array and copy existing elements into it.
- Insert at an index by shifting later elements right.
- Remove at an index by shifting later elements left and clearing the unused final slot.

### When It Is Used

Use an array list for an ordered collection that needs fast indexed access and occasional growth. It is a good general-purpose list when additions mostly happen at the end.

### Complexity

| Operation | Cost | Why |
| --- | --- | --- |
| `get(index)` | $O(1)$ | Direct array index |
| `set(index, value)` | $O(1)$ | Direct array index |
| Append | $O(1)$ amortized | Resizing happens only occasionally |
| Insert or remove in the middle | $O(n)$ | Later elements must shift |
| `contains(value)` | $O(n)$ | Values are checked one by one |

### Pros

- Fast indexed reads and updates.
- Compact storage with good cache locality.
- Efficient appends on average.
- Simple foundation for array-backed stacks and queues.

### Cons

- Middle insertions and removals require shifting elements.
- Resizing temporarily allocates a new, larger array.
- Unused capacity consumes some memory.

---

## SortedArray

A sorted array keeps its elements in ascending or descending order at all times.

```text
[3, 8, 12, 19, 27]
          + 15
[3, 8, 12, 15, 19, 27]
```

### How It Works

- Use an internal array and `size`, as with an array list.
- Find an element's position with binary search.
- Insert a new value at its sorted position by shifting later elements right.
- Remove a value by finding it, shifting later elements left, and decreasing `size`.
- Use a type that can compare values, such as `T extends Comparable<T>`, or accept a `Comparator<T>`.

### When It Is Used

Use a sorted array when membership checks, ordered iteration, or range queries are common and insertions or removals are relatively infrequent. Examples include sorted scores or a small in-memory index.

### Complexity

| Operation | Cost | Why |
| --- | --- | --- |
| `get(index)` | $O(1)$ | Direct array index |
| `contains(value)` | $O(\log n)$ | Binary search |
| Add | $O(n)$ | Values may need shifting |
| Remove | $O(n)$ | Later values shift left |
| Iterate in order | $O(n)$ | Values are already sorted |

### Pros

- Fast membership checks through binary search.
- Values are always ready for ordered iteration.
- Compact contiguous storage with good cache locality.
- Supports efficient range boundaries through binary search.

### Cons

- Insertions and removals are expensive because elements move.
- The duplicate-value policy must be defined.
- Capacity management is still required.
- A balanced tree is often a better choice when frequent insertions and removals are required.

---

## CircularArray

A circular array is a fixed-size array that treats the position after its last slot as the first slot again. It is commonly used to implement a queue or circular buffer without shifting elements.

### How It Works

- Store elements in an internal array and track the positions of the first element (`head`) and the next open slot (`tail`).

```text
[A, B, C, D, E, null, null] has capacity 7 with head [0]: A and tail [5]: null
[A, B, C, D, E, F, G] has capacity 7 with head [0]: A and tail [0]: A (full)
```

- Advance positions with modulo arithmetic: `(index + 1) % capacity`.
- Add an element at `tail`, then advance `tail`.
- Remove an element from `head`, clear its slot, then advance `head`.
- Track `size` to distinguish an empty array from a full one, because both can make `head` and `tail` equal.

### When It Is Used

Use a circular array when values are added and removed in first-in, first-out order and the collection has a known or bounded capacity. Typical examples include queues, rolling logs, streaming buffers, and round-robin scheduling.

### Complexity

| Operation | Cost | Why |
| --- | --- | --- |
| Add at tail | $O(1)$ | Write at the next open position |
| Remove from head | $O(1)$ | Read and clear the first position |
| Peek at head | $O(1)$ | `head` identifies the first value |
| `get(index)` | $O(1)$ | Convert the logical index to a wrapped array index |
| `contains(value)` | $O(n)$ | Values may need to be checked one by one |

### Pros

- Adds and removes values without shifting elements.
- Reuses freed slots efficiently through wraparound.
- Provides predictable memory usage for bounded collections.
- Supports constant-time queue operations.

### Cons

- Fixed capacity requires a policy for full buffers.
- Wrapped logical order is less intuitive than a regular array layout.
- Random insertions and removals still require shifting elements.
- Correctly handling empty and full states requires tracking additional state.

---

## SparseMatrix

A sparse matrix is a two-dimensional matrix in which most values are the default value, usually `0`. Instead of allocating space for every row and column position, it stores only the non-default entries and their coordinates.

```text
0 0 0 5
0 0 0 0
2 0 0 0

Stored entries: (0, 3) = 5, (2, 0) = 2
```

### How It Works

- Represent each stored value with its row index, column index, and value.
- Keep those entries in an array or array-backed list, often ordered by row and then column.
- Return the default value when a requested coordinate has no stored entry.
- Insert or remove an entry when a value changes from or to the default value.

### When It Is Used

Use a sparse matrix when a matrix has many rows and columns but relatively few meaningful values. Common examples include graph adjacency matrices, grid-based simulations, recommendation data, and scientific datasets with many zero measurements.

### Complexity

The cost depends on the representation. With an ordered array of stored entries, where $k$ is the number of non-default values:

| Operation | Cost | Why |
| --- | --- | --- |
| Read a value | $O(\log k)$ | Binary search locates a stored coordinate |
| Update a stored value | $O(\log k)$ | Find the coordinate, then replace its value |
| Insert or remove an entry | $O(k)$ | Array entries may need to shift |
| Iterate stored values | $O(k)$ | Only non-default entries are visited |
| Space | $O(k)$ | Storage scales with meaningful values |

### Pros

- Uses much less memory when non-default values are rare.
- Iterating meaningful values avoids scanning empty positions.
- Can represent very large logical matrices when $k$ is small.

### Cons

- Coordinate lookups are less direct than in a regular two-dimensional array.
- Inserting or removing entries in an array-backed representation may shift values.
- It is inefficient for dense matrices because coordinate metadata adds overhead.

