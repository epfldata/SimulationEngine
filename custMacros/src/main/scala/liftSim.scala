package custMacros

import scala.reflect.runtime.universe._
import scala.language.experimental.macros
import scala.reflect.macros.blackbox

import scala.annotation.StaticAnnotation
import scala.annotation.compileTimeOnly

import scala.collection.mutable.{ListBuffer, Map => MutMap}

// Lift the behavior of each Sim
@compileTimeOnly("Enable macro paradise to expand macro annotations.")
class Sim extends StaticAnnotation {
  def macroTransform(annottees: Any*): Any = macro SimMacro.impl
}

// class dbg_Sim extends StaticAnnotation {
//   def macroTransform(annottees: Any*): Any = macro SimMacro.impl
// }

// Inject code for asyncMessage, wait, and handleMessages
object rewriteBehavior {
     def apply(c: blackbox.Context)(t: c.universe.Tree)(API_methods_meta: List[c.universe.Tree]): c.universe.Tree = {
        import c.universe._

        var handleMessageCode: ListBuffer[c.universe.Tree] = new ListBuffer[c.universe.Tree]()

        def handleAPI(x: List[c.universe.Tree]): Unit = {
            x match {
                case Nil => 
                case head :: tail =>
                    head match {
                        case q"$tname[..$tparams](...$paramss)" => 
                            val ans = if (!paramss.isEmpty && !paramss.head.isEmpty) {
                                
                                val argss = paramss.head.zipWithIndex.map(x => {
                                    x._1 match {
                                        case q"$mods val $tn: $tpt = $expr" => 
                                            q"args(${x._2}).asInstanceOf[$tpt]"
                                        case q"$mods var $tn: $tpt = $expr" => 
                                            q"args(${x._2}).asInstanceOf[$tpt]"
                                    }
                                })
                                
                                q""" if (x == ${tname.toString}) {
                                  val reply = $tname(..$argss)
                                  m.reply(this, reply)
                                }
                                """
                            } else {
                                q""" if (x == ${tname.toString}) {      
                                    val reply = $tname(...$paramss)
                                    m.reply(this, reply)
                                }
                                """
                            }
                            handleMessageCode.append(ans)
                            handleAPI(tail)
                        case _ =>
                    }
            }
        }

        handleAPI(API_methods_meta)

        def recursive(t: Tree): Tree = {

            t match {
                // Two or more sequential case
                case q"$a ; $b; ..$c" => 
                    val f = c.map(i => q"${recursive(i)}")
                    q"${recursive(a)}; ${recursive(b)}; ..$f"

                case q"val $lhs = $rhs" =>
                    q"val $lhs = ${recursive(rhs)}"

                case q"var $lhs = $rhs" =>
                    q"var $lhs = ${recursive(rhs)}"

                case q"$lhs = $rhs" =>
                    q"$lhs = ${recursive(rhs)}"    

                case q"while($cond) $body" =>
                    q"while(${recursive(cond)}) ${recursive(body)}"

                case q"waitLabel(Turn, $t)" =>

                    q"""elapsed_time_counter = 0;
                    while (elapsed_time_counter < $t) {
                        elapsed_time_counter += 1;
                        org.coroutines.yieldval(sendMessages.toList);
                        sendMessages.clear()
                    }
                    """
                case q"handleMessages()" => 
                    q"""receivedRequests = popRequestMessages

                        receivedRequests.foreach(m => {
                            m.methodInfo match {
                                case Left(x) => 
                                    val args = m.argss.flatten
                                    
                                    ..${handleMessageCode.toList}

                                case Right(x) => 
                            }
                        })
                      """

                case q"asyncMessage[..$ts](() => $receiver.$mtd(...$args))" => 
                    if (ts.isEmpty) {
                        throw new Exception("Error! Need to annotate the type of asyncMessage!")
                    }

                    val t = ts.head
                    q"""val request = new meta.runtime.RequestMessage(id, ${receiver}.id, false, Left(${mtd.toString}), $args);
                    var future = meta.runtime.Future[$t](request.sessionId); 
                    sendMessage(request);
                    setMessageResponseHandler(request.sessionId, (response: meta.runtime.Message) => {
                        future.setValue(response.asInstanceOf[meta.runtime.ResponseMessage].arg.asInstanceOf[$t])
                    });
                    future
                    """
                case q"if ($cond) $thenp else $elsep" => 
                    q"if (${recursive(cond)}) ${recursive(thenp)} else ${recursive(elsep)}"

                case q"$x(..$exp)" =>    // member selection
                    val f = exp.map(i => q"${recursive(i)}")
                    q"$x(..${f})"

                case q"(..$params) => $body" =>
                    q"(..$params) => ${recursive(body)}"

                // case cq"$pat if $expr => $expr" =>
                                    
                // case q"$expr match { case ..$cases } " =>
                //     q"${recursive(expr)} match ${recursive(body)}"
                case _ => 
                    t
            }
        }

        recursive(t)
    }
}

object SimMacro {

  def impl(c: blackbox.Context)(annottees: c.Expr[Any]*): c.Expr[Any] = {
    import c.universe._
    import Flag._

   val result = {
      annottees.map(_.tree).toList match {
        // Leave out the companion object for now
        // case q"object $name extends ..$parents { ..$methods }" :: Nil =>
        //     q"""
        //         object $name extends ..$parents {
        //         ..$methods
        //         }
        //     """

        case q"class $name (..$args) extends ..$parents { ..$methods }" :: Nil =>

            // regular methods include both API methods and non-API
            val regularMethods = methods.filterNot(m => {
                m.isDef && m.asInstanceOf[DefTree].name.toString == "main"
            })

           // meta info of API methods 
            val API_methods_meta = regularMethods.map(x => 
            x match {
                case q"$mods def $tname[..$tparams](...$paramss): $tpt = $expr" if mods.flags!=PRIVATE => {
                    q"$tname[..$tparams](...$paramss)"
                }
                case _ => q""
            }).filterNot(_.isEmpty)

            val mainMethod = methods.diff(regularMethods).head

            assert(!mainMethod.isEmpty)

            val m = mainMethod.asInstanceOf[DefTree]

            val mainBehaviour = rewriteBehavior(c)(m.children.last)(API_methods_meta)

            val ans = q"""

                class $name (..$args) extends ..$parents {
                    var elapsed_time_counter: Int = 0;
                    var receivedRequests: List[meta.runtime.RequestMessage] = List();
                    ..$regularMethods
                
                    override def run() = org.coroutines.coroutine {() => {
                        $mainBehaviour
                    }}
                }
            """

            // println(showCode(ans))
            ans
      }
    }
    c.Expr[Any](result)
  }
}