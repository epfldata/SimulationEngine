batchMessages() applies each of its argument sequentially. In the default configuration, the expected output is 

```
(TIMER,0)
Hello world
(TIMER,1)
Hello world
Greeting from object3
(TIMER,2)
Hello world
Greeting from object2 10 sibling's age is 15
Greeting from object2 10 sibling's age is 15
Greeting from object3
(TIMER,3)
Hello world
Greeting from object3
(TIMER,4)
Hello world
Greeting from object2 10 sibling's age is 15
Greeting from object2 10 sibling's age is 15
Greeting from object3
(TIMER,5)
Hello world
Greeting from object3
```
Since there are no waitTurns in any of the method call, one batch message is sent every turn. Since Object2 checks its mailbox every 2 turns, 
we see that Object2 receives two messages in its mailbox. 

However, if there are wait statements in any of batchMessages' local messages, then batchMessages take time to complete and will block any following call. 
Consider uncommenting waitTurns(1) in hello() in Object1. The expected output now becomes
```
(TIMER,0)
Hello world
(TIMER,1)
(TIMER,2)
Hello world
Greeting from object2 10 sibling's age is 15
Greeting from object3
(TIMER,3)
(TIMER,4)
Hello world
Greeting from object2 10 sibling's age is 15
Greeting from object3
(TIMER,5)
(TIMER,6)
Hello world
Greeting from object2 10 sibling's age is 15
Greeting from object3
(TIMER,7)
(TIMER,8)
Hello world
Greeting from object2 10 sibling's age is 15
Greeting from object3
```
There are two things worth noting. First, object2 now receives only 1 message rather than two, as the batchMessages() takes 1 time unit to finish, one message is sent every other time unit. 
Second, note that the messages in the argument are blocking. Even though the wait statement is only in msg3, msg1 and msg2 are also delayed, otherwise we should see "Greeting from object3" at Timer 1, 
as is the case for waitTurns(2) with no wait in hello(), shown below:  

```
(TIMER,0)
Hello world
(TIMER,1)
Greeting from object3
(TIMER,2)
Hello world
Greeting from object2 10 sibling's age is 15
(TIMER,3)
Greeting from object3
(TIMER,4)
Hello world
Greeting from object2 10 sibling's age is 15
(TIMER,5)
Greeting from object3
(TIMER,6)
Hello world
Greeting from object2 10 sibling's age is 15
(TIMER,7)
Greeting from object3
(TIMER,8)
Hello world
Greeting from object2 10 sibling's age is 15
(TIMER,9)
Greeting from object3
```

If we make msg3 the last message, then the result is identical to when waitTurns of Object1 is changed to 2. 

However, if the wait statements are in the remote calls, then it won't block any of the following messages. Consider uncommenting waitTurns(1) in Object2 get(). Now with no other wait statements in messages
and the order of the messages being msg1, msg2, msg3, the expected output is: 
```
(TIMER,0)
Hello world
(TIMER,1)
Hello world
Greeting from object3
(TIMER,2)
Hello world
Greeting from object2 10 sibling's age is 15
Greeting from object3
(TIMER,3)
Hello world
Greeting from object2 10 sibling's age is 15
Greeting from object3
(TIMER,4)
Hello world
Greeting from object2 10 sibling's age is 15
Greeting from object3
(TIMER,5)
Hello world
Greeting from object2 10 sibling's age is 15
Greeting from object3
(TIMER,6)
```

As can be seen, even though msg1 contains wait statement, it only delays its own execution, and the rest of the messages are delivered without any blocking. 

Comparison: without batching: 
```
(TIMER,0)
(TIMER,1)
(TIMER,2)
Greeting from object2 10 sibling's age is 15
(TIMER,3)
(TIMER,4)
Greeting from object3
(TIMER,5)
Hello world
(TIMER,6)
(TIMER,7)
(TIMER,8)
Greeting from object2 10 sibling's age is 15
(TIMER,9)
(TIMER,10)
Greeting from object3
(TIMER,11)
Hello world
(TIMER,12)
(TIMER,13)
(TIMER,14)
Greeting from object2 10 sibling's age is 15
(TIMER,15)
(TIMER,16)
Greeting from object3
(TIMER,17)
Hello world
```
