package meta.deep.member

/**
* Wrapper class for modeling a node id, which can ether be a method id or a position
*/
abstract class CodeNode {
    def getId: String

    def getNativeId: Int
}

/**
* Helper class to save mappings between a variable introduced to a mutable variable, which is used in
* the program. This is necessary, since it is not possible to define var variable references in squid
* at the moment, thus we have to use the MutVar Wrapper to change the variable afterwards
*
* @param from original variable defined by Algo
* @param to   new mutation variable created as replacement for original one
* @param A    code type of original variable
* @tparam C type of original variable
*/
// case class VarWrapper[C](from: Variable[C], to: Variable[MutVar[C]])(
//     implicit val A: CodeType[C])

/**
* Node for a position in code
*
* @param pos id of posCounter
*/
case class CodeNodePos(pos: Int) extends CodeNode {
    override def toString: String = {
        pos.toString
    }

    override def getId: String = pos.toString

    override def getNativeId: Int = pos
}

/**
* method id, which should reference the node to
*
* @param id  methodId, which node should be referenced
* @param end if node is start or end of method
*/
case class CodeNodeMtd(id: Int, end: Boolean = false) extends CodeNode {
    override def getId: String = "M" + id + (if (end) "E" else "")

    override def getNativeId: Int = id
}