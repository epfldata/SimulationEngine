asyncMessage() allows asynchronous message passing, for both local and remote methods. Sending an asynchronous message should not block the rest of execution, regardless of whether the message contains wait statements. For asynchronous messages that we expect replies, no new messages should be sent before the response arrives. When processing a local message with a wait statement, the wait time should be respected. Here is the expected output for the sample example. You are encouraged to make changes to the code to test different behaviour, such as adding wait statements to different messages. 

```
(TIMER,0)
Send async msg1: with response
Send async msg2: w/o response
Send async msg3: w/o response
Send async msg4: with response
(TIMER,1)
Async msg3: local method with wait 1
Async msg1: remote method with wait 1
Async msg2
Async msg4
(TIMER,2)
msg1 not completed!
Send async msg2: w/o response
Send async msg3: w/o response
Receive response from msg4!
(TIMER,3)
Async msg3: local method with wait 1
Async msg2
(TIMER,4)
Receive response from msg1!
Send async msg2: w/o response
Send async msg3: w/o response
Send async msg4: with response
(TIMER,5)
Async msg3: local method with wait 1
Async msg4
Async msg2
```

