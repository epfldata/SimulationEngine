package generated.meta.test.resetSim

class Vertex() extends meta.runtime.Actor {

   var counter: Int = 0;
   var neighbor: generated.meta.test.resetSim.Vertex = null;
  private var  reflectionIR_37: Int = -1
private var resetData_0: scala.Any = null
private val resetData_1 = scala.collection.mutable.ListBuffer.apply[scala.collection.immutable.List[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]]]()
private var resetData_2: meta.runtime.ResponseMessage = null
private var bindingMut_3: scala.Any = null
private var listValMut_4: meta.runtime.RequestMessage = null
@transient private var iterMut_5: scala.collection.Iterator[meta.runtime.RequestMessage] = null
private var bindingMut_6: scala.Double = 0.0
private var bindingMut_7: generated.meta.test.resetSim.Vertex = null
private var listValMut_8: meta.runtime.Actor = null
@transient private var iterMut_9: scala.collection.Iterator[meta.runtime.Actor] = null
private var bindingMut_10: scala.collection.immutable.List[meta.runtime.Actor] = null
private var unblockFlag_11: scala.Boolean = true
private var positionVar_12: scala.Int = 0
private 
  val commands_83 = (() => {
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
        val x_31 = scala.collection.immutable.Nil.::[scala.collection.immutable.List[scala.Any]](((scala.collection.immutable.Nil): scala.collection.immutable.List[scala.Any]));
        val x_32 = meta.runtime.RequestMessage.apply(x_29, x_30, false, "API", x_31);
        val x_33 = x_32.sessionId;
        val x_34 = meta.runtime.Future.apply$default$2[scala.Unit];
        val x_35 = meta.runtime.Future.apply[scala.Unit](x_33, x_34);
        var v_36: meta.runtime.Future[scala.Unit] = x_35;
        ((this): meta.runtime.Actor).sendMessage(x_32);
        val x_37 = x_32.sessionId;
        ((this): meta.runtime.Actor).setMessageResponseHandler(x_37, ((response_38: meta.runtime.Message) => {
          val x_39 = v_36;
          val x_40 = response_38.asInstanceOf[meta.runtime.ResponseMessage];
          val x_41 = x_40.arg;
          x_41.asInstanceOf[scala.Unit];
          x_39.setValue(())
        }));
        val x_42 = v_36;
        resetData_0 = x_42;
        positionVar_12 = 2
      }
    else
      positionVar_12 = 3
  }));
  data_13.update(3, (() => {
    val x_43 = iterMut_9;
    val x_44 = x_43.hasNext;
    val x_45 = x_44.`unary_!`;
    if (x_45)
      {
        resetData_0 = 0.0;
        val x_46 = resetData_0;
        val x_47 = x_46.asInstanceOf[scala.Double];
        bindingMut_6 = x_47;
        positionVar_12 = 4
      }
    else
      ()
  }));
  data_13.update(4, (() => {
    val x_48 = bindingMut_6;
    val x_49 = x_48.+(1);
    resetData_0 = x_49;
    val x_50 = resetData_0;
    val x_51 = x_50.asInstanceOf[scala.Double];
    bindingMut_6 = x_51;
    positionVar_12 = 5;
    unblockFlag_11 = false
  }));
  data_13.update(5, (() => positionVar_12 = 6));
  data_13.update(6, (() => {
    val x_52 = this.popRequestMessages;
    val x_53 = x_52.iterator;
    iterMut_5 = x_53;
    positionVar_12 = 7
  }));
  data_13.update(7, (() => {
    val x_54 = iterMut_5;
    val x_55 = x_54.hasNext;
    if (x_55)
      {
        val x_56 = iterMut_5;
        val x_57 = x_56.next();
        listValMut_4 = x_57;
        positionVar_12 = 8
      }
    else
      positionVar_12 = 16
  }));
  data_13.update(8, (() => {
    val x_58 = listValMut_4;
    val x_59 = x_58.methodInfo;
    val x_60 = x_59.==("API");
    if (x_60)
      positionVar_12 = 9
    else
      positionVar_12 = 15
  }));
  data_13.update(9, (() => {
    val x_61 = listValMut_4;
    this.handleNonblockingMessage(x_61);
    resetData_0 = ();
    positionVar_12 = 10
  }));
  data_13.update(10, (() => positionVar_12 = 11));
  data_13.update(11, (() => positionVar_12 = 7));
  data_13.update(12, (() => positionVar_12 = 13));
  data_13.update(13, (() => {
    val x_62 = this.counter;
    val x_63 = x_62.+(1);
    this.`counter_=`(x_63);
    resetData_0 = ();
    positionVar_12 = 14
  }));
  data_13.update(14, (() => {
    val x_64 = resetData_0;
    val x_65 = x_64.asInstanceOf[scala.Any];
    bindingMut_3 = x_65;
    val x_66 = bindingMut_3;
    val x_67 = listValMut_4;
    x_67.reply(this, x_66);
    resetData_0 = ();
    positionVar_12 = 10
  }));
  data_13.update(15, (() => {
    val x_68 = listValMut_4;
    val x_69 = x_68.methodInfo;
    val x_70 = x_69.==("API");
    val x_71 = x_70.`unary_!`;
    if (x_71)
      {
        val x_72 = listValMut_4;
        val x_73 = scala.collection.immutable.List.apply[meta.runtime.RequestMessage](x_72);
        val x_74 = this.addReceiveMessages(x_73);
        resetData_0 = x_74;
        positionVar_12 = 11
      }
    else
      ()
  }));
  data_13.update(16, (() => {
    val x_75 = iterMut_5;
    val x_76 = x_75.hasNext;
    val x_77 = x_76.`unary_!`;
    if (x_77)
      positionVar_12 = 17
    else
      ()
  }));
  data_13.update(17, (() => positionVar_12 = 18));
  data_13.update(18, (() => {
    val x_78 = bindingMut_6;
    val x_79 = x_78.<(1.0);
    if (x_79)
      positionVar_12 = 4
    else
      positionVar_12 = 19
  }));
  data_13.update(19, (() => {
    val x_80 = bindingMut_6;
    val x_81 = x_80.<(1.0);
    val x_82 = x_81.`unary_!`;
    if (x_82)
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
  
  private def wrapper_API(args: List[Any]): Unit = {
    
          
          API()
          
  }
  
  override def run(msgs: List[meta.runtime.Message]): (List[meta.runtime.Message], Int) = {
    addReceiveMessages(msgs)
    sendMessages.clear()
    unblockFlag_11 = true
    while (unblockFlag_11 && (positionVar_12 < 25)) {
      commands_83(positionVar_12)()
    }
    (sendMessages.toList, 1)
  }

  override def handleNonblockingMessage(m: meta.runtime.RequestMessage): Unit = {
    val args = m.argss.flatten
    val response = m.methodInfo match {
      case "API" => wrapper_API(args)
    }
    m.reply(this, response)
  }
  
    override def gotoHandleMessages(new_ir: Int = 5): meta.runtime.Actor = {
      // first entry, save the current IR to reflectionIR
      unblockFlag_11 = true

      if (reflectionIR_37 == -1){
        reflectionIR_37 = positionVar_12
        positionVar_12 = new_ir
      }

      while (positionVar_12 <= 15 && unblockFlag_11) {
        commands_83(positionVar_12)()
      }

      // reset instruction register when finishes processing
      if (positionVar_12 > 15) {
        positionVar_12 = reflectionIR_37
        reflectionIR_37 = -1
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
  positionVar_12 = 0
  val newAgent = new Vertex()
  counter = newAgent.counter
  neighbor = newAgent.neighbor
}

}
