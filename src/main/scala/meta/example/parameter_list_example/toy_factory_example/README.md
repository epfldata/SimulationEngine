This example demonstrates how new agents can be created using parameter list. 
toyFactory creates two new toys at each time step, and each toy is itself an agent. 
There is no communication among agents. 

Expected output: 
```
(TIMER,0)
(TIMER,1)
Toy Tag: name Bob Number 1
Toy Tag: name Alice Number 6
(TIMER,2)
Toy Tag: name Bob Number 1
Toy Tag: name Alice Number 6
Toy Tag: name Bob Number 11
Toy Tag: name Alice Number 16
(TIMER,3)
Toy Tag: name Bob Number 1
Toy Tag: name Alice Number 6
Toy Tag: name Bob Number 11
Toy Tag: name Alice Number 16
Toy Tag: name Bob Number 21
Toy Tag: name Alice Number 26
(TIMER,4)
Toy Tag: name Bob Number 1
Toy Tag: name Alice Number 6
Toy Tag: name Bob Number 11
Toy Tag: name Alice Number 16
Toy Tag: name Bob Number 21
Toy Tag: name Alice Number 26
Toy Tag: name Bob Number 31
Toy Tag: name Alice Number 36
(TIMER,5)
...
```