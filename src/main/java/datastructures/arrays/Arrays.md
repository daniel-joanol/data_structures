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