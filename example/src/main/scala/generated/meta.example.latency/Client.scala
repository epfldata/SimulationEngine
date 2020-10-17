package generated.meta.example.latency

class Client (var server: generated.meta.example.latency.Server, var reqTime: Double, var replyTime: Double) extends meta.deep.runtime.Actor {
  var sentTime: Double = 0.0
  var receivedTime: Double = 0.0
  private var resetData_0: scala.Any = null;
  private val resetData_1 = scala.collection.mutable.ListBuffer.apply[scala.collection.immutable.List[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]]]();
  private var resetData_2: meta.deep.runtime.ResponseMessage = null;
  private var bindingMut_3: scala.Long = 0L;
  private var bindingMut_4: generated.meta.example.latency.Server = null;
  private var bindingMut_5: java.lang.String = null;
  private var bindingMut_6: java.lang.String = null;
  private var bindingMut_7: scala.Long = 0L;
  private var bindingMut_8: java.lang.String = null;
  private var bindingMut_9: scala.Double = 0.0;
  private var bindingMut_10: java.lang.String = null;
  private var bindingMut_11: java.lang.String = null;
  private var bindingMut_12: scala.Long = 0L;
  private var positionVar_14: scala.Int = 0;
  
  val commands_62 = (() => {
  val data_15 = new scala.Array[scala.Function0[scala.Unit]](12);
  data_15.update(0, (() => positionVar_14 = 1));
  data_15.update(1, (() => if (true)
    positionVar_14 = 2
  else
    positionVar_14 = 11));
  data_15.update(2, (() => {
    val x_16 = this.id;
    resetData_0 = x_16;
    val x_17 = resetData_0;
    val x_18 = x_17.asInstanceOf[scala.Long];
    bindingMut_7 = x_18;
    val x_19 = bindingMut_7;
    val x_20 = x_19.asInstanceOf[scala.Long];
    val x_21 = "Client ".+(x_20);
    resetData_0 = x_21;
    val x_22 = resetData_0;
    val x_23 = x_22.asInstanceOf[java.lang.String];
    bindingMut_6 = x_23;
    val x_24 = bindingMut_6;
    val x_25 = x_24.asInstanceOf[java.lang.String];
    val x_26 = x_25.+(" processing ");
    resetData_0 = x_26;
    val x_27 = resetData_0;
    val x_28 = x_27.asInstanceOf[java.lang.String];
    bindingMut_5 = x_28;
    val x_29 = bindingMut_5;
    val x_30 = x_29.asInstanceOf[java.lang.String];
    scala.Predef.println(x_30);
    resetData_0 = ();
    val x_31 = this.server;
    resetData_0 = x_31;
    val x_32 = resetData_0;
    val x_33 = x_32.asInstanceOf[generated.meta.example.latency.Server];
    bindingMut_4 = x_33;
    val x_34 = this.id;
    resetData_0 = x_34;
    val x_35 = resetData_0;
    val x_36 = x_35.asInstanceOf[scala.Long];
    bindingMut_3 = x_36;
    val x_37 = ((this): meta.deep.runtime.Actor).id;
    val x_39 = {
      val x_38 = bindingMut_4;
      x_38.asInstanceOf[generated.meta.example.latency.Server]
    };
    val x_40 = x_39.id;
    val x_41 = bindingMut_3;
    val x_42 = x_41.asInstanceOf[scala.Long];
    val x_43 = scala.collection.immutable.Nil.::[scala.Any](x_42);
    val x_44 = scala.collection.immutable.Nil.::[scala.collection.immutable.List[scala.Any]](x_43);
    val x_45 = meta.deep.runtime.RequestMessage.apply(x_37, x_40, 0, x_44);
    ((this): meta.deep.runtime.Actor).sendMessage(x_45);
    val x_46 = x_45.sessionId;
    ((this): meta.deep.runtime.Actor).setMessageResponseHandler(x_46, ((response_47: meta.deep.runtime.Message) => {
      val x_48 = response_47.asInstanceOf[meta.deep.runtime.ResponseMessage];
      resetData_2 = x_48
    }));
    resetData_0 = null;
    positionVar_14 = 3
  }));
  data_15.update(3, (() => {
    positionVar_14 = 4;
    val x_49 = currentTurn;
    val x_50 = x_49.+(1);
    currentTurn = x_50
  }));
  data_15.update(4, (() => {
    val x_51 = resetData_2;
    val x_52 = x_51.==(null);
    if (x_52)
      {
        val x_53 = meta.deep.runtime.Actor.labelVals("turn");
        x_53.append(1.0);
        positionVar_14 = 3
      }
    else
      positionVar_14 = 5
  }));
  data_15.update(5, (() => {
    val x_54 = resetData_2;
    val x_55 = x_54.!=(null);
    if (x_55)
      {
        val x_56 = resetData_2;
        val x_57 = x_56.arg;
        resetData_0 = x_57;
        resetData_2 = null;
        positionVar_14 = 6
      }
    else
      ()
  }));
  data_15.update(6, (() => if (true)
    positionVar_14 = 2
  else
    positionVar_14 = 7));
  data_15.update(7, (() => {
    val x_58 = true.`unary_!`;
    if (x_58)
      positionVar_14 = 8
    else
      ()
  }));
  data_15.update(8, (() => positionVar_14 = 9));
  data_15.update(9, (() => {
    positionVar_14 = 10;
    val x_59 = currentTurn;
    val x_60 = x_59.+(1);
    currentTurn = x_60
  }));
  data_15.update(10, (() => positionVar_14 = 9));
  data_15.update(11, (() => {
    val x_61 = true.`unary_!`;
    if (x_61)
      positionVar_14 = 8
    else
      ()
  }));
  data_15
}).apply();
  
  override def run_until(until_63: scala.Int) : meta.deep.runtime.Actor =  {
    while ({
      val x_64 = currentTurn;
      val x_65 = x_64.<=(until_63);
      x_65.&&({
        val x_66 = positionVar_14;
        val x_67 = commands_62.length;
        x_66.<(x_67)
      })
    }) 
      {
        val x_68 = positionVar_14;
        val x_69 = commands_62.apply(x_68);
        x_69.apply()
      }
    ;
    this
  }
}
