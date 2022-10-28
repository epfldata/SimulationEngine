[![Scala CI](https://github.com/ZiluTian/economic_simulations/actions/workflows/scala.yml/badge.svg?branch=latest)](https://github.com/ZiluTian/economic_simulations/actions/workflows/scala.yml)

## Large-scale agent-based simulation 

The goal of this project is to provide a framework suitable for scaled-out agent-based simulation. 

### Overview 
- [Synchronization DSL](#DSL)
- [Sims as Meta-Programs](#Meta-programs)
- [Start Simulation](#Simulation)
- [Test Locally](#bin)
- [Folder Structure](#Folder)
- [Issues and Solutions](docs/Issues.md)

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
 
We include uber jars of the Squid (class-lifting branch) in the lib/ folder of dependent subprojects (currently under `core/` and `example/`). If you prefer, you can also build it locally and uncomment the lines in build.sbt which loads Squid snapshot. 

```
- clone the class-lifting branch of Squid repo: 
  https://github.com/epfldata/squid.git
- create a local snapshot by running publishLocal: 
  bash bin/publishLocal.sh
- Expected output: 
  you should see "build 0.4.1-SNAPSHOT"
```
   
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

* The lifter recognizes following code patterns: 
  * while(cond) {code}
  * val x = code or var x = code
  * List.map(code)
  * List.flatMap(code)
  * Iterable.foreach(code)
  * Iterable.exists(code)
  * Iterable.forall(code)
  * if(cond) code else code2
* You can extend the lifting by:
  * creating a subclass of algo
  * add in this subclass the nodes to the graph
  * Extend the lifter and override the method liftCodeOther  
  and handle there your created algos.
* To lift a class, annotate it with @lift and extend from runtime.Actor

* The `main` method is the entrypoint of the compiler. Please initialize any variables that require references to `this` inside `main`. 

### <a name="Simulation"></a> Start Simulation 
For the compiled version, you can start simulation after generating object programs. The object programs are in folders `/generated*/`. To start a simulation, first select a runtime, such as base (sequential), then create a driver program in the `test` folder in `Base`. 


### <a name="bin"></a> Local test
After cloning this repo, please initialize the repo with `init.sh`, which generates files for the tests in akka and base projects.

On linux or Mac:
```
bash bin/init.sh 
```

Following which, you can test with
```
bash bin/test.sh 
```

### <a name="Folder"></a> Folder Structure 
- `core/` contains the compiler
- `Akka/` distributed runtime based on Akka
- `Base/` the sequential runtime
- `Spark/` distributed runtime based on Spark
- `gen-core/` contains the object programs generated by tests in `core`
- `example/` contains the examples using class-lifting and message-passing 
- `generated/` contains the object programs generated by simulations in `example`
- `library/` contains richer APIs for more specific simulations
- `ecosim/` contains the legacy implementation of the economic simulation without using message passing 
- `docs/` contains documentation
- `stagedSims/` contains the no-compilation implementation of the DSL (in the branch staged)
- `bin/` contains local test scripts for this project.
 
### Code Format
We use Scalafmt for formatting the code.
For installing Scalafmt with your favorite IDE see https://scalameta.org/scalafmt/
 
Please open an issue or make a pull request if you encounter other problems or have suggestions. Thank you.  
