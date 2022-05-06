package generated.meta.test.inheritance4

class CommunicatingVehicle(val n: generated.meta.test.inheritance4.ShortDistanceTransport, val n2: generated.meta.test.inheritance4.Vehicle) extends generated.meta.test.inheritance4.Vehicle {

  price = 20;
  load = 10;
  override val licensePlate: Int = 0;
  private var  reflectionIR_56: Int = -1
private var resetData_0: scala.Any = null
private val resetData_1 = scala.collection.mutable.ListBuffer.apply[scala.collection.immutable.List[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]]]()
private var resetData_2: meta.runtime.ResponseMessage = null
private var bindingMut_3: scala.Any = null
private var listValMut_4: meta.runtime.RequestMessage = null
@transient private var iterMut_5: scala.collection.Iterator[meta.runtime.RequestMessage] = null
private var bindingMut_6: scala.Double = 0.0
private var bindingMut_7: scala.Boolean = false
private var bindingMut_8: generated.meta.test.inheritance4.Vehicle = null
private var bindingMut_9: java.lang.String = null
private var bindingMut_10: java.lang.String = null
private var bindingMut_11: scala.Int = 0
private var bindingMut_12: scala.Boolean = false
private var bindingMut_13: generated.meta.test.inheritance4.ShortDistanceTransport = null
private var bindingMut_14: scala.Boolean = false
private var bindingMut_15: generated.meta.test.inheritance4.ShortDistanceTransport = null
private var unblockFlag_16: scala.Boolean = true
private var positionVar_17: scala.Int = 0
private 
  val commands_146 = (() => {
  val data_18 = new scala.Array[scala.Function0[scala.Unit]](42);
  data_18.update(0, (() => positionVar_17 = 1));
  data_18.update(1, (() => {
    val x_19 = this.n;
    resetData_0 = x_19;
    val x_20 = resetData_0;
    val x_21 = x_20.asInstanceOf[generated.meta.test.inheritance4.ShortDistanceTransport];
    bindingMut_15 = x_21;
    val x_22 = bindingMut_15;
    val x_23 = x_22.!=(null);
    resetData_0 = x_23;
    val x_24 = resetData_0;
    val x_25 = x_24.asInstanceOf[scala.Boolean];
    bindingMut_14 = x_25;
    positionVar_17 = 2
  }));
  data_18.update(2, (() => {
    val x_26 = bindingMut_14;
    val x_27 = x_26.`unary_!`;
    if (x_27)
      positionVar_17 = 3
    else
      positionVar_17 = 34
  }));
  data_18.update(3, (() => {
    val x_28 = this.n2;
    resetData_0 = x_28;
    val x_29 = resetData_0;
    val x_30 = x_29.asInstanceOf[generated.meta.test.inheritance4.Vehicle];
    bindingMut_8 = x_30;
    val x_31 = bindingMut_8;
    val x_32 = x_31.!=(null);
    resetData_0 = x_32;
    val x_33 = resetData_0;
    val x_34 = x_33.asInstanceOf[scala.Boolean];
    bindingMut_7 = x_34;
    positionVar_17 = 4
  }));
  data_18.update(4, (() => {
    val x_35 = bindingMut_7;
    val x_36 = x_35.`unary_!`;
    if (x_36)
      positionVar_17 = 5
    else
      positionVar_17 = 33
  }));
  data_18.update(5, (() => {
    resetData_0 = 0.0;
    val x_37 = resetData_0;
    val x_38 = x_37.asInstanceOf[scala.Double];
    bindingMut_6 = x_38;
    positionVar_17 = 6
  }));
  data_18.update(6, (() => {
    val x_39 = bindingMut_6;
    val x_40 = x_39.+(1);
    resetData_0 = x_40;
    val x_41 = resetData_0;
    val x_42 = x_41.asInstanceOf[scala.Double];
    bindingMut_6 = x_42;
    positionVar_17 = 7;
    unblockFlag_16 = false
  }));
  data_18.update(7, (() => positionVar_17 = 8));
  data_18.update(8, (() => {
    val x_43 = this.popRequestMessages;
    val x_44 = x_43.iterator;
    iterMut_5 = x_44;
    positionVar_17 = 9
  }));
  data_18.update(9, (() => {
    val x_45 = iterMut_5;
    val x_46 = x_45.hasNext;
    if (x_46)
      {
        val x_47 = iterMut_5;
        val x_48 = x_47.next();
        listValMut_4 = x_48;
        positionVar_17 = 10
      }
    else
      positionVar_17 = 25
  }));
  data_18.update(10, (() => {
    val x_49 = listValMut_4;
    val x_50 = x_49.methodInfo;
    val x_51 = x_50.==("getLoad");
    if (x_51)
      positionVar_17 = 11
    else
      positionVar_17 = 17
  }));
  data_18.update(11, (() => {
    val x_52 = listValMut_4;
    this.handleNonblockingMessage(x_52);
    resetData_0 = ();
    positionVar_17 = 12
  }));
  data_18.update(12, (() => positionVar_17 = 13));
  data_18.update(13, (() => positionVar_17 = 9));
  data_18.update(14, (() => positionVar_17 = 15));
  data_18.update(15, (() => {
    val x_53 = this.load;
    resetData_0 = x_53;
    positionVar_17 = 16
  }));
  data_18.update(16, (() => {
    val x_54 = resetData_0;
    val x_55 = x_54.asInstanceOf[scala.Any];
    bindingMut_3 = x_55;
    val x_56 = bindingMut_3;
    val x_57 = listValMut_4;
    x_57.reply(this, x_56);
    resetData_0 = ();
    positionVar_17 = 12
  }));
  data_18.update(17, (() => {
    val x_58 = listValMut_4;
    val x_59 = x_58.methodInfo;
    val x_60 = x_59.==("getLoad");
    val x_61 = x_60.`unary_!`;
    if (x_61)
      positionVar_17 = 18
    else
      ()
  }));
  data_18.update(18, (() => {
    val x_62 = listValMut_4;
    val x_63 = x_62.methodInfo;
    val x_64 = x_63.==("getPrice");
    if (x_64)
      positionVar_17 = 19
    else
      positionVar_17 = 24
  }));
  data_18.update(19, (() => {
    val x_65 = listValMut_4;
    this.handleNonblockingMessage(x_65);
    resetData_0 = ();
    positionVar_17 = 20
  }));
  data_18.update(20, (() => positionVar_17 = 13));
  data_18.update(21, (() => positionVar_17 = 22));
  data_18.update(22, (() => {
    val x_66 = this.price;
    resetData_0 = x_66;
    positionVar_17 = 23
  }));
  data_18.update(23, (() => {
    val x_67 = resetData_0;
    val x_68 = x_67.asInstanceOf[scala.Any];
    bindingMut_3 = x_68;
    val x_69 = bindingMut_3;
    val x_70 = listValMut_4;
    x_70.reply(this, x_69);
    resetData_0 = ();
    positionVar_17 = 20
  }));
  data_18.update(24, (() => {
    val x_71 = listValMut_4;
    val x_72 = x_71.methodInfo;
    val x_73 = x_72.==("getPrice");
    val x_74 = x_73.`unary_!`;
    if (x_74)
      {
        val x_75 = listValMut_4;
        val x_76 = scala.collection.immutable.List.apply[meta.runtime.RequestMessage](x_75);
        val x_77 = this.addReceiveMessages(x_76);
        resetData_0 = x_77;
        positionVar_17 = 13
      }
    else
      ()
  }));
  data_18.update(25, (() => {
    val x_78 = iterMut_5;
    val x_79 = x_78.hasNext;
    val x_80 = x_79.`unary_!`;
    if (x_80)
      positionVar_17 = 26
    else
      ()
  }));
  data_18.update(26, (() => positionVar_17 = 27));
  data_18.update(27, (() => {
    val x_81 = bindingMut_6;
    val x_82 = x_81.<(1.0);
    if (x_82)
      positionVar_17 = 6
    else
      positionVar_17 = 28
  }));
  data_18.update(28, (() => {
    val x_83 = bindingMut_6;
    val x_84 = x_83.<(1.0);
    val x_85 = x_84.`unary_!`;
    if (x_85)
      positionVar_17 = 29
    else
      ()
  }));
  data_18.update(29, (() => positionVar_17 = 1));
  data_18.update(30, (() => positionVar_17 = 31));
  data_18.update(31, (() => {
    positionVar_17 = 32;
    unblockFlag_16 = false
  }));
  data_18.update(32, (() => positionVar_17 = 31));
  data_18.update(33, (() => {
    val x_86 = bindingMut_7;
    squid.lib.`package`.IfThenElse[scala.Unit](x_86, {
      val receiver_87 = this.n2;
      val x_88 = ((this): meta.runtime.Actor).id;
      val x_89 = receiver_87.id;
      val x_90 = scala.collection.immutable.Nil.::[scala.collection.immutable.List[scala.Any]](((scala.collection.immutable.Nil): scala.collection.immutable.List[scala.Any]));
      val x_91 = meta.runtime.RequestMessage.apply(x_88, x_89, false, "getPrice", x_90);
      val x_92 = x_91.sessionId;
      val x_93 = meta.runtime.Future.apply$default$2[scala.Int];
      val x_94 = meta.runtime.Future.apply[scala.Int](x_92, x_93);
      var v_95: meta.runtime.Future[scala.Int] = x_94;
      ((this): meta.runtime.Actor).sendMessage(x_91);
      val x_96 = x_91.sessionId;
      ((this): meta.runtime.Actor).setMessageResponseHandler(x_96, ((response_97: meta.runtime.Message) => {
        val x_98 = v_95;
        val x_99 = response_97.asInstanceOf[meta.runtime.ResponseMessage];
        val x_100 = x_99.arg;
        val x_101 = x_100.asInstanceOf[scala.Int];
        x_98.setValue(x_101)
      }));
      val x_102 = v_95;
      resetData_0 = x_102;
      positionVar_17 = 5
    }, ())
  }));
  data_18.update(34, (() => {
    val x_103 = bindingMut_14;
    squid.lib.`package`.IfThenElse[scala.Unit](x_103, {
      val x_104 = this.n;
      resetData_0 = x_104;
      val x_105 = resetData_0;
      val x_106 = x_105.asInstanceOf[generated.meta.test.inheritance4.ShortDistanceTransport];
      bindingMut_13 = x_106;
      val x_107 = this._container;
      val x_108 = x_107.!=(null);
      val x_113 = if (x_108)
        {
          val x_109 = this._container;
          val x_110 = x_109.proxyIds;
          val x_111 = bindingMut_13;
          val x_112 = x_111.id;
          x_110.contains[scala.Long](x_112)
        }
      else
        false;
      resetData_0 = x_113;
      val x_114 = resetData_0;
      val x_115 = x_114.asInstanceOf[scala.Boolean];
      bindingMut_12 = x_115;
      positionVar_17 = 35
    }, ())
  }));
  data_18.update(35, (() => {
    val x_116 = bindingMut_12;
    val x_117 = x_116.`unary_!`;
    if (x_117)
      {
        val x_118 = ((this): meta.runtime.Actor).id;
        val x_119 = bindingMut_13;
        val x_120 = x_119.id;
        val x_121 = scala.collection.immutable.Nil.::[scala.collection.immutable.List[scala.Any]](((scala.collection.immutable.Nil): scala.collection.immutable.List[scala.Any]));
        val x_122 = meta.runtime.RequestMessage.apply(x_118, x_120, true, "getPrice", x_121);
        ((this): meta.runtime.Actor).sendMessage(x_122);
        val x_123 = x_122.sessionId;
        ((this): meta.runtime.Actor).setMessageResponseHandler(x_123, ((response_124: meta.runtime.Message) => {
          val x_125 = response_124.asInstanceOf[meta.runtime.ResponseMessage];
          resetData_2 = x_125
        }));
        resetData_0 = null;
        positionVar_17 = 36
      }
    else
      positionVar_17 = 40
  }));
  data_18.update(36, (() => {
    positionVar_17 = 37;
    unblockFlag_16 = false
  }));
  data_18.update(37, (() => {
    val x_126 = resetData_2;
    val x_127 = x_126.==(null);
    if (x_127)
      positionVar_17 = 36
    else
      positionVar_17 = 38
  }));
  data_18.update(38, (() => {
    val x_128 = resetData_2;
    val x_129 = x_128.!=(null);
    if (x_129)
      {
        val x_130 = resetData_2;
        val x_131 = x_130.arg;
        resetData_0 = x_131;
        resetData_2 = null;
        positionVar_17 = 39
      }
    else
      ()
  }));
  data_18.update(39, (() => {
    val x_132 = resetData_0;
    val x_133 = x_132.asInstanceOf[scala.Int];
    bindingMut_11 = x_133;
    val x_134 = bindingMut_11;
    val x_135 = "The price for a short distance transport vehicle is ".+(x_134);
    resetData_0 = x_135;
    val x_136 = resetData_0;
    val x_137 = x_136.asInstanceOf[java.lang.String];
    bindingMut_10 = x_137;
    val x_138 = bindingMut_10;
    val x_139 = x_138.+(" should change");
    resetData_0 = x_139;
    val x_140 = resetData_0;
    val x_141 = x_140.asInstanceOf[java.lang.String];
    bindingMut_9 = x_141;
    val x_142 = bindingMut_9;
    scala.Predef.println(x_142);
    resetData_0 = ();
    positionVar_17 = 3
  }));
  data_18.update(40, (() => {
    val x_143 = bindingMut_12;
    squid.lib.`package`.IfThenElse[scala.Unit](x_143, {
      val x_144 = bindingMut_13;
      val x_145 = x_144.getPrice();
      resetData_0 = x_145;
      positionVar_17 = 39
    }, ())
  }));
  data_18.update(41, (() => positionVar_17 = 31));
  data_18
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
    unblockFlag_16 = true
    while (unblockFlag_16 && (positionVar_17 < 42)) {
      commands_146(positionVar_17)()
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
  
    override def gotoHandleMessages(new_ir: Int = 7): meta.runtime.Actor = {
      // first entry, save the current IR to reflectionIR
      unblockFlag_16 = true

      if (reflectionIR_56 == -1){
        reflectionIR_56 = positionVar_17
        positionVar_17 = new_ir
      }

      while (positionVar_17 <= 24 && unblockFlag_16) {
        commands_146(positionVar_17)()
      }

      // reset instruction register when finishes processing
      if (positionVar_17 > 24) {
        positionVar_17 = reflectionIR_56
        reflectionIR_56 = -1
      }
      this
    }
    
override def SimClone(): CommunicatingVehicle = {
  val newAgent = new CommunicatingVehicle(n, n2)
  newAgent.price = price
  newAgent.load = load
  newAgent
}

override def SimReset(): Unit = {
  positionVar_17 = 0
  val newAgent = new CommunicatingVehicle(n, n2)
  price = newAgent.price
  load = newAgent.load
}

}
