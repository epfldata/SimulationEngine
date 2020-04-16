# economic_simulations

### Write Classes using Code-Lifting
* Do not use val parameters, they can't be lifted.  Use var instead
* The optimzations created work for specific use-cases:
  * ActorMerge takes a pair of ActorType Names to specify which one to merge.  
  Take care, that the class variables are named differently in the two actors
  * Stateless Server Optimization has following rules:
    * A stateless server here means a class whose methods don't change any of its attributes, because then those methods can be copied to other actors.
    * A stateless server class should not have a wait in a non-blocking method, otherwise the program will not behave as the original
    * A stateless server cannot call a method from a non-stateless Server
    * An object can have only one reference to a (unique) specific stateless server class.
    It can have different references to different stateless servers(if those servers have unique attribute names among themselves).
    * The optimization has to be applied before using the EdgeMerge Optimization, since it requires the original graph
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
  * asyncMessage(message) ... send a nonblocking message. Please checkout the example in folder nb_methods_example/ for how it is used 
  * waitTurns(x) ... the actor waits for x steps
* It is required to use waitTurns at least once in a loop, like while(true),
  otherwise this actor will not stop its step
* To lift a class annotate it with @lift and extend from runtime.Actor
 
### Run Classlifting-Examples
Make sure to run the code with enough stack size, otherwise you will get a StackOverflowError. 
You can set this parameter for example in Intellij by putting -Xss128m (128MB Stack size) at VM configuration in the 
configuration settings.

At the moment it is important to build squid locally before using this code. Since there is no public release with
the bug fixes.
 
Please note that `/src/main/scala/ecosim` contains a standalone copy of the project in current master branch, which is the initial version that doesn't include message passing or relying on Squid. 

Examples in `meta`, upon successful termination, will generate files which are used for the actual simulation in 
folder `generated`. For example, consider the case `server_communication` in `src/main/scala/meta/example`. After running **ServerExample**, one should find 
files **BackendServer**, **FrontendServer**, and **InitData** been generated in folder `generated/main/scala/generated`. Now one 
can proceed to start the actual simulation by running **generated/main/scala/generated/Simulation** and the expected output is
```$xslt
(TIMER, 0)
...
(Time consumed, *some_number*)
```
If desired, you can test the actorMerge optimization by replacing the **ServerExample** with **ServerExampleMerge**. One should see the merged file `BackendServer_FrontendServer` being generated in 
`generated`. As of now, you need to modify the generated **InitData** file to start the merged actor. 

To test the performance with Spark support, you can run **generated/main/scala/generated/SimulationSpark** in lieu of **generated/main/scala/generated/Simulation** over IRs. Please do note
that the **InitData** is overwritten by the new example. It is easy to modify the output directory and package name for the desired simulation, where are specified in the ***Example** file. Upon 
such modification, please also change 

```actors = generated.InitData.initActors``` 

in **Simulation** to 

```actors = *new_package_name*.InitData.initActors```

where the **new_package_name** is what specified in ***Example** file with default value "generated" defined in `src/main/scala/meta/deep/codegen/CreateCode`. 
 
### Code Format
We use Scalafmt for formatting the code.
For installing Scalafmt with your favorite IDE see https://scalameta.org/scalafmt/
