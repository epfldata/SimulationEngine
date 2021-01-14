This version uses DFS when constructing the rooted spanning tree. 

Possible output: 

```
(Time 0.0 Turn 0)
(Time 0.0 Turn 1)
(Time 0.0 Turn 2)
(Time 0.0 Turn 3)
Initialization completed! Neighbors of 1: Set(3, 2)
Initialization completed! Neighbors of 2: Set(1, 3)
Initialization completed! Neighbors of 3: Set(1, 2, 4)
Initialization completed! Neighbors of 4: Set(3)s
(Time 0.0 Turn 4)
3 receives go message from 1
(Time 0.0 Turn 5)
2 receives go message from 3
(Time 0.0 Turn 6)
3 children: List(2) visited: Set(1, 3, 2)
(Time 0.0 Turn 7)
4 receives go message from 3
(Time 0.0 Turn 8)
3 children: List(4, 2) visited: Set(1, 3, 2, 4)
(Time 0.0 Turn 9)
1 children: List(3) visited: Set(1, 3, 2, 4)
Broadcast completed!
(Time 0.0 Turn 10)
... 
```