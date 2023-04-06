[![Scala CI](https://github.com/ZiluTian/economic_simulations/actions/workflows/scala.yml/badge.svg?branch=latest)](https://github.com/ZiluTian/economic_simulations/actions/workflows/scala.yml)

## Large-scale agent-based simulation 

CloudCity is a full-stack system designed for large-scale agent-based simulations, following the BSP model. The system contains three parts, frontend (DSL), core (compiler that translates the DSL to Scala), and backend (Akka-based runtime and a single-threaded runtime). We also provide a user-level library that contains some helpful methods, which you can optionally use in your simulation.

### <a name="bin"></a> Include in your own project
To export the compiler, library, and runtime as jars that can be included in your project, you can call `publishLocal`
```
bash bin/publishLocal.sh
```
which would generate jar files in `target/scala-2.12` under respective projects (Akka, core, or library).
Then you can include it in your project as
```
libraryDependencies += "ch.epfl.data" %% "cloudcity-core" % "2.0-SNAPSHOT",
libraryDependencies += "ch.epfl.data" %% "cloudcity-library" % "2.0-SNAPSHOT",
libraryDependencies += "ch.epfl.data" %% "cloudcity-akka" % "2.0-SNAPSHOT",
```
selecting only the ones you need.

### Overview 
- [Synchronization DSL](#DSL)
- [Sims as Meta-Programs](#Meta-programs)
- [Start Simulation](#Simulation)
- [Run in a cluster](#Distributed)
- [Test Locally](#bin)
- [Folder Structure](#Folder)

### <a name="DSL"></a> Synchronization DSL

We define an embedded DSL to address the synchronization issue among Sims. 

The syntax for sending a blocking message is the same as dynamic dispatch in object-oriented language. When a Sim calls the public method of another, it sends a message to the receiver and waits for the response. You can define in Sim1 a call to Sim2:  

`val secret: Int = Sim2.tellMeThis()` or `val workDone: Boolean = Sim2.doThat()`

given that `tellMeThis()` and `doThat()` are public methods in Sim2's class. The transpiler converts it to delivering a message to Sim2 and waiting for a reply. When the reply arrives, the function returns and Sim1's variables `secret` and `workDone`  get their values. 

Besides blocking calls, Sims can also send asynchronous messages, with a different syntax `asyncSend(receiver.API(args))`. You need to mark the callee method with annotation `@transparencyPropagating`, see example in `core/src/test/scala/lifterTest1`. For asynchronous messaging, the Sim places the message in its mailbox and continues. Messages are not delivered immediately. The non-block message return a Future object, which user can query about the status of the message. `core/src/main/scala/meta/runtime/Future.scala`.

Instruction `waitRounds(someTicks)` signals that messages in the Sim's mailbox are ready and agents will wait for the specified ticks. The agent will *not* do anything besides waiting. To process received RPC requests, the receiver agent calls `handleRPC()`. Agents can also send or receive messages directly using the message-passing protocol. Other DSL instructions in ```core/src/main/scala/meta/classLifting/SpecialInstructions.scala``` are experimental and subject to future changes.

An agent is sequential and processes one message at a time. Therefore, you can not use instructions `handleRPC()`; doing so will trigger a compile-time error.

### <a name="Meta-Programs"></a> Sims as Meta-Programs
The embedded DSL is in a staged meta-programming environment. Staging is the operation that generates **object programs** from **meta-programs**. In our framework, users define the behaviour of each agent in **meta-programs** written in a subset of Scala enriched with DSL. We offer two flavors of DSL, one with compilation and one without. For the compiled version, our transpiler compiles the source programs to **object programs** (valid Scala source programs) in `generated\` folder with the help of Squid. For the non-compiled version, we use ScalaMeta and coroutines, see branch `staged`.
 
We include uber jars of the Squid (class-lifting branch) in the lib/ folder of dependent subprojects (currently under `core/` and `example/`). If you prefer, you can also build it locally (using the class-lifting branch of Squid repo: https://github.com/epfldata/squid.git) and uncomment the lines in build.sbt which loads Squid snapshot. 
   
Here are some tips for writing meta-programs in this framework: 
<!-- * The optimzations created work for specific use-cases:
  * ActorMerge takes a pair of ActorType Names to specify which one to merge.  
  Take care, that the class variables are named differently in the two Sims
  * Stateless Server Optimization has following rules:
    * A stateless server here means a class whose methods don't change any of its attributes, because then those methods can be copied to other Sims.
    * A stateless server class should not have a wait in a non-blocking method, otherwise the program will not behave as the original
    * A stateless server cannot call a method from a non-stateless Server
    * An object can have only one reference to a (unique) specific stateless server class.
    It can have different references to different stateless servers(if those servers have unique attribute names among themselves).
    * The optimization has to be applied before using the EdgeMerge Optimization, since it requires the original graph -->
* We support limited inheritance. 
  * The parent classes can not contain parameter lists. 
  * If a parent class is not a lifted agent, then you need to append it to the rootAgents in the Lifter, see example `inheritance1`. 
  * To add modifiers, use instructions `markPrivate(names: String*)` or `markOverride(names: String*)`, see examples `inheritance1` and `inheritance2`.
  * Public methods in the parent class which are inherited by children should *not* have references to private variables.

* The Squid class-lifting interface has the following non-exhaustive restrictions. The lifter does *not* support: 
  * instance variables inside an agent class definition. You cannot use the keyword *this* and should add the modifier *val* or *var* to your variables in the parameter list. 
  * pattern match. You can only use good old if-else or while-loop.
  * Array type. You will see errors complaining about Scala ClassTag not found if you a class variable of type Array
  * return instruction. You cannot use "return" inside a method definition.
  * default values in a parameter list
* To lift a class, annotate it with @lift and extend from runtime.Actor. When in doubt, please check how the examples are defined.

### <a name="Simulation"></a> Start Simulation 
For the compiled version, you can start simulation after generating object programs. The object programs are in folders `/generated*/`. To start a simulation, first select a runtime, such as base (sequential), then create a driver program in the `test` folder in `Base`. 

### <a name="Distributed"></a> Run in a cluster 
The Akka backend enables you to run distributed simulations in a cluster. Make sure you can reach any machine using `ssh` without entering passwords.    

For Akka, update file `Akka/src/main/resources/application.conf` to reflect the cluster setting. More specifically, replace the `localhost` in `remote.artery.canonical.hostnames` and `remote.cluster.seed-nodes` to the ip of the server and the seed machine respectively. Please refer to the Akka home page (https://akka.io/) for more information.

### <a name="bin"></a> Initialization
After cloning this repo, please initialize the repo with `init.sh`, which generates files for the tests in akka and base projects.

On linux or Mac:
```
bash bin/init.sh 
```

### <a name="Folder"></a> Folder Structure 
- `core/` contains the compiler
- `Akka/` Akka backend
- `Base/` Single-threaded backend
- `gen-core/` contains the object programs generated by tests in `core`
- `example/` contains the examples using class-lifting and message-passing 
- `generated/` contains the object programs generated by simulations in `example`
- `library/` contains user-level library that you can include when designing simulations
- `lib/` contains jar files for Squid library
- `ecosim/` contains the legacy implementation of the economic simulation without using message passing 
- `docs/` contains known issues and their solutions