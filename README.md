[![Scala CI](https://github.com/ZiluTian/economic_simulations/actions/workflows/scala.yml/badge.svg?branch=latest)](https://github.com/ZiluTian/economic_simulations/actions/workflows/scala.yml)

## Large-scale agent-based simulation 

The goal of this project is to provide a framework suitable for scaled-out agent-based simulation. 

### Overview 
- [Synchronization DSL](#DSL)
- [Sims as Meta-Programs](#Meta-programs)
- [Start Simulation](#Simulation)
- [Folder Structure](#Folder)
- [Issues and Solutions](docs/Issues.md)

### <a name="DSL"></a> Synchronization DSL

We define an embedded DSL to address the synchronization issue among Sims. 

The syntax for a blocking message is the same as a regular function. When a Sim calls the public method of another, it sends a blocking message to the receiver, waiting for its response. You can define in Sim1 a call to Sim2:  

```val secret: Int = Sim2.tellMeThis()``` or ```val workDone: Boolean = Sim2.doThat()``` 

given that `tellMeThis()` and `doThat()` are public methods in Sim2's class. The transpiler converts it to delivering a message to Sim2 and waiting for a reply. When the reply arrives, the function returns and Sim1's variables ```secret``` and ```workDone```  get their values. 

Besides blocking calls, Sims can also send asynchronous messages, with a different syntax```asyncMessage(() => receiver.API(args))```. For asynchronous messaging, the Sim places the message in its mailbox and continues. Messages are not delivered immediately. The non-block message return a Future object, which user can query about the status of the message. `src/main/scala/meta/runtime/Future.scala`.

Function ```waitLabel(Turn, someTicks)``` signals that messages in the Sim's mailbox are ready and agents will wait for the specified ticks. After each turn, the agents automatically process all the messages in their mailboxes. You can find the syntax of the DSLs here ```core/src/main/scala/meta/classLifting/SpecialInstructions.scala```

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
* The optimzations created work for specific use-cases:
  * ActorMerge takes a pair of ActorType Names to specify which one to merge.  
  Take care, that the class variables are named differently in the two Sims
  * Stateless Server Optimization has following rules:
    * A stateless server here means a class whose methods don't change any of its attributes, because then those methods can be copied to other Sims.
    * A stateless server class should not have a wait in a non-blocking method, otherwise the program will not behave as the original
    * A stateless server cannot call a method from a non-stateless Server
    * An object can have only one reference to a (unique) specific stateless server class.
    It can have different references to different stateless servers(if those servers have unique attribute names among themselves).
    * The optimization has to be applied before using the EdgeMerge Optimization, since it requires the original graph
* Following structures are lifted by default: 
  * while(cond) {code}
  * val x = code
  * list.foreach(code)
  * list.map(code)
  * list.flatMap(code)
  * (x <: Actor).\[methodCall\](...)
  * if(cond) code else code2
* You can use any other code, which does not contain a nested supported lifting structure.   
* You can extend the lifting by:
  * creating a subclass of algo
  * add in this subclass the nodes to the graph
  * Extend the lifter and override the method liftCodeOther  
  and handle there your created algos.
* To lift a class, annotate it with @lift and extend from runtime.Actor
* At the moment, *args* in the syntax of asyncMessage must be class variables. For example, code snippet below is incorrect. 

```allProducts.map(elem => asyncMessage[Product](() => market.consumerPrice(elem))) ```

But the following code snippet works fine.
```neighbors.map(n => asyncMessage[Int](() => n.getStatus()))```

To fix it, simply bind *elem* with a class variable. 
```
var product: Product = null // class variable

allProducts.map(elem => {
  product = elem
  asyncMessage[Product](() => market.consumerPrice(product))
})
```
* Please make sure that **only** the main method of an agent contains special instructions.


### <a name="Simulation"></a> Start Simulation 
For the compiled version, you can start simulation after generating object programs. The object programs are in folder `/generated/src/main/scala/$packageName$`. To start a simulation, define you simulation configuration and choose a messaging layer implementation. You can find more information about the simulation API in folder `core/src/main/scala/meta/API/`. Here is an example usage assuming the object generated.example.gameOfLife.InitData is the configuration object of your target simulation.

```
import meta.API._

object gameOfLifeTest {

  def main(args: Array[String]): Unit = {
    if (args.size < 3) {
      throw new Exception("Not enough argument!")
    }
    val gridWidth = args(0).toInt
    val gridHeight = args(1).toInt
    val totalTurns = args(2).toInt

    val agents = generated.example.gameOfLife.InitData(gridWidth, gridHeight)

    val c = new SimulationConfig(agents, totalTurns)

    StartSimulation[AkkaMessagingLayer.type](c)
    // StartSimulation[BaseMessagingLayer.type](c)
  }
}
```

### <a name="Folder"></a> Folder Structure 
- `ecosim/` contains the legacy implementation of the simulation framework without using message passing 
- `docs/` contains documentation
- `example/` contains the examples using class-lifting and message-passing 
- `generated/` contains the object programs. The simulation drivers take object programs and run 
- `library/` contains helper classes which are non-agent that you can use in your example. 
- `core/` contains the compiler source code and supporting runtime objects. 
- `stagedSims/` contains the no-compilation implementation of the DSL (in the branch staged)
 
### Code Format
We use Scalafmt for formatting the code.
For installing Scalafmt with your favorite IDE see https://scalameta.org/scalafmt/
 
Please open an issue or make a pull request if you encounter other problems or have suggestions. Thank you.  
