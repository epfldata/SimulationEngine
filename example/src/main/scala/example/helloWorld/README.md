This helloWorld example demonstrats how one can dynamically change the topology of the communication graph by passing Sim reference. In the initial setup, we have three people, named Alice, Bob, and Eve. Alice and Bob are friends; Bob is also friend with Eve Being an outgoing person, Bob wants to introduce Alice and Eve to each other, if they are not already familiar. During the simulation, we see that Alice and Eve become friends after Bob introducing them, and can communicate directly. 

Please try helloWorldExampleTest in the test folder for similar output. 

Expected output: 
```
(TIMER,0)
(TIMER,3)
My name is Alice 
(TIMER,4)
Hello, Alice! My name is Eve
Hello, Eve! My name is Alice
(TIMER,6)
```
