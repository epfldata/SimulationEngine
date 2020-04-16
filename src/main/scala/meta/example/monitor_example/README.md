This simple example demonstrates the usage of monitor. Due to the presence of random number generator, so your output won't be exactly the same. But you should see something similar to the following   

Expected output: 
```
(TIMER,0)
Object1 infected!
Object2 Recovered!
Monitor stats: Map(Recovered -> 1, Infectious -> 1)
(TIMER,1)
...
(TIMER,9)
Object1 infected!
Object2 infected!
Monitor stats: Map(Recovered -> 6, Infectious -> 11)
(TIMER,10)
Object1 infected!
Monitor stats: Map(Recovered -> 6, Infectious -> 12)
Summary: 
Map(Recovered -> 6, Infectious -> 12)
Timeseries:
Map(Recovered -> ListBuffer(1, 0, 1, 1, 1, 1, 0, 1, 0, 0, 0), Infectious -> ListBuffer(1, 0, 2, 2, 1, 0, 2, 0, 1, 2, 1))
...
```

