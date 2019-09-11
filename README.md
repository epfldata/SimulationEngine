# economic_simulations

### Write Classes using Code-Lifting
* Do not use val parameters or class arguments, they can't be lifted.  Use var instead
* The optimzations created work for specific use-cases:
  * ActorMerge takes the first two Actors. This has to be modified for a real use-case
  * Stateless Server Optimization has following rules:
    * A Class is considered as statless, if it ends with the word Stateless
    * That class should not have a wait in a non-blocking method, otherwise the program is not behaving as original
    * Statless Server cannot call a method from a non-statless Server
    * An object can have only one specific reference to one (unique) specifc statless server class.  
    It can have different references to different stateless servers.
* Following structures are lifted by default: 
  * while(cond) {code}
  * var x = code
  * list.foreach(code)
  * (x <: Actor).\[methodCall\](...)
  * if(cond) code else code2
* You can use any other code, which does not contain a nested supported lifting structure.   
* You can extend the lifting by:
  * creating a subclass of algo
  * add in this subclass the nodes to the graph
  * Extend the lifter and override the method liftCodeOther  
  and handle there your created algos.
* You can call following additional functions:
  * handleMessages() ... handles all messages received
  * waitTurns(x) ... the actor waits for x steps
* It is required to use waitTurns at least once in a loop, like while(true),
  otherwise this actor will not stop its step
* To lift a class annotate it with @lift and extend from runtime.Actor
 
### Run Classlifting-Examples
Make sure to run the code with enough stack size, otherwise you will get a StackOverflowError. 
You can set this paramter for example in Intellij by putting -Xss128m (128MB Stack size) at VM configuration in the 
configuration settings.

At the moment it is important to build squid locally before using this code. Since there is no public release with
the bug fixes.
 

### Code Format
We use Scalafmt for formatting the code.
For installing Scalafmt with your favorite IDE see https://scalameta.org/scalafmt/