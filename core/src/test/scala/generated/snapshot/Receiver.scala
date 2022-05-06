package generated.meta.test.snapshot

class Receiver() extends meta.runtime.Actor {


  private var  reflectionIR_18: Int = -1
private var resetData_0: scala.Any = null
private val resetData_1 = scala.collection.mutable.ListBuffer.apply[scala.collection.immutable.List[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]]]()
private var resetData_2: meta.runtime.ResponseMessage = null
private var bindingMut_3: scala.Any = null
private var listValMut_4: meta.runtime.RequestMessage = null
@transient private var iterMut_5: scala.collection.Iterator[meta.runtime.RequestMessage] = null
private var bindingMut_6: scala.Double = 0.0
private var unblockFlag_7: scala.Boolean = true
private var positionVar_8: scala.Int = 0
private 
  val commands_45 = (() => {
  val data_9 = new scala.Array[scala.Function0[scala.Unit]](23);
  data_9.update(0, (() => positionVar_8 = 1));
  data_9.update(1, (() => {
    resetData_0 = 0.0;
    val x_10 = resetData_0;
    val x_11 = x_10.asInstanceOf[scala.Double];
    bindingMut_6 = x_11;
    positionVar_8 = 2
  }));
  data_9.update(2, (() => {
    val x_12 = bindingMut_6;
    val x_13 = x_12.+(1);
    resetData_0 = x_13;
    val x_14 = resetData_0;
    val x_15 = x_14.asInstanceOf[scala.Double];
    bindingMut_6 = x_15;
    positionVar_8 = 3;
    unblockFlag_7 = false
  }));
  data_9.update(3, (() => positionVar_8 = 4));
  data_9.update(4, (() => {
    val x_16 = this.popRequestMessages;
    val x_17 = x_16.iterator;
    iterMut_5 = x_17;
    positionVar_8 = 5
  }));
  data_9.update(5, (() => {
    val x_18 = iterMut_5;
    val x_19 = x_18.hasNext;
    if (x_19)
      {
        val x_20 = iterMut_5;
        val x_21 = x_20.next();
        listValMut_4 = x_21;
        positionVar_8 = 6
      }
    else
      positionVar_8 = 14
  }));
  data_9.update(6, (() => {
    val x_22 = listValMut_4;
    val x_23 = x_22.methodInfo;
    val x_24 = x_23.==("rpc1");
    if (x_24)
      positionVar_8 = 7
    else
      positionVar_8 = 13
  }));
  data_9.update(7, (() => {
    val x_25 = listValMut_4;
    this.handleNonblockingMessage(x_25);
    resetData_0 = ();
    positionVar_8 = 8
  }));
  data_9.update(8, (() => positionVar_8 = 9));
  data_9.update(9, (() => positionVar_8 = 5));
  data_9.update(10, (() => positionVar_8 = 11));
  data_9.update(11, (() => {
    scala.Predef.println("rpc 1!");
    resetData_0 = 2;
    positionVar_8 = 12
  }));
  data_9.update(12, (() => {
    val x_26 = resetData_0;
    val x_27 = x_26.asInstanceOf[scala.Any];
    bindingMut_3 = x_27;
    val x_28 = bindingMut_3;
    val x_29 = listValMut_4;
    x_29.reply(this, x_28);
    resetData_0 = ();
    positionVar_8 = 8
  }));
  data_9.update(13, (() => {
    val x_30 = listValMut_4;
    val x_31 = x_30.methodInfo;
    val x_32 = x_31.==("rpc1");
    val x_33 = x_32.`unary_!`;
    if (x_33)
      {
        val x_34 = listValMut_4;
        val x_35 = scala.collection.immutable.List.apply[meta.runtime.RequestMessage](x_34);
        val x_36 = this.addReceiveMessages(x_35);
        resetData_0 = x_36;
        positionVar_8 = 9
      }
    else
      ()
  }));
  data_9.update(14, (() => {
    val x_37 = iterMut_5;
    val x_38 = x_37.hasNext;
    val x_39 = x_38.`unary_!`;
    if (x_39)
      positionVar_8 = 15
    else
      ()
  }));
  data_9.update(15, (() => positionVar_8 = 16));
  data_9.update(16, (() => {
    val x_40 = bindingMut_6;
    val x_41 = x_40.<(1.0);
    if (x_41)
      positionVar_8 = 2
    else
      positionVar_8 = 17
  }));
  data_9.update(17, (() => {
    val x_42 = bindingMut_6;
    val x_43 = x_42.<(1.0);
    val x_44 = x_43.`unary_!`;
    if (x_44)
      positionVar_8 = 18
    else
      ()
  }));
  data_9.update(18, (() => positionVar_8 = 1));
  data_9.update(19, (() => positionVar_8 = 20));
  data_9.update(20, (() => {
    positionVar_8 = 21;
    unblockFlag_7 = false
  }));
  data_9.update(21, (() => positionVar_8 = 20));
  data_9.update(22, (() => positionVar_8 = 20));
  data_9
}).apply();
  

   def rpc1(): Int = 
      {
  scala.Predef.println("rpc 1!");
  2
}
  
  private def wrapper_rpc1(args: List[Any]): Int = {
    
          
          rpc1()
          
  }
  
  override def run(msgs: List[meta.runtime.Message]): (List[meta.runtime.Message], Int) = {
    addReceiveMessages(msgs)
    sendMessages.clear()
    unblockFlag_7 = true
    while (unblockFlag_7 && (positionVar_8 < 23)) {
      commands_45(positionVar_8)()
    }
    (sendMessages.toList, 1)
  }

  override def handleNonblockingMessage(m: meta.runtime.RequestMessage): Unit = {
    val args = m.argss.flatten
    val response = m.methodInfo match {
      case "rpc1" => wrapper_rpc1(args)
    }
    m.reply(this, response)
  }
  
    override def gotoHandleMessages(new_ir: Int = 3): meta.runtime.Actor = {
      // first entry, save the current IR to reflectionIR
      unblockFlag_7 = true

      if (reflectionIR_18 == -1){
        reflectionIR_18 = positionVar_8
        positionVar_8 = new_ir
      }

      while (positionVar_8 <= 13 && unblockFlag_7) {
        commands_45(positionVar_8)()
      }

      // reset instruction register when finishes processing
      if (positionVar_8 > 13) {
        positionVar_8 = reflectionIR_18
        reflectionIR_18 = -1
      }
      this
    }
    
override def SimClone(): Receiver = {
  val newAgent = new Receiver()

  newAgent
}

override def SimReset(): Unit = {
  positionVar_8 = 0
  
}

}
