This folder shows how to define agent-based simulations using our DSL. Since the agent definitions contain special instructions, they need to be compiled before running on our system. To compile an example, type the following instructions in sbt shell. ("sbt >>" stands for sbt shell. Please substitute *mainClass* and *yourTest* with appropriate arguments.)
```
sbt >> project example 
sbt >> runMain *mainClass*
```
The generated files can be found in folder `generated/src/main/scala/*exampleName*`. To run a simulation, please execute the generated code using one of our backends, such as `Akka` or `Base`. See instructions in the corresponding directory.
