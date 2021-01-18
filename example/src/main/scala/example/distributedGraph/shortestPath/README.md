In this example, we look at the shortest problem in a distributed graph. 

The graph we used in the examples is visualized below. 

<img src="weightedGraph.png" alt="drawing" width="200"/>
<!-- 
Pseudo code for distributed synchronized shortest path algorithm: 

<img src="syncShortestPath.png" alt="drawing" width="350"/>

Pseudo code for distributed Floyd-Warshall algorithm: 

<img src="distFloydWarshall.png" alt="drawing" width="350"/> -->

<!-- - [Parallel traversal](parallelTraversal) shows an example of parallel traversal in an arbitrary network using broadcast and convergecast. 
- [DFS spanning tree](DFSpanningTree) builds a spanning tree using DFS in a distributed graph, optimized with global knowledge (about what their neighbors know). 
- [BFS spanning tree, decentralized](BFSpanningTree) demonstrates how to build a spanning tree using BFS, without centralized control, i.e. node has insufficient knowledge, unaware of whether its states are final. 
- [BFS spanning tree, centralized](BFSpanningTreeWave) introduces central control to the BFS implementation; comparing to the decentralized alternative, this implementation trades time complexity for better message complexity. 
- [Logical ring](LogicalRing) is an application of the parallel network traversal: it shows how we can build a logical unidirectional ring for a connected network. For more details, please refer to the chapter listed above.  -->
