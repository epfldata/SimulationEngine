package meta.deep.algo

import meta.deep.IR.Predef._

case class WaitLabel[R](label: OpenCode[String],
                        value: OpenCode[Double])
                       (implicit val R: CodeType[R])
  extends Algo[R] {
  override def codegen(): Unit = {

    val waitCounter = Variable[Double]

    value match {
      case code"${Const(n)}: Double" =>
        if (n <= 0) {
          throw new Exception("The waitLabel takes a positive value!")
        }
      case _ =>   //  If variable turn number, skip the check
    }

    val f =
          LetBinding(
            Some(waitCounter),
            ScalaCode(code"0.0"),
            DoWhile(code"$waitCounter < $value",
              LetBinding(None,
                        LetBinding(None,      // push the waitValue to the stack
                                  ScalaCode(code"meta.runtime.SimRuntime.labelVals($label).append($value - $waitCounter)"),
                                  Wait()),
                        LetBinding(Some(waitCounter),
                                  ScalaCode(code"$waitCounter + meta.runtime.SimRuntime.proceedLabel($label)"),
                                  NoOp()))))
    f.codegen()
  }
}
