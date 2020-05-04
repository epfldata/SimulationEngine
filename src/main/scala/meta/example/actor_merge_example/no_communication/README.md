This example demonstrates merging non-communicating agents with parameter lists and identical local variable and method names. We are simulating two independent bank accounts with different types of interest plan, one collects interest daily, and the other collects weekly. 

Expected output: 
```
(TIMER,0)
Hello Alice Your current balance is 1000.0
Hello Bob Your current balance is 1000.0
(TIMER,1)
Hello Alice Your current balance is 1000.1
Hello Bob Your current balance is 1000.6999999999999
(TIMER,2)
Hello Alice Your current balance is 1000.20001
Hello Bob Your current balance is 1000.6999999999999
(TIMER,3)
Hello Alice Your current balance is 1000.300030001
Hello Bob Your current balance is 1000.6999999999999
(TIMER,4)
Hello Alice Your current balance is 1000.4000600040001
Hello Bob Your current balance is 1000.6999999999999
(TIMER,5)
Hello Alice Your current balance is 1000.5001000100006
Hello Bob Your current balance is 1000.6999999999999
...
```
