asyncMessage() allows asynchronous message passing. In the default configuration, responses for msg1 and msg3 are both received at the same cycle. The expected output is

```
(TIMER,0)
This should be printed in each iteration
Send async msg1
Send async msg3
(TIMER,1)
Hello Chris
object2!
getWR is called from object3!
Greeting from object3
(TIMER,2)
This should be printed in each iteration
msg1 is completed!
msg1 value +10 is 35
msg3 is completed!
msg3 value is 11
(TIMER,3)
Hello Chris
Greeting from object3
(TIMER,4)
This should be printed in each iteration
Send async msg1
Send async msg3
(TIMER,5)
```

