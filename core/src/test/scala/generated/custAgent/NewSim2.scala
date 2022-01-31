package generated.meta.test.custAgent

class NewSim2 (val n: generated.meta.test.custAgent.NewSim2) extends meta.runtime.Actor with meta.test.custAgent.custActor {


  private var  reflectionIR_6: Int = -1
  var resetData_0: scala.Any = null
  val resetData_1 = scala.collection.mutable.ListBuffer.apply[scala.collection.immutable.List[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]]]()
  var resetData_2: meta.runtime.ResponseMessage = null
  var bindingMut_3: scala.Any = null
  var listValMut_4: meta.runtime.RequestMessage = null
  @transient var iterMut_5: scala.collection.Iterator[meta.runtime.RequestMessage] = null
  var bindingMut_6: scala.Double = 0.0
  var bindingMut_7: scala.Boolean = false
  var bindingMut_8: generated.meta.test.custAgent.NewSim2 = null
  var bindingMut_9: scala.Boolean = false
  var bindingMut_10: generated.meta.test.custAgent.NewSim2 = null
  var bindingMut_11: java.lang.String = null
  var bindingMut_12: scala.Long = 0L
  var unblockFlag_13: scala.Boolean = true
  var positionVar_14: scala.Int = 0
  
  val commands_102 = (() => {
  val data_15 = new scala.Array[scala.Function0[scala.Unit]](32);
  data_15.update(0, (() => positionVar_14 = 1));
  data_15.update(1, (() => {
    val x_16 = this.id;
    resetData_0 = x_16;
    val x_17 = resetData_0;
    val x_18 = x_17.asInstanceOf[scala.Long];
    bindingMut_12 = x_18;
    val x_19 = bindingMut_12;
    val x_20 = "This is agent ".+(x_19);
    resetData_0 = x_20;
    val x_21 = resetData_0;
    val x_22 = x_21.asInstanceOf[java.lang.String];
    bindingMut_11 = x_22;
    val x_23 = bindingMut_11;
    scala.Predef.println(x_23);
    resetData_0 = ();
    val x_24 = this.n;
    resetData_0 = x_24;
    val x_25 = resetData_0;
    val x_26 = x_25.asInstanceOf[generated.meta.test.custAgent.NewSim2];
    bindingMut_10 = x_26;
    val x_27 = bindingMut_10;
    val x_28 = x_27.!=(null);
    resetData_0 = x_28;
    val x_29 = resetData_0;
    val x_30 = x_29.asInstanceOf[scala.Boolean];
    bindingMut_9 = x_30;
    positionVar_14 = 2
  }));
  data_15.update(2, (() => {
    val x_31 = bindingMut_9;
    val x_32 = x_31.`unary_!`;
    if (x_32)
      positionVar_14 = 3
    else
      positionVar_14 = 24
  }));
  data_15.update(3, (() => {
    resetData_0 = 0.0;
    val x_33 = resetData_0;
    val x_34 = x_33.asInstanceOf[scala.Double];
    bindingMut_6 = x_34;
    positionVar_14 = 4
  }));
  data_15.update(4, (() => {
    val x_35 = bindingMut_6;
    val x_36 = x_35.+(1);
    resetData_0 = x_36;
    val x_37 = resetData_0;
    val x_38 = x_37.asInstanceOf[scala.Double];
    bindingMut_6 = x_38;
    positionVar_14 = 5;
    unblockFlag_13 = false
  }));
  data_15.update(5, (() => positionVar_14 = 6));
  data_15.update(6, (() => {
    val x_39 = this.popRequestMessages;
    val x_40 = x_39.iterator;
    iterMut_5 = x_40;
    positionVar_14 = 7
  }));
  data_15.update(7, (() => {
    val x_41 = iterMut_5;
    val x_42 = x_41.hasNext;
    if (x_42)
      {
        val x_43 = iterMut_5;
        val x_44 = x_43.next();
        listValMut_4 = x_44;
        positionVar_14 = 8
      }
    else
      positionVar_14 = 16
  }));
  data_15.update(8, (() => {
    val x_45 = listValMut_4;
    val x_46 = x_45.methodInfo;
    val x_47 = scala.`package`.Right.apply[scala.Nothing, scala.Int](20);
    val x_48 = x_46.==(x_47);
    if (x_48)
      positionVar_14 = 9
    else
      positionVar_14 = 15
  }));
  data_15.update(9, (() => {
    val x_49 = listValMut_4;
    this.handleNonblockingMessage(x_49);
    resetData_0 = ();
    positionVar_14 = 10
  }));
  data_15.update(10, (() => positionVar_14 = 11));
  data_15.update(11, (() => positionVar_14 = 7));
  data_15.update(12, (() => positionVar_14 = 13));
  data_15.update(13, (() => {
    scala.Predef.println("Hello world!");
    resetData_0 = ();
    positionVar_14 = 14
  }));
  data_15.update(14, (() => {
    val x_50 = resetData_0;
    val x_51 = x_50.asInstanceOf[scala.Any];
    bindingMut_3 = x_51;
    val x_52 = bindingMut_3;
    val x_53 = listValMut_4;
    x_53.reply(this, x_52);
    resetData_0 = ();
    positionVar_14 = 10
  }));
  data_15.update(15, (() => {
    val x_54 = listValMut_4;
    val x_55 = x_54.methodInfo;
    val x_56 = scala.`package`.Right.apply[scala.Nothing, scala.Int](20);
    val x_57 = x_55.==(x_56);
    val x_58 = x_57.`unary_!`;
    if (x_58)
      {
        val x_59 = listValMut_4;
        val x_60 = scala.collection.immutable.List.apply[meta.runtime.RequestMessage](x_59);
        val x_61 = this.addReceiveMessages(x_60);
        resetData_0 = x_61;
        positionVar_14 = 11
      }
    else
      ()
  }));
  data_15.update(16, (() => {
    val x_62 = iterMut_5;
    val x_63 = x_62.hasNext;
    val x_64 = x_63.`unary_!`;
    if (x_64)
      positionVar_14 = 17
    else
      ()
  }));
  data_15.update(17, (() => positionVar_14 = 18));
  data_15.update(18, (() => {
    val x_65 = bindingMut_6;
    val x_66 = x_65.<(1.0);
    if (x_66)
      positionVar_14 = 4
    else
      positionVar_14 = 19
  }));
  data_15.update(19, (() => {
    val x_67 = bindingMut_6;
    val x_68 = x_67.<(1.0);
    val x_69 = x_68.`unary_!`;
    if (x_69)
      positionVar_14 = 20
    else
      ()
  }));
  data_15.update(20, (() => positionVar_14 = 1));
  data_15.update(21, (() => positionVar_14 = 22));
  data_15.update(22, (() => {
    positionVar_14 = 23;
    unblockFlag_13 = false
  }));
  data_15.update(23, (() => positionVar_14 = 22));
  data_15.update(24, (() => {
    val x_70 = bindingMut_9;
    squid.lib.`package`.IfThenElse[scala.Unit](x_70, {
      val x_71 = this.n;
      resetData_0 = x_71;
      val x_72 = resetData_0;
      val x_73 = x_72.asInstanceOf[generated.meta.test.custAgent.NewSim2];
      bindingMut_8 = x_73;
      val x_74 = this._container;
      val x_75 = x_74.!=(null);
      val x_80 = if (x_75)
        {
          val x_76 = this._container;
          val x_77 = x_76.proxyIds;
          val x_78 = bindingMut_8;
          val x_79 = x_78.id;
          x_77.contains[scala.Long](x_79)
        }
      else
        false;
      resetData_0 = x_80;
      val x_81 = resetData_0;
      val x_82 = x_81.asInstanceOf[scala.Boolean];
      bindingMut_7 = x_82;
      positionVar_14 = 25
    }, ())
  }));
  data_15.update(25, (() => {
    val x_83 = bindingMut_7;
    val x_84 = x_83.`unary_!`;
    if (x_84)
      {
        val x_85 = ((this): meta.runtime.Actor).id;
        val x_86 = bindingMut_8;
        val x_87 = x_86.id;
        val x_88 = scala.`package`.Right.apply[scala.Nothing, scala.Int](20);
        val x_89 = scala.collection.immutable.Nil.::[scala.collection.immutable.List[scala.Any]](((scala.collection.immutable.Nil): scala.collection.immutable.List[scala.Any]));
        val x_90 = meta.runtime.RequestMessage.apply(x_85, x_87, true, x_88, x_89);
        ((this): meta.runtime.Actor).sendMessage(x_90);
        val x_91 = x_90.sessionId;
        ((this): meta.runtime.Actor).setMessageResponseHandler(x_91, ((response_92: meta.runtime.Message) => {
          val x_93 = response_92.asInstanceOf[meta.runtime.ResponseMessage];
          resetData_2 = x_93
        }));
        resetData_0 = null;
        positionVar_14 = 26
      }
    else
      positionVar_14 = 30
  }));
  data_15.update(26, (() => {
    positionVar_14 = 27;
    unblockFlag_13 = false
  }));
  data_15.update(27, (() => {
    val x_94 = resetData_2;
    val x_95 = x_94.==(null);
    if (x_95)
      positionVar_14 = 26
    else
      positionVar_14 = 28
  }));
  data_15.update(28, (() => {
    val x_96 = resetData_2;
    val x_97 = x_96.!=(null);
    if (x_97)
      {
        val x_98 = resetData_2;
        val x_99 = x_98.arg;
        resetData_0 = x_99;
        resetData_2 = null;
        positionVar_14 = 29
      }
    else
      ()
  }));
  data_15.update(29, (() => positionVar_14 = 3));
  data_15.update(30, (() => {
    val x_100 = bindingMut_7;
    squid.lib.`package`.IfThenElse[scala.Unit](x_100, {
      val x_101 = bindingMut_8;
      x_101.someMtd();
      resetData_0 = ();
      positionVar_14 = 29
    }, ())
  }));
  data_15.update(31, (() => positionVar_14 = 22));
  data_15
}).apply();
  

  def someMtd(): Unit = 
      scala.Predef.println("Hello world!")
  
  def wrapper_someMtd(args: List[Any]): Unit = {
    
          
          someMtd()
          
  }
  
  override def run(msgs: List[meta.runtime.Message]): (List[meta.runtime.Message], Int) = {
    addReceiveMessages(msgs)
    sendMessages.clear()
    unblockFlag_13 = true
    while (unblockFlag_13 && (positionVar_14 < 32)) {
      commands_102(positionVar_14)()
    }
    (sendMessages.toList, 1)
  }
  
  override def handleNonblockingMessage(m: meta.runtime.RequestMessage): Unit = {
    val args = m.argss.flatten
    val response = m.methodInfo match {
      case Right(x) => {
        x match {
          case 20 => wrapper_someMtd(args)
        }
      }
      case Left(x) => println("For staged implementation only")
    }
    m.reply(this, response)
  }
  
    override def gotoHandleMessages(new_ir: Int = 5): meta.runtime.Actor = {
      // first entry, save the current IR to reflectionIR
      unblockFlag_13 = true

      if (reflectionIR_6 == -1){
        reflectionIR_6 = positionVar_14
        positionVar_14 = new_ir
      }

      while (positionVar_14 <= 15 && unblockFlag_13) {
        commands_102(positionVar_14)()
      }

      // reset instruction register when finishes processing
      if (positionVar_14 > 15) {
        positionVar_14 = reflectionIR_6
        reflectionIR_6 = -1
      }
      this
    }
    
}
