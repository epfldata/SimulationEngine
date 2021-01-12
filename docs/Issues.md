### Possible Issues 
- "StackOverFlow": You may get StackOverFlowError in staging phase. Please make sure to increase your stack size before launching JVM. In Intellij, you can set -Xss128m (128MB Stack size) at VM configuration in the configuration settings.

- "ClassNotFoundException": Not restricted to this project, but you may see the following exception when using the "run" shortcut from your IDE and even sbt `runMain *mainclass*`, especially for nested packages. 
```
"scala, (run-main-2) java.lang.ClassNotFoundException: example.epistemicLogicExamples.MuddyChildren_v2[error] java.lang.ClassNotFoundException: example.epistemicLogicExamples.MuddyChildren_v2"
```
First, please make sure your project structure follows the guidelines. If files are located properly, please try `run` from your project, i.e. after switching to the proper project in sbt, and select the index of the mainclass of your example. 
```
sbt    
>> project example   (Comment: assumes the error for main class in example.*)
>> run 
(an indexed list of discovered main classes)
enter the index of your main class! 
```
Not sure how/why it is different from `runMain`, but now it no longer complains (hopefully!) 

- "None.get during macro expansion": If you see the following error message when compiling your example, please check all the parameters in your lifted classes have references val or var.  
```
exception during macro expansion: 
java.util.NoSuchElementException: None.get
	at scala.None$.get(Option.scala:366)
	at scala.None$.get(Option.scala:364)
    ... 

@lift 
```

- "Unsupported feature: Refinement type 'Product with Serializable with $your_trait_name$'": If you get this error during lifting, please make sure your trait referenced in $your_trait_name$ extends "Product with Serializable" 
```
Embedding Error: Unsupported feature: Refinement type 'Product with Serializable with ...'
@lift
```

- "Term ... rewritten to ...": You can ignore this warning 
```
Term of type _$1 was rewritten to a term of type List[Option[meta.runtime.Future[Unit]]], not a known subtype.
``` 

- Inconsistency of the test and src. Please be aware that sometimes the test files don't actually sync in with the latest objects. First, please make sure the generated objects indeed contain your latest updates. If the problem still exists, please run `testOnly *testName*` from sbt, and then try from your IDE. This should bring the references up-to-date. 

- Lifted classes, at the time of writing, do not support type parameters or call-by-name parameters.