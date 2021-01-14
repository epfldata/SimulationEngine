This version shows how we can use BFS to build a spanning tree without centralized control 

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
2 receives go message from 1 level 0
3 receives go message from 1 level 0
(Time 0.0 Turn 5)
2 receives go message from 3 level 1
3 receives go message from 2 level 1
4 receives go message from 3 level 1
(Time 0.0 Turn 6)
2 receives back message from List() level: 2
3 receives back message from List(4) level: 2
3 receives back message from List() level: 2
(Time 0.0 Turn 7)
1 receives back message from List(2) level: 1
1 receives back message from List(3) level: 1
Broadcast completed!
(Time 0.0 Turn 8)
(Time 0.0 Turn 9)
...
```