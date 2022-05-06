package generated.meta.test.custAgent

class NewSim2(val n: generated.meta.test.custAgent.NewSim2) extends meta.runtime.Actor with meta.test.custAgent.custActor {


  private var  reflectionIR_86: Int = -1
private var resetData_0: scala.Any = null
private val resetData_1 = scala.collection.mutable.ListBuffer.apply[scala.collection.immutable.List[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]]]()
private var resetData_2: meta.runtime.ResponseMessage = null
private var bindingMut_3: scala.Any = null
private var listValMut_4: meta.runtime.RequestMessage = null
@transient private var iterMut_5: scala.collection.Iterator[meta.runtime.RequestMessage] = null
private var bindingMut_6: scala.Double = 0.0
private var bindingMut_7: scala.Boolean = false
private var bindingMut_8: generated.meta.test.custAgent.NewSim2 = null
private var bindingMut_9: scala.Boolean = false
private var bindingMut_10: generated.meta.test.custAgent.NewSim2 = null
private var bindingMut_11: java.lang.String = null
private var bindingMut_12: scala.Long = 0L
private var unblockFlag_13: scala.Boolean = true
private var positionVar_14: scala.Int = 0
private 
  val commands_99 = (() => {
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
    val x_47 = x_46.==("someMtd");
    if (x_47)
      positionVar_14 = 9
    else
      positionVar_14 = 15
  }));
  data_15.update(9, (() => {
    val x_48 = listValMut_4;
    this.handleNonblockingMessage(x_48);
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
    val x_49 = resetData_0;
    val x_50 = x_49.asInstanceOf[scala.Any];
    bindingMut_3 = x_50;
    val x_51 = bindingMut_3;
    val x_52 = listValMut_4;
    x_52.reply(this, x_51);
    resetData_0 = ();
    positionVar_14 = 10
  }));
  data_15.update(15, (() => {
    val x_53 = listValMut_4;
    val x_54 = x_53.methodInfo;
    val x_55 = x_54.==("someMtd");
    val x_56 = x_55.`unary_!`;
    if (x_56)
      {
        val x_57 = listValMut_4;
        val x_58 = scala.collection.immutable.List.apply[meta.runtime.RequestMessage](x_57);
        val x_59 = this.addReceiveMessages(x_58);
        resetData_0 = x_59;
        positionVar_14 = 11
      }
    else
      ()
  }));
  data_15.update(16, (() => {
    val x_60 = iterMut_5;
    val x_61 = x_60.hasNext;
    val x_62 = x_61.`unary_!`;
    if (x_62)
      positionVar_14 = 17
    else
      ()
  }));
  data_15.update(17, (() => positionVar_14 = 18));
  data_15.update(18, (() => {
    val x_63 = bindingMut_6;
    val x_64 = x_63.<(1.0);
    if (x_64)
      positionVar_14 = 4
    else
      positionVar_14 = 19
  }));
  data_15.update(19, (() => {
    val x_65 = bindingMut_6;
    val x_66 = x_65.<(1.0);
    val x_67 = x_66.`unary_!`;
    if (x_67)
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
    val x_68 = bindingMut_9;
    squid.lib.`package`.IfThenElse[scala.Unit](x_68, {
      val x_69 = this.n;
      resetData_0 = x_69;
      val x_70 = resetData_0;
      val x_71 = x_70.asInstanceOf[generated.meta.test.custAgent.NewSim2];
      bindingMut_8 = x_71;
      val x_72 = this._container;
      val x_73 = x_72.!=(null);
      val x_78 = if (x_73)
        {
          val x_74 = this._container;
          val x_75 = x_74.proxyIds;
          val x_76 = bindingMut_8;
          val x_77 = x_76.id;
          x_75.contains[scala.Long](x_77)
        }
      else
        false;
      resetData_0 = x_78;
      val x_79 = resetData_0;
      val x_80 = x_79.asInstanceOf[scala.Boolean];
      bindingMut_7 = x_80;
      positionVar_14 = 25
    }, ())
  }));
  data_15.update(25, (() => {
    val x_81 = bindingMut_7;
    val x_82 = x_81.`unary_!`;
    if (x_82)
      {
        val x_83 = ((this): meta.runtime.Actor).id;
        val x_84 = bindingMut_8;
        val x_85 = x_84.id;
        val x_86 = scala.collection.immutable.Nil.::[scala.collection.immutable.List[scala.Any]](((scala.collection.immutable.Nil): scala.collection.immutable.List[scala.Any]));
        val x_87 = meta.runtime.RequestMessage.apply(x_83, x_85, true, "someMtd", x_86);
        ((this): meta.runtime.Actor).sendMessage(x_87);
        val x_88 = x_87.sessionId;
        ((this): meta.runtime.Actor).setMessageResponseHandler(x_88, ((response_89: meta.runtime.Message) => {
          val x_90 = response_89.asInstanceOf[meta.runtime.ResponseMessage];
          resetData_2 = x_90
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
    val x_91 = resetData_2;
    val x_92 = x_91.==(null);
    if (x_92)
      positionVar_14 = 26
    else
      positionVar_14 = 28
  }));
  data_15.update(28, (() => {
    val x_93 = resetData_2;
    val x_94 = x_93.!=(null);
    if (x_94)
      {
        val x_95 = resetData_2;
        val x_96 = x_95.arg;
        resetData_0 = x_96;
        resetData_2 = null;
        positionVar_14 = 29
      }
    else
      ()
  }));
  data_15.update(29, (() => positionVar_14 = 3));
  data_15.update(30, (() => {
    val x_97 = bindingMut_7;
    squid.lib.`package`.IfThenElse[scala.Unit](x_97, {
      val x_98 = bindingMut_8;
      x_98.someMtd();
      resetData_0 = ();
      positionVar_14 = 29
    }, ())
  }));
  data_15.update(31, (() => positionVar_14 = 22));
  data_15
}).apply();
  

   def someMtd(): Unit = 
      scala.Predef.println("Hello world!")
  
  private def wrapper_someMtd(args: List[Any]): Unit = {
    
          
          someMtd()
          
  }
  
  override def run(msgs: List[meta.runtime.Message]): (List[meta.runtime.Message], Int) = {
    addReceiveMessages(msgs)
    sendMessages.clear()
    unblockFlag_13 = true
    while (unblockFlag_13 && (positionVar_14 < 32)) {
      commands_99(positionVar_14)()
    }
    (sendMessages.toList, 1)
  }

  override def handleNonblockingMessage(m: meta.runtime.RequestMessage): Unit = {
    val args = m.argss.flatten
    val response = m.methodInfo match {
      case "someMtd" => wrapper_someMtd(args)
    }
    m.reply(this, response)
  }
  
    override def gotoHandleMessages(new_ir: Int = 5): meta.runtime.Actor = {
      // first entry, save the current IR to reflectionIR
      unblockFlag_13 = true

      if (reflectionIR_86 == -1){
        reflectionIR_86 = positionVar_14
        positionVar_14 = new_ir
      }

      while (positionVar_14 <= 15 && unblockFlag_13) {
        commands_99(positionVar_14)()
      }

      // reset instruction register when finishes processing
      if (positionVar_14 > 15) {
        positionVar_14 = reflectionIR_86
        reflectionIR_86 = -1
      }
      this
    }
    
override def SimClone(): NewSim2 = {
  val newAgent = new NewSim2(n)

  newAgent
}

override def SimReset(): Unit = {
  positionVar_14 = 0
  
}

}
