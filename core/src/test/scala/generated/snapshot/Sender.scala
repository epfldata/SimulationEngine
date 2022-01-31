package generated.meta.test.snapshot

class Sender (val r: generated.meta.test.snapshot.Receiver) extends meta.runtime.Actor {


  private var  reflectionIR_16: Int = -1
  var resetData_0: scala.Any = null
  val resetData_1 = scala.collection.mutable.ListBuffer.apply[scala.collection.immutable.List[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]]]()
  var resetData_2: meta.runtime.ResponseMessage = null
  var listValMut_3: meta.runtime.RequestMessage = null
  @transient var iterMut_4: scala.collection.Iterator[meta.runtime.RequestMessage] = null
  var bindingMut_5: scala.Double = 0.0
  var unblockFlag_6: scala.Boolean = true
  var positionVar_7: scala.Int = 0
  
  val commands_48 = (() => {
  val data_8 = new scala.Array[scala.Function0[scala.Unit]](15);
  data_8.update(0, (() => positionVar_7 = 1));
  data_8.update(1, (() => {
    val receiver_9 = this.r;
    val x_10 = ((this): meta.runtime.Actor).id;
    val x_11 = receiver_9.id;
    val x_12 = scala.`package`.Right.apply[scala.Nothing, scala.Int](17);
    val x_13 = scala.collection.immutable.Nil.::[scala.collection.immutable.List[scala.Any]](((scala.collection.immutable.Nil): scala.collection.immutable.List[scala.Any]));
    val x_14 = meta.runtime.RequestMessage.apply(x_10, x_11, false, x_12, x_13);
    val x_15 = x_14.sessionId;
    val x_16 = meta.runtime.Future.apply$default$2[scala.Unit];
    val x_17 = meta.runtime.Future.apply[scala.Unit](x_15, x_16);
    var v_18: meta.runtime.Future[scala.Unit] = x_17;
    ((this): meta.runtime.Actor).sendMessage(x_14);
    val x_19 = x_14.sessionId;
    ((this): meta.runtime.Actor).setMessageResponseHandler(x_19, ((response_20: meta.runtime.Message) => {
      val x_21 = v_18;
      val x_22 = response_20.asInstanceOf[meta.runtime.ResponseMessage];
      val x_23 = x_22.arg;
      x_23.asInstanceOf[scala.Unit];
      x_21.setValue(())
    }));
    val x_24 = v_18;
    resetData_0 = x_24;
    resetData_0 = 0.0;
    val x_25 = resetData_0;
    val x_26 = x_25.asInstanceOf[scala.Double];
    bindingMut_5 = x_26;
    positionVar_7 = 2
  }));
  data_8.update(2, (() => {
    val x_27 = bindingMut_5;
    val x_28 = x_27.+(1);
    resetData_0 = x_28;
    val x_29 = resetData_0;
    val x_30 = x_29.asInstanceOf[scala.Double];
    bindingMut_5 = x_30;
    positionVar_7 = 3;
    unblockFlag_6 = false
  }));
  data_8.update(3, (() => positionVar_7 = 4));
  data_8.update(4, (() => {
    val x_31 = this.popRequestMessages;
    val x_32 = x_31.iterator;
    iterMut_4 = x_32;
    positionVar_7 = 5
  }));
  data_8.update(5, (() => {
    val x_33 = iterMut_4;
    val x_34 = x_33.hasNext;
    val x_35 = x_34.`unary_!`;
    if (x_35)
      positionVar_7 = 6
    else
      positionVar_7 = 13
  }));
  data_8.update(6, (() => positionVar_7 = 7));
  data_8.update(7, (() => {
    val x_36 = bindingMut_5;
    val x_37 = x_36.<(1.0);
    if (x_37)
      positionVar_7 = 2
    else
      positionVar_7 = 8
  }));
  data_8.update(8, (() => {
    val x_38 = bindingMut_5;
    val x_39 = x_38.<(1.0);
    val x_40 = x_39.`unary_!`;
    if (x_40)
      positionVar_7 = 9
    else
      ()
  }));
  data_8.update(9, (() => positionVar_7 = 1));
  data_8.update(10, (() => positionVar_7 = 11));
  data_8.update(11, (() => {
    positionVar_7 = 12;
    unblockFlag_6 = false
  }));
  data_8.update(12, (() => positionVar_7 = 11));
  data_8.update(13, (() => {
    val x_41 = iterMut_4;
    val x_42 = x_41.hasNext;
    if (x_42)
      {
        val x_43 = iterMut_4;
        val x_44 = x_43.next();
        listValMut_3 = x_44;
        val x_45 = listValMut_3;
        val x_46 = scala.collection.immutable.List.apply[meta.runtime.RequestMessage](x_45);
        val x_47 = this.addReceiveMessages(x_46);
        resetData_0 = x_47;
        positionVar_7 = 5
      }
    else
      ()
  }));
  data_8.update(14, (() => positionVar_7 = 11));
  data_8
}).apply();
  

  override def run(msgs: List[meta.runtime.Message]): (List[meta.runtime.Message], Int) = {
    addReceiveMessages(msgs)
    sendMessages.clear()
    unblockFlag_6 = true
    while (unblockFlag_6 && (positionVar_7 < 15)) {
      commands_48(positionVar_7)()
    }
    (sendMessages.toList, 1)
  }
  
    override def gotoHandleMessages(new_ir: Int = 3): meta.runtime.Actor = {
      // first entry, save the current IR to reflectionIR
      unblockFlag_6 = true

      if (reflectionIR_16 == -1){
        reflectionIR_16 = positionVar_7
        positionVar_7 = new_ir
      }

      while (positionVar_7 <= 6 && unblockFlag_6) {
        commands_48(positionVar_7)()
      }

      // reset instruction register when finishes processing
      if (positionVar_7 > 6) {
        positionVar_7 = reflectionIR_16
        reflectionIR_16 = -1
      }
      this
    }
    
}
