This example shows how latency increases as clients queue up.
Please note that server handles the same batch of requests randomly, so you may not see the exact same output. However, you can see how the latency number changes. 

Sample outcome: 
```
(Time 0.0 Turn 0)
Client 2 processing; take 0.2
Client 3 processing; take 0.2
Client 4 processing; take 0.2
Client 5 processing; take 0.2
Client 6 processing; take 0.2
(Time 0.1 Turn 1)
(Time 0.1 Turn 2)
(Time 0.2 Turn 3)
(Time 0.2 Turn 4)
Client 2 sent msg at time 0.2
Client 3 sent msg at time 0.2
Client 4 sent msg at time 0.2
Client 5 sent msg at time 0.2
Client 6 sent msg at time 0.2
(Time 0.3 Turn 5)
(Time 0.3 Turn 6)
Server processing request for 4; take 0.5
...
(Time 0.8 Turn 18)
Client 4 received reply! 0  Latency: 0.5999999999999999
Client 4 analyzing reply; take 0.4
...
(Time 1.2 Turn 26)
Client 4 processing; take 0.2
(Time 1.2 Turn 27)
...
(Time 1.3 Turn 30)
Response for client id 2 is 97
Server processing request for 6; take 0.5
(Time 1.3 Turn 31)
(Time 1.3 Turn 32)
Client 2 received reply! 97  Latency: 1.0999999999999999
Client 2 analyzing reply; take 0.4
(Time 1.4 Turn 33)
...
```  