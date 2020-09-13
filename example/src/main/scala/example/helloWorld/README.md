This helloWorld example demonstrats how one can dynamically change the topology of the communication graph by passing Sim reference. In the initial setup, we have three people, named Alice, Bob, and Eve. Alice and Bob are friends; Bob is also friend with Eve Being an outgoing person, Bob wants to introduce Alice and Eve to each other, if they are not already familiar. During the simulation, we see that Alice and Eve become friends after Bob introducing them, and can communicate directly. We can also create new Sims at runtime. 

Please try helloWorldExampleTest in the test folder for similar output. 

Expected output: 
```
(Time 0.0 Turn 0)
(Time 0.0 Turn 3)
17:37:13.861 [run-main-1] INFO generated.meta.example.helloWorld.TimidPerson - My name is Eve
(Time 0.0 Turn 4)
(Time 0.0 Turn 6)
(Time 0.0 Turn 9)
17:37:13.865 [run-main-1] INFO generated.meta.example.helloWorld.TimidPerson - My name is Alice
(Time 0.0 Turn 10)
17:37:13.866 [run-main-1] INFO generated.meta.example.helloWorld.TimidPerson - Hello, Alice! My name is ʳ࢐壾㋜쏖
17:37:13.866 [run-main-1] INFO generated.meta.example.helloWorld.TimidPerson - Hello, ʳ࢐壾㋜쏖! My name is Alice
(Time 0.0 Turn 12)
(Time 0.0 Turn 15)
17:37:13.867 [run-main-1] INFO generated.meta.example.helloWorld.TimidPerson - My name is ʳ࢐壾㋜쏖
(Time 0.0 Turn 16)
17:37:13.868 [run-main-1] INFO generated.meta.example.helloWorld.TimidPerson - Hello, ʳ࢐壾㋜쏖! My name is ⥎耩曳筃깇
17:37:13.868 [run-main-1] INFO generated.meta.example.helloWorld.TimidPerson - Hello, ⥎耩曳筃깇! My name is ʳ࢐壾㋜쏖
```
