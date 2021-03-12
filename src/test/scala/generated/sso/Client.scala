package generated.meta.test.SSO

class Client (val s: generated.meta.test.SSO.Server) extends meta.runtime.Actor {


  private var resetData_0: scala.Any = null;
  private val resetData_1 = scala.collection.mutable.ListBuffer.apply[scala.collection.immutable.List[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]]]();
  private var resetData_2: meta.runtime.ResponseMessage = null;
  private val x_3 = scala.collection.mutable.ListBuffer.apply[scala.Any]();
  private var methodArgsMut_4: scala.Int = 0;
  private var bindingMut_5: java.lang.String = null;
  private var bindingMut_6: scala.Double = 0.0;
  private var bindingMut_7: java.lang.String = null;
  private var bindingMut_8: scala.Int = 0;
  private var bindingMut_9: generated.meta.test.SSO.Server = null;

  private var positionVar_11: scala.Int = 0;
  
  val commands_68 = (() => {
  val data_12 = new scala.Array[scala.Function0[scala.Unit]](11);
  data_12.update(0, (() => if (true)
    positionVar_11 = 1
  else
    positionVar_11 = 10));
  data_12.update(1, (() => {
    val x_13 = this.s;
    resetData_0 = x_13;
    val x_14 = resetData_0;
    val x_15 = x_14.asInstanceOf[generated.meta.test.SSO.Server];
    bindingMut_9 = x_15;
    x_3.prepend(3);
    val x_16 = 3.asInstanceOf[scala.Int];
    methodArgsMut_4 = x_16;
    val x_17 = methodArgsMut_4;
    val x_18 = x_17.asInstanceOf[scala.Int];
    val x_19 = "Stateless mtd invoked! Args: ".+(x_18);
    resetData_0 = x_19;
    val x_20 = resetData_0;
    val x_21 = x_20.asInstanceOf[java.lang.String];
    bindingMut_5 = x_21;
    val x_22 = bindingMut_5;
    val x_23 = x_22.asInstanceOf[java.lang.String];
    scala.Predef.println(x_23);
    resetData_0 = ();
    val x_24 = methodArgsMut_4;
    val x_25 = x_24.asInstanceOf[scala.Int];
    val x_26 = (42).+(x_25);
    resetData_0 = x_26;
    x_3.remove(0);
    val x_27 = x_3.isEmpty;
    val x_28 = x_27.`unary_!`;
    if (x_28)
      {
        val x_29 = x_3(0);
        val x_30 = x_29.asInstanceOf[scala.Int];
        methodArgsMut_4 = x_30
      }
    else
      ();
    val x_31 = resetData_0;
    val x_32 = x_31.asInstanceOf[scala.Int];
    bindingMut_8 = x_32;
    val x_33 = bindingMut_8;
    val x_34 = x_33.asInstanceOf[scala.Int];
    val x_35 = "Answer of the stateless method is ".+(x_34);
    resetData_0 = x_35;
    val x_36 = resetData_0;
    val x_37 = x_36.asInstanceOf[java.lang.String];
    bindingMut_7 = x_37;
    val x_38 = bindingMut_7;
    val x_39 = x_38.asInstanceOf[java.lang.String];
    scala.Predef.println(x_39);
    resetData_0 = ();
    resetData_0 = 0.0;
    val x_40 = resetData_0;
    val x_41 = x_40.asInstanceOf[scala.Double];
    bindingMut_6 = x_41;
    positionVar_11 = 2
  }));
  data_12.update(2, (() => {
    val x_42 = meta.classLifting.SpecialInstructions.Turn.toString();
    val x_43 = meta.runtime.SimRuntime.labelVals(x_42);
    val x_44 = bindingMut_6;
    val x_45 = x_44.asInstanceOf[scala.Double];
    val x_46 = 1.0.-(x_45);
    x_43.append(x_46);
    resetData_0 = ();
    positionVar_11 = 3;
    val x_47 = currentTurn;
    val x_48 = x_47.+(1);
    currentTurn = x_48
  }));
  data_12.update(3, (() => {
    val x_49 = meta.runtime.SimRuntime.proceedLabel;
    val x_50 = meta.classLifting.SpecialInstructions.Turn.toString();
    val x_51 = x_49(x_50);
    val x_52 = bindingMut_6;
    val x_53 = x_52.asInstanceOf[scala.Double];
    val x_54 = x_53.+(x_51);
    resetData_0 = x_54;
    val x_55 = resetData_0;
    val x_56 = x_55.asInstanceOf[scala.Double];
    bindingMut_6 = x_56;
    positionVar_11 = 4
  }));
  data_12.update(4, (() => {
    val x_57 = bindingMut_6;
    val x_58 = x_57.asInstanceOf[scala.Double];
    val x_59 = x_58.<(1.0);
    if (x_59)
      positionVar_11 = 2
    else
      positionVar_11 = 5
  }));
  data_12.update(5, (() => {
    val x_60 = bindingMut_6;
    val x_61 = x_60.asInstanceOf[scala.Double];
    val x_62 = x_61.<(1.0);
    val x_63 = x_62.`unary_!`;
    if (x_63)
      positionVar_11 = 6
    else
      ()
  }));
  data_12.update(6, (() => if (true)
    positionVar_11 = 1
  else
    positionVar_11 = 7));
  data_12.update(7, (() => {
    val x_64 = true.`unary_!`;
    if (x_64)
      positionVar_11 = 8
    else
      ()
  }));
  data_12.update(8, (() => {
    positionVar_11 = 9;
    val x_65 = currentTurn;
    val x_66 = x_65.+(1);
    currentTurn = x_66
  }));
  data_12.update(9, (() => positionVar_11 = 8));
  data_12.update(10, (() => {
    val x_67 = true.`unary_!`;
    if (x_67)
      positionVar_11 = 8
    else
      ()
  }));
  data_12
}).apply();
  
  override def run_until(until_69: scala.Int) : meta.runtime.Actor =  {
    while ({
      val x_70 = currentTurn;
      val x_71 = x_70.<=(until_69);
      x_71.&&({
        val x_72 = positionVar_11;
        val x_73 = commands_68.length;
        x_72.<(x_73)
      })
    }) 
      {
        val x_74 = positionVar_11;
        val x_75 = commands_68.apply(x_74);
        x_75.apply()
      }
    ;
    this
  }
}
