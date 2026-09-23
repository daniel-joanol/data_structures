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
		tree/
		graph/
```

Add each implementation to the package that matches its category. For example, a custom stack belongs in `stack`.

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
