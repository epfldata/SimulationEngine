package generated.meta.test.inheritance4

class CommunicatingVehicle(val n: generated.meta.test.inheritance4.ShortDistanceTransport) extends generated.meta.test.inheritance4.Vehicle {

  price = 20;
  load = 10;
  override val licensePlate: Int = 0;
  private var  reflectionIR_36: Int = -1
private var resetData_0: scala.Any = null
private val resetData_1 = scala.collection.mutable.ListBuffer.apply[scala.collection.immutable.List[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]]]()
private var resetData_2: meta.runtime.ResponseMessage = null
private var bindingMut_3: scala.Any = null
private var listValMut_4: meta.runtime.RequestMessage = null
@transient private var iterMut_5: scala.collection.Iterator[meta.runtime.RequestMessage] = null
private var bindingMut_6: scala.Double = 0.0
private var bindingMut_7: java.lang.String = null
private var bindingMut_8: java.lang.String = null
private var bindingMut_9: scala.Int = 0
private var bindingMut_10: scala.Boolean = false
private var bindingMut_11: generated.meta.test.inheritance4.ShortDistanceTransport = null
private var bindingMut_12: scala.Boolean = false
private var bindingMut_13: generated.meta.test.inheritance4.ShortDistanceTransport = null
private var unblockFlag_14: scala.Boolean = true
private var positionVar_15: scala.Int = 0
private 
  val commands_118 = (() => {
  val data_16 = new scala.Array[scala.Function0[scala.Unit]](39);
  data_16.update(0, (() => positionVar_15 = 1));
  data_16.update(1, (() => {
    val x_17 = this.n;
    resetData_0 = x_17;
    val x_18 = resetData_0;
    val x_19 = x_18.asInstanceOf[generated.meta.test.inheritance4.ShortDistanceTransport];
    bindingMut_13 = x_19;
    val x_20 = bindingMut_13;
    val x_21 = x_20.!=(null);
    resetData_0 = x_21;
    val x_22 = resetData_0;
    val x_23 = x_22.asInstanceOf[scala.Boolean];
    bindingMut_12 = x_23;
    positionVar_15 = 2
  }));
  data_16.update(2, (() => {
    val x_24 = bindingMut_12;
    val x_25 = x_24.`unary_!`;
    if (x_25)
      positionVar_15 = 3
    else
      positionVar_15 = 31
  }));
  data_16.update(3, (() => {
    resetData_0 = 0.0;
    val x_26 = resetData_0;
    val x_27 = x_26.asInstanceOf[scala.Double];
    bindingMut_6 = x_27;
    positionVar_15 = 4
  }));
  data_16.update(4, (() => {
    val x_28 = bindingMut_6;
    val x_29 = x_28.+(1);
    resetData_0 = x_29;
    val x_30 = resetData_0;
    val x_31 = x_30.asInstanceOf[scala.Double];
    bindingMut_6 = x_31;
    positionVar_15 = 5;
    unblockFlag_14 = false
  }));
  data_16.update(5, (() => positionVar_15 = 6));
  data_16.update(6, (() => {
    val x_32 = this.popRequestMessages;
    val x_33 = x_32.iterator;
    iterMut_5 = x_33;
    positionVar_15 = 7
  }));
  data_16.update(7, (() => {
    val x_34 = iterMut_5;
    val x_35 = x_34.hasNext;
    if (x_35)
      {
        val x_36 = iterMut_5;
        val x_37 = x_36.next();
        listValMut_4 = x_37;
        positionVar_15 = 8
      }
    else
      positionVar_15 = 23
  }));
  data_16.update(8, (() => {
    val x_38 = listValMut_4;
    val x_39 = x_38.methodInfo;
    val x_40 = x_39.==("getLoad");
    if (x_40)
      positionVar_15 = 9
    else
      positionVar_15 = 15
  }));
  data_16.update(9, (() => {
    val x_41 = listValMut_4;
    this.handleNonblockingMessage(x_41);
    resetData_0 = ();
    positionVar_15 = 10
  }));
  data_16.update(10, (() => positionVar_15 = 11));
  data_16.update(11, (() => positionVar_15 = 7));
  data_16.update(12, (() => positionVar_15 = 13));
  data_16.update(13, (() => {
    val x_42 = this.load;
    resetData_0 = x_42;
    positionVar_15 = 14
  }));
  data_16.update(14, (() => {
    val x_43 = resetData_0;
    val x_44 = x_43.asInstanceOf[scala.Any];
    bindingMut_3 = x_44;
    val x_45 = bindingMut_3;
    val x_46 = listValMut_4;
    x_46.reply(this, x_45);
    resetData_0 = ();
    positionVar_15 = 10
  }));
  data_16.update(15, (() => {
    val x_47 = listValMut_4;
    val x_48 = x_47.methodInfo;
    val x_49 = x_48.==("getLoad");
    val x_50 = x_49.`unary_!`;
    if (x_50)
      positionVar_15 = 16
    else
      ()
  }));
  data_16.update(16, (() => {
    val x_51 = listValMut_4;
    val x_52 = x_51.methodInfo;
    val x_53 = x_52.==("getPrice");
    if (x_53)
      positionVar_15 = 17
    else
      positionVar_15 = 22
  }));
  data_16.update(17, (() => {
    val x_54 = listValMut_4;
    this.handleNonblockingMessage(x_54);
    resetData_0 = ();
    positionVar_15 = 18
  }));
  data_16.update(18, (() => positionVar_15 = 11));
  data_16.update(19, (() => positionVar_15 = 20));
  data_16.update(20, (() => {
    val x_55 = this.price;
    resetData_0 = x_55;
    positionVar_15 = 21
  }));
  data_16.update(21, (() => {
    val x_56 = resetData_0;
    val x_57 = x_56.asInstanceOf[scala.Any];
    bindingMut_3 = x_57;
    val x_58 = bindingMut_3;
    val x_59 = listValMut_4;
    x_59.reply(this, x_58);
    resetData_0 = ();
    positionVar_15 = 18
  }));
  data_16.update(22, (() => {
    val x_60 = listValMut_4;
    val x_61 = x_60.methodInfo;
    val x_62 = x_61.==("getPrice");
    val x_63 = x_62.`unary_!`;
    if (x_63)
      {
        val x_64 = listValMut_4;
        val x_65 = scala.collection.immutable.List.apply[meta.runtime.RequestMessage](x_64);
        val x_66 = this.addReceiveMessages(x_65);
        resetData_0 = x_66;
        positionVar_15 = 11
      }
    else
      ()
  }));
  data_16.update(23, (() => {
    val x_67 = iterMut_5;
    val x_68 = x_67.hasNext;
    val x_69 = x_68.`unary_!`;
    if (x_69)
      positionVar_15 = 24
    else
      ()
  }));
  data_16.update(24, (() => positionVar_15 = 25));
  data_16.update(25, (() => {
    val x_70 = bindingMut_6;
    val x_71 = x_70.<(1.0);
    if (x_71)
      positionVar_15 = 4
    else
      positionVar_15 = 26
  }));
  data_16.update(26, (() => {
    val x_72 = bindingMut_6;
    val x_73 = x_72.<(1.0);
    val x_74 = x_73.`unary_!`;
    if (x_74)
      positionVar_15 = 27
    else
      ()
  }));
  data_16.update(27, (() => positionVar_15 = 1));
  data_16.update(28, (() => positionVar_15 = 29));
  data_16.update(29, (() => {
    positionVar_15 = 30;
    unblockFlag_14 = false
  }));
  data_16.update(30, (() => positionVar_15 = 29));
  data_16.update(31, (() => {
    val x_75 = bindingMut_12;
    squid.lib.`package`.IfThenElse[scala.Unit](x_75, {
      val x_76 = this.n;
      resetData_0 = x_76;
      val x_77 = resetData_0;
      val x_78 = x_77.asInstanceOf[generated.meta.test.inheritance4.ShortDistanceTransport];
      bindingMut_11 = x_78;
      val x_79 = this._container;
      val x_80 = x_79.!=(null);
      val x_85 = if (x_80)
        {
          val x_81 = this._container;
          val x_82 = x_81.proxyIds;
          val x_83 = bindingMut_11;
          val x_84 = x_83.id;
          x_82.contains[scala.Long](x_84)
        }
      else
        false;
      resetData_0 = x_85;
      val x_86 = resetData_0;
      val x_87 = x_86.asInstanceOf[scala.Boolean];
      bindingMut_10 = x_87;
      positionVar_15 = 32
    }, ())
  }));
  data_16.update(32, (() => {
    val x_88 = bindingMut_10;
    val x_89 = x_88.`unary_!`;
    if (x_89)
      {
        val x_90 = ((this): meta.runtime.Actor).id;
        val x_91 = bindingMut_11;
        val x_92 = x_91.id;
        val x_93 = scala.collection.immutable.Nil.::[scala.collection.immutable.List[scala.Any]](((scala.collection.immutable.Nil): scala.collection.immutable.List[scala.Any]));
        val x_94 = meta.runtime.RequestMessage.apply(x_90, x_92, true, "getPrice", x_93);
        ((this): meta.runtime.Actor).sendMessage(x_94);
        val x_95 = x_94.sessionId;
        ((this): meta.runtime.Actor).setMessageResponseHandler(x_95, ((response_96: meta.runtime.Message) => {
          val x_97 = response_96.asInstanceOf[meta.runtime.ResponseMessage];
          resetData_2 = x_97
        }));
        resetData_0 = null;
        positionVar_15 = 33
      }
    else
      positionVar_15 = 37
  }));
  data_16.update(33, (() => {
    positionVar_15 = 34;
    unblockFlag_14 = false
  }));
  data_16.update(34, (() => {
    val x_98 = resetData_2;
    val x_99 = x_98.==(null);
    if (x_99)
      positionVar_15 = 33
    else
      positionVar_15 = 35
  }));
  data_16.update(35, (() => {
    val x_100 = resetData_2;
    val x_101 = x_100.!=(null);
    if (x_101)
      {
        val x_102 = resetData_2;
        val x_103 = x_102.arg;
        resetData_0 = x_103;
        resetData_2 = null;
        positionVar_15 = 36
      }
    else
      ()
  }));
  data_16.update(36, (() => {
    val x_104 = resetData_0;
    val x_105 = x_104.asInstanceOf[scala.Int];
    bindingMut_9 = x_105;
    val x_106 = bindingMut_9;
    val x_107 = "The price for a short distance transport vehicle is ".+(x_106);
    resetData_0 = x_107;
    val x_108 = resetData_0;
    val x_109 = x_108.asInstanceOf[java.lang.String];
    bindingMut_8 = x_109;
    val x_110 = bindingMut_8;
    val x_111 = x_110.+(" should change");
    resetData_0 = x_111;
    val x_112 = resetData_0;
    val x_113 = x_112.asInstanceOf[java.lang.String];
    bindingMut_7 = x_113;
    val x_114 = bindingMut_7;
    scala.Predef.println(x_114);
    resetData_0 = ();
    positionVar_15 = 3
  }));
  data_16.update(37, (() => {
    val x_115 = bindingMut_10;
    squid.lib.`package`.IfThenElse[scala.Unit](x_115, {
      val x_116 = bindingMut_11;
      val x_117 = x_116.getPrice();
      resetData_0 = x_117;
      positionVar_15 = 36
    }, ())
  }));
  data_16.update(38, (() => positionVar_15 = 29));
  data_16
}).apply();
  

  override def getPrice(): Int = 
      this.price
  
  private def wrapper_getPrice(args: List[Any]): Int = {
    
          
          getPrice()
          
  }
  
  override def getLoad(): Int = 
      this.load
  
  private def wrapper_getLoad(args: List[Any]): Int = {
    
          
          getLoad()
          
  }
  
  override def run(msgs: List[meta.runtime.Message]): (List[meta.runtime.Message], Int) = {
    addReceiveMessages(msgs)
    sendMessages.clear()
    unblockFlag_14 = true
    while (unblockFlag_14 && (positionVar_15 < 39)) {
      commands_118(positionVar_15)()
    }
    (sendMessages.toList, 1)
  }

  override def handleNonblockingMessage(m: meta.runtime.RequestMessage): Unit = {
    val args = m.argss.flatten
    val response = m.methodInfo match {
      case "getPrice" => wrapper_getPrice(args)
    case "getLoad" => wrapper_getLoad(args)
    }
    m.reply(this, response)
  }
  
    override def gotoHandleMessages(new_ir: Int = 5): meta.runtime.Actor = {
      // first entry, save the current IR to reflectionIR
      unblockFlag_14 = true

      if (reflectionIR_36 == -1){
        reflectionIR_36 = positionVar_15
        positionVar_15 = new_ir
      }

      while (positionVar_15 <= 22 && unblockFlag_14) {
        commands_118(positionVar_15)()
      }

      // reset instruction register when finishes processing
      if (positionVar_15 > 22) {
        positionVar_15 = reflectionIR_36
        reflectionIR_36 = -1
      }
      this
    }
    
override def SimClone(): CommunicatingVehicle = {
  val newAgent = new CommunicatingVehicle(n)
  newAgent.price = price
  newAgent.load = load
  newAgent
}

override def SimReset(): Unit = {
  positionVar_15 = 0
  val newAgent = new CommunicatingVehicle(n)
  price = newAgent.price
  load = newAgent.load
}

}
