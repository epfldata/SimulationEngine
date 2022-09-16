package meta.deep.codegen

import java.io.{BufferedWriter, File, FileWriter}

import meta.deep.IR
import meta.deep.IR.Predef._
import meta.deep.algo.AlgoInfo
import meta.deep.member.{EdgeInfo, CompiledActorGraph, VarValue, VarWrapper, MethodInfo}
import meta.runtime.Actor

import scala.collection.mutable.ListBuffer
import meta.API.CompilationMode
import scala.util.Random

object GeneratedMethods {
    var actorName: String = null
    var methodsIdMap: Map[String, Int] = null
    var methodsMap: Map[String, MethodInfo[_]] = null
    var compiledActorGraph: CompiledActorGraph = null
    var parts: Array[String] = Array()
    var createCode: CreateCode = null
    var instructionRegister: String = null
    var totalStates: Int = 0
    var memAddr: String = ""
    var parameterApplication: String = null

    def apply(graph: CompiledActorGraph, methodsIdMap: Map[String, Int], methodsMap: Map[String, MethodInfo[_]], createCode: CreateCode): Unit = {
        compiledActorGraph = graph
        actorName = graph.name
        this.methodsIdMap = methodsIdMap
        this.methodsMap = methodsMap
        this.createCode = createCode
        this.parameterApplication = compiledActorGraph.actorTypes.flatMap(actorType => {
            actorType.states.filter(x => x.parameter).map(s => {
            s.name
            })
        }).mkString(", ")
    }
}

trait GeneratedMethods {
    def run(): String
}

case object runUntil extends GeneratedMethods {
    import GeneratedMethods._
    override def run(): String = {
s"""
  override def run(): Int = {
    messageListener()
    sendMessages.clear()
    ${createCode.unblockRegMap(actorName)} = true
    while (${createCode.unblockRegMap(actorName)} && (${instructionRegister} < ${totalStates})) {
      ${memAddr}(${instructionRegister})()
    }
    time += 1
    time
  }
"""
    }
}


case object parametersString extends GeneratedMethods {
    import GeneratedMethods._

    override def run(): String = {
        compiledActorGraph.actorTypes.flatMap(actorType => {
            actorType.states.filter(x => x.parameter).map(s => {
                if (s.mutable) {
                s"var ${s.name} : ${createCode.changeTypes(s.tpeRep)}"
                } else {
                s"val ${s.name} : ${createCode.changeTypes(s.tpeRep)}"
                }
            })
            }).mkString(", ")
    }
}

case object parentString extends GeneratedMethods {
    import GeneratedMethods._

    override def run(): String = {
        val parentNames: List[String] = compiledActorGraph.parentNames
        val changedParentNames = parentNames.map(i => createCode.changeTypes(i))
        changedParentNames.mkString(" with ")
    }
}

case object cloneAgent extends GeneratedMethods {
    import GeneratedMethods._

    // val mutablePublicVariableRewrite: List[String] = compiledActorGraph.actorTypes.flatMap(actorType => {
    //     actorType.states.filter(x => x.mutable && !x.modifiers.contains("private") && !x.parameter).map(s => {
    //         f"""if (cloned_variables.contains("${s.name}")) newAgent.${s.name} = ${s.name}""" 
    //     })
    // })
    // ${mutablePublicVariableRewrite.mkString("\n  ")}
    override def run(): String = {
        s"""
override def SimClone(cloned_variables: Set[String]): ${actorName} = {
  val newAgent = new ${actorName}(${parameterApplication})
${compiledActorGraph.actorTypes.flatMap(actorType => {
      actorType.states.filter(x => x.mutable && !x.parameter && !x.modifiers.contains("private")).map(s => {
        s"""  if (cloned_variables.contains("${s.name}")) newAgent.${s.name} = ${s.name}"""
      })
    }).mkString("\n")}
  newAgent
}
"""
    }
}

case object resetAgent extends GeneratedMethods {
    import GeneratedMethods._

    override def run(): String = {
        val mutablePublicVariableRewrite: List[String] = compiledActorGraph.actorTypes.flatMap(actorType => {
            actorType.states.filter(x => x.mutable && !x.modifiers.contains("private") && !x.parameter).map(s => {
                s"""if (!preserved_names.contains("${s.name}")) ${s.name} = newAgent.${s.name}""" 
            })
        })

        val userVarsReset: String = 
            if (!mutablePublicVariableRewrite.isEmpty){
                s"val newAgent = new ${actorName}(${parameterApplication})\n" + " "*2 + mutablePublicVariableRewrite.mkString("\n" + " "*2)
            } else ""

        s"""
override def SimReset(preserved_names: Set[String]): Unit = {
  ${instructionRegister} = 0
  time = 0
  ${userVarsReset}
}
""" 
}
}

case object reflectionMethods extends GeneratedMethods {
    import GeneratedMethods._

    override def run(): String = {
        
        val getIR: String = 
s"""
override def getInstructionPointer: Int = {
${instructionRegister}
}
"""

        val setIR: String = 
s"""
override def setInstructionPointer(new_ir: Int) = {
if (new_ir >= ${totalStates} || new_ir <0) {
    throw new Exception("Invalid address pointer " + new_ir + " for agent " + id)
}
val prev_ir: Int = ${instructionRegister}
${instructionRegister} = new_ir
this
}
"""
        getIR + setIR
    }
}