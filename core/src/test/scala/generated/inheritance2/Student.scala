package generated.meta.test.inheritance2

class Student(var neighbor: generated.meta.test.inheritance2.Teacher) extends meta.runtime.Actor with meta.test.Person {


  private var  reflectionIR_73: Int = -1
  var resetData_0: scala.Any = null
  val resetData_1 = scala.collection.mutable.ListBuffer.apply[scala.collection.immutable.List[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]]]()
  var resetData_2: meta.runtime.ResponseMessage = null
  var bindingMut_3: scala.Any = null
  var listValMut_4: meta.runtime.RequestMessage = null
  @transient var iterMut_5: scala.collection.Iterator[meta.runtime.RequestMessage] = null
  var bindingMut_6: scala.Double = 0.0
  var unblockFlag_7: scala.Boolean = true
  var positionVar_8: scala.Int = 0
  
  val commands_76 = (() => {
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
    val x_30 = scala.`package`.Right.apply[scala.Nothing, scala.Int](4);
    val x_31 = x_29.==(x_30);
    if (x_31)
      positionVar_8 = 8
    else
      positionVar_8 = 10
  }));
  data_9.update(8, (() => {
    val x_32 = listValMut_4;
    this.handleNonblockingMessage(x_32);
    resetData_0 = ();
    positionVar_8 = 4
  }));
  data_9.update(9, (() => {
    positionVar_8 = 2;
    val x_33 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
    val x_34 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_33, 3);
    val x_35 = scala.collection.immutable.Nil.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_34);
    resetData_1.prepend(x_35)
  }));
  data_9.update(10, (() => {
    val x_36 = listValMut_4;
    val x_37 = x_36.methodInfo;
    val x_38 = scala.`package`.Right.apply[scala.Nothing, scala.Int](4);
    val x_39 = x_37.==(x_38);
    val x_40 = x_39.`unary_!`;
    if (x_40)
      {
        val x_41 = listValMut_4;
        val x_42 = scala.collection.immutable.List.apply[meta.runtime.RequestMessage](x_41);
        val x_43 = this.addReceiveMessages(x_42);
        resetData_0 = x_43;
        positionVar_8 = 5
      }
    else
      ()
  }));
  data_9.update(11, (() => {
    val x_44 = iterMut_5;
    val x_45 = x_44.hasNext;
    val x_46 = x_45.`unary_!`;
    if (x_46)
      positionVar_8 = 12
    else
      ()
  }));
  data_9.update(12, (() => positionVar_8 = 13));
  data_9.update(13, (() => {
    val x_47 = bindingMut_6;
    val x_48 = x_47.<(1.0);
    if (x_48)
      positionVar_8 = 14
    else
      positionVar_8 = 17
  }));
  data_9.update(14, (() => {
    val x_49 = bindingMut_6;
    val x_50 = x_49.+(1);
    resetData_0 = x_50;
    val x_51 = resetData_0;
    val x_52 = x_51.asInstanceOf[scala.Double];
    bindingMut_6 = x_52;
    positionVar_8 = 15;
    unblockFlag_7 = false
  }));
  data_9.update(15, (() => positionVar_8 = 16));
  data_9.update(16, (() => {
    val x_53 = this.popRequestMessages;
    val x_54 = x_53.iterator;
    iterMut_5 = x_54;
    positionVar_8 = 6
  }));
  data_9.update(17, (() => {
    val x_55 = bindingMut_6;
    val x_56 = x_55.<(1.0);
    val x_57 = x_56.`unary_!`;
    if (x_57)
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
    val receiver_58 = this.neighbor;
    val x_59 = ((this): meta.runtime.Actor).id;
    val x_60 = receiver_58.id;
    val x_61 = scala.`package`.Right.apply[scala.Nothing, scala.Int](0);
    val x_62 = scala.collection.immutable.Nil.::[scala.collection.immutable.List[scala.Any]](((scala.collection.immutable.Nil): scala.collection.immutable.List[scala.Any]));
    val x_63 = meta.runtime.RequestMessage.apply(x_59, x_60, false, x_61, x_62);
    val x_64 = x_63.sessionId;
    val x_65 = meta.runtime.Future.apply$default$2[scala.Unit];
    val x_66 = meta.runtime.Future.apply[scala.Unit](x_64, x_65);
    var v_67: meta.runtime.Future[scala.Unit] = x_66;
    ((this): meta.runtime.Actor).sendMessage(x_63);
    val x_68 = x_63.sessionId;
    ((this): meta.runtime.Actor).setMessageResponseHandler(x_68, ((response_69: meta.runtime.Message) => {
      val x_70 = v_67;
      val x_71 = response_69.asInstanceOf[meta.runtime.ResponseMessage];
      val x_72 = x_71.arg;
      x_72.asInstanceOf[scala.Unit];
      x_70.setValue(())
    }));
    val x_73 = v_67;
    resetData_0 = x_73;
    resetData_0 = 0.0;
    val x_74 = resetData_0;
    val x_75 = x_74.asInstanceOf[scala.Double];
    bindingMut_6 = x_75;
    positionVar_8 = 14
  }));
  data_9.update(23, (() => positionVar_8 = 20));
  data_9
}).apply();
  

  override def work(): Unit = 
      scala.Predef.println("Study at school")
  
  def wrapper_work(args: List[Any]): Unit = {
    
          
          work()
          
  }
  
  override def run(msgs: List[meta.runtime.Message]): (List[meta.runtime.Message], Int) = {
    addReceiveMessages(msgs)
    sendMessages.clear()
    unblockFlag_7 = true
    while (unblockFlag_7 && (positionVar_8 < 24)) {
      commands_76(positionVar_8)()
    }
    (sendMessages.toList, 1)
  }

  override def handleNonblockingMessage(m: meta.runtime.RequestMessage): Unit = {
    val args = m.argss.flatten
    val response = m.methodInfo match {
      case Right(x) => {
        x match {
          case 4 => wrapper_work(args)
        }
      }
      case Left(x) => println("For staged implementation only")
    }
    m.reply(this, response)
  }
  
    override def gotoHandleMessages(new_ir: Int = 2): meta.runtime.Actor = {
      // first entry, save the current IR to reflectionIR
      unblockFlag_7 = true

      if (reflectionIR_73 == -1){
        reflectionIR_73 = positionVar_8
        positionVar_8 = new_ir
      }

      while (positionVar_8 <= 17 && unblockFlag_7) {
        commands_76(positionVar_8)()
      }

      // reset instruction register when finishes processing
      if (positionVar_8 > 17) {
        positionVar_8 = reflectionIR_73
        reflectionIR_73 = -1
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
