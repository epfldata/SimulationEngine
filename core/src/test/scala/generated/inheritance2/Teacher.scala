package generated.meta.test.inheritance2

class Teacher() extends meta.runtime.Actor with meta.test.Person {


  private var  reflectionIR_26: Int = -1
  var resetData_0: scala.Any = null
  val resetData_1 = scala.collection.mutable.ListBuffer.apply[scala.collection.immutable.List[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]]]()
  var resetData_2: meta.runtime.ResponseMessage = null
  var bindingMut_3: scala.Double = 0.0
  var bindingMut_4: scala.Any = null
  var listValMut_5: meta.runtime.RequestMessage = null
  @transient var iterMut_6: scala.collection.Iterator[meta.runtime.RequestMessage] = null
  var bindingMut_7: scala.Double = 0.0
  var unblockFlag_8: scala.Boolean = true
  var positionVar_9: scala.Int = 0
  
  val commands_71 = (() => {
  val data_10 = new scala.Array[scala.Function0[scala.Unit]](27);
  data_10.update(0, (() => positionVar_9 = 1));
  data_10.update(1, (() => {
    positionVar_9 = 2;
    val x_11 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
    val x_12 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_11, 25);
    val x_13 = scala.collection.immutable.Nil.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_12);
    resetData_1.prepend(x_13)
  }));
  data_10.update(2, (() => {
    scala.Predef.println("Teach in a classroom. ");
    resetData_0 = ();
    resetData_0 = 0.0;
    val x_14 = resetData_0;
    val x_15 = x_14.asInstanceOf[scala.Double];
    bindingMut_3 = x_15;
    positionVar_9 = 3
  }));
  data_10.update(3, (() => {
    val x_16 = bindingMut_3;
    val x_17 = x_16.+(1);
    resetData_0 = x_17;
    val x_18 = resetData_0;
    val x_19 = x_18.asInstanceOf[scala.Double];
    bindingMut_3 = x_19;
    positionVar_9 = 4;
    unblockFlag_8 = false
  }));
  data_10.update(4, (() => {
    val x_20 = bindingMut_3;
    val x_21 = x_20.<(1.0);
    if (x_21)
      positionVar_9 = 3
    else
      positionVar_9 = 5
  }));
  data_10.update(5, (() => {
    val x_22 = bindingMut_3;
    val x_23 = x_22.<(1.0);
    val x_24 = x_23.`unary_!`;
    if (x_24)
      {
        val x_25 = resetData_1.remove(0);
        val x_29 = x_25.find(((x_26: scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]) => {
          val x_27 = x_26._1;
          val x_28 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
          x_27.==(x_28)
        }));
        val x_30 = x_29.get;
        val x_31 = x_30._2;
        positionVar_9 = x_31
      }
    else
      ()
  }));
  data_10.update(6, (() => {
    val x_32 = resetData_0;
    val x_33 = x_32.asInstanceOf[scala.Any];
    bindingMut_4 = x_33;
    val x_34 = bindingMut_4;
    val x_35 = listValMut_5;
    x_35.reply(this, x_34);
    resetData_0 = ();
    positionVar_9 = 7
  }));
  data_10.update(7, (() => positionVar_9 = 8));
  data_10.update(8, (() => positionVar_9 = 9));
  data_10.update(9, (() => {
    val x_36 = iterMut_6;
    val x_37 = x_36.hasNext;
    if (x_37)
      {
        val x_38 = iterMut_6;
        val x_39 = x_38.next();
        listValMut_5 = x_39;
        positionVar_9 = 10
      }
    else
      positionVar_9 = 14
  }));
  data_10.update(10, (() => {
    val x_40 = listValMut_5;
    val x_41 = x_40.methodInfo;
    val x_42 = scala.`package`.Right.apply[scala.Nothing, scala.Int](0);
    val x_43 = x_41.==(x_42);
    if (x_43)
      positionVar_9 = 11
    else
      positionVar_9 = 13
  }));
  data_10.update(11, (() => positionVar_9 = 12));
  data_10.update(12, (() => {
    positionVar_9 = 2;
    val x_44 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
    val x_45 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_44, 6);
    val x_46 = scala.collection.immutable.Nil.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_45);
    resetData_1.prepend(x_46)
  }));
  data_10.update(13, (() => {
    val x_47 = listValMut_5;
    val x_48 = x_47.methodInfo;
    val x_49 = scala.`package`.Right.apply[scala.Nothing, scala.Int](0);
    val x_50 = x_48.==(x_49);
    val x_51 = x_50.`unary_!`;
    if (x_51)
      {
        val x_52 = listValMut_5;
        val x_53 = scala.collection.immutable.List.apply[meta.runtime.RequestMessage](x_52);
        val x_54 = this.addReceiveMessages(x_53);
        resetData_0 = x_54;
        positionVar_9 = 8
      }
    else
      ()
  }));
  data_10.update(14, (() => {
    val x_55 = iterMut_6;
    val x_56 = x_55.hasNext;
    val x_57 = x_56.`unary_!`;
    if (x_57)
      positionVar_9 = 15
    else
      ()
  }));
  data_10.update(15, (() => positionVar_9 = 16));
  data_10.update(16, (() => {
    val x_58 = bindingMut_7;
    val x_59 = x_58.<(1.0);
    if (x_59)
      positionVar_9 = 17
    else
      positionVar_9 = 20
  }));
  data_10.update(17, (() => {
    val x_60 = bindingMut_7;
    val x_61 = x_60.+(1);
    resetData_0 = x_61;
    val x_62 = resetData_0;
    val x_63 = x_62.asInstanceOf[scala.Double];
    bindingMut_7 = x_63;
    positionVar_9 = 18;
    unblockFlag_8 = false
  }));
  data_10.update(18, (() => positionVar_9 = 19));
  data_10.update(19, (() => {
    val x_64 = this.popRequestMessages;
    val x_65 = x_64.iterator;
    iterMut_6 = x_65;
    positionVar_9 = 9
  }));
  data_10.update(20, (() => {
    val x_66 = bindingMut_7;
    val x_67 = x_66.<(1.0);
    val x_68 = x_67.`unary_!`;
    if (x_68)
      positionVar_9 = 21
    else
      ()
  }));
  data_10.update(21, (() => positionVar_9 = 1));
  data_10.update(22, (() => positionVar_9 = 23));
  data_10.update(23, (() => {
    positionVar_9 = 24;
    unblockFlag_8 = false
  }));
  data_10.update(24, (() => positionVar_9 = 23));
  data_10.update(25, (() => {
    resetData_0 = 0.0;
    val x_69 = resetData_0;
    val x_70 = x_69.asInstanceOf[scala.Double];
    bindingMut_7 = x_70;
    positionVar_9 = 17
  }));
  data_10.update(26, (() => positionVar_9 = 23));
  data_10
}).apply();
  

  override def run(msgs: List[meta.runtime.Message]): (List[meta.runtime.Message], Int) = {
    addReceiveMessages(msgs)
    sendMessages.clear()
    unblockFlag_8 = true
    while (unblockFlag_8 && (positionVar_9 < 27)) {
      commands_71(positionVar_9)()
    }
    (sendMessages.toList, 1)
  }

    override def gotoHandleMessages(new_ir: Int = 5): meta.runtime.Actor = {
      // first entry, save the current IR to reflectionIR
      unblockFlag_8 = true

      if (reflectionIR_26 == -1){
        reflectionIR_26 = positionVar_9
        positionVar_9 = new_ir
      }

      while (positionVar_9 <= 20 && unblockFlag_8) {
        commands_71(positionVar_9)()
      }

      // reset instruction register when finishes processing
      if (positionVar_9 > 20) {
        positionVar_9 = reflectionIR_26
        reflectionIR_26 = -1
      }
      this
    }
    
override def SimClone(): Teacher = {
  val newAgent = new Teacher()

  newAgent
}

override def SimReset(): Unit = {
  positionVar_9 = 0
  
}

}
