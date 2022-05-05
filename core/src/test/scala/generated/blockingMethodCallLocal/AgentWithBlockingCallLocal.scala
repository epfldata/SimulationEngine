package generated.meta.test.blockingMethodCallLocal

class AgentWithBlockingCallLocal() extends meta.runtime.Actor {


  private var  reflectionIR_77: Int = -1
private var resetData_0: scala.Any = null
private val resetData_1 = scala.collection.mutable.ListBuffer.apply[scala.collection.immutable.List[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]]]()
private var resetData_2: meta.runtime.ResponseMessage = null
private var bindingMut_3: java.lang.String = null
private var bindingMut_4: scala.Long = 0L
private var bindingMut_5: scala.Double = 0.0
private var bindingMut_6: java.lang.String = null
private var bindingMut_7: scala.Long = 0L
private var bindingMut_8: scala.Any = null
private var listValMut_9: meta.runtime.RequestMessage = null
@transient private var iterMut_10: scala.collection.Iterator[meta.runtime.RequestMessage] = null
private var bindingMut_11: scala.Double = 0.0
private var unblockFlag_12: scala.Boolean = true
private var positionVar_13: scala.Int = 0
private 
  val commands_116 = (() => {
  val data_14 = new scala.Array[scala.Function0[scala.Unit]](35);
  data_14.update(0, (() => positionVar_13 = 1));
  data_14.update(1, (() => {
    positionVar_13 = 2;
    val x_15 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
    val x_16 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_15, 33);
    val x_17 = scala.collection.immutable.Nil.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_16);
    resetData_1.prepend(x_17)
  }));
  data_14.update(2, (() => {
    val x_18 = this.id;
    resetData_0 = x_18;
    val x_19 = resetData_0;
    val x_20 = x_19.asInstanceOf[scala.Long];
    bindingMut_7 = x_20;
    val x_21 = bindingMut_7;
    val x_22 = x_21.+(" processes blocking mtd!");
    resetData_0 = x_22;
    val x_23 = resetData_0;
    val x_24 = x_23.asInstanceOf[java.lang.String];
    bindingMut_6 = x_24;
    val x_25 = bindingMut_6;
    scala.Predef.println(x_25);
    resetData_0 = ();
    resetData_0 = 0.0;
    val x_26 = resetData_0;
    val x_27 = x_26.asInstanceOf[scala.Double];
    bindingMut_5 = x_27;
    positionVar_13 = 3
  }));
  data_14.update(3, (() => {
    val x_28 = bindingMut_5;
    val x_29 = x_28.+(1);
    resetData_0 = x_29;
    val x_30 = resetData_0;
    val x_31 = x_30.asInstanceOf[scala.Double];
    bindingMut_5 = x_31;
    positionVar_13 = 4;
    unblockFlag_12 = false
  }));
  data_14.update(4, (() => {
    val x_32 = bindingMut_5;
    val x_33 = x_32.<(1.0);
    if (x_33)
      positionVar_13 = 3
    else
      positionVar_13 = 5
  }));
  data_14.update(5, (() => {
    val x_34 = bindingMut_5;
    val x_35 = x_34.<(1.0);
    val x_36 = x_35.`unary_!`;
    if (x_36)
      {
        val x_37 = this.id;
        resetData_0 = x_37;
        val x_38 = resetData_0;
        val x_39 = x_38.asInstanceOf[scala.Long];
        bindingMut_4 = x_39;
        val x_40 = bindingMut_4;
        val x_41 = x_40.+(" finishes processing!");
        resetData_0 = x_41;
        val x_42 = resetData_0;
        val x_43 = x_42.asInstanceOf[java.lang.String];
        bindingMut_3 = x_43;
        val x_44 = bindingMut_3;
        scala.Predef.println(x_44);
        resetData_0 = ();
        resetData_0 = true;
        val x_45 = resetData_1.remove(0);
        val x_49 = x_45.find(((x_46: scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]) => {
          val x_47 = x_46._1;
          val x_48 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
          x_47.==(x_48)
        }));
        val x_50 = x_49.get;
        val x_51 = x_50._2;
        positionVar_13 = x_51
      }
    else
      ()
  }));
  data_14.update(6, (() => {
    val x_52 = resetData_0;
    val x_53 = x_52.asInstanceOf[scala.Any];
    bindingMut_8 = x_53;
    val x_54 = bindingMut_8;
    val x_55 = listValMut_9;
    x_55.reply(this, x_54);
    resetData_0 = ();
    positionVar_13 = 7
  }));
  data_14.update(7, (() => positionVar_13 = 8));
  data_14.update(8, (() => positionVar_13 = 9));
  data_14.update(9, (() => {
    val x_56 = iterMut_10;
    val x_57 = x_56.hasNext;
    if (x_57)
      {
        val x_58 = iterMut_10;
        val x_59 = x_58.next();
        listValMut_9 = x_59;
        positionVar_13 = 10
      }
    else
      positionVar_13 = 25
  }));
  data_14.update(10, (() => {
    val x_60 = listValMut_9;
    val x_61 = x_60.methodInfo;
    val x_62 = x_61.==("blockingMtd");
    if (x_62)
      positionVar_13 = 11
    else
      positionVar_13 = 13
  }));
  data_14.update(11, (() => positionVar_13 = 12));
  data_14.update(12, (() => {
    positionVar_13 = 2;
    val x_63 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
    val x_64 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_63, 6);
    val x_65 = scala.collection.immutable.Nil.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_64);
    resetData_1.prepend(x_65)
  }));
  data_14.update(13, (() => {
    val x_66 = listValMut_9;
    val x_67 = x_66.methodInfo;
    val x_68 = x_67.==("blockingMtd");
    val x_69 = x_68.`unary_!`;
    if (x_69)
      positionVar_13 = 14
    else
      ()
  }));
  data_14.update(14, (() => {
    val x_70 = listValMut_9;
    val x_71 = x_70.methodInfo;
    val x_72 = x_71.==("nonBlockingMtd");
    if (x_72)
      positionVar_13 = 15
    else
      positionVar_13 = 24
  }));
  data_14.update(15, (() => {
    val x_73 = listValMut_9;
    this.handleNonblockingMessage(x_73);
    resetData_0 = ();
    positionVar_13 = 16
  }));
  data_14.update(16, (() => positionVar_13 = 8));
  data_14.update(17, (() => {
    positionVar_13 = 18;
    val x_74 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
    val x_75 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_74, 19);
    val x_76 = scala.collection.immutable.Nil.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_75);
    resetData_1.prepend(x_76)
  }));
  data_14.update(18, (() => {
    val x_77 = this.id;
    val x_78 = x_77.+(" processes a nonblocking mtd!");
    scala.Predef.println(x_78);
    resetData_0 = false;
    val x_79 = resetData_1.remove(0);
    val x_83 = x_79.find(((x_80: scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]) => {
      val x_81 = x_80._1;
      val x_82 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
      x_81.==(x_82)
    }));
    val x_84 = x_83.get;
    val x_85 = x_84._2;
    positionVar_13 = x_85
  }));
  data_14.update(19, (() => {
    val x_86 = resetData_0;
    val x_87 = x_86.asInstanceOf[scala.Any];
    bindingMut_8 = x_87;
    val x_88 = bindingMut_8;
    val x_89 = listValMut_9;
    x_89.reply(this, x_88);
    resetData_0 = ();
    positionVar_13 = 16
  }));
  data_14.update(20, (() => {
    resetData_0 = 0.0;
    val x_90 = resetData_0;
    val x_91 = x_90.asInstanceOf[scala.Double];
    bindingMut_11 = x_91;
    positionVar_13 = 21
  }));
  data_14.update(21, (() => {
    val x_92 = bindingMut_11;
    val x_93 = x_92.+(1);
    resetData_0 = x_93;
    val x_94 = resetData_0;
    val x_95 = x_94.asInstanceOf[scala.Double];
    bindingMut_11 = x_95;
    positionVar_13 = 22;
    unblockFlag_12 = false
  }));
  data_14.update(22, (() => positionVar_13 = 23));
  data_14.update(23, (() => {
    val x_96 = this.popRequestMessages;
    val x_97 = x_96.iterator;
    iterMut_10 = x_97;
    positionVar_13 = 9
  }));
  data_14.update(24, (() => {
    val x_98 = listValMut_9;
    val x_99 = x_98.methodInfo;
    val x_100 = x_99.==("nonBlockingMtd");
    val x_101 = x_100.`unary_!`;
    if (x_101)
      {
        val x_102 = listValMut_9;
        val x_103 = scala.collection.immutable.List.apply[meta.runtime.RequestMessage](x_102);
        val x_104 = this.addReceiveMessages(x_103);
        resetData_0 = x_104;
        positionVar_13 = 8
      }
    else
      ()
  }));
  data_14.update(25, (() => {
    val x_105 = iterMut_10;
    val x_106 = x_105.hasNext;
    val x_107 = x_106.`unary_!`;
    if (x_107)
      positionVar_13 = 26
    else
      ()
  }));
  data_14.update(26, (() => positionVar_13 = 27));
  data_14.update(27, (() => {
    val x_108 = bindingMut_11;
    val x_109 = x_108.<(1.0);
    if (x_109)
      positionVar_13 = 21
    else
      positionVar_13 = 28
  }));
  data_14.update(28, (() => {
    val x_110 = bindingMut_11;
    val x_111 = x_110.<(1.0);
    val x_112 = x_111.`unary_!`;
    if (x_112)
      positionVar_13 = 29
    else
      ()
  }));
  data_14.update(29, (() => positionVar_13 = 1));
  data_14.update(30, (() => positionVar_13 = 31));
  data_14.update(31, (() => {
    positionVar_13 = 32;
    unblockFlag_12 = false
  }));
  data_14.update(32, (() => positionVar_13 = 31));
  data_14.update(33, (() => {
    positionVar_13 = 18;
    val x_113 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
    val x_114 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_113, 20);
    val x_115 = scala.collection.immutable.Nil.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_114);
    resetData_1.prepend(x_115)
  }));
  data_14.update(34, (() => positionVar_13 = 31));
  data_14
}).apply();
  

   def nonBlockingMtd(): Boolean = 
      {
  val x_0 = this.id;
  val x_1 = x_0.+(" processes a nonblocking mtd!");
  scala.Predef.println(x_1);
  false
}
  
  private def wrapper_nonBlockingMtd(args: List[Any]): Boolean = {
    
          
          nonBlockingMtd()
          
  }
  
  override def run(msgs: List[meta.runtime.Message]): (List[meta.runtime.Message], Int) = {
    addReceiveMessages(msgs)
    sendMessages.clear()
    unblockFlag_12 = true
    while (unblockFlag_12 && (positionVar_13 < 35)) {
      commands_116(positionVar_13)()
    }
    (sendMessages.toList, 1)
  }

  override def handleNonblockingMessage(m: meta.runtime.RequestMessage): Unit = {
    val args = m.argss.flatten
    val response = m.methodInfo match {
      case "nonBlockingMtd" => wrapper_nonBlockingMtd(args)
    }
    m.reply(this, response)
  }
  
    override def gotoHandleMessages(new_ir: Int = 5): meta.runtime.Actor = {
      // first entry, save the current IR to reflectionIR
      unblockFlag_12 = true

      if (reflectionIR_77 == -1){
        reflectionIR_77 = positionVar_13
        positionVar_13 = new_ir
      }

      while (positionVar_13 <= 24 && unblockFlag_12) {
        commands_116(positionVar_13)()
      }

      // reset instruction register when finishes processing
      if (positionVar_13 > 24) {
        positionVar_13 = reflectionIR_77
        reflectionIR_77 = -1
      }
      this
    }
    
override def SimClone(): AgentWithBlockingCallLocal = {
  val newAgent = new AgentWithBlockingCallLocal()

  newAgent
}

override def SimReset(): Unit = {
  positionVar_13 = 0
  
}

}
