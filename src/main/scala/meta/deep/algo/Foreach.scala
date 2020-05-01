package meta.deep.algo

import meta.deep.IR.Predef._
import squid.lib.MutVar

case class Foreach[E, R: CodeType](ls: OpenCode[List[E]],
                                   variable: Variable[E],
                                   f: Algo[R])(implicit val E: CodeType[E])
    extends Algo[Unit] {

  /**
    * 1. Get iterator to iterate over list
    * 2. As long as there is a next value, get next value and execute algo
    * 3. If there is no next value, jump to end of the code fragment
    *
    * This code adds a iter variable and a list variable as needed initialization to the variables list.
    *
    */
  override def codegen(): Unit = {
    val iter = Variable[Iterator[E]]
    val iterMut = Variable[MutVar[Iterator[E]]]
    val listValMut = Variable[MutVar[E]]

    AlgoInfo.variables = AlgoInfo.VarWrapper(iter, iterMut) :: AlgoInfo.variables
    AlgoInfo.variables = AlgoInfo.VarWrapper(variable, listValMut) :: AlgoInfo.variables

    val f1 = code"""$iterMut := $ls.iterator; ()"""

    AlgoInfo.stateGraph.append(
      AlgoInfo.EdgeInfo("Foreach f1",
                        AlgoInfo.CodeNodePos(AlgoInfo.posCounter),
                        AlgoInfo.CodeNodePos(AlgoInfo.posCounter + 1),
                        f1))
    AlgoInfo.nextPos()

    AlgoInfo.stateGraph.append(
      AlgoInfo.EdgeInfo("Foreach f2 if",
                        AlgoInfo.CodeNodePos(AlgoInfo.posCounter),
                        AlgoInfo.CodeNodePos(AlgoInfo.posCounter + 1),
                        code"$listValMut := $iter.next; ()",
                        cond = code"$iter.hasNext"))
//    AlgoInfo.nextPos()
    //Pos of f2 has to be saved before calling createCode of f3!!!
    val tmpPos = AlgoInfo.posCounter
    AlgoInfo.nextPos()

    f.codegen()

    AlgoInfo.stateGraph.append(
      AlgoInfo.EdgeInfo("go back to foreach",
                        AlgoInfo.CodeNodePos(AlgoInfo.posCounter),
                        AlgoInfo.CodeNodePos(tmpPos),
                        code"()")
    )

    AlgoInfo.stateGraph.append(
      AlgoInfo.EdgeInfo("Foreach f2 else",
                        AlgoInfo.CodeNodePos(tmpPos),
                        AlgoInfo.CodeNodePos(AlgoInfo.posCounter + 1),
                        code"()",
                        cond = code"!($iter.hasNext)"))

    AlgoInfo.nextPos()

  }

}
