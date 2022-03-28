package generated.meta.test.timeseries

class CounterSim(val n: generated.meta.test.timeseries.CounterSim) extends meta.runtime.Actor {

  var state: Int = 1;
  private var  reflectionIR_91: Int = -1
  var resetData_0: scala.Any = null
  val resetData_1 = scala.collection.mutable.ListBuffer.apply[scala.collection.immutable.List[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]]]()
  var resetData_2: meta.runtime.ResponseMessage = null
  var bindingMut_3: scala.Any = null
  var listValMut_4: meta.runtime.RequestMessage = null
  @transient var iterMut_5: scala.collection.Iterator[meta.runtime.RequestMessage] = null
  var bindingMut_6: scala.Double = 0.0
  var bindingMut_7: java.lang.String = null
  var bindingMut_8: scala.Int = 0
  var bindingMut_9: java.lang.String = null
  var bindingMut_10: scala.Long = 0L
  var bindingMut_11: java.lang.String = null
  var bindingMut_12: scala.Long = 0L
  var bindingMut_13: scala.Boolean = false
  var bindingMut_14: generated.meta.test.timeseries.CounterSim = null
  var unblockFlag_15: scala.Boolean = true
  var positionVar_16: scala.Int = 0
  
  val commands_122 = (() => {
  val data_17 = new scala.Array[scala.Function0[scala.Unit]](27);
  data_17.update(0, (() => positionVar_16 = 1));
  data_17.update(1, (() => {
    val x_18 = this.n;
    resetData_0 = x_18;
    val x_19 = resetData_0;
    val x_20 = x_19.asInstanceOf[generated.meta.test.timeseries.CounterSim];
    bindingMut_14 = x_20;
    val x_21 = bindingMut_14;
    val x_22 = x_21.!=(null);
    resetData_0 = x_22;
    val x_23 = resetData_0;
    val x_24 = x_23.asInstanceOf[scala.Boolean];
    bindingMut_13 = x_24;
    positionVar_16 = 2
  }));
  data_17.update(2, (() => {
    val x_25 = bindingMut_13;
    val x_26 = x_25.`unary_!`;
    if (x_26)
      positionVar_16 = 3
    else
      positionVar_16 = 25
  }));
  data_17.update(3, (() => {
    positionVar_16 = 4;
    val x_27 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
    val x_28 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_27, 24);
    val x_29 = scala.collection.immutable.Nil.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_28);
    resetData_1.prepend(x_29)
  }));
  data_17.update(4, (() => {
    val x_30 = this.popRequestMessages;
    val x_31 = x_30.iterator;
    iterMut_5 = x_31;
    positionVar_16 = 5
  }));
  data_17.update(5, (() => {
    val x_32 = iterMut_5;
    val x_33 = x_32.hasNext;
    if (x_33)
      {
        val x_34 = iterMut_5;
        val x_35 = x_34.next();
        listValMut_4 = x_35;
        positionVar_16 = 6
      }
    else
      positionVar_16 = 14
  }));
  data_17.update(6, (() => {
    val x_36 = listValMut_4;
    val x_37 = x_36.methodInfo;
    val x_38 = scala.`package`.Right.apply[scala.Nothing, scala.Int](0);
    val x_39 = x_37.==(x_38);
    if (x_39)
      positionVar_16 = 7
    else
      positionVar_16 = 13
  }));
  data_17.update(7, (() => {
    val x_40 = listValMut_4;
    this.handleNonblockingMessage(x_40);
    resetData_0 = ();
    positionVar_16 = 8
  }));
  data_17.update(8, (() => positionVar_16 = 9));
  data_17.update(9, (() => positionVar_16 = 5));
  data_17.update(10, (() => positionVar_16 = 11));
  data_17.update(11, (() => {
    val x_41 = this.id;
    val x_42 = x_41.+(" processes inc message!");
    scala.Predef.println(x_42);
    val x_43 = this.state;
    val x_44 = x_43.+(1);
    this.`state_=`(x_44);
    resetData_0 = ();
    positionVar_16 = 12
  }));
  data_17.update(12, (() => {
    val x_45 = resetData_0;
    val x_46 = x_45.asInstanceOf[scala.Any];
    bindingMut_3 = x_46;
    val x_47 = bindingMut_3;
    val x_48 = listValMut_4;
    x_48.reply(this, x_47);
    resetData_0 = ();
    positionVar_16 = 8
  }));
  data_17.update(13, (() => {
    val x_49 = listValMut_4;
    val x_50 = x_49.methodInfo;
    val x_51 = scala.`package`.Right.apply[scala.Nothing, scala.Int](0);
    val x_52 = x_50.==(x_51);
    val x_53 = x_52.`unary_!`;
    if (x_53)
      {
        val x_54 = listValMut_4;
        val x_55 = scala.collection.immutable.List.apply[meta.runtime.RequestMessage](x_54);
        val x_56 = this.addReceiveMessages(x_55);
        resetData_0 = x_56;
        positionVar_16 = 9
      }
    else
      ()
  }));
  data_17.update(14, (() => {
    val x_57 = iterMut_5;
    val x_58 = x_57.hasNext;
    val x_59 = x_58.`unary_!`;
    if (x_59)
      {
        val x_60 = resetData_1.remove(0);
        val x_64 = x_60.find(((x_61: scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]) => {
          val x_62 = x_61._1;
          val x_63 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
          x_62.==(x_63)
        }));
        val x_65 = x_64.get;
        val x_66 = x_65._2;
        positionVar_16 = x_66
      }
    else
      ()
  }));
  data_17.update(15, (() => positionVar_16 = 16));
  data_17.update(16, (() => {
    val x_67 = bindingMut_6;
    val x_68 = x_67.<(1.0);
    if (x_68)
      positionVar_16 = 17
    else
      positionVar_16 = 19
  }));
  data_17.update(17, (() => {
    val x_69 = bindingMut_6;
    val x_70 = x_69.+(1);
    resetData_0 = x_70;
    val x_71 = resetData_0;
    val x_72 = x_71.asInstanceOf[scala.Double];
    bindingMut_6 = x_72;
    positionVar_16 = 18;
    unblockFlag_15 = false
  }));
  data_17.update(18, (() => {
    positionVar_16 = 4;
    val x_73 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
    val x_74 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_73, 15);
    val x_75 = scala.collection.immutable.Nil.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_74);
    resetData_1.prepend(x_75)
  }));
  data_17.update(19, (() => {
    val x_76 = bindingMut_6;
    val x_77 = x_76.<(1.0);
    val x_78 = x_77.`unary_!`;
    if (x_78)
      positionVar_16 = 20
    else
      ()
  }));
  data_17.update(20, (() => positionVar_16 = 1));
  data_17.update(21, (() => positionVar_16 = 22));
  data_17.update(22, (() => {
    positionVar_16 = 23;
    unblockFlag_15 = false
  }));
  data_17.update(23, (() => positionVar_16 = 22));
  data_17.update(24, (() => {
    val x_79 = this.id;
    resetData_0 = x_79;
    val x_80 = resetData_0;
    val x_81 = x_80.asInstanceOf[scala.Long];
    bindingMut_10 = x_81;
    val x_82 = bindingMut_10;
    val x_83 = x_82.+(" counter value is ");
    resetData_0 = x_83;
    val x_84 = resetData_0;
    val x_85 = x_84.asInstanceOf[java.lang.String];
    bindingMut_9 = x_85;
    val x_86 = this.state;
    resetData_0 = x_86;
    val x_87 = resetData_0;
    val x_88 = x_87.asInstanceOf[scala.Int];
    bindingMut_8 = x_88;
    val x_89 = bindingMut_8;
    val x_90 = bindingMut_9;
    val x_91 = x_90.+(x_89);
    resetData_0 = x_91;
    val x_92 = resetData_0;
    val x_93 = x_92.asInstanceOf[java.lang.String];
    bindingMut_7 = x_93;
    val x_94 = bindingMut_7;
    scala.Predef.println(x_94);
    resetData_0 = ();
    resetData_0 = 0.0;
    val x_95 = resetData_0;
    val x_96 = x_95.asInstanceOf[scala.Double];
    bindingMut_6 = x_96;
    positionVar_16 = 17
  }));
  data_17.update(25, (() => {
    val x_97 = bindingMut_13;
    squid.lib.`package`.IfThenElse[scala.Unit](x_97, {
      val receiver_98 = this.n;
      val x_99 = ((this): meta.runtime.Actor).id;
      val x_100 = receiver_98.id;
      val x_101 = scala.`package`.Right.apply[scala.Nothing, scala.Int](0);
      val x_102 = scala.collection.immutable.Nil.::[scala.collection.immutable.List[scala.Any]](((scala.collection.immutable.Nil): scala.collection.immutable.List[scala.Any]));
      val x_103 = meta.runtime.RequestMessage.apply(x_99, x_100, false, x_101, x_102);
      val x_104 = x_103.sessionId;
      val x_105 = meta.runtime.Future.apply$default$2[scala.Unit];
      val x_106 = meta.runtime.Future.apply[scala.Unit](x_104, x_105);
      var v_107: meta.runtime.Future[scala.Unit] = x_106;
      ((this): meta.runtime.Actor).sendMessage(x_103);
      val x_108 = x_103.sessionId;
      ((this): meta.runtime.Actor).setMessageResponseHandler(x_108, ((response_109: meta.runtime.Message) => {
        val x_110 = v_107;
        val x_111 = response_109.asInstanceOf[meta.runtime.ResponseMessage];
        val x_112 = x_111.arg;
        x_112.asInstanceOf[scala.Unit];
        x_110.setValue(())
      }));
      val x_113 = v_107;
      resetData_0 = x_113;
      val x_114 = this.id;
      resetData_0 = x_114;
      val x_115 = resetData_0;
      val x_116 = x_115.asInstanceOf[scala.Long];
      bindingMut_12 = x_116;
      val x_117 = bindingMut_12;
      val x_118 = x_117.+(" sends a message to increment the neighbor!");
      resetData_0 = x_118;
      val x_119 = resetData_0;
      val x_120 = x_119.asInstanceOf[java.lang.String];
      bindingMut_11 = x_120;
      val x_121 = bindingMut_11;
      scala.Predef.println(x_121);
      resetData_0 = ();
      positionVar_16 = 3
    }, ())
  }));
  data_17.update(26, (() => positionVar_16 = 22));
  data_17
}).apply();
  

  def inc(): Unit = 
      {
  val x_0 = this.id;
  val x_1 = x_0.+(" processes inc message!");
  scala.Predef.println(x_1);
  val x_2 = this.state;
  val x_3 = x_2.+(1);
  this.`state_=`(x_3)
}
  
  def wrapper_inc(args: List[Any]): Unit = {
    
          
          inc()
          
  }
  
  override def run(msgs: List[meta.runtime.Message]): (List[meta.runtime.Message], Int) = {
    addReceiveMessages(msgs)
    sendMessages.clear()
    unblockFlag_15 = true
    while (unblockFlag_15 && (positionVar_16 < 27)) {
      commands_122(positionVar_16)()
    }
    (sendMessages.toList, 1)
  }
  
  override def handleNonblockingMessage(m: meta.runtime.RequestMessage): Unit = {
    val args = m.argss.flatten
    val response = m.methodInfo match {
      case Right(x) => {
        x match {
          case 0 => wrapper_inc(args)
        }
      }
      case Left(x) => println("For staged implementation only")
    }
    m.reply(this, response)
  }
  
    override def gotoHandleMessages(new_ir: Int = 3): meta.runtime.Actor = {
      // first entry, save the current IR to reflectionIR
      unblockFlag_15 = true

      if (reflectionIR_91 == -1){
        reflectionIR_91 = positionVar_16
        positionVar_16 = new_ir
      }

      while (positionVar_16 <= 13 && unblockFlag_15) {
        commands_122(positionVar_16)()
      }

      // reset instruction register when finishes processing
      if (positionVar_16 > 13) {
        positionVar_16 = reflectionIR_91
        reflectionIR_91 = -1
      }
      this
    }
    
override def stateClone(): CounterSim = {
  val newAgent = new CounterSim(n)
  newAgent.state = state
  newAgent
}

}
