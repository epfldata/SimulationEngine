# Large-scale agent-based simulation 

The goal of this project is to provide a framework suitable for scaled-out simulation. Each Sim has a mailbox, and communicates with others through sending and receiving messages. 

### Synchronization DSLs

To address the synchronization issue among Sims (agents), we define a set of DSL functions. The syntax for a blocking message is the same as a regular function. You can define in Sim1 a call to Sim2:  

```val secret: Int = Sim2.tellMeThis()``` or ```val workDone: Boolean = Sim2.doThat()``` 

given that tellMeThis() and doThat() are public methods in Sim2's class. The compiler converts it to delivering a message to Sim2 and waiting for a reply. When the reply arrives, the function returns and Sim1's variables ```secret``` and ```workDone```  get their values. Besides blocking calls, Sim can also send asynchronous messages, with a different syntax```asyncMessage(() => msg)```. For asynchronous messaging, the Sim places the message in its mailbox and continues. Messages are not delivered immediately. Function ```waitLabel``` signals that messages in the Sim's mailbox are ready to be delivered. Apart from communicating with others, Sim decides when it wants to check its mailbox by calling another DSL function, ```handleMessages```. We also have another special instruction, interrupt.  You can find the signature of these methods here ```/src/main/scala/meta/classLifting/SpecialInstructions.scala```

### Class-lifting Sims 
Users define the behaviour of each agent in its own class. This project uses library Squid to lift classes and generate the IR for our compiler before emitting compiled code to folder ```/generated```. Please make sure you have the  Squid library installed before proceeding. 
```
- clone the class-lifting branch of Squid repo: 
  https://github.com/epfldata/squid.git
- create a local snapshot by running publishLocal: 
  bash bin/publishLocal.sh
- Expected output: 
  you should see "build 0.4.1-SNAPSHOT"
```
   
   Here are some tips for writing class-lifting code: 
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
  * var x = code
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

All simulation samples are in folder `example/`. Besides defining Sim classes that describe their behaviour, users also define a MainInit file that describes how many instances of the Sims to generate, and a singleton object that compiles the example.


### Run Class-lifting Examples
 
The generated code is in folder `/generated/src/main/scala/$packageName$`. Please reference the test scripts in `/generated/src/test/scala` for how to start a simulation. 
 
### Project Structure 
- `ecosim/` contains the legacy implementation of the simulation framework without using message passing 
- `example/` contains the examples using class-lifting and message-passing 
- `generated/` contains the compiled examples. The simulation drivers take compiled examples and run 
- `lib/` contains the library for writing the class-lifting examples. 
    - `Bot/` are the agents which you can instantiate directly in your example. Please refer to the rumor example which uses the LoggerBot to see how to use it. Please make sure to include the Bot you used in your main class when compiling your example. 
    - Other folders contain helper classes which are non-agent that you can use directly in your example. 
- `src/` contains the compiler source code and supporting runtime objects. 

 
### Code Format
We use Scalafmt for formatting the code.
For installing Scalafmt with your favorite IDE see https://scalameta.org/scalafmt/

### Known Issues 
Occassionally you may see StackOverFlowError when compiling your example. Please make sure to increase your stack size before launching JVM. 
In Intellij, you can set -Xss128m (128MB Stack size) at VM configuration in the configuration settings.

Please open an issue or make a pull request if you encounter any problems or have suggestions. Thank you.  