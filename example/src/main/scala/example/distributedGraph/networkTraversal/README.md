Examples in this folder address network traversal in the distributed settings. It also highlights how one can break dependency cycles using channels. Examples here are from reference: "Distributed algorithms for message-passing systems", Chapter 1. 

Figure below shows the graph we used in the example (from the same reference). 

<img src="rootedSpanningTree.png" alt="drawing" width="200"/>

In parallelTraversal, we show a basic implementation of parallel traversal in an arbitrary network using broadcast and convergecast. In DFSpanning, we build a spanning tree using DFS in a distributed graph, optimized with global knowledge (about what their neighbors know). Similarly, BFSpanning demonstrates how to build a spanning tree using DFS, without centralized control, i.e. node has insufficient knowledge, unaware of whether its states are final. 
BFSpanningTreeWave is an alternative to BFSpanning; comparing to BFSpanning, this implementation trades time complexity for better message complexity. LogicalRing is an application of the parallel network traversal: it shows how we can build a logical unidirectional ring for a connected network. For more details, please refer to the chapter listed above. 
