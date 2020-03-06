package meta.example.codegen_example

import meta.deep.IR
import meta.deep.IR.Predef._
import meta.deep.IR.TopLevel._
import meta.deep.algo._
import meta.deep.codegen._
import meta.deep.member.{ActorType, LiftedMethod, State}
import meta.deep.runtime.RequestMessage

object CodegenExample extends App {

  val marketActorType = marketLifted()
  val controlFlowTest = controlFlowTestLifted()
  val farmerActorType = farmerLifted(
    marketActorType.methods
      .find(_.sym.asMethodSymbol.name.toString == "sell2")
      .get)
  val actorTypes
    : List[ActorType[_]] = marketActorType :: farmerActorType :: controlFlowTest :: Nil
  val pipeline = Pipeline(
    new CreateActorGraphs(actorTypes),
    List(
      //new ActorMerge(),
      new EdgeMerge(),
      new CreateCode(
        code"""val m = new Market; val f = new Farmer(); f.market = m; List(m, f)""",
        "generated/main/scala"),
    )
  )

  def marketLifted(): ActorType[Market] = {
    val m: ClassWithObject[Market] = Market.reflect(IR)

    val mS = m.methods.find(_.symbol.asMethodSymbol.name.toString == "sell").get
    val mSB =
      m.methods.find(_.symbol.asMethodSymbol.name.toString == "sell2").get
    val rF = m.methods
      .find(_.symbol.asMethodSymbol.name.toString == "recursiveTest")
      .get

    val marketSell: LiftedMethod[Unit] = new LiftedMethod[Unit](
      m,
      ScalaCode(code"""println("Market sells: " + ${mS.vparamss.head.head})"""),
      false,
      1) {
      override val mtd: cls.Method[Unit, cls.Scp] =
        mS.asInstanceOf[this.cls.Method[Unit, cls.Scp]]
    }
    val marketSellB: LiftedMethod[Int] = new LiftedMethod[Int](
      m,
      ScalaCode[Int](
        code"""println("Market sells: " + ${mSB.vparamss.head.head}); 42"""),
      true,
      2) {
      override val mtd: cls.Method[Int, cls.Scp] =
        mSB.asInstanceOf[this.cls.Method[Int, cls.Scp]]
    }
    val marketSelf = Variable[Market]

    val rFParam1: Variable[_] = rF.vparamss.head.head
    val recursiveFunction: LiftedMethod[Unit] = new LiftedMethod[Unit](
      m,
      LetBinding(
        None,
        IfThenElse(
          code"$rFParam1.asInstanceOf[List[Int]].tail.isEmpty == false",
          CallMethod[Unit](
            3,
            List(List(code"$rFParam1.asInstanceOf[List[Int]].tail"))),
          NoOp[Unit]()
        ),
        ScalaCode(code"""println($rFParam1.asInstanceOf[List[Int]].head);"""),
      ),
      false,
      3
    ) {
      override val mtd: cls.Method[Unit, cls.Scp] =
        rF.asInstanceOf[this.cls.Method[Unit, cls.Scp]]
    }

    val resultMessageCall = Variable[Any]

    val p1 = Variable[RequestMessage]
    //Handle later

    val marketFunctions = marketSell :: marketSellB :: recursiveFunction :: Nil

    val algo: Algo[Any] = NoOp()
    val callCode = marketFunctions.zipWithIndex.foldRight(algo)((a, b) => {
      val argss: List[List[OpenCode[_]]] =
        a._1.mtd.vparamss.zipWithIndex.map(x => {
          x._1.zipWithIndex.map(y => {
            code"$p1.argss(${Const(x._2)})(${Const(y._2)})"
          })
        })
      IfThenElse(code"$p1.methodId==${Const(a._2 + 1)}",
                 CallMethod[Any](a._2 + 1, argss),
                 b)
    })

    val handleMessage = Foreach(
      code"$marketSelf.popRequestMessages",
      p1,
      LetBinding(
        Option(resultMessageCall),
        callCode,
        ScalaCode(code"""$p1.reply($marketSelf, $resultMessageCall)""")
      )
    )

    val bindingTest = Variable[Int]

    val market = ActorType[Market](
      "Market",
      List("meta.deep.runtime.Actor"),
      List(),
      State[List[String]](IR.methodSymbol[Market]("goods"),
                          codeTypeOf[List[String]],
                          code"Nil") :: Nil,
      marketFunctions,
      LetBinding(
        Option(bindingTest),
        ScalaCode[Int](code"0"),
        DoWhile(
          code"true",
          LetBinding(
            None,
            handleMessage,
            LetBinding(
              None,
              LetBinding(
                Option(bindingTest),
                ScalaCode[Int](code"$bindingTest + 1"),
                ScalaCode(code"""println("Binding test:",$bindingTest)""")),
              LetBinding(
                None,
                CallMethod[Unit](marketSell.methodId, List(List(code"10"))),
                LetBinding(None,
                           CallMethod[Unit](recursiveFunction.methodId,
                                            List(List(code"List(10,20,30)"))),
                           Wait())
              )
            )
          )
        )
      ),
      marketSelf
    )

    market
  }

  def farmerLifted(marketSellB: LiftedMethod[_]): ActorType[Farmer] = {
    val f: ClassWithObject[Farmer] = Farmer.reflect(IR)
    val te = f.methods.find(_.symbol.asMethodSymbol.name.toString == "tell").get
    val nP =
      f.methods.find(_.symbol.asMethodSymbol.name.toString == "notifyPeers").get

    val farmerSelf = Variable[Farmer]
    val testResult = Variable[Int]

    val tellP2: Variable[Int] =
      te.vparamss.head.tail.head.asInstanceOf[Variable[Int]]
    val tell: LiftedMethod[Unit] = new LiftedMethod[Unit](
      f,
      ScalaCode(
        code"$farmerSelf.happiness = $farmerSelf.happiness - $tellP2; ()"),
      true,
      4) {
      override val mtd: cls.Method[Unit, cls.Scp] =
        te.asInstanceOf[this.cls.Method[Unit, cls.Scp]]
    }

    val p2 = Variable[Farmer]
    val notifyPeers: LiftedMethod[Unit] = new LiftedMethod[Unit](
      f,
      Foreach[Farmer, Unit](
        code"$farmerSelf.peers",
        p2,
        Send[Unit](
          code"$farmerSelf",
          code"$p2",
          tell.methodId,
          List(List(code"$farmerSelf", code"$farmerSelf.happiness")),
          blocking = false
        )
      ),
      false,
      5
    ) {
      override val mtd: cls.Method[Unit, cls.Scp] =
        nP.asInstanceOf[this.cls.Method[Unit, cls.Scp]]
    }

    val farmerFunctions = tell :: notifyPeers :: Nil
    val p1F = Variable[RequestMessage]

    val algo: Algo[Any] = NoOp()
    val callCode = farmerFunctions.zipWithIndex.foldRight(algo)((a, b) => {
      val argss: List[List[OpenCode[_]]] =
        a._1.mtd.vparamss.zipWithIndex.map(x => {
          x._1.zipWithIndex.map(y => {
            code"$p1F.argss(${Const(x._2)})(${Const(y._2)})"
          })
        })
      IfThenElse(code"$p1F.methodId==${Const(a._2 + 1 + 3)}",
                 CallMethod[Any](a._2 + 1 + 3, argss),
                 b)
    })

    val resultMessageCallF = Variable[Any]

    val handleMessage = Foreach(
      code"$farmerSelf.popRequestMessages",
      p1F,
      LetBinding(
        Option(resultMessageCallF),
        callCode,
        ScalaCode(code"""$p1F.reply($farmerSelf, $resultMessageCallF)""")
      )
    )

    val farmer = ActorType[Farmer](
      "Farmer",
      List("meta.deep.runtime.Actor"),
      List(),
      State[Market](IR.methodSymbol[Farmer]("market"),
                    codeTypeOf[Market],
                    nullValue[Market].asOpenCode) ::
        State[Int](IR.methodSymbol[Farmer]("happiness"),
                   codeTypeOf[Int],
                   code"0") ::
        State[List[Farmer]](IR.methodSymbol[Farmer]("peers"),
                            codeTypeOf[List[Farmer]],
                            code"Nil") :: Nil,
      farmerFunctions,
      DoWhile(
        code"true",
        LetBinding(
          None,
          handleMessage,
          LetBinding(
            None,
            LetBinding(
              Option(testResult),
              Send[Int](
                code"$farmerSelf",
                code"$farmerSelf.market",
                marketSellB.methodId,
                List(List(code"500")),
                blocking = true
              ),
              ScalaCode(code"""println("TEST_VAR",$testResult)""")
            ),
            Wait()
          )
        )
      ),
      farmerSelf
    )
    farmer
  }

  def controlFlowTestLifted(): ActorType[ControlFlowTestObject] = {
    /*val c: ClassWithObject[ControlFlowTestObject] =
      ControlFlowTestObject.reflect(IR)*/

    val cFself = Variable[ControlFlowTestObject]

    val cF = ActorType[ControlFlowTestObject](
      "ControlFlowTestObject",
      List("meta.deep.runtime.Actor"),
      List(),
      State[Int](IR.methodSymbol[ControlFlowTestObject]("x"),
                 codeTypeOf[Int],
                 code"0") ::
        State[Int](IR.methodSymbol[ControlFlowTestObject]("y"),
                   codeTypeOf[Int],
                   code"0") ::
        Nil,
      Nil,
      DoWhile(code"true",
              LetBinding(None,
                         IfThenElse[Unit](code"$cFself.x < 0",
                                          Wait(),
                                          IfThenElse[Unit](code"$cFself.y < 0",
                                                           Wait(),
                                                           NoOp())),
                         Wait())),
      cFself
    )

    cF
  }

  pipeline.run()

}
