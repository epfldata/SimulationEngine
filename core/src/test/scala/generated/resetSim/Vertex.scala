package generated.meta.test.resetSim

class Vertex() extends meta.runtime.Actor {

  var counter: Int = 0;
  var neighbor: generated.meta.test.resetSim.Vertex = null;
  private var  reflectionIR_29: Int = -1
  var resetData_0: scala.Any = null
  val resetData_1 = scala.collection.mutable.ListBuffer.apply[scala.collection.immutable.List[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]]]()
  var resetData_2: meta.runtime.ResponseMessage = null
  var bindingMut_3: scala.Any = null
  var listValMut_4: meta.runtime.RequestMessage = null
  @transient var iterMut_5: scala.collection.Iterator[meta.runtime.RequestMessage] = null
  var bindingMut_6: scala.Double = 0.0
  var bindingMut_7: generated.meta.test.resetSim.Vertex = null
  var listValMut_8: meta.runtime.Actor = null
  @transient var iterMut_9: scala.collection.Iterator[meta.runtime.Actor] = null
  var bindingMut_10: scala.collection.immutable.List[meta.runtime.Actor] = null
  var unblockFlag_11: scala.Boolean = true
  var positionVar_12: scala.Int = 0
  
  val commands_86 = (() => {
  val data_13 = new scala.Array[scala.Function0[scala.Unit]](25);
  data_13.update(0, (() => positionVar_12 = 1));
  data_13.update(1, (() => {
    val x_14 = this.connectedAgents;
    resetData_0 = x_14;
    val x_15 = resetData_0;
    val x_16 = x_15.asInstanceOf[scala.collection.immutable.List[meta.runtime.Actor]];
    bindingMut_10 = x_16;
    val x_17 = bindingMut_10;
    val x_18 = x_17.iterator;
    iterMut_9 = x_18;
    positionVar_12 = 2
  }));
  data_13.update(2, (() => {
    val x_19 = iterMut_9;
    val x_20 = x_19.hasNext;
    if (x_20)
      {
        val x_21 = iterMut_9;
        val x_22 = x_21.next();
        listValMut_8 = x_22;
        val x_23 = listValMut_8;
        val x_24 = x_23.asInstanceOf[generated.meta.test.resetSim.Vertex];
        resetData_0 = x_24;
        val x_25 = resetData_0;
        val x_26 = x_25.asInstanceOf[generated.meta.test.resetSim.Vertex];
        bindingMut_7 = x_26;
        val x_27 = bindingMut_7;
        this.`neighbor_=`(x_27);
        resetData_0 = ();
        val receiver_28 = this.neighbor;
        val x_29 = ((this): meta.runtime.Actor).id;
        val x_30 = receiver_28.id;
        val x_31 = scala.`package`.Right.apply[scala.Nothing, scala.Int](0);
        val x_32 = scala.collection.immutable.Nil.::[scala.collection.immutable.List[scala.Any]](((scala.collection.immutable.Nil): scala.collection.immutable.List[scala.Any]));
        val x_33 = meta.runtime.RequestMessage.apply(x_29, x_30, false, x_31, x_32);
        val x_34 = x_33.sessionId;
        val x_35 = meta.runtime.Future.apply$default$2[scala.Unit];
        val x_36 = meta.runtime.Future.apply[scala.Unit](x_34, x_35);
        var v_37: meta.runtime.Future[scala.Unit] = x_36;
        ((this): meta.runtime.Actor).sendMessage(x_33);
        val x_38 = x_33.sessionId;
        ((this): meta.runtime.Actor).setMessageResponseHandler(x_38, ((response_39: meta.runtime.Message) => {
          val x_40 = v_37;
          val x_41 = response_39.asInstanceOf[meta.runtime.ResponseMessage];
          val x_42 = x_41.arg;
          x_42.asInstanceOf[scala.Unit];
          x_40.setValue(())
        }));
        val x_43 = v_37;
        resetData_0 = x_43;
        positionVar_12 = 2
      }
    else
      positionVar_12 = 3
  }));
  data_13.update(3, (() => {
    val x_44 = iterMut_9;
    val x_45 = x_44.hasNext;
    val x_46 = x_45.`unary_!`;
    if (x_46)
      {
        resetData_0 = 0.0;
        val x_47 = resetData_0;
        val x_48 = x_47.asInstanceOf[scala.Double];
        bindingMut_6 = x_48;
        positionVar_12 = 4
      }
    else
      ()
  }));
  data_13.update(4, (() => {
    val x_49 = bindingMut_6;
    val x_50 = x_49.+(1);
    resetData_0 = x_50;
    val x_51 = resetData_0;
    val x_52 = x_51.asInstanceOf[scala.Double];
    bindingMut_6 = x_52;
    positionVar_12 = 5;
    unblockFlag_11 = false
  }));
  data_13.update(5, (() => positionVar_12 = 6));
  data_13.update(6, (() => {
    val x_53 = this.popRequestMessages;
    val x_54 = x_53.iterator;
    iterMut_5 = x_54;
    positionVar_12 = 7
  }));
  data_13.update(7, (() => {
    val x_55 = iterMut_5;
    val x_56 = x_55.hasNext;
    if (x_56)
      {
        val x_57 = iterMut_5;
        val x_58 = x_57.next();
        listValMut_4 = x_58;
        positionVar_12 = 8
      }
    else
      positionVar_12 = 16
  }));
  data_13.update(8, (() => {
    val x_59 = listValMut_4;
    val x_60 = x_59.methodInfo;
    val x_61 = scala.`package`.Right.apply[scala.Nothing, scala.Int](0);
    val x_62 = x_60.==(x_61);
    if (x_62)
      positionVar_12 = 9
    else
      positionVar_12 = 15
  }));
  data_13.update(9, (() => {
    val x_63 = listValMut_4;
    this.handleNonblockingMessage(x_63);
    resetData_0 = ();
    positionVar_12 = 10
  }));
  data_13.update(10, (() => positionVar_12 = 11));
  data_13.update(11, (() => positionVar_12 = 7));
  data_13.update(12, (() => positionVar_12 = 13));
  data_13.update(13, (() => {
    val x_64 = this.counter;
    val x_65 = x_64.+(1);
    this.`counter_=`(x_65);
    resetData_0 = ();
    positionVar_12 = 14
  }));
  data_13.update(14, (() => {
    val x_66 = resetData_0;
    val x_67 = x_66.asInstanceOf[scala.Any];
    bindingMut_3 = x_67;
    val x_68 = bindingMut_3;
    val x_69 = listValMut_4;
    x_69.reply(this, x_68);
    resetData_0 = ();
    positionVar_12 = 10
  }));
  data_13.update(15, (() => {
    val x_70 = listValMut_4;
    val x_71 = x_70.methodInfo;
    val x_72 = scala.`package`.Right.apply[scala.Nothing, scala.Int](0);
    val x_73 = x_71.==(x_72);
    val x_74 = x_73.`unary_!`;
    if (x_74)
      {
        val x_75 = listValMut_4;
        val x_76 = scala.collection.immutable.List.apply[meta.runtime.RequestMessage](x_75);
        val x_77 = this.addReceiveMessages(x_76);
        resetData_0 = x_77;
        positionVar_12 = 11
      }
    else
      ()
  }));
  data_13.update(16, (() => {
    val x_78 = iterMut_5;
    val x_79 = x_78.hasNext;
    val x_80 = x_79.`unary_!`;
    if (x_80)
      positionVar_12 = 17
    else
      ()
  }));
  data_13.update(17, (() => positionVar_12 = 18));
  data_13.update(18, (() => {
    val x_81 = bindingMut_6;
    val x_82 = x_81.<(1.0);
    if (x_82)
      positionVar_12 = 4
    else
      positionVar_12 = 19
  }));
  data_13.update(19, (() => {
    val x_83 = bindingMut_6;
    val x_84 = x_83.<(1.0);
    val x_85 = x_84.`unary_!`;
    if (x_85)
      positionVar_12 = 20
    else
      ()
  }));
  data_13.update(20, (() => positionVar_12 = 1));
  data_13.update(21, (() => positionVar_12 = 22));
  data_13.update(22, (() => {
    positionVar_12 = 23;
    unblockFlag_11 = false
  }));
  data_13.update(23, (() => positionVar_12 = 22));
  data_13.update(24, (() => positionVar_12 = 22));
  data_13
}).apply();
  

  def API(): Unit = 
      {
  val x_0 = this.counter;
  val x_1 = x_0.+(1);
  this.`counter_=`(x_1)
}
  
  def wrapper_API(args: List[Any]): Unit = {
    
          
          API()
          
  }
  
  override def run(msgs: List[meta.runtime.Message]): (List[meta.runtime.Message], Int) = {
    addReceiveMessages(msgs)
    sendMessages.clear()
    unblockFlag_11 = true
    while (unblockFlag_11 && (positionVar_12 < 25)) {
      commands_86(positionVar_12)()
    }
    (sendMessages.toList, 1)
  }

  override def handleNonblockingMessage(m: meta.runtime.RequestMessage): Unit = {
    val args = m.argss.flatten
    val response = m.methodInfo match {
      case Right(x) => {
        x match {
          case 0 => wrapper_API(args)
        }
      }
      case Left(x) => println("For staged implementation only")
    }
    m.reply(this, response)
  }
  
    override def gotoHandleMessages(new_ir: Int = 5): meta.runtime.Actor = {
      // first entry, save the current IR to reflectionIR
      unblockFlag_11 = true

      if (reflectionIR_29 == -1){
        reflectionIR_29 = positionVar_12
        positionVar_12 = new_ir
      }

      while (positionVar_12 <= 15 && unblockFlag_11) {
        commands_86(positionVar_12)()
      }

      // reset instruction register when finishes processing
      if (positionVar_12 > 15) {
        positionVar_12 = reflectionIR_29
        reflectionIR_29 = -1
      }
      this
    }
    
override def SimClone(): Vertex = {
  val newAgent = new Vertex()
  newAgent.counter = counter  
newAgent.neighbor = neighbor
  newAgent
}

override def SimReset(): Unit = {
  val newAgent = new Vertex()
  counter = newAgent.counter
  neighbor = newAgent.neighbor
}

}
