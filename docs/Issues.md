### Idioms and Issues 

### Idioms  
- Dependency injection: the suggested way of doing dependency injection is to introduce another agent. For example, if there is dependency cycle between agent A and B, then you can define agent C taking A and B as parameter variables, and initialize the dependency at runtime. You can refer to spanning tree example for more details. 
    
### Issues 
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

- Exceptions are not allowed in source objects
```
"Embedding Error: Unsupported feature: throw new scala.`package`.Exception("...")"
```

- Modifier "override" in class parameter list is not preserved in the staged code. For now you can add it manually in the generated objects 
   
- "runtime.RichInt is not nullable nor ." If you see this message, please check your source object for any pattern of `var foo = List(); ...; foo = (1 to total).toList` and replace it with passing `foo` as a parameter variable rather than `total`
```
[error] (run-main-13) java.lang.AssertionError: assertion failed: runtime.RichInt is not nullable nor .
[error] java.lang.AssertionError: assertion failed: runtime.RichInt is not nullable nor .
[error]         at scala.Predef$.assert(Predef.scala:223)
[error]         at squid.ir.ScalaTyping.nullValue(ScalaTyping.scala:312)
[error]         at squid.ir.ScalaTyping.nullValue$(ScalaTyping.scala:295)
[error]         at meta.deep.IR$.nullValue(IR.scala:6)
[error]         at squid.quasi.QuasiBase$Predef.nullValue(QuasiBase.scala:326)
[error]         at meta.deep.codegen.CreateCode.$anonfun$initVar$2(CreateCode.scala:503)
[error]         at squid.ir.AST.substitute(AST.scala:103)
[error]         at squid.ir.AST.substitute$(AST.scala:103)
[error]         at meta.deep.IR$.substitute(IR.scala:6)
[error]         at squid.quasi.QuasiBase.substitute(QuasiBase.scala:96)
[error]         at squid.quasi.QuasiBase.substitute$(QuasiBase.scala:94)
[error]         at meta.deep.IR$.substitute(IR.scala:6)
[error]         at meta.deep.codegen.CreateCode.$anonfun$initVar$1(CreateCode.scala:503)
[error]         at squid.quasi.QuasiBase.wrapConstruct(QuasiBase.scala:462)
[error]         at squid.quasi.QuasiBase.wrapConstruct$(QuasiBase.scala:462)
[error]         at meta.deep.IR$.wrapConstruct(IR.scala:6)
[error]         at meta.deep.codegen.CreateCode.initVar(CreateCode.scala:503)
[error]         at meta.deep.codegen.CreateCode.generateMutVarInit(CreateCode.scala:453)
...
[error] Nonzero exit code: 1
```
- "Long arrow not nullable": This arrow is caused by the use of "->" symbol in the source object, such as when adding elements to a map. Solution: avoid using ->. If you have a map, please make it mutable and update using the function call syntax: `val aMutableMap: Map[Int, Int] = Map[Int, Int]; ...; aMutableMap(5)=10`

- Inconsistency of the test and src. Please be aware that sometimes the test files don't actually sync in with the latest objects. First, please make sure the generated objects indeed contain your latest updates. If the problem still exists, please run `testOnly *testName*` from sbt, and then try from your IDE. This should bring the references up-to-date. 

- Lifted classes, at the time of writing, do not support type parameters or call-by-name parameters.
