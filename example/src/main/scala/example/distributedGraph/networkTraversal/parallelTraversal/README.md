The algorithm may return any spanning tree. One possible tree (from the trace below): (1, 2), (1, 3, 4)

Possible output: 

```
(Time 0.0 Turn 0)
(Time 0.0 Turn 1)
(Time 0.0 Turn 2)
(Time 0.0 Turn 3)
Initialization completed! Neighbors of 1: List(2, 3)
Initialization completed! Neighbors of 2: List(1, 3)
Initialization completed! Neighbors of 3: List(1, 2, 4)
Initialization completed! Neighbors of 4: List(3)
(Time 0.0 Turn 4)
2 receives go message from 1t.spanningTreeTest 0s
3 receives go message from 1
(Time 0.0 Turn 5)
2 receives go message from 3
3 receives go message from 2
4 receives go message from 3
Node 4 is done!
(Time 0.0 Turn 6)
2 receives back message of List()
3 receives back message of List(4)
3 receives back message of List()
(Time 0.0 Turn 7)
1 receives back message of List(2)
1 receives back message of List(3, 4)
Broadcast completed!
... 
```
