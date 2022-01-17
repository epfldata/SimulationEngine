This folder contains examples for the simulation. To run an example, type the following instructions in sbt shell. ("sbt >>" stands for sbt shell. Please substitute *mainClass* and *yourTest* with appropriate arguments.)
```
sbt >> project example 
sbt >> runMain *mainClass*
```
Each example generates files in folder `economic_simulations/generated/src/main/scala/*exampleName*`. 
You can add the corresponding test for your example in `economic_simulations/generated/test/scala/*yourTest*`
```
sbt >> project genExample 
sbt >> testOnly *yourTest*
```
