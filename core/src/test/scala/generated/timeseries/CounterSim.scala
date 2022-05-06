package generated.meta.test.timeseries

class CounterSim(val n: generated.meta.test.timeseries.CounterSim) extends meta.runtime.Actor {

   var state: Int = 1;
   val immutableSecret: Int = 10;
  private var  reflectionIR_54: Int = -1
private var resetData_0: scala.Any = null
private val resetData_1 = scala.collection.mutable.ListBuffer.apply[scala.collection.immutable.List[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]]]()
private var resetData_2: meta.runtime.ResponseMessage = null
private var bindingMut_3: scala.Any = null
private var listValMut_4: meta.runtime.RequestMessage = null
@transient private var iterMut_5: scala.collection.Iterator[meta.runtime.RequestMessage] = null
private var bindingMut_6: scala.Double = 0.0
private var bindingMut_7: scala.Boolean = false
private var bindingMut_8: generated.meta.test.timeseries.CounterSim = null
private var unblockFlag_9: scala.Boolean = true
private var positionVar_10: scala.Int = 0
private 
  val commands_88 = (() => {
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
    val x_32 = x_31.==("inc");
    if (x_32)
      positionVar_10 = 7
    else
      positionVar_10 = 13
  }));
  data_11.update(7, (() => {
    val x_33 = listValMut_4;
    this.handleNonblockingMessage(x_33);
    resetData_0 = ();
    positionVar_10 = 8
  }));
  data_11.update(8, (() => positionVar_10 = 9));
  data_11.update(9, (() => positionVar_10 = 5));
  data_11.update(10, (() => positionVar_10 = 11));
  data_11.update(11, (() => {
    val x_34 = this.state;
    val x_35 = x_34.+(1);
    this.`state_=`(x_35);
    resetData_0 = 0;
    positionVar_10 = 12
  }));
  data_11.update(12, (() => {
    val x_36 = resetData_0;
    val x_37 = x_36.asInstanceOf[scala.Any];
    bindingMut_3 = x_37;
    val x_38 = bindingMut_3;
    val x_39 = listValMut_4;
    x_39.reply(this, x_38);
    resetData_0 = ();
    positionVar_10 = 8
  }));
  data_11.update(13, (() => {
    val x_40 = listValMut_4;
    val x_41 = x_40.methodInfo;
    val x_42 = x_41.==("inc");
    val x_43 = x_42.`unary_!`;
    if (x_43)
      {
        val x_44 = listValMut_4;
        val x_45 = scala.collection.immutable.List.apply[meta.runtime.RequestMessage](x_44);
        val x_46 = this.addReceiveMessages(x_45);
        resetData_0 = x_46;
        positionVar_10 = 9
      }
    else
      ()
  }));
  data_11.update(14, (() => {
    val x_47 = iterMut_5;
    val x_48 = x_47.hasNext;
    val x_49 = x_48.`unary_!`;
    if (x_49)
      {
        val x_50 = resetData_1.remove(0);
        val x_54 = x_50.find(((x_51: scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]) => {
          val x_52 = x_51._1;
          val x_53 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
          x_52.==(x_53)
        }));
        val x_55 = x_54.get;
        val x_56 = x_55._2;
        positionVar_10 = x_56
      }
    else
      ()
  }));
  data_11.update(15, (() => positionVar_10 = 16));
  data_11.update(16, (() => {
    val x_57 = bindingMut_6;
    val x_58 = x_57.<(1.0);
    if (x_58)
      positionVar_10 = 17
    else
      positionVar_10 = 19
  }));
  data_11.update(17, (() => {
    val x_59 = bindingMut_6;
    val x_60 = x_59.+(1);
    resetData_0 = x_60;
    val x_61 = resetData_0;
    val x_62 = x_61.asInstanceOf[scala.Double];
    bindingMut_6 = x_62;
    positionVar_10 = 18;
    unblockFlag_9 = false
  }));
  data_11.update(18, (() => {
    positionVar_10 = 4;
    val x_63 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
    val x_64 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_63, 15);
    val x_65 = scala.collection.immutable.Nil.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_64);
    resetData_1.prepend(x_65)
  }));
  data_11.update(19, (() => {
    val x_66 = bindingMut_6;
    val x_67 = x_66.<(1.0);
    val x_68 = x_67.`unary_!`;
    if (x_68)
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
    val x_69 = resetData_0;
    val x_70 = x_69.asInstanceOf[scala.Double];
    bindingMut_6 = x_70;
    positionVar_10 = 17
  }));
  data_11.update(25, (() => {
    val x_71 = bindingMut_7;
    squid.lib.`package`.IfThenElse[scala.Unit](x_71, {
      val receiver_72 = this.n;
      val x_73 = ((this): meta.runtime.Actor).id;
      val x_74 = receiver_72.id;
      val x_75 = scala.collection.immutable.Nil.::[scala.collection.immutable.List[scala.Any]](((scala.collection.immutable.Nil): scala.collection.immutable.List[scala.Any]));
      val x_76 = meta.runtime.RequestMessage.apply(x_73, x_74, false, "inc", x_75);
      val x_77 = x_76.sessionId;
      val x_78 = meta.runtime.Future.apply$default$2[scala.Int];
      val x_79 = meta.runtime.Future.apply[scala.Int](x_77, x_78);
      var v_80: meta.runtime.Future[scala.Int] = x_79;
      ((this): meta.runtime.Actor).sendMessage(x_76);
      val x_81 = x_76.sessionId;
      ((this): meta.runtime.Actor).setMessageResponseHandler(x_81, ((response_82: meta.runtime.Message) => {
        val x_83 = v_80;
        val x_84 = response_82.asInstanceOf[meta.runtime.ResponseMessage];
        val x_85 = x_84.arg;
        val x_86 = x_85.asInstanceOf[scala.Int];
        x_83.setValue(x_86)
      }));
      val x_87 = v_80;
      resetData_0 = x_87;
      positionVar_10 = 3
    }, ())
  }));
  data_11.update(26, (() => positionVar_10 = 22));
  data_11
}).apply();
  

   def inc(): Int = 
      {
  val x_0 = this.state;
  val x_1 = x_0.+(1);
  this.`state_=`(x_1);
  0
}
  
  private def wrapper_inc(args: List[Any]): Int = {
    
          
          inc()
          
  }
  
  override def run(msgs: List[meta.runtime.Message]): (List[meta.runtime.Message], Int) = {
    addReceiveMessages(msgs)
    sendMessages.clear()
    unblockFlag_9 = true
    while (unblockFlag_9 && (positionVar_10 < 27)) {
      commands_88(positionVar_10)()
    }
    (sendMessages.toList, 1)
  }

  override def handleNonblockingMessage(m: meta.runtime.RequestMessage): Unit = {
    val args = m.argss.flatten
    val response = m.methodInfo match {
      case "inc" => wrapper_inc(args)
    }
    m.reply(this, response)
  }
  
    override def gotoHandleMessages(new_ir: Int = 3): meta.runtime.Actor = {
      // first entry, save the current IR to reflectionIR
      unblockFlag_9 = true

      if (reflectionIR_54 == -1){
        reflectionIR_54 = positionVar_10
        positionVar_10 = new_ir
      }

      while (positionVar_10 <= 13 && unblockFlag_9) {
        commands_88(positionVar_10)()
      }

      // reset instruction register when finishes processing
      if (positionVar_10 > 13) {
        positionVar_10 = reflectionIR_54
        reflectionIR_54 = -1
      }
      this
    }
    
override def SimClone(): CounterSim = {
  val newAgent = new CounterSim(n)
  newAgent.state = state
  newAgent
}

override def SimReset(): Unit = {
  positionVar_10 = 0
  val newAgent = new CounterSim(n)
  state = newAgent.state
}

}
