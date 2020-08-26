Time advances only when all the Sims are waiting for time. Due to the random number, your output may not be the same as below. The point is to see that when Sim 2 is waiting for time, the timer value doesn't change right away. Only when Sim1 also waits on time does the timer advances. You can also define an interrupt that occurs at a given time. Please note that since a given time may corresponds to multiple turns, as in the output below for time 5, add some additional check if you want the interrupt to happen only once.  

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
...
(Time 4.5 Turn 30)
Wait time! 1
(Time 5.0 Turn 31)
(Time 5.0 Turn 32)
Wait time has finished 1
Alert! 5.0 has elapsed
Wait turn!
Wait time has finished 2
Alert! 5.0 has elapsed
Wait turn!
(Time 5.0 Turn 33)
Alert! 5.0 has elapsed
```
