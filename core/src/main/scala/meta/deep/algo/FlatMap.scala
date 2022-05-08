package meta.deep.algo

import meta.deep.IR.Predef._

case class FlatMap[E, R](ls: OpenCode[Iterable[E]],
                         variable: Variable[E],
                         body: Algo[Iterable[R]])
                        (implicit val E: CodeType[E], implicit val R: CodeType[R]) extends Algo[R] {

  override def codegen(): Unit = {
    val res = Variable[Iterable[R]]
    val el = Variable[Iterable[R]]

    val f = LetBinding(Some(res),
      ScalaCode(code"Iterable[$R]()"),
      Foreach[E, Unit](ls, variable,
        LetBinding(
          Some(res),
          LetBinding(
            Some(el),
            body,
            ScalaCode(code"$res ++ $el")),
          NoOp()
        )),
    )

    f.codegen()
  }
}
