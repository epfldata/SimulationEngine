package generated.meta.test.inheritance

class Worker() extends meta.runtime.Actor with meta.test.Person {


  private var  reflectionIR_43: Int = -1
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
  val commands_73 = (() => {
  val data_9 = new scala.Array[scala.Function0[scala.Unit]](30);
  data_9.update(0, (() => positionVar_8 = 1));
  data_9.update(1, (() => {
    positionVar_8 = 2;
    val x_10 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
    val x_11 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_10, 28);
    val x_12 = scala.collection.immutable.Nil.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_11);
    resetData_1.prepend(x_12)
  }));
  data_9.update(2, (() => {
    scala.Predef.println("Work in a factory.");
    resetData_0 = ();
    val x_13 = resetData_1.remove(0);
    val x_17 = x_13.find(((x_14: scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]) => {
      val x_15 = x_14._1;
      val x_16 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
      x_15.==(x_16)
    }));
    val x_18 = x_17.get;
    val x_19 = x_18._2;
    positionVar_8 = x_19
  }));
  data_9.update(3, (() => {
    val x_20 = resetData_0;
    val x_21 = x_20.asInstanceOf[scala.Any];
    bindingMut_3 = x_21;
    val x_22 = bindingMut_3;
    val x_23 = listValMut_4;
    x_23.reply(this, x_22);
    resetData_0 = ();
    positionVar_8 = 4
  }));
  data_9.update(4, (() => positionVar_8 = 5));
  data_9.update(5, (() => positionVar_8 = 6));
  data_9.update(6, (() => {
    val x_24 = iterMut_5;
    val x_25 = x_24.hasNext;
    if (x_25)
      {
        val x_26 = iterMut_5;
        val x_27 = x_26.next();
        listValMut_4 = x_27;
        positionVar_8 = 7
      }
    else
      positionVar_8 = 16
  }));
  data_9.update(7, (() => {
    val x_28 = listValMut_4;
    val x_29 = x_28.methodInfo;
    val x_30 = x_29.==("work");
    if (x_30)
      positionVar_8 = 8
    else
      positionVar_8 = 11
  }));
  data_9.update(8, (() => {
    val x_31 = listValMut_4;
    this.handleNonblockingMessage(x_31);
    resetData_0 = ();
    positionVar_8 = 9
  }));
  data_9.update(9, (() => positionVar_8 = 5));
  data_9.update(10, (() => {
    positionVar_8 = 2;
    val x_32 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
    val x_33 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_32, 27);
    val x_34 = scala.collection.immutable.Nil.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_33);
    resetData_1.prepend(x_34)
  }));
  data_9.update(11, (() => {
    val x_35 = listValMut_4;
    val x_36 = x_35.methodInfo;
    val x_37 = x_36.==("work");
    val x_38 = x_37.`unary_!`;
    if (x_38)
      positionVar_8 = 12
    else
      ()
  }));
  data_9.update(12, (() => {
    val x_39 = listValMut_4;
    val x_40 = x_39.methodInfo;
    val x_41 = x_40.==("work");
    if (x_41)
      positionVar_8 = 13
    else
      positionVar_8 = 15
  }));
  data_9.update(13, (() => {
    val x_42 = listValMut_4;
    this.handleNonblockingMessage(x_42);
    resetData_0 = ();
    positionVar_8 = 4
  }));
  data_9.update(14, (() => {
    positionVar_8 = 2;
    val x_43 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
    val x_44 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_43, 3);
    val x_45 = scala.collection.immutable.Nil.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_44);
    resetData_1.prepend(x_45)
  }));
  data_9.update(15, (() => {
    val x_46 = listValMut_4;
    val x_47 = x_46.methodInfo;
    val x_48 = x_47.==("work");
    val x_49 = x_48.`unary_!`;
    if (x_49)
      {
        val x_50 = listValMut_4;
        val x_51 = scala.collection.immutable.List.apply[meta.runtime.RequestMessage](x_50);
        val x_52 = this.addReceiveMessages(x_51);
        resetData_0 = x_52;
        positionVar_8 = 5
      }
    else
      ()
  }));
  data_9.update(16, (() => {
    val x_53 = iterMut_5;
    val x_54 = x_53.hasNext;
    val x_55 = x_54.`unary_!`;
    if (x_55)
      positionVar_8 = 17
    else
      ()
  }));
  data_9.update(17, (() => positionVar_8 = 18));
  data_9.update(18, (() => {
    val x_56 = bindingMut_6;
    val x_57 = x_56.<(1.0);
    if (x_57)
      positionVar_8 = 19
    else
      positionVar_8 = 22
  }));
  data_9.update(19, (() => {
    val x_58 = bindingMut_6;
    val x_59 = x_58.+(1);
    resetData_0 = x_59;
    val x_60 = resetData_0;
    val x_61 = x_60.asInstanceOf[scala.Double];
    bindingMut_6 = x_61;
    positionVar_8 = 20;
    unblockFlag_7 = false
  }));
  data_9.update(20, (() => positionVar_8 = 21));
  data_9.update(21, (() => {
    val x_62 = this.popRequestMessages;
    val x_63 = x_62.iterator;
    iterMut_5 = x_63;
    positionVar_8 = 6
  }));
  data_9.update(22, (() => {
    val x_64 = bindingMut_6;
    val x_65 = x_64.<(1.0);
    val x_66 = x_65.`unary_!`;
    if (x_66)
      positionVar_8 = 23
    else
      ()
  }));
  data_9.update(23, (() => positionVar_8 = 1));
  data_9.update(24, (() => positionVar_8 = 25));
  data_9.update(25, (() => {
    positionVar_8 = 26;
    unblockFlag_7 = false
  }));
  data_9.update(26, (() => positionVar_8 = 25));
  data_9.update(27, (() => {
    val x_67 = resetData_0;
    val x_68 = x_67.asInstanceOf[scala.Any];
    bindingMut_3 = x_68;
    val x_69 = bindingMut_3;
    val x_70 = listValMut_4;
    x_70.reply(this, x_69);
    resetData_0 = ();
    positionVar_8 = 9
  }));
  data_9.update(28, (() => {
    resetData_0 = 0.0;
    val x_71 = resetData_0;
    val x_72 = x_71.asInstanceOf[scala.Double];
    bindingMut_6 = x_72;
    positionVar_8 = 19
  }));
  data_9.update(29, (() => positionVar_8 = 25));
  data_9
}).apply();
  

  override def work(): Unit = 
      scala.Predef.println("Work in a factory.")
  
  private def wrapper_work(args: List[Any]): Unit = {
    
          
          work()
          
  }
  
  override def run(msgs: List[meta.runtime.Message]): (List[meta.runtime.Message], Int) = {
    addReceiveMessages(msgs)
    sendMessages.clear()
    unblockFlag_7 = true
    while (unblockFlag_7 && (positionVar_8 < 30)) {
      commands_73(positionVar_8)()
    }
    (sendMessages.toList, 1)
  }

  override def handleNonblockingMessage(m: meta.runtime.RequestMessage): Unit = {
    val args = m.argss.flatten
    val response = m.methodInfo match {
      case "work" => wrapper_work(args)
    }
    m.reply(this, response)
  }
  
    override def gotoHandleMessages(new_ir: Int = 2): meta.runtime.Actor = {
      // first entry, save the current IR to reflectionIR
      unblockFlag_7 = true

      if (reflectionIR_43 == -1){
        reflectionIR_43 = positionVar_8
        positionVar_8 = new_ir
      }

      while (positionVar_8 <= 22 && unblockFlag_7) {
        commands_73(positionVar_8)()
      }

      // reset instruction register when finishes processing
      if (positionVar_8 > 22) {
        positionVar_8 = reflectionIR_43
        reflectionIR_43 = -1
      }
      this
    }
    
override def SimClone(): Worker = {
  val newAgent = new Worker()

  newAgent
}

override def SimReset(): Unit = {
  positionVar_8 = 0
  
}

}
