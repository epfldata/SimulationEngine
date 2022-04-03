package generated.meta.test.blockingMethodCallLocal

class AgentWithBlockingCallLocal() extends meta.runtime.Actor {


  private var  reflectionIR_14: Int = -1
  var resetData_0: scala.Any = null
  val resetData_1 = scala.collection.mutable.ListBuffer.apply[scala.collection.immutable.List[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]]]()
  var resetData_2: meta.runtime.ResponseMessage = null
  var bindingMut_3: java.lang.String = null
  var bindingMut_4: scala.Long = 0L
  var bindingMut_5: scala.Double = 0.0
  var bindingMut_6: java.lang.String = null
  var bindingMut_7: scala.Long = 0L
  var bindingMut_8: scala.Any = null
  var listValMut_9: meta.runtime.RequestMessage = null
  @transient var iterMut_10: scala.collection.Iterator[meta.runtime.RequestMessage] = null
  var bindingMut_11: scala.Double = 0.0
  var unblockFlag_12: scala.Boolean = true
  var positionVar_13: scala.Int = 0
  
  val commands_120 = (() => {
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
    val x_62 = scala.`package`.Right.apply[scala.Nothing, scala.Int](5);
    val x_63 = x_61.==(x_62);
    if (x_63)
      positionVar_13 = 11
    else
      positionVar_13 = 20
  }));
  data_14.update(11, (() => {
    val x_64 = listValMut_9;
    this.handleNonblockingMessage(x_64);
    resetData_0 = ();
    positionVar_13 = 12
  }));
  data_14.update(12, (() => positionVar_13 = 8));
  data_14.update(13, (() => {
    positionVar_13 = 14;
    val x_65 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
    val x_66 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_65, 15);
    val x_67 = scala.collection.immutable.Nil.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_66);
    resetData_1.prepend(x_67)
  }));
  data_14.update(14, (() => {
    val x_68 = this.id;
    val x_69 = x_68.+(" processes a nonblocking mtd!");
    scala.Predef.println(x_69);
    resetData_0 = false;
    val x_70 = resetData_1.remove(0);
    val x_74 = x_70.find(((x_71: scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]) => {
      val x_72 = x_71._1;
      val x_73 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
      x_72.==(x_73)
    }));
    val x_75 = x_74.get;
    val x_76 = x_75._2;
    positionVar_13 = x_76
  }));
  data_14.update(15, (() => {
    val x_77 = resetData_0;
    val x_78 = x_77.asInstanceOf[scala.Any];
    bindingMut_8 = x_78;
    val x_79 = bindingMut_8;
    val x_80 = listValMut_9;
    x_80.reply(this, x_79);
    resetData_0 = ();
    positionVar_13 = 12
  }));
  data_14.update(16, (() => {
    resetData_0 = 0.0;
    val x_81 = resetData_0;
    val x_82 = x_81.asInstanceOf[scala.Double];
    bindingMut_11 = x_82;
    positionVar_13 = 17
  }));
  data_14.update(17, (() => {
    val x_83 = bindingMut_11;
    val x_84 = x_83.+(1);
    resetData_0 = x_84;
    val x_85 = resetData_0;
    val x_86 = x_85.asInstanceOf[scala.Double];
    bindingMut_11 = x_86;
    positionVar_13 = 18;
    unblockFlag_12 = false
  }));
  data_14.update(18, (() => positionVar_13 = 19));
  data_14.update(19, (() => {
    val x_87 = this.popRequestMessages;
    val x_88 = x_87.iterator;
    iterMut_10 = x_88;
    positionVar_13 = 9
  }));
  data_14.update(20, (() => {
    val x_89 = listValMut_9;
    val x_90 = x_89.methodInfo;
    val x_91 = scala.`package`.Right.apply[scala.Nothing, scala.Int](5);
    val x_92 = x_90.==(x_91);
    val x_93 = x_92.`unary_!`;
    if (x_93)
      positionVar_13 = 21
    else
      ()
  }));
  data_14.update(21, (() => {
    val x_94 = listValMut_9;
    val x_95 = x_94.methodInfo;
    val x_96 = scala.`package`.Right.apply[scala.Nothing, scala.Int](4);
    val x_97 = x_95.==(x_96);
    if (x_97)
      positionVar_13 = 22
    else
      positionVar_13 = 24
  }));
  data_14.update(22, (() => positionVar_13 = 23));
  data_14.update(23, (() => {
    positionVar_13 = 2;
    val x_98 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
    val x_99 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_98, 6);
    val x_100 = scala.collection.immutable.Nil.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_99);
    resetData_1.prepend(x_100)
  }));
  data_14.update(24, (() => {
    val x_101 = listValMut_9;
    val x_102 = x_101.methodInfo;
    val x_103 = scala.`package`.Right.apply[scala.Nothing, scala.Int](4);
    val x_104 = x_102.==(x_103);
    val x_105 = x_104.`unary_!`;
    if (x_105)
      {
        val x_106 = listValMut_9;
        val x_107 = scala.collection.immutable.List.apply[meta.runtime.RequestMessage](x_106);
        val x_108 = this.addReceiveMessages(x_107);
        resetData_0 = x_108;
        positionVar_13 = 8
      }
    else
      ()
  }));
  data_14.update(25, (() => {
    val x_109 = iterMut_10;
    val x_110 = x_109.hasNext;
    val x_111 = x_110.`unary_!`;
    if (x_111)
      positionVar_13 = 26
    else
      ()
  }));
  data_14.update(26, (() => positionVar_13 = 27));
  data_14.update(27, (() => {
    val x_112 = bindingMut_11;
    val x_113 = x_112.<(1.0);
    if (x_113)
      positionVar_13 = 17
    else
      positionVar_13 = 28
  }));
  data_14.update(28, (() => {
    val x_114 = bindingMut_11;
    val x_115 = x_114.<(1.0);
    val x_116 = x_115.`unary_!`;
    if (x_116)
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
    positionVar_13 = 14;
    val x_117 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
    val x_118 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_117, 16);
    val x_119 = scala.collection.immutable.Nil.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_118);
    resetData_1.prepend(x_119)
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
  
  def wrapper_nonBlockingMtd(args: List[Any]): Boolean = {
    
          
          nonBlockingMtd()
          
  }
  
  override def run(msgs: List[meta.runtime.Message]): (List[meta.runtime.Message], Int) = {
    addReceiveMessages(msgs)
    sendMessages.clear()
    unblockFlag_12 = true
    while (unblockFlag_12 && (positionVar_13 < 35)) {
      commands_120(positionVar_13)()
    }
    (sendMessages.toList, 1)
  }

  override def handleNonblockingMessage(m: meta.runtime.RequestMessage): Unit = {
    val args = m.argss.flatten
    val response = m.methodInfo match {
      case Right(x) => {
        x match {
          case 5 => wrapper_nonBlockingMtd(args)
        }
      }
      case Left(x) => println("For staged implementation only")
    }
    m.reply(this, response)
  }
  
    override def gotoHandleMessages(new_ir: Int = 5): meta.runtime.Actor = {
      // first entry, save the current IR to reflectionIR
      unblockFlag_12 = true

      if (reflectionIR_14 == -1){
        reflectionIR_14 = positionVar_13
        positionVar_13 = new_ir
      }

      while (positionVar_13 <= 23 && unblockFlag_12) {
        commands_120(positionVar_13)()
      }

      // reset instruction register when finishes processing
      if (positionVar_13 > 23) {
        positionVar_13 = reflectionIR_14
        reflectionIR_14 = -1
      }
      this
    }
    
override def SimClone(): AgentWithBlockingCallLocal = {
  val newAgent = new AgentWithBlockingCallLocal()
  
  newAgent
}

}
