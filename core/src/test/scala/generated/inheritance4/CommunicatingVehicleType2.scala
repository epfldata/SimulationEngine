package generated.meta.test.inheritance4

class CommunicatingVehicleType2() extends generated.meta.test.inheritance4.Vehicle {

  price = 20;
  load = 10;
  override val licensePlate: Int = 0;
  private var  reflectionIR_47: Int = -1
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
  val commands_58 = (() => {
  val data_9 = new scala.Array[scala.Function0[scala.Unit]](30);
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
      positionVar_8 = 21
  }));
  data_9.update(6, (() => {
    val x_22 = listValMut_4;
    val x_23 = x_22.methodInfo;
    val x_24 = x_23.==("getPrice");
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
    scala.Predef.println("Received a message for get price in Commmunicating vehicle type 2!");
    resetData_0 = 30;
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
    val x_32 = x_31.==("getPrice");
    val x_33 = x_32.`unary_!`;
    if (x_33)
      positionVar_8 = 14
    else
      ()
  }));
  data_9.update(14, (() => {
    val x_34 = listValMut_4;
    val x_35 = x_34.methodInfo;
    val x_36 = x_35.==("getLoad");
    if (x_36)
      positionVar_8 = 15
    else
      positionVar_8 = 20
  }));
  data_9.update(15, (() => {
    val x_37 = listValMut_4;
    this.handleNonblockingMessage(x_37);
    resetData_0 = ();
    positionVar_8 = 16
  }));
  data_9.update(16, (() => positionVar_8 = 9));
  data_9.update(17, (() => positionVar_8 = 18));
  data_9.update(18, (() => {
    val x_38 = this.load;
    resetData_0 = x_38;
    positionVar_8 = 19
  }));
  data_9.update(19, (() => {
    val x_39 = resetData_0;
    val x_40 = x_39.asInstanceOf[scala.Any];
    bindingMut_3 = x_40;
    val x_41 = bindingMut_3;
    val x_42 = listValMut_4;
    x_42.reply(this, x_41);
    resetData_0 = ();
    positionVar_8 = 16
  }));
  data_9.update(20, (() => {
    val x_43 = listValMut_4;
    val x_44 = x_43.methodInfo;
    val x_45 = x_44.==("getLoad");
    val x_46 = x_45.`unary_!`;
    if (x_46)
      {
        val x_47 = listValMut_4;
        val x_48 = scala.collection.immutable.List.apply[meta.runtime.RequestMessage](x_47);
        val x_49 = this.addReceiveMessages(x_48);
        resetData_0 = x_49;
        positionVar_8 = 9
      }
    else
      ()
  }));
  data_9.update(21, (() => {
    val x_50 = iterMut_5;
    val x_51 = x_50.hasNext;
    val x_52 = x_51.`unary_!`;
    if (x_52)
      positionVar_8 = 22
    else
      ()
  }));
  data_9.update(22, (() => positionVar_8 = 23));
  data_9.update(23, (() => {
    val x_53 = bindingMut_6;
    val x_54 = x_53.<(1.0);
    if (x_54)
      positionVar_8 = 2
    else
      positionVar_8 = 24
  }));
  data_9.update(24, (() => {
    val x_55 = bindingMut_6;
    val x_56 = x_55.<(1.0);
    val x_57 = x_56.`unary_!`;
    if (x_57)
      positionVar_8 = 25
    else
      ()
  }));
  data_9.update(25, (() => positionVar_8 = 1));
  data_9.update(26, (() => positionVar_8 = 27));
  data_9.update(27, (() => {
    positionVar_8 = 28;
    unblockFlag_7 = false
  }));
  data_9.update(28, (() => positionVar_8 = 27));
  data_9.update(29, (() => positionVar_8 = 27));
  data_9
}).apply();
  

  override def getLoad(): Int = 
      this.load
  
  private def wrapper_getLoad(args: List[Any]): Int = {
    
          
          getLoad()
          
  }
  
  override def getPrice(): Int = 
      {
  scala.Predef.println("Received a message for get price in Commmunicating vehicle type 2!");
  30
}
  
  private def wrapper_getPrice(args: List[Any]): Int = {
    
          
          getPrice()
          
  }
  
  override def run(msgs: List[meta.runtime.Message]): (List[meta.runtime.Message], Int) = {
    addReceiveMessages(msgs)
    sendMessages.clear()
    unblockFlag_7 = true
    while (unblockFlag_7 && (positionVar_8 < 30)) {
      commands_58(positionVar_8)()
    }
    (sendMessages.toList, 1)
  }

  override def handleNonblockingMessage(m: meta.runtime.RequestMessage): Unit = {
    val args = m.argss.flatten
    val response = m.methodInfo match {
      case "getLoad" => wrapper_getLoad(args)
    case "getPrice" => wrapper_getPrice(args)
    }
    m.reply(this, response)
  }
  
    override def gotoHandleMessages(new_ir: Int = 3): meta.runtime.Actor = {
      // first entry, save the current IR to reflectionIR
      unblockFlag_7 = true

      if (reflectionIR_47 == -1){
        reflectionIR_47 = positionVar_8
        positionVar_8 = new_ir
      }

      while (positionVar_8 <= 20 && unblockFlag_7) {
        commands_58(positionVar_8)()
      }

      // reset instruction register when finishes processing
      if (positionVar_8 > 20) {
        positionVar_8 = reflectionIR_47
        reflectionIR_47 = -1
      }
      this
    }
    
override def SimClone(): CommunicatingVehicleType2 = {
  val newAgent = new CommunicatingVehicleType2()
  newAgent.price = price
  newAgent.load = load
  newAgent
}

override def SimReset(): Unit = {
  positionVar_8 = 0
  val newAgent = new CommunicatingVehicleType2()
  price = newAgent.price
  load = newAgent.load
}

}
