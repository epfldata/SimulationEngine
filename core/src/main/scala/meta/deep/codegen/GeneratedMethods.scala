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
    
    def apply(graph: CompiledActorGraph, methodsIdMap: Map[String, Int], methodsMap: Map[String, MethodInfo[_]], createCode: CreateCode): Unit = {
        compiledActorGraph = graph
        actorName = graph.name
        this.methodsIdMap = methodsIdMap
        this.methodsMap = methodsMap
        this.createCode = createCode
    }
}

trait GeneratedMethods {
    def run(): String
}

case object runUntil extends GeneratedMethods {
    import GeneratedMethods._
    override def run(): String = {
s"""
  override def run(msgs: List[meta.runtime.Message]): (List[meta.runtime.Message], Int) = {
    addReceiveMessages(msgs)
    sendMessages.clear()
    ${createCode.unblockRegMap(actorName)} = true
    while (${createCode.unblockRegMap(actorName)} && (${instructionRegister} < ${totalStates})) {
      ${memAddr}(${instructionRegister})()
    }
    (sendMessages.toList, 1)
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
                s"var ${s.name}: ${createCode.changeTypes(s.tpeRep)}"
                } else {
                s"val ${s.name}: ${createCode.changeTypes(s.tpeRep)}"
                }
            })
            }).mkString(", ")
    }
}

case object parentString extends GeneratedMethods {
    import GeneratedMethods._

    override def run(): String = {
        var parentNames: List[String] = compiledActorGraph.parentNames
        if (!parentNames.contains("meta.runtime.Actor")) {
            parentNames = "meta.runtime.Actor" :: parentNames 
        }
        s"${parentNames.head}${parentNames.tail.foldLeft("")((a,b) => a + " with " + createCode.changeTypes(b))}"
    }
}

case object cloneAgent extends GeneratedMethods {
    import GeneratedMethods._

    override def run(): String = {
        val parameterApplication: String = 
            compiledActorGraph.actorTypes.flatMap(actorType => {
                actorType.states.filter(x => x.parameter).map(s => {
                s.name
                })
            }).mkString(", ")

        s"""
override def SimClone(): ${actorName} = {
  val newAgent = new ${actorName}(${parameterApplication})
  ${compiledActorGraph.actorTypes.flatMap(actorType => {
      actorType.states.filter(x => x.mutable && !x.parameter).map(s => {
        s"newAgent.${s.name} = ${s.name}"  
      })
    }).mkString("  \n")}
  newAgent
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