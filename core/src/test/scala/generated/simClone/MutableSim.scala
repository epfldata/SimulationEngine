package generated.meta.test.simClone

class MutableSim() extends meta.runtime.Actor {

   var counter: Int = 0;
  private var  reflectionIR_23: Int = -1
private var resetData_0: scala.Any = null
private val resetData_1 = scala.collection.mutable.ListBuffer.apply[scala.collection.immutable.List[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]]]()
private var resetData_2: meta.runtime.ResponseMessage = null
private var listValMut_3: meta.runtime.RequestMessage = null
@transient private var iterMut_4: scala.collection.Iterator[meta.runtime.RequestMessage] = null
private var bindingMut_5: scala.Double = 0.0
private var bindingMut_6: scala.Int = 0
private var bindingMut_7: scala.Int = 0
private var unblockFlag_8: scala.Boolean = true
private var positionVar_9: scala.Int = 0
private 
  val commands_42 = (() => {
  val data_10 = new scala.Array[scala.Function0[scala.Unit]](15);
  data_10.update(0, (() => positionVar_9 = 1));
  data_10.update(1, (() => {
    val x_11 = this.counter;
    resetData_0 = x_11;
    val x_12 = resetData_0;
    val x_13 = x_12.asInstanceOf[scala.Int];
    bindingMut_7 = x_13;
    val x_14 = bindingMut_7;
    val x_15 = x_14.+(1);
    resetData_0 = x_15;
    val x_16 = resetData_0;
    val x_17 = x_16.asInstanceOf[scala.Int];
    bindingMut_6 = x_17;
    val x_18 = bindingMut_6;
    this.`counter_=`(x_18);
    resetData_0 = ();
    resetData_0 = 0.0;
    val x_19 = resetData_0;
    val x_20 = x_19.asInstanceOf[scala.Double];
    bindingMut_5 = x_20;
    positionVar_9 = 2
  }));
  data_10.update(2, (() => {
    val x_21 = bindingMut_5;
    val x_22 = x_21.+(1);
    resetData_0 = x_22;
    val x_23 = resetData_0;
    val x_24 = x_23.asInstanceOf[scala.Double];
    bindingMut_5 = x_24;
    positionVar_9 = 3;
    unblockFlag_8 = false
  }));
  data_10.update(3, (() => positionVar_9 = 4));
  data_10.update(4, (() => {
    val x_25 = this.popRequestMessages;
    val x_26 = x_25.iterator;
    iterMut_4 = x_26;
    positionVar_9 = 5
  }));
  data_10.update(5, (() => {
    val x_27 = iterMut_4;
    val x_28 = x_27.hasNext;
    val x_29 = x_28.`unary_!`;
    if (x_29)
      positionVar_9 = 6
    else
      positionVar_9 = 13
  }));
  data_10.update(6, (() => positionVar_9 = 7));
  data_10.update(7, (() => {
    val x_30 = bindingMut_5;
    val x_31 = x_30.<(1.0);
    if (x_31)
      positionVar_9 = 2
    else
      positionVar_9 = 8
  }));
  data_10.update(8, (() => {
    val x_32 = bindingMut_5;
    val x_33 = x_32.<(1.0);
    val x_34 = x_33.`unary_!`;
    if (x_34)
      positionVar_9 = 9
    else
      ()
  }));
  data_10.update(9, (() => positionVar_9 = 1));
  data_10.update(10, (() => positionVar_9 = 11));
  data_10.update(11, (() => {
    positionVar_9 = 12;
    unblockFlag_8 = false
  }));
  data_10.update(12, (() => positionVar_9 = 11));
  data_10.update(13, (() => {
    val x_35 = iterMut_4;
    val x_36 = x_35.hasNext;
    if (x_36)
      {
        val x_37 = iterMut_4;
        val x_38 = x_37.next();
        listValMut_3 = x_38;
        val x_39 = listValMut_3;
        val x_40 = scala.collection.immutable.List.apply[meta.runtime.RequestMessage](x_39);
        val x_41 = this.addReceiveMessages(x_40);
        resetData_0 = x_41;
        positionVar_9 = 5
      }
    else
      ()
  }));
  data_10.update(14, (() => positionVar_9 = 11));
  data_10
}).apply();
  

  override def run(msgs: List[meta.runtime.Message]): (List[meta.runtime.Message], Int) = {
    addReceiveMessages(msgs)
    sendMessages.clear()
    unblockFlag_8 = true
    while (unblockFlag_8 && (positionVar_9 < 15)) {
      commands_42(positionVar_9)()
    }
    (sendMessages.toList, 1)
  }

    override def gotoHandleMessages(new_ir: Int = 3): meta.runtime.Actor = {
      // first entry, save the current IR to reflectionIR
      unblockFlag_8 = true

      if (reflectionIR_23 == -1){
        reflectionIR_23 = positionVar_9
        positionVar_9 = new_ir
      }

      while (positionVar_9 <= 6 && unblockFlag_8) {
        commands_42(positionVar_9)()
      }

      // reset instruction register when finishes processing
      if (positionVar_9 > 6) {
        positionVar_9 = reflectionIR_23
        reflectionIR_23 = -1
      }
      this
    }
    
override def SimClone(): MutableSim = {
  val newAgent = new MutableSim()
  newAgent.counter = counter
  newAgent
}

override def SimReset(): Unit = {
  positionVar_9 = 0
  val newAgent = new MutableSim()
  counter = newAgent.counter
}

}
