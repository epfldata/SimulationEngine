package generated.meta.test.inheritance4

class Vehicle() extends meta.runtime.Actor {

   var price: Int = 20;
   var load: Int = 10;
   val licensePlate: Int = 0;
  private val donot_copy: Double = 512.0;
  private var  reflectionIR_4: Int = -1
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
  val commands_71 = (() => {
  val data_9 = new scala.Array[scala.Function0[scala.Unit]](37);
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
      positionVar_8 = 28
  }));
  data_9.update(6, (() => {
    val x_22 = listValMut_4;
    val x_23 = x_22.methodInfo;
    val x_24 = x_23.==("getLoad");
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
    val x_26 = this.load;
    resetData_0 = x_26;
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
    val x_33 = x_32.==("getLoad");
    val x_34 = x_33.`unary_!`;
    if (x_34)
      positionVar_8 = 14
    else
      ()
  }));
  data_9.update(14, (() => {
    val x_35 = listValMut_4;
    val x_36 = x_35.methodInfo;
    val x_37 = x_36.==("getPrice");
    if (x_37)
      positionVar_8 = 15
    else
      positionVar_8 = 20
  }));
  data_9.update(15, (() => {
    val x_38 = listValMut_4;
    this.handleNonblockingMessage(x_38);
    resetData_0 = ();
    positionVar_8 = 16
  }));
  data_9.update(16, (() => positionVar_8 = 9));
  data_9.update(17, (() => positionVar_8 = 18));
  data_9.update(18, (() => {
    val x_39 = this.price;
    resetData_0 = x_39;
    positionVar_8 = 19
  }));
  data_9.update(19, (() => {
    val x_40 = resetData_0;
    val x_41 = x_40.asInstanceOf[scala.Any];
    bindingMut_3 = x_41;
    val x_42 = bindingMut_3;
    val x_43 = listValMut_4;
    x_43.reply(this, x_42);
    resetData_0 = ();
    positionVar_8 = 16
  }));
  data_9.update(20, (() => {
    val x_44 = listValMut_4;
    val x_45 = x_44.methodInfo;
    val x_46 = x_45.==("getPrice");
    val x_47 = x_46.`unary_!`;
    if (x_47)
      positionVar_8 = 21
    else
      ()
  }));
  data_9.update(21, (() => {
    val x_48 = listValMut_4;
    val x_49 = x_48.methodInfo;
    val x_50 = x_49.==("local_mtd");
    if (x_50)
      positionVar_8 = 22
    else
      positionVar_8 = 27
  }));
  data_9.update(22, (() => {
    val x_51 = listValMut_4;
    this.handleNonblockingMessage(x_51);
    resetData_0 = ();
    positionVar_8 = 23
  }));
  data_9.update(23, (() => positionVar_8 = 9));
  data_9.update(24, (() => positionVar_8 = 25));
  data_9.update(25, (() => {
    scala.Predef.println("This is an invisible local method!");
    resetData_0 = ();
    positionVar_8 = 26
  }));
  data_9.update(26, (() => {
    val x_52 = resetData_0;
    val x_53 = x_52.asInstanceOf[scala.Any];
    bindingMut_3 = x_53;
    val x_54 = bindingMut_3;
    val x_55 = listValMut_4;
    x_55.reply(this, x_54);
    resetData_0 = ();
    positionVar_8 = 23
  }));
  data_9.update(27, (() => {
    val x_56 = listValMut_4;
    val x_57 = x_56.methodInfo;
    val x_58 = x_57.==("local_mtd");
    val x_59 = x_58.`unary_!`;
    if (x_59)
      {
        val x_60 = listValMut_4;
        val x_61 = scala.collection.immutable.List.apply[meta.runtime.RequestMessage](x_60);
        val x_62 = this.addReceiveMessages(x_61);
        resetData_0 = x_62;
        positionVar_8 = 9
      }
    else
      ()
  }));
  data_9.update(28, (() => {
    val x_63 = iterMut_5;
    val x_64 = x_63.hasNext;
    val x_65 = x_64.`unary_!`;
    if (x_65)
      positionVar_8 = 29
    else
      ()
  }));
  data_9.update(29, (() => positionVar_8 = 30));
  data_9.update(30, (() => {
    val x_66 = bindingMut_6;
    val x_67 = x_66.<(1.0);
    if (x_67)
      positionVar_8 = 2
    else
      positionVar_8 = 31
  }));
  data_9.update(31, (() => {
    val x_68 = bindingMut_6;
    val x_69 = x_68.<(1.0);
    val x_70 = x_69.`unary_!`;
    if (x_70)
      positionVar_8 = 32
    else
      ()
  }));
  data_9.update(32, (() => positionVar_8 = 1));
  data_9.update(33, (() => positionVar_8 = 34));
  data_9.update(34, (() => {
    positionVar_8 = 35;
    unblockFlag_7 = false
  }));
  data_9.update(35, (() => positionVar_8 = 34));
  data_9.update(36, (() => positionVar_8 = 34));
  data_9
}).apply();
  

   def getLoad(): Int = 
      this.load
  
  private def wrapper_getLoad(args: List[Any]): Int = {
    
          
          getLoad()
          
  }
  
  private def local_mtd(): Unit = 
      scala.Predef.println("This is an invisible local method!")
  
  private def wrapper_local_mtd(args: List[Any]): Unit = {
    
          
          local_mtd()
          
  }
  
   def getPrice(): Int = 
      this.price
  
  private def wrapper_getPrice(args: List[Any]): Int = {
    
          
          getPrice()
          
  }
  
  override def run(msgs: List[meta.runtime.Message]): (List[meta.runtime.Message], Int) = {
    addReceiveMessages(msgs)
    sendMessages.clear()
    unblockFlag_7 = true
    while (unblockFlag_7 && (positionVar_8 < 37)) {
      commands_71(positionVar_8)()
    }
    (sendMessages.toList, 1)
  }

  override def handleNonblockingMessage(m: meta.runtime.RequestMessage): Unit = {
    val args = m.argss.flatten
    val response = m.methodInfo match {
      case "getLoad" => wrapper_getLoad(args)
    case "local_mtd" => wrapper_local_mtd(args)
    case "getPrice" => wrapper_getPrice(args)
    }
    m.reply(this, response)
  }
  
    override def gotoHandleMessages(new_ir: Int = 3): meta.runtime.Actor = {
      // first entry, save the current IR to reflectionIR
      unblockFlag_7 = true

      if (reflectionIR_4 == -1){
        reflectionIR_4 = positionVar_8
        positionVar_8 = new_ir
      }

      while (positionVar_8 <= 27 && unblockFlag_7) {
        commands_71(positionVar_8)()
      }

      // reset instruction register when finishes processing
      if (positionVar_8 > 27) {
        positionVar_8 = reflectionIR_4
        reflectionIR_4 = -1
      }
      this
    }
    
override def SimClone(): Vehicle = {
  val newAgent = new Vehicle()
  newAgent.price = price
  newAgent.load = load
  newAgent
}

override def SimReset(): Unit = {
  positionVar_8 = 0
  val newAgent = new Vehicle()
  price = newAgent.price
  load = newAgent.load
}

}
