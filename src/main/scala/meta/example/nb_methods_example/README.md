asyncMessage() allows asynchronous message passing for remote methods. You can only pass local variables defined in the Sim as arguments for the asynchronous call; no constants or references to other vars. Sending an asynchronous message should not block the rest of execution, regardless of whether the message contains wait statements. For asynchronous messages that we expect replies, no new messages should be sent before the response arrives. When processing a local message with a wait statement, the wait time should be respected. Here is the expected output for the sample example. You are encouraged to make changes to the code to test different behaviour, such as adding wait statements to different messages. 

```
(TIMER,0)
Send async msg1: with response
The line after async call
(TIMER,1)
msg1 not completed!
Async msg1: remote method with wait 1  arg: 0.5 arg: 10.7
(TIMER,2)
msg1 not completed!
(TIMER,3)
Receive response from msg1!
(TIMER,4)
...
```

