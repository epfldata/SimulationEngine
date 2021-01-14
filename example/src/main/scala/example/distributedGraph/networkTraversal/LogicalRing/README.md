Here we simulate a token traveling along a logical ring. A token is a message and a ring is a unidirectional network. This algorithm builds a logical unidirectional ring on top of a connected network. 

Possible output: 
```
(Time 0.0 Turn 0)
(Time 0.0 Turn 1)
(Time 0.0 Turn 2)
(Time 0.0 Turn 3)
(Time 0.0 Turn 4)
2 receives go message from 1
(Time 0.0 Turn 5)
3 receives go message from 2
(Time 0.0 Turn 6)
4 receives go message from 3
(Time 0.0 Turn 7)
3 visited: Set(1, 2, 3, 4)
(Time 0.0 Turn 8)
2 visited: Set(1, 2, 3, 4)
(Time 0.0 Turn 9)
1 visited: Set(1, 2, 3, 4)
Ring completed!
...
```