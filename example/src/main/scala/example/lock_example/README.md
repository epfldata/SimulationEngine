This example illustrates how to use a consensus object to achieve an agreement among voters. Each voter who has won will stop making proposal. In the following output, the number in the message is voter's id. You can see that each voter wins at most once. 

Sample output: 
```
(Time 0.0 Turn 0)
(Time 0.0 Turn 1)
(Time 0.0 Turn 2)
I lost. Try again! 2
I lost. Try again! 3
I win! 4 No more propose
I lost. Try again! 5
I lost. Try again! 6
(Time 0.0 Turn 3)
(Time 0.0 Turn 4)
(Time 0.0 Turn 5)
I lost. Try again! 2
I lost. Try again! 3
I lost. Try again! 5
I win! 6 No more propose
... 
```
