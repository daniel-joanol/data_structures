# Data Structures

A plain Java project for studying and implementing data structures. It has no Spring or runtime framework dependencies.

## Layout

```text
src/
	main/java/datastructures/
		arrays/
		linkedlist/
		stack/
		queue/
		map/
		tree/
		graph/
```

Add each implementation to the package that matches its category. For example, a custom stack belongs in `stack`.

## Custom Data Structure Roadmap

This roadmap lists the planned data structure implementations in a suggested order. Each implementation begins with its core operations before expanding to convenience methods or optimizations.

### Arrays (`arrays`)

- `ArrayList`: dynamically resized array with indexed access
- `SortedArray`: ordered insertion and binary search
- `CircularArray`: wraparound indexing used by queues and deques
- `SparseMatrix`: map-backed representation for mostly empty matrices

### Linked Lists (`linkedlist`)

- `SinglyLinkedList`: append, prepend, insert, remove, and traversal
- `DoublyLinkedList`: previous and next links with bidirectional traversal
- `CircularLinkedList`: tail connected to head

### Stacks (`stack`)

- `ArrayStack`: array-backed LIFO stack
- `LinkedStack`: linked-list-backed LIFO stack
- `MinStack`: stack that returns the minimum value in $O(1)$

### Queues (`queue`)

- `ArrayQueue`: basic array-backed FIFO queue
- `CircularQueue`: fixed-capacity ring buffer
- `LinkedQueue`: linked-list-backed FIFO queue
- `Deque`: double-ended queue
- `PriorityQueue`: heap-backed queue ordered by priority

### Maps (`map`)

Maps deserve their own section: they store key-value pairs and underpin fast lookup, sets, caches, and graph adjacency lists.

- `HashMap`: separate chaining with buckets, hashing, collision handling, and resizing
- `OpenAddressingHashMap`: probing-based collision handling
- `TreeMap`: ordered map backed by a self-balancing binary search tree
- `LinkedHashMap`: hash map that preserves insertion or access order
- `Set`: a collection of unique values, implemented using a map internally

### Trees (`tree`)

- `BinarySearchTree`: ordered insertion, lookup, removal, and traversal
- `AVLTree` or `RedBlackTree`: self-balancing binary search tree
- `Heap`: min-heap and max-heap used by priority queues
- `Trie`: prefix tree for words and autocomplete
- `SegmentTree` or `FenwickTree`: range queries and updates

### Graphs (`graph`)

- `Graph`: adjacency-list and adjacency-matrix representations
- `UnionFind`: disjoint-set structure for connectivity checks

## Requirements

- JDK 21
- Maven 3.9 or newer

## Commands

Compile and run the entry point:

```bash
mvn compile
java -cp target/classes datastructures.App
```

Run tests when they are added:

```bash
mvn test
```
