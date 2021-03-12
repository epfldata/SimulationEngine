package generated.meta.test.inheritance

class child2 (val c: generated.meta.test.inheritance.child1) extends meta.runtime.Actor {


  private var resetData_0: scala.Any = null;
  private val resetData_1 = scala.collection.mutable.ListBuffer.apply[scala.collection.immutable.List[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]]]();
  private var resetData_2: meta.runtime.ResponseMessage = null;
  private var bindingMut_3: scala.Double = 0.0;
  private var bindingMut_4: generated.meta.test.inheritance.child1 = null;

  private var positionVar_6: scala.Int = 0;
  
  val commands_56 = (() => {
  val data_7 = new scala.Array[scala.Function0[scala.Unit]](14);
  data_7.update(0, (() => if (true)
    positionVar_6 = 1
  else
    positionVar_6 = 13));
  data_7.update(1, (() => {
    scala.Predef.println("This is child 2! sends msg to c1");
    resetData_0 = ();
    val x_8 = this.c;
    resetData_0 = x_8;
    val x_9 = resetData_0;
    val x_10 = x_9.asInstanceOf[generated.meta.test.inheritance.child1];
    bindingMut_4 = x_10;
    val x_11 = ((this): meta.runtime.Actor).id;
    val x_13 = {
      val x_12 = bindingMut_4;
      x_12.asInstanceOf[generated.meta.test.inheritance.child1]
    };
    val x_14 = x_13.id;
    val x_15 = scala.collection.immutable.Nil.::[scala.collection.immutable.List[scala.Any]](((scala.collection.immutable.Nil): scala.collection.immutable.List[scala.Any]));
    val x_16 = meta.runtime.RequestMessage.apply(x_11, x_14, 5, x_15);
    ((this): meta.runtime.Actor).sendMessage(x_16);
    val x_17 = x_16.sessionId;
    ((this): meta.runtime.Actor).setMessageResponseHandler(x_17, ((response_18: meta.runtime.Message) => {
      val x_19 = response_18.asInstanceOf[meta.runtime.ResponseMessage];
      resetData_2 = x_19
    }));
    resetData_0 = null;
    positionVar_6 = 2
  }));
  data_7.update(2, (() => {
    positionVar_6 = 3;
    val x_20 = currentTurn;
    val x_21 = x_20.+(1);
    currentTurn = x_21
  }));
  data_7.update(3, (() => {
    val x_22 = resetData_2;
    val x_23 = x_22.==(null);
    if (x_23)
      positionVar_6 = 2
    else
      positionVar_6 = 4
  }));
  data_7.update(4, (() => {
    val x_24 = resetData_2;
    val x_25 = x_24.!=(null);
    if (x_25)
      {
        val x_26 = resetData_2;
        val x_27 = x_26.arg;
        resetData_0 = x_27;
        resetData_2 = null;
        resetData_0 = 0.0;
        val x_28 = resetData_0;
        val x_29 = x_28.asInstanceOf[scala.Double];
        bindingMut_3 = x_29;
        positionVar_6 = 5
      }
    else
      ()
  }));
  data_7.update(5, (() => {
    val x_30 = meta.classLifting.SpecialInstructions.Turn.toString();
    val x_31 = meta.runtime.SimRuntime.labelVals(x_30);
    val x_32 = bindingMut_3;
    val x_33 = x_32.asInstanceOf[scala.Double];
    val x_34 = 1.0.-(x_33);
    x_31.append(x_34);
    resetData_0 = ();
    positionVar_6 = 6;
    val x_35 = currentTurn;
    val x_36 = x_35.+(1);
    currentTurn = x_36
  }));
  data_7.update(6, (() => {
    val x_37 = meta.runtime.SimRuntime.proceedLabel;
    val x_38 = meta.classLifting.SpecialInstructions.Turn.toString();
    val x_39 = x_37(x_38);
    val x_40 = bindingMut_3;
    val x_41 = x_40.asInstanceOf[scala.Double];
    val x_42 = x_41.+(x_39);
    resetData_0 = x_42;
    val x_43 = resetData_0;
    val x_44 = x_43.asInstanceOf[scala.Double];
    bindingMut_3 = x_44;
    positionVar_6 = 7
  }));
  data_7.update(7, (() => {
    val x_45 = bindingMut_3;
    val x_46 = x_45.asInstanceOf[scala.Double];
    val x_47 = x_46.<(1.0);
    if (x_47)
      positionVar_6 = 5
    else
      positionVar_6 = 8
  }));
  data_7.update(8, (() => {
    val x_48 = bindingMut_3;
    val x_49 = x_48.asInstanceOf[scala.Double];
    val x_50 = x_49.<(1.0);
    val x_51 = x_50.`unary_!`;
    if (x_51)
      positionVar_6 = 9
    else
      ()
  }));
  data_7.update(9, (() => if (true)
    positionVar_6 = 1
  else
    positionVar_6 = 10));
  data_7.update(10, (() => {
    val x_52 = true.`unary_!`;
    if (x_52)
      positionVar_6 = 11
    else
      ()
  }));
  data_7.update(11, (() => {
    positionVar_6 = 12;
    val x_53 = currentTurn;
    val x_54 = x_53.+(1);
    currentTurn = x_54
  }));
  data_7.update(12, (() => positionVar_6 = 11));
  data_7.update(13, (() => {
    val x_55 = true.`unary_!`;
    if (x_55)
      positionVar_6 = 11
    else
      ()
  }));
  data_7
}).apply();
  
  override def run_until(until_57: scala.Int) : meta.runtime.Actor =  {
    while ({
      val x_58 = currentTurn;
      val x_59 = x_58.<=(until_57);
      x_59.&&({
        val x_60 = positionVar_6;
        val x_61 = commands_56.length;
        x_60.<(x_61)
      })
    }) 
      {
        val x_62 = positionVar_6;
        val x_63 = commands_56.apply(x_62);
        x_63.apply()
      }
    ;
    this
  }
}
