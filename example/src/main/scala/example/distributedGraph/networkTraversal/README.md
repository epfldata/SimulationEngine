This example demonstrates how we can build a spanning tree from a connected graph and apply broadcasting. It also illustrates one way of doing dependency injection in the simulation framework (through the use of channels). (reference: "Distributed algorithms for message-passing systems", Chapter 1)

Figure below shows the graph we used in the example (from the same reference). 

![figure](rootedSpanningTree.png)

Version 1 contains a basic implementation of the broadcast and convergecast
Version 2 shows how we can build a spanning tree using DFS, optimized with global knowledge (about what their neighbors know) 
Version 3 builds the spanning tree using BFS without centralized control (node has insufficient knowledge, unaware of whether its states are final)
Version 4 demonstrates how we can optimize the BFS with centralized control, and is an interesting use case highlighting the tradeoff between msg complexity and time complexity.   
