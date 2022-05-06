package generated.meta.test.inheritance3

class ShortDistanceTransport() extends generated.meta.test.inheritance3.Vehicle {

   val licensePlace: Int = 800;
  private val donot_copy: Double = 521.0;
  price = 20;
  load = 10;
  override val licensePlate: Int = 0;
  private var  reflectionIR_43: Int = -1
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
private var bindingMut_10: java.lang.String = null
private var bindingMut_11: scala.Int = 0
private var unblockFlag_12: scala.Boolean = true
private var positionVar_13: scala.Int = 0
private 
  val commands_110 = (() => {
  val data_14 = new scala.Array[scala.Function0[scala.Unit]](33);
  data_14.update(0, (() => {
    this.`price_=`(15);
    resetData_0 = ();
    positionVar_13 = 1
  }));
  data_14.update(1, (() => positionVar_13 = 2));
  data_14.update(2, (() => {
    positionVar_13 = 3;
    val x_15 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
    val x_16 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_15, 31);
    val x_17 = scala.collection.immutable.Nil.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_16);
    resetData_1.prepend(x_17)
  }));
  data_14.update(3, (() => {
    val x_18 = this.price;
    val x_19 = x_18.+(2);
    this.`price_=`(x_19);
    val x_20 = this.price;
    resetData_0 = x_20;
    val x_21 = resetData_1.remove(0);
    val x_25 = x_21.find(((x_22: scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]) => {
      val x_23 = x_22._1;
      val x_24 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
      x_23.==(x_24)
    }));
    val x_26 = x_25.get;
    val x_27 = x_26._2;
    positionVar_13 = x_27
  }));
  data_14.update(4, (() => {
    val x_28 = resetData_0;
    val x_29 = x_28.asInstanceOf[scala.Any];
    bindingMut_3 = x_29;
    val x_30 = bindingMut_3;
    val x_31 = listValMut_4;
    x_31.reply(this, x_30);
    resetData_0 = ();
    positionVar_13 = 5
  }));
  data_14.update(5, (() => positionVar_13 = 6));
  data_14.update(6, (() => positionVar_13 = 7));
  data_14.update(7, (() => {
    val x_32 = iterMut_5;
    val x_33 = x_32.hasNext;
    if (x_33)
      {
        val x_34 = iterMut_5;
        val x_35 = x_34.next();
        listValMut_4 = x_35;
        positionVar_13 = 8
      }
    else
      positionVar_13 = 23
  }));
  data_14.update(8, (() => {
    val x_36 = listValMut_4;
    val x_37 = x_36.methodInfo;
    val x_38 = x_37.==("getPrice");
    if (x_38)
      positionVar_13 = 9
    else
      positionVar_13 = 11
  }));
  data_14.update(9, (() => {
    val x_39 = listValMut_4;
    this.handleNonblockingMessage(x_39);
    resetData_0 = ();
    positionVar_13 = 5
  }));
  data_14.update(10, (() => {
    positionVar_13 = 3;
    val x_40 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
    val x_41 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_40, 4);
    val x_42 = scala.collection.immutable.Nil.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_41);
    resetData_1.prepend(x_42)
  }));
  data_14.update(11, (() => {
    val x_43 = listValMut_4;
    val x_44 = x_43.methodInfo;
    val x_45 = x_44.==("getPrice");
    val x_46 = x_45.`unary_!`;
    if (x_46)
      positionVar_13 = 12
    else
      ()
  }));
  data_14.update(12, (() => {
    val x_47 = listValMut_4;
    val x_48 = x_47.methodInfo;
    val x_49 = x_48.==("getLoad");
    if (x_49)
      positionVar_13 = 13
    else
      positionVar_13 = 22
  }));
  data_14.update(13, (() => {
    val x_50 = listValMut_4;
    this.handleNonblockingMessage(x_50);
    resetData_0 = ();
    positionVar_13 = 14
  }));
  data_14.update(14, (() => positionVar_13 = 6));
  data_14.update(15, (() => {
    positionVar_13 = 16;
    val x_51 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
    val x_52 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_51, 17);
    val x_53 = scala.collection.immutable.Nil.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_52);
    resetData_1.prepend(x_53)
  }));
  data_14.update(16, (() => {
    val x_54 = this.load;
    resetData_0 = x_54;
    val x_55 = resetData_1.remove(0);
    val x_59 = x_55.find(((x_56: scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]) => {
      val x_57 = x_56._1;
      val x_58 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
      x_57.==(x_58)
    }));
    val x_60 = x_59.get;
    val x_61 = x_60._2;
    positionVar_13 = x_61
  }));
  data_14.update(17, (() => {
    val x_62 = resetData_0;
    val x_63 = x_62.asInstanceOf[scala.Any];
    bindingMut_3 = x_63;
    val x_64 = bindingMut_3;
    val x_65 = listValMut_4;
    x_65.reply(this, x_64);
    resetData_0 = ();
    positionVar_13 = 14
  }));
  data_14.update(18, (() => {
    val x_66 = resetData_0;
    val x_67 = x_66.asInstanceOf[scala.Int];
    bindingMut_9 = x_67;
    val x_68 = bindingMut_9;
    val x_69 = "Short distance transport load is ".+(x_68);
    resetData_0 = x_69;
    val x_70 = resetData_0;
    val x_71 = x_70.asInstanceOf[java.lang.String];
    bindingMut_8 = x_71;
    val x_72 = bindingMut_8;
    val x_73 = x_72.+(" should be 10");
    resetData_0 = x_73;
    val x_74 = resetData_0;
    val x_75 = x_74.asInstanceOf[java.lang.String];
    bindingMut_7 = x_75;
    val x_76 = bindingMut_7;
    scala.Predef.println(x_76);
    resetData_0 = ();
    resetData_0 = 0.0;
    val x_77 = resetData_0;
    val x_78 = x_77.asInstanceOf[scala.Double];
    bindingMut_6 = x_78;
    positionVar_13 = 19
  }));
  data_14.update(19, (() => {
    val x_79 = bindingMut_6;
    val x_80 = x_79.+(1);
    resetData_0 = x_80;
    val x_81 = resetData_0;
    val x_82 = x_81.asInstanceOf[scala.Double];
    bindingMut_6 = x_82;
    positionVar_13 = 20;
    unblockFlag_12 = false
  }));
  data_14.update(20, (() => positionVar_13 = 21));
  data_14.update(21, (() => {
    val x_83 = this.popRequestMessages;
    val x_84 = x_83.iterator;
    iterMut_5 = x_84;
    positionVar_13 = 7
  }));
  data_14.update(22, (() => {
    val x_85 = listValMut_4;
    val x_86 = x_85.methodInfo;
    val x_87 = x_86.==("getLoad");
    val x_88 = x_87.`unary_!`;
    if (x_88)
      {
        val x_89 = listValMut_4;
        val x_90 = scala.collection.immutable.List.apply[meta.runtime.RequestMessage](x_89);
        val x_91 = this.addReceiveMessages(x_90);
        resetData_0 = x_91;
        positionVar_13 = 6
      }
    else
      ()
  }));
  data_14.update(23, (() => {
    val x_92 = iterMut_5;
    val x_93 = x_92.hasNext;
    val x_94 = x_93.`unary_!`;
    if (x_94)
      positionVar_13 = 24
    else
      ()
  }));
  data_14.update(24, (() => positionVar_13 = 25));
  data_14.update(25, (() => {
    val x_95 = bindingMut_6;
    val x_96 = x_95.<(1.0);
    if (x_96)
      positionVar_13 = 19
    else
      positionVar_13 = 26
  }));
  data_14.update(26, (() => {
    val x_97 = bindingMut_6;
    val x_98 = x_97.<(1.0);
    val x_99 = x_98.`unary_!`;
    if (x_99)
      positionVar_13 = 27
    else
      ()
  }));
  data_14.update(27, (() => positionVar_13 = 2));
  data_14.update(28, (() => positionVar_13 = 29));
  data_14.update(29, (() => {
    positionVar_13 = 30;
    unblockFlag_12 = false
  }));
  data_14.update(30, (() => positionVar_13 = 29));
  data_14.update(31, (() => {
    val x_100 = resetData_0;
    val x_101 = x_100.asInstanceOf[scala.Int];
    bindingMut_11 = x_101;
    val x_102 = bindingMut_11;
    val x_103 = "Short distance transport price is ".+(x_102);
    resetData_0 = x_103;
    val x_104 = resetData_0;
    val x_105 = x_104.asInstanceOf[java.lang.String];
    bindingMut_10 = x_105;
    val x_106 = bindingMut_10;
    scala.Predef.println(x_106);
    resetData_0 = ();
    positionVar_13 = 16;
    val x_107 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
    val x_108 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_107, 18);
    val x_109 = scala.collection.immutable.Nil.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_108);
    resetData_1.prepend(x_109)
  }));
  data_14.update(32, (() => positionVar_13 = 29));
  data_14
}).apply();
  

  override def getPrice(): Int = 
      {
  val x_0 = this.price;
  val x_1 = x_0.+(2);
  this.`price_=`(x_1);
  this.price
}
  
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
    unblockFlag_12 = true
    while (unblockFlag_12 && (positionVar_13 < 33)) {
      commands_110(positionVar_13)()
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
  
    override def gotoHandleMessages(new_ir: Int = 3): meta.runtime.Actor = {
      // first entry, save the current IR to reflectionIR
      unblockFlag_12 = true

      if (reflectionIR_43 == -1){
        reflectionIR_43 = positionVar_13
        positionVar_13 = new_ir
      }

      while (positionVar_13 <= 22 && unblockFlag_12) {
        commands_110(positionVar_13)()
      }

      // reset instruction register when finishes processing
      if (positionVar_13 > 22) {
        positionVar_13 = reflectionIR_43
        reflectionIR_43 = -1
      }
      this
    }
    
override def SimClone(): ShortDistanceTransport = {
  val newAgent = new ShortDistanceTransport()
  newAgent.price = price
  newAgent.load = load
  newAgent
}

override def SimReset(): Unit = {
  positionVar_13 = 0
  val newAgent = new ShortDistanceTransport()
  price = newAgent.price
  load = newAgent.load
}

}
