package generated.meta.test.timeseries

class CounterSim(val n: generated.meta.test.timeseries.CounterSim) extends meta.runtime.Actor {

  var state: Int = 1;
  val immutableSecret: Int = 10;
  private var  reflectionIR_33: Int = -1
  var resetData_0: scala.Any = null
  val resetData_1 = scala.collection.mutable.ListBuffer.apply[scala.collection.immutable.List[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]]]()
  var resetData_2: meta.runtime.ResponseMessage = null
  var bindingMut_3: scala.Any = null
  var listValMut_4: meta.runtime.RequestMessage = null
  @transient var iterMut_5: scala.collection.Iterator[meta.runtime.RequestMessage] = null
  var bindingMut_6: scala.Double = 0.0
  var bindingMut_7: scala.Boolean = false
  var bindingMut_8: generated.meta.test.timeseries.CounterSim = null
  var unblockFlag_9: scala.Boolean = true
  var positionVar_10: scala.Int = 0
  
  val commands_90 = (() => {
  val data_11 = new scala.Array[scala.Function0[scala.Unit]](27);
  data_11.update(0, (() => positionVar_10 = 1));
  data_11.update(1, (() => {
    val x_12 = this.n;
    resetData_0 = x_12;
    val x_13 = resetData_0;
    val x_14 = x_13.asInstanceOf[generated.meta.test.timeseries.CounterSim];
    bindingMut_8 = x_14;
    val x_15 = bindingMut_8;
    val x_16 = x_15.!=(null);
    resetData_0 = x_16;
    val x_17 = resetData_0;
    val x_18 = x_17.asInstanceOf[scala.Boolean];
    bindingMut_7 = x_18;
    positionVar_10 = 2
  }));
  data_11.update(2, (() => {
    val x_19 = bindingMut_7;
    val x_20 = x_19.`unary_!`;
    if (x_20)
      positionVar_10 = 3
    else
      positionVar_10 = 25
  }));
  data_11.update(3, (() => {
    positionVar_10 = 4;
    val x_21 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
    val x_22 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_21, 24);
    val x_23 = scala.collection.immutable.Nil.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_22);
    resetData_1.prepend(x_23)
  }));
  data_11.update(4, (() => {
    val x_24 = this.popRequestMessages;
    val x_25 = x_24.iterator;
    iterMut_5 = x_25;
    positionVar_10 = 5
  }));
  data_11.update(5, (() => {
    val x_26 = iterMut_5;
    val x_27 = x_26.hasNext;
    if (x_27)
      {
        val x_28 = iterMut_5;
        val x_29 = x_28.next();
        listValMut_4 = x_29;
        positionVar_10 = 6
      }
    else
      positionVar_10 = 14
  }));
  data_11.update(6, (() => {
    val x_30 = listValMut_4;
    val x_31 = x_30.methodInfo;
    val x_32 = scala.`package`.Right.apply[scala.Nothing, scala.Int](15);
    val x_33 = x_31.==(x_32);
    if (x_33)
      positionVar_10 = 7
    else
      positionVar_10 = 13
  }));
  data_11.update(7, (() => {
    val x_34 = listValMut_4;
    this.handleNonblockingMessage(x_34);
    resetData_0 = ();
    positionVar_10 = 8
  }));
  data_11.update(8, (() => positionVar_10 = 9));
  data_11.update(9, (() => positionVar_10 = 5));
  data_11.update(10, (() => positionVar_10 = 11));
  data_11.update(11, (() => {
    val x_35 = this.state;
    val x_36 = x_35.+(1);
    this.`state_=`(x_36);
    resetData_0 = ();
    positionVar_10 = 12
  }));
  data_11.update(12, (() => {
    val x_37 = resetData_0;
    val x_38 = x_37.asInstanceOf[scala.Any];
    bindingMut_3 = x_38;
    val x_39 = bindingMut_3;
    val x_40 = listValMut_4;
    x_40.reply(this, x_39);
    resetData_0 = ();
    positionVar_10 = 8
  }));
  data_11.update(13, (() => {
    val x_41 = listValMut_4;
    val x_42 = x_41.methodInfo;
    val x_43 = scala.`package`.Right.apply[scala.Nothing, scala.Int](15);
    val x_44 = x_42.==(x_43);
    val x_45 = x_44.`unary_!`;
    if (x_45)
      {
        val x_46 = listValMut_4;
        val x_47 = scala.collection.immutable.List.apply[meta.runtime.RequestMessage](x_46);
        val x_48 = this.addReceiveMessages(x_47);
        resetData_0 = x_48;
        positionVar_10 = 9
      }
    else
      ()
  }));
  data_11.update(14, (() => {
    val x_49 = iterMut_5;
    val x_50 = x_49.hasNext;
    val x_51 = x_50.`unary_!`;
    if (x_51)
      {
        val x_52 = resetData_1.remove(0);
        val x_56 = x_52.find(((x_53: scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]) => {
          val x_54 = x_53._1;
          val x_55 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
          x_54.==(x_55)
        }));
        val x_57 = x_56.get;
        val x_58 = x_57._2;
        positionVar_10 = x_58
      }
    else
      ()
  }));
  data_11.update(15, (() => positionVar_10 = 16));
  data_11.update(16, (() => {
    val x_59 = bindingMut_6;
    val x_60 = x_59.<(1.0);
    if (x_60)
      positionVar_10 = 17
    else
      positionVar_10 = 19
  }));
  data_11.update(17, (() => {
    val x_61 = bindingMut_6;
    val x_62 = x_61.+(1);
    resetData_0 = x_62;
    val x_63 = resetData_0;
    val x_64 = x_63.asInstanceOf[scala.Double];
    bindingMut_6 = x_64;
    positionVar_10 = 18;
    unblockFlag_9 = false
  }));
  data_11.update(18, (() => {
    positionVar_10 = 4;
    val x_65 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
    val x_66 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_65, 15);
    val x_67 = scala.collection.immutable.Nil.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_66);
    resetData_1.prepend(x_67)
  }));
  data_11.update(19, (() => {
    val x_68 = bindingMut_6;
    val x_69 = x_68.<(1.0);
    val x_70 = x_69.`unary_!`;
    if (x_70)
      positionVar_10 = 20
    else
      ()
  }));
  data_11.update(20, (() => positionVar_10 = 1));
  data_11.update(21, (() => positionVar_10 = 22));
  data_11.update(22, (() => {
    positionVar_10 = 23;
    unblockFlag_9 = false
  }));
  data_11.update(23, (() => positionVar_10 = 22));
  data_11.update(24, (() => {
    resetData_0 = 0.0;
    val x_71 = resetData_0;
    val x_72 = x_71.asInstanceOf[scala.Double];
    bindingMut_6 = x_72;
    positionVar_10 = 17
  }));
  data_11.update(25, (() => {
    val x_73 = bindingMut_7;
    squid.lib.`package`.IfThenElse[scala.Unit](x_73, {
      val receiver_74 = this.n;
      val x_75 = ((this): meta.runtime.Actor).id;
      val x_76 = receiver_74.id;
      val x_77 = scala.`package`.Right.apply[scala.Nothing, scala.Int](15);
      val x_78 = scala.collection.immutable.Nil.::[scala.collection.immutable.List[scala.Any]](((scala.collection.immutable.Nil): scala.collection.immutable.List[scala.Any]));
      val x_79 = meta.runtime.RequestMessage.apply(x_75, x_76, false, x_77, x_78);
      val x_80 = x_79.sessionId;
      val x_81 = meta.runtime.Future.apply$default$2[scala.Unit];
      val x_82 = meta.runtime.Future.apply[scala.Unit](x_80, x_81);
      var v_83: meta.runtime.Future[scala.Unit] = x_82;
      ((this): meta.runtime.Actor).sendMessage(x_79);
      val x_84 = x_79.sessionId;
      ((this): meta.runtime.Actor).setMessageResponseHandler(x_84, ((response_85: meta.runtime.Message) => {
        val x_86 = v_83;
        val x_87 = response_85.asInstanceOf[meta.runtime.ResponseMessage];
        val x_88 = x_87.arg;
        x_88.asInstanceOf[scala.Unit];
        x_86.setValue(())
      }));
      val x_89 = v_83;
      resetData_0 = x_89;
      positionVar_10 = 3
    }, ())
  }));
  data_11.update(26, (() => positionVar_10 = 22));
  data_11
}).apply();
  

  def inc(): Unit = 
      {
  val x_0 = this.state;
  val x_1 = x_0.+(1);
  this.`state_=`(x_1)
}
  
  def wrapper_inc(args: List[Any]): Unit = {
    
          
          inc()
          
  }
  
  override def run(msgs: List[meta.runtime.Message]): (List[meta.runtime.Message], Int) = {
    addReceiveMessages(msgs)
    sendMessages.clear()
    unblockFlag_9 = true
    while (unblockFlag_9 && (positionVar_10 < 27)) {
      commands_90(positionVar_10)()
    }
    (sendMessages.toList, 1)
  }

  override def handleNonblockingMessage(m: meta.runtime.RequestMessage): Unit = {
    val args = m.argss.flatten
    val response = m.methodInfo match {
      case Right(x) => {
        x match {
          case 15 => wrapper_inc(args)
        }
      }
      case Left(x) => println("For staged implementation only")
    }
    m.reply(this, response)
  }
  
    override def gotoHandleMessages(new_ir: Int = 3): meta.runtime.Actor = {
      // first entry, save the current IR to reflectionIR
      unblockFlag_9 = true

      if (reflectionIR_33 == -1){
        reflectionIR_33 = positionVar_10
        positionVar_10 = new_ir
      }

      while (positionVar_10 <= 13 && unblockFlag_9) {
        commands_90(positionVar_10)()
      }

      // reset instruction register when finishes processing
      if (positionVar_10 > 13) {
        positionVar_10 = reflectionIR_33
        reflectionIR_33 = -1
      }
      this
    }
    
override def SimClone(): CounterSim = {
  val newAgent = new CounterSim(n)
  newAgent.state = state
  newAgent
}

}
