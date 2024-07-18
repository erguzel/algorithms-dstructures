# Algorithms and Data Structures in Java

## Introduction
Welcome to the Algorithms and Data Structures project! This project is designed to help you understand and implement various algorithms and data structures using Java. it can help you sharpen your clean and efficient code writting skills by controlling test cases, and by comparing the performance trade offs between algorithms and datastructures. Whether you are a beginner or looking to refresh your knowledge, this project provides hands-on experience with fundamental concepts in computer science, and provide testing and debugging tools to understand the concepts deeper and making test cases easier, especially with graphs. 

## Table of Contents
- [Introduction](#introduction)
- [Features](#features)
- [Installation](#installation)
- [Usage](#usage)
- [Algorithms Covered](#algorithms-covered)
- [Data Structures Covered](#data-structures-covered)
- [Contributing](#contributing)
- [License](#license)

## Features
- Comprehensive implementation of common algorithms,
- Examples of competitive programming problems and solutions from leetcode and other sources,
- Graph data generation, validation, visualization and manipulation tools,
- Benchmarking tools,
- Search algorithm examples,
- Sorting algorithm examples,
- Data structure examples,
- Graph algorithm examples,
- Dynnamic programming examples,
- Examples test cases.


## Installation
To get started with the project, follow these steps:

1. **Clone the repository:**
   ```sh
   git clone git@github.com:erguzel/algorithms-dstructures.git
   ```
2. run tests:
   ```sh
   gradle clean && gradle test
   ```

## Usage
- Play around and run/debug each class in their main, or call them via Benchmarker.
- For your own graph excercises:
    - Create your graph with a graph visualisation tool of any. Or use existing graphs defined in ```com.egzel.lib.util.SampleData``` in. Below tools are some of graph visualizers;
        - https://csacademy.com/app/graph_editor/
        - https://graphonline.ru/en/
    - Give a name to your graph and save to ```com.egzel.lib.util.SampleData``` static class as adjMatrix, adjList or edgeList.
    - Use static Convertors in ```com.egzel.lib.util.DataUtil``` to convert your graph data between adjMatrix - adxList - edgeList when necessary,
    - Call your data as public static field of ```com.egzel.lib.util.SampleData``` into your function or class,
    - You can also generate a graph data via static Generators in  ```com.egzel.lib.util.DataUtil```

Remarks:
- I implemented a plain logger with a simple timer-benchmark in order to keep the project dependency free. 
- You can use parallel threads to test various control groups as in the example ```package com.egzel.problem.HowSumProblem``` main method.
- There is a versatile exception library, feel free to create your own exceptions using ```com.egzel.lib.exception.BaseException```.
- This project is for educative purposes. There might be bugs or unfinished excercises.


   ## Algorithms Covered
- Graph algorithms:
    - Dijkstra's Algorithm,
    - Bellman-Ford Algorithm,
    - Floyd-Warshall Algorithm,
    - Cycle detection,
    - Topological sort,
    - Minimum cost spanning tree,
    - Strongly connected components,
- Search algorithms:
    - Breadth first search,
    - Depth first search,
    - Binary search
- Sorting algorithms:
    - Bublesort,
    - Heapsort,
    - Insertion sort,
    - Quick sort,
    - Selection sort

## Data Structures covered
- Binary tree,
- Binary search tree,
- Heap,
- Linkedlist,
- Graph

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE.txt) file for details.
