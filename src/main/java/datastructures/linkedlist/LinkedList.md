# Linked Lists

A linked list stores elements in nodes. Each node holds an element and a reference to the next node, so elements do not need to occupy consecutive memory locations as they do in an array.

## Singly Linked List

`SinglyLinkedList<T>` stores a reference to its first node (`head`), last node (`tail`), and the number of elements (`size`). The tail reference makes appending efficient, while accessing a position requires walking forward from the head.

```text
head                                  tail
 |                                     |
 v                                     v
[A | next] -> [B | next] -> [C | null]
```

### Operations

| Operation | Description | Time complexity |
| --- | --- | --- |
| `add(element)` | Appends an element to the tail. | $O(1)$ |
| `add(index, element)` | Inserts an element at an index; index `0` prepends and `size()` appends. | $O(n)$ |
| `get(index)` | Returns the element at an index. | $O(n)$ |
| `set(index, element)` | Replaces and returns the element at an index. | $O(n)$ |
| `remove(index)` | Removes and returns the element at an index. | $O(n)$ |
| `contains(element)` / `indexOf(element)` | Searches the list from the head. | $O(n)$ |
| `size()` / `isEmpty()` | Returns the list state. | $O(1)$ |
| `clear()` | Removes every link and resets the list. | $O(n)$ |

Indexed reads, updates, removals, and insertions reject an invalid index with `IndexOutOfBoundsException`. The list accepts `null` elements and compares values with `Objects.equals`.

### When It Is Used

Use a singly linked list when elements are frequently added or removed at the beginning, or when the collection grows without needing fast indexed access. It works well for stack-like workflows, queues when both head and tail are tracked, and collections that are primarily traversed in order.

### Pros

- Appending is $O(1)$ because the list tracks its tail.
- Prepending is $O(1)$ because only the head reference changes.
- Inserting or removing after a known node only changes nearby links.
- The list grows node by node, so it does not need to resize a backing array.

### Cons

- Reading, updating, or locating an index requires traversal from the head.
- Each node stores an extra reference, increasing memory overhead.
- Nodes are not contiguous in memory, which is typically less cache-friendly than an array.
- Moving backward is not supported without traversing again from the head.

### Example

```java
SinglyLinkedList<String> topics = new SinglyLinkedList<>();
topics.add("A");
topics.add("C");
topics.add(1, "B");

System.out.println(topics); // [A, B, C]
topics.remove(0);
System.out.println(topics); // [B, C]
```

See `datastructures.linkedlist.cases.SinglyLinkedListCase` for a runnable example.

## Doubly Linked List

`DoubleLinkedList<T>` stores `head`, `tail`, and `size` like a singly linked list, but every node also references its previous node. That allows indexed operations to traverse from the nearer end of the list.

```text
null <- [A] <-> [B] <-> [C] -> null
				 ^                 ^
			 head               tail
```

### Operations

| Operation | Description | Time complexity |
| --- | --- | --- |
| `add(element)` | Appends an element to the tail. | $O(1)$ |
| `add(index, element)` | Inserts an element at an index; index `0` prepends and `size()` appends. | $O(n)$ |
| `get(index)` / `set(index, element)` | Traverses from the nearer end to access or replace an element. | $O(n)$ |
| `remove(index)` | Unlinks an element using its adjacent nodes. | $O(n)$ |
| `contains(element)` / `indexOf(element)` | Searches the list from the head. | $O(n)$ |
| `size()` / `isEmpty()` | Returns the list state. | $O(1)$ |
| `clear()` | Removes every link and resets the list. | $O(n)$ |

The list accepts `null` elements and compares values with `Objects.equals`. Invalid indexes throw `IndexOutOfBoundsException`.

### When It Is Used

Use a doubly linked list when insertions or removals near either end are common, or when backward traversal is useful. The extra previous-node reference costs more memory than a singly linked list but avoids retraversing from the head for positions near the tail.

### Pros

- Traversal can begin at either the head or tail, reducing work for positions near the end.
- Removing a known node only updates its adjacent links.
- Prepending and appending are both $O(1)$ while head and tail are tracked.
- Backward traversal is supported directly through previous-node references.

### Cons

- Each node stores an additional reference, increasing memory usage.
- Insertion and removal must update both previous and next links, making the implementation more complex.
- Indexed access still requires traversal and is $O(n)$.
- Nodes are not contiguous in memory, which is typically less cache-friendly than an array.

### Example

```java
DoubleLinkedList<String> topics = new DoubleLinkedList<>();
topics.add("A");
topics.add("C");
topics.add(1, "B");

System.out.println(topics); // [A, B, C]
topics.remove(2);
System.out.println(topics); // [A, B]
```

See `datastructures.linkedlist.cases.DoubleLinkedListCase` for a runnable example.
