package generated.meta.test.inheritance3

class Van() extends generated.meta.test.inheritance3.Vehicle {

  price = 20;
  load = 10;
  override val licensePlate: Int = 0;
  private var  reflectionIR_37: Int = -1
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
private var bindingMut_11: java.lang.String = null
private var bindingMut_12: scala.Int = 0
private var unblockFlag_13: scala.Boolean = true
private var positionVar_14: scala.Int = 0
private 
  val commands_113 = (() => {
  val data_15 = new scala.Array[scala.Function0[scala.Unit]](32);
  data_15.update(0, (() => positionVar_14 = 1));
  data_15.update(1, (() => {
    positionVar_14 = 2;
    val x_16 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
    val x_17 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_16, 30);
    val x_18 = scala.collection.immutable.Nil.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_17);
    resetData_1.prepend(x_18)
  }));
  data_15.update(2, (() => {
    val x_19 = this.price;
    resetData_0 = x_19;
    val x_20 = resetData_1.remove(0);
    val x_24 = x_20.find(((x_21: scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]) => {
      val x_22 = x_21._1;
      val x_23 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
      x_22.==(x_23)
    }));
    val x_25 = x_24.get;
    val x_26 = x_25._2;
    positionVar_14 = x_26
  }));
  data_15.update(3, (() => {
    val x_27 = resetData_0;
    val x_28 = x_27.asInstanceOf[scala.Any];
    bindingMut_3 = x_28;
    val x_29 = bindingMut_3;
    val x_30 = listValMut_4;
    x_30.reply(this, x_29);
    resetData_0 = ();
    positionVar_14 = 4
  }));
  data_15.update(4, (() => positionVar_14 = 5));
  data_15.update(5, (() => positionVar_14 = 6));
  data_15.update(6, (() => {
    val x_31 = iterMut_5;
    val x_32 = x_31.hasNext;
    if (x_32)
      {
        val x_33 = iterMut_5;
        val x_34 = x_33.next();
        listValMut_4 = x_34;
        positionVar_14 = 7
      }
    else
      positionVar_14 = 22
  }));
  data_15.update(7, (() => {
    val x_35 = listValMut_4;
    val x_36 = x_35.methodInfo;
    val x_37 = x_36.==("getLoad");
    if (x_37)
      positionVar_14 = 8
    else
      positionVar_14 = 17
  }));
  data_15.update(8, (() => {
    val x_38 = listValMut_4;
    this.handleNonblockingMessage(x_38);
    resetData_0 = ();
    positionVar_14 = 9
  }));
  data_15.update(9, (() => positionVar_14 = 5));
  data_15.update(10, (() => {
    positionVar_14 = 11;
    val x_39 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
    val x_40 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_39, 12);
    val x_41 = scala.collection.immutable.Nil.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_40);
    resetData_1.prepend(x_41)
  }));
  data_15.update(11, (() => {
    val x_42 = this.load;
    resetData_0 = x_42;
    val x_43 = resetData_1.remove(0);
    val x_47 = x_43.find(((x_44: scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]) => {
      val x_45 = x_44._1;
      val x_46 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
      x_45.==(x_46)
    }));
    val x_48 = x_47.get;
    val x_49 = x_48._2;
    positionVar_14 = x_49
  }));
  data_15.update(12, (() => {
    val x_50 = resetData_0;
    val x_51 = x_50.asInstanceOf[scala.Any];
    bindingMut_3 = x_51;
    val x_52 = bindingMut_3;
    val x_53 = listValMut_4;
    x_53.reply(this, x_52);
    resetData_0 = ();
    positionVar_14 = 9
  }));
  data_15.update(13, (() => {
    val x_54 = resetData_0;
    val x_55 = x_54.asInstanceOf[scala.Int];
    bindingMut_9 = x_55;
    val x_56 = bindingMut_9;
    val x_57 = "Van load ".+(x_56);
    resetData_0 = x_57;
    val x_58 = resetData_0;
    val x_59 = x_58.asInstanceOf[java.lang.String];
    bindingMut_8 = x_59;
    val x_60 = bindingMut_8;
    val x_61 = x_60.+(" should be 10");
    resetData_0 = x_61;
    val x_62 = resetData_0;
    val x_63 = x_62.asInstanceOf[java.lang.String];
    bindingMut_7 = x_63;
    val x_64 = bindingMut_7;
    scala.Predef.println(x_64);
    resetData_0 = ();
    resetData_0 = 0.0;
    val x_65 = resetData_0;
    val x_66 = x_65.asInstanceOf[scala.Double];
    bindingMut_6 = x_66;
    positionVar_14 = 14
  }));
  data_15.update(14, (() => {
    val x_67 = bindingMut_6;
    val x_68 = x_67.+(1);
    resetData_0 = x_68;
    val x_69 = resetData_0;
    val x_70 = x_69.asInstanceOf[scala.Double];
    bindingMut_6 = x_70;
    positionVar_14 = 15;
    unblockFlag_13 = false
  }));
  data_15.update(15, (() => positionVar_14 = 16));
  data_15.update(16, (() => {
    val x_71 = this.popRequestMessages;
    val x_72 = x_71.iterator;
    iterMut_5 = x_72;
    positionVar_14 = 6
  }));
  data_15.update(17, (() => {
    val x_73 = listValMut_4;
    val x_74 = x_73.methodInfo;
    val x_75 = x_74.==("getLoad");
    val x_76 = x_75.`unary_!`;
    if (x_76)
      positionVar_14 = 18
    else
      ()
  }));
  data_15.update(18, (() => {
    val x_77 = listValMut_4;
    val x_78 = x_77.methodInfo;
    val x_79 = x_78.==("getPrice");
    if (x_79)
      positionVar_14 = 19
    else
      positionVar_14 = 21
  }));
  data_15.update(19, (() => {
    val x_80 = listValMut_4;
    this.handleNonblockingMessage(x_80);
    resetData_0 = ();
    positionVar_14 = 4
  }));
  data_15.update(20, (() => {
    positionVar_14 = 2;
    val x_81 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
    val x_82 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_81, 3);
    val x_83 = scala.collection.immutable.Nil.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_82);
    resetData_1.prepend(x_83)
  }));
  data_15.update(21, (() => {
    val x_84 = listValMut_4;
    val x_85 = x_84.methodInfo;
    val x_86 = x_85.==("getPrice");
    val x_87 = x_86.`unary_!`;
    if (x_87)
      {
        val x_88 = listValMut_4;
        val x_89 = scala.collection.immutable.List.apply[meta.runtime.RequestMessage](x_88);
        val x_90 = this.addReceiveMessages(x_89);
        resetData_0 = x_90;
        positionVar_14 = 5
      }
    else
      ()
  }));
  data_15.update(22, (() => {
    val x_91 = iterMut_5;
    val x_92 = x_91.hasNext;
    val x_93 = x_92.`unary_!`;
    if (x_93)
      positionVar_14 = 23
    else
      ()
  }));
  data_15.update(23, (() => positionVar_14 = 24));
  data_15.update(24, (() => {
    val x_94 = bindingMut_6;
    val x_95 = x_94.<(1.0);
    if (x_95)
      positionVar_14 = 14
    else
      positionVar_14 = 25
  }));
  data_15.update(25, (() => {
    val x_96 = bindingMut_6;
    val x_97 = x_96.<(1.0);
    val x_98 = x_97.`unary_!`;
    if (x_98)
      positionVar_14 = 26
    else
      ()
  }));
  data_15.update(26, (() => positionVar_14 = 1));
  data_15.update(27, (() => positionVar_14 = 28));
  data_15.update(28, (() => {
    positionVar_14 = 29;
    unblockFlag_13 = false
  }));
  data_15.update(29, (() => positionVar_14 = 28));
  data_15.update(30, (() => {
    val x_99 = resetData_0;
    val x_100 = x_99.asInstanceOf[scala.Int];
    bindingMut_12 = x_100;
    val x_101 = bindingMut_12;
    val x_102 = "Van price is ".+(x_101);
    resetData_0 = x_102;
    val x_103 = resetData_0;
    val x_104 = x_103.asInstanceOf[java.lang.String];
    bindingMut_11 = x_104;
    val x_105 = bindingMut_11;
    val x_106 = x_105.+(" should be 20");
    resetData_0 = x_106;
    val x_107 = resetData_0;
    val x_108 = x_107.asInstanceOf[java.lang.String];
    bindingMut_10 = x_108;
    val x_109 = bindingMut_10;
    scala.Predef.println(x_109);
    resetData_0 = ();
    positionVar_14 = 11;
    val x_110 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
    val x_111 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_110, 13);
    val x_112 = scala.collection.immutable.Nil.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_111);
    resetData_1.prepend(x_112)
  }));
  data_15.update(31, (() => positionVar_14 = 28));
  data_15
}).apply();
  

  override def getLoad(): Int = 
      this.load
  
  private def wrapper_getLoad(args: List[Any]): Int = {
    
          
          getLoad()
          
  }
  
  override def getPrice(): Int = 
      this.price
  
  private def wrapper_getPrice(args: List[Any]): Int = {
    
          
          getPrice()
          
  }
  
  override def run(msgs: List[meta.runtime.Message]): (List[meta.runtime.Message], Int) = {
    addReceiveMessages(msgs)
    sendMessages.clear()
    unblockFlag_13 = true
    while (unblockFlag_13 && (positionVar_14 < 32)) {
      commands_113(positionVar_14)()
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
  
    override def gotoHandleMessages(new_ir: Int = 2): meta.runtime.Actor = {
      // first entry, save the current IR to reflectionIR
      unblockFlag_13 = true

      if (reflectionIR_37 == -1){
        reflectionIR_37 = positionVar_14
        positionVar_14 = new_ir
      }

      while (positionVar_14 <= 20 && unblockFlag_13) {
        commands_113(positionVar_14)()
      }

      // reset instruction register when finishes processing
      if (positionVar_14 > 20) {
        positionVar_14 = reflectionIR_37
        reflectionIR_37 = -1
      }
      this
    }
    
override def SimClone(): Van = {
  val newAgent = new Van()
  newAgent.price = price
  newAgent.load = load
  newAgent
}

override def SimReset(): Unit = {
  positionVar_14 = 0
  val newAgent = new Van()
  price = newAgent.price
  load = newAgent.load
}

}
