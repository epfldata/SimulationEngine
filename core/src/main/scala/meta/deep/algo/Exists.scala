package meta.deep.algo

import meta.deep.IR.Predef._

case class Exists[E](ls: OpenCode[List[E]],
                         variable: Variable[E],
                         body: Algo[Boolean])
                        (implicit val E: CodeType[E]) extends Algo[Boolean] {

  override def codegen(): Unit = {
    val res = Variable[Boolean]
    val el = Variable[Boolean]

    val f = LetBinding(Some(res),
      ScalaCode(code"false"),
      LetBinding(Some(el),
        ScalaCode(code"false"),
        LetBinding(None,
          Foreach[E, Unit](ls, variable,
            LetBinding(
              Some(el),
              body,
              IfThenElse(code"$el",
                LetBinding(Some(res),
                  ScalaCode(code"true"), NoOp()),
                NoOp()))),
          ScalaCode(code"$res"))))

    f.codegen()
  }
}