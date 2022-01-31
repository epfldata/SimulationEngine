package generated.meta.test.newSim

class NewSim () extends meta.runtime.Actor {


  private var  reflectionIR_14: Int = -1
  var resetData_0: scala.Any = null
  val resetData_1 = scala.collection.mutable.ListBuffer.apply[scala.collection.immutable.List[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]]]()
  var resetData_2: meta.runtime.ResponseMessage = null
  var listValMut_3: meta.runtime.RequestMessage = null
  @transient var iterMut_4: scala.collection.Iterator[meta.runtime.RequestMessage] = null
  var bindingMut_5: scala.Double = 0.0
  var bindingMut_6: java.lang.String = null
  var bindingMut_7: scala.Long = 0L
  var bindingMut_8: generated.meta.test.newSim.NewSim = null
  var bindingMut_9: java.lang.String = null
  var bindingMut_10: scala.Long = 0L
  var unblockFlag_11: scala.Boolean = true
  var positionVar_12: scala.Int = 0
  
  val commands_57 = (() => {
  val data_13 = new scala.Array[scala.Function0[scala.Unit]](15);
  data_13.update(0, (() => positionVar_12 = 1));
  data_13.update(1, (() => {
    val x_14 = this.id;
    resetData_0 = x_14;
    val x_15 = resetData_0;
    val x_16 = x_15.asInstanceOf[scala.Long];
    bindingMut_10 = x_16;
    val x_17 = bindingMut_10;
    val x_18 = "This is agent ".+(x_17);
    resetData_0 = x_18;
    val x_19 = resetData_0;
    val x_20 = x_19.asInstanceOf[java.lang.String];
    bindingMut_9 = x_20;
    val x_21 = bindingMut_9;
    scala.Predef.println(x_21);
    resetData_0 = ();
    val x_22 = new generated.meta.test.newSim.NewSim();
    meta.runtime.SimRuntime.newActors.append(x_22);
    resetData_0 = x_22;
    val x_23 = resetData_0;
    val x_24 = x_23.asInstanceOf[generated.meta.test.newSim.NewSim];
    bindingMut_8 = x_24;
    val x_25 = bindingMut_8;
    val x_26 = x_25.id;
    resetData_0 = x_26;
    val x_27 = resetData_0;
    val x_28 = x_27.asInstanceOf[scala.Long];
    bindingMut_7 = x_28;
    val x_29 = bindingMut_7;
    val x_30 = "Create new agent ".+(x_29);
    resetData_0 = x_30;
    val x_31 = resetData_0;
    val x_32 = x_31.asInstanceOf[java.lang.String];
    bindingMut_6 = x_32;
    val x_33 = bindingMut_6;
    scala.Predef.println(x_33);
    resetData_0 = ();
    resetData_0 = 0.0;
    val x_34 = resetData_0;
    val x_35 = x_34.asInstanceOf[scala.Double];
    bindingMut_5 = x_35;
    positionVar_12 = 2
  }));
  data_13.update(2, (() => {
    val x_36 = bindingMut_5;
    val x_37 = x_36.+(1);
    resetData_0 = x_37;
    val x_38 = resetData_0;
    val x_39 = x_38.asInstanceOf[scala.Double];
    bindingMut_5 = x_39;
    positionVar_12 = 3;
    unblockFlag_11 = false
  }));
  data_13.update(3, (() => positionVar_12 = 4));
  data_13.update(4, (() => {
    val x_40 = this.popRequestMessages;
    val x_41 = x_40.iterator;
    iterMut_4 = x_41;
    positionVar_12 = 5
  }));
  data_13.update(5, (() => {
    val x_42 = iterMut_4;
    val x_43 = x_42.hasNext;
    val x_44 = x_43.`unary_!`;
    if (x_44)
      positionVar_12 = 6
    else
      positionVar_12 = 13
  }));
  data_13.update(6, (() => positionVar_12 = 7));
  data_13.update(7, (() => {
    val x_45 = bindingMut_5;
    val x_46 = x_45.<(1.0);
    if (x_46)
      positionVar_12 = 2
    else
      positionVar_12 = 8
  }));
  data_13.update(8, (() => {
    val x_47 = bindingMut_5;
    val x_48 = x_47.<(1.0);
    val x_49 = x_48.`unary_!`;
    if (x_49)
      positionVar_12 = 9
    else
      ()
  }));
  data_13.update(9, (() => positionVar_12 = 1));
  data_13.update(10, (() => positionVar_12 = 11));
  data_13.update(11, (() => {
    positionVar_12 = 12;
    unblockFlag_11 = false
  }));
  data_13.update(12, (() => positionVar_12 = 11));
  data_13.update(13, (() => {
    val x_50 = iterMut_4;
    val x_51 = x_50.hasNext;
    if (x_51)
      {
        val x_52 = iterMut_4;
        val x_53 = x_52.next();
        listValMut_3 = x_53;
        val x_54 = listValMut_3;
        val x_55 = scala.collection.immutable.List.apply[meta.runtime.RequestMessage](x_54);
        val x_56 = this.addReceiveMessages(x_55);
        resetData_0 = x_56;
        positionVar_12 = 5
      }
    else
      ()
  }));
  data_13.update(14, (() => positionVar_12 = 11));
  data_13
}).apply();
  

  override def run(msgs: List[meta.runtime.Message]): (List[meta.runtime.Message], Int) = {
    addReceiveMessages(msgs)
    sendMessages.clear()
    unblockFlag_11 = true
    while (unblockFlag_11 && (positionVar_12 < 15)) {
      commands_57(positionVar_12)()
    }
    (sendMessages.toList, 1)
  }
  
    override def gotoHandleMessages(new_ir: Int = 3): meta.runtime.Actor = {
      // first entry, save the current IR to reflectionIR
      unblockFlag_11 = true

      if (reflectionIR_14 == -1){
        reflectionIR_14 = positionVar_12
        positionVar_12 = new_ir
      }

      while (positionVar_12 <= 6 && unblockFlag_11) {
        commands_57(positionVar_12)()
      }

      // reset instruction register when finishes processing
      if (positionVar_12 > 6) {
        positionVar_12 = reflectionIR_14
        reflectionIR_14 = -1
      }
      this
    }
    
}
