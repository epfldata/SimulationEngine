Time advances only when all the Sims are waiting for time. Due to the random number, your output may not be the same as below. The point is to see that when Sim 2 is waiting for time, the timer value doesn't change right away. Only when Sim1 also waits on time does the timer advances. 

Expected output: 
```
(Time 0.0 Turn 0)
Wait turn!
Wait turn!
(Time 0.0 Turn 1)
Wait time! 1
Wait turn!
(Time 0.0 Turn 2)
Wait time! 2
(Time 0.5 Turn 3)
(Time 0.5 Turn 4)
Wait time has finished 1
Wait time! 1
(Time 1.0 Turn 5)
(Time 1.0 Turn 6)
Wait time has finished 1
Wait turn!
Wait time has finished 2
Wait turn!
(Time 1.0 Turn 7)
...
```
