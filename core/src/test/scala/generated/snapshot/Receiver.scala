package generated.meta.test.snapshot

class Receiver () extends meta.runtime.Actor {


  private var  reflectionIR_94: Int = -1
  var resetData_0: scala.Any = null
  val resetData_1 = scala.collection.mutable.ListBuffer.apply[scala.collection.immutable.List[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]]]()
  var resetData_2: meta.runtime.ResponseMessage = null
  var bindingMut_3: scala.Any = null
  var listValMut_4: meta.runtime.RequestMessage = null
  @transient var iterMut_5: scala.collection.Iterator[meta.runtime.RequestMessage] = null
  var bindingMut_6: scala.Double = 0.0
  var unblockFlag_7: scala.Boolean = true
  var positionVar_8: scala.Int = 0
  
  val commands_47 = (() => {
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
    val x_24 = scala.`package`.Right.apply[scala.Nothing, scala.Int](17);
    val x_25 = x_23.==(x_24);
    if (x_25)
      positionVar_8 = 7
    else
      positionVar_8 = 13
  }));
  data_9.update(7, (() => {
    val x_26 = listValMut_4;
    this.handleNonblockingMessage(x_26);
    resetData_0 = ();
    positionVar_8 = 8
  }));
  data_9.update(8, (() => positionVar_8 = 9));
  data_9.update(9, (() => positionVar_8 = 5));
  data_9.update(10, (() => positionVar_8 = 11));
  data_9.update(11, (() => {
    scala.Predef.println("rpc 1!");
    resetData_0 = ();
    positionVar_8 = 12
  }));
  data_9.update(12, (() => {
    val x_27 = resetData_0;
    val x_28 = x_27.asInstanceOf[scala.Any];
    bindingMut_3 = x_28;
    val x_29 = bindingMut_3;
    val x_30 = listValMut_4;
    x_30.reply(this, x_29);
    resetData_0 = ();
    positionVar_8 = 8
  }));
  data_9.update(13, (() => {
    val x_31 = listValMut_4;
    val x_32 = x_31.methodInfo;
    val x_33 = scala.`package`.Right.apply[scala.Nothing, scala.Int](17);
    val x_34 = x_32.==(x_33);
    val x_35 = x_34.`unary_!`;
    if (x_35)
      {
        val x_36 = listValMut_4;
        val x_37 = scala.collection.immutable.List.apply[meta.runtime.RequestMessage](x_36);
        val x_38 = this.addReceiveMessages(x_37);
        resetData_0 = x_38;
        positionVar_8 = 9
      }
    else
      ()
  }));
  data_9.update(14, (() => {
    val x_39 = iterMut_5;
    val x_40 = x_39.hasNext;
    val x_41 = x_40.`unary_!`;
    if (x_41)
      positionVar_8 = 15
    else
      ()
  }));
  data_9.update(15, (() => positionVar_8 = 16));
  data_9.update(16, (() => {
    val x_42 = bindingMut_6;
    val x_43 = x_42.<(1.0);
    if (x_43)
      positionVar_8 = 2
    else
      positionVar_8 = 17
  }));
  data_9.update(17, (() => {
    val x_44 = bindingMut_6;
    val x_45 = x_44.<(1.0);
    val x_46 = x_45.`unary_!`;
    if (x_46)
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
  

  def rpc1(): Unit = 
      scala.Predef.println("rpc 1!")
  
  def wrapper_rpc1(args: List[Any]): Unit = {
    
          
          rpc1()
          
  }
  
  override def run(msgs: List[meta.runtime.Message]): (List[meta.runtime.Message], Int) = {
    addReceiveMessages(msgs)
    sendMessages.clear()
    unblockFlag_7 = true
    while (unblockFlag_7 && (positionVar_8 < 23)) {
      commands_47(positionVar_8)()
    }
    (sendMessages.toList, 1)
  }
  
  override def handleNonblockingMessage(m: meta.runtime.RequestMessage): Unit = {
    val args = m.argss.flatten
    val response = m.methodInfo match {
      case Right(x) => {
        x match {
          case 17 => wrapper_rpc1(args)
        }
      }
      case Left(x) => println("For staged implementation only")
    }
    m.reply(this, response)
  }
  
    override def gotoHandleMessages(new_ir: Int = 3): meta.runtime.Actor = {
      // first entry, save the current IR to reflectionIR
      unblockFlag_7 = true

      if (reflectionIR_94 == -1){
        reflectionIR_94 = positionVar_8
        positionVar_8 = new_ir
      }

      while (positionVar_8 <= 13 && unblockFlag_7) {
        commands_47(positionVar_8)()
      }

      // reset instruction register when finishes processing
      if (positionVar_8 > 13) {
        positionVar_8 = reflectionIR_94
        reflectionIR_94 = -1
      }
      this
    }
    
}
