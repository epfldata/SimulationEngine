package generated.meta.test.inheritance2

class Student(var neighbor: generated.meta.test.inheritance2.Teacher) extends meta.runtime.Actor with meta.test.Person {


  private var  reflectionIR_63: Int = -1
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
  val data_9 = new scala.Array[scala.Function0[scala.Unit]](24);
  data_9.update(0, (() => positionVar_8 = 1));
  data_9.update(1, (() => {
    positionVar_8 = 2;
    val x_10 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
    val x_11 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_10, 22);
    val x_12 = scala.collection.immutable.Nil.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_11);
    resetData_1.prepend(x_12)
  }));
  data_9.update(2, (() => {
    scala.Predef.println("Study at school");
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
      positionVar_8 = 11
  }));
  data_9.update(7, (() => {
    val x_28 = listValMut_4;
    val x_29 = x_28.methodInfo;
    val x_30 = x_29.==("work");
    if (x_30)
      positionVar_8 = 8
    else
      positionVar_8 = 10
  }));
  data_9.update(8, (() => {
    val x_31 = listValMut_4;
    this.handleNonblockingMessage(x_31);
    resetData_0 = ();
    positionVar_8 = 4
  }));
  data_9.update(9, (() => {
    positionVar_8 = 2;
    val x_32 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
    val x_33 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_32, 3);
    val x_34 = scala.collection.immutable.Nil.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_33);
    resetData_1.prepend(x_34)
  }));
  data_9.update(10, (() => {
    val x_35 = listValMut_4;
    val x_36 = x_35.methodInfo;
    val x_37 = x_36.==("work");
    val x_38 = x_37.`unary_!`;
    if (x_38)
      {
        val x_39 = listValMut_4;
        val x_40 = scala.collection.immutable.List.apply[meta.runtime.RequestMessage](x_39);
        val x_41 = this.addReceiveMessages(x_40);
        resetData_0 = x_41;
        positionVar_8 = 5
      }
    else
      ()
  }));
  data_9.update(11, (() => {
    val x_42 = iterMut_5;
    val x_43 = x_42.hasNext;
    val x_44 = x_43.`unary_!`;
    if (x_44)
      positionVar_8 = 12
    else
      ()
  }));
  data_9.update(12, (() => positionVar_8 = 13));
  data_9.update(13, (() => {
    val x_45 = bindingMut_6;
    val x_46 = x_45.<(1.0);
    if (x_46)
      positionVar_8 = 14
    else
      positionVar_8 = 17
  }));
  data_9.update(14, (() => {
    val x_47 = bindingMut_6;
    val x_48 = x_47.+(1);
    resetData_0 = x_48;
    val x_49 = resetData_0;
    val x_50 = x_49.asInstanceOf[scala.Double];
    bindingMut_6 = x_50;
    positionVar_8 = 15;
    unblockFlag_7 = false
  }));
  data_9.update(15, (() => positionVar_8 = 16));
  data_9.update(16, (() => {
    val x_51 = this.popRequestMessages;
    val x_52 = x_51.iterator;
    iterMut_5 = x_52;
    positionVar_8 = 6
  }));
  data_9.update(17, (() => {
    val x_53 = bindingMut_6;
    val x_54 = x_53.<(1.0);
    val x_55 = x_54.`unary_!`;
    if (x_55)
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
  data_9.update(22, (() => {
    val receiver_56 = this.neighbor;
    val x_57 = ((this): meta.runtime.Actor).id;
    val x_58 = receiver_56.id;
    val x_59 = scala.collection.immutable.Nil.::[scala.collection.immutable.List[scala.Any]](((scala.collection.immutable.Nil): scala.collection.immutable.List[scala.Any]));
    val x_60 = meta.runtime.RequestMessage.apply(x_57, x_58, false, "work", x_59);
    val x_61 = x_60.sessionId;
    val x_62 = meta.runtime.Future.apply$default$2[scala.Unit];
    val x_63 = meta.runtime.Future.apply[scala.Unit](x_61, x_62);
    var v_64: meta.runtime.Future[scala.Unit] = x_63;
    ((this): meta.runtime.Actor).sendMessage(x_60);
    val x_65 = x_60.sessionId;
    ((this): meta.runtime.Actor).setMessageResponseHandler(x_65, ((response_66: meta.runtime.Message) => {
      val x_67 = v_64;
      val x_68 = response_66.asInstanceOf[meta.runtime.ResponseMessage];
      val x_69 = x_68.arg;
      x_69.asInstanceOf[scala.Unit];
      x_67.setValue(())
    }));
    val x_70 = v_64;
    resetData_0 = x_70;
    resetData_0 = 0.0;
    val x_71 = resetData_0;
    val x_72 = x_71.asInstanceOf[scala.Double];
    bindingMut_6 = x_72;
    positionVar_8 = 14
  }));
  data_9.update(23, (() => positionVar_8 = 20));
  data_9
}).apply();
  

  override def work(): Unit = 
      scala.Predef.println("Study at school")
  
  private def wrapper_work(args: List[Any]): Unit = {
    
          
          work()
          
  }
  
  override def run(msgs: List[meta.runtime.Message]): (List[meta.runtime.Message], Int) = {
    addReceiveMessages(msgs)
    sendMessages.clear()
    unblockFlag_7 = true
    while (unblockFlag_7 && (positionVar_8 < 24)) {
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

      if (reflectionIR_63 == -1){
        reflectionIR_63 = positionVar_8
        positionVar_8 = new_ir
      }

      while (positionVar_8 <= 17 && unblockFlag_7) {
        commands_73(positionVar_8)()
      }

      // reset instruction register when finishes processing
      if (positionVar_8 > 17) {
        positionVar_8 = reflectionIR_63
        reflectionIR_63 = -1
      }
      this
    }
    
override def SimClone(): Student = {
  val newAgent = new Student(neighbor)

  newAgent
}

override def SimReset(): Unit = {
  positionVar_8 = 0
  
}

}
