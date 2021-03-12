package generated.meta.test.SSO

class RandomServer () extends meta.runtime.Actor {

  val foo: Double = 30.0;
  private var resetData_0: scala.Any = null;
  private val resetData_1 = scala.collection.mutable.ListBuffer.apply[scala.collection.immutable.List[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]]]();
  private var resetData_2: meta.runtime.ResponseMessage = null;
  private var bindingMut_3: scala.Double = 0.0;
  private var bindingMut_4: scala.Any = null;
  private var listValMut_5: meta.runtime.RequestMessage = null;
  @transient private var iterMut_6: scala.collection.Iterator[meta.runtime.RequestMessage] = null;

  private var positionVar_8: scala.Int = 0;
  
  val commands_86 = (() => {
  val data_9 = new scala.Array[scala.Function0[scala.Unit]](18);
  data_9.update(0, (() => if (true)
    positionVar_8 = 1
  else
    positionVar_8 = 17));
  data_9.update(1, (() => {
    val x_10 = this.popRequestMessages;
    val x_11 = x_10.iterator;
    iterMut_6 = x_11;
    positionVar_8 = 2
  }));
  data_9.update(2, (() => {
    val x_12 = iterMut_6;
    val x_13 = x_12.asInstanceOf[scala.collection.Iterator[meta.runtime.RequestMessage]];
    val x_14 = x_13.hasNext;
    if (x_14)
      {
        val x_15 = iterMut_6;
        val x_16 = x_15.asInstanceOf[scala.collection.Iterator[meta.runtime.RequestMessage]];
        val x_17 = x_16.next();
        listValMut_5 = x_17;
        positionVar_8 = 3
      }
    else
      positionVar_8 = 8
  }));
  data_9.update(3, (() => {
    val x_18 = listValMut_5;
    val x_19 = x_18.asInstanceOf[meta.runtime.RequestMessage];
    val x_20 = x_19.methodId;
    val x_21 = x_20.==(5);
    val x_22 = x_21.`unary_!`;
    if (x_22)
      positionVar_8 = 4
    else
      positionVar_8 = 7
  }));
  data_9.update(4, (() => {
    val x_23 = listValMut_5;
    val x_24 = x_23.asInstanceOf[meta.runtime.RequestMessage];
    val x_25 = x_24.methodId;
    val x_26 = x_25.==(4);
    val x_27 = x_26.`unary_!`;
    if (x_27)
      {
        val x_28 = listValMut_5;
        val x_29 = x_28.asInstanceOf[meta.runtime.RequestMessage];
        val x_30 = scala.collection.immutable.List.apply[meta.runtime.RequestMessage](x_29);
        val x_31 = this.addReceiveMessages(x_30);
        resetData_0 = x_31;
        positionVar_8 = 5
      }
    else
      positionVar_8 = 6
  }));
  data_9.update(5, (() => positionVar_8 = 2));
  data_9.update(6, (() => {
    val x_32 = listValMut_5;
    val x_33 = x_32.asInstanceOf[meta.runtime.RequestMessage];
    val x_34 = x_33.methodId;
    val x_35 = x_34.==(4);
    if (x_35)
      {
        val x_36 = scala.util.Random.nextInt(20);
        resetData_0 = x_36;
        val x_37 = resetData_0;
        val x_38 = x_37.asInstanceOf[scala.Any];
        bindingMut_4 = x_38;
        val x_39 = bindingMut_4;
        val x_40 = x_39.asInstanceOf[scala.Any];
        val x_41 = listValMut_5;
        val x_42 = x_41.asInstanceOf[meta.runtime.RequestMessage];
        x_42.reply(this, x_40);
        resetData_0 = ();
        positionVar_8 = 5
      }
    else
      ()
  }));
  data_9.update(7, (() => {
    val x_43 = listValMut_5;
    val x_44 = x_43.asInstanceOf[meta.runtime.RequestMessage];
    val x_45 = x_44.methodId;
    val x_46 = x_45.==(5);
    if (x_46)
      {
        val x_47 = this.foo;
        resetData_0 = x_47;
        val x_48 = resetData_0;
        val x_49 = x_48.asInstanceOf[scala.Any];
        bindingMut_4 = x_49;
        val x_50 = bindingMut_4;
        val x_51 = x_50.asInstanceOf[scala.Any];
        val x_52 = listValMut_5;
        val x_53 = x_52.asInstanceOf[meta.runtime.RequestMessage];
        x_53.reply(this, x_51);
        resetData_0 = ();
        positionVar_8 = 5
      }
    else
      ()
  }));
  data_9.update(8, (() => {
    val x_54 = iterMut_6;
    val x_55 = x_54.asInstanceOf[scala.collection.Iterator[meta.runtime.RequestMessage]];
    val x_56 = x_55.hasNext;
    val x_57 = x_56.`unary_!`;
    if (x_57)
      {
        resetData_0 = 0.0;
        val x_58 = resetData_0;
        val x_59 = x_58.asInstanceOf[scala.Double];
        bindingMut_3 = x_59;
        positionVar_8 = 9
      }
    else
      ()
  }));
  data_9.update(9, (() => {
    val x_60 = meta.classLifting.SpecialInstructions.Turn.toString();
    val x_61 = meta.runtime.SimRuntime.labelVals(x_60);
    val x_62 = bindingMut_3;
    val x_63 = x_62.asInstanceOf[scala.Double];
    val x_64 = 1.0.-(x_63);
    x_61.append(x_64);
    resetData_0 = ();
    positionVar_8 = 10;
    val x_65 = currentTurn;
    val x_66 = x_65.+(1);
    currentTurn = x_66
  }));
  data_9.update(10, (() => {
    val x_67 = meta.runtime.SimRuntime.proceedLabel;
    val x_68 = meta.classLifting.SpecialInstructions.Turn.toString();
    val x_69 = x_67(x_68);
    val x_70 = bindingMut_3;
    val x_71 = x_70.asInstanceOf[scala.Double];
    val x_72 = x_71.+(x_69);
    resetData_0 = x_72;
    val x_73 = resetData_0;
    val x_74 = x_73.asInstanceOf[scala.Double];
    bindingMut_3 = x_74;
    positionVar_8 = 11
  }));
  data_9.update(11, (() => {
    val x_75 = bindingMut_3;
    val x_76 = x_75.asInstanceOf[scala.Double];
    val x_77 = x_76.<(1.0);
    if (x_77)
      positionVar_8 = 9
    else
      positionVar_8 = 12
  }));
  data_9.update(12, (() => {
    val x_78 = bindingMut_3;
    val x_79 = x_78.asInstanceOf[scala.Double];
    val x_80 = x_79.<(1.0);
    val x_81 = x_80.`unary_!`;
    if (x_81)
      positionVar_8 = 13
    else
      ()
  }));
  data_9.update(13, (() => if (true)
    positionVar_8 = 1
  else
    positionVar_8 = 14));
  data_9.update(14, (() => {
    val x_82 = true.`unary_!`;
    if (x_82)
      positionVar_8 = 15
    else
      ()
  }));
  data_9.update(15, (() => {
    positionVar_8 = 16;
    val x_83 = currentTurn;
    val x_84 = x_83.+(1);
    currentTurn = x_84
  }));
  data_9.update(16, (() => positionVar_8 = 15));
  data_9.update(17, (() => {
    val x_85 = true.`unary_!`;
    if (x_85)
      positionVar_8 = 15
    else
      ()
  }));
  data_9
}).apply();
  
  override def run_until(until_87: scala.Int) : meta.runtime.Actor =  {
    while ({
      val x_88 = currentTurn;
      val x_89 = x_88.<=(until_87);
      x_89.&&({
        val x_90 = positionVar_8;
        val x_91 = commands_86.length;
        x_90.<(x_91)
      })
    }) 
      {
        val x_92 = positionVar_8;
        val x_93 = commands_86.apply(x_92);
        x_93.apply()
      }
    ;
    this
  }
}
