package generated.meta.test.liftSome

class liftSomeSpec () extends meta.runtime.Actor {


  private var resetData_0: scala.Any = null;
  private val resetData_1 = scala.collection.mutable.ListBuffer.apply[scala.collection.immutable.List[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]]]();
  private var resetData_2: meta.runtime.ResponseMessage = null;
  private var bindingMut_3: scala.Boolean = false;
  private var bindingMut_4: scala.Double = 0.0;
  private var bindingMut_5: scala.Option[meta.test.liftSome.liftSomeUtil.Bar[meta.test.liftSome.liftSomeUtil.Foo]] = null;

  private var positionVar_7: scala.Int = 0;
  
  val commands_49 = (() => {
  val data_8 = new scala.Array[scala.Function0[scala.Unit]](13);
  data_8.update(0, (() => positionVar_7 = 1));
  data_8.update(1, (() => if (true)
    positionVar_7 = 2
  else
    positionVar_7 = 12));
  data_8.update(2, (() => {
    val x_9 = scala.util.Random.nextBoolean();
    resetData_0 = x_9;
    val x_10 = resetData_0;
    val x_11 = x_10.asInstanceOf[scala.Boolean];
    bindingMut_3 = x_11;
    val x_12 = bindingMut_3;
    val x_13 = x_12.asInstanceOf[scala.Boolean];
    val x_16 = if (x_13)
      {
        val x_14 = meta.test.liftSome.liftSomeUtil.Foo(95);
        val x_15 = meta.test.liftSome.liftSomeUtil.Bar.apply[meta.test.liftSome.liftSomeUtil.Foo](x_14);
        scala.Some.apply[meta.test.liftSome.liftSomeUtil.Bar[meta.test.liftSome.liftSomeUtil.Foo]](x_15)
      }
    else
      scala.None;
    resetData_0 = x_16;
    val x_17 = resetData_0;
    val x_18 = x_17.asInstanceOf[scala.Option[meta.test.liftSome.liftSomeUtil.Bar[meta.test.liftSome.liftSomeUtil.Foo]]];
    bindingMut_5 = x_18;
    val x_19 = bindingMut_5;
    val x_20 = x_19.asInstanceOf[scala.Option[meta.test.liftSome.liftSomeUtil.Bar[meta.test.liftSome.liftSomeUtil.Foo]]];
    scala.Predef.println(x_20);
    resetData_0 = ();
    resetData_0 = 0.0;
    val x_21 = resetData_0;
    val x_22 = x_21.asInstanceOf[scala.Double];
    bindingMut_4 = x_22;
    positionVar_7 = 3
  }));
  data_8.update(3, (() => {
    val x_23 = meta.classLifting.SpecialInstructions.Turn.toString();
    val x_24 = meta.runtime.SimRuntime.labelVals(x_23);
    val x_25 = bindingMut_4;
    val x_26 = x_25.asInstanceOf[scala.Double];
    val x_27 = 1.0.-(x_26);
    x_24.append(x_27);
    resetData_0 = ();
    positionVar_7 = 4;
    val x_28 = currentTurn;
    val x_29 = x_28.+(1);
    currentTurn = x_29
  }));
  data_8.update(4, (() => {
    val x_30 = meta.runtime.SimRuntime.proceedLabel;
    val x_31 = meta.classLifting.SpecialInstructions.Turn.toString();
    val x_32 = x_30(x_31);
    val x_33 = bindingMut_4;
    val x_34 = x_33.asInstanceOf[scala.Double];
    val x_35 = x_34.+(x_32);
    resetData_0 = x_35;
    val x_36 = resetData_0;
    val x_37 = x_36.asInstanceOf[scala.Double];
    bindingMut_4 = x_37;
    positionVar_7 = 5
  }));
  data_8.update(5, (() => {
    val x_38 = bindingMut_4;
    val x_39 = x_38.asInstanceOf[scala.Double];
    val x_40 = x_39.<(1.0);
    if (x_40)
      positionVar_7 = 3
    else
      positionVar_7 = 6
  }));
  data_8.update(6, (() => {
    val x_41 = bindingMut_4;
    val x_42 = x_41.asInstanceOf[scala.Double];
    val x_43 = x_42.<(1.0);
    val x_44 = x_43.`unary_!`;
    if (x_44)
      positionVar_7 = 7
    else
      ()
  }));
  data_8.update(7, (() => if (true)
    positionVar_7 = 2
  else
    positionVar_7 = 8));
  data_8.update(8, (() => {
    val x_45 = true.`unary_!`;
    if (x_45)
      positionVar_7 = 9
    else
      ()
  }));
  data_8.update(9, (() => positionVar_7 = 10));
  data_8.update(10, (() => {
    positionVar_7 = 11;
    val x_46 = currentTurn;
    val x_47 = x_46.+(1);
    currentTurn = x_47
  }));
  data_8.update(11, (() => positionVar_7 = 10));
  data_8.update(12, (() => {
    val x_48 = true.`unary_!`;
    if (x_48)
      positionVar_7 = 9
    else
      ()
  }));
  data_8
}).apply();
  
  override def run_until(until_50: scala.Int) : meta.runtime.Actor =  {
    while ({
      val x_51 = currentTurn;
      val x_52 = x_51.<=(until_50);
      x_52.&&({
        val x_53 = positionVar_7;
        val x_54 = commands_49.length;
        x_53.<(x_54)
      })
    }) 
      {
        val x_55 = positionVar_7;
        val x_56 = commands_49.apply(x_55);
        x_56.apply()
      }
    ;
    this
  }
}
